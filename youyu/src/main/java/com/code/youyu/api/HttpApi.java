package com.code.youyu.api;


import com.code.bean.ChannelTokenBean;
import com.code.bean.CreateChannelBean;
import com.code.bean.ChannelResultBean;
import com.code.bean.JoinChannelBean;
import com.code.bean.ModelCoverListBean;
import com.code.bean.ModelListBean;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HttpApi {
    String BASE_URL = "https://api.hitradegate.com/v1/";
    String GET_CHANNEL_TOKEN = "channel/channel/{channelId}/token";
    String CREATE_CHANNEL = "channel/channel";
    String JOIN_CHANNEL = "channel/channel/{channelId}/users";
    String GET_MODEL_LIST = "broadcaster/list";
    String GET_MODEL_COVER = "avatar/list";

    @GET(GET_CHANNEL_TOKEN)
    Observable<ChannelTokenBean> getChannelToken(@Header("Authorization") String authorization, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Header("Content-Type") String Content_Type, @Header("Session-Token") String Session_Token, @Path("channelId") String channelId);

    // @Header("Session-Token") String sessionToken,
    @POST(CREATE_CHANNEL)
    Observable<ChannelResultBean> createChannel(@Header("Authorization") String authorization, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Header("Content-Type") String Content_Type, @Header("Session-Token") String Session_Token, @Body CreateChannelBean createInfo);

    @PUT(JOIN_CHANNEL)
    Observable<ChannelResultBean> joinChannel(@Header("Authorization") String authorization, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Header("Content-Type") String Content_Type, @Header("Session-Token") String Session_Token, @Path("channelId") String channelId, @Body JoinChannelBean joinChannelInfo);

    @GET(GET_MODEL_LIST)
    Observable<ModelListBean> getModelList(@Header("Authorization") String authorization, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Header("Content-Type") String Content_Type, @Header("Session-Token") String Session_Token, @Query("sort") int sort, @Query("page") int page, @Query("limit") int limit);

    @GET(GET_MODEL_COVER)
    Observable<ModelCoverListBean> getModelCoverList(@Header("Authorization") String authorization, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Header("Content-Type") String Content_Type, @Header("Session-Token") String Session_Token, @Query("id") int modelId);

}
