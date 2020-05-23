package com.victor.okhttp.library.module;

import android.util.Log;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: UnSafeHostnameVerifier
 * Author: Victor
 * Date: 2020/5/22 上午 10:41
 * Description:
 * -----------------------------------------------------------------
 */
public class UnSafeHostnameVerifier implements HostnameVerifier {
    private String TAG = "UnSafeHostnameVerifier";
    @Override
    public boolean verify(String hostname, SSLSession session) {
        Log.e(TAG,"verify-hostname = "+hostname);
        return true;
    }
}