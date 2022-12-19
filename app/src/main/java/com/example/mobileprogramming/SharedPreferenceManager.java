package com.example.mobileprogramming;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {

    private static final String PREFERENCES_NAME = "preference";
    public static final String KEY_LOCATION_DATA = "key_location_data";
    public static final String KEY_MID_LOCATION_DATA = "key_mid_location_data";

    public static final String KEY_TODAY_DATA = "key_today_data";
    public static final String KEY_DUST_DATA = "key_dust_data";
    public static final String KEY_MICRO_DUST_DATA = "key_micro_dust_data";
    public static final String KEY_UV_DATA = "key_uv_data";
    public static final String KEY_NEWS_DATA = "key_news_data";

    public static final String KEY_VISIBLE_WEEK = "KEY_VISIBLE_WEEK";
    public static final String KEY_VISIBLE_DUST = "KEY_VISIBLE_DUST";
    public static final String KEY_VISIBLE_SUN = "KEY_VISIBLE_SUN";
    public static final String KEY_VISIBLE_UV = "KEY_VISIBLE_UV";
    public static final String KEY_VISIBLE_NEWS = "KEY_VISIBLE_NEWS";
    public static final String KEY_VISIBLE_RADAR = "KEY_VISIBLE_RADAR";



    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static void setString(Context context, String key, String value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getString(key, "false");
    }

    public static void setBoolean(Context context, String key, Boolean value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static Boolean getBoolean(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        return prefs.getBoolean(key, true);
    }
}
