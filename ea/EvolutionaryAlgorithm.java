package sorta.ea;

import java.util.Arrays;
import sorta.*;
import sorta.model.*;

public class EvolutionaryAlgorithm 
{
	private int selectionNumber;
	private int populationNumber;
	private TestDataGenerator testDataGenerator;
	private PrintingPartion[] population;
 
	public EvolutionaryAlgorithm(int _selectionNumber, int _populationNumber, TestDataGenerator _testDataGenerator) 
	{
		this.selectionNumber = _selectionNumber;
		this.populationNumber = _populationNumber;
		this.testDataGenerator = _testDataGenerator;
		this.population = new PrintingPartion[_populationNumber];
	}

	public void initializePopulation() 
	{
		for(int i = 0; i < this.populationNumber; i++)
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
		double bestGen = 0;
		String bestPrintingPartionFoundString = "";
		while(generation < TestData.generations)
		{
			this.selectParents();
			this.reproduceParents();
			PrintingPartion bestPrintingPartion = this.evaluatePopulation();
			double recentFitness = bestPrintingPartion.getFitness();
			if(recentFitness < bestFitness)
			{ 
				bestFitness = recentFitness;
				bestGen = generation;
				bestPrintingPartionFoundString = bestPrintingPartion.toString();
			}
			System.out.println(generation+" # "+bestGen+" # "+bestFitness);
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
		Recombination myRecombination = new Recombination(this.population,this.populationNumber);
		return myRecombination.getChildren();
	}

	private PrintingPartion[] mutateChildren() 
	{
		Mutation myMutation = new Mutation(this.population);
		return myMutation.getChildren();
	}

	public PrintingPartion evaluatePopulation()
	{
		Evaluation myEvaluation = new Evaluation();
		double bestFitness = Integer.MAX_VALUE;
		int bestPopulation = -1;
		for(int i = 0; i < this.populationNumber; i++)
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
