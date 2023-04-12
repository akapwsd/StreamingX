package com.example.utils;

import android.content.Context;

import com.example.retrofit.RetrofitHelper;
import com.example.retrofit.RxObserver;
import com.example.youyu.api.HttpApi;

public class HttpRequestUtils {
    private static HttpRequestUtils httpRequestUtils;

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

    public void requestTest(Context context){
        RetrofitHelper.createApi(HttpApi.class,context)
                .getSummary("",0)
                .compose(RetrofitHelper.schedulersTransformer())
                .subscribe(new RxObserver<String>(){
                    @Override
                    public void Success(String o, String msg) {

                    }

                    @Override
                    public void error(int code, String error) {

                    }
                });
    }
}
