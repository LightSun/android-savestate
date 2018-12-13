package com.heaven7.android.savestate2;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * Created by heaven7 on 2018/12/13 0013.
 */
/*public*/ class JsonSaveFieldInfo extends SaveFieldInfo implements SaveInfoDelegate {

    private final Gson mGson;

    public JsonSaveFieldInfo(Field f, SaveStateField saveField, Object owner, Gson gson) {
        super(f, saveField, saveField.flag(), owner);
        this.mGson = gson;
    }

    @Override
    public void set(Object value) throws IllegalAccessException {
        Object owner = getOwner();
        if(value instanceof JsonNull){
            field.set(owner, null);
        }else {
            //for primitive
            JsonElement je = (JsonElement) value;
            JsonAdapter adapter = field.getAnnotation(JsonAdapter.class);
            Class<?> type = field.getType();
            if(adapter != null){
                try {
                    Class<?> clazz = adapter.value();
                    if (TypeAdapter.class.isAssignableFrom(clazz)) {
                        TypeAdapter ta = (TypeAdapter) clazz.newInstance();
                        field.set(owner, ta.fromJsonTree(je));
                    }else if(TypeAdapterFactory.class.isAssignableFrom(clazz)){
                        TypeAdapterFactory ta = (TypeAdapterFactory) clazz.newInstance();
                        TypeAdapter a = ta.create(mGson, TypeToken.get(type));
                        if(a != null) {
                            field.set(owner, a.fromJsonTree(je));
                        }else{
                            field.set(owner, new Gson().fromJson(je, type));
                        }
                    }else if(JsonDeserializer.class.isAssignableFrom(clazz)){
                        JsonDeserializer ta = (JsonDeserializer) clazz.newInstance();
                        value = ta.deserialize(je, type, new GsonContextImpl());
                        field.set(owner, value);
                    }else {
                        throw new RuntimeException("@JsonAdapter wrong class. " + clazz.getClass().getName());
                    }
                }catch (IllegalAccessException e){
                    throw new RuntimeException(e);
                }catch (InstantiationException e){
                    throw new RuntimeException(e);
                }
            }else{
                field.set(owner, new Gson().fromJson(je, type));
            }
        }
    }

    @Override
    public Object get() throws IllegalAccessException {
        Object value = field.get(getOwner());
        if(value == null){
            return JsonNull.INSTANCE;
        }
        JsonAdapter adapter = field.getAnnotation(JsonAdapter.class);
        if(adapter != null){
            try {
                Class<?> clazz = adapter.value();
                if (TypeAdapter.class.isAssignableFrom(clazz)) {
                    TypeAdapter ta = (TypeAdapter) clazz.newInstance();
                    return ta.toJsonTree(value);
                }else if(TypeAdapterFactory.class.isAssignableFrom(clazz)){
                    TypeAdapterFactory ta = (TypeAdapterFactory) clazz.newInstance();
                    TypeAdapter a = ta.create(mGson, TypeToken.get(field.getType()));
                    if(a != null) {
                        return a.toJsonTree(value);
                    }
                }else if(JsonSerializer.class.isAssignableFrom(clazz)){
                    JsonSerializer ta = (JsonSerializer) clazz.newInstance();
                    return ta.serialize(value, field.getType(), new GsonContextImpl());
                }else {
                    throw new RuntimeException("@JsonAdapter wrong class. " + clazz.getClass().getName());
                }
            }catch (IllegalAccessException e){
                throw new RuntimeException(e);
            }catch (InstantiationException e){
                throw new RuntimeException(e);
            }
        }
        return mGson.toJsonTree(value);
    }

    private final class GsonContextImpl implements JsonSerializationContext, JsonDeserializationContext {
        @Override public JsonElement serialize(Object src) {
            return mGson.toJsonTree(src);
        }
        @Override public JsonElement serialize(Object src, Type typeOfSrc) {
            return mGson.toJsonTree(src, typeOfSrc);
        }
        @SuppressWarnings("unchecked")
        @Override public <R> R deserialize(JsonElement json, Type typeOfT) throws JsonParseException {
            return (R) mGson.fromJson(json, typeOfT);
        }
    }
}
