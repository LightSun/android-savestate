package com.heaven7.android.savestate2;

/**
 * constant
 */
public interface BundleSupportType extends SupportType{

    int CHAR_SEQUENCE               = 31;
    int CHAR_SEQUENCE_ARRAY         = 32;
    int CHAR_SEQUENCE_ARRAY_LIST    = 33;

    int PARCELABLE                  = 34;
    int PARCELABLE_ARRAY_LIST       = 35;
    int PARCELABLE_LIST             = 36;
    int PARCELABLE_ARRAY            = 37;

    int SERIALIZABLE                = 38;
    int SPARSE_PARCELABLE_ARRAY     = 39;

    int IBINDER = 41;     // must >= api-18
    int BUNDLE = 42;

}
