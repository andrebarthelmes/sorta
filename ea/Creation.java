package sorta.ea;

import sorta.Constants;
import sorta.model.Order;
import sorta.model.OrderList;
import sorta.model.PrintingPlan;

public class Creation 
{
	private PrintingPlan[] population;
	private int optimumColumHeightPerBatchAndColumn;
	private int[][] sheetsLeft;
	private int sheetsLeftSum;
	
	public Creation(int _populationSize, OrderList orderList) 
	{
		this.population = new PrintingPlan[_populationSize];
		for(int i = 0; i < _populationSize; i++)
		{
			PrintingPlan newPlan = this.getRandomPrintingPlan(orderList);
			this.population[i] = newPlan;
		}
	}

	private PrintingPlan getRandomPrintingPlan(OrderList _orderList) 
	{
		PrintingPlan newPlan = new PrintingPlan();
		int batchSize = this.getNewStartingBatchNumber(_orderList.getOrderListLength());
		int cardsToPrint = _orderList.getCardCountSum();
		this.optimumColumHeightPerBatchAndColumn = (int) cardsToPrint/(batchSize*Constants.columns);
		for(int i = 0; i < batchSize; i++)
		{
			newPlan.addPrintingBatch();
		}
		this.sheetsLeft = new int[batchSize][Constants.columns];
		this.sheetsLeftSum = 0;
		for(int i = 0; i < batchSize; i++)
		{
			for(int j = 0; j < Constants.columns; j++)
			{
				this.sheetsLeft[i][j] = this.optimumColumHeightPerBatchAndColumn;
				this.sheetsLeftSum += this.optimumColumHeightPerBatchAndColumn;
			}
		}
		for(int i = 0; i < _orderList.getOrderListLength(); i++)
		{
			this.placeOrderInPlan(newPlan,_orderList.getOrder(i));
		}
		return newPlan;
	}

	private void placeOrderInPlan(PrintingPlan newPlan, Order order) 
	{
		
	}

	private int getNewStartingBatchNumber(int _numberOfOrders) 
	{		
		int batchNumber = (int) Math.round((_numberOfOrders/(Constants.columns*1.0*Constants.startingColumnsOrderHeight)+0.5));
		return batchNumber;
	}

	public PrintingPlan[] getStartingPopulation() 
	{
		return this.population;
	}
}
