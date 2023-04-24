package com.code.youyu.api;


import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface HttpApi {
    String BASE_URL = "https://api.hitradegate.com/v1/iam/";
    String SUMMARY = "token/";

    @GET(SUMMARY)
    Observable<String> getToken(@Header("accessKeyId") String sign);
}
