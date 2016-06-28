import java.io.*;
import java.util.*;
/**
* Clase : ProblemaMolino implementa los metodos abstractos de la interface
* AdversarySearchProblem, para la representacion de un problema
* del juego NineMeansMorris.
*
* @author: Delle Vedove Mauricio, Rondeau Matias
* @version 1.0  
**/


public class ProblemaMolino implements AdversarySearchProblem<EstadoMolino>{
        //instancia de la clase EstadoMolino
        protected EstadoMolino inicial;
        //Constructor de la clase
        public ProblemaMolino() {
            inicial = new EstadoMolino();
        }
        //Constructor de la clase con parametro
        public ProblemaMolino(EstadoMolino inicial) {
            this.inicial = inicial;
        }
        //Retorna el estado inicial.
        public EstadoMolino initialState() {
            return inicial;
        }
       
        /*Dado un EstadoMolino, obtener los estados sucesores.
        Los estados sucesores se obtienen de dos formas:
            -Si la cantidad de fichas es menor a 18 : Entonces se obtienen 
                sucesores de colocar fichas en el tablero.
            -Si la cantidad de fichas es 18, entonces se obtienen sucesores
                con respecto a realizar movimientos en el tablero.    

        */
        public List<EstadoMolino> getSuccessors(EstadoMolino s) {
            List<EstadoMolino> successors = new LinkedList<EstadoMolino>();
            //si la cantidad de fichas es menor a 18: colocar fichas en tablero.
           
            EstadoMolino s1= (EstadoMolino) s; //backup de s.
            
            Tablero tab= s1.getTablero();
            Vecinos vecino=s1.getVecino();
            int numFichas=s1.getCantFichas();
                
            if (s1.getCantFichas()<18){//COLOCACION DE FICHAS       
                 
                if (s1.isMax()){//jugador 1.
                     
                    List<Integer> fichasColocadas2 = s1.dondeColoco(2);//donde coloco el 2    
                    List<Integer> posiciones=s1.lugaresDisp();//donde se puede colocar fichas       
                    for (int i=0; i < posiciones.size() ; i++) {
                        EstadoMolino succ= new EstadoMolino(1,posiciones.get(i).intValue(),vecino,tab,s1);//genero un nuevo estado y seteo valores
                        
                        if (succ.esMolino()){ //entonces generar otra tipo de estado.
                        //permito borrar una ficha de su contrario
                        //le asigno false a molino porque ya deja de ser molino.
                           for (int k=0;k < fichasColocadas2.size() ; k++ ) {
                                int posABorrar= fichasColocadas2.get(k).intValue();
                                EstadoMolino aux= new EstadoMolino(1,posiciones.get(i).intValue(),posABorrar,s1.getVecino(),s1.getTablero(),false,s1);//creo y hago backup con actualizaciones
                                successors.add(aux);//agrego a la lista de sucesores    
                            }                        
                        }
                        else{//si no es molino, agrego a la lista simplemente.
                            //System.out.println("No fue Molino : Solo agrego a la lista");
                            
                            successors.add(succ);

                        }
                    }
                    posiciones.clear(); //Por las dudas
                    fichasColocadas2.clear();    
                }
                else{ //jugador 2 : PC
                    System.out.println("s is Max? "+s.isMax());
                    //EstadoMolino s1=s.clonarEstado(); 

                    List<Integer> fichasColocadas1 = s1.dondeColoco(1);//donde coloco el 1    
                    List<Integer> posiciones=s1.lugaresDisp();//donde se puede colocar fichas       
                    for (int i=0; i < posiciones.size() ; i++) {
                        EstadoMolino succ= new EstadoMolino(2,posiciones.get(i).intValue(),vecino,tab,s1);    
                        //System.out.println("************Seteo del Estado************** "+succ.toString());
                        if (succ.esMolino()){ //entonces generar otra tipo de estado.
                           // System.out.println("Entre porq es Molino : NO HAGO NADA");
                            
                        //permito borrar una ficha de su contrario
                        //le asigno false a molino porque ya deja de ser molino.
                           for (int k=0;k < fichasColocadas1.size() ; k++ ) {
                                int posABorrar= fichasColocadas1.get(k).intValue();
                                
                               // System.out.println("**********Ficha Eliminada*********: "+posABorrar);
                                
                                EstadoMolino aux= new EstadoMolino(2,posiciones.get(i).intValue(),posABorrar,s1.getVecino(),s1.getTablero(),false,s1  );//creo y hago backup con actualizaciones
                                
                                successors.add(aux);//agrego a la lista de sucesores    
                              //  System.out.println("**********Estado creado a partir de esMolino************");
                               // System.out.println(aux.toString());
                               // System.out.println("***********************************************************");
                            }                        
                        }
                        else{//si no es molino, agrego a la lista simplemente.
                            successors.add(succ);
                            
                        }
                    }
                    posiciones.clear(); //Por las dudas
                    fichasColocadas1.clear();
                }
            } //Cierra IF si cantidadFichas < 18    
            else{
                //Si la cantidad es 19, obtener sucesores con respecto a realizar movidas de fichas.    
                if (s1.isMax()){//jugador1
                    List<Integer> fichasColocadas2 = s1.dondeColoco(2);
                    succMoverFichas(1,successors,fichasColocadas2,s1);
                    fichasColocadas2.clear();
                }
                else{//jugador 2
                    List<Integer> fichasColocadas1 = s1.dondeColoco(1);
                    succMoverFichas(2,successors,fichasColocadas1,s1);
                    fichasColocadas1.clear();
                }
            }
            //System.out.println("SUCESORES FINAL");
           // System.out.println(successors.toString());
            return successors;
        }


        
        /*
        //Metodo para obtener los sucesores de un estado por colocacion de fichas
        private List<EstadoMolino> succColocarFichas(int jugador,List<EstadoMolino> listSucc,List<Integer> dondeColoco, EstadoMolino suc,EstadoMolino backup ,EstadoMolino s){
            List<Integer> posiciones=s.lugaresDisp(); //guardo todos los lugares disponibles.
            System.out.println("Posiciones disponibles para jugador "+jugador+" : "+posiciones.toString());
            //generar un estado sucesor a partir de poner una ficha de Max en cada posicion
            //teniendo en cuenta que en cada insercion, puede generar molino y permita
            //borrarle una ficha de su contrario.
            //Vecinos vecino= s.getVecino();
            //Tablero tab= s.getTablero();
            //int cantFichas= s.getCantFichas();
            //EstadoMolino parent= s.getParent();
            //int player= s.getPlayer();
            //boolean molino=false;
            //Fin de backup
           
            for (int i=0; i < posiciones.size() ; i++) {
                System.out.println(" Sucesor "+posiciones.get(i));
              
                suc.setEstadoMolino2(jugador,posiciones.get(i).intValue(),s.getVecino(),s.getTablero(),s.getCantFichas(),s);
              
                System.out.println("Vecinos "+ s.getVecino().toString2()); 
                System.out.println("Es Molino? "+suc.esMolino());
                if (suc.esMolino()){ //entonces generar otra tipo de estado.
                    //permito borrar una ficha de su contrario
                    //le asigno false a molino porque ya deja de ser molino.
                    for (int k=0;k < dondeColoco.size() ; k++ ) {
                        int posABorrar= dondeColoco.get(k).intValue();
                        EstadoMolino aux= new EstadoMolino();//creo y hago backup con actualizaciones
                        aux.setEstadoMolino3(jugador,posiciones.get(i).intValue(),posABorrar,s.getVecino(),s.getTablero(),s.getCantFichas(),false,s  );
                        listSucc.add(aux);//agrego a la lista de sucesores    
                    }                        
                }
                else{//si no es molino, agrego a la lista simplemente.
                    System.out.println("No fue Molino : ");
                    //System.out.println("SUCESOR OBTENIDO COLOCAR FICHAS "+ suc.toString());
                    //System.out.println("-------------------------------------------------------------");
                    listSucc.add(suc);
                }
                s=backup.clonarEstado();
               // s.setPlayer(player);
                //s.setTablero(tab);
               // s.setVecino(vecino);
               // s.setCantFichas(cantFichas);
               // s.setParent(parent);
                //s.setMolino(molino);
                //System.out.println("VECINO DE PADRE "+s.getVecino().toString2());
                System.out.println("PADRE RESETEADO"+s.toString());
            }
            posiciones.clear(); //Por las dudas
            return listSucc;
        }*/

