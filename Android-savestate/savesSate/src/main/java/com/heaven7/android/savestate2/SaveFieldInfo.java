package com.heaven7.android.savestate2;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/*public*/ class SaveFieldInfo {
    Field field;
    SaveStateField saveField;
    /** flag type */
    int type;

    private final WeakReference<Object> ownerRef;

    public SaveFieldInfo(Field f, SaveStateField saveField, int type, Object owner) {
        this.field = f;
        this.saveField = saveField;
        this.type = type;
        this.ownerRef = new WeakReference<>(owner);
    }
    public Object getOwner(){
        return ownerRef.get();
    }
}
