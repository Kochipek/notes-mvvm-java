package com.kochipek.noteapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {
    private static final String PREF_NAME = "NoteAppPrefs";
    private static final String TUTORIAL_COMPLETED_KEY = "tutorial_completed";

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static boolean isTutorialCompleted(Context context) {
        return getSharedPreferences(context).getBoolean(TUTORIAL_COMPLETED_KEY, false);
    }

    public static void setTutorialCompleted(Context context, boolean completed) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(TUTORIAL_COMPLETED_KEY, completed);
        editor.apply();
    }
}