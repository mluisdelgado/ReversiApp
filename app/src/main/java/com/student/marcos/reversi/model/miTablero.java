package com.student.marcos.reversi.model;

import java.util.ArrayList;

import es.uam.eps.multij.ExcepcionJuego;
import es.uam.eps.multij.Movimiento;
import es.uam.eps.multij.Tablero;


public class miTablero extends Tablero{
	
	private int[][] tablero;
	
	public miTablero(){
		tablero = new int[8][8];
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				tablero[i][j] = -1;
			}
		}
		tablero[3][4] = 0;
		tablero[4][3] = 0;
		tablero[3][3] = 1;
		tablero[4][4] = 1;
		turno = 0;
		numJugadas = 0;
		estado = EN_CURSO;
	}

	public int getCasilla(int i, int j){
		return tablero[i][j];
	}

	private void convertirPasadas(int i, int j){
		int conv;
		//VABJ
		if (i+1 < 8){
			conv = 0;
			if(tablero[i+1][j] != turno && tablero[i+1][j] != -1){
				for (int aux = 2; i+aux < 8; aux++){
					if (tablero[i+aux][j] != turno){
						if (conv == 1)
							tablero[i+aux][j] = turno;
						continue;
					}else if (tablero[i+aux][j] == turno){
						if (conv == 1)
							break;
						aux = 0;
						conv = 1;
					}
				}
			}
		}
		//VARR
		if (i-1 >= 0){
			conv = 0;
			if(tablero[i-1][j] != turno && tablero[i-1][j] != -1){
				for (int aux = 2; i-aux >= 0; aux++){
					if (tablero[i-aux][j] != turno){
						if (conv == 1)
							tablero[i-aux][j] = turno;
						continue;
					}else if (tablero[i-aux][j] == turno){
						if (conv == 1)
							break;
						aux = 0;
						conv = 1;
					}
				}
			}
		}
		//HDER
		if (j+1 < 8){
			conv = 0;
			if(tablero[i][j+1] != turno && tablero[i][j+1] != -1){
				for (int aux = 2; j+aux < 8; aux++){
					if (tablero[i][j+aux] != turno){
						if (conv == 1)
							tablero[i][j+aux] = turno;
						continue;
					}else if (tablero[i][j+aux] == turno){
						if (conv == 1)
							break;
						aux = 0;
						conv = 1;
					}
				}
			}
		}
		//HIZQ
		if (j-1 >= 0){
			conv = 0;
			if(tablero[i][j-1] != turno && tablero[i][j-1] != -1){
				for (int aux = 2; j-aux >= 0; aux++){
					if (tablero[i][j-aux] != turno){
						if (conv == 1)
							tablero[i][j-aux] = turno;
						continue;
					}else if (tablero[i][j-aux] == turno){
						if (conv == 1)
							break;
						aux = 0;
						conv = 1;
					}
				}
			}
		}
		//DABD
		if (i+1 < 8 && j+1 < 8){
			conv = 0;
			if(tablero[i+1][j+1] != turno && tablero[i+1][j+1] != -1){
				for (int aux = 2; i+aux < 8 && j+aux < 8; aux++){
					if (tablero[i+aux][j+aux] != turno){
						if (conv == 1)
							tablero[i+aux][j+aux] = turno;
						continue;
					}else if (tablero[i+aux][j+aux] == turno){
						if (conv == 1)
							break;
						aux = 0;
						conv = 1;
					}
				}
			}
		}
		//DARD
		if (i-1 >= 0 && j+1 < 8){
			conv = 0;
			if(tablero[i-1][j+1] != turno && tablero[i-1][j+1] != -1){
				for (int aux = 2; i-aux >= 0 && j+aux < 8; aux++){
					if (tablero[i-aux][j+aux] != turno){
						if (conv == 1)
							tablero[i-aux][j+aux] = turno;
						continue;
					}else if (tablero[i-aux][j+aux] == turno){
						if (conv == 1)
							break;
						aux = 0;
						conv = 1;
					}
				}
			}
		}
		//DARI
		if (i-1 >= 0 && j-1 >= 0){
			conv = 0;
			if(tablero[i-1][j-1] != turno && tablero[i-1][j-1] != -1){
				for (int aux = 2; i-aux >= 0 && j-aux >= 0; aux++){
					if (tablero[i-aux][j-aux] != turno){
						if (conv == 1)
							tablero[i-aux][j-aux] = turno;
						continue;
					}else if (tablero[i-aux][j-aux] == turno){
						if (conv == 1)
							break;
						aux = 0;
						conv = 1;
					}
				}
			}
		}
		//DABI
		if (i+1 < 8 && j-1 >= 0){
			conv = 0;
			if(tablero[i+1][j-1] != turno && tablero[i+1][j-1] != -1){
				for (int aux = 2; i+aux < 8 && j-aux >= 0; aux++){
					if (tablero[i+aux][j-aux] != turno){
						if (conv == 1)
							tablero[i+aux][j-aux] = turno;
						continue;
					}else if (tablero[i+aux][j-aux] == turno){
						if (conv == 1)
							break;
						aux = 0;
						conv = 1;
					}
				}
			}
		}
	}
	
	@Override
	public void mueve(Movimiento m) throws ExcepcionJuego {
		if(esValido(m)){
			if (m.equals(new miMovimiento(8, 8))){
				cambiaTurno();
				ultimoMovimiento = m;
				if(compruebaFin()!=-1){
					estado = FINALIZADA;
					turno = compruebaFin();
				}
			}else{
			//mover ficha a m
				String aux = m.toString();
				tablero[7 - (((int) aux.charAt(1)) - 48)][((int) aux.charAt(0)) - 65] = turno;
				//Convertir fichas pasadas
				convertirPasadas(7 - (((int) aux.charAt(1)) - 48), ((int) aux.charAt(0)) - 65);
				cambiaTurno();
				ultimoMovimiento = m;
				if(compruebaFin()!=-1){
					estado = FINALIZADA;
					turno = compruebaFin();
				}
			}
		}else{
			throw new ExcepcionJuego("Movimiento no válido.");
		}
		
	}

	@Override
	public boolean esValido(Movimiento m) {
		if (movimientosValidos().contains(m) || m.equals(new miMovimiento(8, 8)))
			return true;
		return false;
	}

	@Override
	public ArrayList<Movimiento> movimientosValidos() {
		ArrayList<Movimiento> movValidos = new ArrayList<Movimiento>();
		movValidos.add(new miMovimiento(8, 8));
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (tablero[i][j] == -1){
					//VABJ
					if (i+1 < 8){
						if(tablero[i+1][j] != turno && tablero[i+1][j] != -1){
							for (int aux = 2; i+aux < 8; aux++){
								if (tablero[i+aux][j] != turno){
									continue;
								}else if (tablero[i+aux][j] == turno){
									movValidos.add(new miMovimiento(i, j));
									break;
								}
							}
						}
					}
					//VARR
					if (i-1 >= 0){
						if(tablero[i-1][j] != turno && tablero[i-1][j] != -1){
							for (int aux = 2; i-aux >= 0; aux++){
								if (tablero[i-aux][j] != turno){
									continue;
								}else if (tablero[i-aux][j] == turno){
									movValidos.add(new miMovimiento(i, j));
									break;
								}
							}
						}
					}
					//HDER
					if (j+1 < 8){
						if(tablero[i][j+1] != turno && tablero[i][j+1] != -1){
							for (int aux = 2; j+aux < 8; aux++){
								if (tablero[i][j+aux] != turno){
									continue;
								}else if (tablero[i][j+aux] == turno){
									movValidos.add(new miMovimiento(i, j));
									break;
								}
							}
						}
					}
					//HIZQ
					if (j-1 >= 0){
						if(tablero[i][j-1] != turno && tablero[i][j-1] != -1){
							for (int aux = 2; j-aux >= 0; aux++){
								if (tablero[i][j-aux] != turno){
									continue;
								}else if (tablero[i][j-aux] == turno){
									movValidos.add(new miMovimiento(i, j));
									break;
								}
							}
						}
					}
					//DABD
					if (i+1 < 8 && j+1 < 8){
						if(tablero[i+1][j+1] != turno && tablero[i+1][j+1] != -1){
							for (int aux = 2; i+aux < 8 && j+aux < 8; aux++){
								if (tablero[i+aux][j+aux] != turno){
									continue;
								}else if (tablero[i+aux][j+aux] == turno){
									movValidos.add(new miMovimiento(i, j));
									break;
								}
							}
						}
					}
					//DARD
					if (i-1 >= 0 && j+1 < 8){
						if(tablero[i-1][j+1] != turno && tablero[i-1][j+1] != -1){
							for (int aux = 2; i-aux >= 0 && j+aux < 8; aux++){
								if (tablero[i-aux][j+aux] != turno){
									continue;
								}else if (tablero[i-aux][j+aux] == turno){
									movValidos.add(new miMovimiento(i, j));
									break;
								}
							}
						}
					}
					//DARI
					if (i-1 >= 0 && j-1 >= 0){
						if(tablero[i-1][j-1] != turno && tablero[i-1][j-1] != -1){
							for (int aux = 2; i-aux >= 0 && j-aux >= 0; aux++){
								if (tablero[i-aux][j-aux] != turno){
									continue;
								}else if (tablero[i-aux][j-aux] == turno){
									movValidos.add(new miMovimiento(i, j));
									break;
								}
							}
						}
					}
					//DABI
					if (i+1 < 8 && j-1 >= 0){
						if(tablero[i+1][j-1] != turno && tablero[i+1][j-1] != -1){
							for (int aux = 2; i+aux < 8 && j-aux >= 0; aux++){
								if (tablero[i+aux][j-aux] != turno){
									continue;
								}else if (tablero[i+aux][j-aux] == turno){
									movValidos.add(new miMovimiento(i, j));
									break;
								}
							}
						}
					}
				}
			}
		}
		return movValidos;
	}

	@Override
	public String tableroToString() {
		String aux = "";
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if( tablero[i][j] == -1)
					aux = aux + ".";
				if( tablero[i][j] == 0)
					aux = aux + "o";
				if( tablero[i][j] == 1)
					aux = aux + "x";
			}
		}
		return aux + "|" + turno + "|" + estado;
	}

	@Override
	public void stringToTablero(String cadena) throws ExcepcionJuego {
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (cadena.charAt(i*8+j) == '.')
					tablero[i][j] = -1;
				else if (cadena.charAt(i*8+j) == 'o')
					tablero[i][j] = 0;
				else if (cadena.charAt(i*8+j) == 'x')
					tablero[i][j] = 1;
				else
					throw new ExcepcionJuego("Tablero mal construido");
			}
		}
		String[] aux = cadena.split("|");
		turno = Integer.parseInt(aux[1]);
		estado = Integer.parseInt(aux[2]);
	}

	@Override
	public String toString() {
		String aux = "";
		for (int i = 0; i < 8; i++){
			aux = aux + i;
			for (int j = 0; j < 8; j++){
				if( tablero[i][j] == -1)
					aux = aux + ".";
				if( tablero[i][j] == 0)
					aux = aux + "o";
				if( tablero[i][j] == 1)
					aux = aux + "x";
			}
			aux = aux + "\n";
		}
		aux = aux + " 01234567";
		return aux;
	}
	
	private int compruebaFin(){ // -1 no acabada, 0 gana 0, 1 gana 1
		int ceros = 0;
		int unos = 0;
		int vacias = 0;
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (tablero[i][j] == 0)
					ceros++;
				if (tablero[i][j] == 1)
					unos++;
				if (tablero[i][j] == -1)
					vacias++;
			}
		}
		if (ceros == 0)
			return 1;
		if (unos == 0)
			return 0;
		if (vacias == 0){
			return (unos > ceros) ? 1 : 0;
		}
		return -1;
	}

}
