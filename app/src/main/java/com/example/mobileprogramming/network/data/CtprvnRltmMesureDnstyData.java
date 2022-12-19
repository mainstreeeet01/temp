package com.example.mobileprogramming.network.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/*
* 좋음	보통	나쁨	매우나쁨
1	2	3	4
*
* */
public class CtprvnRltmMesureDnstyData { // 미세먼지

    @Expose
    @SerializedName("pm10Value")
    String pm10Value;

    @Expose
    @SerializedName("pm10Grade")
    String pm10Grade;

    public String getPm10Value() {
        return pm10Value;
    }

    public void setPm10Value(String pm10Value) {
        this.pm10Value = pm10Value;
    }

    public String getPm10Grade() {
        return pm10Grade;
    }

    public void setPm10Grade(String pm10Grade) {
        this.pm10Grade = pm10Grade;
    }

    @Expose
    @SerializedName("pm25Value")
    String pm25Value;

    @Expose
    @SerializedName("pm25Grade")
    String pm25Grade;

    public String getPm25Value() {
        return pm25Value;
    }

    public void setPm25Value(String pm25Value) {
        this.pm25Value = pm25Value;
    }

    public String getPm25Grade() {
        return pm25Grade;
    }

    public void setPm25Grade(String pm25Grade) {
        this.pm25Grade = pm25Grade;
    }
}
