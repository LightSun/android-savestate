package com.heaven7.android.savestate2;

import android.text.TextUtils;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/*public*/ class SaveFieldInfo implements SaveInfoDelegate {
    private Field field;
    private SaveStateField saveField;
    /** flag type */
    private int type;

    private final WeakReference<Object> ownerRef;

    public SaveFieldInfo(Field f, SaveStateField saveField, int type, Object owner) {
        this.field = f;
        this.saveField = saveField;
        this.type = type;
        this.ownerRef = new WeakReference<>(owner);
    }
    @Override
    public int getType(){
        return type;
    }
    @Override
    public Object getOwner(){
        return ownerRef.get();
    }

    @Override
    public String getKey(){
        String value = saveField.value();
        if(TextUtils.isEmpty(value)){
            return field.getName();
        }
        return value;
    }

    @Override
    public byte getByte() throws IllegalAccessException{
        return field.getByte(getOwner());
    }

    @Override
    public short getShort() throws IllegalAccessException {
        return field.getShort(getOwner());
    }
    @Override
    public int getInt() throws IllegalAccessException  {
        return field.getInt(getOwner());
    }

    @Override
    public boolean getBoolean() throws IllegalAccessException {
        return field.getBoolean(getOwner());
    }
    @Override
    public double getDouble() throws IllegalAccessException {
        return field.getDouble(getOwner());
    }

    @Override
    public char getChar() throws IllegalAccessException {
        return field.getChar(getOwner());
    }

    @Override
    public float getFloat() throws IllegalAccessException {
        return field.getFloat(getOwner());
    }

    @Override
    public long getLong() throws IllegalAccessException {
        return field.getLong(getOwner());
    }
    @Override
    public Object get() throws IllegalAccessException {
        return field.get(getOwner());
    }

    @Override
    public void set(Object value) throws IllegalAccessException {
        field.set(getOwner(), value);
    }
}
