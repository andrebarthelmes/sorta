package sorta.ea;

import sorta.model.*;

public class Recombination 
{
	private PrintingPartion[] children;
	private int populaionNumber;	
	
	public Recombination(PrintingPartion[] _parents, int _populationNumber)
	{
		this.populaionNumber = _populationNumber;
		this.children = new  PrintingPartion[this.populaionNumber];
		for(int i = 0; i < _populationNumber; i++)
		{
			int selectedParentOnePos = (int) (Math.random() * _parents.length);
			this.children[i] = _parents[selectedParentOnePos].getClone();
		}		
	}
	
	public PrintingPartion[] getChildren()
	{
		return this.children;
	}
}
