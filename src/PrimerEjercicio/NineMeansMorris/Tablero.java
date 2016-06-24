/**
* Clase : Tablero 
* Representacion interna del tablero
*@author Delle Vedove Mauricio, Rondeau Matias
*@version 1.0
**/

public class Tablero{
	private int[][] tab; //representa el tablero del juego
	private int fila;//la cantidad de filas del juego
	private int col;//la cantidad de columnas del juego
	private final int MAX_FICHAS= 24; //la cantidad de fichas q se puede poner en el tablero.	
	//Setea desde afuera el tamaño q va a ser el tablero
	public Tablero(int f, int c){
		fila=f;//setea el tamaño de la fila
		col=c;//setea el tamaño de la columna
		tab=new int[fila][col];
		for (int i=0; i < fila ;i++ ) { //carga de posiciones vacias con 0
			for (int j=0;j <col ; j++) {
				tab[i][j]=0;
			}
		}
		//Carga de posiciones Invalidas del tablero con -1.
		tab[0][1]=-1;
		tab[0][2]=-1;
		tab[0][4]=-1;
		tab[0][5]=-1;
		
		tab[1][0]=-1;
		tab[1][2]=-1;
		tab[1][4]=-1;
		tab[1][6]=-1;
		
		tab[2][0]=-1;
		tab[2][1]=-1;
		tab[2][5]=-1;
		tab[2][6]=-1;
		
		tab[3][3]=-1;
		
		tab[4][0]=-1;
		tab[4][1]=-1;
		tab[4][5]=-1;
		tab[4][6]=-1;
		
		tab[5][0]=-1;
		tab[5][2]=-1;
		tab[5][4]=-1;
		tab[5][6]=-1;
		
		tab[6][1]=-1;
		tab[6][2]=-1;
		tab[6][4]=-1;
		tab[6][5]=-1;
	}
	//retorna el tablero de la clase.
	public int[][] getTab(){
		return tab;
	}
	//setea posiciones del tablero, dado una fila, una col y un valor
	public void setTab(int f,int c, int v){
		tab[f][c]=v;
	}
	//retorna la constante q determina la cantidad de fichas
	//q se pueden poner
	public int sizeTablero(){
		return MAX_FICHAS;
	}


	//Dada un arreglo conteniendo que fichas tienen
	//Setea la matriz local para actualizar que jugadas
	//tiene en cada posicion valida y quien esta en la posicion.
	//1 representa a jug1
	//2 representa a jug2.
	public void refreshTab(Vecinos vec){
		int[] jugadas= vec.getFichasJug();
		int i=0;
		for (int f=0;f < fila ;f++ ) {
			for (int c=0;c <col ;c++ ) {
				if(tab[f][c] == 0 ){
					tab[f][c]=jugadas[i];
		
					i++;
				}
			}
		}

	}


	//toString de la clase.
	 public String toString() {
        String s = "\n\t  ---------------------------------- \n\t";
        for (int f = 0; f < fila; f++) {
            for (int c = 0; c < col; c++) {
               s = s + " | "+tab[f][c];
            }
            s = s + " | \n\t  --------------------------------- \n\t";
        }
        s = s + "   0   1   2   3   4   5   6\n";
        return s;
     }
     /*
		toString() : Representacion de la estructura del juego con las posiciones ocupadas por
		jugador 1= 1 o jugador 2=2 o 0 correspondiente a vacio.
     */
     public String toString2(){
     	String s="\n\t                            ------------- NINE MENS MORRIS ----------- \n\t";
     	s+="\n\t ||| JUGADOR 1 = 1 vs JUGADOR 2 = 2 (PC) ||| CASILLAS DISPONIBLES=0 ||| CASILLAS EXISTENTES= de 00 a 23 |||\n\t";
     	s+="\n\t \n\t";
     	s+="\n\t((00)) "+tab[0][0]+ " ---------------------------------- ((01)) "+tab[0][3]+ " ------------------------------------ ((02)) "+tab[0][6]+"\n\t";
     	s+="\n\t|           		                          |       		                       |\n\t";
     	s+="\n\t|            ((03)) "+tab[1][1]+" ----------------------- ((04)) "+tab[1][3]+" -------------------------((05)) "+tab[1][5]+"       | \n\t";
     	s+="\n\t|     	       |	                          |		                  |            |\n\t";
     	s+="\n\t|              |     ((06)) "+tab[2][2]+" -------------- ((07)) "+tab[2][3]+" --------------- ((08)) "+tab[2][4]+"    | 	       |	\n\t";
     	s+="\n\t|      	       |         |                                               |        |            |  \n\t";
     	s+="\n\t((09)) "+tab[3][0]+" - ((10)) "+tab[3][1]+" - ((11)) "+tab[3][2]+"                                        ((12)) "+tab[3][4]+" - ((13)) "+tab[3][5]+" - ((14)) "+tab[3][6]+" \n\t";
     	s+="\n\t|              |         |                                               |        |            |  \n\t";
     	s+="\n\t|              |     ((15)) "+tab[4][2]+" -------------- ((16)) "+tab[4][3]+" --------------- ((17)) "+tab[4][4]+"    |            |\n\t";
     	s+="\n\t|     	       |	                          |                               |            |\n\t";
     	s+="\n\t|            ((18)) "+tab[5][1]+" ---------------------- ((19)) "+tab[5][3]+" ------------------------ ((20)) "+tab[5][5]+"        | \n\t";
     	s+="\n\t|    		                                  |                                            |\n\t";
     	s+="\n\t((21)) "+tab[6][0]+" ----------------------------------- ((22)) "+tab[6][3]+" ----------------------------------- ((23)) "+tab[6][6]+" \n\t";
     	
     	return s;
     }

} 