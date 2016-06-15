
import java.util.*;


public class ProblemaMolino implements AbstractInformedSearchProblem<EstadoMolino> {

        protected EstadoMolino inicial;

        public ProblemaGranja() {
            inicial = new EstadoMolino();
        }

        public ProblemaGranja(EstadoMolino inicial) {
            this.inicial = inicial;
        }

        public EstadoMolino initialState() {
            return inicial;
        }

        //agrego como parametro del constructor "s" que va a ser el padre del estado proximo o sucesores que se estan construyendo.
        public List<EstadoMolino> getSuccessors(EstadoMolino m) {
            List<EstadoGranja> successors = new LinkedList<EstadoGranja>();
            int cantFichas = m.getFichas();
            if (cantFichas<18){
                
            }else {
                
            }
      
            return successors;
        }

        public boolean success(EstadoMolino m) {
            return (s.granjero<0 && s.zorro<0 && s.gallina<0 && s.maiz<0);
        }

		

}
