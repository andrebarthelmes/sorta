package sorta.ea;

import java.util.Arrays;
import sorta.*;
import sorta.model.*;

public class EvolutionaryAlgorithm 
{
	private int selectionNumber;
	private int populationSize;
	private TestDataGenerator testDataGenerator;
	private PrintingPartion[] population;
	private double mutationStrength;
 
	public EvolutionaryAlgorithm(int _selectionNumber, int _populationSize, TestDataGenerator _testDataGenerator) 
	{
		this.selectionNumber = _selectionNumber;
		this.populationSize = _populationSize;
		this.testDataGenerator = _testDataGenerator;
		this.population = new PrintingPartion[_populationSize];
	}

	public void initializePopulation() 
	{
		for(int i = 0; i < this.populationSize; i++)
		{
			Creation myCreation = new Creation(this.testDataGenerator.getTestOrders());
			PrintingPartion currentPartition = myCreation.getCreatedPartition();
			this.population[i] = currentPartition;
		}		
	}

	public void run() 
	{
		int generation = 1;
		double bestFitness = Integer.MAX_VALUE;
		String bestPrintingPartionFoundString = "";
		this.mutationStrength = TestData.spots;
		while(generation < TestData.maxGenerations && ((int) this.mutationStrength != 0))
		{
			this.selectParents();
			this.reproduceParents();
			PrintingPartion bestPrintingPartion = this.evaluatePopulation();
			double recentFitness = bestPrintingPartion.getFitness();
			if(recentFitness < bestFitness)
			{ 
				bestFitness = recentFitness;
				bestPrintingPartionFoundString = bestPrintingPartion.toString();
				System.out.println(generation+" # "+bestFitness);
			}
			generation++;
		}
		System.out.println(bestPrintingPartionFoundString);
	}	
	
	private void selectParents() 
	{
		Arrays.sort(this.population);
		PrintingPartion[] parents = new  PrintingPartion[this.selectionNumber];
		for(int i = 0; i < this.selectionNumber; i++)
		{
			parents[i] = this.population[i];
		}
		this.population = parents;
	}
		
	private void reproduceParents() 
	{
		this.population = this.recombineParents();
		this.population = this.mutateChildren();
	}

	private PrintingPartion[] recombineParents() 
	{
		Recombination myRecombination = new Recombination(this.population,this.populationSize);
		return myRecombination.getChildren();
	}

	private PrintingPartion[] mutateChildren() 
	{
		Mutation myMutation = new Mutation(this.population, (int) this.mutationStrength);
		this.adoptMutationStrength(myMutation.getSuccessRatio());
		return myMutation.getChildren();
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

	public PrintingPartion evaluatePopulation()
	{
		Evaluation myEvaluation = new Evaluation();
		double bestFitness = Integer.MAX_VALUE;
		int bestPopulation = -1;
		for(int i = 0; i < this.populationSize; i++)
		{
			double fitness = myEvaluation.evaluate(this.population[i]).getFitness();
			if(fitness < bestFitness)
			{
				bestFitness = fitness;
				bestPopulation = i;
			}
		}
		return this.population[bestPopulation];
	}	
}
