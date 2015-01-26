package linearEquations;

import storageObjects.Point;
import decimalsToFractions.decimalsToFractions;

public class slopeIntercept
{
	private double b,c;
	
	
	/*
	 * this gets passed terms when the quadratic is in the form of y = bx + c
	 * b=1, c=0
	 */
	public slopeIntercept()
	{
		b=1;
		c=0;
	}
	/*
	 * this gets passed terms when the quadratic is in the form of y = bx + c
	 * pass in b then c
	 */
	public slopeIntercept(double bx, double cx)
	{
		if(bx == 0)
		{
			bx = 1;
			System.out.println("Error: not linear equation");
		}
		else
		{
			b=bx;
		}
		c=cx;
	}
	
	public double getB()
	{
		return b;
	}
	
	public double getC()
	{
		return c;
	}
	
	public Point xIntercept()
	{
		Point xIntercept = new Point(-c/b,0);
		return xIntercept;
		
	}
	
	public Point yIntercept()
	{
		Point yIntercept = new Point(0, c);
		return yIntercept;
	}
	
	public standard toStandard()
	{
		decimalsToFractions bFrac = new decimalsToFractions(b);
				
		bFrac.simplify();
		
		int d=1,e=1;
		double f=1;
		
		e = (int) bFrac.fraction().getX();
		d *= (int) bFrac.fraction().getY();
		f *= bFrac.fraction().getY();
		
		standard formated = new standard(d,e,f);
		return formated;
	}
	
	public pointSlope toPointSlope(Point userPoint)
	{
		double g = userPoint.getY();
		double h = userPoint.getX();
		
		Point newPoint = new Point(h,g);
		
		pointSlope formated = new pointSlope(newPoint, b);
		
		return formated;
	}
	
}