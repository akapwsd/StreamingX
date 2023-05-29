package com.code.youyu.api;


import com.code.bean.AccountBean;
import com.code.bean.ApplyResultBean;
import com.code.bean.AvatarListBean;
import com.code.bean.ChannelTokenBean;
import com.code.bean.CreateChannelBean;
import com.code.bean.ChannelResultBean;
import com.code.bean.JoinChannelBean;
import com.code.bean.ModelCoverListBean;
import com.code.bean.ModelListBean;
import com.code.bean.NullBean;
import com.code.bean.SmsBean;
import com.code.bean.SmsCodeBean;
import com.code.bean.TokenBean;
import com.code.bean.UploadUserInfoBean;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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

    /*==============================model=======================================*/ String BASE_BROADCASTER_URL = "https://broadcaster.hitradegate.com/v1/";
    String POST_REGISTER_WITH_PHONE = "login/{phone}/phone";
    String POST_VALIDATE_SMS_CODE = "login/{receipt}/receipt";
    String GET_ACCOUNT_INFO = "broadcaster/{uid}/uid";
    String DELETE_AVATAR = "broadcaster/{uid}/uid/{md5}/md5/avatar";
    String PUT_SET_DEFAULT_AVATAR = "broadcaster/{uid}/uid/{md5}/md5/avatar";
    String GET_CHECK_APPLY_STATUS = "broadcaster/application/list";
    String POST_APPLY = "broadcaster/apply";
    String POST_UPLOAD_AVATAR = "broadcaster/{uid}/uid/avatar";
    String GET_TOKEN = "broadcaster/token/{uid}/uid";
    String PUT_UPLOAD_USER_INFO = "broadcaster/attributes";

    @POST(POST_REGISTER_WITH_PHONE)
    Observable<SmsBean> registerWithPhone(@Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Path("phone") String phone, @Body NullBean nullBean);

    @POST(POST_VALIDATE_SMS_CODE)
    Observable<TokenBean> validateSmsCode(@Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Path("receipt") String receipt, @Body SmsCodeBean smsCodeBean);

    @GET(GET_ACCOUNT_INFO)
    Observable<AccountBean> getAccountInfo(@Header("Authorization") String authorization, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Path("uid") int uid);

    @DELETE(DELETE_AVATAR)
    Observable<Object> deleteAvatar(@Header("Authorization") String authorization, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Path("uid") int uid, @Path("md5") String md5);

    @PUT(PUT_SET_DEFAULT_AVATAR)
    Observable<Object> setDefaultAvatar(@Header("Authorization") String authorization, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Path("uid") int uid, @Path("md5") String md5);

    @GET(GET_CHECK_APPLY_STATUS)
    Observable<ApplyResultBean> checkApplyStatus(@Header("Authorization") String authorization, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Query("uid") int uid, @Query("aType") int aType, @Query("page") int page, @Query("limit") int limit, @Query("aState") int aState);

    @POST(POST_APPLY)
    Observable<Object> applyModel(@Header("Authorization") String authorization, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Body UploadUserInfoBean userInfoBean);

    @POST(POST_UPLOAD_AVATAR)
    Observable<Object> uploadAvatar(@Header("Authorization") String authorization, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Path("uid") int uid, @Body AvatarListBean avatarListBean);

    @GET(GET_TOKEN)
    Observable<String> getToken(@Header("Authorization") String authorization, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Path("uid") int uid);

    @PUT(PUT_UPLOAD_USER_INFO)
    Observable<Object> uploadUserInfo(@Header("Authorization") String authorization, @Header("X-Uyj-Timestamp") String X_Uyj_Timestamp, @Body UploadUserInfoBean uploadUserInfoBean);
}
