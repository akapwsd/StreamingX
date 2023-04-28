package com.code.utils;

import android.content.Context;

import com.code.bean.ModelBean;
import com.code.listener.HttpRequestListener;
import com.code.listener.RequestModelListListener;
import com.code.okhttp.WSManager;
import com.code.retrofit.RetrofitHelper;
import com.code.retrofit.RxObserver;
import com.code.youyu.api.HttpApi;

import java.util.ArrayList;

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
                .subscribe(new RxObserver<ArrayList<ModelBean>>() {

                    @Override
                    public void Success(ArrayList<ModelBean> modelBeans, String msg) {
                        requestModelListListener.onResult(modelBeans);
                    }

                    @Override
                    public void error(int code, String error) {
                        requestModelListListener.onFailure(code, error);
                    }
                });
    }

    public void getChannelToken(Context context, String channelId, HttpRequestListener httpRequestListener) {
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
                .getChannelToken(authorization, session_token, X_Uyj_Timestamp, channelId)
                .compose(RetrofitHelper.schedulersTransformer())
                .subscribe(new RxObserver<String>() {
                    @Override
                    public void Success(String o, String msg) {
                        httpRequestListener.requestSuccess(o, msg);
                    }

                    @Override
                    public void error(int code, String error) {
                        httpRequestListener.requestError(code, error);
                    }
                });
    }

    public void joinChannel(Context context, String channelId, HttpRequestListener httpRequestListener) {
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
                .joinChannel(authorization, session_token, X_Uyj_Timestamp, channelId)
                .compose(RetrofitHelper.schedulersTransformer())
                .subscribe(new RxObserver<String>() {
                    @Override
                    public void Success(String o, String msg) {
                        httpRequestListener.requestSuccess(o, msg);
                        String token = "";
                        WSManager.getInstance().joinChannel(channelId, token);
                    }

                    @Override
                    public void error(int code, String error) {
                        httpRequestListener.requestError(code, error);
                    }
                });
    }

    public void createChannel(Context context, String uid, HttpRequestListener httpRequestListener) {
        String access_key_secret = RtcSpUtils.getInstance().getAccessKeySecret();
        String access_key_id = RtcSpUtils.getInstance().getAccessKeyId();
        String session_token = RtcSpUtils.getInstance().getSessionToken();
        long currentTimeMillis = System.currentTimeMillis();
        String X_Uyj_Timestamp = String.valueOf(currentTimeMillis);
        String Content_Type = "application/json";
        String data = X_Uyj_Timestamp + Content_Type;
        String sign = DataUtils.sha256_HMAC(access_key_secret, data);
        String authorization = "UYJ-HMAC-SHA256 " + access_key_id + ", X-Uyj-Timestamp;Content-Type, " + sign;
        LogUtil.d(TAG, "aaaaa authorization:" + authorization + "\n X_Uyj_Timestamp:" + X_Uyj_Timestamp + "\n Content_Type:" + Content_Type);
        RetrofitHelper.createApi(HttpApi.class, context)
                .createChannel(authorization, X_Uyj_Timestamp, Content_Type, uid)
                .compose(RetrofitHelper.schedulersTransformer())
                .subscribe(new RxObserver<String>() {
                    @Override
                    public void Success(String o, String msg) {
                        httpRequestListener.requestSuccess(o, msg);
                        String channelId = "";
                        String token = "";
                        WSManager.getInstance().joinChannel(channelId, token);
                    }

                    @Override
                    public void error(int code, String error) {
                        httpRequestListener.requestError(code, error);
                    }
                });
    }
}
