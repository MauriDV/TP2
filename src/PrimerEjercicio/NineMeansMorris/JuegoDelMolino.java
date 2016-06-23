public class JuegoDelMolino{
	public static void main(String[] args) {
		ProblemaMolino problem = new ProblemaMolino();
		MinMaxEngine<ProblemaMolino,EstadoMolino> engine =new MinMaxEngine(problem,4);
		System.out.println("\n\tESTADO INICIAL\n\t");
		System.out.println(problem.initialState().toString());
	}

}