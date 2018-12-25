package com.personal.xingji.xingji;

import android.app.Application;
import android.content.Context;

public class XingJiApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();


    }


}
