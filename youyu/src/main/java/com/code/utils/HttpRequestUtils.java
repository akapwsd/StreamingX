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

    public void requestToken(Context context, HttpRequestListener httpRequestListener) {
        mHttpRequestListener = httpRequestListener;
        RetrofitHelper.createApi(HttpApi.class, context)
                .getToken(AppSigningUtils.getSha1(context))
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
