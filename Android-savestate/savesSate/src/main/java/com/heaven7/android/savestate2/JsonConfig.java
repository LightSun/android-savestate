package com.heaven7.android.savestate2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by heaven7 on 2018/12/13 0013.
 * @since 1.0.6
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonConfig {

    /**
     * define the json version.
     * @return the json version. often is Gson
     */
    double value() default 1.0;

    /**
     * define the gson factory
     * @return the gson factory.
     */
    Class<? extends GsonFactory> gsonFactory() default GsonFactory.class;
}
