package com.example.mobileprogramming.network.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WthrPwnData { // 특보

    @SerializedName("t1")
    @Expose
    String t1;

    @SerializedName("t2")
    @Expose
    String t2;

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public String getT3() {
        return t3;
    }

    public void setT3(String t3) {
        this.t3 = t3;
    }

    public String getT4() {
        return t4;
    }

    public void setT4(String t4) {
        this.t4 = t4;
    }

    public String getT5() {
        return t5;
    }

    public void setT5(String t5) {
        this.t5 = t5;
    }

    @SerializedName("t3")
    @Expose
    String t3;

    @SerializedName("t4")
    @Expose
    String t4;

    @SerializedName("t5")
    @Expose
    String t5;
}
