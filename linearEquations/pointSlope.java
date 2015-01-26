package linearEquations;

import storageObjects.Point;

public class pointSlope
{
	private Point thePoint;
	private double slope;
	
	public pointSlope()
	{
		thePoint = new Point(0,0);
		slope = 1;
	}
	
	public pointSlope(Point userPoint, double userSlope)
	{
		thePoint = userPoint;
		slope = userSlope;
	}
	
	public double getSlope()
	{
		return slope;
	}
	
	public Point getPoint()
	{
		return thePoint;
	}
	
	public slopeIntercept toSlopeIntercept()
	{
		double c = - (thePoint.getX()*slope)+ thePoint.getY();
		slopeIntercept formated = new slopeIntercept(slope, c);
		return formated;	
	}
	
	public standard toStandard()
	{
		slopeIntercept transFormated = this.toSlopeIntercept();
		standard formated = transFormated.toStandard();
		return formated;
		
		
	}
}
