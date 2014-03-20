package sorta.model;

import sorta.*;

public class PrintingSheet 
{
	private Order[] spot;
	private int[] orderPage;
	
	public PrintingSheet()
	{
		this.spot = new Order[Constants.spots];
		this.orderPage = new int[Constants.spots];
	}
	
	public void setOrderPageToSpot(Order _order, int orderPage, int _spot)
	{
		this.spot[_spot] = _order;
		this.orderPage[_spot] = orderPage;
	}
	
	public boolean isOrderAssignedToSpot(int _spot)
	{
		return this.spot[_spot] !=  null;
	}
	
	public int[] getOrders()
	{
		int[] ordersOnPage = new int[Constants.spots]; 
		for(int i = 0; i < Constants.spots; i++)
		{
			if(this.spot[i] != null)
			{
				ordersOnPage[i] = this.spot[i].getId();
			} else {
				ordersOnPage[i] = -1;
			}
		}
		return ordersOnPage;
	}
	
	public String toString()
	{
		String output = "# ";
		int[] orderNumbers = this.getOrders();
		for(int i = 0; i < Constants.spots; i++)
		{
			output += orderNumbers[i] + " # ";
		}
		return output;
	}
}
