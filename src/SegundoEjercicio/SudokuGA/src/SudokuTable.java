import java.util.HashSet;
import org.jgap.*;

import java.util.Set;

class SudokuTable {

	private int[][] tablero;
	
	public int[][] getTablero(){
		return tablero;
	}

	//CREA UN TABLERO DE SUDOKU APARTIR DE UNA LISTA
	public SudokuTable(int[] list){
		tablero = new int[9][9];
		int x=0;
		for(int i=0;i<9;i++){
			for (int j=0;j<9;j++ ){
				tablero[i][j]=list[x];
				x++;
			}
		}
	}
	
	//RETORNO EL TABLERO EN FORMA DE ARREGLO
	public int[] toArray(int[][] t){
		int x = 0;
		int[] aux = new int[81];
		for(int i=0;i<9;i++){
			for (int j=0;j<9;j++ ){
				aux[x]=t[i][j];
				x++;
			}
		}
		return aux;
	}
	
	//RETORNA TRUE SI LOS ELEMENTOS DEL TABLERO PERTENECEN A [1,9]
	public boolean tableroCorrecto(){
		boolean correcto = true;
		for(int i=0;i<9;i++){
			for (int j=0;j<9;j++ ){
				if (tablero[i][j]==0){
					correcto = false;
					return correcto;
				}
			}
		}
		return correcto;
	}
	
	//RETORNA LA CANTIDAD DE ELEMENTOS DISTINTOS EN UN TABLERO (por fila)
	public int checkFila(){
		int contDistintos = 0;
		Set<Integer> valores = new HashSet<Integer>();
		for(int i=0;i<9;i++){
			valores.clear();
			for (int j=0;j<9;j++ ){
				if(!(tablero[i][j]==0))valores.add(tablero[i][j]);
			}
			contDistintos += valores.size();
		}
		return contDistintos;
	}
	
	//RETORNA LA CANTIDAD DE ELEMENTOS DISTINTOS EN UN TABLERO (por columna)
	public int checkColumna(){
		int contDistintos = 0;
		Set<Integer> valores = new HashSet<Integer>();
		for(int i=0;i<9;i++){
			valores.clear();
			for (int j=0;j<9;j++ ){
				if(!(tablero[j][i]==0))valores.add(tablero[j][i]);
			}
			contDistintos += valores.size();
		}
		return contDistintos;
	}
	
	//RETORNA LA CANTIDAD DE ELEMENTOS DISTINTOS EN UNA MATRIZ INTERNA
	public int checkMatrizI(int x,int y){
		int contDistintos = 0;
		Set<Integer> valores = new HashSet<Integer>();
		for(int i=x;i<(x+3);i++){
			valores.clear();
			for (int j=y;j<(y+3);j++ ){
				if(!(tablero[i][j]==0))valores.add(tablero[i][j]);
			}
			contDistintos += valores.size();
		}
		return contDistintos;
	}
	
	public int checkMatriz(){
		int contDistintos = 0;
		contDistintos += checkMatrizI(0,0);
		contDistintos += checkMatrizI(0,3);
		contDistintos += checkMatrizI(0,6);
		contDistintos += checkMatrizI(0,3);
		contDistintos += checkMatrizI(3,3);
		contDistintos += checkMatrizI(6,3);
		contDistintos += checkMatrizI(0,6);
		contDistintos += checkMatrizI(3,6);
		contDistintos += checkMatrizI(6,6);
		return contDistintos;
	}

	public String toString() {
        String s = "\n\t  -----------------------------------\n\t";
        for (int f = 0; f < 9; f++) {
            for (int c = 0; c < 9; c++) {
               s = s +" | "+tablero[f][c];
            }
            s = s +" | \n\t  -----------------------------------\n\t";
        }
        s = s + "   0   1   2   3   4   5   6   7   8  \n";
        return s;
    }
}