package com.heaven7.android.savestate2;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by heaven7 on 2018/12/7 0007.
 */
/*public*/ class Util {

    public static void init(Object holder, SaveInfoFactory factory, List<SaveInfoDelegate> outInfos) {
        List<Method> getMethods = new ArrayList<>();
        List<Method> setMethods = new ArrayList<>();
        Class<?> clazz = holder.getClass();
        do {
            getMatchedFieldInfos(holder, clazz, factory, outInfos);
            getMatchedMethodInfos(clazz, getMethods, setMethods);
            clazz = clazz.getSuperclass();
            if(clazz == ViewGroup.class || clazz == View.class || clazz == Object.class || clazz == null){
                break;
            }
        } while (true);
        //make method pairs.
        if(getMethods.size() > setMethods.size()){
            makePairMethods(getMethods, setMethods, holder, true, factory, outInfos);
        }else {
            makePairMethods(setMethods, getMethods, holder, false, factory, outInfos);
        }
    }

    public static void onSaveInstanceState(List<SaveInfoDelegate> inInfos, Bundle outState) {
        SaveInfoDelegate info;
        for (int i = 0, size = inInfos.size(); i < size; i++) {
            info = inInfos.get(i);
            Object owner = info.getOwner();
            if(owner != null){
                SaveStateUtil.doSaveState(outState, info);
            }
        }
    }
    public static void onRestoreInstanceState(List<SaveInfoDelegate> inInfos, Bundle savedInstanceState) {
        if (savedInstanceState == null)
            return;
        SaveInfoDelegate info;
        for (int i = 0, size = inInfos.size(); i < size; i++) {
            info = inInfos.get(i);
            Object owner = info.getOwner();
            if(owner != null) {
                SaveStateUtil.doRestoreState(savedInstanceState, info);
            }
        }
    }

    private static void getMatchedFieldInfos(Object holder, Class<?> cls, SaveInfoFactory factory, List<SaveInfoDelegate> out){
        Field[] fields = cls.getDeclaredFields();
        if(fields != null){
            for (Field f : fields){
                f.setAccessible(true);
                SaveStateField ssf = f.getAnnotation(SaveStateField.class);
                if(ssf != null){
                    out.add(factory.createFieldInfo(f, ssf, holder));
                }
            }
        }
    }

    private static void getMatchedMethodInfos(Class<?> cls, List<Method> getMethods, List<Method> setMethods){
        Method[] methods = cls.getDeclaredMethods();
        if(methods != null){
            for (Method m : methods){
                m.setAccessible(true);
                SaveStateMethod ssf = m.getAnnotation(SaveStateMethod.class);
                if(ssf != null){
                    if(ssf.applyType() == SaveStateMethodType.GET){
                        getMethods.add(m);
                    }else{
                        setMethods.add(m);
                    }
                }
            }
        }
    }
    private static void makePairMethods(List<Method> src, List<Method> notSureMethods, Object holder,
                                        boolean srcIsGet, SaveInfoFactory factory, List<SaveInfoDelegate> outInfos){
        for(Iterator<Method> it = src.iterator() ; it.hasNext() ; ){
            Method m = it.next();
            //
            String property = getPropertyFromMethod(m);
            Method another = null;
            //find same property method of it.
            for(Iterator<Method> it2 = notSureMethods.iterator() ; it2.hasNext();){
                Method m2 = it2.next();
                String temp = getPropertyFromMethod(m2);
                if(temp.equals(property)){
                    another = m2;
                    it2.remove();
                    break;
                }
            }
            if(another == null){
                throw new IllegalStateException("[ Save-State Init ]: can't find get/set method for property = " + property
                        +" ,method name = " + m.getName() + " ,declare class is " + m.getDeclaringClass().getName());
            }
            //make pair success
            if(srcIsGet){
                outInfos.add(factory.createMethodInfo(m, another, holder));
            }else{
                outInfos.add(factory.createMethodInfo(another, m, holder));
            }
            it.remove();
        }
    }
    private static String getPropertyFromMethod(Method method){
        SaveStateMethod ssm = method.getAnnotation(SaveStateMethod.class);
        String value = ssm.value();
        if(TextUtils.isEmpty(value)){
            String name = method.getName();
            if(name.startsWith("get") || name.startsWith("set")){
                value = name.substring(3);
            }else{
                value = name;
            }
        }
        return value;
    }

    public static JsonVersion getJsonVersion(Object holder) {
        JsonVersion version;
        Class<?> clazz = holder.getClass();
        do {
            version = clazz.getAnnotation(JsonVersion.class);
            if(version != null){
                break;
            }
            clazz = clazz.getSuperclass();
            if(clazz == ViewGroup.class || clazz == View.class || clazz == Object.class || clazz == null){
                break;
            }
        } while (true);
        return version;
    }
}
