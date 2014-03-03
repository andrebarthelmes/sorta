package sorta.ea;

import sorta.*;
import sorta.model.*;

public class Mutation 
{
	private PrintingPartion[] children;

	public Mutation(PrintingPartion[] _population) 
	{
		this.children = _population;
		for(int j = 0; j < _population.length;j++)
		{
			for(int i = 0; i < MathCalculator.sum0toN(TestData.spots-1);i++)
			{
				this.children[j] = this.mutate(this.children[j]);
			}
		}		
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

}
