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
    private int cantFichas;
    
    //Contructor de EstadoMolino para el Estado inicial
	public EstadoMolino(){
        tablero= new Tablero(7,7); //tablero 7x7 con sus operaciones
        vecino= new Vecinos(24,24);//matriz ady 24x24
        currentPlayer=1; //Comienza el jugador numero 1
	    parent=null;//no tiene padre
        esMolinoBool=false;//no existen molinos
        cantFichas=0;
    }
	
    //SETS DE ESTADOS PARA COLOCAR FICHAS : SI NO GENERA MOLINO!!!
    public void setEstadoMolino(int jug,Vecinos vec, Tablero tab,boolean molino,int cantF,EstadoMolino father){
        this.vecino= vec;
        this.tablero=tab;
        if (jug==1) currentPlayer=2;//actualizo current jugador
        if (jug==2) currentPlayer=1;
        this.esMolinoBool=molino;
        this.parent=father;
        this.cantFichas=cantF;
        cantFichas++;   
    }
    //POR SI GENERAN MOLINO
    public void setEstadoMolino2(int jug,int posNew,Vecinos vec,Tablero tab,int cantF,EstadoMolino father){
        this.vecino=vec; //hago el backup de los atributos de la clase.
        this.tablero=tab;
        vecino.setFicha(jug,posNew);//juega la ficha
        tablero.refreshTab(vecino);
        if (jug==1) currentPlayer=2;//actualizo current jugador
        if (jug==2) currentPlayer=1;
        this.cantFichas=cantF;//backup de la cantidad de fichas
        cantFichas++;
        this.esMolinoBool= vecino.esMolino(posNew,jug);//calcula si es molino
    }
    //PARA BORRAR FICHAS !!! ACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
    public void setEstadoMolino3(int jug,int posNew,int posABorrar,Vecinos vec, Tablero tab, int cantF,boolean molino,EstadoMolino father ){
        this.vecino=vec; //backup
        this.tablero=tab;
        this.cantFichas=cantF;
        vecino.setFicha(jug,posNew);//juega la ficha
        vecino.borraFicha(posABorrar);//Desocupa ese "nodo" pos de su contrario
        tablero.refreshTab(vecino);//actualiza el tablero
        if (jug==1) currentPlayer=2;
        if (jug==2) currentPlayer=1;
        parent=father;//Identifica de que estado viene
        esMolinoBool=molino;
        cantFichas++;    
    }
    //PARA MOVER FICHAS Y CALCULAR MOLINO!!!
    public void setEstadoMolino4(int jug, int orig,int dest,Vecinos vec, Tablero tab, int cantF, EstadoMolino father){
        this.vecino=vec; //backup
        this.tablero=tab;
        this.cantFichas=cantF;
        vecino.borraFicha(orig);//borro la ficha seleccionada
        vecino.setFicha(jug,dest);//la muevo a algun adyacente desocupado
        tablero.refreshTab(vecino);//actualizo el tablero
        if (jug==1) currentPlayer=2;//actualizo current jugador
        if (jug==2) currentPlayer=1;
        parent=father;//actualizo padre
        esMolinoBool=vecino.esMolino(dest,jug);//si genero molino el movimiento
        cantFichas++;
    }
    //POR MOVIMIENTO DE FICHAS Y YA GENERO MOLINO
    public void setEstadoMolino5(int jug, int orig,int dest,int posABorrar,Vecinos vec, Tablero tab, int cantF, boolean molino, EstadoMolino father){
        this.vecino=vec; //backup
        this.tablero=tab;
        this.cantFichas=cantF;
        
        vecino.borraFicha(posABorrar);//borro la ficha seleccionada del contrario
        vecino.borraFicha(orig);//borro la ficha vieja
        vecino.setFicha(jug,dest);//la muevo a algun adyacente desocupado
        tablero.refreshTab(vecino);//actualizo el tablero
        if (jug==1) currentPlayer=2;//actualizo current jugador
        if (jug==2) currentPlayer=1;
        parent=father;//actualizo padre
        esMolinoBool=molino;
        cantFichas++;
    }

//Usar estas para la construccion de estados a partir de seteos
    public Vecinos getVecino(){
        return vecino;
    }
    public Tablero getTablero(){
        return tablero;
    }
    public EstadoMolino getParent(){
        return parent;
    }
    public int getCantFichas(){
        return cantFichas;
    }


    //Retorna si el estado es Max, es decir, si esta jugando 
	//el jugador 1.
    public boolean isMax(){
		return (currentPlayer==1);
	}
	
    //retorna el jugador corriente
	public int getPlayer(){
		return currentPlayer;
	}
	//retorna el tablero corriente
    public int[][] getTab(){
        return tablero.getTab();
    }
    //Retorna si dos estados son iguales, 
	//Para que sean iguales, deben tener los mismos tableros,
	//mismas componentes y debe estar jugando el mismo jugador. 
	public boolean equals(AdversarySearchState other){
		EstadoMolino st = (EstadoMolino) other;
		boolean eq = ( (Arrays.deepEquals( st.getTab(),this.getTab() ) ) && (st.getPlayer()==this.getPlayer() ) );		
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
    public List<Integer> dondeColoco(int player){ 
        return vecino.dondeColoco(player);
    }
    //retorna una lista de pares, que representa el nodo y cuantos ady tiene
    //el nodo de ese jugador.
    public List<Pair<Integer,Integer>> nodosJug(int player){
        List<Integer> lugares= vecino.dondeColoco(player);//saco lista de los nodos donde jugo
        List<Pair<Integer,Integer>> nodoCantAdy= new LinkedList<Pair<Integer,Integer>>(); 
        for (int i=0; i < lugares.size() ;i++ ) {//recorro toda la lista
            List<Integer> ady = vecino.nodosAdyLibres(lugares.get(i).intValue()); //saco la lista de ady libres de ese nodo
            Pair<Integer,Integer> par = new Pair<Integer,Integer>(lugares.get(i),ady.size());//armo par (nodo,cantidad de adyacentes) 
            nodoCantAdy.add(par);
        }
        return nodoCantAdy;
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
    public boolean estadoFin(){ //ESTADO FINAL ANALIZAR MEJOR
        //Gana jugador 2 :
        //jugador 1 tiene menos de 3 piezas
        boolean end=false;
        if (cantFichas>18){ //Si se supera el estado de carga de fichas 
            int piezas= vecino.cantFichasJug(1);
             //Lista de movimientos posibles del 1 es vacia.
            List<Pair<Integer,Integer>> mov= vecino.posibleMov(1);
            if (piezas <3 || mov.isEmpty()) end=true; //gana jugador 2.
            //Gana jugador 1 :
            piezas=vecino.cantFichasJug(2);
            mov.clear();
            mov=vecino.posibleMov(2);
            if (piezas <3 || mov.isEmpty()) end= true;
            //o como seria el empate.
        }
        return end;   
           
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