package com.code.utils;

import android.content.Context;

import com.code.bean.ChannelInfoBean;
import com.code.bean.CreateChannelBean;
import com.code.bean.ChannelResultBean;
import com.code.bean.JoinChannelBean;
import com.code.bean.ModelRequestBean;
import com.code.listener.HttpRequestListener;
import com.code.listener.RequestModelListListener;
import com.code.okhttp.WSManager;
import com.code.retrofit.RetrofitHelper;
import com.code.retrofit.RxObserver;
import com.code.youyu.api.HttpApi;
import com.code.youyu.api.RtcManager;

import io.agora.rtc.Constants;

public class HttpRequestUtils {
    private static HttpRequestUtils httpRequestUtils;
    public static final String TAG = "HttpRequestUtils";

    public static HttpRequestUtils getInstance() {
        if (httpRequestUtils == null) {
            synchronized (HttpRequestUtils.class) {
                if (httpRequestUtils == null) {
                    httpRequestUtils = new HttpRequestUtils();
                }
            }
        }
        return httpRequestUtils;
    }

    public void getModelList(Context context, int limit, RequestModelListListener requestModelListListener) {
        String access_key_secret = RtcSpUtils.getInstance().getAccessKeySecret();
        String access_key_id = RtcSpUtils.getInstance().getAccessKeyId();
        String session_token = RtcSpUtils.getInstance().getSessionToken();
        long currentTimeMillis = System.currentTimeMillis();
        String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
        String Content_Type = "application/json";
        String data = X_Uyj_Timestamp + Content_Type;
        String sign = DataUtils.sha256_HMAC(access_key_secret, data);
        String authorization = "UYJ-HMAC-SHA256 " + access_key_id + ", X-Uyj-Timestamp;Content-Type, " + sign;
        ModelRequestBean modelRequestBean = new ModelRequestBean();
        modelRequestBean.setLimit(limit);
        RetrofitHelper.createApi(HttpApi.class, context).getModelList(authorization, X_Uyj_Timestamp, Content_Type, session_token, modelRequestBean).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {

            @Override
            public void Success(Object modelBeans) {
//                        requestModelListListener.onResult(modelBeans);
            }

            @Override
            public void error(int code, String error) {
                requestModelListListener.onFailure(code, error);
            }
        });
    }

    public void getChannelToken(Context context, String channelId, HttpRequestListener httpRequestListener) {
        LogUtil.d(TAG, "getChannelToken is start channelId:" + channelId);
        String access_key_secret = RtcSpUtils.getInstance().getAccessKeySecret();
        String access_key_id = RtcSpUtils.getInstance().getAccessKeyId();
        String session_token = RtcSpUtils.getInstance().getSessionToken();
        long currentTimeMillis = System.currentTimeMillis();
        String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
        String Content_Type = "application/json";
        String data = X_Uyj_Timestamp + Content_Type;
        String sign = DataUtils.sha256_HMAC(access_key_secret, data);
        String authorization = "UYJ-HMAC-SHA256 " + access_key_id + ", X-Uyj-Timestamp;Content-Type, " + sign;
        RetrofitHelper.createApi(HttpApi.class, context).getChannelToken(authorization, X_Uyj_Timestamp, Content_Type, session_token, channelId).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {
            @Override
            public void Success(Object o) {
                httpRequestListener.requestSuccess(o);
            }

            @Override
            public void error(int code, String error) {
                httpRequestListener.requestError(code, error);
            }
        });
    }

    public void joinChannel(Context context, String channelId, String peerUid, int category, HttpRequestListener httpRequestListener) {
        String access_key_secret = RtcSpUtils.getInstance().getAccessKeySecret();
        String access_key_id = RtcSpUtils.getInstance().getAccessKeyId();
        String session_token = RtcSpUtils.getInstance().getSessionToken();
        long currentTimeMillis = System.currentTimeMillis();
        String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
        String Content_Type = "application/json";
        String data = X_Uyj_Timestamp + Content_Type;
        String sign = DataUtils.sha256_HMAC(access_key_secret, data);
        String authorization = "UYJ-HMAC-SHA256 " + access_key_id + ", X-Uyj-Timestamp;Content-Type, " + sign;
        JoinChannelBean joinChannelBean = new JoinChannelBean();
        joinChannelBean.setBroadcaster(peerUid);
        RetrofitHelper.createApi(HttpApi.class, context).joinChannel(authorization, X_Uyj_Timestamp, Content_Type, session_token, channelId, joinChannelBean).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {
            @Override
            public void Success(Object o) {
                httpRequestListener.requestSuccess(o);
                ChannelResultBean channelResultBean = (ChannelResultBean) o;
                ChannelInfoBean ch = channelResultBean.getCh();
                String serverChannelId = ch.getId();
                String token = channelResultBean.getToken();
                String uniqId = channelResultBean.getUniqId();
                RtcManager.getInstance().localUid = Integer.parseInt(uniqId);
                LogUtil.d(TAG, "joinChannel success channelId:" + channelId + " serverChannelId:" + serverChannelId + " token:" + token + " uniqId:" + uniqId);
                if (channelId.equals(serverChannelId)) {
                    WSManager.getInstance().joinChannel(channelId, token, Integer.parseInt(uniqId), Constants.CLIENT_ROLE_BROADCASTER, category);
                }
            }

            @Override
            public void error(int code, String error) {
                httpRequestListener.requestError(code, error);
            }
        });
    }

    public void createChannel(Context context, int category, HttpRequestListener httpRequestListener) {
        String access_key_secret = RtcSpUtils.getInstance().getAccessKeySecret();
        String access_key_id = RtcSpUtils.getInstance().getAccessKeyId();
        String session_token = RtcSpUtils.getInstance().getSessionToken();
        long currentTimeMillis = System.currentTimeMillis();
        String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
        String Content_Type = "application/json";
        String data = X_Uyj_Timestamp + Content_Type;
        String sign = DataUtils.sha256_HMAC(access_key_secret, data);
        String authorization = "UYJ-HMAC-SHA256 " + access_key_id + ", X-Uyj-Timestamp;Content-Type, " + sign;
        LogUtil.d(TAG, "authorization:" + authorization + "\n X_Uyj_Timestamp:" + X_Uyj_Timestamp + "\n Content_Type:" + Content_Type);
        CreateChannelBean createChannelBean = new CreateChannelBean();
        createChannelBean.setCategory(category);
        RetrofitHelper.createApi(HttpApi.class, context).createChannel(authorization, X_Uyj_Timestamp, Content_Type, session_token, createChannelBean).compose(RetrofitHelper.schedulersTransformer()).subscribe(new RxObserver() {
            @Override
            public void Success(Object o) {
                httpRequestListener.requestSuccess(o);
                ChannelResultBean channelResultBean = (ChannelResultBean) o;
                ChannelInfoBean ch = channelResultBean.getCh();
                String channelId = ch.getId();
                String token = channelResultBean.getToken();
                String uniqId = channelResultBean.getUniqId();
                RtcManager.getInstance().localUid = Integer.parseInt(uniqId);
                LogUtil.d(TAG, "createChannel success channelId:" + channelId + " token:" + token + " uniqId:" + uniqId);
                WSManager.getInstance().joinChannel(channelId, token, Integer.parseInt(uniqId), Constants.CLIENT_ROLE_BROADCASTER, category);
            }

            @Override
            public void error(int code, String error) {
                httpRequestListener.requestError(code, error);
            }
        });
    }
}
