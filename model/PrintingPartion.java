package sorta.model;

import java.util.Vector;
import sorta.Constants;

public class PrintingPartion implements Comparable<PrintingPartion>
{
	private Vector<Vector<Order>> partition;
	private double fitness;
	private double oldFitness;
		
	public PrintingPartion()
	{
		this.partition = new Vector<Vector<Order>>(Constants.spots);
		for(int i = 0; i < Constants.spots; i++)
		{
			Vector<Order> myVect = new Vector<Order>();
			this.partition.add(myVect);
		}
	}
	
	public Vector<Vector<Order>> getPartition()
	{
		return this.partition;
	}

	public void setPartition(Vector<Vector<Order>> _partition)
	{
		this.partition = _partition;
	}
	
	public Order getOrder(int _pageSpot, int _orderPosition)
	{
		return this.partition.elementAt(_pageSpot).elementAt(_orderPosition);
	}

	public double getFitness()
	{
		return this.fitness;
	}
	
	public double getOldFitness()
	{
		return this.oldFitness;
	}
	
	public void setOldFitness(double _oldFitness)
	{
		this.oldFitness = _oldFitness;
	}	
	
	public void setFitness(double _fitness)
	{
		this.fitness = _fitness;
	}	
	
	public void add(int _selectedSpot, Order _order) 
	{
		this.partition.elementAt(_selectedSpot).add(_order);		
	}
	
	public int getSpotOrderSize(int _spot)
	{
		return this.partition.elementAt(_spot).size();
	}
	
	public void moveOrderToOtherSpot(Order _orderToMove, int _newSpots)
	{
		this.partition.elementAt(_orderToMove.getSpot()).removeElement(_orderToMove);
		_orderToMove.setSpot(_newSpots);
		this.partition.elementAt(_newSpots).add(_orderToMove);
	}

	public PrintingPartion getClone() 
	{
		PrintingPartion myClone = new PrintingPartion();
		myClone.setFitness(this.fitness);
		myClone.setOldFitness(this.oldFitness);
		for(int i = 0; i<this.partition.size();i++)
		{
			for(int j = 0; j < this.partition.elementAt(i).size();j++)
			{
				myClone.add(i, this.partition.elementAt(i).elementAt(j));
			}
		}
		return myClone;
	}	
	
	@Override
	public int compareTo(PrintingPartion _anotherPrintingPartion) 
	{
		int return_;
		if(this.getFitness() < _anotherPrintingPartion.getFitness())
		{
			return_ =  -1;
		} else if(this.getFitness() > _anotherPrintingPartion.getFitness())
		{
			return_ =  1;			
		} else {
			return_ =  0;			
		}
		return return_;
	}
	
	public String toString()
	{
		String output_ = "##### Printing Partition #####\r\n";		
		int[] spotSum = new int[Constants.spots];
		for(int j = 0; j < Constants.orderCountToTest; j++)
		{
			int emptyColums = 0;
			for(int i = 0; i < Constants.spots; i++)
			{
				if(this.partition.elementAt(i).size() >  j && this.partition.elementAt(i).elementAt(j) != null)
				{
					String idString = String.format("%" + 3 + "s", this.partition.elementAt(i).elementAt(j).getId());
					String cardCountString = String.format("%" + 3 + "s", this.partition.elementAt(i).elementAt(j).getCardCount());					
					output_ += idString + " : " + cardCountString+ " # ";
					spotSum[i] += partition.elementAt(i).elementAt(j).getCardCount();
				} else {
					output_ += "___ : ___ # ";
					emptyColums++;
				}
			}
			output_ += "\r\n";
			if(emptyColums == Constants.spots)
			{
				break;
			}
		}
		int sum = 0;
		for(int i = 0; i < Constants.spots; i++)
		{
			output_ += String.format("%" + 9 + "s", spotSum[i])+" # ";
			sum += spotSum[i];
		}
		output_ += "\r\n";
		output_ += "##### Optimum : "+(int) sum / Constants.spots+ "   #####";
		output_ += "\r\n";
		output_ += "##### Fitness : "+ this.fitness + " #####";
		return output_;
	}
}
