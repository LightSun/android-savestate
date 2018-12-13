package com.heaven7.android.savestate2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by heaven7 on 2018/12/7 0007.
 * @since 1.0.6
 */
public class BundleSaveStateDelegate extends BaseSaveStateDelegate<Bundle> implements SaveStateDelegate<Bundle>{

    public BundleSaveStateDelegate(Object... holders) {
        super(holders);
    }

    public BundleSaveStateDelegate addHolder(Object holder){
        Util.init(holder, SaveInfoFactory.DEFAULT , getSaveInfos());
        return this;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Util.onSaveInstanceState(getSaveInfos(), outState);
    }
    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        Util.onRestoreInstanceState(getSaveInfos(), savedInstanceState);
    }
}
