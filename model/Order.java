package sorta.model;

public class Order implements Comparable<Order>
{
	private int cardCount;
	private int id;
	
	static private int nextId = 0;
	
	public Order(int _cardCount)
	{
		this.id = Order.nextId;
		Order.nextId++;
		this.cardCount = _cardCount;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public int getCardCount()
	{
		return this.cardCount;
	}

	@Override
	public int compareTo(Order _anotherOrder) 
	{
		int return_;
		if(this.getId() < _anotherOrder.getId())
		{
			return_ =  1;
		} else if(this.getId() > _anotherOrder.getId())
		{
			return_ =  -1;			
		} else {
			return_ =  0;			
		}
		return return_;
	}	
}
