package sorta.ea;

import sorta.*;
import sorta.model.*;

public class Mutation 
{
	private PrintingPartion[] children;
	private double successRatio;

	public Mutation(PrintingPartion[] _population, int mutationStrength) 
	{
		this.children = _population;
		int successCount = 0;
		int noSuccessCount = 0;
		Evaluation myEval = new Evaluation();
		for(int j = 0; j < _population.length;j++)
		{
			double fitnessStart = this.children[j].getFitness();
			for(int i = 0; i < mutationStrength;i++)
			{
				this.children[j] = this.mutate(this.children[j]);
			}
			myEval.evaluate(this.children[j]);
			double fitnessAfter = this.children[j].getFitness();
			if(fitnessStart > fitnessAfter)
			{
				noSuccessCount++;
			} else {
				successCount++;
			}
		}
		this.successRatio = successCount / (successCount + noSuccessCount);
	}

	private PrintingPartion mutate(PrintingPartion _partitionToMutate) 
	{
		int selectedSpot1 = (int) (Math.random()* TestData.spots);
		int selectedSpot2 = selectedSpot1;
		while (selectedSpot2 == selectedSpot1)
		{
			selectedSpot2 = (int) (Math.random()* (TestData.spots));
		}
		int selectedOrder1 = (int) (Math.random()* _partitionToMutate.getNextFreeOrder(selectedSpot1));
		int selectedOrder2 = (int) (Math.random()* _partitionToMutate.getNextFreeOrder(selectedSpot2));
		_partitionToMutate.swap(selectedSpot1,selectedOrder1,selectedSpot2,selectedOrder2);
		
		return _partitionToMutate;
	}

	public PrintingPartion[] getChildren() 
	{
		return this.children;
	}

	public double getSuccessRatio() 
	{
		return this.successRatio;
	}

}
