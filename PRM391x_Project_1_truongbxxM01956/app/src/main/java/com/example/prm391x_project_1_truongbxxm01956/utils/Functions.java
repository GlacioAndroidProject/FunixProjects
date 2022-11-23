package com.example.prm391x_project_1_truongbxxm01956.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Functions {
    public  static void setSharedPreferences(Activity activity, String favorite_key, boolean value ){
        SharedPreferences sharedPref = activity.getSharedPreferences("favorite_keys", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(favorite_key, value);
        editor.apply();
    }
    public static boolean getsetSharedPreferences(Activity activity, String favorite_key){
        SharedPreferences sharedPref = activity.getSharedPreferences("favorite_keys", Context.MODE_PRIVATE);
        boolean favorite_value = sharedPref.getBoolean(favorite_key, false);
        return favorite_value;
    }
}
