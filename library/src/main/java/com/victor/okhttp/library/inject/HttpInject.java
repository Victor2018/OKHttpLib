package com.victor.okhttp.library.inject;

import com.victor.okhttp.library.annotation.HttpParms;
import com.victor.okhttp.library.module.HttpRequest;
import com.victor.okhttp.library.module.OkHttpRequest;

import java.lang.reflect.Method;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: HttpInject.java
 * Author: Victor
 * Date: 2018/9/6 18:25
 * Description: http 注解解析
 * -----------------------------------------------------------------
 */

public class HttpInject {
    private static String TAG = "HttpInject";
    /**
     * 解析注解 http request method
     */
    public static void inject(Object obj) {
        Class cls = obj.getClass();
        Method[] methods = cls.getDeclaredMethods();

        for (Method method : methods) {
            try {
                HttpParms httpParms = method.getAnnotation(HttpParms.class);
                if (httpParms != null) {
                    HttpRequest.getInstance().setRequestMethod(httpParms.method());
                    HttpRequest.getInstance().setBodyContentType(httpParms.bodyContentType());
//                    HttpRequest.getInstance().setResponseCls(httpParms.responseCls());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
