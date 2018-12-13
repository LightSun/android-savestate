package com.heaven7.android.savestate2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heaven7 on 2018/12/13 0013.
 * @param <S> the state to save and restore
 * @since 1.0.6
 */
/*public*/ abstract class BaseSaveStateDelegate<S> implements SaveStateDelegate<S> {

    private final List<SaveInfoDelegate> mInfos = new ArrayList<>();

    public BaseSaveStateDelegate(Object[] holders) {
        for (Object holder : holders){
            addHolder(holder);
        }
    }
    protected List<SaveInfoDelegate> getSaveInfos(){
        return mInfos;
    }

    public abstract BaseSaveStateDelegate<S> addHolder(Object holder);

}
