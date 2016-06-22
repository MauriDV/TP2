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
            List<EstadoMolino> successors = new LinkedList<EstadoMolino>();
            //si la cantidad de fichas es menor a 19: colocar fichas en tablero.
            if (s.getNumFichas()<19){//COLOCACION DE FICHAS
                
                if (s.isMax()){//jugador 1.
                    succColocarFichas(1,successors,s);
                }
                else{ //jugador 2
                    succColocarFichas(2,successors,s);    
                }
            }    
            else{
                //Si la cantidad es 19, obtener sucesores con respecto a realizar movidas de fichas.    
                

            }
            return successors;
        }
        
        //Metodo para obtener los sucesores de un estado por colocacion de fichas
        private void succColocarFichas(int jugador,List<EstadoMolino> listSucc,EstadoMolino s){
            List<Integer> posiciones=s.lugaresDisp(); //guardo todos los lugares disponibles.
            //generar un estado sucesor a partir de poner una ficha de Max en cada posicion
            //teniendo en cuenta que en cada insercion, puede generar molino y permita
            //borrarle una ficha de su contrario.
            for (int i=0; i < posiciones.size() ; i++) {
                EstadoMolino suc = new EstadoMolino(jugador, posiciones.get(i).intValue(),s);
                if (suc.esMolino()){ //entonces generar otra tipo de estado.
                    //permito borrar una ficha de su contrario
                    //le asigno false a molino porque ya deja de ser molino.
                    EstadoMolino aux = new EstadoMolino(jugador,posiciones.get(i).intValue(),false,s);
                    listSucc.add(aux);//agrego a la lista de sucesores    
                 }
                 else{//si no es molino, agrego a la lista simplemente.
                    listSucc.add(suc);
                 }
            }
            posiciones.clear(); //Por las dudas
        }
        //Metodo para obtener los sucesores de un estado por movimiento de fichas.
        private void succMoverFichas(int jugador,List<EstadoMolino> listSucc,EstadoMolino s){

        } 




        //Indica si es estado final.
		public boolean end(EstadoMolino state){
           if(state == null) throw new IllegalArgumentException("ProblemaMolino: _end : State null");
           return state.estadoFin(); 
        }
    //Computa el valor de un estado dado. si el estado es una hoja o estado final,
    //entonces este valor es un valor exacto, e indica el resultado del juego.
    //sino indica un valor aproximado.
        public int value(EstadoMolino state){
            return 0;
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
