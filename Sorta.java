package sorta;

import sorta.ea.*;

public class Sorta 
{

	public static void main(String[] args) 
	{
		TestDataGenerator myTDG = new TestDataGenerator();
		EvolutionaryAlgorithm myEA = new EvolutionaryAlgorithm(10,100,myTDG);
		myEA.initializePopulation();
		myEA.evaluatePopulation();
		myEA.run();
	}
}
