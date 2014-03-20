package sorta.controller.spotea;

import sorta.Constants;
import sorta.model.*;

public class SpoteaEvaluation 
{
	private int[] cardCountsPerSpot;
	
	public SpoteaEvaluation()
	{
		this.cardCountsPerSpot = new int[Constants.spots];		
	}
	
	public PrintingPartion evaluate(PrintingPartion _evaluatePartition)
	{
		int cardCount = 0;
		int maxSpotCount = 0;
		for(int i = 0; i < Constants.spots; i++)
		{	
			for(int j = 0; j < _evaluatePartition.getPartition().elementAt(i).size(); j++)
			{
				if(_evaluatePartition.getOrder(i,j) != null)
				{
					cardCount += _evaluatePartition.getOrder(i,j).getCardCount();
					this.cardCountsPerSpot[i] += _evaluatePartition.getOrder(i,j).getCardCount(); 
				}
			}
			if(this.cardCountsPerSpot[i] > maxSpotCount)
			{
				maxSpotCount = this.cardCountsPerSpot[i];
			}
		}
		int optimalCount = (int) cardCount / Constants.spots;
		
		double fitness = maxSpotCount -  optimalCount;
		_evaluatePartition.setFitness(fitness);		
		return _evaluatePartition;
	}
}