        //Metodo para obtener los sucesores de un estado por movimiento de fichas.
        private void succMoverFichas(int jugador,List<EstadoMolino> listSucc,List<Integer> dondeColoco,EstadoMolino s){
            //guardo la lista de (nodo,movimiento a que nodo);
            List<Pair<Integer,Integer>> movimientos = s.getPosiblesMov();
            for (int i=0; i < movimientos.size();i++ ) {
                //creo un par
                Pair<Integer,Integer> current = new Pair<Integer,Integer>(movimientos.get(i).getFst(),movimientos.get(i).getSnd());            
                //guardo primera y segunda componente del par
                //(nodo, adyacente disponible)
                int fst= current.getFst().intValue();
                int snd= current.getSnd().intValue();
                //creo un nuevo estado borrando el antiguo y moviendolo a su adyacente
                EstadoMolino suc = new EstadoMolino(jugador,fst,snd,s.getVecino(),s.getTablero(),s);
                if (suc.esMolino()){//si el estado generado es molino
                    //ACA BORRRAR UNA POR UNA LAS DEL CONTRARIO,Generando varios estados
                    for (int k=0;k <dondeColoco.size() ;k++ ) {
                        int posABorrar= dondeColoco.get(k).intValue();    
                        //generar un nuevo estado permitiendo borrar
                        EstadoMolino aux= new EstadoMolino(jugador,fst,snd,posABorrar,s.getVecino(),s.getTablero(),false,s);
                        listSucc.add(aux);
                    }
                }
                else{
                    //simplemente agrego el estado
                    listSucc.add(suc);
                }    
            }    
        } 
        //Indica si es estado final.
		public boolean end(EstadoMolino state){
           if(state == null) throw new IllegalArgumentException("ProblemaMolino: _end : State null");
           return state.estadoFin(); 
        }
  
