package sorta.controller.spotea;

import sorta.*;
import sorta.model.*;

public class SpoteaCreation {

	private PrintingPartion createdPartition;
	
	public SpoteaCreation(OrderList testOrders) 
	{
		int optimalSpotHeight = (int) testOrders.getCardCountSum() / Constants.spots;
		int[] spaceLeft = new int[Constants.spots];
		for (int i = 0; i < Constants.spots; i++)
		{
			spaceLeft[i] = optimalSpotHeight;
		}
		int spaceLeftSum = optimalSpotHeight * Constants.spots;
		
		this.createdPartition = new PrintingPartion();
		
		for(int i = 0; i < testOrders.getOrderListLength();i++)
		{
			int selectedSpot = this.selectColum(spaceLeft,spaceLeftSum);
			testOrders.getOrder(i).setSpot(selectedSpot);
			spaceLeft[selectedSpot] -= testOrders.getOrder(i).getCardCount();
			spaceLeftSum -= testOrders.getOrder(i).getCardCount();
			this.createdPartition.add(selectedSpot,testOrders.getOrder(i));
		}
	}

	private int selectColum(int[] _spaceLeft, int _spaceLeftSum) 
	{
		int myRandom = (int) (RandomGenerator.getRandom() * _spaceLeftSum);
		int currentSum=0;
		for(int i = 0; i < Constants.spots; i++)
		{
			currentSum += _spaceLeft[i];
			if(myRandom < currentSum)
			{
				return i;
			}
		} 		
		return Constants.spots;
	}

	public PrintingPartion getCreatedPartition()
	{
		return this.createdPartition;
	}
	
}
