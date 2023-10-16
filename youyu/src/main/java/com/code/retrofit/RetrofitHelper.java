package com.code.retrofit;

import android.content.Context;
import android.os.Build;

import com.code.utils.LogUtil;
import com.code.youyu.api.HttpApi;
import com.code.youyu.api.StreamingXRtcManager;
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
import retrofit2.converter.protobuf.ProtoConverterFactory;

public class RetrofitHelper {
    private static final String TAG = "RetrofitHelper";
    public static final int MODEL = 2;
    public static final int USER = 1;
    public static String baseUrl = HttpApi.BASE_URL;
    public final static int GSON_TYPE = 0;
    public final static int PROTOBUF_TYPE = 1;
    private volatile static Retrofit retrofitInstance = null;
    private static Context mContext;
    private static int dataType = GSON_TYPE;

    public static <T> T createApi(Class<T> clazz, Context context) {
        mContext = context;
        return getInstance(USER).create(clazz);
    }

    public static <T> T createApi(Class<T> clazz, Context context, int type) {
        mContext = context;
        return getInstance(type).create(clazz);
    }

    private static Retrofit getInstance(int type) {
        if (null == retrofitInstance) {
            synchronized (Retrofit.class) {
                if (type == MODEL) {
                    if (StreamingXRtcManager.getInstance().isEnableDebug) {
                        baseUrl = HttpApi.BASE_BROADCASTER_TEST_URL;
                    } else {
                        baseUrl = HttpApi.BASE_BROADCASTER_URL;
                    }
                } else {
                    if (StreamingXRtcManager.getInstance().isEnableDebug) {
                        baseUrl = HttpApi.BASE_TEST_URL;
                    } else {
                        baseUrl = HttpApi.BASE_URL;
                    }
                }
                if (null == retrofitInstance) { // 双重检验锁,仅第一次调用时实例化
                    Retrofit.Builder builder = new Retrofit.Builder()
                            .client(buildOKHttpsClient())
                            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                            .baseUrl(baseUrl);
                    if (dataType == PROTOBUF_TYPE) {
                        builder.addConverterFactory(ProtoConverterFactory.create());
                    } else {
                        builder.addConverterFactory(GsonConverterFactory.create());
                    }
                    retrofitInstance = builder.build();
                }
            }
        }
        return retrofitInstance;
    }

    private static OkHttpClient buildOKHttpsClient() {
        LogUtil.d(TAG, "buildOKHttpsClient is start");
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> LogUtil.d(TAG, "OkHttp====Message:" + message));
        loggingInterceptor.setLevel(level);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        LogUtil.d(TAG, "buildOKHttpsClient sdk version:" + Build.VERSION.SDK_INT);
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
        LogUtil.d(TAG, "buildOKHttpClient is start");
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> LogUtil.d(TAG, "OkHttp====Message:" + message));
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
