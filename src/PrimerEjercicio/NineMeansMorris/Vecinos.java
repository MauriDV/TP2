/**
* Clase : Vecinos implementa una clase que contiene una matriz de adyancencias
* para vecinos de un nodo.
*@author Delle Vedove Mauricio, Rondeau Matias
*@version 1.0
**/


public class Vecinos {
	private int[][] adyacencia;
	private int fila; //24 x 24
	private int col;
	private int[] fichaJug; 
	//Constructor de la clase parametrizado, seteo desde afuera la cantidad de
	//filas y de columnas
	public Vecinos(int f, int c){
		fila=f;
		col=c;
		adyacencia= new int[fila][col];
		//carga de ceros
		for (int fi=0;fi < fila ;fi++ ) {
			for (int co=0;co <col ; co++) {
				adyacencia[fi][co]=0;	
			}
		}
		//Inicializacion de las fichas en cada fila.
		//parte de que no hay ninguna ficha
		fichaJug= new int[fila];
		for (int i=0;i <fila ;i++ ) {
			fichaJug[i]=0;
		}
	}	
	//Carga la matriz de adyacencias correspondiente a las relaciones
	//que existen de "vecindad"
	public void cargaVecino(){
		//1 para adyancentes horizontales
		//2 para adyacentes verticales
		adyacencia[0][1]=1;
		adyacencia[0][9]=2;
		
		adyacencia[1][0]=1;
		adyacencia[1][2]=1;
		adyacencia[1][4]=2;
		
		adyacencia[2][1]=1;
		adyacencia[2][14]=2;
		
		adyacencia[3][10]=2;
		adyacencia[3][4]=1;
		
		adyacencia[4][1]=2;
		adyacencia[4][3]=1;
		adyacencia[4][5]=1;
		adyacencia[4][7]=2;
		
		adyacencia[5][4]=1;
		adyacencia[5][13]=2;
		
		adyacencia[6][7]=1;
		adyacencia[6][11]=2;
		
		adyacencia[7][4]=2;
		adyacencia[7][6]=1;
		adyacencia[7][8]=1;
		
		adyacencia[8][7]=1;
		adyacencia[8][12]=2;
		
		adyacencia[9][0]=2;
		adyacencia[9][10]=1;
		adyacencia[9][21]=2;
		
		adyacencia[10][3]=2;
		adyacencia[10][9]=1;
		adyacencia[10][11]=1;
		adyacencia[10][18]=2;
		
		adyacencia[11][6]=2;
		adyacencia[11][10]=1;
		adyacencia[11][15]=2;
		
		adyacencia[12][8]=2;
		adyacencia[12][13]=1;
		adyacencia[12][17]=2;
		
		adyacencia[13][5]=2;
		adyacencia[13][12]=1;
		adyacencia[13][14]=1;
		adyacencia[13][20]=2;
		
		adyacencia[14][2]=2;
		adyacencia[14][13]=1;
		adyacencia[14][23]=2;
		
		adyacencia[15][11]=2;
		adyacencia[15][16]=1;
		
		adyacencia[16][15]=1;
		adyacencia[16][19]=2;
		adyacencia[16][17]=1;
		
		adyacencia[17][12]=2;
		adyacencia[17][16]=1;
		
		adyacencia[18][10]=2;
		adyacencia[18][19]=1;
		
		adyacencia[19][16]=2;
		adyacencia[19][18]=1;
		adyacencia[19][20]=1;
		adyacencia[19][22]=2;

		adyacencia[20][13]=2;
		adyacencia[20][19]=1;
		
		adyacencia[21][9]=2;
		adyacencia[21][22]=1;
		
		adyacencia[22][19]=2;
		adyacencia[22][21]=1;
		adyacencia[22][23]=1;
		
		adyacencia[23][14]=2;
		adyacencia[23][22]=1;
	
		
	}
	//Setea que ficha va en dicha posicion.
	public void setFicha(int ficha, int pos){
		fichaJug[pos]=ficha;
	}
	//Retorna el arreglo que contiene que fichas se jugaron en cada posicion
	//ej: 0 1 2 3 4 5 6 7 ...
	//    1 2 1 1 1 2 1 2 ...
	//cada posicion representa un nodo del juego. 
	public int[] getFichasJug(){
		return fichaJug;
	}
	//Retorna la matriz de adyancencias
	public int[][] getAdyacencias(){
		return adyacencia; 
	}
	//le paso el tipo de ficha y miro las mismas a ella.
	//public boolean esMolino(int nodo){
		// List<Integer> visitados = new LinkedList<Integer>();
		// for (int i=0; i < fila ; i++) {
		// 	if (fichaJug[i] == ficha){
		// 		for (int c=0; c < col ; c++ ) {
					
		// 			if (adyacencia[i][c]==1){
		// 				//Busqueda de MOlino Horizontal
		// 				visitados.add(i);


		// 			}
		// 			if (adyacencia[i][c]==2){
		// 				//Busqueda de MOLINO vertical.

		// 			}						
				

		// 		}

		// 	}
		// }


		return false;
	}

	//toString de la clase.
	 public String toString() {
        String s ="";// "\n\t  ---------------------------- \n\t";
        for (int f = 0; f < fila; f++) {
            for (int c = 0; c < col; c++) {
               s = s + " | "+adyacencia[f][c];
            }
            //s = s + " | \n\t  ---------------------------- \n\t";
        }
        //s = s + "   1   2   3   4   5   6   7\n";
        return s;
     }


}