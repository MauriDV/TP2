import org.jgap.*;
import org.jgap.audit.*;
import org.jgap.data.*;
import org.jgap.impl.*;
import org.jgap.xml.*;

public class Sudoku {

	public static void main(String[] args) throws InvalidConfigurationException{
		
		int[] sudo = {
			    0, 0, 7, 0, 0, 9, 8, 1, 2,
			    4, 8, 0, 5, 1, 0, 7, 9, 3,
			    0, 3, 9, 7, 0, 0, 0, 0, 6,
			    8, 2, 0, 0, 9, 0, 0, 3, 0,
			    0, 0, 1, 0, 0, 0, 6, 2, 9,		// EJEMPLO DE UN TABLERO DE SUDOKU
			    3, 0, 0, 0, 7, 0, 4, 0, 0,
			    9, 5, 8, 1, 3, 7, 0, 0, 0,
			    0, 7, 3, 4, 0, 5, 9, 8, 1,
			    6, 1, 4, 9, 8, 0, 3, 0, 5
		};
		
		SudokuTable s = new SudokuTable(sudo);
		
		//CONFIGURACION

		Configuration conf = new DefaultConfiguration();
	    conf.setPreservFittestIndividual(true);
	    conf.addGeneticOperator(new CrossoverOperator(conf,100,false,true));
        conf.addGeneticOperator(new MutationOperator(conf,35));

		//FUNCION FITNESS

    	FitnessFunction myFunc = new SudokuFitnessFunction(sudo);
    	conf.setBulkFitnessFunction(new BulkFitnessOffsetRemover(myFunc));

		// CREACION DE GENES
		Gene[] gen = new Gene[81];
		for (int i=0; i<81; i++) {
			if(sudo[i]==0){
				gen[i] = new IntegerGene(conf,1,9);
			}else{
				gen[i] = new IntegerGene(conf,sudo[i],sudo[i]);
			}
		}

		//POBLACION

		IChromosome sampleChromosome = new Chromosome(conf, gen);
	    conf.setSampleChromosome(sampleChromosome);
	    conf.setPopulationSize(3000);
	    Genotype population = Genotype.randomInitialGenotype(conf);
	    
	    System.out.println(s.toString());
	    double max = 0;
	    boolean solucion = false;
	    int i = 0;
	    SudokuTable posibleSolucionTabla=null;
	    
	    /*population.evolve();
    	System.out.println(population.toString());
    	System.out.println("--------------------------------------------");
    	System.out.println(population.getFittestChromosome().toString());
    	System.out.println("--------------------------------------------");*/
	    
    	while(!solucion){
	    	population.evolve();
	    	IChromosome posibleSolucion = population.getFittestChromosome();
	    	System.out.println("----------------------------CROMOSOMA-------------------------------");
	    	System.out.println(posibleSolucion.toString());
	    	System.out.println("********************************************************************");
	    	posibleSolucionTabla = SudokuFitnessFunction.crearSudoku(posibleSolucion);
	    	System.out.println("---------------------POSIBLE SOLUCION-------------------------------");
	    	System.out.println(posibleSolucionTabla.toString());
	    	System.out.println("********************************************************************");
	    	System.out.println(max+" < "+myFunc.getFitnessValue(posibleSolucion));
	    	if (max<myFunc.getFitnessValue(posibleSolucion)){
	    		max=myFunc.getFitnessValue(posibleSolucion);
	    		System.out.println(max);
	    		if(max==(81*3)){
	    			solucion = true;
	    		}
	    	}
	    	i++;
	    }
	    
	    System.out.println("----------------------------SOLUCION-------------------------------");
    	System.out.println(posibleSolucionTabla.toString());
    	System.out.println("********************************************************************");
	    
	    
	}

}