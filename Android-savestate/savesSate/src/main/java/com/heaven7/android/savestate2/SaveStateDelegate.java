package com.heaven7.android.savestate2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * save state delegate
 * Created by heaven7 on 2018/12/7 0007.
 */
public interface SaveStateDelegate {

    /**
     * called on save instance state
     * @param outState the out state
     */
    void onSaveInstanceState(@NonNull Bundle outState);

    /**
     * called on restore instance state
     * @param savedInstanceState the state
     */
    void onRestoreInstanceState(@Nullable Bundle savedInstanceState);

}
