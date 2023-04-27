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
        RetrofitHelper.createApi(HttpApi.class, context)
                .getModelList()
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
        RetrofitHelper.createApi(HttpApi.class, context)
                .getChannelToken(channelId)
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
        RetrofitHelper.createApi(HttpApi.class, context)
                .joinChannel(channelId)
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

    public void createChannel(Context context, HttpRequestListener httpRequestListener) {
        RetrofitHelper.createApi(HttpApi.class, context)
                .createChannel()
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
