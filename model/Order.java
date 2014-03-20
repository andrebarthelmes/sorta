package sorta.model;

public class Order implements Comparable<Order>
{
	private int cardCount;
	private int id;
	private String sortBy = "id";
	private int spot;
	
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
	
	public void setSpot(int _spot)	
	{
		this.spot = _spot;
	}

	public int getSpot()
	{
		return this.spot;
	}
	public void setSortBy(String _varName)
	{
		this.sortBy = _varName;
	}
	
	@Override
	public int compareTo(Order _anotherOrder) 
	{
		if(this.sortBy == "cardCount")
		{
			return this.compareToCardCount(_anotherOrder);
		}
		return this.compareToId(_anotherOrder);
	}
	
	public int compareToId(Order _anotherOrder)
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

	public int compareToCardCount(Order _anotherOrder)
	{		
		int return_;
		if(this.getCardCount() < _anotherOrder.getCardCount())
		{
			return_ =  1;
		} else if(this.getCardCount() > _anotherOrder.getCardCount())
		{
			return_ =  -1;			
		} else {
			return_ =  0;			
		}
		return return_;		
	}
}
