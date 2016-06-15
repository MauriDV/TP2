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
	private int[][] tablero;//simula el estado del tablero nineMensMorris
	private int currentPlayer; //Jug1 = 1, Jug2= -1
	private EstadoMolino parent; //el padre del estado.
	private int fila=7; //tamaño de filas
	private int col=7;	//tamaño de columnas
	private List<Integer> vecinoCol; //Guarda por columna los vecinos
	private List<Integer> vecinoFil; //Guarda por fila los vecinos
	private int nficha;
	//Contructor de EstadoMolino para el Estado inicial
	public EstadoMolino(){
		//representa al jugador corriente que esta jugando
		currentPlayer=0;
		nficha=0;//
		//Inicializar Tablero con 0's
		for (int f=0; f<fila;f++){
			for(int c=0; c<col;c++){
				tablero[f][c]=0;
			}
		}
		parent=null;//para llevar quien es el padre del estado o 
					//en todo caso que regla se aplico para generarlo.
		//Seteo de vecinos de cada columna
		//HACER !!! 	
	}
	//Constructor de la clase con parametros
	//A partir de el se genera un nuevo estado.
	//le paso como dato quien juega, donde juega, y de que estado viene
	public EstadoMolino(int jug,int f,int c, EstadoMolino father){
		//El control de si es correcta la posicion se hace desde el juego
		tablero[f][c]= jug;//Donde juega 
		currentPlayer=jug; //Seteo quien hizo la jugada.		
		parent=father;//Y de que estado provino.
		nficha++;
	}
/*
	//ESTAS SON PARA EL CONTROL DEL JUEGO, NO VAN AQUI
	//--------------------------------------------------------------
	//Una posicion es apta en la matriz si es una posicion que respete
	//la estructura del juego Nine Mens Morris
	public boolean posicionApta(){

	}
	//una posicion esta Disponible en la matriz si, es posicionApta() y 
	//ademas esa posicion no esta ocupado por un 1 o -1.
	public boolean posicionDisponible(){

	}
	//-----------------------------------------------------------
*/
	//Retorna si el estado es Max, es decir, si esta jugando 
	//el jugador 1.
	public boolean isMax(){
		return (currentPlayer==1);
	}
	//retorna el tablero corriente
	public int[][] getTablero(){
		return tablero;
	}
	//retorna el jugador corriente
	public int getPlayer(){
		return currentPlayer;
	}
	//Retorna si dos estados son iguales, 
	//Para que sean iguales, deben tener los mismos tableros,
	//mismas componentes y debe estar jugando el mismo jugador. 
	public boolean equals(AdversarySearchState other){
		EstadoMolino st = (EstadoMolino) other;
		boolean eq = ( (Arrays.deepEquals( st.getTablero(),this.getTablero() ) ) && (st.getPlayer()==this.getPlayer() ) );		
		return eq;
	}
	//Retorna el estado del tablero en forma de String.
	//con el jugador que esta jugando 
	 public String toString() {
        String s = "\n\t  ---------------------------- \n\t";
        for (int f = 0; f < fila; f++) {
            for (int c = 0; c < c; c++) {
               s = s + " | "+tablero[f][c];
            }
            s = s + " | \n\t  ---------------------------- \n\t";
        }
        s = s + "   1   2   3   4   5   6   7\n";
        if (currentPlayer==1)
        	s+="Player 1 : "+currentPlayer;
        if (currentPlayer==-1)
        	s+="Player 2 : "+currentPlayer;
        if (currentPlayer==0)
        	s+="No hay jugadas "+currentPlayer;
        return s;
     }

     //Retorna el valor de verdad si el estado parametro es un molino
     //es molino si en la estructura del juego hay 3 mismas fichas alineadas
     //vertical u horizontalmente y ademas las fichas son "vecinas".
     public boolean esMolino(EstadoMolino s){
     	return false;
     }
     //retorna la cantida de fichas 
     public int getNumFicha(){
     	return nficha;
     }


     //Seteo de vecinos de cada columna en una Lista de listas
     //Se hacer solo una vez para saber que vecinos tiene cada columna
  //   public void seteoDeVecinos(){
   	//
    // 	vecinoCol.add()	
      // 	vecino.add(0,)
     	
     //}

    //Retorna la regla Aplicada para llegar this.Estado 
    //O quien es el padre de este estado. 
	public Object ruleApplied(){
		return (Object) parent;
	}
}