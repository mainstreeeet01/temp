package com.example.mobileprogramming.network.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResponseBase2<T> {

    @SerializedName("response")
    @Expose
    private Response2<T> response;

    public Response2<T> getResponse() {
        return response;
    }

    public void setResponse(Response2<T> response) {
        this.response = response;
    }

}
