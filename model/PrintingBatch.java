package sorta.model;

import java.util.Vector;
import sorta.Constants;

public class PrintingBatch 
{
	private PrintingColumn[] columns;
	private Vector<PrintingSheet> sheets;
	
	public PrintingBatch()
	{
		this.columns = new PrintingColumn[Constants.columns];
	}
	
	public PrintingColumn getPrintingColumn(int _pos)
	{
		return this.columns[_pos];
	}
	 
	public void addOrderToCloumn(Order _order, int _column)
	{
		this.columns[_column].addOrder(_order);
	}
	
	private int getMaxColumnHeight()
	{
		int maxHeight=0;
		for(int i=0; i < Constants.columns; i++)
		{
			if(this.columns[i].getHeight() > maxHeight)
			{
				maxHeight = this.columns[i].getHeight();
			}
		}
		return maxHeight;
	}
	
	public void generateSheetsFromColumns()
	{
		this.sheets.clear();
		for(int i =0; i < this.getMaxColumnHeight();i++)
		{
			int[] ordersForSheet = new int[Constants.columns];
			for(int j = 0; j < Constants.columns;j++)
			{
				ordersForSheet[j] = this.columns[j].getOrderAtHeight(i);
			}
			PrintingSheet newSheet = new PrintingSheet(ordersForSheet);
			this.sheets.add(newSheet);
		}
	}
	
	public int getUsedSheets()
	{
		return this.sheets.size();
	}
	
	public int getDifferentSheetCount()
	{
		int diffCount = this.sheets.size();
		for(int i = 1; i < this.sheets.size();i++)
		{
			if(this.sheets.elementAt(i).isIdenticalToSheet(this.sheets.elementAt(i-1)))
			{
				diffCount--;
			}
		}
		return diffCount;
	}
}
