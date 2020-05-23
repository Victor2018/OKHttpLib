package com.victor.interfaces;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: OnSSLCertifcationListener
 * Author: Victor
 * Date: 2020/5/22 下午 03:16
 * Description:
 * -----------------------------------------------------------------
 */
public interface OnSSLCertifcationListener {
    void OnSSLCertifcation(String clientPriKey, String trustStorePubKey);
}
