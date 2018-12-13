package com.heaven7.android.savestate2;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.SparseArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/*public*/ class SaveStateUtil {

    /**
     * default is null / 0
     */
    public static void doRestoreState(Bundle b, SaveInfoDelegate info) {
        String key = info.getKey();
        try {  //default 0
            //no mapping return
            if (b.get(key) == null) {
                return;
            }
            switch (info.getType()) {
                //primetive
                case BundleSupportType.BYTE:
                    info.set(b.getByte(key));
                    break;
                case BundleSupportType.BYTE_ARRAY:
                    info.set(b.getByteArray(key));
                    break;
                case BundleSupportType.SHORT:
                    info.set(b.getShort(key));
                    break;
                case BundleSupportType.SHORT_ARRAY:
                    info.set(b.getShortArray(key));
                    break;
                case BundleSupportType.INT:
                    info.set(b.getInt(key));
                    break;
                case BundleSupportType.INT_ARRAY:
                    info.set(b.getIntArray(key));
                    break;
                case BundleSupportType.BOOLEAN:
                    info.set(b.getBoolean(key));
                    break;
                case BundleSupportType.BOOLEAN_ARRAY:
                    info.set(b.getBooleanArray(key));
                    break;
                case BundleSupportType.DOUBLE:
                    info.set(b.getDouble(key));
                    break;
                case BundleSupportType.DOUBLE_ARRAY:
                    info.set(b.getDoubleArray(key));
                    break;
                case BundleSupportType.CHAR:
                    info.set(b.getChar(key));
                    break;
                case BundleSupportType.CHAR_ARRAY:
                    info.set(b.getCharArray(key));
                    break;
                case BundleSupportType.FLOAT:
                    info.set(b.getFloat(key));
                    break;
                case BundleSupportType.FLOAT_ARRAY:
                    info.set(b.getFloatArray(key));
                    break;
                case BundleSupportType.LONG:
                    info.set(b.getLong(key));
                    break;
                case BundleSupportType.LONG_ARRAY:
                    info.set(b.getLongArray(key));
                    break;
                //==================================

                case BundleSupportType.STRING:
                    info.set(b.getString(key));
                    break;
                case BundleSupportType.STRING_ARRAY:
                    info.set(b.getStringArray(key));
                    break;
                case BundleSupportType.CHAR_SEQUENCE:
                    info.set(b.getCharSequence(key));
                    break;
                case BundleSupportType.CHAR_SEQUENCE_ARRAY:
                    info.set(b.getCharSequenceArray(key));
                    break;
                case BundleSupportType.PARCELABLE:
                    info.set(b.getParcelable(key));
                    break;
                case BundleSupportType.PARCELABLE_ARRAY:
                    info.set(b.getParcelableArray(key));
                    break;
                //================================
                     /*  BundleSupportType.IBINDER,
                            BundleSupportType.BUNDLE,
                            BundleSupportType.SERIALIZABLE,
                            BundleSupportType.SPARSE_PARCELABLE_ARRAY,*/
                case BundleSupportType.IBINDER:
                    //api-18
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2)
                        info.set(b.getBinder(key));
                    break;
                case BundleSupportType.BUNDLE:
                    info.set(b.getBundle(key));
                    break;
                case BundleSupportType.SERIALIZABLE:
                    info.set(b.getSerializable(key));
                    break;
                case BundleSupportType.SPARSE_PARCELABLE_ARRAY:
                    info.set(b.getSparseParcelableArray(key));
                    break;
                // ----------------------------------------------
                 /*   BundleSupportType.INTEGER_ARRAY_lIST,
                            BundleSupportType.STRING_ARRAY_LIST,
                            BundleSupportType.PARCELABLE_ARRAY_LIST,
                            BundleSupportType.PARCELABLE_LIST,
                            BundleSupportType.CHAR_SEQUENCE_ARRAY_LIST,*/
                case BundleSupportType.INTEGER_ARRAY_lIST:
                    info.set(b.getIntegerArrayList(key));
                    break;
                case BundleSupportType.STRING_ARRAY_LIST:
                    info.set(b.getStringArrayList(key));
                    break;
                case BundleSupportType.PARCELABLE_ARRAY_LIST:
                    info.set(b.getParcelableArrayList(key));
                    break;
                case BundleSupportType.PARCELABLE_LIST:
                    info.set(b.getParcelableArrayList(key));
                    break;
                case BundleSupportType.CHAR_SEQUENCE_ARRAY_LIST:
                    info.set(b.getCharSequenceArrayList(key));
                    break;

            }
        } catch (Exception e) {
            throw new RuntimeException("Error to restore instance state ---> key = " + key, e);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static void doSaveState(Bundle outState, SaveInfoDelegate info) {
        String key = info.getKey();
        try {
            if (info.get() == null) {
                return;
            }
            switch (info.getType()) {
                //primetive
                case BundleSupportType.BYTE:
                    outState.putByte(key, info.getByte());
                    break;
                case BundleSupportType.BYTE_ARRAY:
                    outState.putByteArray(key, (byte[]) info.get());
                    break;
                case BundleSupportType.SHORT:
                    outState.putShort(key, info.getShort());
                    break;
                case BundleSupportType.SHORT_ARRAY:
                    outState.putShortArray(key, (short[]) info.get());
                    break;
                case BundleSupportType.INT:
                    outState.putInt(key, info.getInt());
                    break;
                case BundleSupportType.INT_ARRAY:
                    outState.putIntArray(key, (int[]) info.get());
                    break;
                case BundleSupportType.BOOLEAN:
                    outState.putBoolean(key, info.getBoolean());
                    break;
                case BundleSupportType.BOOLEAN_ARRAY:
                    outState.putBooleanArray(key, (boolean[]) info.get());
                    break;
                case BundleSupportType.DOUBLE:
                    outState.putDouble(key, info.getDouble());
                    break;
                case BundleSupportType.DOUBLE_ARRAY:
                    outState.putDoubleArray(key, (double[]) info.get());
                    break;
                case BundleSupportType.CHAR:
                    outState.putChar(key, info.getChar());
                    break;
                case BundleSupportType.CHAR_ARRAY:
                    outState.putCharArray(key, (char[]) info.get());
                    break;
                case BundleSupportType.FLOAT:
                    outState.putFloat(key, info.getFloat());
                    break;
                case BundleSupportType.FLOAT_ARRAY:
                    outState.putFloatArray(key, (float[]) info.get());
                    break;
                case BundleSupportType.LONG:
                    outState.putLong(key, info.getLong());
                    break;
                case BundleSupportType.LONG_ARRAY:
                    outState.putLongArray(key, (long[]) info.get());
                    break;
                //==================================

                case BundleSupportType.STRING:
                    outState.putString(key, (String) info.get());
                    break;
                case BundleSupportType.STRING_ARRAY:
                    outState.putStringArray(key, (String[]) info.get());
                    break;
                case BundleSupportType.CHAR_SEQUENCE:
                    outState.putCharSequence(key, (CharSequence) info.get());
                    break;
                case BundleSupportType.CHAR_SEQUENCE_ARRAY:
                    outState.putCharSequenceArray(key, (CharSequence[]) info.get());
                    break;
                case BundleSupportType.PARCELABLE:
                    outState.putParcelable(key, (Parcelable) info.get());
                    break;
                case BundleSupportType.PARCELABLE_ARRAY:
                    outState.putParcelableArray(key, (Parcelable[]) info.get());
                    break;
                //================================
                     /*  BundleSupportType.IBINDER,
                            BundleSupportType.BUNDLE,
                            BundleSupportType.SERIALIZABLE,
                            BundleSupportType.SPARSE_PARCELABLE_ARRAY,*/
                case BundleSupportType.IBINDER:
                    //api-18
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        outState.putBinder(key, (IBinder) info.get());
                    }
                    break;
                case BundleSupportType.BUNDLE:
                    outState.putBundle(key, (Bundle) info.get());
                    break;
                case BundleSupportType.SERIALIZABLE:
                    outState.putSerializable(key, (Serializable) info.get());
                    break;
                case BundleSupportType.SPARSE_PARCELABLE_ARRAY:
                    outState.putSparseParcelableArray(key,
                            (SparseArray<? extends Parcelable>) info.get());
                    break;
                // ----------------------------------------------
                 /*   BundleSupportType.INTEGER_ARRAY_lIST,
                            BundleSupportType.STRING_ARRAY_LIST,
                            BundleSupportType.PARCELABLE_ARRAY_LIST,
                            BundleSupportType.PARCELABLE_LIST,
                            BundleSupportType.CHAR_SEQUENCE_ARRAY_LIST,*/
                case BundleSupportType.INTEGER_ARRAY_lIST:
                    outState.putIntegerArrayList(key, (ArrayList<Integer>) info.get());
                    break;
                case BundleSupportType.STRING_ARRAY_LIST:
                    outState.putStringArrayList(key, (ArrayList<String>) info.get());
                    break;
                case BundleSupportType.PARCELABLE_ARRAY_LIST:
                    outState.putParcelableArrayList(key,
                            (ArrayList<? extends Parcelable>) info.get());
                    break;
                case BundleSupportType.PARCELABLE_LIST:
                    outState.putParcelableArrayList(key, new ArrayList<Parcelable>(
                            (Collection<? extends Parcelable>) info.get()));
                    break;
                case BundleSupportType.CHAR_SEQUENCE_ARRAY_LIST:
                    outState.putCharSequenceArrayList(key, (ArrayList<CharSequence>) info.get());
                    break;

            }
        } catch (Exception e) {
            throw new RuntimeException("Error to save instance state: key = " + key, e);
        }
    }

    @SupportValueTypes
    public static int getFlag(Class<?> clazz, @SupportValueTypes int flag, String prefix) {
        if (Byte.TYPE.isAssignableFrom(clazz)) {
            return BundleSupportType.BYTE;
        } else if (Short.TYPE.isAssignableFrom(clazz)) {
            return BundleSupportType.SHORT;
        } else if (Integer.TYPE.isAssignableFrom(clazz)) {
            return BundleSupportType.INT;
        } else if (Boolean.TYPE.isAssignableFrom(clazz)) {
            return BundleSupportType.BOOLEAN;
        } else if (Double.TYPE.isAssignableFrom(clazz)) {
            return BundleSupportType.DOUBLE;
        } else if (Long.TYPE.isAssignableFrom(clazz)) {
            return BundleSupportType.LONG;
        } else if (Character.TYPE.isAssignableFrom(clazz)) {
            return BundleSupportType.CHAR;
        } else if (Float.TYPE.isAssignableFrom(clazz)) {
            return BundleSupportType.FLOAT;
        } else if (String.class.isAssignableFrom(clazz)) {
            return BundleSupportType.STRING;
        } else if (CharSequence.class.isAssignableFrom(clazz)) {
            return BundleSupportType.CHAR_SEQUENCE;
        } else if (IBinder.class.isAssignableFrom(clazz)) {
            return BundleSupportType.IBINDER;
        } else if (Bundle.class.isAssignableFrom(clazz)) {
            return BundleSupportType.BUNDLE;
        } else if (Parcelable.class.isAssignableFrom(clazz)) {
            return BundleSupportType.PARCELABLE;
        } else if (Serializable.class.isAssignableFrom(clazz)) {
            return BundleSupportType.SERIALIZABLE;
        }
        // array
        else if (clazz.isArray()) {
            Class<?> clazz2 = clazz.getComponentType();
            if (Byte.TYPE.isAssignableFrom(clazz2)) {
                return BundleSupportType.BYTE_ARRAY;
            } else if (Short.TYPE.isAssignableFrom(clazz2)) {
                return BundleSupportType.SHORT_ARRAY;
            } else if (Integer.TYPE.isAssignableFrom(clazz2)) {
                return BundleSupportType.INT_ARRAY;
            } else if (Boolean.TYPE.isAssignableFrom(clazz2)) {
                return BundleSupportType.BOOLEAN_ARRAY;
            } else if (Double.TYPE.isAssignableFrom(clazz2)) {
                return BundleSupportType.DOUBLE_ARRAY;
            } else if (Long.TYPE.isAssignableFrom(clazz2)) {
                return BundleSupportType.LONG_ARRAY;
            } else if (Character.TYPE.isAssignableFrom(clazz2)) {
                return BundleSupportType.CHAR_ARRAY;
            } else if (Float.TYPE.isAssignableFrom(clazz2)) {
                return BundleSupportType.FLOAT_ARRAY;
            } else if (String.class.isAssignableFrom(clazz2)) {
                return BundleSupportType.STRING_ARRAY;
            } else if (CharSequence.class.isAssignableFrom(clazz2)) {
                return BundleSupportType.CHAR_SEQUENCE_ARRAY;
            } else if (Parcelable.class.isAssignableFrom(clazz2)) {
                return BundleSupportType.PARCELABLE_ARRAY;
            }
              /*  else{
                    Class<?>[] interfaces = clazz2.getInterfaces();
                    if(contains(interfaces, CharSequence.class)){
                        return BundleSupportType.CHAR_SEQUENCE_ARRAY;
                    }else if(contains(interfaces, Parcelable.class)){
                        return BundleSupportType.PARCELABLE_ARRAY;
                    }
                }*/
        }/*else {
                Class<?>[] interfaces = clazz.getInterfaces();
                if(contains(interfaces, Parcelable.class)){
                    return BundleSupportType.PARCELABLE;
                }else if(contains(interfaces, CharSequence.class)){
                    return BundleSupportType.CHAR_SEQUENCE;
                }else if(contains(interfaces, IBinder.class)){
                    return BundleSupportType.IBINDER;
                }else if(contains(interfaces, Serializable.class)){
                    return BundleSupportType.SERIALIZABLE;
                }
            }*/
        //list ?  must assign flag or throw RuntimeException
        switch (flag) {
            case BundleSupportType.INTEGER_ARRAY_lIST:
            case BundleSupportType.STRING_ARRAY_LIST:
            case BundleSupportType.PARCELABLE_ARRAY_LIST:
            case BundleSupportType.PARCELABLE_LIST:
            case BundleSupportType.CHAR_SEQUENCE_ARRAY_LIST:
                // SparseArray<? extends Parcelable>
            case BundleSupportType.SPARSE_PARCELABLE_ARRAY:
                return flag;
            default:
                String extra = "flag = " + flag + " ," + prefix;
                System.err.println(extra);
                throw new RuntimeException("flag only can be the value in interface BundleSupportType." + extra);
        }
    }

      /*  private static boolean contains(Class<?>[] interfaces, Class<?> clzz) {
            if(interfaces==null || interfaces.length==0)
                return false;
            for(Class<?> clazz : interfaces){
                if(clazz == clzz)
                    return true;
            }
            return false;
        }*/

}
