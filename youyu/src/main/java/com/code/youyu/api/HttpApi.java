package com.code.youyu.api;

import com.code.bean.AccountBean;
import com.code.bean.AccountStateBean;
import com.code.bean.ChannelTokenBean;
import com.code.bean.ChannelResultBean;
import com.code.bean.JoinChannelBean;
import com.code.bean.LanguageBean;
import com.code.bean.MatchBean;
import com.code.bean.ModelCoverListBean;
import com.code.bean.ModelListBean;
import com.code.bean.SkipBean;

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
    String JOIN_CHANNEL = "channel/channel/{channelId}/users";
    String GET_MODEL_LIST = "broadcaster/broadcaster";
    String GET_MODEL_COVER = "broadcaster/broadcaster/{uid}/avatar";
    String GET_ACCOUNT_INFO = "broadcaster/broadcaster/{uid}/uid";
    String GET_MODEL_STATE = "broadcaster/broadcaster/{uid}/state";
    String START_MATCH = "channel/channel/match";
    String START_SKIP = "channel/channel/match/skip";
    String GET_LANGUAGE_LIST = "broadcaster/language";

    @GET(GET_CHANNEL_TOKEN)
    Observable<ChannelTokenBean> getChannelToken(@Header("Authorization") String authorization, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Header("Content-Type") String Content_Type, @Header("Session-Token") String Session_Token, @Path("channelId") String channelId);

    // @Header("Session-Token") String sessionToken,
    @PUT(JOIN_CHANNEL)
    Observable<ChannelResultBean> joinChannel(@Header("Authorization") String authorization, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Header("Content-Type") String Content_Type, @Header("Session-Token") String Session_Token, @Path("channelId") String channelId, @Body JoinChannelBean joinChannelInfo);

    @GET(GET_MODEL_LIST)
    Observable<ModelListBean> getModelList(@Header("Authorization") String authorization, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Header("Content-Type") String Content_Type, @Header("Session-Token") String Session_Token, @Query("sort") int sort, @Query("page") int page, @Query("limit") int limit, @Query("languageIso") String languageIso);

    @GET(GET_MODEL_COVER)
    Observable<ModelCoverListBean> getModelAvatar(@Header("Authorization") String authorization, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Header("Content-Type") String Content_Type, @Header("Session-Token") String Session_Token, @Path("uid") int modelId);

    @GET(GET_ACCOUNT_INFO)
    Observable<AccountBean> getAccountInfo(@Header("Authorization") String authorization, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Header("Content-Type") String Content_Type, @Header("Session-Token") String Session_Token, @Path("uid") int uid);

    @GET(GET_MODEL_STATE)
    Observable<AccountStateBean> getAccountState(@Header("Authorization") String authorization, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Header("Content-Type") String Content_Type, @Header("Session-Token") String Session_Token, @Path("uid") int uid);

    @POST(START_MATCH)
    Observable<Object> startMatch(@Header("Authorization") String authorization, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp
            , @Header("Content-Type") String Content_Type, @Header("Session-Token") String Session_Token, @Body MatchBean matchBean);

    @POST(START_SKIP)
    Observable<Object> startSkip(@Header("Authorization") String authorization, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp
            , @Header("Content-Type") String Content_Type, @Header("Session-Token") String Session_Token, @Body SkipBean skipBean);

    @GET(GET_LANGUAGE_LIST)
    Observable<LanguageBean> getLanguageList(@Header("Authorization") String authorization, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Header("Content-Type") String Content_Type, @Header("Session-Token") String Session_Token);

    /*==============================model=======================================*/
    String BASE_BROADCASTER_URL = "https://broadcaster.hitradegate.com/v1/";

    @GET(GET_CHANNEL_TOKEN)
    Observable<ChannelTokenBean> getChannelToken(@Header("Authorization") String authorization, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Path("channelId") String channelId);
}
