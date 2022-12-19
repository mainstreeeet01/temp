package com.example.mobileprogramming;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobileprogramming.data.LocationData;
import com.example.mobileprogramming.data.MidLocationData;
import com.example.mobileprogramming.databinding.ActivityMainBinding;
import com.example.mobileprogramming.viewmodel.MainViewModel;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private GpsTracker gpsTracker;

    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    private TextToSpeech tts;

    MainViewModel mainViewModel;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        initViewModel();
        binding.setViewModel(mainViewModel);

        initVisible();
        readExcel();

        initObserver();

        searchLocation();
        initTTS();
    }

    public void initVisible() {
        if (mainViewModel.visibleArr.getValue() != null) {
            mainViewModel.visibleArr.getValue().put(SharedPreferenceManager.KEY_VISIBLE_WEEK, SharedPreferenceManager.getBoolean(this, SharedPreferenceManager.KEY_VISIBLE_WEEK));
            mainViewModel.visibleArr.getValue().put(SharedPreferenceManager.KEY_VISIBLE_DUST, SharedPreferenceManager.getBoolean(this, SharedPreferenceManager.KEY_VISIBLE_DUST));
            mainViewModel.visibleArr.getValue().put(SharedPreferenceManager.KEY_VISIBLE_SUN, SharedPreferenceManager.getBoolean(this, SharedPreferenceManager.KEY_VISIBLE_SUN));
            mainViewModel.visibleArr.getValue().put(SharedPreferenceManager.KEY_VISIBLE_UV, SharedPreferenceManager.getBoolean(this, SharedPreferenceManager.KEY_VISIBLE_UV));
            mainViewModel.visibleArr.getValue().put(SharedPreferenceManager.KEY_VISIBLE_NEWS, SharedPreferenceManager.getBoolean(this, SharedPreferenceManager.KEY_VISIBLE_NEWS));
            mainViewModel.visibleArr.getValue().put(SharedPreferenceManager.KEY_VISIBLE_RADAR, SharedPreferenceManager.getBoolean(this, SharedPreferenceManager.KEY_VISIBLE_RADAR));
        }
    }

    public void initTTS() {
        tts = new TextToSpeech(this, i -> {
            if (i != TextToSpeech.ERROR) {
                tts.setLanguage(Locale.KOREAN);
            }
        });

        binding.vgTitle.imgSpeak.setOnClickListener(view -> {
            tts.setPitch(1.0f);
            tts.setSpeechRate(1.0f);

            String text = makeSpeachText();

            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        });

    }

    private String makeSpeachText() {
        String text = "";
        if (Objects.equals(SharedPreferenceManager.getString(this, SharedPreferenceManager.KEY_TODAY_DATA), String.valueOf(true))) {
            text += String.format("현재 기온은 %s도 오늘의 최고 기온은 %s도 최저 기온은 %s도이고 하늘은 %s입니다 ", mainViewModel.ultraShortForecastData.getValue().curTemp, mainViewModel.ultraShortForecastData.getValue().maxTemp, mainViewModel.ultraShortForecastData.getValue().minTemp, mainViewModel.ultraShortForecastData.getValue().getSkyTypeName());
        }

        if (Objects.equals(SharedPreferenceManager.getString(this, SharedPreferenceManager.KEY_DUST_DATA), String.valueOf(true))) {
            text += String.format("미세먼지 상태는 %s 입니다 ", mainViewModel.dustData.getValue().dustGradeStr(mainViewModel.dustData.getValue().dustGrade));
        }

        if (Objects.equals(SharedPreferenceManager.getString(this, SharedPreferenceManager.KEY_MICRO_DUST_DATA), String.valueOf(true))) {
            text += String.format("초미세먼지 상태는 %s 입니다 ", mainViewModel.dustData.getValue().dustGradeStr(mainViewModel.dustData.getValue().microDustGrade));
        }

        if (Objects.equals(SharedPreferenceManager.getString(this, SharedPreferenceManager.KEY_UV_DATA), String.valueOf(true))) {
            text += String.format("자외선 지수는 %s 입니다 ", mainViewModel.uvData.getValue().getUVGrade(mainViewModel.uvData.getValue().today));
        }

        if (Objects.equals(SharedPreferenceManager.getString(this, SharedPreferenceManager.KEY_NEWS_DATA), String.valueOf(true))) {
            text += String.format("속보입니다 %s" , mainViewModel.brkNewsData.getValue().content);
        }

        if(text.isEmpty()) {
            text += "설정 화면에서 음성지원 옵션을 설정해주세요";
        } else {
            text = "오늘의 날씨입니다 " + text;
        }
        return text;
    }



    public void readExcel() {
        try {
            InputStream is = getBaseContext().getAssets().open("uv.tsv");

            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String line;
            ArrayList<LocationData> arr = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\t");
                LocationData temp = new LocationData();
                temp.code = words[0];
                temp.firstName = words[1];
                temp.secondName = words[2];
                temp.thirdName = words[3];
                temp.longitude = Double.parseDouble(words[4]);
                temp.latitude = Double.parseDouble(words[5]);

                arr.add(temp);
            }

            mainViewModel.locationDataArrayList = arr;

            Gson gson = new Gson();
            SharedPreferenceManager.setString(this, SharedPreferenceManager.KEY_LOCATION_DATA, gson.toJson(arr));

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        try {
            InputStream is = getBaseContext().getAssets().open("mid.tsv");

            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String line;
            ArrayList<MidLocationData> arr = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\t");
                MidLocationData temp = new MidLocationData();
                temp.city = words[0];
                temp.code = words[1];

                arr.add(temp);
            }

            mainViewModel.midLocationDataArrayList = arr;

            Gson gson = new Gson();
            SharedPreferenceManager.setString(this, SharedPreferenceManager.KEY_MID_LOCATION_DATA, gson.toJson(arr));

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void searchLocation() {
        if (!checkLocationServicesStatus()) {
            showDialogForLocationServiceSetting();
        } else {
            checkRunTimePermission();
        }
    }

    public void initViewModel() {
        mainViewModel = new ViewModelProvider(
                this,
                ViewModelProvider.Factory.from(MainViewModel.initializer)
        ).get(MainViewModel.class);
    }

    public void initObserver() {
        mainViewModel.failMsg.observe(this, s -> Log.e("TAG", s));
        mainViewModel.event.observe(this, s -> {

            if (Objects.equals(s, "setting")) {
                Intent intent = new Intent(this, SettingActivity.class);
                startActivityForResult(intent, 111);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_CODE && grantResults.length == REQUIRED_PERMISSIONS.length) {
            boolean check_result = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }
            if (!check_result) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {
                    Toast.makeText(MainActivity.this, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();
                }
            } else {
                gpsTracker = new GpsTracker(this);
                mainViewModel.curAddress = getCurrentAddress(gpsTracker.latitude, gpsTracker.longitude);
                mainViewModel.longitude = gpsTracker.longitude;
                mainViewModel.latitude = gpsTracker.latitude;

                mainViewModel.request();
            }
        }
    }

    void checkRunTimePermission() {
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION);

        if (hasFineLocationPermission != PackageManager.PERMISSION_GRANTED ||
                hasCoarseLocationPermission != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, REQUIRED_PERMISSIONS[0])) {
                Toast.makeText(MainActivity.this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(MainActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }
        } else {
            gpsTracker = new GpsTracker(this);
            mainViewModel.curAddress = getCurrentAddress(gpsTracker.latitude, gpsTracker.longitude);
            mainViewModel.longitude = gpsTracker.longitude;
            mainViewModel.latitude = gpsTracker.latitude;

            mainViewModel.request();
        }
    }

    public String getCurrentAddress(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;

        try {
            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    7);
        } catch (IOException ioException) {
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";
        }

        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";
        }

        Address address = addresses.get(0);
        return address.getAddressLine(0) + "\n";
    }

    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", (dialog, id) -> {
            Intent callGPSSettingIntent
                    = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
        });
        builder.setNegativeButton("취소", (dialog, id) -> dialog.cancel());
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GPS_ENABLE_REQUEST_CODE) {
            if (checkLocationServicesStatus()) {
                if (checkLocationServicesStatus()) {
                    Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
                    checkRunTimePermission();
                }
            }
        } else if(requestCode == 111) {
            initVisible();
            mainViewModel.updateData();
        }
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
}