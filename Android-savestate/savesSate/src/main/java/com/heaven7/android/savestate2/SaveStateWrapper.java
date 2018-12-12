package com.heaven7.android.savestate2;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

/**
 * Created by heaven7 on 2018/12/6 0006.
 */
public class SaveStateWrapper {

    private final Bundle mBundle;
    private final SaveStateDelegate mDelegate;

    public SaveStateWrapper(SaveStateDelegate delegate, Bundle mBundle) {
        this.mDelegate = delegate;
        this.mBundle = mBundle;
    }
    public SaveStateWrapper(SaveStateDelegate delegate){
        this(delegate, new Bundle());
    }

    public static SaveStateWrapper of(Object... holders){
        if(holders.length == 0){
            throw new IllegalArgumentException();
        }
        final SaveStateDelegate delegate;
        if(holders.length == 1){
            delegate = new SimpleSaveStateManager(holders[0]);
        }else{
            delegate = new ComposeSaveStateManager().addHolders(holders);
        }
        return new SaveStateWrapper(delegate, new Bundle());
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
