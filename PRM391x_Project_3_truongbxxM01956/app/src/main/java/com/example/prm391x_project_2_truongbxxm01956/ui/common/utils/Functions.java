package com.example.prm391x_project_2_truongbxxm01956.ui.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Functions {
    public static String favorite_keys = "favorite_keys";
    public static String string_keys = "string_keys";

    public  static void setSharedPreferencesByBooleanValue(Activity activity, String favorite_key, boolean value ){
        SharedPreferences sharedPref = activity.getSharedPreferences(favorite_keys, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(favorite_key, value);
        editor.apply();
    }
    public static boolean getSharedPreferencesByBooleanValue(Activity activity, String favorite_key){
        SharedPreferences sharedPref = activity.getSharedPreferences(favorite_keys, Context.MODE_PRIVATE);
        boolean favorite_value = sharedPref.getBoolean(favorite_key, false);
        return favorite_value;
    }
    public static void setSharedPreferencesByStringValue(Activity activity, String string_key, String value ){
        SharedPreferences sharedPref = activity.getSharedPreferences(string_keys, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(string_key, value);
        editor.apply();
    }
    public static String getSharedPreferencesByStringValue(Activity activity, String string_key){
        SharedPreferences sharedPref = activity.getSharedPreferences(string_keys, Context.MODE_PRIVATE);
        String value = sharedPref.getString(string_key, "");
        return value;
    }
    public static void ClearStringValue(Activity activity, String memoryName){
        SharedPreferences sharedPref = activity.getSharedPreferences(memoryName, Context.MODE_PRIVATE);
        sharedPref.edit().clear().apply();
    }
}
