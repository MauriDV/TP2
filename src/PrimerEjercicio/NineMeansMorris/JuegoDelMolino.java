import java.util.Scanner;

public class JuegoDelMolino{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		ProblemaMolino problem = new ProblemaMolino();
		MinMaxEngine<ProblemaMolino,EstadoMolino> engine =new MinMaxEngine<ProblemaMolino,EstadoMolino>(problem,4);
		
		System.out.println("\n\tESTADO INICIAL\n\t");
		System.out.println(problem.initialState().toString());

		//como jugador 1, creo juego, seteo
		//EstadoMolino player1 = new EstadoMolino();
		Tablero board = new Tablero(7,7); //creo un tablero
		Vecinos vecino = new Vecinos(24,24);//creo la lista de adyacencias
		System.out.println("\n\t -------- PRESIONE ENTER PARA CONTINUAR ----------\n\t ");
		String option = in.nextLine();

		System.out.println("\n\t------------ COMIENZO DEL JUEGO -------------------\n\t");
		

		while( problem.end(problem.initialState() ) ){
			if (problem.initialState().isMax()){//Si es el jugador 1=Humano
				System.out.println("\n\tElija una posicion de 00 a 23 NO OCUPADA y presione ENTER \n\t");
				Integer jugada = in.nextInt(); //Lectura de datos desde terminal de la jugada
				//CONTROLAR ACA SI NO ES UN NODO EXISTENTE Pedir uno hasta q lo sea.
				//jugar en el juego y setear un nuevo estado a partir de lo jugado.
				while(!correctEntrada(jugada.intValue())){
					System.out.println("\n\t Elija una posicion de 00 a 23 NO OCUPADA y presione ENTER\n\t");
					jugada=in.nextInt();
				}
				System.out.println("SALI DE SELECCION");
				vecino.setFicha(1,jugada.intValue());
				board.refreshTab(vecino);
				System.out.println( board.toString2());
				if(vecino.esMolino(jugada.intValue(),1)){//Verificar si hizo molino el humano
					System.out.println("\n\t HIZO MOLINO, PUEDE BORRAR UNA FICHA DE SU CONTRARIO\n\t");
					System.out.println("\n\tELIJA LA FICHA QUE DESEA BORRAR :\n\t");	
					Integer fichaElim = in.nextInt();
					//VERIFICAR ACA SI ES UNA FICHA VALIDA A BORRAR
					vecino.borraFicha(fichaElim.intValue());
					board.refreshTab(vecino);
				}
				//ACA GENERO EL ESTADO LUEGO DE JUGAR EL HUMANO
				
				
			}
			else{//Si le toca jugar a jugador 2= Pc

			}
			
		}
	}

	//Verificacion de datos de entrada
	public static boolean correctEntrada(int valor){
		if (valor==0) return true;
		if (valor==1) return true;
		if (valor==2) return true;
		if (valor==3) return true;
		if (valor==4) return true;
		if (valor==5) return true;
		if (valor==6) return true;
		if (valor==7) return true;
		if (valor==8) return true;
		if (valor==9) return true;
		if (valor==10) return true;
		if (valor==11) return true;
		if (valor==12) return true;
		if (valor==13) return true;
		if (valor==14) return true;
		if (valor==15) return true;
		if (valor==16) return true;
		if (valor==17) return true;
		if (valor==18) return true;
		if (valor==19) return true;
		if (valor==20) return true;
		if (valor==21) return true;
		if (valor==22) return true;
		if (valor==23) return true;
		return false;
	}

}