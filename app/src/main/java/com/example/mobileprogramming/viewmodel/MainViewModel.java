package com.example.mobileprogramming.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import com.example.mobileprogramming.SharedPreferenceManager;
import com.example.mobileprogramming.data.BrkNewsData;
import com.example.mobileprogramming.data.DustData;
import com.example.mobileprogramming.data.LocationData;
import com.example.mobileprogramming.data.MidLandData;
import com.example.mobileprogramming.data.MidLocationData;
import com.example.mobileprogramming.data.MidTaViewData;
import com.example.mobileprogramming.data.RadarImgData;
import com.example.mobileprogramming.data.SunRiseSetData;
import com.example.mobileprogramming.data.UVViewData;
import com.example.mobileprogramming.data.UltraShortForecastData;
import com.example.mobileprogramming.data.WeatherWrnData;
import com.example.mobileprogramming.network.data.ApiResponseBase;
import com.example.mobileprogramming.network.data.ApiResponseBase2;
import com.example.mobileprogramming.network.data.Body;
import com.example.mobileprogramming.network.data.CtprvnRltmMesureDnstyData;
import com.example.mobileprogramming.network.data.Item;
import com.example.mobileprogramming.network.data.LCRiseSetInfo.LCRiseSetInfoResponse;
import com.example.mobileprogramming.network.data.MidLandFcstData;
import com.example.mobileprogramming.network.data.MidTaData;
import com.example.mobileprogramming.network.data.RadarImgResponse;
import com.example.mobileprogramming.network.data.UVData;
import com.example.mobileprogramming.network.data.WthrBrkNewsData;
import com.example.mobileprogramming.network.data.WthrPwnData;
import com.example.mobileprogramming.repo.MainRepo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {
    MainViewModel(MainRepo mainRepo) {
        this.mainRepo = mainRepo;
    }

    public MutableLiveData<String> failMsg = new MutableLiveData<>();
    public MutableLiveData<String> event = new MutableLiveData<>();

    public MutableLiveData<SunRiseSetData> sunRiseSetData = new MutableLiveData<>(new SunRiseSetData());
    public MutableLiveData<RadarImgData> radarImgData = new MutableLiveData<>(new RadarImgData());
    public MutableLiveData<DustData> dustData = new MutableLiveData<>(new DustData());
    public MutableLiveData<UVViewData> uvData = new MutableLiveData<>(new UVViewData());
    public MutableLiveData<BrkNewsData> brkNewsData = new MutableLiveData<>(new BrkNewsData());
    public MutableLiveData<ArrayList<WeatherWrnData>> weatherWrnData = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<MidTaViewData> midTaViewData = new MutableLiveData<>(new MidTaViewData());
    public MutableLiveData<MidLandData> midLandData = new MutableLiveData<>(new MidLandData());
    public MutableLiveData<UltraShortForecastData> ultraShortForecastData = new MutableLiveData<>(new UltraShortForecastData());

    public MutableLiveData<HashMap<String, Boolean>> visibleArr = new MutableLiveData<>(new HashMap<>());

    public SharedPreferenceManager sharedPreferenceManager;

    public ArrayList<LocationData> locationDataArrayList = new ArrayList<>();
    public ArrayList<MidLocationData> midLocationDataArrayList = new ArrayList<>();

    private final CompositeDisposable disposable = new CompositeDisposable();
    private final MainRepo mainRepo;
    public static final ViewModelInitializer<MainViewModel> initializer = new ViewModelInitializer<>(
            MainViewModel.class,
            creationExtras -> new MainViewModel(new MainRepo())
    );

    public String curAddress;
    public double longitude;
    public double latitude;
    public double threshold = 0.01;

    public void request() {
        getShortForecast();
        getMidLandFcst();
        getCmpImg();
        getMidTa();
        getCtprvnRltmMesureDnsty();
        getWthrPwn();
        getWthrBrkNews();
        getUVIdxV3();
        getLCRiseSetInfo();
    }

    public void updateData() {
        visibleArr.postValue(visibleArr.getValue());
    }

    public void getShortForecast() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
        Calendar cal = Calendar.getInstance();
        cal.setTime(Calendar.getInstance().getTime());
        cal.add(Calendar.HOUR, -1);
        String date = dateFormat.format(cal.getTime());
        String time = timeFormat.format(cal.getTime()) + "00";


        Log.d("TAG", "getShortForecast");
        disposable.add(mainRepo.getShortForecast(date, time, String.valueOf((int)longitude) ,String.valueOf((int)latitude))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ApiResponseBase<Item>>(){

                    @Override
                    public void onSuccess(ApiResponseBase<Item> value) {
                        if (value.getResponse().getBody() != null) {
                            List<Item> result = value.getResponse().getBody().getItems().getItem();
                            if (result.isEmpty()) return;

                            UltraShortForecastData temp = ultraShortForecastData.getValue();
                            ArrayList<Boolean> boArr = new ArrayList<>();
                            boArr.add(false);
                            boArr.add(false);
                            boArr.add(false);
                            boArr.add(false);
                            boArr.add(false);
                            boArr.add(false);

                            if (temp != null) {
                                for(Item item: result) {
                                    switch (item.getCategory()) {
                                        case "POP": // 강수확률 %
                                            if (!boArr.get(0)){
                                                temp.rainPer = item.getFcstValue();
                                                boArr.set(0, true);
                                            }
                                            break;
                                        case "PTY": // 강수형태 코드
                                            if (!boArr.get(1)) {
                                                temp.rainType = item.getFcstValue();
                                                boArr.set(1, true);
                                            }
                                            break;
                                        case "TMP": // 1시간 기온
                                            if (!boArr.get(2)) {
                                                temp.curTemp = item.getFcstValue();
                                                boArr.set(2, true);
                                            }
                                            break;
                                        case "TMN": // 최저기온
                                            if (!boArr.get(3)) {
                                                temp.minTemp = item.getFcstValue();
                                                boArr.set(3, true);
                                            }
                                            break;
                                        case "TMX": // 최고 기온
                                            if (!boArr.get(4)) {
                                                temp.maxTemp = item.getFcstValue();
                                                boArr.set(4, true);
                                            }
                                            break;
                                        case "SKY": // 하늘 상태
                                            if (!boArr.get(5)) {
                                                temp.skyType = item.getFcstValue();
                                                boArr.set(5, true);
                                            }
                                            break;

                                    }
                                }

                                ultraShortForecastData.postValue(temp);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        failMsg.postValue(e.getMessage());
                    }
                }));
    }

    public void getMidLandFcst() { // 일주일 날씨
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date today = Calendar.getInstance().getTime();
        String date = dateFormat.format(today);

        String regId = searchMyLocationCodeMid();

        disposable.add(mainRepo.getMidLandFcst(regId, date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ApiResponseBase<MidLandFcstData>>(){
                    @Override
                    public void onSuccess(ApiResponseBase<MidLandFcstData> radarImgResponseApiResponseBase) {

                        List<MidLandFcstData> result = radarImgResponseApiResponseBase.getResponse().getBody().getItems().getItem();
                        if (result.isEmpty()) return;

                        if (midLandData.getValue() != null) {
                            midLandData.postValue(new MidLandData(result.get(0)));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        failMsg.postValue(e.getMessage());
                    }
                }));
    }

    public void getCmpImg() { // 레이더
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date today = Calendar.getInstance().getTime();
        String date = dateFormat.format(today);


        disposable.add(mainRepo.getCmpImg(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ApiResponseBase<RadarImgResponse>>(){
                    @Override
                    public void onSuccess(ApiResponseBase<RadarImgResponse> radarImgResponseApiResponseBase) {
                        if (radarImgResponseApiResponseBase.getResponse().getBody() != null) {
                            List<RadarImgResponse> result = radarImgResponseApiResponseBase.getResponse().getBody().getItems().getItem();
                            if (result.isEmpty()) return;
                            String imgFile = result.get(0).getRarImg().substring(1);
                            imgFile = imgFile.substring(0, imgFile.indexOf(','));

                            if (radarImgData.getValue() != null) {
                                RadarImgData temp = radarImgData.getValue();
                                temp.imgUrl = imgFile;
                                radarImgData.postValue(temp);
                            }
                        }

                        Log.d("TAG", radarImgResponseApiResponseBase.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        failMsg.postValue(e.getMessage());
                    }
                }));
    }

    public void getMidTa() { // 일주일 온도
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date today = Calendar.getInstance().getTime();
        String date = dateFormat.format(today);
        String regId = searchMyLocationCodeMid();

        disposable.add(mainRepo.getMidTa(regId, date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ApiResponseBase<MidTaData>>(){
                    @Override
                    public void onSuccess(ApiResponseBase<MidTaData> radarImgResponseApiResponseBase) {
                        List<MidTaData> result = radarImgResponseApiResponseBase.getResponse().getBody().getItems().getItem();
                        if (result.isEmpty()) return;

                        if (midTaViewData.getValue() != null) {
                            MidTaViewData temp = midTaViewData.getValue();
                            MidTaData data = result.get(0);
                            temp.taMax3 = data.getTaMax3();
                            temp.taMax4 = data.getTaMax4();
                            temp.taMax5 = data.getTaMax5();
                            temp.taMax6 = data.getTaMax6();
                            temp.taMax7 = data.getTaMax7();
                            temp.taMax8 = data.getTaMax8();
                            temp.taMax9 = data.getTaMax9();
                            temp.taMax10 = data.getTaMax10();

                            temp.taMin3 = data.getTaMin3();
                            temp.taMin4 = data.getTaMin4();
                            temp.taMin5 = data.getTaMin5();
                            temp.taMin6 = data.getTaMin6();
                            temp.taMin7 = data.getTaMin7();
                            temp.taMin8 = data.getTaMin8();
                            temp.taMin9 = data.getTaMin9();
                            temp.taMin10 = data.getTaMin10();

                            midTaViewData.postValue(temp);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        failMsg.postValue(e.getMessage());
                    }
                }));
    }

    public void getCtprvnRltmMesureDnsty() { // 미세먼지
        String cityName = "서울";

        disposable.add(mainRepo.getCtprvnRltmMesureDnsty(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ApiResponseBase2<CtprvnRltmMesureDnstyData>>(){
                    @Override
                    public void onSuccess(ApiResponseBase2<CtprvnRltmMesureDnstyData> listApiResponseBase) {
                        Log.d("TAG", listApiResponseBase.toString());
                        List<CtprvnRltmMesureDnstyData> result = listApiResponseBase.getResponse().getBody().getItems();
                        if (result.isEmpty()) return;

                        if (dustData.getValue() != null) {
                            DustData temp = dustData.getValue();
                            CtprvnRltmMesureDnstyData dust = result.get(0);
                            temp.dustValue = dust.getPm10Value();
                            temp.dustGrade = dust.getPm10Grade();
                            temp.microDustValue = dust.getPm25Value();
                            temp.microDustGrade = dust.getPm25Grade();

                            dustData.postValue(temp);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        failMsg.postValue(e.getMessage());
                    }
                }));
    }

    public void getWthrPwn() { // 특보
        String stdId = "108";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date today = Calendar.getInstance().getTime();
        String fromTmFc = dateFormat.format(today);
        String toTmFc = dateFormat.format(today);

        disposable.add(mainRepo.getWthrPwn(stdId, fromTmFc, toTmFc)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ApiResponseBase<WthrPwnData>>(){
                    @Override
                    public void onSuccess(ApiResponseBase<WthrPwnData> wthrPwnDataApiResponseBase) {
                        Body<WthrPwnData> body = wthrPwnDataApiResponseBase.getResponse().getBody();
                        if (body != null) {
                            List<WthrPwnData> arr = body.getItems().getItem();
                            if (arr.isEmpty()) return;

                            ArrayList<WeatherWrnData> arrList = new ArrayList<>();

                            for (WthrPwnData data : arr) {
                                WeatherWrnData weatherWrnData = new WeatherWrnData();
                                weatherWrnData.t1 = data.getT1();
                                weatherWrnData.t2 = data.getT2();
                                weatherWrnData.t3 = data.getT3();
                                weatherWrnData.t4 = data.getT4();
                                weatherWrnData.t5 = data.getT5();
                                arrList.add(weatherWrnData);
                            }

                            weatherWrnData.postValue(arrList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        failMsg.postValue(e.getMessage());
                    }
                }));
    }

    public void getWthrBrkNews() { // 속보
        String stdId = "108";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date today = Calendar.getInstance().getTime();
        String fromTmFc = dateFormat.format(today);
        String toTmFc = dateFormat.format(today);

        disposable.add(mainRepo.getWthrBrkNews(stdId, fromTmFc, toTmFc)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ApiResponseBase<WthrBrkNewsData>>(){
                    @Override
                    public void onSuccess(ApiResponseBase<WthrBrkNewsData> wthrBrkNewsDataApiResponseBase) {
                        Body<WthrBrkNewsData> body = wthrBrkNewsDataApiResponseBase.getResponse().getBody();
                        if (body != null) {
                            List<WthrBrkNewsData> arr = body.getItems().getItem();
                            if (arr.isEmpty()) return;

                            BrkNewsData temp = brkNewsData.getValue();
                            if (temp != null) {
                                String [] str = arr.get(0).getAnn().split("○");
                                StringBuilder saveStr = new StringBuilder();
                                for(String tempStr: str) {
                                    saveStr.append(tempStr).append('\n');
                                }
                                temp.content = saveStr.toString();
                                brkNewsData.postValue(temp);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        failMsg.postValue(e.getMessage());
                    }
                }));
    }

    public void getUVIdxV3() { // 자외선
        String areaNo = searchMyLocationCodeUV();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhh");
        Date today = Calendar.getInstance().getTime();
        String date = dateFormat.format(today);

        disposable.add(mainRepo.getUVIdxV3(areaNo, date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ApiResponseBase<UVData>>(){
                    @Override
                    public void onSuccess(ApiResponseBase<UVData> uvDataApiResponseBase) {
                        Body<UVData> body = uvDataApiResponseBase.getResponse().getBody();
                        if (body != null) {
                            List<UVData> arr = body.getItems().getItem();
                            if (arr.isEmpty()) return;

                            LocationData locationData = locationDataArrayList.get(0);
                            for(LocationData location: locationDataArrayList) {
                                if (Objects.equals(location.code, areaNo)) {
                                    locationData = location;
                                    break;
                                }
                            }

                            UVViewData temp = uvData.getValue();
                            if (temp != null) {
                                temp.today = temp.getUVGrade(arr.get(0).getToday());
                                temp.tomorrow = temp.getUVGrade(arr.get(0).getTomorrow());
                                temp.firstName = locationData.firstName;
                                temp.secondName = locationData.secondName;
                                temp.thirdName = locationData.thirdName;

                                uvData.postValue(temp);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        failMsg.postValue(e.getMessage());
                    }
                }));

    }

    public void getLCRiseSetInfo() { // 일몰 일출
        disposable.add(mainRepo.getLCRiseSetInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<LCRiseSetInfoResponse>() {

                    @Override
                    public void onSuccess(LCRiseSetInfoResponse lcRiseSetInfoResponse) {
                        if (sunRiseSetData.getValue() != null && lcRiseSetInfoResponse.getBody() != null) {
                            SunRiseSetData temp = sunRiseSetData.getValue();
                            temp.sunRise = lcRiseSetInfoResponse.getBody().getItems().get(0).getSunrise();
                            temp.sunSet = lcRiseSetInfoResponse.getBody().getItems().get(0).getSunset();

                            Log.d("TAG", temp.toString());
                            sunRiseSetData.postValue(temp);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        failMsg.postValue(e.toString());
                    }
                }));
    }

    public String searchMyLocationCodeUV() {
        if (locationDataArrayList.isEmpty()) return "";

        for (int i = 0 ; i < locationDataArrayList.size(); i++) {
            LocationData location = locationDataArrayList.get(i);
            if (location != null && isRange(location.latitude, latitude) && isRange(location.longitude, longitude)) {
                return location.code;
            }
        }
        return locationDataArrayList.get(0).code;
    }

    public String searchMyLocationCodeMid() {
        if (midLocationDataArrayList.isEmpty()) return "";

        for (MidLocationData midLocationData : midLocationDataArrayList) {
            if (curAddress.contains(midLocationData.city)) {
                return midLocationData.code;
            }
        }

        return midLocationDataArrayList.get(1).code;
    }

    public boolean isRange(double value, double standard) {
        return value >= standard - threshold && value <= standard + threshold;
    }

    public void onClickSetting() {
        event.postValue("setting");
    }
}
