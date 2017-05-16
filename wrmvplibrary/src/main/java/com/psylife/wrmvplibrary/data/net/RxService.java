package com.psylife.wrmvplibrary.data.net;


import com.psylife.wrmvplibrary.WRCoreApp;
import com.psylife.wrmvplibrary.utils.LogUtil;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hpw on 16/11/2.
 */

public class RxService {
    private static final int TIMEOUT_READ = 20;
    private static final int TIMEOUT_CONNECTION = 10;
    private static final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private static CacheInterceptor cacheInterceptor = new CacheInterceptor();
    private static Builder builder = new OkHttpClient().newBuilder();
    private static OkHttpClient okHttpClient = builder
            //SSL证书
            .sslSocketFactory(TrustManager.getUnsafeOkHttpClient())
            .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
            //打印日志
            .addInterceptor(interceptor)
            //设置Cache
            .addNetworkInterceptor(cacheInterceptor)//缓存方面需要加入这个拦截器
            .addInterceptor(cacheInterceptor)
//            .addNetworkInterceptor(chain -> {
//                Request original = chain.request();
//                Request request = original.newBuilder()
//                .addHeader("dcreatedate", "201705151633")
//                .addHeader("spid", "17621159290")
//                .addHeader("Content-Type", "application/x-www-form-urlencoded")
//                .build();
//                LogUtil.d("request:" + request.toString());
//                LogUtil.d("request headers:" + request.headers().toString());
//                return chain.proceed(request);
//            })

            .cache(HttpCache.getCache())
            //time out
            .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
            //失败重连
            .retryOnConnectionFailure(true)
            .build();


    public static <T> T createApi(Class<T> clazz) {
        return createApi(clazz, WRCoreApp.getInstance().setBaseUrl());
    }

    public static <T> T createApi(Class<T> clazz, String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(clazz);
    }

    /**
     * 请求拦截器，修改请求header
     */
    public static class RequestInterceptor implements Interceptor {

        Map<String, String> map;

        public RequestInterceptor(Map<String, String> map) {
            this.map = map;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request.Builder mBuild = original.newBuilder();
            for (Map.Entry<String, String> entry: map.entrySet()) {
                mBuild.addHeader(entry.getKey(), entry.getValue());
            }
            Request request = mBuild.build();
            LogUtil.d("request:" + request.toString());
            LogUtil.d("request headers:" + request.headers().toString());
            return chain.proceed(request);
        }
    }

    public static void setHeaders(Map<String, String> map) {
        RequestInterceptor requestInterceptor = new RequestInterceptor(map);
        okHttpClient = builder.addNetworkInterceptor(requestInterceptor).build();
    }
}

