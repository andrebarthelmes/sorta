package sorta.ea;

import sorta.TestData;
import sorta.model.*;

public class Creation {

	private PrintingPartion createdPartition;
	
	public Creation(OrderList testOrders) 
	{
		int optimalSpotHeight = (int) testOrders.getCardCountSum() / TestData.spots;
		int[] spaceLeft = new int[TestData.spots];
		for (int i = 0; i < TestData.spots; i++)
		{
			spaceLeft[i] = optimalSpotHeight;
		}
		int spaceLeftSum = optimalSpotHeight * TestData.spots;
		
		this.createdPartition = new PrintingPartion();
		
		for(int i = 0; i < testOrders.getOrderListLength();i++)
		{
			int selectedSpot = this.selectColum(spaceLeft,spaceLeftSum);
			spaceLeft[selectedSpot] -= testOrders.getOrder(i).getCardCount();
			spaceLeftSum -= testOrders.getOrder(i).getCardCount();
			this.createdPartition.add(selectedSpot,testOrders.getOrder(i));
		}
	}

	private int selectColum(int[] _spaceLeft, int _spaceLeftSum) 
	{
		int myRandom = (int) (Math.random() * _spaceLeftSum);
		int currentSum=0;
		for(int i = 0; i < TestData.spots; i++)
		{
			currentSum += _spaceLeft[i];
			if(myRandom < currentSum)
			{
				return i;
			}
		} 		
		return TestData.spots;
	}

	public PrintingPartion getCreatedPartition()
	{
		return this.createdPartition;
	}
	
}
