package com.code.youyu.api;


import com.code.bean.ModelBean;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface HttpApi {
    String BASE_URL = "https://api.hitradegate.com/v1/";

    String GET_CHANNEL_TOKEN = "channel/{channelId}/token";
    String CREATE_CHANNEL = "channel/channel";
    String JOIN_CHANNEL = "channel/{channelId}/users";
    String GET_MODEL_LIST = "";

    @GET(GET_CHANNEL_TOKEN)
    Observable<String> getChannelToken(@Path("channelId") String channelId);

    @POST(CREATE_CHANNEL)
    Observable<String> createChannel();

    @PUT(JOIN_CHANNEL)
    Observable<String> joinChannel(@Path("channelId") String channelId);

    @GET(GET_MODEL_LIST)
    Observable<ArrayList<ModelBean>> getModelList();
}
