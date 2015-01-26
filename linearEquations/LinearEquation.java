package linearEquations;

import org.omg.CORBA.DynAnyPackage.InvalidValue;
import storageObjects.Point;

public class LinearEquation
{
	private Point thePoint;
	private double slope;
	
	public LinearEquation()
	{
		slope = 1;
		thePoint = new Point(0,0);
	}
	
	public LinearEquation(int slope, int yIntercept) throws InvalidValue
	{
		if(slope == 0)
			throw new InvalidValue("Slope is 0, not a Linear Equation!");
		this.slope = slope;
		thePoint = new Point(0,yIntercept);
	}
	
	public LinearEquation(double xCoordinate, double yCoordinate, double slope) throws InvalidValue
	{
		if(slope == 0)
			throw new InvalidValue("Slope is 0, not a Linear Equation!");
		thePoint = new Point(xCoordinate, yCoordinate);
		this.slope = slope;
	}
	
	public LinearEquation(int xCoef, int yCoef, double constant) throws InvalidValue
	{
		if(xCoef == 0)
			throw new InvalidValue("Slope is 0, not a Linear Equation!");
		if(yCoef == 0)
			throw new InvalidValue("Not a function!");
		slope = (xCoef * -1.0) / yCoef;
		thePoint = new Point(0, constant/yCoef);	
	}
	
	public double getYIntercept()
	{
		return thePoint.getY() - thePoint.getX()*slope;
	}
	
	public double getSlope()
	{
		return slope;
	}
	
	public double getXIntercept()
	{
		return thePoint.getX() - thePoint.getY()/slope;
	}
	
	public double getXCoordinate()
	{
		return thePoint.getX();
	}
	
	public double getYCoordinate()
	{
		return thePoint.getY();
	}
	
	public int getXCoeficient()
	{
		return (int) doubleToFraction(slope).getX();
	}
	
	public int getYCoeficient()
	{
		return 1;
	}
	
	public double getConstant()
	{
		return this.getYIntercept()/doubleToFraction(slope).getY();
	}
	
	private Point doubleToFraction(double x)
	{
		int integer = (int)x;
		String dec = String.valueOf(x-integer);
		int decimal = (int) ((x-integer)*(Math.pow(10,(dec.length()-1)))); 
		
		int denom = (int) (Math.pow(10,(dec.length()-1)));
		int neum = decimal+integer*denom;
		
		return simplify(new Point(neum,denom));
	}
	
	private Point simplify(Point x)
	{
		@SuppressWarnings("unused")
		boolean canSimp = true;
		int neum = (int) x.getX();
		int denom = (int) x.getY();
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
		return new Point(neum, denom);
	}
}