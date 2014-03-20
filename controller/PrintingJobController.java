package sorta.controller;

import sorta.model.*;

public class PrintingJobController 
{
	private PrintingJob printingJob;
	
	public PrintingJobController(int _batchSize, PrintingPartion _partition)
	{
		this.printingJob = new PrintingJob(_batchSize);
		for(int i = 0; i < _batchSize; i++)
		{
			for(int j = 0;j < 1;j++)
			{
				this.placePartionSlotInPrintingJob(i,j,_partition);
			}			
		}
	}
	
	private void placePartionSlotInPrintingJob(int _batch, int _spot, PrintingPartion _partition)
	{
		for(int i = 0; i < _partition.getSpotOrderSize(_spot); i++)
		{
			this.printingJob.addOrderToBatchAndSpot(_partition.getOrder(_spot, i), _batch, _spot);
		}
	}
}
