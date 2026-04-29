package com.yousef.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferences {

    private static final String PREF_NAME = "MyUserPrefs";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_UPDATES = "ReceiveUpdates";
    private static final String KEY_LOGGED_IN = "isLoggedIn";

    private SharedPreferences sharedPreferences;

    public UserPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }



    // 🔹 Register (save + login)
    public void register(String name, String email, boolean receiveUpdates) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putBoolean(KEY_UPDATES, receiveUpdates);
        editor.putBoolean(KEY_LOGGED_IN, true);
        editor.apply();
    }

    public boolean login(String name, String email) {
        String savedName = sharedPreferences.getString(KEY_NAME, null);
        String savedEmail = sharedPreferences.getString(KEY_EMAIL, null);

        if (savedName != null && savedEmail != null &&
                savedName.equals(name) && savedEmail.equals(email)) {

            sharedPreferences.edit().putBoolean(KEY_LOGGED_IN, true).apply();
            return true;
        }

        return false;
    }

    // 🔹 Logout
    public void logout() {
        sharedPreferences.edit().putBoolean(KEY_LOGGED_IN, false).apply();
    }

    // 🔹 Check if logged in
    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_LOGGED_IN, false);
    }

    // 🔹 Check if user exists
    public boolean isUserRegistered() {
        String email = sharedPreferences.getString(KEY_EMAIL, null);
        return email != null && !email.isEmpty();
    }

    // 🔹 Get data (your original methods - kept)
    public String getName() {
        return sharedPreferences.getString(KEY_NAME, "");
    }

    public String getEmail() {
        return sharedPreferences.getString(KEY_EMAIL, "");
    }

    public boolean isReceiveUpdates() {
        return sharedPreferences.getBoolean(KEY_UPDATES, false);
    }

    // 🔹 Clear all data
    public void clear() {
        sharedPreferences.edit().clear().apply();
    }
}