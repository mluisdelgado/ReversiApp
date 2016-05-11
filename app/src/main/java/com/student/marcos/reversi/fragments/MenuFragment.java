package com.student.marcos.reversi.fragments;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.student.marcos.reversi.R;
import com.student.marcos.reversi.activities.MainActivity;

public class MenuFragment extends Fragment {
    private OnButtonSelectedListener listener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState){
        View view = inflater.inflate(R.layout.menu_fragment, container, false);
        Button button1 = (Button) view.findViewById(R.id.menuButton1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.onButtonSelected(1);
                //startActivity(new Intent(Menu.this, MainActivity.class));
            }
        });
        Button button5 = (Button) view.findViewById(R.id.menuButton5);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.onButtonSelected(5);
                //startActivity(new Intent(Menu.this, MainActivity.class));
            }
        });
        Button button2 = (Button) view.findViewById(R.id.menuButton2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.onButtonSelected(2);
                //startActivity(new Intent(Menu.this, MainActivity.class));
            }
        });
        Button button3 = (Button) view.findViewById(R.id.menuButton3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.onButtonSelected(3);
                //startActivity(new Intent(Menu.this, MainActivity.class));
            }
        });
        Button button4 = (Button) view.findViewById(R.id.menuButton4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.onButtonSelected(4);
                //startActivity(new Intent(Menu.this, MainActivity.class));
            }
        });
        Button button6 = (Button) view.findViewById(R.id.menuButton6);
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.onButtonSelected(6);
                //startActivity(new Intent(Menu.this, MainActivity.class));
            }
        });
        return view;
    }

    public interface OnButtonSelectedListener {
        public void onButtonSelected (int i);
    }

    @Override
    public void onAttach (Activity activity){
        super.onAttach(activity);
        if (activity instanceof OnButtonSelectedListener)
            listener = (OnButtonSelectedListener) activity;
        else {
            throw new ClassCastException(activity.toString() +
                    " does not implement OnButtonSelectedListener");
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        listener = null;
    }
}