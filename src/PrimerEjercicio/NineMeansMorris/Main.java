import java.io.*;
import java.util.*;


public class Main{
	public static void main(String[] args) {
		Vecinos a= new Vecinos(24,24); //crea lista de adyacencias
		a.cargaVecino();//carga la lista de adyancencias
		String s=a.toString();
		System.out.println(s);//muestra lista de ady

		Tablero tab= new Tablero(7,7); //crea un nuevo tablero 
		
		a.setFicha(1,0);//jugada del 1 en la posicion 3
		a.setFicha(1,1);//juega el 1 en la posicion 4
		a.setFicha(1,2);//
		//a.setFicha(1,13);
		//a.setFicha(1,20);

		boolean molino=a.esMolino(2,1);
		
		System.out.println("");
		System.out.println("VALOR MOLINO "+molino);
		
		System.out.println("");
		a.setFicha(2,7);//jugada del player 2 en posicion 7

		tab.refreshTab(a);//actualiza las jugadas 
		System.out.println(tab.toString2());
		List<Pair<Integer,Integer>> posiblesMovimientos = a.posibleMov(2);
		System.out.println("posiblesMovimientos "+posiblesMovimientos.toString());
	}
}