package sorta;

public class MathCalculator 
{
	public static int sum0toN(int n)
	{
		int sum = 0;
		for(int i = 0; i < n; i++)
		{
			sum += i;
		}
		return sum;
	}
}
