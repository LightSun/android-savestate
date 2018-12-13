package com.heaven7.android.savestate2;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.AbsSavedState;

/**
 * @author heaven7
 * @since 1.0.5
 */
public class SaveState extends AbsSavedState {

    private final Bundle mBundle;

    protected SaveState(@Nullable Parcelable superState, @NonNull Bundle bundle) {
        super(superState);
        this.mBundle = bundle;
    }

    protected SaveState(@NonNull Parcel source, @NonNull Bundle bundle) {
        super(source);
        this.mBundle = bundle;
    }

    public Bundle getBundle() {
        return mBundle;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(mBundle, flags);
    }

    public static final Creator<SaveState> CREATOR
            = new ClassLoaderCreator<SaveState>() {
        @Override
        public SaveState createFromParcel(Parcel in) {
            return createFromParcel(in, null);
        }

        @Override
        public SaveState createFromParcel(Parcel in, ClassLoader loader) {
            Parcelable superState = in.readParcelable(loader);
            Bundle bundle = in.readParcelable(loader);
            return new SaveState(superState, bundle);
        }

        @Override
        public SaveState[] newArray(int size) {
            return new SaveState[size];
        }
    };
}