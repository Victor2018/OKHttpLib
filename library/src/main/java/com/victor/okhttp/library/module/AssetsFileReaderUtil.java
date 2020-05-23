package com.victor.okhttp.library.module;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: AssetsJsonReaderUtil
 * Author: Victor
 * Date: 2020/4/9 上午 11:54
 * Description:
 * -----------------------------------------------------------------
 */
public class AssetsFileReaderUtil {

    public static void main(String[] args) {
    }

    public static String getStringFromAssets(Context context,String fileName) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open(fileName);
            byte[] buffer = new byte[1024];
            int size = is.read(buffer);
            while (size != -1) {
                baos.write(buffer, 0, size);
                size = is.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return baos.toString();
    }
}
