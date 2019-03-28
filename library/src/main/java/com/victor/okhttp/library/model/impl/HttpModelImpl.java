package com.victor.okhttp.library.model.impl;

import com.victor.okhttp.library.model.HttpModel;
import com.victor.okhttp.library.module.HttpRequest;
import com.victor.okhttp.library.presenter.OnHttpListener;

import java.util.HashMap;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: HttpModelImpl.java
 * Author: Victor
 * Date: 2018/9/6 18:25
 * Description:
 * -----------------------------------------------------------------
 */

public class HttpModelImpl<H,T> implements HttpModel<H,T> {
    private String TAG = "HttpModelImpl";

    @Override
    public void sendReuqest(String url, H header, T parm, final OnHttpListener<T> listener) {
        HttpRequest.getInstance().sendRequest(url, (HashMap<String, String>) header, parm == null ? "" : parm.toString(), listener);
    }

}
