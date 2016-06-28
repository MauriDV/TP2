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

//-------------------------CONSTRUCTORES SEGUN SU UTILIDAD ---------------------------------
    
    //Constructor de EstadoMolino para el Estado inicial
	public EstadoMolino(){
        
        tablero= new Tablero(7,7); //tablero 7x7 con sus operaciones
        vecino= new Vecinos(24,24);//matriz ady 24x24
        vecino.cargaVecino();//Carga la lista de ADYACENCIAS---VEEEEEEEEEEEEEEEEER EN LOS OTROSSSS
        currentPlayer=1; //Comienza el jugador numero 1
	    parent=null;//no tiene padre
        esMolinoBool=false;//no existen molinos
        
        
    }

    //Constructor que se basa en clonar, para evitar problemas de memoria.
    //Insercion de una ficha y se fija si es molino.
    public EstadoMolino(int jug,int posNew,Vecinos vec, Tablero tabl,EstadoMolino father){
        tablero= new Tablero(7,7); //tablero 7x7 con sus operaciones
        vecino= new Vecinos(24,24);//matriz ady 24x24
        vecino.cargaVecino();//
        //-------------- CLONACION ------------------------
        //pasar lo de vec a vecinos 
        for (int f=0;f<vec.fila ; f++) {
            for (int c=0;c<vec.col ;c++ ) {
                vecino.adyacencia[f][c]=vec.adyacencia[f][c];
            }
        }
        //pasar jugadas
        for (int i=0; i<vec.fichaJug.length;i++ ) {
            vecino.fichaJug[i]=vec.fichaJug[i];
        }
        //paso cantidad historial de fichas.
        vecino.fichas=vec.fichas;
        //Paso la informacion del tablero (Para toString())
        for (int f=0;f<tabl.fila ;f++ ) {
            for (int c=0;c<tabl.col ;c++ ) {
                tablero.tab[f][c]=tabl.tab[f][c];
            }
        }
        //realizo las operaciones de insercion de una ficha verificando si genera molino
        vecino.setFicha(jug,posNew);//juega la ficha
        tablero.refreshTab(vecino);
        //System.out.println("EstadoMolino: Constructor: Luego de Actualizacion como que vecino :"+vecino.toString2());
        if (jug==1) currentPlayer=2;//actualizo current jugador
        if (jug==2) currentPlayer=1;
        //System.out.println("currentPlayer pasa a ser : "+jug);
        
        parent=father;      
        this.esMolinoBool= vecino.esMolino(posNew,jug);//calcula si es molino
    }   
      //Constructor para generar estados a partir de INSERCION DE FICHA Y ELIMINACION DE UNA DE
     //SU CONTRARIO
    public EstadoMolino(int jug,int posNew,int posABorrar,Vecinos vec, Tablero tabl, boolean molino,EstadoMolino father ){
        tablero= new Tablero(7,7); //tablero 7x7 con sus operaciones    
        vecino= new Vecinos(24,24);//matriz ady 24x24
        vecino.cargaVecino();//Carga la lista de ADYACENCIAS---VEEEEEEEEEEEEEEEEER EN LOS OTROSSSS
        //-------------- CLONACION ------------------------
        //pasar lo de vec a vecinos 
        for (int f=0;f<vec.fila ; f++) {
            for (int c=0;c<vec.col ;c++ ) {
                vecino.adyacencia[f][c]=vec.adyacencia[f][c];
            }
        }
        //pasar jugadas
        for (int i=0; i<vec.fichaJug.length;i++ ) {
            vecino.fichaJug[i]=vec.fichaJug[i];
        }
        //paso cantidad historial de fichas.
        vecino.fichas=vec.fichas;
        //Paso la informacion del tablero (Para toString())
        for (int f=0;f<tabl.fila ;f++ ) {
            for (int c=0;c<tabl.col ;c++ ) {
                tablero.tab[f][c]=tabl.tab[f][c];
            }
        }
        //Tratamiento por encuentro de MOlino y eliminar ficha del contrario

        vecino.setFicha(jug,posNew);//juega la ficha
        vecino.borraFicha(posABorrar);//Desocupa ese "nodo" pos de su contrario
        tablero.refreshTab(vecino);//actualiza el tablero
        if (jug==1) currentPlayer=2;
        if (jug==2) currentPlayer=1;
        this.parent=father;//Identifica de que estado viene
        esMolinoBool=molino;
        parent=father;
    }
    

    //Para generacion de estados a partir de toda la informacion y ademas de cuantas fichas se actualizan
    public EstadoMolino(int jug,Vecinos vec, Tablero tabl,boolean molino,EstadoMolino father){
        tablero= new Tablero(7,7); //tablero 7x7 con sus operaciones
        vecino= new Vecinos(24,24);//matriz ady 24x24
        vecino.cargaVecino();//
        //-------------- CLONACION ------------------------
        //pasar lo de vec a vecinos 
        for (int f=0;f<vec.fila ; f++) {
            for (int c=0;c<vec.col ;c++ ) {
                vecino.adyacencia[f][c]=vec.adyacencia[f][c];
            }
        }
        //pasar jugadas
        for (int i=0; i<vec.fichaJug.length;i++ ) {
            vecino.fichaJug[i]=vec.fichaJug[i];
        }
        //paso cantidad historial de fichas.
        vecino.fichas=vec.fichas;
        //Paso la informacion del tablero (Para toString())
        for (int f=0;f<tabl.fila ;f++ ) {
            for (int c=0;c<tabl.col ;c++ ) {
                tablero.tab[f][c]=tabl.tab[f][c];
            }
        }
        
        //El vecino ya fue seteado afuera, asi que solo actualizo el tablero
        tablero.refreshTab(vecino);
        if (jug==1) currentPlayer=2;//actualizo current jugador
        if (jug==2) currentPlayer=1;
        
        parent=father;
        this.esMolinoBool= molino;//calcula si es molino
    }

    //PARA MOVER FICHAS Y CALCULAR MOLINO!!!
    public EstadoMolino(int jug, int orig,int dest,Vecinos vec, Tablero tabl, EstadoMolino father){
        tablero= new Tablero(7,7); //tablero 7x7 con sus operaciones
        vecino= new Vecinos(24,24);//matriz ady 24x24
        vecino.cargaVecino();//
        //-------------- CLONACION ------------------------
        //pasar lo de vec a vecinos 
        for (int f=0;f<vec.fila ; f++) {
            for (int c=0;c<vec.col ;c++ ) {
                vecino.adyacencia[f][c]=vec.adyacencia[f][c];
            }
        }
        //pasar jugadas
        for (int i=0; i<vec.fichaJug.length;i++ ) {
            vecino.fichaJug[i]=vec.fichaJug[i];
        }
        //paso cantidad historial de fichas.
        vecino.fichas=vec.fichas;
        //Paso la informacion del tablero (Para toString())
        for (int f=0;f<tabl.fila ;f++ ) {
            for (int c=0;c<tabl.col ;c++ ) {
                tablero.tab[f][c]=tabl.tab[f][c];
            }
        }
          
        //tratamiento de movimiento de ficha

        vecino.borraFicha(orig);//borro la ficha seleccionada
        vecino.setFicha(jug,dest);//la muevo a algun adyacente desocupado
        tablero.refreshTab(vecino);//actualizo el tablero
        
        if (jug==1) currentPlayer=2;//actualizo current jugador
        if (jug==2) currentPlayer=1;
        this.parent=father;//actualizo padre
        esMolinoBool=vecino.esMolino(dest,jug);//si genero molino el movimiento
    
    }
   
    //POR MOVIMIENTO DE FICHAS Y YA GENERO MOLINO
    public EstadoMolino(int jug, int orig,int dest,int posABorrar,Vecinos vec, Tablero tabl, boolean molino, EstadoMolino father){
       tablero= new Tablero(7,7); //tablero 7x7 con sus operaciones
        vecino= new Vecinos(24,24);//matriz ady 24x24
        vecino.cargaVecino();//
        //-------------- CLONACION ------------------------
        //pasar lo de vec a vecinos 
        for (int f=0;f<vec.fila ; f++) {
            for (int c=0;c<vec.col ;c++ ) {
                vecino.adyacencia[f][c]=vec.adyacencia[f][c];
            }
        }
        //pasar jugadas
        for (int i=0; i<vec.fichaJug.length;i++ ) {
            vecino.fichaJug[i]=vec.fichaJug[i];
        }
        //paso cantidad historial de fichas.
        vecino.fichas=vec.fichas;
        //Paso la informacion del tablero (Para toString())
        for (int f=0;f<tabl.fila ;f++ ) {
            for (int c=0;c<tabl.col ;c++ ) {
                tablero.tab[f][c]=tabl.tab[f][c];
            }
        }
        //tratamiento de movimiento de fichas y eliminacion
        vecino.borraFicha(posABorrar);//borro la ficha seleccionada del contrario
        vecino.borraFicha(orig);//borro la ficha vieja
        vecino.setFicha(jug,dest);//la muevo a algun adyacente desocupado
        tablero.refreshTab(vecino);//actualizo el tablero
        if (jug==1) currentPlayer=2;//actualizo current jugador
        if (jug==2) currentPlayer=1;
        this.parent=father;//actualizo padre
        esMolinoBool=molino;

    }
