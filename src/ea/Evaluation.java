package sorta.ea;

import sorta.TestData;
import sorta.model.*;

public class Evaluation 
{
	private int[] cardCountsPerSpot;
	
	public Evaluation()
	{
		this.cardCountsPerSpot = new int[TestData.spots];		
	}
	
	public PrintingPartion evaluate(PrintingPartion _evaluatePartition)
	{
		int cardCount = 0;
		for(int i = 0; i < TestData.spots; i++)
		{	
			for(int j = 0; j < TestData.orderCountToTest; j++)
			{
				if(_evaluatePartition.getOrder(i,j) != null)
				{
					cardCount += _evaluatePartition.getOrder(i,j).getCardCount();
					this.cardCountsPerSpot[i] += _evaluatePartition.getOrder(i,j).getCardCount(); 
				}
			}			
		}
		int optimalCount = (int) cardCount / TestData.spots;
		int fitnessSum = 0;
		for(int i = 0; i < TestData.spots; i++)
		{
			int temp = optimalCount - this.cardCountsPerSpot[i];
			fitnessSum += Math.pow(temp,2); 
		}
		double fitness = (double) fitnessSum ;
		_evaluatePartition.setFitness(fitness);		
		return _evaluatePartition;
	}
}
