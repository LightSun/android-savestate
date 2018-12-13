package com.heaven7.android.savestate2;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by heaven7 on 2018/12/13 0013.
 * @since 1.0.6
 */
/*public*/ class SaveInfoFactory {

    public static final SaveInfoFactory DEFAULT = new SaveInfoFactory();

    public SaveInfoDelegate createFieldInfo(Field f, SaveStateField ssf, Object owner) {
        return new SaveFieldInfo(f, ssf,
                SaveStateUtil.getFlag(f.getType(), ssf.flag() ,"field name = " + f.getName()),
                owner);
    }

    public SaveInfoDelegate createMethodInfo(Method get, Method set, Object owner) {
        return new SaveMethodInfo(owner, get, set);
    }

    public static SaveInfoFactory ofGson(Gson gson){
        return new JsonSaveInfoFactory(gson);
    }

    private static final class JsonSaveInfoFactory extends SaveInfoFactory{

        private final @NonNull
        Gson gson;

        public JsonSaveInfoFactory(@NonNull Gson gson) {
            this.gson = gson;
        }

        public SaveInfoDelegate createFieldInfo(Field f, SaveStateField ssf, Object owner) {
            return new JsonSaveFieldInfo(f, ssf, owner, gson);
        }

        public SaveInfoDelegate createMethodInfo(Method get, Method set, Object owner) {
            return new JsonSaveMethodInfo(owner, get, set, gson);
        }
    }

}