//----------------------------- FIN DE CONSTRUCTORES 
    
// GETS
    //retorna la clase vecinos de la clase
    public Vecinos getVecino(){
        return this.vecino;
    }
    //retorna la clase tablero de la clase.
    public Tablero getTablero(){
        return this.tablero;
    }
    //retorna el padre de la clase.
    public EstadoMolino getParent(){
        return this.parent;
    }
    //Historial de cantida de fichas
    public int getCantFichas(){
        return this.vecino.cantFichas();
    }
    //retorna el jugador corriente
    public int getPlayer(){
        return currentPlayer;
    }
    //retorna el tablero corriente
    public int[][] getTab(){
        return tablero.getTab();
    }

//Clonar un estado    
    public EstadoMolino clonarEstado(){
        EstadoMolino clone= new EstadoMolino();
        clone.currentPlayer=this.getPlayer();; //Jug1 = 1, Jug2= 2
        clone.parent=this.getParent(); //el padre del estado.
        clone.tablero= this.getTablero(); //instancia de la clase Tablero
        clone.vecino=this.getVecino(); //instancia de la clase Vecinos
        clone.esMolinoBool=this.esMolinoBool;//Almacena el valor de verdad si el estado es o no molino.
        return clone;
    }


    //Retorna si el estado es Max, es decir, si esta jugando 
	//el jugador 1.
    public boolean isMax(){
		return (currentPlayer==1);
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
    //Dado un jugador retorna la cantidad de segmentos tomados de a 2 que tiene
    //en el juego
    public int segmentoDeA2 (int player){
        return vecino.cantSegmentosDea2(player);
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
        return vecino.win();   
    }
    //Quien gano en this.estado
    public int whosWin(){
        return vecino.whosWin();
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