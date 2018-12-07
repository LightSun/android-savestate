package com.heaven7.android.savestate2;

/**
 * Created by heaven7 on 2018/12/7 0007.
 */
public class ComposeSaveStateManager extends AbstractSaveStateDelegate implements SaveStateDelegate {

    public ComposeSaveStateManager() {
        super();
    }

    public ComposeSaveStateManager addHolder(Object holder){
        Util.init(holder, getSaveFieldInfos());
        return this;
    }

}
