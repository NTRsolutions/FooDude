package com.verbosetech.fooddude.Others;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sagar on 21/6/17.
 */

@SuppressLint("CommitPrefEdits")

public class PrefManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;


    // Shared pref file name
    private static final String PREF_NAME = "AndroidHive";

    // Constructor
    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setItem(String name) {
        editor.putString("item", name);
        editor.commit();
    }

    public String getItem() {
        return pref.getString("item", null);
    }

    public void setCartAmount(float amount) {
        editor.putFloat("amount", amount);
        editor.commit();
    }

    public float getCartAmount() {
        return pref.getFloat("amount", 0.0f);
    }

}
