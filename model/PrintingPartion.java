package sorta.model;

import sorta.TestData;

public class PrintingPartion implements Comparable<PrintingPartion>
{
	private Order[][] partition;
	private double fitness;
		
	public PrintingPartion()
	{
		this.partition = new Order[TestData.spots][TestData.orderCountToTest];
	}
	
	public Order[][] getPartition()
	{
		return this.partition;
	}

	public void setPartition(Order[][] _partition)
	{
		this.partition = _partition;
	}
	
	public Order getOrder(int _pageSpot, int _orderPosition)
	{
		return this.partition[_pageSpot][_orderPosition];
	}

	public double getFitness()
	{
		return this.fitness;
	}
	
	public void setFitness(double _fitness)
	{
		this.fitness = _fitness;
	}
	
	public void add(int _selectedSpot, Order _order) 
	{
		int nextFreeOrder = this.getNextFreeOrder(_selectedSpot);
		this.partition[_selectedSpot][nextFreeOrder] = _order;		
	}
	
	public int getNextFreeOrder(int _spot)
	{
		int nextFreeOrder = -1;
		do
		{
			nextFreeOrder++;
		} while(this.partition[_spot][nextFreeOrder] != null);
		return nextFreeOrder;
	}
	
	public void swap(int _spotsOnPage1, int _orderPosition1, int _spotsOnPage2, int _orderPosition2)
	{
		Order temp = this.partition[_spotsOnPage1][_orderPosition1];
		this.partition[_spotsOnPage1][_orderPosition1] = this.partition[_spotsOnPage2][_orderPosition2];
		this.partition[_spotsOnPage2][_orderPosition2] = temp;
	}

	public PrintingPartion getClone() 
	{
		PrintingPartion clonedPrintingPartion = new PrintingPartion();
		clonedPrintingPartion.setFitness(this.fitness);
		clonedPrintingPartion.setPartition(this.getPartition());
		return clonedPrintingPartion;
	}
	
	@Override
	public int compareTo(PrintingPartion _anotherPrintingPartion) 
	{
		int return_;
		if(this.getFitness() < _anotherPrintingPartion.getFitness())
		{
			return_ =  1;
		} else if(this.getFitness() > _anotherPrintingPartion.getFitness())
		{
			return_ =  -1;			
		} else {
			return_ =  0;			
		}
		return return_;
	}
	
	public String toString()
	{
		String output_ = "##### Printing Partition #####\r\n";		
		int[] spotSum = new int[TestData.spots];
		for(int j = 0; j < TestData.orderCountToTest; j++)
		{
			int emptyColums = 0;
			for(int i = 0; i < TestData.spots; i++)
			{
				if(this.partition[i][j] != null)
				{
					String idString = String.format("%" + 3 + "s", this.partition[i][j].getId());
					String cardCountString = String.format("%" + 3 + "s", this.partition[i][j].getCardCount());					
					output_ += idString + " : " + cardCountString+ " # ";
					spotSum[i] += partition[i][j].getCardCount();
				} else {
					output_ += "___ : ___ # ";
					emptyColums++;
				}
			}
			output_ += "\r\n";
			if(emptyColums == TestData.spots)
			{
				break;
			}
		}
		int sum = 0;
		for(int i = 0; i < TestData.spots; i++)
		{
			output_ += String.format("%" + 9 + "s", spotSum[i])+" # ";
			sum += spotSum[i];
		}
		output_ += "\r\n";
		output_ += "##### Optimum : "+(int) sum / TestData.spots+ "   #####";
		output_ += "\r\n";
		output_ += "##### Fitness : "+ this.fitness + " #####";
		return output_;
	}	
}
