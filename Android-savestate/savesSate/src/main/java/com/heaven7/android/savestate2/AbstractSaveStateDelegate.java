package com.heaven7.android.savestate2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heaven7 on 2018/12/7 0007.
 */
/*public*/ abstract class AbstractSaveStateDelegate implements SaveStateDelegate{

    private final List<SaveInfoDelegate> mInfos;

    public AbstractSaveStateDelegate() {
        this.mInfos = new ArrayList<>();
    }

    protected List<SaveInfoDelegate> getSaveInfos(){
        return mInfos;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Util.onSaveInstanceState(mInfos, outState);
    }
    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        Util.onRestoreInstanceState(mInfos, savedInstanceState);
    }
}
