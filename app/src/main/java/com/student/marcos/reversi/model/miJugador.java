package com.student.marcos.reversi.model;

import android.util.Log;

import java.io.IOException;

import es.uam.eps.multij.AccionMover;
import es.uam.eps.multij.Evento;
import es.uam.eps.multij.Jugador;
import es.uam.eps.multij.Tablero;


public class miJugador implements Jugador{
	private String nombre;
	private boolean bot;
	
	public miJugador(String nombre){
		this.nombre = nombre;
		this.bot = false;
	}
	
	public miJugador(){
		this.bot = true;
	}

	@Override
	public void onCambioEnPartida(Evento evento) {
		switch (evento.getTipo()) {
	        case Evento.EVENTO_CAMBIO:
	        	if(!this.puedeJugar(null)){
	        		//System.out.println(evento.getDescripcion());
	        	}
	            break;
	            
	        case Evento.EVENTO_CONFIRMA:
	            //Siempre confirma las acciones
	            try {
	                evento.getPartida().confirmaAccion(
	                        this, evento.getCausa(), true);
	            }
	            catch(Exception e) {
	            	
	            }
	            break;
	            
	        case Evento.EVENTO_TURNO:
	            //No hace falta hacer nada.
	            break;
	    }
		
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public boolean puedeJugar(Tablero tablero) {
		return !bot;
	}

}
