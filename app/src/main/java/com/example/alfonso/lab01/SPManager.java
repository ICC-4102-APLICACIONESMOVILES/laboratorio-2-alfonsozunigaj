package com.example.alfonso.lab01;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

/**
 * Created by Alfonso on 20-03-2018.
 */

public class SPManager {

    public String getEmail(Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        String current_email = sharedPref.getString("email",null);

        return current_email;
    }

    public String getPassword(Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        String current_password = sharedPref.getString("password",null);

        return current_password;
    }

    public void removeData(Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("email");
        editor.remove("password");
        editor.commit();

        return;
    }

    public void addData(Activity activity, String email, String password) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.commit();

        return;
    }
}
