package com.heaven7.android.savestate2;

import android.os.Bundle;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by heaven7 on 2018/12/7 0007.
 */
/*public*/ class Util {

    public static void init(Object holder, List<SaveFieldInfo> outInfos) {
        Field[] fields = holder.getClass().getDeclaredFields();
        if (fields == null || fields.length == 0)
            return;
        SaveStateField sf;
        for (int i = 0, size = fields.length; i < size; i++) {
            Field f = fields[i];
            f.setAccessible(true);
            sf = f.getAnnotation(SaveStateField.class);
            if (sf == null)
                continue;
            outInfos.add(new SaveFieldInfo(f, sf, SaveStateUtil.getFlag(f, sf.flag()), holder));
        }
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


}
