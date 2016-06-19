/**
* Clase : Tablero 
* Representacion interna del tablero
*
**/
public class Tablero{
	private int[][] tab;
	private int fila;
	private int col;
	private int[][] ady;
		
	public Tablero(int f, int c){
		fila=f;
		col=c;
		tab=new int[fila][col];
		for (int i=0; i < fila ;i++ ) {
			for (int j=0;j <col ; j++) {
				tab[i][j]=0;
			}
		}
		//Carga de posiciones Invalidas del tablero.
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
	
	public int[][] getTab(){
		return tab;
	}
	public void setTab(int f,int c, int v){
		tab[f][c]=v;
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
     	String s="\n\t"+tab[0][0]+"-------------------"+tab[0][3]+"---------------------"+tab[0][6]+"\n\t";
     	s+="\n\t|    		    | 	     		  |\n\t";
     	s+="\n\t|        "+tab[1][1]+"----------"+tab[1][3]+"------------"+tab[1][5]+"        | \n\t";
     	s+="\n\t|     	 |	    |		 |	  |\n\t";
     	s+="\n\t|        |     "+tab[2][2]+"----"+tab[2][3]+"----"+tab[2][4]+"	 |	  |	\n\t";
     	s+="\n\t|        |     |         |       |        |  \n\t";
     	s+="\n\t"+tab[3][0]+"--------"+tab[3][1]+"-----"+tab[3][2]+"         "+tab[3][4]+"-------"+tab[3][5]+"--------"+tab[3][6]+" \n\t";
     	s+="\n\t|        |     |         |       |        |  \n\t";
     	s+="\n\t|        |     "+tab[4][2]+"----"+tab[4][3]+"----"+tab[4][4]+"       |        |\n\t";
     	s+="\n\t|     	 |	    |		 |	  |\n\t";
     	s+="\n\t|        "+tab[5][1]+"----------"+tab[5][3]+"------------"+tab[5][5]+"        | \n\t";
     	s+="\n\t|    		    | 	     		  |\n\t";
     	s+="\n\t"+tab[6][0]+"-------------------"+tab[6][3]+"---------------------"+tab[6][6]+"\n\t";
     	
     	return s;
     }

} 