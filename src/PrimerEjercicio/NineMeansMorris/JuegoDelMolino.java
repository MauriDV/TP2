import java.util.Scanner;
import java.util.*;

public class JuegoDelMolino{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		ProblemaMolino problem = new ProblemaMolino();
		MinMaxABEngine<ProblemaMolino,EstadoMolino> engine =new MinMaxABEngine<ProblemaMolino,EstadoMolino>(problem,1);
	
		System.out.println("\n\tESTADO INICIAL\n\t");
		System.out.println(problem.initialState().toString());
		System.out.println("\n\t ****************************************************************************************************\n\t");

		Tablero board = new Tablero(7,7); //creo un tablero
		Vecinos vecino = new Vecinos(24,24);//creo la lista de adyacencias
		vecino.cargaVecino();//carga de adyacencias
		
		System.out.println("\n\t -------- PRESIONE ENTER PARA CONTINUAR ----------\n\t ");
		String option = in.nextLine();

		System.out.println("\n\t------------ COMIENZO DEL JUEGO -------------------\n\t");
		boolean salir=false;
		while( ! (problem.end( problem.initialState()) && (!salir) ) ){
			
			if (problem.initialState().getCantFichas()<18){//Juego por Insercion de fichas
				System.out.println("CANTIDAD DE FICHAS "+problem.initialState().getCantFichas());
				if ( problem.initialState().isMax() ){//Si es el jugador 1=Humano  
					System.out.println("Es Max? == "+problem.initialState().isMax());
					//----------------------------------------------------------------------------
					System.out.println("\n\t **************** JUEGA JUGADOR 1 ****************\n\t");
					System.out.println("\n\tElija una posicion de 00 a 23 NO OCUPADA y presione ENTER \n\t");
					Integer jugada = in.nextInt(); //Lectura de datos desde terminal de la jugada
					//SI NO ES UN NODO EXISTENTE Pedir uno hasta q lo sea.				
					while(!correctEntrada(jugada.intValue())){
						System.out.println("\n\t Elija una posicion de 00 a 23 NO OCUPADA y presione ENTER\n\t");
						jugada=in.nextInt();
					}
					//-------------------------------------------------------------------------------	
					//jugar en el juego y setear un nuevo estado a partir de lo jugado.
					
					vecino.setFicha(1,jugada.intValue());
					
					board.refreshTab(vecino);
					System.out.println( board.toString2());//Muestra jugada hecha
					System.out.println("Cantidad de fichas en jugador uno, pero VEcino "+vecino.cantFichas());
					//-------------------------------------------------------------------------------
					
					if(vecino.esMolino(jugada.intValue(),1)){//Verificar si hizo molino el humano
						System.out.println("\n\t HIZO MOLINO, PUEDE BORRAR UNA FICHA DE SU CONTRARIO\n\t");
						System.out.println("\n\tELIJA LA FICHA QUE DESEA BORRAR :\n\t");	
						Integer fichaElim = in.nextInt();
						List<Integer> fichasContrarias = vecino.dondeColoco(2);
						//verificacion de fichas validas a borrar
						while(!fichasContrarias.contains(fichaElim)){
							System.out.println("\n\tELIJA UNA FICHA VALIDA QUE DESEA BORRAR :\n\t");
							fichaElim=in.nextInt();
						}
						vecino.borraFicha(fichaElim.intValue());
						board.refreshTab(vecino);
						System.out.println("Luego de borrar ficha, cantFichas segun vecino en player 1 "+vecino.cantFichas());
						//generar el estado por insercion y borrado de ficha
						EstadoMolino humanState = new EstadoMolino(1,vecino,board,false,problem.initialState()); //creo un nuevo estado
						problem= new ProblemaMolino(humanState); //reseteo el estado inicial.
					}
					//-------------------------------------------------------------------------------
					else{
						//generar el estado solo por insercion sin molino
						EstadoMolino humanState= new EstadoMolino(1,vecino,board,false,problem.initialState());
						problem= new ProblemaMolino (humanState);//seteo el problema nuevo	
						System.out.println("Fin DE CRECION DE HUMAN STATE Y FICHAS= "+humanState.getCantFichas());

					}
					System.out.println("CANTIDAD DE FICHAS luego de jugar HUmano "+problem.initialState().getCantFichas());
				}
				else{//Si le toca jugar a jugador 2= Pc
					//backup para solo ver q tienen
					
					//--------------------------------------------------------------------------------------
					
					System.out.println("\n\t **** JUEGA PC : Loading ... *****\n\t");
					
					EstadoMolino pcState= new EstadoMolino();
					pcState= engine.computeSuccessor(problem.initialState().clonarEstado());
					problem= new ProblemaMolino(pcState);
					
					vecino=problem.initialState().getVecino();
					board= problem.initialState().getTablero();
					System.out.println("CANTIDAD DE FICHAS luego de jugar PC "+problem.initialState().getCantFichas());	
				
				}			
			}
			else{
				System.out.println("******************************* MOVIMIENTOS DE FICHAS ************************ ");
				System.out.println("CHAU !!!");
				salir=true;
				break;
			}

			/*else{//COMIENZA JUEGO POR MOVIMIENTOS !!!
				System.out.println("\n\t************COMIENZAN LOS MOVIMIENTOS*****************\n\t");
				if (player==1){//MOVIMIENTOS DEL PLAYER 1
					System.out.println(board.toString2());
					System.out.println("\n\tMovimientos disponibles para player \n\t"+player);
					List<Pair<Integer,Integer>> movim=vecino.posibleMov(player);//(ficha,destinos)
					System.out.println(movim.toString());
					
					List<Integer> colocoF=vecino.dondeColoco(player);				
					
					Integer pos=100;
					//corroboracion de ficha valida y posicion valida.
					boolean ok= false;
					while  (!ok){
						if (colocoF.contains(pos)) {
							ok=true;
						}else{ 
							System.out.println("\n\tELIJA UNA FICHA VALIDA PARA MOVER\n\t");
							pos= in.nextInt();
						}
					}
					
					//posicion valida
					//si es ficha valida, corroborar donde quiere mover sea valido
					//y q sea segunda componente de la ficha

					boolean fichaValida=false;				
					Integer pos2=0;
					while(!fichaValida){
						System.out.println("\n\tELIJA UNA POSICION VALIDA HACIA DONDE MOVER:\n\t");
						pos2= in.nextInt();
						for (int i=0; i < movim.size() ; i++) {
							if(pos2.intValue() ==movim.get(i).getSnd().intValue() ){
								if(movim.get(i).getFst().intValue()==pos.intValue()){//que la fichavieja pos tenga como destino la nueva pos2
									fichaValida=true;
								}
							}
						}
					}
					//elimino antigua 
					vecino.borraFicha(pos.intValue());
					//y agrego a nueva posicion
					vecino.setFicha(player,pos2.intValue());
					board.refreshTab(vecino);
					System.out.println(board.toString2());
					System.out.println("\n\t MOVIO (("+pos+")) HACIA (("+pos2+"))\n\t");
					//verifico si hizo molino en esa posicion, permite borrar una
					if(vecino.esMolino(pos2.intValue(),player)){//Verificar si hizo molino el humano
						System.out.println("\n\t HIZO MOLINO, PUEDE BORRAR UNA FICHA DE SU CONTRARIO\n\t");
						System.out.println("\n\tELIJA LA FICHA QUE DESEA BORRAR :\n\t");	
						Integer fichaElim = in.nextInt();
						List<Integer> fichasContrarias = vecino.dondeColoco(2);
						//VERIFICAR ACA SI ES UNA FICHA VALIDA A BORRAR
						while(!fichasContrarias.contains(fichaElim)){
							System.out.println("\n\tELIJA UNA FICHA VALIDA QUE DESEA BORRAR :\n\t");
							fichaElim=in.nextInt();
						}						
						vecino.borraFicha(fichaElim.intValue());
						board.refreshTab(vecino);
					}
					System.out.println(board.toString2());
					player=2;	
				}
				else{//MOVIMIENTOS DEL PLAYER 2
					System.out.println(board.toString2());
					System.out.println("\n\tMovimientos disponibles para player \n\t"+player);
					List<Pair<Integer,Integer>> movim=vecino.posibleMov(player);//(ficha,destinos)
					System.out.println(movim.toString());
					
					List<Integer> colocoF=vecino.dondeColoco(player);				
					
					Integer pos=100;
					//corroboracion de ficha valida y posicion valida.
					boolean ok= false;
					while  (!ok){
						if (colocoF.contains(pos)) {
							ok=true;
						}else{ 
							System.out.println("\n\tELIJA UNA FICHA VALIDA PARA MOVER\n\t");
							pos= in.nextInt();
						}
					}
					
					//posicion valida
					//si es ficha valida, corroborar donde quiere mover sea valido
					boolean fichaValida=false;				
					Integer pos2=0;
					while(!fichaValida){
						System.out.println("\n\tELIJA UNA POSICION VALIDA HACIA DONDE MOVER:\n\t");
						pos2= in.nextInt();
						for (int i=0; i < movim.size() ; i++) {
							if(pos2.intValue() ==movim.get(i).getSnd().intValue() ){
								if(movim.get(i).getFst().intValue()==pos.intValue()){//que la fichavieja pos tenga como destino la nueva pos2
									fichaValida=true;
								}
							}
						}
					}
					//elimino antigua 
					vecino.borraFicha(pos.intValue());
					//y agrego a nueva posicion
					vecino.setFicha(player,pos2.intValue());
					board.refreshTab(vecino);
					System.out.println(board.toString2());
					System.out.println("\n\t MOVIO (("+pos+")) HACIA (("+pos2+"))\n\t");
					//verifico si hizo molino en esa posicion, permite borrar una
					if(vecino.esMolino(pos2.intValue(),player)){//Verificar si hizo molino el humano
						System.out.println("\n\t HIZO MOLINO, PUEDE BORRAR UNA FICHA DE SU CONTRARIO\n\t");
						System.out.println("\n\tELIJA LA FICHA QUE DESEA BORRAR :\n\t");	
						Integer fichaElim = in.nextInt();
						List<Integer> fichasContrarias = vecino.dondeColoco(1);
						//VERIFICAR ACA SI ES UNA FICHA VALIDA A BORRAR
						while(!fichasContrarias.contains(fichaElim)){
							System.out.println("\n\tELIJA UNA FICHA VALIDA QUE DESEA BORRAR :\n\t");
							fichaElim=in.nextInt();
						}						
						vecino.borraFicha(fichaElim.intValue());
						board.refreshTab(vecino);
					}
					System.out.println(board.toString2());
					player=1;
				}
			}*/

		}//FIN DE WHILE PRINCIPAL
		
		if (problem.end(problem.initialState()  ) ){

    		System.out.println("\n\t ------------- ********* GANADOR PLAYER : "+problem.initialState().whosWin()+"\n\t");
		}	
	}

	//Verificacion de datos de entrada
	public static boolean correctEntrada(int valor){
		if (0<=valor && valor<=23) return true;
		return false;
	}

}