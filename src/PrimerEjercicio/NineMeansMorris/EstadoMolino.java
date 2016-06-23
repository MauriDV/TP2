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
	private int currentPlayer; //Jug1 = 1, Jug2= 2
	private EstadoMolino parent; //el padre del estado.
	private Tablero tablero; //instancia de la clase Tablero
    public Vecinos vecino; //instancia de la clase Vecinos
	public boolean esMolinoBool;//Almacena el valor de verdad si el estado es o no molino.
    
    //Contructor de EstadoMolino para el Estado inicial
	public EstadoMolino(){
        tablero= new Tablero(7,7); //tablero 7x7 con sus operaciones
        vecino= new Vecinos(24,24);//matriz ady 24x24
        currentPlayer=1; //Comienza el jugador numero 1
	    parent=null;//no tiene padre
        esMolinoBool=false;//no existen molinos
    }
	
    //Constructor de la clase con parametros
	//A partir de el se genera un nuevo estado.
	//le paso como dato quien juega, donde juega, y de que estado viene
    //Constructor para cuando se inserta una ficha
    //posiciones para jugar de 0 a 23."nodos"
	public EstadoMolino(int jug,int pos, EstadoMolino father){
		vecino.setFicha(jug,pos);//jugada del jugador en posicion donde juega
        tablero.refreshTab(vecino);//Actualiza el tablero con la jugada
        if (jug==1) currentPlayer=2;
        if (jug==2) currentPlayer=1;
         //Seteo quien hizo la jugada.		
		parent=father;//Y de que estado provino.
        esMolinoBool= vecino.esMolino(pos,jug);//Calcula si se genero molino con esa jugada
	}
    
    //Constructor para cuando se genera molino por poner una ficha
    //Entonces provee de la generacion de mas estados a partir de ello,
    //permitiendo eliminar, al jugador corriente, una ficha del contrario.
	public EstadoMolino(int jug,int posNueva,int posABorrar,boolean molino,EstadoMolino father){
        vecino.setFicha(jug,posNueva);//juega la ficha
        vecino.borraFicha(posABorrar);//Desocupa ese "nodo" pos de su contrario
        tablero.refreshTab(vecino);//actualiza el tablero
        if (jug==1) currentPlayer=2;
        if (jug==2) currentPlayer=1;
        parent=father;//Identifica de que estado viene
        esMolinoBool=molino;
    }
    
    //Constructor para generar estados a partir de movimientos
    // parametros importantes: ficha a mover y hacia que nodo destino moverla
    //ver si la ficha que mueve genera molino
    public EstadoMolino(int jug,int posOld,int posNew,EstadoMolino father){
        vecino.borraFicha(posOld);//borro la ficha seleccionada
        vecino.setFicha(jug,posNew);//la muevo a algun adyacente desocupado
        tablero.refreshTab(vecino);//actualizo el tablero
        if (jug==1) currentPlayer=2;//actualizo current jugador
        if (jug==2) currentPlayer=1;
        parent=father;//actualizo padre
        esMolinoBool=vecino.esMolino(posNew,jug);//si genero molino el movimiento
    }

    //Constructor de estados a partir de realizar un movimiento, eliminar ese movimiento
    //insertar en el destino y ademas permitir borrar una ficha del contrario.
    public EstadoMolino(int jug,int posOld,int posNew,int posDel,boolean molino,EstadoMolino father){
        vecino.borraFicha(posDel);//borro la ficha seleccionada del contrario
        vecino.borraFicha(posOld);//borro la ficha vieja
        vecino.setFicha(jug,posNew);//la muevo a algun adyacente desocupado
        tablero.refreshTab(vecino);//actualizo el tablero
        if (jug==1) currentPlayer=2;//actualizo current jugador
        if (jug==2) currentPlayer=1;
        parent=father;//actualizo padre
        esMolinoBool=molino;
    }



    //Retorna si el estado es Max, es decir, si esta jugando 
	//el jugador 1.
    public boolean isMax(){
		return (currentPlayer==1);
	}
	
    //retorna el tablero corriente
	public int[][] getTablero(){
		return tablero.getTab();
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
    
     //Retorna el valor de verdad si el estado parametro es un molino
     //es molino si en la estructura del juego hay 3 mismas fichas alineadas
     //vertical u horizontalmente y ademas las fichas son "vecinas".
     public boolean esMolino(){
        return esMolinoBool;
     }
     //Retorna para el player actual que lugares tiene disponible para moverse
     public List<Pair<Integer,Integer>> getPosiblesMov(){
        List<Pair<Integer,Integer>> lugares= vecino.posibleMov(currentPlayer);
        return lugares;
     }
     //Retorna una lista con los nodos "INTEGER OBJECT" libres para poder colocar fichas
     public List<Integer> lugaresDisp(){
        return vecino.lugaresLibres(); //convertir a int afuera!
     }
    
     //retorna la cantida de fichas jugadas en el tablero.
     public int getNumFichas(){
     	return vecino.cantFichas();
     }
     //Dado un jugador retorna la cantidad de fichas que tiene colocadas en el tablero.
    public int getNumFichaJug(int jugador){
        return vecino.cantFichasJug(jugador);
    }
    //Retorna la lista de los nodos en donde coloco el jugador 1
    public List<Integer> dondeColoco1(){
        return vecino.dondeColoco(1);
    }
    //Analogo a dondeColoco1
    public List<Integer> dondeColoco2(){
        return vecino.dondeColoco(2);
    }   




    //Retorna la regla Aplicada para llegar this.Estado 
    //O quien es el padre de este estado. 
	public Object ruleApplied(){
		return (Object) parent;
	}
    /*
    Un jugador gana si consigue que su oponente quede con menos de tres piezas,
    o no pueda realizar movimientos.
    */
    public boolean estadoFin(){
        //Gana jugador 2 :
        //jugador 1 tiene menos de 3 piezas
        int piezas= vecino.cantFichasJug(1);
        //Lista de movimientos posibles del 1 es vacia.
        List<Pair<Integer,Integer>> mov= vecino.posibleMov(1);
        if (piezas <3 || mov.isEmpty()) return true; //gana jugador 2.
        //Gana jugador 1 :
        piezas=vecino.cantFichasJug(2);
        mov.clear();
        mov=vecino.posibleMov(2);
        if (piezas <3 || mov.isEmpty()) return true;
        //o como seria el empate.
        return false;   
    }
    //Retorna el estado del tablero en forma de String.
    //con el jugador que esta jugando 
     public String toString() {
        String s= tablero.toString2();
        if (currentPlayer==1)
            s+="Player 1 : "+currentPlayer;
        if (currentPlayer==2)
            s+="Player 2 : "+currentPlayer;
        return s;
     }
}