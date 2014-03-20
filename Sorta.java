package sorta;

import sorta.controller.spotea.*;

public class Sorta 
{

	public static void main(String[] args) 
	{
		RandomGenerator.setupRandomGenerator();
		TestDataGenerator myTDG = new TestDataGenerator();
		SpoteaEvolutionaryAlgorithm myEA = new SpoteaEvolutionaryAlgorithm(Constants.spoteaParentSize,Constants.spoteaPopulationSize,myTDG);
		myEA.initializePopulation();
		myEA.evaluatePopulation(false);
		myEA.run();
	}
}
