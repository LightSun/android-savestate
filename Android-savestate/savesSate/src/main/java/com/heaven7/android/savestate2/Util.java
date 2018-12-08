package com.heaven7.android.savestate2;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by heaven7 on 2018/12/7 0007.
 */
/*public*/ class Util {

    public static void init(Object holder, List<SaveFieldInfo> outInfos) {
        Class<?> clazz = holder.getClass();
        do {
            getMatchedFieldInfos(holder, clazz, outInfos);
            clazz = clazz.getSuperclass();
            if(clazz == ViewGroup.class || clazz == View.class || clazz == Object.class || clazz == null){
                break;
            }
        } while (true);
    }

    public static void onSaveInstanceState(List<SaveFieldInfo> inInfos, Bundle outState) {
        SaveFieldInfo info;
        for (int i = 0, size = inInfos.size(); i < size; i++) {
            info = inInfos.get(i);
            Object owner = info.getOwner();
            if(owner != null){
                SaveStateUtil.doSaveState(outState, info, owner);
            }
        }
    }
    public static void onRestoreInstanceState(List<SaveFieldInfo> inInfos, Bundle savedInstanceState) {
        if (savedInstanceState == null)
            return;
        SaveFieldInfo info;
        for (int i = 0, size = inInfos.size(); i < size; i++) {
            info = inInfos.get(i);
            Object owner = info.getOwner();
            if(owner != null) {
                SaveStateUtil.doRestoreState(savedInstanceState, info, owner);
            }
        }
    }

    private static void getMatchedFieldInfos(Object holder, Class<?> cls, List<SaveFieldInfo> out){
        Field[] fields = cls.getDeclaredFields();
        if(fields != null){
            for (Field f : fields){
                f.setAccessible(true);
                SaveStateField ssf = f.getAnnotation(SaveStateField.class);
                if(ssf != null){
                    out.add(new SaveFieldInfo(f, ssf, SaveStateUtil.getFlag(f, ssf.flag()), holder));
                }
            }
        }
    }
}
