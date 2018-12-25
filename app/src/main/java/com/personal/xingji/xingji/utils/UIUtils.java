package com.personal.xingji.xingji.utils;

import android.app.Application;
import android.content.Context;

import com.personal.xingji.xingji.XingJiApplication;

public class UIUtils {
    private final static Context context = XingJiApplication.context;

    public static int dip2px( float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
