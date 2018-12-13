package com.heaven7.android.savestate2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * useful to quick save state and restore it from bundle.
 * some times we use a java class to wrap a c++ class. so may used method to save/restore info.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface SaveStateField {
    /**
     * the key used to save data into bundle or restore data from bundle. empty means use field name.
     * @return the field name
     */
    String value() default "";

    /**
     * the flag indicate the value type
     *  @return the type flag of save instance state.
     */
    @SupportValueTypes int flag() default BundleSupportType.STRING;
}
