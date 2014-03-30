package sorta.model;

import java.util.Vector;

public class PrintingColumn 
{
	private Vector<Order> orders;
	
	public PrintingColumn()
	{
		this.orders = new Vector<Order>();
	}
	
	public void addOrder(Order _order)
	{
		this.orders.add(_order);
	}
	
	public void deleteOrder(int _orderId)	
	{
		for(int i = 0; i < this.orders.size(); i++)
		{
			if(this.orders.elementAt(i).getId() == _orderId)
			{
				this.orders.remove(i);
				return;
			}
		}
	}
	
	public int getOrderAtHeight(int _height)
	{
		int currentOrderNrInColumn = 0;
		int currentHeight = this.orders.elementAt(0).getCardCount();
		while(currentHeight < _height)
		{
			currentHeight += this.orders.elementAt(currentOrderNrInColumn).getCardCount();
			currentOrderNrInColumn++;
			if( this.orders.elementAt(currentOrderNrInColumn) == null)
			{
				return -1;
			}
		}
		return this.orders.elementAt(currentOrderNrInColumn).getId();
	}
	

	public int getHeight() 
	{
		int height =0;
		for(int i =0; i < this.orders.size();i++)
		{
			height += this.orders.elementAt(i).getCardCount();
		}
		return height;
	}
}
