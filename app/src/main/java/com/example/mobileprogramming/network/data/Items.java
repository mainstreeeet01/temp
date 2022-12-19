package com.example.mobileprogramming.network.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Items<T> {

    @SerializedName("item")
    @Expose
    private List<T> item = null;

    public List<T> getItem() {
        return item;
    }

    public void setItem(List<T> item) {
        this.item = item;
    }

}
