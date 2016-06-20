/**
* Clase : Vecinos implementa una clase que contiene una matriz de adyancencias
* para vecinos de un nodo.
*@author Delle Vedove Mauricio, Rondeau Matias
*@version 1.0
**/
import java.io.*;
import java.util.*;


public class Vecinos {
	private int[][] adyacencia;//la matriz de adyancencia cada fila contiene 10's o 20's
	//ambos valores representan costos de llegar de un nodo a otro horizontalmente 10.
	//llega de un nodo a otro verticalmente 20.
	//ej: fila 0: 0 0 0 0 0 10 0 10 0 20 0 20 20 ...hasta 23 col
	//    fila 1: 10 0 0 0 0 20 0 0 0 10 10 10 20 ...
	//Los 10 hacen referencia a que dada una fila que representa un nodo en la estructura
	//del juego, donde hay un 10 entonces esa columna es adyacente a el (a la fila) pero horizontalmente.
	//Donde hay 20 adyancente vertical.
	//AMPLIAR MAS EN MANUAL !!!---------------------------------------

	private int fila; //24 x 24
	private int col;
	private int[] fichaJug; //1 o 2 dependiendo que jugador juega jug1=1, jug2=2
	//Constructor de la clase parametrizado, seteo desde afuera la cantidad de
	//filas y de columnas
	private List<Integer> molinos; //Historial de los nodos en los que se generaron molinos
	private List<Integer> molinoCurrent;//Los molinos corrientes

	public Vecinos(int f, int c){
		fila=f;//se setea con 23 desde afuera
		col=c;//se setea con 23 desde afuera.
		adyacencia= new int[fila][col];
		//carga de ceros
		for (int fi=0;fi < fila ;fi++ ) {
			for (int co=0;co <col ; co++) {
				adyacencia[fi][co]=0;	
			}
		}
		//Inicializacion de las fichas en cada fila.
		//parte de que no hay ninguna ficha
		//una celda puede contener 1 o 2 : jug1=1, jug2=2.
		fichaJug= new int[fila];
		for (int i=0;i <fila ;i++ ) {
			fichaJug[i]=0;
		}
		molinos= new LinkedList<Integer>();
		molinoCurrent=new LinkedList<Integer>();
	}	
	//Carga la matriz de adyacencias correspondiente a las relaciones
	//que existen de "vecindad"
	public void cargaVecino(){
		//10 para costo de adyancentes horizontales
		//20 para costo de adyacentes verticales
		//ej: el 0 tiene adyacent horizontal al 1, y adj vertical al 9.
		adyacencia[0][1]=10; 
		adyacencia[0][9]=20;
		
		adyacencia[1][0]=10;
		adyacencia[1][2]=10;
		adyacencia[1][4]=20;
		
		adyacencia[2][1]=10;
		adyacencia[2][14]=20;
		
		adyacencia[3][10]=20;
		adyacencia[3][4]=10;
		
		adyacencia[4][1]=20;
		adyacencia[4][3]=10;
		adyacencia[4][5]=10;
		adyacencia[4][7]=20;
		
		adyacencia[5][4]=10;
		adyacencia[5][13]=20;
		
		adyacencia[6][7]=10;
		adyacencia[6][11]=20;
		
		adyacencia[7][4]=20;
		adyacencia[7][6]=10;
		adyacencia[7][8]=10;
		
		adyacencia[8][7]=10;
		adyacencia[8][12]=20;
		
		adyacencia[9][0]=20;
		adyacencia[9][10]=10;
		adyacencia[9][21]=20;
		
		adyacencia[10][3]=20;
		adyacencia[10][9]=10;
		adyacencia[10][11]=10;
		adyacencia[10][18]=20;
		
		adyacencia[11][6]=20;
		adyacencia[11][10]=10;
		adyacencia[11][15]=20;
		
		adyacencia[12][8]=20;
		adyacencia[12][13]=10;
		adyacencia[12][17]=20;
		
		adyacencia[13][5]=20;
		adyacencia[13][12]=10;
		adyacencia[13][14]=10;
		adyacencia[13][20]=20;
		
		adyacencia[14][2]=20;
		adyacencia[14][13]=10;
		adyacencia[14][23]=20;
		
		adyacencia[15][11]=20;
		adyacencia[15][16]=10;
		
		adyacencia[16][15]=10;
		adyacencia[16][19]=20;
		adyacencia[16][17]=10;
		
		adyacencia[17][12]=20;
		adyacencia[17][16]=10;
		
		adyacencia[18][10]=20;
		adyacencia[18][19]=10;
		
		adyacencia[19][16]=20;
		adyacencia[19][18]=10;
		adyacencia[19][20]=10;
		adyacencia[19][22]=20;

		adyacencia[20][13]=20;
		adyacencia[20][19]=10;
		
		adyacencia[21][9]=20;
		adyacencia[21][22]=10;
		
		adyacencia[22][19]=20;
		adyacencia[22][21]=10;
		adyacencia[22][23]=10;
		
		adyacencia[23][14]=20;
		adyacencia[23][22]=10;
	
		
	}
	//Setea que jugador jugo en una posicion,0 si ninguno. 
	public void setFicha(int ficha, int pos){
		fichaJug[pos]=ficha;
	}
	//Dada una posicion borra del arreglo de jugadas, ese "nodo".
	//y lo deja disponible
	public void borraFicha(int pos){
		fichaJug[pos]=0;
	}
	//Retorna el arreglo que contiene que jugador jugo en cada posicion
	//ej: 0 1 2 3 4 5 6 7 ... posiciones del arreglo
	//    1 2 1 1 1 2 1 2 ... jugador
	//cada posicion representa un nodo del juego. 
	public int[] getFichasJug(){
		return fichaJug;
	}
	//Retorna la matriz de adyancencias
	public int[][] getAdyacencias(){
		return adyacencia; 
	}

