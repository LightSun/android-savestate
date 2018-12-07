package com.heaven7.android.savestate2;

/**
 * the save state helper. help we save state.
 * @author heaven7
 */
public class SimpleSaveStateManager extends AbstractSaveStateDelegate implements SaveStateDelegate{

    /**
     * @param holder may be activity or fragment
     */
    public SimpleSaveStateManager(Object holder) {
        super();
        Util.init(holder, getSaveFieldInfos());
    }
}
