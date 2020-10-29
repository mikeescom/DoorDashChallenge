package com.mikeescom.doordashchallenge.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref
{
    private static SharedPreferences mSharedPref;
    public static final String DB_UPDATE_TIME = "DB_UPDATE_TIME";

    private SharedPref() {

    }

    public static void init(Context context) {
        if(mSharedPref == null) {
            mSharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
        }
    }

    public static long read(String key, long defValue) {
        return mSharedPref.getLong(key, defValue);
    }

    public static void write(String key, long value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putLong(key, value);
        prefsEditor.apply();
    }
}
