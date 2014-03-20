package sorta.controller.spotea;

import sorta.model.*;
import sorta.*;

public class SpoteaRecombination 
{
	private PrintingPartion[] children;
	private int populationSize;	
	
	public SpoteaRecombination(PrintingPartion[] _parents, int _populationSize)
	{
		this.populationSize = _populationSize;
		this.children = new  PrintingPartion[this.populationSize];
		for(int i = 0; i < _parents.length; i++)
		{
			this.children[i] = _parents[i].getClone();
		}
		for(int i = _parents.length; i < _populationSize; i++)
		{
			int selectedParentOnePos = (int) (RandomGenerator.getRandom() * _parents.length);
			this.children[i] = _parents[selectedParentOnePos].getClone();
		}
	}
	
	public PrintingPartion[] getChildren()
	{
		return this.children;
	}
}
