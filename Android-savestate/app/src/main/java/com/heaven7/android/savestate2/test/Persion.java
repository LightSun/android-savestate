package com.heaven7.android.savestate2.test;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by heaven7 on 2018/12/13 0013.
 */
public class Persion implements Parcelable {

    private String name;
    private int age;

    public Persion() {
    }

    public Persion(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(age);
        dest.writeString(name);
    }

    public void readFrom(Parcel src){
        age = src.readInt();
        name = src.readString();
    }

    @Override
    public String toString() {
        return "Persion{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static final Creator<Persion> CREATOR = new Creator<Persion>() {
        @Override
        public Persion createFromParcel(Parcel source) {
            Persion p = new Persion();
            p.readFrom(source);
            return p;
        }
        @Override
        public Persion[] newArray(int size) {
            return new Persion[size];
        }
    };
}
