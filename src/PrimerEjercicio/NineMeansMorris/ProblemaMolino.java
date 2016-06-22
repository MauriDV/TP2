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

            //jugador 1.
            //brindarle todas las posiciones en las q puede colocar una ficha.
            //obtener todos esos sucesores

            //analogo si es jugador 2


            //Si la cantidad es 19, obtener sucesores con respecto a realizar movidas de fichas.

            return successors;
        



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
