package com.victor.okhttp.library.presenter.impl;

import com.victor.okhttp.library.model.HttpModel;
import com.victor.okhttp.library.model.impl.HttpModelImpl;
import com.victor.okhttp.library.presenter.HttpPresenter;
import com.victor.okhttp.library.presenter.OnHttpListener;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: BasePresenterImpl.java
 * Author: Victor
 * Date: 2018/9/6 18:25
 * Description:
 * -----------------------------------------------------------------
 */

public abstract class BasePresenterImpl<H,T> implements HttpPresenter<H,T>, OnHttpListener<T> {
    private HttpModel httpModelImpl;
    public abstract void detachView();

    public BasePresenterImpl () {
        httpModelImpl = new HttpModelImpl();
    }

    @Override
    public void sendRequest(String url, H header, T parm) {
        httpModelImpl.sendReuqest(url,header,parm,this);
    }

}
