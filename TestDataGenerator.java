package sorta;

import sorta.model.*;

public class TestDataGenerator 
{
	private OrderList testOrders;
	
	public TestDataGenerator()
	{
		Order[] orderList = new Order[TestData.orderCountToTest]; 
		for(int i = 0; i < TestData.orderCountToTest; i++)
		{
			orderList[i] = this.generateRandomOrder();
		}
		this.testOrders = new OrderList(TestData.orderCountToTest);
		this.testOrders.setOrderList(orderList);
	}

	public OrderList getTestOrders()
	{
		return this.testOrders;
	}
	
	private Order generateRandomOrder() 
	{
		int random = (int) (Math.random()*100);
		int cardCount = TestData.availableOrderCount[TestData.probabilityofOrderCount[random]];
		Order order_ = new Order(cardCount);
		return order_;
	}
	
	public String toString()
	{
		String orderPrint = "";
		for(int i = 0; i < TestData.orderCountToTest; i++)
		{
			orderPrint += this.testOrders.getOrder(i).getId()+
			              " : "+
			              this.testOrders.getOrder(i).getCardCount()+
			              "\r\n";
		}
		return orderPrint;
	}
	
}
