package com.heaven7.android.savestate2.test;

import android.app.Application;

/**
 * Created by Administrator on 2017/5/4 0004.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
    }
}
