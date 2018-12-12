package com.heaven7.android.savestate2;

import java.util.List;

/**
 * Created by heaven7 on 2018/12/7 0007.
 */
public class ComposeSaveStateManager extends AbstractSaveStateDelegate implements SaveStateDelegate {

    public ComposeSaveStateManager() {
        super();
    }

    public ComposeSaveStateManager addHolder(Object holder){
        Util.init(holder, getSaveInfos());
        return this;
    }

    public ComposeSaveStateManager addHolders(Object... holders){
        List<SaveInfoDelegate> saveInfos = getSaveInfos();
        for (Object holder : holders){
            Util.init(holder, saveInfos);
        }
        return this;
    }

}
