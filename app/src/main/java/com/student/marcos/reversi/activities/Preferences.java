package com.student.marcos.reversi.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.student.marcos.reversi.R;
import com.student.marcos.reversi.fragments.PreferencesFragment;

public class Preferences extends Activity {
    public final static String PLAY_MUSIC_KEY = "music";
    public final static boolean PLAY_MUSIC_DEFAULT = true;
    public final static String PLAYER_NAME_KEY = "playername";
    public final static String PLAYER_NAME_DEFAULT = "unspecified";
    public final static String FIGURE_NAME_KEY = "figurename";
    public final static String FIGURE_NAME_DEFAULT = "circulo";
    public final static String FIGURE_CODE_KEY = "figurecode";
    public final static String FIGURE_CODE_DEFAULT = "0";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences_fragment);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        PreferencesFragment fragment = new PreferencesFragment();
        fragmentTransaction.replace(android.R.id.content, fragment);
        fragmentTransaction.commit();
    }
    public static String getFigureName(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(FIGURE_NAME_KEY, FIGURE_NAME_DEFAULT);
    }
    public static void setFigureName(Context context, String figurename) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Preferences.FIGURE_NAME_KEY, figurename);
        editor.commit();
    }
    public static String getFigureCode(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(FIGURE_CODE_KEY, FIGURE_CODE_DEFAULT);
    }
    public static void setFigureCode(Context context, String figurecode) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Preferences.FIGURE_CODE_KEY, figurecode);
        editor.commit();
    }
}