package com.example.mobileprogramming.network.data;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*
* 위험	11 이상
매우높음	8~10
높음	6~7
보통	3~5
낮음	0~2
*
* */
public class UVData {
    @SerializedName("h0")
    @Expose
    String today;

    @SerializedName("h24")
    @Expose
    String tomorrow;

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getTomorrow() {
        return tomorrow;
    }

    public void setTomorrow(String tomorrow) {
        this.tomorrow = tomorrow;
    }


}
