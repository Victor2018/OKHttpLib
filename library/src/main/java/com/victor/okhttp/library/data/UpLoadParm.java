package com.victor.okhttp.library.data;

import java.util.HashMap;
import java.util.List;

import javax.net.ssl.SSLSocketFactory;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: UpLoadParm.java
 * Author: Victor
 * Date: 2018/9/6 18:25
 * Description:
 * -----------------------------------------------------------------
 */

public class UpLoadParm {
    public SSLSocketFactory sslSocketFactory;
    public HashMap<String,String> headers;
    public List<FormImage> imgs;

    public Class responseCls;//响应数据
}
