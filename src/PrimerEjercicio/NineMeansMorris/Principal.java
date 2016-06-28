/**
* Clase : Principal 
* Contiene el main del juego
* Ejecutando esta clase, Se comienza a jugar al Nine Mens Morris
* @author: Delle Vedove Mauricio, Rondeau Matias
* @version 1.0  
**/
import java.util.Scanner;


public class Principal{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("\n\tSe recomienda Maximizar su Terminal\n\t ");
		System.out.println("\n\tLuego presione Enter para comenzar :\n\t");
		String option = in.nextLine();
		clearScr();

		JuegoDelMolino juego= new JuegoDelMolino();
		juego.partida();

	}
	//limpia pantalla : clear
	public static void clearScr(){
		for (int i=0; i<50 ; i++ ) {
			System.out.println("");	
		}
	}
}