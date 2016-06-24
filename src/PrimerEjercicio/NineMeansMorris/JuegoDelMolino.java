import java.util.Scanner;
import java.util.*;

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
		

		while( ! problem.end(problem.initialState() ) ){
			if (problem.initialState().isMax()){//Si es el jugador 1=Humano
			//POR INSERCION, FALTA POR MOVIMIENTO !!!
				System.out.println("\n\tElija una posicion de 00 a 23 NO OCUPADA y presione ENTER \n\t");
				Integer jugada = in.nextInt(); //Lectura de datos desde terminal de la jugada
				//SI NO ES UN NODO EXISTENTE Pedir uno hasta q lo sea.				
				while(!correctEntrada(jugada.intValue())){
					System.out.println("\n\t Elija una posicion de 00 a 23 NO OCUPADA y presione ENTER\n\t");
					jugada=in.nextInt();
				}
				System.out.println("\n\tPASE JUGADA HUMANO\n\t");
				//jugar en el juego y setear un nuevo estado a partir de lo jugado.
				vecino.setFicha(1,jugada.intValue());
				board.refreshTab(vecino);
				System.out.println( board.toString2());//Muestra jugada hecha
				if(vecino.esMolino(jugada.intValue(),1)){//Verificar si hizo molino el humano
					System.out.println("\n\t HIZO MOLINO, PUEDE BORRAR UNA FICHA DE SU CONTRARIO\n\t");
					System.out.println("\n\tELIJA LA FICHA QUE DESEA BORRAR :\n\t");	
					Integer fichaElim = in.nextInt();
					List<Integer> fichasContrarias = vecino.dondeColoco(2);
					while(!fichasContrarias.contains(fichaElim)){
						System.out.println("\n\tELIJA UNA FICHA VALIDA QUE DESEA BORRAR :\n\t");
						fichaElim=in.nextInt();
					}
					//VERIFICAR ACA SI ES UNA FICHA VALIDA A BORRAR
					vecino.borraFicha(fichaElim.intValue());
					board.refreshTab(vecino);
					System.out.println("CANTIDAD DE FICHAS JUGANDO 1 "+vecino.cantFichas());
					//generar el estado por insercion y borrado de ficha
					EstadoMolino humanState = new EstadoMolino(); //creo un nuevo estado
					humanState.setEstadoMolino(1,vecino,board,false,problem.initialState().getCantFichas(),problem.initialState()); //seteo actualizaciones
					problem= new ProblemaMolino(humanState); //reseteo el estado inicial.
				}
				else{
					//generar el estado solo por insercion sin molino
					EstadoMolino humanState= new EstadoMolino();//NO PUEDO CREAR SIN TENER ESTE CONSTRUCTOR ANTES
					humanState.setEstadoMolino(1,vecino,board,false,problem.initialState().getNumFichas(),problem.initialState());
					problem= new ProblemaMolino (humanState);//seteo el estado nuevo	
				}
			}
			else{//Si le toca jugar a jugador 2= Pc
				System.out.println("\n\tJuega Computadora: Loading...\n\t");
				System.out.println("PASE PPOR PC");
				//System.out.println( problem.initialState().toString() );
				System.out.println("CREA NUEVO PROBLEMA ABAJO");
				problem= new ProblemaMolino(engine.computeSuccessor( problem.initialState() ));
				//System.out.println("");
			 	//System.out.println(problem.initialState().toString());
			 	//System.out.println("FIN DE PC JUGADA");
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