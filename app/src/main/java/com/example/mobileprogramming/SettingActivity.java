package com.example.mobileprogramming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.CompoundButton;

import com.example.mobileprogramming.databinding.ActivitySettingBinding;

import java.util.Locale;

public class SettingActivity extends AppCompatActivity {

    ActivitySettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);

        binding.checkCurTemp.setChecked(Boolean.parseBoolean(SharedPreferenceManager.getString(this, SharedPreferenceManager.KEY_TODAY_DATA)));
        binding.checkDust.setChecked(Boolean.parseBoolean(SharedPreferenceManager.getString(this, SharedPreferenceManager.KEY_DUST_DATA)));
        binding.checkMicroDust.setChecked(Boolean.parseBoolean(SharedPreferenceManager.getString(this, SharedPreferenceManager.KEY_MICRO_DUST_DATA)));
        binding.checkNews.setChecked(Boolean.parseBoolean(SharedPreferenceManager.getString(this, SharedPreferenceManager.KEY_NEWS_DATA)));
        binding.checkUv.setChecked(Boolean.parseBoolean(SharedPreferenceManager.getString(this, SharedPreferenceManager.KEY_UV_DATA)));

        binding.checkWeek.setChecked(SharedPreferenceManager.getBoolean(this, SharedPreferenceManager.KEY_VISIBLE_WEEK));
        binding.checkTotalDust.setChecked(SharedPreferenceManager.getBoolean(this, SharedPreferenceManager.KEY_VISIBLE_DUST));
        binding.checkSun.setChecked(SharedPreferenceManager.getBoolean(this, SharedPreferenceManager.KEY_VISIBLE_SUN));
        binding.checkUV.setChecked(SharedPreferenceManager.getBoolean(this, SharedPreferenceManager.KEY_VISIBLE_UV));
        binding.checkTotalNews.setChecked(SharedPreferenceManager.getBoolean(this, SharedPreferenceManager.KEY_VISIBLE_NEWS));
        binding.checkRadar.setChecked(SharedPreferenceManager.getBoolean(this, SharedPreferenceManager.KEY_VISIBLE_RADAR));

        binding.checkCurTemp.setOnCheckedChangeListener((compoundButton, b) -> {
            SharedPreferenceManager.setString(this, SharedPreferenceManager.KEY_TODAY_DATA, String.valueOf(b));
        });

        binding.checkDust.setOnCheckedChangeListener((compoundButton, b) -> {
            SharedPreferenceManager.setString(this, SharedPreferenceManager.KEY_DUST_DATA, String.valueOf(b));
        });

        binding.checkMicroDust.setOnCheckedChangeListener((compoundButton, b) -> {
            SharedPreferenceManager.setString(this, SharedPreferenceManager.KEY_MICRO_DUST_DATA, String.valueOf(b));
        });

        binding.checkNews.setOnCheckedChangeListener((compoundButton, b) -> {
            SharedPreferenceManager.setString(this, SharedPreferenceManager.KEY_NEWS_DATA, String.valueOf(b));
        });

        binding.checkUv.setOnCheckedChangeListener((compoundButton, b) -> {
            SharedPreferenceManager.setString(this, SharedPreferenceManager.KEY_UV_DATA, String.valueOf(b));
        });


        binding.checkWeek.setOnCheckedChangeListener((compoundButton, b) -> {
            SharedPreferenceManager.setBoolean(this, SharedPreferenceManager.KEY_VISIBLE_WEEK, b);
        });

        binding.checkTotalDust.setOnCheckedChangeListener((compoundButton, b) -> {
            SharedPreferenceManager.setBoolean(this, SharedPreferenceManager.KEY_VISIBLE_DUST, b);
        });

        binding.checkSun.setOnCheckedChangeListener((compoundButton, b) -> {
            SharedPreferenceManager.setBoolean(this, SharedPreferenceManager.KEY_VISIBLE_SUN, b);
        });

        binding.checkUV.setOnCheckedChangeListener((compoundButton, b) -> {
            SharedPreferenceManager.setBoolean(this, SharedPreferenceManager.KEY_VISIBLE_UV, b);
        });

        binding.checkTotalNews.setOnCheckedChangeListener((compoundButton, b) -> {
            SharedPreferenceManager.setBoolean(this, SharedPreferenceManager.KEY_VISIBLE_NEWS, b);
        });

        binding.checkRadar.setOnCheckedChangeListener((compoundButton, b) -> {
            SharedPreferenceManager.setBoolean(this, SharedPreferenceManager.KEY_VISIBLE_RADAR, b);
        });
    }

}