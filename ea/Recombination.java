package sorta.ea;

import sorta.model.*;

public class Recombination 
{
	private PrintingPartion[] children;
	private int populationSize;	
	
	public Recombination(PrintingPartion[] _parents, int _populationSize)
	{
		this.populationSize = _populationSize;
		this.children = new  PrintingPartion[this.populationSize];
		for(int i = 0; i < _parents.length; i++)
		{
			this.children[i] = _parents[i];
		}
		for(int i = _parents.length; i < _populationSize; i++)
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
