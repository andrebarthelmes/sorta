package sorta.model;

import sorta.Constants;

public class PrintingSheet 
{
	private int[] columns;
	
	public PrintingSheet()
	{
		this.columns = new int[Constants.columns]; 
	}
	
	public PrintingSheet(int[] _ordersOnThisSheet)
	{
		this.columns = _ordersOnThisSheet;
	}
	
	public void setOrderToColumn(Order _order, int _column)
	{
		this.columns[_column] = _order.getId();
	}
	
	public int getOrderIdFromColumn(int _column)
	{
		return this.columns[_column];
	}
	
	public boolean isIdenticalToSheet(PrintingSheet _sheet)
	{
		boolean matchFound = true;
		for(int i = 0; i < this.columns.length;i++)
		{
			if(this.columns[i] != _sheet.getOrderIdFromColumn(i))
			{
				matchFound = false;
			}
		}
		return matchFound;
	}
}