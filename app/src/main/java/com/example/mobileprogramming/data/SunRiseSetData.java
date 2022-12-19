package com.example.mobileprogramming.data;

public class SunRiseSetData {
    public String sunRise;
    public String sunSet;

    public String getSunRiseStr() {
        if (sunRise == null) return "";
        return sunRise.substring(0, 2) + ":" + sunRise.substring(2);
    }

    public String getSunSetStr() {
        if (sunSet == null) return "";
        return sunSet.substring(0, 2) + ":" + sunSet.substring(2);
    }
}
