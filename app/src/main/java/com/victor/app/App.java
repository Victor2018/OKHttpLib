package com.victor.app;

import android.app.Application;
import android.util.Log;

import com.victor.module.SSLCertifcationHelper;
import com.victor.util.Constant;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: App
 * Author: Victor
 * Date: 2020/5/22 下午 03:13
 * Description:
 * -----------------------------------------------------------------
 */
public class App extends Application {
    private String TAG = "App";
    private static App instance;

    private String clientPriKey;
    private String trustStorePubKey;
    private SSLCertifcationHelper mSSLCertifcationHelper;

//    private BmsLoginReq bmsLoginReq;

   /* public void setBmsLoginReq(BmsLoginReq bmsLoginReq) {
        this.bmsLoginReq = bmsLoginReq;
        SharePreferencesUtil.putString(this,Constant.BMS_LOG_DATA_KEY, JSON.toJSONString(bmsLoginReq));
    }

    public BmsLoginReq getBmsLoginReq() {
        String logRes = SharePreferencesUtil.getString(this,Constant.BMS_LOG_DATA_KEY, JSON.toJSONString(bmsLoginReq));
        if (!TextUtils.isEmpty(logRes)) {
            bmsLoginReq = JSON.parseObject(logRes,BmsLoginReq.class);
        }
        return bmsLoginReq;
    }*/

    public App() {
        instance = this;
    }

    public static App get() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化crash日志捕获，放在其他库初始化前
        mSSLCertifcationHelper = new SSLCertifcationHelper(this,null);
        mSSLCertifcationHelper.sendRequest(Constant.Action.SSL_ACTION);
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        if (mSSLCertifcationHelper != null) {
            mSSLCertifcationHelper.onDestroy();
            mSSLCertifcationHelper = null;
        }
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        // 低内存的时候执行
        Log.e(TAG, "onLowMemory");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        // 程序在内存清理的时候执行（回收内存）
        // HOME键退出应用程序、长按MENU键，打开Recent TASK都会执行
        Log.e(TAG, "onTrimMemory");
        super.onTrimMemory(level);
    }

    public void setClientPriKey(String clientPriKey) {
        this.clientPriKey = clientPriKey;
    }

    public void setTrustStorePubKey(String trustStorePubKey) {
        this.trustStorePubKey = trustStorePubKey;
    }

    public String getClientPriKey() {
        return clientPriKey;
    }

    public String getTrustStorePubKey() {
        return trustStorePubKey;
    }

}
