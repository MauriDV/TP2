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
            -Si la cantidad de fichas es menor a 19 : Entonces se obtienen 
                sucesores de colocar fichas en el tablero.
            -Si la cantidad de fichas es 19, entonces se obtienen sucesores
                con respecto a realizar movimientos en el tablero.    

        */
        public List<EstadoMolino> getSuccessors(EstadoMolino s) {
            //System.out.println("---------- ESTADO PADRE -------- ");
           // System.out.println(s.toString());
            System.out.println("NUMERO DE FICHAS "+s.getNumFichas());
            List<EstadoMolino> successors = new LinkedList<EstadoMolino>();
            //si la cantidad de fichas es menor a 19: colocar fichas en tablero.
            EstadoMolino succ= new EstadoMolino();
            if (s.getNumFichas()<19){//COLOCACION DE FICHAS       
                System.out.println("S ES MAX??? "+ s.isMax()) ;
                if (s.isMax()){//jugador 1.
                    //System.out.println("-----JUGADOR 1 OBT SUCC");
                    List<Integer> fichasColocadas2 = s.dondeColoco(2);//donde coloco el 2    
                    //System.out.println("Fichas colocadas por 2");
                    //System.out.println(fichasColocadas2.toString());    
                    successors.addAll(succColocarFichas(1,successors,fichasColocadas2,succ,s));
                    //System.out.println("SUCESORES A PARTIR DE 1");
                    //System.out.println(successors.toString());
                    fichasColocadas2.clear();    
                }
                else{ //jugador 2
                    System.out.println("------JUGADOR 2 OBT SUCC");
                    List<Integer> fichasColocadas1 = s.dondeColoco(1);//donde coloco el 1
                    System.out.println("Fichas colocadas por 1");
                    System.out.println(fichasColocadas1.toString());
                    successors.addAll(succColocarFichas(2,successors,fichasColocadas1,succ,s));
                    System.out.println("SUCESORES A PARTIR DE 2");
                    System.out.println(successors.toString());    
                    fichasColocadas1.clear();
                }
            }    
            /*else{
                System.out.println("ENTRA POR cantidad >=19");
                //Si la cantidad es 19, obtener sucesores con respecto a realizar movidas de fichas.    
                if (s.isMax()){//jugador1
                   // System.out.println("-----JUGADOR 1 OBT SUCC");
                    List<Integer> fichasColocadas2 = s.dondeColoco(2);
                  //  System.out.println("Fichas colocadas por 2");
                   // System.out.println(fichasColocadas2.toString());    
                    succMoverFichas(1,successors,fichasColocadas2,s);
                  //  System.out.println("SUCESORES A PARTIR DE 1");
                   // System.out.println(successors.toString());
                    fichasColocadas2.clear();
                }
                else{//jugador 2
                 //   System.out.println("------JUGADOR 2 OBT SUCC");
                    List<Integer> fichasColocadas1 = s.dondeColoco(1);
                   // System.out.println("Fichas colocadas por 1");
                   // System.out.println(fichasColocadas1.toString());
                    succMoverFichas(2,successors,fichasColocadas1,s);
                   // System.out.println("SUCESORES A PARTIR DE 2");
                   // System.out.println(successors.toString());    
                    fichasColocadas1.clear();
                }
            }*/
            //System.out.println("SUCESORES FINAL");
           // System.out.println(successors.toString());
            return successors;
        }
        
        //Metodo para obtener los sucesores de un estado por colocacion de fichas
        private List<EstadoMolino> succColocarFichas(int jugador,List<EstadoMolino> listSucc,List<Integer> dondeColoco, EstadoMolino suc, EstadoMolino s){
            List<Integer> posiciones=s.lugaresDisp(); //guardo todos los lugares disponibles.
            System.out.println("LUGARES disponibles "+posiciones.toString());
            //generar un estado sucesor a partir de poner una ficha de Max en cada posicion
            //teniendo en cuenta que en cada insercion, puede generar molino y permita
            //borrarle una ficha de su contrario.
            //ACA HACER BACKUP DE VECINOS ANTES DE AGREGAR ALGO, PORQUE ES AGREGAR 1, LUEGO EN OTRO
            //PERO SIN ESE 1 PRIMERO
            EstadoMolino backup= s.clonarEstado(s);  
            System.out.println("BACKUP FUNCIONA???? "+backup.getVecino().toString2());
          
            for (int i=0; i < posiciones.size() ; i++) {
                System.out.println("SUCESOR "+posiciones.get(i));
                //NO PUEDO CREAR ESTADOS SIN INICIALIZAR TABLERO Y VECINOS
                //---- ARREGLAR PARA Q SE ACTUALIZEN ESTOS DIAS PERO CON EL TABLERO Y VECINOS Q VIENE Y DEMAS!!!!!
                //EstadoMolino suc = new EstadoMolino();
                //HACER UN SETEOESTADO2 , Y LOS GET TABLERO Y GET VECINO DE ESTADO
                suc.setEstadoMolino2(jugador,posiciones.get(i).intValue(),s.getVecino(),s.getTablero(),s.getCantFichas(),s);
                System.out.println("VECINOS"+ s.getVecino().toString2()); 
                System.out.println("ES MOLINO??? "+suc.esMolino());
                if (suc.esMolino()){ //entonces generar otra tipo de estado.
                    //permito borrar una ficha de su contrario
                    //le asigno false a molino porque ya deja de ser molino.
                    //ACA BORRAR UNA POR UNA LAS DEL CONTRARIO, VARIOS ESTADOS
                    for (int k=0;k < dondeColoco.size() ; k++ ) {
                        int posABorrar= dondeColoco.get(k).intValue();
                        EstadoMolino aux= new EstadoMolino();//creo y hago backup con actualizaciones
                        aux.setEstadoMolino3(jugador,posiciones.get(i).intValue(),posABorrar,s.getVecino(),s.getTablero(),s.getCantFichas(),false,s  );
                        listSucc.add(aux);//agrego a la lista de sucesores    
                    }                        
                 }
                 else{//si no es molino, agrego a la lista simplemente.
                    System.out.println("NO FUE MOLINO ");
                    //System.out.println("SUCESOR OBTENIDO COLOCAR FICHAS "+ suc.toString());
                    //System.out.println("-------------------------------------------------------------");
                    listSucc.add(suc);
                 }
                 System.out.println("BACKUP LUEGO"+backup.getVecino().toString2());
                 s=backup.clonarEstado(backup);
                 System.out.println("Q PASO CON ESTADO PADRE "+s.getVecino().toString2());
                 System.out.println("-----------FIN ITERACION "+i+" ---------------");
            }
            posiciones.clear(); //Por las dudas
            return listSucc;
        }
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
                EstadoMolino suc = new EstadoMolino();
                suc.setEstadoMolino4(jugador,fst,snd,s.getVecino(),s.getTablero(),s.getCantFichas(),s);
                if (suc.esMolino()){//si el estado generado es molino
                    //ACA BORRRAR UNA POR UNA LAS DEL CONTRARIO,Generando varios estados
                    for (int k=0;k <dondeColoco.size() ;k++ ) {
                        int posABorrar= dondeColoco.get(k).intValue();    
                        //generar un nuevo estado permitiendo borrar
                        EstadoMolino aux= new EstadoMolino();
                        aux.setEstadoMolino5(jugador,fst,snd,posABorrar,s.getVecino(),s.getTablero(),s.getCantFichas(),false,s);
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
           return Integer.MIN_VALUE; 
        }
        public int maxValue(){
           return Integer.MAX_VALUE; 
        }

}
