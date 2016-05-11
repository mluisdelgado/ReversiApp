package com.student.marcos.reversi.activities;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import com.student.marcos.reversi.R;
import com.student.marcos.reversi.fragments.MenuFragment;

public class Menu extends Activity implements MenuFragment.OnButtonSelectedListener {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(R.id.menu_fragment) == null) {
            MenuFragment menuFragment = new MenuFragment();
            fm.beginTransaction().add(R.id.menu_fragment, menuFragment).commit();
        }
    }

    @Override
    public void onButtonSelected(int i) {
        switch(i){
            case 1:
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
                break;
            case 6:
                Intent intent6 = new Intent(getApplicationContext(), Preferences.class);
                startActivity(intent6);
                break;
        }
    }
}