        //(2X CANTFMAX + CANT2SeGMemtos de a 2)   -  (2XCANTFMIN + CANT2SGM) value() MEJORAR CON ESTO        
        //  tomar la diferencia entre la cantidad de piezas disponibles en el tablero de cada jugador, 
        //  ponderadas por el numero de casillas libres adyacentes a cada una
        public int value(EstadoMolino state){
            List<Pair<Integer,Integer>> nodoPlayer1= state.nodosJug(1);
            List<Pair<Integer,Integer>> nodoPlayer2= state.nodosJug(2);
            //cantidad de piezas del jugador 1.
            int piezas= nodoPlayer1.size();
            //cantidad de adyacentes de cada una
            int cantAdy=0;
            for (int i=0;i < nodoPlayer1.size() ;i++ ) {
                cantAdy+=nodoPlayer1.get(i).getSnd().intValue();//sumo cuantos ady tiene cada nodo.
            }
            int result1=piezas*cantAdy; //cantidad de piezas de jugador1 ponderados por la cantidad de ady de cada ficha.
            //-------------------------------
            //cantidad de piezas del jugador 2.
            piezas=0;
            piezas=nodoPlayer2.size();
            cantAdy=0;
            for (int i=0; i < nodoPlayer2.size() ;i++ ) {
                cantAdy+=nodoPlayer2.get(i).getSnd().intValue();
            }
            int result2=piezas*cantAdy;
            //Diferencia entre la cantidad de piezas disponibles en el tablero de cada jugador, 
            //ponderadas por el numero de casillas libres adyacentes a cada una
            int value = result1 - result2;
            return value;
        }
        //Indica el valor mínimo posible para un estado en el problema.
        //Junto con maxValue (), se determina un intervalo en el que los
        // valores de los estados deben variar.
        public int minValue(){
           return -1000; 
        }
        public int maxValue(){
           return 1000; 
        }

}


