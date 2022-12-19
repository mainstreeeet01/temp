package com.example.mobileprogramming.network.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WthrBrkNewsData { // 속보
    @SerializedName("ann")
    @Expose
    String ann;

    public String getAnn() {
        return ann;
    }

    public void setAnn(String ann) {
        this.ann = ann;
    }
}
