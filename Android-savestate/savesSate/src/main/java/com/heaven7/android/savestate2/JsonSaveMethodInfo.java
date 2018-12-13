package com.heaven7.android.savestate2;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by heaven7 on 2018/12/13 0013.
 * @since 1.0.6
 */
/*public*/ class JsonSaveMethodInfo extends SaveMethodInfo {

    private final Gson mGson;

    public JsonSaveMethodInfo(Object owner, Method getMethod, Method setMethod, Gson gson) {
        super(owner, getMethod, setMethod);
        this.mGson = gson;
    }

    @Override
    protected int getFlag(Method getMethod, SaveStateMethod ssm) {
        return 0;
    }

    @Override
    public Object get() throws IllegalAccessException, InvocationTargetException {
        Object value = super.get();
        if(value == null){
            return JsonNull.INSTANCE;
        }
        return new Gson().toJsonTree(value);
    }

    @Override
    public void set(Object value) throws IllegalAccessException, InvocationTargetException {
        if(value instanceof JsonNull){
            super.set(null);
        }else{
            super.set(mGson.fromJson((JsonElement) value, getMethod.getReturnType()));
        }
    }
}
