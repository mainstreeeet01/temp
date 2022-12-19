package com.example.mobileprogramming.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RetrofitConfig {
    public static final String BASE_URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/"; // 단기
    public static final String BASE_URL2 = "http://apis.data.go.kr/B090041/openapi/service/RiseSetInfoService/"; // 일몰일출
    public static final String BASE_URL3 = "http://apis.data.go.kr/1360000/RadarImgInfoService/"; // 레이더
    public static final String BASE_URL4 = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/"; // 미세먼지
    public static final String BASE_URL5 = "http://apis.data.go.kr/1360000/WthrWrnInfoService/"; // 특보 속보
    public static final String BASE_URL6 = "http://apis.data.go.kr/1360000/LivingWthrIdxServiceV3/"; // 미세 먼지
    public static final String BASE_URL7 = "http://apis.data.go.kr/1360000/MidFcstInfoService/"; // 일주일


    public static RetrofitService getApiService(String url) {
        return getInstance(url).create(RetrofitService.class);
    }

    private static Retrofit getInstance(String url) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(httpLoggingInterceptor);

        Gson gson = new GsonBuilder().setLenient().create();

        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build();
    }

}
