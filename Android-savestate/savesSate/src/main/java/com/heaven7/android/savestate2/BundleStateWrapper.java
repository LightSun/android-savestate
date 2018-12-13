package com.heaven7.android.savestate2;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

/**
 * Created by heaven7 on 2018/12/6 0006.
 * @since 1.0.6
 */
public class BundleStateWrapper {

    private final Bundle mBundle;
    private final BundleSaveStateDelegate mDelegate;

    public BundleStateWrapper(BundleSaveStateDelegate delegate, Bundle mBundle) {
        this.mDelegate = delegate;
        this.mBundle = mBundle;
    }
    public BundleStateWrapper(BundleSaveStateDelegate delegate){
        this(delegate, new Bundle());
    }

    public BundleSaveStateDelegate getSaveStateDelegate(){
        return mDelegate;
    }

    public static BundleStateWrapper of(Object... holders){
        if(holders.length == 0){
            throw new IllegalArgumentException();
        }
        return new BundleStateWrapper(new BundleSaveStateDelegate(holders), new Bundle());
    }

    /**
     * called on save instance state.
     * @param superState the super state.
     * @return the wrapped save state
     */
    public Parcelable onSaveInstanceState(@Nullable Parcelable superState){
        mDelegate.onSaveInstanceState(mBundle);
        return new SaveState(superState, mBundle);
    }

    /**
     * called on restore instance state.
     * @param state the parcel state which saved
     * @return the super saved state.
     */
    public Parcelable onRestoreInstanceState(Parcelable state){
        SaveState ss = (SaveState) state;
        Bundle bundle = ss.getBundle();
        mDelegate.onRestoreInstanceState(bundle);
        //mBundle.putAll(bundle);
        return ss.getSuperState();
    }
}
