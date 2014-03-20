package sorta.controller.spotea;

import java.util.Arrays;
import sorta.*;
import sorta.model.*;

public class SpoteaEvolutionaryAlgorithm 
{
	private int selectionNumber;
	private int populationSize;
	private TestDataGenerator testDataGenerator;
	private PrintingPartion[] population;
	private int generation;
	private double mutationStrength;
 
	public SpoteaEvolutionaryAlgorithm(int _selectionNumber, int _populationSize, TestDataGenerator _testDataGenerator) 
	{
		this.selectionNumber = _selectionNumber;
		this.populationSize = _populationSize;
		this.testDataGenerator = _testDataGenerator;
		this.population = new PrintingPartion[_populationSize];
		this.mutationStrength = 1 + (Constants.orderCountToTest * 0.5 * (Constants.spoteaMaxGenerations-this.generation) / Constants.spoteaMaxGenerations);
	}

	public void initializePopulation() 
	{
		for(int i = 0; i < this.populationSize; i++)
		{
			SpoteaCreation myCreation = new SpoteaCreation(this.testDataGenerator.getTestOrders());
			PrintingPartion currentPartition = myCreation.getCreatedPartition();
			this.population[i] = currentPartition;
		}		
	}

	public void run() 
	{
		this.generation = 1;
		double bestFitness = Integer.MAX_VALUE;
		PrintingPartion everBestPrintingPartion = null;
		PrintingPartion currentBestPrintingPartion = null;
		while(this.generation < Constants.spoteaMaxGenerations && (int) this.mutationStrength >0)
		{
			this.selectParents();
			this.reproduceParents();
			currentBestPrintingPartion = this.evaluatePopulation(true);
			double currentFitness = currentBestPrintingPartion.getFitness();
			if(currentFitness < bestFitness)
			{ 
				bestFitness = currentFitness;
				everBestPrintingPartion = currentBestPrintingPartion.getClone();
				System.out.println(this.generation+" # "+bestFitness);
			}
			this.generation++;
		}
		System.out.println(everBestPrintingPartion.toString());
	}	
	
	private void selectParents() 
	{
		Arrays.sort(this.population);
		PrintingPartion[] parents = new  PrintingPartion[this.selectionNumber];
		for(int i = 0; i < this.selectionNumber; i++)
		{
			parents[i] = this.population[i].getClone();
			parents[i].setOldFitness(this.population[i].getFitness());
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
		
		SpoteaRecombination myRecombination = new SpoteaRecombination(this.population,this.populationSize);
		return myRecombination.getChildren();
	}

	private PrintingPartion[] mutateChildren() 
	{
		SpoteaMutation myMutation = new SpoteaMutation(this.population,(int)this.mutationStrength,this.testDataGenerator.getTestOrders(),this.selectionNumber);
		return myMutation.getChildren();
	}

	public PrintingPartion evaluatePopulation(boolean adjustMutationStrength)
	{
		double bestFitness = Integer.MAX_VALUE;
		int bestPopulation = -1;
		int newFitnessBetter = 0;
		for(int i = 0; i < this.populationSize; i++)
		{			
			SpoteaEvaluation myEvaluation = new SpoteaEvaluation();
			myEvaluation.evaluate(this.population[i]);
			double fitness = this.population[i].getFitness();
			double oldFitness = this.population[i].getOldFitness();
			if(fitness < oldFitness)
			{
				newFitnessBetter++;
			}
			if(fitness < bestFitness)
			{
				bestFitness = fitness;
				bestPopulation = i;
			}
		}
		if(adjustMutationStrength) this.adjustMutationStrength(newFitnessBetter);
		return this.population[bestPopulation];
	}
	
	private void adjustMutationStrength(int newFitnessBetter)
	{
		double ratio = newFitnessBetter / this.populationSize;
		if(ratio < 0.2)
		{
			this.mutationStrength *= 0.85;
		} else if (ratio > 0.2)
		{
			System.out.println("-");
			this.mutationStrength /= 0.85;			
		} else {
			// do nothing;
		}
	}
}
