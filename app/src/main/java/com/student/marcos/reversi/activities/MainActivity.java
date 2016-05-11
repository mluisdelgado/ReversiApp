package com.student.marcos.reversi.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import com.student.marcos.reversi.R;
import com.student.marcos.reversi.activities.Menu;
import com.student.marcos.reversi.model.miJugador;
import com.student.marcos.reversi.model.miTablero;

import java.util.ArrayList;

import es.uam.eps.multij.Evento;
import es.uam.eps.multij.Jugador;
import es.uam.eps.multij.Partida;

public class MainActivity extends Activity {

    public String getInitialFigure() {
        return Preferences.getFigureCode(MainActivity.this);
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Guarda el tablero en un string.
     * @param outState
     */
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        Board mainBoard = (Board) findViewById(R.id.board);
        if(mainBoard.getEvent()!=null)
            outState.putString("GRID", mainBoard.getEvent().getPartida().getTablero().tableroToString());
    }

    /**
     * Reinicia el tablero desde el string guardado.
     * @param savedInstanceState
     */
    public void onRestoreInstanceState (Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            String grid = savedInstanceState.getString("GRID");
            Board mainBoard = (Board) findViewById(R.id.board);
            mainBoard.continueGame(grid);
        }
    }
}
