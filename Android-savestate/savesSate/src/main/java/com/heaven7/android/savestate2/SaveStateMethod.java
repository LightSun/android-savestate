package com.heaven7.android.savestate2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * some times java use class to wrap a c++ class. wee need save and restore it.
 * Created by heaven7 on 2018/12/12 0012.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SaveStateMethod {

    /**
     * the key used to save data into bundle or restore data from bundle.
     * <p>Note: for save state method . must in pairs. such as get and set. like setName and getName. </p>
     * @return the field name
     */
    String value() default "";

    /**
     * the flag indicate the value type
     *  @return the type flag of save instance state.
     */
    @BundleSupportTypeFlag int flag() default BundleSupportType.STRING;

    /**
     * the method apply type . save(get) or restore(set). default is {@linkplain SaveStateMethodType#SAVE}.
     * @return the method apply type
     */
    @SaveStateMethodTypeFlag int applyType() default SaveStateMethodType.SAVE;

}
