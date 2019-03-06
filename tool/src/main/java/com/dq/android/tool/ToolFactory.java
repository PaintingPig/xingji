package com.dq.android.tool;

import android.app.Application;

public class ToolFactory {
    public static Application application;

    public static void init(Application context){
        application = context;
    }
}
