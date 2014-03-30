package sorta.ea;

import sorta.Constants;
import sorta.TestDataGenerator;
import sorta.model.PrintingPlan;
import sun.text.normalizer.CharTrie.FriendAgent;

public class EvolutionaryAlgorithm 
{
	private int selectionNumber;
	private int populationSize;
	private TestDataGenerator testDataGenerator;
	private double mutationStrength;
	private PrintingPlan[] population;
 
	public EvolutionaryAlgorithm(int _selectionNumber, int _populationSize, TestDataGenerator _testDataGenerator) 
	{
		this.selectionNumber = _selectionNumber;
		this.populationSize = _populationSize;
		this.testDataGenerator = _testDataGenerator;
		this.population = new PrintingPlan[this.populationSize];
	}

	public void initializePopulation() 
	{
		Creation firstGenGenerator  = new Creation(this.populationSize, this.testDataGenerator.getTestOrders());
		this.population = firstGenGenerator.getStartingPopulation();
	}

	public void run() 
	{
		int generation = 1;
		double bestFitness = Integer.MAX_VALUE;
		String bestPrintingPartionFoundString = "";
		this.mutationStrength = Constants.columns;
		while(generation < Constants.maxGenerations && ((int) this.mutationStrength != 0))
		{
			this.selectParents();
			this.reproduceParents();
			generation++;
		}
		System.out.println(bestPrintingPartionFoundString);
	}	
	
	private void selectParents() 
	{
	}
		
	private void reproduceParents() 
	{
	}


	private void adoptMutationStrength(double _successRatio) 
	{
		if(_successRatio > 0.2)
		{
			this.mutationStrength /= 0.9999;
		} else if (_successRatio < 0.2) {
			this.mutationStrength *= 0.9999;						
		}		
	}

	public void evaluatePopulation(boolean b) 
	{
		// TODO 
		
	}

}