	//Dado un "nodo" y el jugador que puso la ultima ficha, se busca si
	//ese nodo creo un molino. 3 alineados verticales o 3 horizontales.
	public boolean esMolino(int nodo,int jugador){ 		
		List<Integer> visited = new LinkedList<Integer>();
		//Analisis para nodos horizontales
		int search=10; 
		int i= dfs(nodo,search,visited,jugador);
		visited.clear();
        if (i==3){
        	//guardo los nodos que generaron el molino en este recorrido.
        	molinos.addAll( molinoCurrent);//guardo en backup 
        	//de la clase el historial de nodos que hicieron molinos	
        	molinoCurrent.clear();//libero el "calculador" de molinos corrientes.
        	return true;
        }
        else{//si no es 3 la cantidad, entonces, no genero molino este recorrido
        	molinoCurrent.clear();
        }	

		//Analisis nodos verticales
		search=20;
		i=dfs(nodo,search,visited,jugador);
		visited.clear();
		if (i==3){ 
			molinos.addAll(molinoCurrent);
			molinoCurrent.clear();	
			return true;
		}
		else{
			molinoCurrent.clear();
		}
		//Si no encontro, entonces no hay molino.
		return false;

	}
	/*
1  método DFS( origen):
2      creamos una pila S
3      agregamos origen a la pila S
4      marcamos origen como visitado
5      mientras S no este vacío:
6          sacamos un elemento de la pila S llamado v
7          para cada vertice w adyacente a v en el Grafo: 
8              si w no ah sido visitado:
9                 marcamos como visitado w
10                 insertamos w dentro de la pila S
	*/
	private int dfs(int nodo, int search,List<Integer> visited,int jug){
		   	Stack<Integer> pila = new Stack<Integer>();
    		pila.push(nodo);//apilo nodo origen 
    		visited.add(nodo);//marco visitado
    		int count=0;//inicializo contador.
    		while (!pila.isEmpty() ) {
    			if (count!=3){
    				Integer current = pila.pop();//saco nodo de la pila ok
    				if (fichaJug[current.intValue()]==jug){ //si la posicion coincide con el jugador (1 o 2)
    					//la primera vez deberia coincidir
    					count++;
    					molinoCurrent.add(current);//Guardo los nodos que van haciendo molino
    					for (int c=0; c < col ;c++ ) { //obtener los adyacentes    							
    						if (adyacencia[current.intValue()][c]==search){ //busca por 10 horizontales o 20 verticales
    							if ( !visited.contains ( (Integer) c) ) { //analisis si no fue visitado "la columna"
    								visited.add(c); //agregarlo a visitados
    								pila.push(c);//agregarlo a la pila
    							}
	   						}		
    					}
    				}
    			}
    			else{
    				//count ==3;
    				return count;
    			}
			}
   			return count;	
  	}		
  	//Dado un jugador 1 o 2, retorna la cantidad de fichas que tiene 
  	public int cantFichasjug(int jug){
  		int count=0;
  		for (int i=0; i <fichaJug.length ;i++ ) {
  				if(fichaJug[i]==jug) count++;
  		}
  		return count;	
  	} 
  	//Cantidad de fichas que tiene el juego.
  	public int cantFichas(){
  		int count=0;
  		for (int i =0;i < fichaJug.length ;i++ ) {
  			if(fichaJug[i]==1 || fichaJug[i]==2) count++; 
  		}
  		return count;
  	}
  	//Muestra una lista de pares de movimientos que puede realizar el jugador
  	//ejemplo (nodo 0, puede mover a nodo 9) (0,9)
  	//(0,1) el 0 puede mover al 1
  	/*public List<Pair<Integer,Integer>> showMoves(int jugador){
  		List<Integer> apareceEn = new LinkedList<Integer>();//En cada posicion de esta
  		//se lista guarda en que fila aparece el jugador pasado por parametro
  		for (int i=0;i < fichaJug.length ;i++ ) {
  			if (fichaJug[i]==jugador) 
  				apareceEn.add(i);//guardo la fila en donde aparece el jugador
  			
  		}


  		List<Pair<Integer,Integer>> allMoves= new LinkedList<LinkedList<Integer>> ();
  		

  		return allMoves;
  	}
*/




	//toString de la clase.
	//muestra la lista de adyancencias
	 public String toString() {
        String s ="";// "\n\t  ---------------------------- \n\t";
        for (int f = 0; f < fila; f++) {
            for (int c = 0; c < col; c++) {
               s = s + " | "+adyacencia[f][c];
            }
            s+=" | END FILA :"+f+"    " ;
            //s = s + " | \n\t  ---------------------------- \n\t";
        }
        s+="| END COL";
        //s = s + "   1   2   3   4   5   6   7\n";
        return s;
     }
     //Muestra los nodos en los que se han generado molino
     public String showMolinos(){
     	String s="";
     	for (int i=0;i <molinos.size() ;i++ ) {
     		s+=molinos.get(i);
     	}
     	return s;
     }

}