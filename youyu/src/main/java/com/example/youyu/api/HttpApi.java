package com.example.youyu.api;


import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface HttpApi {
    String SUMMARY = "relationship/summary";
    @GET(SUMMARY)
    Observable<Object> getSummary(@Header("Authorization") String token, @Header("uid") int uid);
}
