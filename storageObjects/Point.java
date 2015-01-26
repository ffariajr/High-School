package storageObjects;

public class Point
{
	private double x,y;
	
	public Point()
	{
		x=0;
		y=0;
	}
	
	public Point(double xnum, double ynum)
	{
		x=xnum;
		y=ynum;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}	
}