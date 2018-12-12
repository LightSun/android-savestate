package com.heaven7.android.savestate2;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by heaven7 on 2018/12/12 0012.
 */
interface SaveInfoDelegate {

    /**
     * get the value type
     * @return the value type.
     */
    int getType();

    Object getOwner();

    String getKey();

    byte getByte() throws IllegalAccessException, InvocationTargetException;

    short getShort() throws IllegalAccessException, InvocationTargetException;

    int getInt() throws IllegalAccessException, InvocationTargetException;

    boolean getBoolean() throws IllegalAccessException, InvocationTargetException;

    double getDouble() throws IllegalAccessException, InvocationTargetException;

    char getChar() throws IllegalAccessException, InvocationTargetException;

    float getFloat() throws IllegalAccessException, InvocationTargetException;

    long getLong() throws IllegalAccessException, InvocationTargetException;

    Object get() throws IllegalAccessException, InvocationTargetException;

    void set(Object value) throws IllegalAccessException, InvocationTargetException;
}
