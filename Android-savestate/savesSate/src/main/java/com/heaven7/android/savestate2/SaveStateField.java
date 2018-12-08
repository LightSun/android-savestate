package com.heaven7.android.savestate2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * useful to quick save state and restore it from bundle
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SaveStateField {
    /**
     * the key used to save data into bundle or restore data from bundle.
     * @return the field name
     */
    String value() default "";

    /**
     * the flag indicate the value type
     *  @return the type flag of save instance state.
     */
    @BundleSupportTypeFlag int flag() default BundleSupportType.STRING;
}
