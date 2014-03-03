package sorta.model;

public class OrderList 
{
	private Order[] list;
	private int cardCountSum = -1; //-1 marks "not set yet"
	
	public OrderList(int _orderCount)
	{
		this.list = new Order[_orderCount];
	}
	
	public Order getOrder(int _pos)
	{
		return this.list[_pos];
	}
	
	public int getOrderListLength()
	{
		return this.list.length;
	}
	
	public int getCardCountSum()
	{
		if(this.cardCountSum == -1)
		{
			this.cardCountSum = this.calaculateCardCountSum();
		}
		return this.cardCountSum;
	}
	
	private int calaculateCardCountSum() 
	{
		int sum_ = 0;
		for(int i = 0; i < this.list.length; i++)
		{
			sum_ += this.list[i].getCardCount();
		}
		return sum_;
	}

	public void setOrderList(Order[] _list)
	{
		this.list = _list;
	}
	
}
