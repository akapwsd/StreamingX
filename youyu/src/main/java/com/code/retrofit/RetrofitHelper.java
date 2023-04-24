package com.code.retrofit;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.code.youyu.api.HttpApi;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.security.cert.X509Certificate;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.X509TrustManager;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private static final String TAG = "RetrofitHelper";
    public static String baseUrl = HttpApi.BASE_URL;
    private volatile static Retrofit retrofitInstance = null;

    private static Context mContext;

    /**
     * 创建Retrofit请求Api
     *
     * @param clazz Retrofit Api接口
     * @return api实例
     */
    public static <T> T createApi(Class<T> clazz, Context context) {
        mContext = context;
        return getInstance().create(clazz);
    }

    /**
     * 获取Retrofit实例
     *
     * @return Retrofit
     */
    private static Retrofit getInstance() {
        if (null == retrofitInstance) {
            synchronized (Retrofit.class) {
                if (null == retrofitInstance) { // 双重检验锁,仅第一次调用时实例化
                    retrofitInstance = new Retrofit.Builder()
                            .client(buildOKHttpsClient())
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                            .baseUrl(baseUrl)
                            .build();
                }
            }
        }
        return retrofitInstance;
    }

    private static OkHttpClient buildOKHttpsClient() {
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> Log.d(TAG, "OkHttp====Message:" + message));
        loggingInterceptor.setLevel(level);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (Build.VERSION.SDK_INT < 29) {
            builder.sslSocketFactory(SSLSocketClient.getSSLSocketFactory());
        } else {
            builder.sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), new X509TrustManager() {

                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            });
        }
        builder.hostnameVerifier(SSLSocketClient.getHostnameVerifier());
        builder.retryOnConnectionFailure(false);
        builder.connectTimeout(20, TimeUnit.SECONDS);
        builder.addNetworkInterceptor(loggingInterceptor);
        builder.addNetworkInterceptor(new StethoInterceptor());
        builder.addInterceptor(chain -> {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("lg", getLanguage(mContext).toLowerCase().trim())
                    .build();
            return chain.proceed(request);
        });
        return builder.build();
    }

    private static OkHttpClient buildOKHttpClient() {
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> Log.d(TAG, "OkHttp====Message:" + message));
        loggingInterceptor.setLevel(level);
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .connectTimeout(20, TimeUnit.SECONDS)
                .addNetworkInterceptor(loggingInterceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(chain -> {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader("lg", getLanguage(mContext).toLowerCase().trim())
                            .build();
                    return chain.proceed(request);
                })
                .build();
    }


    public static ObservableTransformer schedulersTransformer() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private static String getLanguage(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        return language;
    }
}