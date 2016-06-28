import org.jgap.*;
public class SudokuFitnessFunction extends FitnessFunction{
	
	private SudokuTable tabla;
	
	public SudokuFitnessFunction(int[] s){
		this.tabla=new SudokuTable(s);
	}
	
	@Override
	protected double evaluate(IChromosome c) {
		double fitness=0;
		SudokuTable aux = crearSudoku(c);
		fitness = aux.checkFila();
		fitness += aux.checkColumna();
		fitness += aux.checkMatriz();
		return fitness;
	}
	
	public static SudokuTable crearSudoku(IChromosome c){
		int[] list = new int[81];
		
		for(int i=0;i<81;i++){
			list[i]= (int) c.getGene(i).getAllele(); //Creo una lista con cada gen para pasarlo a 
		}											 //   un tablero de sudoku
		return new SudokuTable(list);
	}
	
}