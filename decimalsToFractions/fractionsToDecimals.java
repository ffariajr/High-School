package decimalsToFractions;

public class fractionsToDecimals
{
	private double decimal;
	private int neum, denom;
	
	public fractionsToDecimals(int neumerator, int denominator)
	{
		neum = neumerator;
		denom = denominator;
	}
	
	public int getNeumerator()
	{
		return neum;
	}
	
	public int getDenominator()
	{
		return denom;
	}
	
	public double decimalAnswer()
	{
		return neum/(denom*1.0);
	}
		
	public void simplify()
	{
		boolean canSimp = true;
		for(int z = 2; z < Math.max(neum, denom); z++)
		{
			canSimp = true;			
			do 
			{
			
				if(neum / z == (int)(neum / z ) && denom / z == (int)(denom / z ))
				{
					neum /= z;
					denom /= z;
				}
				else 
					canSimp = false;	
			
			}while(canSimp = true);
		}
	}
	
}
