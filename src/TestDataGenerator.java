package sorta;

import sorta.model.*;

public class TestDataGenerator 
{
	private OrderList testOrders;
	
	public TestDataGenerator()
	{
		Order[] orderList = new Order[Constants.orderCountToTest]; 
		for(int i = 0; i < Constants.orderCountToTest; i++)
		{
			orderList[i] = this.generateRandomOrder();
		}
		this.testOrders = new OrderList(Constants.orderCountToTest);
		this.testOrders.setOrderList(orderList);
	}

	public OrderList getTestOrders()
	{
		return this.testOrders;
	}
	
	private Order generateRandomOrder() 
	{
		int random = (int) (RandomGenerator.getRandom()*100);
		int cardCount = Constants.availableOrderCount[Constants.probabilityofOrderCount[random]]+Constants.addPages;
		Order order_ = new Order(cardCount);
		return order_;
	}
	
	public String toString()
	{
		String orderPrint = "";
		for(int i = 0; i < Constants.orderCountToTest; i++)
		{
			orderPrint += this.testOrders.getOrder(i).getId()+
			              " : "+
			              this.testOrders.getOrder(i).getCardCount()+
			              "\r\n";
		}
		return orderPrint;
	}
	
}
