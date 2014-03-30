package sorta;

import sorta.ea.EvolutionaryAlgorithm;

public class Sorta 
{

	public static void main(String[] args) 
	{
		RandomGenerator.setupRandomGenerator();
		TestDataGenerator myTDG = new TestDataGenerator();
		EvolutionaryAlgorithm myEA = new EvolutionaryAlgorithm(Constants.parentSize,Constants.populationSize,myTDG);
		myEA.initializePopulation();
		myEA.evaluatePopulation(false);
		myEA.run();
	}
}
