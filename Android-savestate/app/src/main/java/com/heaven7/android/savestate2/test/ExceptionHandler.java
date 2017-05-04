package com.heaven7.android.savestate2.test;

import android.content.Context;

import com.heaven7.core.util.Logger;

import java.lang.ref.WeakReference;

/**
 * the exception handler.
 * Created by heaven7 on 2017/4/11 0011.
 */

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    private final WeakReference<Context> mContext;

    public ExceptionHandler(Context mContext) {
        this.mContext = new WeakReference<Context>(mContext);
    }

    @Override
    public void uncaughtException(Thread t, final Throwable e) {
        final Context context = mContext.get();
        if(context == null){
            Logger.i("ExceptionHandler","uncaughtException","context == null");
            return;
        }
        if (e != null) {
            // e.printStackTrace();
            Logger.e("ExceptionHandler", "uncaughtException", Logger.toString(e));
            try {
                //Logger.write2SD("ExceptionHandler", e, LogFileCutUtil.getLogFilename());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        RestartAppTool.restartAPP(context, 1500);
    }
}
