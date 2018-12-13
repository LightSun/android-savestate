package com.heaven7.android.savestate2;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Created by heaven7 on 2018/12/13 0013.
 * @since 1.0.6
 */
public class JsonSaveStateDelegate extends BaseSaveStateDelegate<JsonObject> implements SaveStateDelegate<JsonObject>{

    private static Gson GSON = null;

    public JsonSaveStateDelegate(Object... holders) {
        super(holders);
    }

    public JsonSaveStateDelegate addHolder(Object holder){
        JsonConfig config = Util.getJsonConfig(holder);
        Gson gson;
        if(config == null){
            if(GSON == null){
                GSON = new Gson();
            }
            gson = GSON;
        }else{
            GsonBuilder builder = new GsonBuilder().setVersion(config.value());
            try {
                gson = config.gsonFactory().newInstance().create(builder);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        Util.init(holder, SaveInfoFactory.ofGson(gson), getSaveInfos());
        return this;
    }

    @Override
    public void onSaveInstanceState(@NonNull JsonObject outState) {
        try {
            for (SaveInfoDelegate sid : getSaveInfos()) {
                if(sid.getOwner() != null) {
                    outState.add(sid.getKey(), (JsonElement) sid.get());
                }
            }
        }catch (Exception e){
            if(e instanceof RuntimeException){
                throw (RuntimeException)e;
            }else{
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onRestoreInstanceState(@Nullable JsonObject savedInstanceState) {
        if(savedInstanceState == null){
            return;
        }
        try {
            for (SaveInfoDelegate sid : getSaveInfos()) {
                if(sid.getOwner() != null) {
                    sid.set(savedInstanceState.get(sid.getKey()));
                }
            }
        }catch (Exception e){
            if(e instanceof RuntimeException){
                throw (RuntimeException)e;
            }else{
                throw new RuntimeException(e);
            }
        }
    }
}
