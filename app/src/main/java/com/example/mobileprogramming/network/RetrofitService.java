package com.example.mobileprogramming.network;

import com.example.mobileprogramming.network.data.ApiResponseBase2;
import com.example.mobileprogramming.network.data.CtprvnRltmMesureDnstyData;
import com.example.mobileprogramming.network.data.Item;
import com.example.mobileprogramming.network.data.LCRiseSetInfo.LCRiseSetInfoResponse;
import com.example.mobileprogramming.network.data.MidLandFcstData;
import com.example.mobileprogramming.network.data.MidTaData;
import com.example.mobileprogramming.network.data.RadarImgResponse;
import com.example.mobileprogramming.network.data.ApiResponseBase;
import com.example.mobileprogramming.network.data.UVData;
import com.example.mobileprogramming.network.data.WthrBrkNewsData;
import com.example.mobileprogramming.network.data.WthrPwnData;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("getVilageFcst") //  오늘 날씨 등등
    Single<ApiResponseBase<Item>> getVilageFcst(
            @Query("serviceKey") String serviceKey,
            @Query("pageNo") String pageNo,
            @Query("numOfRows") String numOfRows,
            @Query("dataType") String dataType,
            @Query("base_date") String base_date,
            @Query("base_time") String base_time,
            @Query("ny") String ny,
            @Query("nx") String nx
    );

    @GET("getLCRiseSetInfo") // 일몰 출몰 o
    Single<LCRiseSetInfoResponse> getLCRiseSetInfo(
            @Query("serviceKey") String serviceKey,
            @Query("locdate") String locdate, // 날짜(연월일)
            @Query("longitude") String longitude, // 경도(도, 분형태)
            @Query("latitude") String latitude, // 위도(도, 분형태)
            @Query("dnYn") String dnYn // 실수형태(129.xxx)일경우 Y, 도와 분(128도 00분)형태의 경우 N
    );

    @GET("getCmpImg") // 레이더 o
    Single<ApiResponseBase<RadarImgResponse>> getCmpImg(
            @Query("serviceKey") String serviceKey,
            @Query("pageNo") String pageNo,
            @Query("numOfRows") String numOfRows,
            @Query("dataType") String dataType,
            @Query("data") String data, // 영상구분(CMP_WRC 고정)
            @Query("time") String time //년월일(YYYYMMDD)
    );

    @GET("getMidLandFcst") // 일주일 날씨
    Single<ApiResponseBase<MidLandFcstData>> getMidLandFcst(
            @Query("serviceKey") String serviceKey,
            @Query("pageNo") String pageNo, // 1
            @Query("numOfRows") String numOfRows, // 10
            @Query("dataType") String dataType, // json
            @Query("regId") String regId, // 위치
            @Query("tmFc") String tmFc // 날짜 + 0600
    );

    @GET("getMidTa") // 일주일 온도
    Single<ApiResponseBase<MidTaData>> getMidTa(
            @Query("serviceKey") String serviceKey,
            @Query("pageNo") String pageNo, // 1
            @Query("numOfRows") String numOfRows, // 10
            @Query("dataType") String dataType, // json
            @Query("regId") String regId, // 위치
            @Query("tmFc") String tmFc // 날짜 + 0600
    );

    @GET("getCtprvnRltmMesureDnsty") // 미세먼지 o
    Single<ApiResponseBase2<CtprvnRltmMesureDnstyData>> getCtprvnRltmMesureDnsty(
            @Query("serviceKey") String serviceKey,
            @Query("pageNo") String pageNo, // 1
            @Query("numOfRows") String numOfRows, // 10
            @Query("returnType") String returnType, // json
            @Query("sidoName") String sidoName, // 서울 경기 ..
            @Query("ver") String ver // 1.3
    );

    @GET("getWthrWrnMsg") // 특보
    Single<ApiResponseBase<WthrPwnData>> getWthrPwn(
            @Query("serviceKey") String serviceKey,
            @Query("pageNo") String pageNo, // 1
            @Query("numOfRows") String numOfRows, // 10
            @Query("dataType") String dataType, // json
            @Query("stnId") String stnId, // 지점 코드
            @Query("fromTmFc") String fromTmFc, // 발표시각
            @Query("toTmFc") String toTmFc // 발표시각 to
    );

    @GET("getWthrBrkNews") // 속보
    Single<ApiResponseBase<WthrBrkNewsData>> getWthrBrkNews( // 첫번째만 사용하자
            @Query("serviceKey") String serviceKey,
            @Query("pageNo") String pageNo, // 1
            @Query("numOfRows") String numOfRows, // 10
            @Query("dataType") String dataType, // json
            @Query("stnId") String stnId, // 지점 코드
            @Query("fromTmFc") String fromTmFc, // 발표시각
            @Query("toTmFc") String toTmFc // 발표시각 to
    );

    @GET("getUVIdxV3") // 자외선
    Single<ApiResponseBase<UVData>> getUVIdxV3(
            @Query("serviceKey") String serviceKey,
            @Query("pageNo") String pageNo, // 1
            @Query("numOfRows") String numOfRows, // 10
            @Query("dataType") String dataType, // json
            @Query("areaNo") String areaNo, // 지역
            @Query("time") String time // 시간
    );

}
