package com.victor.okhttp.library.presenter.impl;

import com.victor.okhttp.library.model.HttpModel;
import com.victor.okhttp.library.model.impl.HttpModelImpl;
import com.victor.okhttp.library.module.HttpRequest;
import com.victor.okhttp.library.presenter.HttpPresenter;
import com.victor.okhttp.library.presenter.OnHttpListener;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

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
    public abstract Class getViewCls();
    public abstract Class getViewImplCls();
    public abstract void detachView();

    public BasePresenterImpl () {
        httpModelImpl = new HttpModelImpl();
    }

    @Override
    public void sendRequest(String url, H header, T parm) {
        HttpRequest.getInstance().setResponseCls(getReponseClass(getViewCls(),getViewImplCls()));
        httpModelImpl.sendReuqest(url,header,parm,this);
    }

    /**
     * 获取返回数据实体
     * @param reponseCls
     * @param reponseImplCls
     * @return
     */
    public Class getReponseClass(Class reponseCls,Class reponseImplCls) {
        Class reponseClass = null;

        Method[] methods = reponseCls.getMethods();

        if (methods != null && methods.length > 0) {
            //1.获取所继承的所有接口
            Class<?>[] interfaces = reponseImplCls.getInterfaces();
            if (interfaces != null && interfaces.length > 0) {
                for(int i=0;i<interfaces.length;i++){
                    //3.获取接口中所有的方法
                    Method[] interMethods = interfaces[i].getDeclaredMethods();
                    if (interMethods != null && interMethods.length > 0) {
                        if (interMethods[0].getName().equals(methods[0].getName())) {
                            Type[] types = reponseImplCls.getGenericInterfaces();
                            ParameterizedType parameterizedType = (ParameterizedType) types[i];
                            Type[] params = parameterizedType.getActualTypeArguments();
                            if (params != null && params.length > 0) {
                                reponseClass = (Class) params[0];
                            }
                        }
                    }
                }
            }
        }

        return reponseClass;
    }

}
