package sorta.model;

import java.util.Vector;

public class PrintingPlan 
{
	private Vector<PrintingBatch> batches;
	
	public PrintingPlan()
	{
		this.batches = new Vector<PrintingBatch>();
	}
	
	public void addPrintingBatch()
	{
		PrintingBatch newBatch = new PrintingBatch();
		this.batches.add(newBatch);
	}
	
	public PrintingBatch getPrintingBatch(int _pos)
	{
		return this.batches.elementAt(_pos);
	}
	
	public int getUsedSheets()
	{
		int currentCount = 0;
		for(int i = 0; i < this.batches.size(); i++)
		{
			currentCount += this.batches.elementAt(i).getUsedSheets();
		}
		return currentCount;
	}
	
	public int getDifferentSheetCount()
	{
		int currentCount = 0;
		for(int i = 0; i < this.batches.size(); i++)
		{
			currentCount += this.batches.elementAt(i).getDifferentSheetCount();
		}
		return currentCount;		
	}	
}
