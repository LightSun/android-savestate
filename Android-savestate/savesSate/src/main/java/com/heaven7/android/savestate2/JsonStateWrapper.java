package com.heaven7.android.savestate2;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by heaven7 on 2018/12/13 0013.
 * @since 1.0.6
 */
public class JsonStateWrapper {

    private final JsonSaveStateDelegate mDelegate;

    public JsonStateWrapper(@NonNull JsonSaveStateDelegate mDelegate) {
        this.mDelegate = mDelegate;
    }

    public JsonSaveStateDelegate getJsonSaveStateDelegate() {
        return mDelegate;
    }

    public static JsonStateWrapper of(Object...holders){
        return new JsonStateWrapper(new JsonSaveStateDelegate(holders));
    }

    public String onSaveInstanceState(){
        JsonObject jo = new JsonObject();
        mDelegate.onSaveInstanceState(jo);
        return jo.toString();
    }

    public void onRestoreInstanceState(String json){
        if(!TextUtils.isEmpty(json)){
            mDelegate.onRestoreInstanceState(new JsonParser().parse(json).getAsJsonObject());
        }
    }
}
