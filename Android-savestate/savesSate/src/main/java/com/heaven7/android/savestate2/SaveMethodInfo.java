package com.heaven7.android.savestate2;

import android.text.TextUtils;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by heaven7 on 2018/12/12 0012.
 */
/*public*/ class SaveMethodInfo implements SaveInfoDelegate {

    private final WeakReference<Object> ownerRef;
    private final Method getMethod;
    private final Method setMethod;
    private final SaveStateMethod stateMethod;

    public SaveMethodInfo(Object owner, Method getMethod, Method setMethod) {
        this.ownerRef = new WeakReference<>(owner);
        this.stateMethod = getMethod.getAnnotation(SaveStateMethod.class);
        this.getMethod = getMethod;
        this.setMethod = setMethod;
    }

    @Override
    public int getType() {
        return stateMethod.flag();
    }

    @Override
    public Object getOwner() {
        return ownerRef.get();
    }

    @Override
    public String getKey() {
        String key = stateMethod.value();
        if(TextUtils.isEmpty(key)){
            String name = getMethod.getName();
            if(name.startsWith("get") || name.startsWith("set")){
                key = "method:" + name.substring(3);
            }else{
                key = "method:" + name;
            }
        }
        return key;
    }

    @Override
    public byte getByte() throws IllegalAccessException, InvocationTargetException {
        return (byte) getMethod.invoke(getOwner());
    }

    @Override
    public short getShort() throws IllegalAccessException ,InvocationTargetException{
        return (short) getMethod.invoke(getOwner());
    }

    @Override
    public int getInt() throws IllegalAccessException, InvocationTargetException {
        return (int) getMethod.invoke(getOwner());
    }

    @Override
    public boolean getBoolean() throws IllegalAccessException,InvocationTargetException {
        return (boolean) getMethod.invoke(getOwner());
    }

    @Override
    public double getDouble() throws IllegalAccessException,InvocationTargetException {
        return (double) getMethod.invoke(getOwner());
    }

    @Override
    public char getChar() throws IllegalAccessException, InvocationTargetException {
        return (char) getMethod.invoke(getOwner());
    }

    @Override
    public float getFloat() throws IllegalAccessException, InvocationTargetException {
        return (float) getMethod.invoke(getOwner());
    }

    @Override
    public long getLong() throws IllegalAccessException, InvocationTargetException {
        return (long) getMethod.invoke(getOwner());
    }

    @Override
    public Object get() throws IllegalAccessException, InvocationTargetException {
        return getMethod.invoke(getOwner());
    }

    @Override
    public void set(Object value) throws IllegalAccessException, InvocationTargetException {
        setMethod.invoke(getOwner(), value);
    }
}
