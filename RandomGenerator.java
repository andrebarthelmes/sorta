package sorta;

import java.util.Random;

public class RandomGenerator {

	private static Random generator;
	
	public static void setupRandomGenerator()
	{
		if(Constants.useRandomSeed)
		{
			RandomGenerator.generator = new Random(Constants.randomSeed);
		} else {
			RandomGenerator.generator = new Random();
		}
	}
	
	public static double getRandom()
	{
		return generator.nextDouble();
	}
	
}
