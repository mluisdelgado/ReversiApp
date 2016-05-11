package com.student.marcos.reversi.activities;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
//import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
//import android.widget.Toast;
import com.student.marcos.reversi.R;
import com.student.marcos.reversi.model.miJugador;
import com.student.marcos.reversi.model.miMovimiento;
import com.student.marcos.reversi.model.miTablero;

import java.util.ArrayList;

import es.uam.eps.multij.AccionMover;
import es.uam.eps.multij.Evento;
import es.uam.eps.multij.ExcepcionJuego;
import es.uam.eps.multij.Jugador;
import es.uam.eps.multij.JugadorAleatorio;
import es.uam.eps.multij.Partida;
import es.uam.eps.multij.Tablero;

/**
 * Constructor del tablero, que extiende a view para el dibujo y la captura de eventos, como onTouch.
 * Implementa a Jugador para capturar los eventos enviados al usuario y poder realizar acciones sobre el tablero.
 */
public class Board extends View implements Jugador {

    private final MainActivity main;
    //Variable para guardar la partida
    Partida partida;

    //Variable para guardar el evento pendiente por resolver
    private Evento pendingEvent;

    //Variable para controlar el tiempo que pasa presionada la pantalla.
    long startTime;

    int widthOfBoard;
    int heightOfBoard;
    private final int SIZE = getResources().getInteger(R.integer.tablero_size);;
    private float heightOfTile;
    private float widthOfTile;
    private Paint circlePaint, backgroundPaint;

    //Variables por implementar a Jugador
    private String nombre;
    private boolean bot;

    /**
     * Constructor del tablero. LLama a iniciarPartida() y muestra las instrucciones.
     * @param context
     * @param attributes
     */
    public Board(Context context, AttributeSet attributes) {
        super(context, attributes);

        this.main = (MainActivity) context;
        System.out.println(main.getInitialFigure());

        setFocusable(true);
        setFocusableInTouchMode(true);

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setStrokeWidth(2);
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(Color.BLACK);

        iniciarPartida();

        Toast.makeText(this.getContext(), getResources().getString(R.string.msg_inicia), Toast.LENGTH_LONG).show();
    }

    /**
     * Devuelve el evento.
     * @return Evento
     */
    public Evento getEvent(){
        return pendingEvent;
    }

    /**
     * Continua una partida. Se le pasa el tablero y lo actualiza y realiza las acciones necesarias.
     * @param tablero String
     */
    public void continueGame(String tablero){
        try {
            partida.getTablero().stringToTablero(tablero);
        } catch (ExcepcionJuego excepcionJuego) {
            excepcionJuego.printStackTrace();
        }
        partida.continuar();
    }

    /**
     * Inicia todas las variables y crea una partida nueva.
     */
    private void iniciarPartida(){
        nombre = "Marcos";

        JugadorAleatorio j2 = new JugadorAleatorio("Pedro");

        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        jugadores.add(this);
        jugadores.add(j2);

        partida = new Partida(new miTablero(), jugadores);
        partida.addObservador(new miJugador());
        partida.comenzar();
    }

    /**
     * Se encarga de redibujar el tablero si se modifica el tamaño de pantalla.
     * @param width
     * @param height
     * @param oldWidth
     * @param oldHeight
     */
    protected void onSizeChanged (int width, int height, int oldWidth, int oldHeight)
    {
        if (width < height)
            height = width;
        else
            width = height;

        widthOfBoard = width;
        heightOfBoard = height;

        widthOfTile = width / SIZE;
        heightOfTile = height / SIZE;
        super.onSizeChanged(width, height, oldWidth, oldHeight);
    }

    /**
     * Dibuja el tablero.
     * @param canvas
     * @param paint
     */
    private void drawTiles (Canvas canvas, Paint paint){

        int onColor1;
        int onColor2;
        int offColor;
        float centerX, centerY, radio;

        //Como es un cuadrado podemos usar width o height, es lo mismo.
        radio = widthOfBoard/SIZE/2f;

        onColor1 = getResources().getColor(R.color.onColorTile1);
        onColor2 = getResources().getColor(R.color.onColorTile2);
        offColor = getResources().getColor(R.color.offColorTile);

        //Pinta el tablero.
        for (int i=0; i<SIZE; i++) {
            centerY = heightOfTile * (1 + 2 * i) / 2f;
            for (int j = 0; j < SIZE; j++) {
                if (pendingEvent.getPartida().getTablero().toString().charAt(i * SIZE + j + 1 + 2 * i) == 'x')
                    paint.setColor(onColor1);
                else if (pendingEvent.getPartida().getTablero().toString().charAt(i * SIZE + j + 1 + 2 * i) == '.')
                    paint.setColor(offColor);
                else if (pendingEvent.getPartida().getTablero().toString().charAt(i * SIZE + j + 1 + 2 * i) == 'o')
                    paint.setColor(onColor2);

                centerX = widthOfTile * (1 + 2 * j) / 2f;

                //canvas.drawRect(centerX-widthOfTile/2+4, centerY-heightOfTile/2+4, centerX+widthOfTile/2-4, centerY+heightOfTile/2-4, paint);
                //canvas.drawCircle(centerX, centerY, radio, paint);
            }
        }
    }

