package com.heaven7.android.savestate2.test;

import com.heaven7.android.savestate2.SaveStateField;
import com.heaven7.android.savestate2.SaveStateMethod;
import com.heaven7.android.savestate2.SaveStateMethodType;

/**
 * Created by heaven7 on 2019/4/10.
 */
//@JsonConfig(gsonFactory = TestHolder.TestGsonFactory.class)
public class TestHolder {

    private String anchor;
    private double version;
    @SaveStateField
    private NestHolder nestHolder;

    @SaveStateMethod(value = "anchor")
    public String getAnchor() {
        return anchor;
    }
    @SaveStateMethod(value = "anchor", applyType = SaveStateMethodType.SET)
    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    @SaveStateMethod(value = "version")
    public double getVersion() {
        return version;
    }
    @SaveStateMethod(value = "version", applyType = SaveStateMethodType.SET)
    public void setVersion(double version) {
        this.version = version;
    }

    public NestHolder getNestHolder() {
        return nestHolder;
    }
    public void setNestHolder(NestHolder nestHolder) {
        this.nestHolder = nestHolder;
    }

    public void reset() {
        anchor = null;
        version = 0;
        nestHolder = null;
    }

    @Override
    public String toString() {
        return "TestHolder{" +
                "anchor='" + anchor + '\'' +
                ", version=" + version +
                '}';
    }


   /* public static class TestGsonFactory extends GsonFactory{
        @Override
        public Gson create(GsonBuilder builder) {
            return builder.excludeFieldsWithoutExposeAnnotation().create();
        }
    }*/
}
