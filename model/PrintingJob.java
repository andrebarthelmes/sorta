package sorta.model;

import java.util.Vector;

public class PrintingJob 
{
	private  Vector<Vector<PrintingSheet>> printingSheets;
	
	public PrintingJob(int _batchSize)
	{
		this.printingSheets = new Vector<Vector<PrintingSheet>>(_batchSize);
	}
	
	public void addOrderToBatchAndSpot(Order _order, int _batch, int _spot)
	{
		boolean addSheet = !(printingSheets.size() > 0); // size = 0 => add = true
		int currentSheet = 0;
		for(int i = 0; i < _order.getCardCount(); i++)
		{
			boolean sheetFound = false;
			while(!sheetFound)
			{
				if(addSheet == false && printingSheets.elementAt(_batch).elementAt(currentSheet) != null)
				{
					if(printingSheets.elementAt(_batch).elementAt(currentSheet).isOrderAssignedToSpot(_spot))
					{
						//can't put page here, go another round;
					} else {
						printingSheets.elementAt(_batch).elementAt(currentSheet).setOrderPageToSpot(_order, i, _spot);
						sheetFound = true;
					}
				} else {
					addSheet = true;
					PrintingSheet mySheet = new PrintingSheet();
					mySheet.setOrderPageToSpot(_order, i, _spot);					
					this.printingSheets.elementAt(_batch).add(mySheet);
					sheetFound = true;
				}
				currentSheet++;
			}
		}
	}
	
	public String toString()
	{
		String output = "";
		return output;
	}
}
