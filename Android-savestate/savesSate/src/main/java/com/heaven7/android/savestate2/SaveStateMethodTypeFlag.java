package com.heaven7.android.savestate2;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by heaven7 on 2018/12/12 0012.
 */
@IntDef({
        SaveStateMethodType.SAVE,
        SaveStateMethodType.RESTORE
})
@Retention(RetentionPolicy.CLASS)
public @interface SaveStateMethodTypeFlag {

}
