package linearEquations;

import storageObjects.Point;

public class standard
{
	private int d,e;
	private double f;
	
	public standard()
	{
		d=1;
		e=1;
		f=1;
	}
	/*
	 * d*y + e*x = f
	 */
	public standard(int dx, int ex, double fx)
	{
		d=dx;
		e=ex;
		f=fx;
	}
	
	public int getD()
	{
		return d;
	}
	
	public int getE()
	{
		return e;
	}
	
	public double getf()
	{
		return f;
	}
	
	public slopeIntercept toSlopeIntercept()
	{
		double b = -e/d;
		double c = f/d;
		slopeIntercept formated = new slopeIntercept(b,c);
		return formated;
	}
	
	public pointSlope toPointSlope(Point userPoint)
	{
		slopeIntercept transFormated = this.toSlopeIntercept();
		pointSlope formated = transFormated.toPointSlope(userPoint);
		return formated;
	}
}
