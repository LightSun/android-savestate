package com.heaven7.android.savestate2;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by heaven7 on 2018/12/13 0013.
 * @since 1.0.6
 */
public class JsonStateWrapper {

    private final JsonSaveStateDelegate mDelegate;

    public JsonStateWrapper(@NonNull JsonSaveStateDelegate mDelegate) {
        this.mDelegate = mDelegate;
    }

    public JsonSaveStateDelegate getJsonSaveStateDelegate() {
        return mDelegate;
    }

    public static JsonStateWrapper of(Object...holders){
        return new JsonStateWrapper(new JsonSaveStateDelegate(holders));
    }

    public String onSaveInstanceState(){
        JsonObject jo = new JsonObject();
        mDelegate.onSaveInstanceState(jo);
        return jo.toString();
    }

    public void onRestoreInstanceState(String json){
        if(!TextUtils.isEmpty(json)){
            mDelegate.onRestoreInstanceState(new JsonParser().parse(json).getAsJsonObject());
        }
    }

    /**
     * called on save json state to file.
     * @param out the out file
     * @param deleteWhenExist true to delete if the out file is already exists.
     */
    public void onSaveInstanceState(File out, boolean deleteWhenExist){
        if(!out.getParentFile().exists()){
            out.getParentFile().mkdirs();
        }
        if(deleteWhenExist && out.exists()){
            out.delete();
        }
        //save
        JsonObject jo = new JsonObject();
        mDelegate.onSaveInstanceState(jo);
        //write to file
        FileWriter writer = null;
        try {
            writer = new FileWriter(out);
            writer.write(jo.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * called on restore instance state(json) to file
     * @param in the file in.
     * @return true if restore success. false if file not exist or empty content.
     */
    public boolean onRestoreInstanceState(File in){
        if(in.isDirectory() || !in.exists()){
            return false;
        }
        final String json;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(in));
            StringBuilder sb = new StringBuilder();
            String line;
           while ((line = reader.readLine()) != null){
               sb.append(line);
           }
            json = sb.toString();
        }catch (IOException e){
            throw new RuntimeException(e);
        }finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
        if(!TextUtils.isEmpty(json)){
            mDelegate.onRestoreInstanceState(new JsonParser().parse(json).getAsJsonObject());
            return true;
        }
        return false;
    }
}
