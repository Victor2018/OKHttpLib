package com.victor.okhttp.library.module;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.victor.okhttp.library.data.HttpParm;
import com.victor.okhttp.library.data.Request;
import com.victor.okhttp.library.data.UpLoadParm;
import com.victor.okhttp.library.presenter.OnHttpListener;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: OkHttpRequest.java
 * Author: Victor
 * Date: 2018/9/6 18:25
 * Description:
 * -----------------------------------------------------------------
 */

public class OkHttpRequest {
    private String TAG = "OkHttpRequest";
    /**
     * 连接超时
     */
    private static final long CONNECT_TIMEOUT_MILLIS = 10;

    /**
     * 读取超时
     */
    private static final long READ_TIMEOUT_MILLIS = 10;

    /**
     * 写入超时
     */
    private static final long WRITE_TIMEOUT_MILLIS = 10;

    private static OkHttpRequest mOkHttpRequest;
    /**
     * 网络请求客户端
     */
    private static OkHttpClient okHttpClient;

    public static OkHttpRequest getInstance() {
        if (mOkHttpRequest == null) {
            synchronized (OkHttpRequest.class) {
                if (mOkHttpRequest == null) {
                    mOkHttpRequest = new OkHttpRequest();
                }
            }
        }
        return mOkHttpRequest;
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            synchronized (OkHttpClient.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient.Builder()
                            .connectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.SECONDS)
                            .readTimeout(READ_TIMEOUT_MILLIS, TimeUnit.SECONDS)
                            .writeTimeout(WRITE_TIMEOUT_MILLIS, TimeUnit.SECONDS)
                            //.addNetworkInterceptor(new HttpUtils.RetryInterceptor())
                            .build();
                }
            }
        }
        return okHttpClient;
    }

    public <T> OkHttpMethod<T> sendGetRequest (String url, HashMap<String,String> headers, String parm, Class<T> clazz, OnHttpListener<T> listener) {
        OkHttpGetRequest<T> request = new OkHttpGetRequest<T>(url,headers,parm,clazz,getOkHttpClient(),listener);
        return request;
    }
    public <T> OkHttpMethod<T> sendPostRequest (String url, HashMap<String,String> headers, String parm, String bodyContentType,
                                                Class<T> clazz, OnHttpListener<T> listener) {

        OkHttpPostRequest<T> request = new OkHttpPostRequest<T>(url,headers,parm,bodyContentType,clazz,getOkHttpClient(),listener);
        return request;
    }

    public <T> OkHttpMethod<T> sendRequest(HttpParm httpParm, OnHttpListener<T> listener) {
        Log.e(TAG,"request url = " + httpParm.url);
        Log.e(TAG,"request parm = " + JSON.toJSONString(httpParm));
        Log.e(TAG,"request requestMethod = " + (httpParm.requestMethod == 0 ? "GET" : "POST"));
        OkHttpMethod<T> request = null;
        if (httpParm.requestMethod == Request.POST) {
            request = sendPostRequest(httpParm.url,httpParm.headers,httpParm.jsonParm,
                    httpParm.bodyContentType,httpParm.responseCls,listener);
        } else if (httpParm.requestMethod == Request.GET) {
            request = sendGetRequest(httpParm.url,httpParm.headers,httpParm.jsonParm,httpParm.responseCls,listener);
        }
        request.sendRequest();
        return request;
    }

    public <T> OkHttpMethod<T> sendMultipartUploadRequest (String url, UpLoadParm upLoadParm, OnHttpListener<T> listener) {
        Log.e(TAG,"request url = " + url);
        Log.e(TAG,"request parm = " + JSON.toJSONString(upLoadParm));
        OkHttpPostImgRequest<T> request = new OkHttpPostImgRequest<T>(url,upLoadParm.responseCls,upLoadParm,
                getOkHttpClient(),listener);
        request.sendRequest();
        return request;
    }
}
