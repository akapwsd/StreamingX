package com.code.utils;

import android.content.Context;

import com.code.retrofit.RetrofitHelper;
import com.code.retrofit.RxObserver;
import com.code.youyu.api.HttpApi;

public class HttpRequestUtils {
    private static HttpRequestUtils httpRequestUtils;
    private HttpRequestListener mHttpRequestListener;

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

    public void requestToken(Context context, String accountToken, String access_key_id,
                             String access_key_secret,
                             String session_token, HttpRequestListener httpRequestListener) {
        mHttpRequestListener = httpRequestListener;
        long l = System.currentTimeMillis();
        String X_Uyj_Timestamp = String.valueOf(l);
        String Content_Type = "application/json";
        String data = X_Uyj_Timestamp + Content_Type;
        String sign = DataUtils.sha256_HMAC(access_key_secret, data);
        String authorization = "UYJ-HMAC-SHA256 " + access_key_id + ", X-Uyj-Timestamp;Content-Type, " + sign;
        RetrofitHelper.createApi(HttpApi.class, context)
                .getToken(AppSigningUtils.getSha1(context), accountToken, authorization, session_token, X_Uyj_Timestamp, Content_Type)
                .compose(RetrofitHelper.schedulersTransformer())
                .subscribe(new RxObserver<String>() {
                    @Override
                    public void Success(String o, String msg) {
                        mHttpRequestListener.requestSuccess(o, msg);
                    }

                    @Override
                    public void error(int code, String error) {
                        mHttpRequestListener.requestError(code, error);
                    }
                });
    }

    public interface HttpRequestListener {
        void requestSuccess(String o, String msg);

        void requestError(int code, String error);
    }

    public void removeListener() {
        if (mHttpRequestListener != null) {
            mHttpRequestListener = null;
        }
    }
}
