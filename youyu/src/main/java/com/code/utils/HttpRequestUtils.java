package com.code.utils;

import android.content.Context;

import com.code.bean.ChannelInfoBean;
import com.code.bean.CreateChannelBean;
import com.code.bean.ChannelResultBean;
import com.code.bean.JoinChannelBean;
import com.code.listener.HttpRequestListener;
import com.code.listener.RequestModelListListener;
import com.code.okhttp.WSManager;
import com.code.retrofit.RetrofitHelper;
import com.code.retrofit.RxObserver;
import com.code.youyu.api.HttpApi;

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

    public void getModelList(Context context, RequestModelListListener requestModelListListener) {
        String access_key_secret = RtcSpUtils.getInstance().getAccessKeySecret();
        String access_key_id = RtcSpUtils.getInstance().getAccessKeyId();
        String session_token = RtcSpUtils.getInstance().getSessionToken();
        long currentTimeMillis = System.currentTimeMillis();
        String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
        String Content_Type = "application/json";
        String data = X_Uyj_Timestamp;// + Content_Type;
        String sign = DataUtils.sha256_HMAC(access_key_secret, data);
        String authorization = "UYJ-HMAC-SHA256 " + access_key_id + ", X-Uyj-Timestamp;Content-Type, " + sign;
        RetrofitHelper.createApi(HttpApi.class, context)
                .getModelList(authorization, session_token, X_Uyj_Timestamp)
                .compose(RetrofitHelper.schedulersTransformer())
                .subscribe(new RxObserver() {

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

    public void getChannelToken(Context context, String channelId, String uid, HttpRequestListener httpRequestListener) {
        String access_key_secret = RtcSpUtils.getInstance().getAccessKeySecret();
        String access_key_id = RtcSpUtils.getInstance().getAccessKeyId();
        String session_token = RtcSpUtils.getInstance().getSessionToken();
        long currentTimeMillis = System.currentTimeMillis();
        String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
        String Content_Type = "application/json";
        String data = X_Uyj_Timestamp + Content_Type;
        String sign = DataUtils.sha256_HMAC(access_key_secret, data);
        String authorization = "UYJ-HMAC-SHA256 " + access_key_id + ", X-Uyj-Timestamp;Content-Type, " + sign;
        RetrofitHelper.createApi(HttpApi.class, context)
                .getChannelToken(authorization, X_Uyj_Timestamp, Content_Type, channelId, uid)
                .compose(RetrofitHelper.schedulersTransformer())
                .subscribe(new RxObserver() {
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

    public void joinChannel(Context context, String channelId, String uid, String peerUid, HttpRequestListener httpRequestListener) {
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
        joinChannelBean.setUid(uid);
        joinChannelBean.setBroadcaster(peerUid);
        RetrofitHelper.createApi(HttpApi.class, context)
                .joinChannel(authorization, X_Uyj_Timestamp, Content_Type, channelId, joinChannelBean)
                .compose(RetrofitHelper.schedulersTransformer())
                .subscribe(new RxObserver() {
                    @Override
                    public void Success(Object o) {
                        httpRequestListener.requestSuccess(o);
                        ChannelResultBean channelResultBean = (ChannelResultBean) o;
                        ChannelInfoBean ch = channelResultBean.getCh();
                        String serverChannelId = ch.getId();
                        String token = channelResultBean.getToken();
                        LogUtil.d("ZHIZHI", "joinChannel success channelId:" + channelId + " serverChannelId:" + serverChannelId + " token:" + token);
                        if (channelId.equals(serverChannelId)) {
                            WSManager.getInstance().joinChannel(channelId, token, Integer.parseInt(uid), Constants.CLIENT_ROLE_BROADCASTER);
                        }
                    }

                    @Override
                    public void error(int code, String error) {
                        httpRequestListener.requestError(code, error);
                    }
                });
    }

    public void createChannel(Context context, String uid, int category, HttpRequestListener httpRequestListener) {
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
        createChannelBean.setUid(uid);
        createChannelBean.setCategory(category);
        RetrofitHelper.createApi(HttpApi.class, context)
                .createChannel(authorization, X_Uyj_Timestamp, Content_Type, createChannelBean)
                .compose(RetrofitHelper.schedulersTransformer())
                .subscribe(new RxObserver() {
                    @Override
                    public void Success(Object o) {
                        httpRequestListener.requestSuccess(o);
                        ChannelResultBean channelResultBean = (ChannelResultBean) o;
                        ChannelInfoBean ch = channelResultBean.getCh();
                        String channelId = ch.getId();
                        String token = channelResultBean.getToken();
                        LogUtil.d("ZHIZHI", "createChannel success channelId:" + channelId + " token:" + token);
                        WSManager.getInstance().joinChannel(channelId, token, Integer.parseInt(uid), Constants.CLIENT_ROLE_AUDIENCE);
                    }

                    @Override
                    public void error(int code, String error) {
                        httpRequestListener.requestError(code, error);
                    }
                });
    }
}
