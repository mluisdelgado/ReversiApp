package com.student.marcos.reversi.fragments;

        import android.os.Bundle;
        import android.preference.PreferenceFragment;

        import com.student.marcos.reversi.R;

public class PreferencesFragment extends PreferenceFragment{
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
