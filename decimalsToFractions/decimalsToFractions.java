package decimalsToFractions;

import storageObjects.Point;

public class decimalsToFractions
{
	private int neum, denom;
	
	public decimalsToFractions(double x)
	{
		int integer = (int)x;
		String dec = String.valueOf(x-integer);
		int decimal = (int) ((x-integer)*(Math.pow(10,(dec.length()-2)))); 
		
		denom = (int) (Math.pow(10,(dec.length()-1)));
		neum = decimal+integer*denom;
	}
	
	public int getNeumerator()
	{
		return neum;
	}
	
	public int getDenominator()
	{
		return denom;
	}
	
	public Point fraction()
	{
		Point frac = new Point(neum,denom);
		return frac;
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
