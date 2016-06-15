import java.io.*;
import java.util.*;
/**
* Clase : EstadoMolino implementa los metodos abstractos de la interface
* AdversarySearchState, para la representacion de un estado en el problema
* del juego NineMeansMorris.
*
* @author: Delle Vedove Mauricio, Rondeau Matias
* @version 1.0	
**/

public class EstadoMolino implements AdversarySearchState {
	private int[][] tablero;
	private EstadoMolino parent;
	private int fila=7;
	private int col=7;

	//Contructor de EstadoMolino para el Estado inicial
	public EstadoMolino(){
		//Inicializar Tablero con 0's
		for (int f; f<fila;f++){
			for(int c; c<col;c++){
				tablero[f][c]=0;
			}
		}
		parent=null;//para llevar quien es el padre del estado o 
					//en todo caso que regla se aplico para generarlo.
	}
	//Constructor de la clase con parametros
	//A partir de el se genera un nuevo estado.
	//le paso como dato quien juega, donde juega, y de que estado viene
	public EstadoMolino(int jug,int f,int c, EstadoMolino father){

	}


	public boolean isMax(){
		return false;
	}

	public boolean equals(AdversarySearchState other){
		return false;
	}

	public String toString(){
		return "";
	}

	public Object ruleApplied(){
		
		return (Object) parent;
	}
}