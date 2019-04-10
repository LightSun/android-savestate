package com.heaven7.android.savestate2.test;

import com.heaven7.android.savestate2.SaveStateMethod;
import com.heaven7.android.savestate2.SaveStateMethodType;

/**
 * Created by heaven7 on 2019/4/10.
 */
public class NestHolder {

    private String anchor;

    @SaveStateMethod(value = "n_anchor")
    public String getAnchor() {
        return anchor;
    }
    @SaveStateMethod(value = "n_anchor", applyType = SaveStateMethodType.SET)
    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }
}
