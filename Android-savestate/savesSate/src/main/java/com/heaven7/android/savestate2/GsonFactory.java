package com.heaven7.android.savestate2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by heaven7 on 2018/12/13 0013.
 * @since 1.0.6
 */
public class GsonFactory {

    public Gson create(GsonBuilder builder){
        return builder.create();
    }

}
