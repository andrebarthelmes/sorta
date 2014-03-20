package sorta.controller.spotea;

import sorta.*;
import sorta.model.*;

public class SpoteaMutation 
{
	private PrintingPartion[] children;
	private OrderList testOrders;

	public SpoteaMutation(PrintingPartion[] _population, int _mutationStrength, OrderList _orderList, int _selectionNumber) 
	{
		this.children = _population;
		this.testOrders = _orderList;
		for(int i = 0; i < _selectionNumber; i++)
		{
			this.children[i] = _population[i].getClone();
		}
		for(int i = _selectionNumber; i < _population.length;i++)
		{
			for(int j = 0; j < _mutationStrength;j++)
			{
				this.children[i] = this.mutate(this.children[i].getClone());
			}
		}
	}

	private PrintingPartion mutate(PrintingPartion _partitionToMutate) 
	{
		int selectedOrder = (int) (RandomGenerator.getRandom()* Constants.orderCountToTest);
		Order myMutatingOrder = this.testOrders.getOrder(selectedOrder);
		int orderSpot = myMutatingOrder.getSpot();
		int newSpot = orderSpot;
		while(orderSpot == newSpot)
		{
			newSpot = (int) (RandomGenerator.getRandom()* Constants.spots);
		}		
		_partitionToMutate.moveOrderToOtherSpot(myMutatingOrder,newSpot);		
		return _partitionToMutate;
	}

	public PrintingPartion[] getChildren() 
	{
		return this.children;
	}
}
