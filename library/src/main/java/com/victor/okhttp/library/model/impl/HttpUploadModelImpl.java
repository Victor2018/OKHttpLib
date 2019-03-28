package com.victor.okhttp.library.model.impl;

import android.text.TextUtils;

import com.victor.okhttp.library.data.UpLoadParm;
import com.victor.okhttp.library.model.HttpModel;
import com.victor.okhttp.library.module.HttpRequest;
import com.victor.okhttp.library.presenter.OnHttpListener;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: HttpUploadModelImpl.java
 * Author: Victor
 * Date: 2018/9/6 18:25
 * Description:
 * -----------------------------------------------------------------
 */

public class HttpUploadModelImpl<H,T> implements HttpModel<H,T> {
    private String TAG = "HttpModelImpl";

    @Override
    public void sendReuqest(String url, H header, T parm, final OnHttpListener<T> listener) {
        HttpRequest.getInstance().sendMultipartUploadRequest(url, (UpLoadParm) (parm), listener);
    }
}
