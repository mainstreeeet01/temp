package com.example.mobileprogramming.network.data;

import com.google.gson.annotations.SerializedName;

public class RadarImgResponse {

    @SerializedName("rdr-img-file")
    String rarImg;

    public String getRarImg() {
        return rarImg;
    }

    public void setRarImg(String rarImg) {
        this.rarImg = rarImg;
    }
}