    /**
     * Dubuja el contenedor del tablero
     * @param canvas
     */
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0, widthOfBoard, heightOfBoard, backgroundPaint);
        drawTiles(canvas, circlePaint);
    }

    private int yToIndex (float y){
        return y < SIZE*heightOfTile ? (int)(y/heightOfTile) : -1;
    }

    private int xToIndex (float x){
        return x < SIZE*widthOfTile ? (int)(x/widthOfTile) : -1;
    }

    /**
     * Gestiona cuanto se toca la pantalla.
     * @param event MotionEvent
     * @return boolean
     */
    public boolean onTouchEvent(MotionEvent event) {

        int xIndex = 0;
        int yIndex = 0;

        int action = event.getAction();
        switch (action){
            //Gestiona cuando se presiona la pantalla. Se guardan las coordenadas pasadas a tablero
            //en xIndex e yIndex y se usan para enviar el evento.
            case MotionEvent.ACTION_DOWN:
                startTime = System.currentTimeMillis()/1000;
                float x = event.getX();
                float y = event.getY();
                xIndex = xToIndex(x);
                yIndex = yToIndex(y);

                if (xIndex < 0 || yIndex < 0) {
                    //Si estamos fuera del tablero cuenta como no movimiento.
                    try {
                        pendingEvent.getPartida().realizaAccion(new AccionMover(
                                this, new miMovimiento(SIZE, SIZE)));
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                }else {
                    try {
                        pendingEvent.getPartida().realizaAccion(new AccionMover(
                                this, new miMovimiento(yIndex, xIndex)));
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                }

                invalidate();

                if (pendingEvent.getTipo() == Evento.EVENTO_FIN) {
                    if(pendingEvent.getPartida().getTablero().getEstado() == Tablero.TABLAS)
                        Toast.makeText(this.getContext(), getResources().getString(R.string.msg_tablas), Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(this.getContext(), getResources().getString(R.string.msg_ganador1) + pendingEvent.getPartida().getJugador(pendingEvent.getPartida().getTablero().getTurno()).getNombre() + getResources().getString(R.string.msg_ganador2), Toast.LENGTH_LONG).show();
                }

                return true;
            case MotionEvent.ACTION_UP:
                //Si ha pasado mas de un segundo desde que se presiono la pantalla y la partida esta finalizada se reinicia.
                if (pendingEvent.getTipo() == Evento.EVENTO_FIN && ((System.currentTimeMillis()/1000) - startTime >= 1)) {
                    iniciarPartida();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * Funcion encargada de gestionar los eventos pasados. Guardo cada uno en pendingEvent para
     * poder acceder a la partida.
     * @param evento Evento pasado al jugador.
     */
    @Override
    public void onCambioEnPartida(Evento evento) {
        switch (evento.getTipo()) {
            case Evento.EVENTO_FIN:
                pendingEvent = evento;
                break;
            case Evento.EVENTO_CAMBIO:
                pendingEvent = evento;
                if(!this.puedeJugar(null)) {
                    //No hago nada si no puede jugar.
                }
                break;

            case Evento.EVENTO_CONFIRMA:

                pendingEvent = evento;
                // Siempre confirma las acciones;
                try {
                    evento.getPartida().confirmaAccion(
                            this, evento.getCausa(), true);
                }
                catch(Exception e) {

                }
                break;

            case Evento.EVENTO_TURNO:
                //Guardo el evento para su posterior uso cuando se detecte una pulsación.
                pendingEvent = evento;

                break;
        }
        //Redibujo el tablero cada vez que se produzca un evento.
        invalidate();
    }

    /**
     * Devuelve el nombre del jugador.
     * @return nombre
     */
    @Override
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Devuelve si el jugador puede jugar.
     * @param tablero Tablero sobre el que realizar la pregunta.
     * @return true o false
     */
    @Override
    public boolean puedeJugar(Tablero tablero) {
        return !bot;
    }
}