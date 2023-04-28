package com.code.youyu.api;


import com.code.bean.BaseBean;
import com.code.bean.ModelBean;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HttpApi {
    String BASE_URL = "https://api.hitradegate.com/v1/";
    String GET_CHANNEL_TOKEN = "channel/{channelId}/token";
    String CREATE_CHANNEL = "channel/channel";
    String JOIN_CHANNEL = "channel/{channelId}/users";
    String GET_MODEL_LIST = "";

    @GET(GET_CHANNEL_TOKEN)
    Observable<String> getChannelToken(@Header("Authorization") String authorization, @Header("Session-Token") String sessionToken, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Path("channelId") String channelId);

    @POST(CREATE_CHANNEL)
    Observable<BaseBean<String>> createChannel(@Header("Authorization") String authorization, @Header("Session-Token") String sessionToken, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Query("uid") String uid);

    @PUT(JOIN_CHANNEL)
    Observable<String> joinChannel(@Header("Authorization") String authorization, @Header("Session-Token") String sessionToken, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Path("channelId") String channelId);

    @GET(GET_MODEL_LIST)
    Observable<ArrayList<ModelBean>> getModelList(@Header("Authorization") String authorization, @Header("Session-Token") String sessionToken, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp);
}
