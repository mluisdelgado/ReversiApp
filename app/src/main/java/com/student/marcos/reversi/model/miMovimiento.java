package com.student.marcos.reversi.model;

import es.uam.eps.multij.Movimiento;


public class miMovimiento extends Movimiento {
	
	private int i;
	private int j;
	
	String[] indexToLetter = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
	
	public miMovimiento(int i, int j){
		this.i = i;
		this.j = j;
	}

	@Override
	public String toString() {
		return indexToLetter[j]+(7-i);
	}

	@Override
	public boolean equals(Object o) {
		return (o.toString().equals(this.toString())) ? true : false;
	}
}
