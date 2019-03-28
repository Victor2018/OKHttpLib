package com.victor.okhttp.library.module;

import android.util.Log;

import com.victor.okhttp.library.data.HttpParm;
import com.victor.okhttp.library.data.Request;
import com.victor.okhttp.library.data.UpLoadParm;
import com.victor.okhttp.library.presenter.OnHttpListener;

import java.util.HashMap;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: HttpRequest.java
 * Author: Victor
 * Date: 2018/9/6 18:25
 * Description:
 * -----------------------------------------------------------------
 */

public class HttpRequest {
    private String TAG = "HttpRequest";

    private int requestMethod = Request.GET;

    public final static String mDefaultBodyContentType = "application/x-www-form-urlencoded; charset=";
    public final static String mJsonBodyContentType = "application/json; charset=";
    private String mBodyContentType = mDefaultBodyContentType;
    public final static String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36";

    public final static String character = "utf-8";

    public Class responseCls;//响应数据

    private static HttpRequest mHttpRequest;

    public static HttpRequest getInstance () {
        if (mHttpRequest == null) {
            synchronized (HttpRequest.class) {
                if (mHttpRequest == null) {
                    mHttpRequest = new HttpRequest();
                }
            }
        }
        return mHttpRequest;
    }

    /**
     * 设置请求方式
     */
    public void setRequestMethod (int method) {
        requestMethod = method;
    }

    /**
     * 设置请求数据格式
     * @param bodyContentType
     */
    public void setBodyContentType (String bodyContentType) {
        mBodyContentType = bodyContentType;
    }

    /**
     * 设置返回数据模型
     * @param cls
     */
    public void setResponseCls (Class cls) {
        responseCls = cls;
    }

    public <T> void sendRequest (String url, HashMap<String,String> headers,
                                 String parm, OnHttpListener<T> httpListener) {
        Log.e(TAG,"sendRequest()......");
        HttpParm httpParams = new HttpParm();
        httpParams.requestMethod = requestMethod;
        httpParams.responseCls = responseCls;
        httpParams.bodyContentType = mBodyContentType;
        httpParams.url = url;
        httpParams.headers = headers;
        httpParams.jsonParm = parm;
        OkHttpRequest.getInstance().sendRequest(httpParams,httpListener);
    }

    public <T> void sendMultipartUploadRequest (String url, UpLoadParm parm, OnHttpListener<T> httpListener) {
        Log.e(TAG, "sendMultipartUploadRequest()......");
        parm.responseCls = responseCls;
        OkHttpRequest.getInstance().sendMultipartUploadRequest(url,parm, httpListener);
    }
}
