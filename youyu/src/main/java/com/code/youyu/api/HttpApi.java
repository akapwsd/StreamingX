package com.code.youyu.api;


import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface HttpApi {
    String BASE_URL = "https://api.hitradegate.com/v1/ws/";
    String SUMMARY = "relationship/summary";

    @GET(SUMMARY)
    Observable<String> getToken(@Header("sign") String sign
            , @Header("account_token") String token
            , @Header("Authorization") String authorization
            , @Header("Session-Token") String session_token
            , @Header("X-Uyj-Timestamp") String x_uyj_timestamp
            , @Header("Content-Type") String content_type);
}
