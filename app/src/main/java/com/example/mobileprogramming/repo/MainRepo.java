package com.example.mobileprogramming.repo;

import static com.example.mobileprogramming.network.RetrofitConfig.BASE_URL;
import static com.example.mobileprogramming.network.RetrofitConfig.BASE_URL2;
import static com.example.mobileprogramming.network.RetrofitConfig.BASE_URL3;
import static com.example.mobileprogramming.network.RetrofitConfig.BASE_URL4;
import static com.example.mobileprogramming.network.RetrofitConfig.BASE_URL5;
import static com.example.mobileprogramming.network.RetrofitConfig.BASE_URL6;
import static com.example.mobileprogramming.network.RetrofitConfig.BASE_URL7;

import com.example.mobileprogramming.network.RetrofitConfig;
import com.example.mobileprogramming.network.RetrofitService;
import com.example.mobileprogramming.network.data.ApiResponseBase2;
import com.example.mobileprogramming.network.data.CtprvnRltmMesureDnstyData;
import com.example.mobileprogramming.network.data.Item;
import com.example.mobileprogramming.network.data.LCRiseSetInfo.LCRiseSetInfoResponse;
import com.example.mobileprogramming.network.data.ApiResponseBase;
import com.example.mobileprogramming.network.data.MidLandFcstData;
import com.example.mobileprogramming.network.data.MidTaData;
import com.example.mobileprogramming.network.data.RadarImgResponse;
import com.example.mobileprogramming.network.data.UVData;
import com.example.mobileprogramming.network.data.WthrBrkNewsData;
import com.example.mobileprogramming.network.data.WthrPwnData;

import io.reactivex.Single;

public class MainRepo {
    private static final String API_KEY = "aIvK6lZhfq6KT+/IADB7qJiQ8R5T4gizXTRen0AQiJ7PrbeCmmigno7Iai8MoNWCxnDzQqlWAXeblZB5AzYAnA==";
    private final RetrofitService service1 = RetrofitConfig.getApiService(BASE_URL);
    private final RetrofitService service2 = RetrofitConfig.getApiService(BASE_URL2);
    private final RetrofitService service3 = RetrofitConfig.getApiService(BASE_URL3);
    private final RetrofitService service4 = RetrofitConfig.getApiService(BASE_URL4);
    private final RetrofitService service5 = RetrofitConfig.getApiService(BASE_URL5);
    private final RetrofitService service6 = RetrofitConfig.getApiService(BASE_URL6);
    private final RetrofitService service7 = RetrofitConfig.getApiService(BASE_URL7);

    public Single<ApiResponseBase<Item>> getShortForecast(String date, String time, String longitude, String latitude) {
        return service1.getVilageFcst(
                 API_KEY,"1","900","JSON", date, time, longitude, latitude
        );
    }

    public Single<LCRiseSetInfoResponse> getLCRiseSetInfo() {
        return service2.getLCRiseSetInfo(
                API_KEY, "20221217", "12800", "3613", "N"
        );
    }

    public Single<ApiResponseBase<RadarImgResponse>> getCmpImg(String date) {
        return service3.getCmpImg(
                API_KEY, "1", "10", "JSON", "CMP_WRC", date
        );
    }

    public Single<ApiResponseBase<MidLandFcstData>> getMidLandFcst(String regId, String date) {
        return service7.getMidLandFcst(
                API_KEY, "1", "10", "JSON", "11B00000", date + "0600"
        );
    }

    public Single<ApiResponseBase<MidTaData>> getMidTa(String regId, String date) {
        return service7.getMidTa(
                API_KEY, "1", "10", "JSON", "11B10101", date + "0600"
        );
    }

    public Single<ApiResponseBase2<CtprvnRltmMesureDnstyData>> getCtprvnRltmMesureDnsty(String cityName) {
        return service4.getCtprvnRltmMesureDnsty(
                API_KEY, "1", "100", "json", cityName, "1.3"
        );
    }

    public Single<ApiResponseBase<WthrPwnData>> getWthrPwn(String stdId, String fromTmFc, String toTmFc) {
        return service5.getWthrPwn(
                API_KEY, "1", "10", "JSON", stdId, fromTmFc, toTmFc
        );
    }

    public Single<ApiResponseBase<WthrBrkNewsData>> getWthrBrkNews(String stdId, String fromTmFc, String toTmFc) {
        return service5.getWthrBrkNews(
                API_KEY, "1", "1", "JSON", stdId, fromTmFc, toTmFc
        );
    }

    /// date 2022121705
    public Single<ApiResponseBase<UVData>> getUVIdxV3(String areaNo, String date) {
        return service6.getUVIdxV3(
                API_KEY, "1", "10", "JSON", areaNo, date
        );
    }

}
