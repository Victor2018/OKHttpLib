package com.victor.module;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.victor.app.App;
import com.victor.interfaces.OnSSLCertifcationListener;
import com.victor.okhttp.library.module.AssetsFileReaderUtil;
import com.victor.util.Constant;

import java.util.HashMap;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: SSLCertifcationHelper
 * Author: Victor
 * Date: 2020/5/22 下午 03:13
 * Description:
 * -----------------------------------------------------------------
 */
public class SSLCertifcationHelper {
    private String TAG = "AirPlayHelper";
    private static final String clientPriKeyName = "client.bks";
    private static final String trustStorePubKeyName = "truststore.bks";
    private Context mContext;
    private Handler mSSLHandler;
    private HandlerThread mSSLHandlerThread;
    private OnSSLCertifcationListener mOnSSLCertifcationListener;

    public SSLCertifcationHelper(Context context, OnSSLCertifcationListener listener) {
        mContext = context;
        mOnSSLCertifcationListener = listener;
        startRequestTask ();
    }

    private void startRequestTask (){
        mSSLHandlerThread = new HandlerThread("SSLTask");
        mSSLHandlerThread.start();
        mSSLHandler = new Handler(mSSLHandlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case Constant.Action.SSL_ACTION:
                        String clientPriKey = AssetsFileReaderUtil.getStringFromAssets(mContext,clientPriKeyName);
                        String trustStorePubKey = AssetsFileReaderUtil.getStringFromAssets(mContext,trustStorePubKeyName);
                        App.get().setClientPriKey(clientPriKey);
                        App.get().setTrustStorePubKey(trustStorePubKey);
                        if (mOnSSLCertifcationListener != null) {
                            mOnSSLCertifcationListener.OnSSLCertifcation(clientPriKey,trustStorePubKey);
                        }
                        break;
                }
            }
        };
    }

    public void sendRequestWithParms (int action,Object requestData) {
        HashMap<Integer, Object> requestMap = new HashMap<Integer, Object>();
        requestMap.put(action, requestData);
        Message msg = mSSLHandler.obtainMessage(action,requestMap);
        mSSLHandler.sendMessage(msg);
    }

    public void sendRequest (int msg) {
        mSSLHandler.sendEmptyMessage(msg);
    }

    public void onDestroy (){
        if (mSSLHandlerThread != null) {
            mSSLHandlerThread.quit();
            mSSLHandlerThread = null;
        }
    }
}
