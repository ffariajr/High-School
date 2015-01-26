package linearEquations;

import storageObjects.Point;

public class Quadratic
{
	private double a,b,c;
	
	
	/*
	 * this gets passed terms when the quadratic is in the form of y = axx + bx + c
	 * a =1, b=1, c=0
	 */
	public Quadratic()
	{
		a=1;
		b=1;
		c=0;
	}
	/*
	 * this gets passed terms when the quadratic is in the form of y = axx + bx + c
	 * pass in a then b then c
	 */
	public Quadratic(double ax, double bx, double cx)
	{
		if(ax == 0 || bx == 0)
		{
			ax = 1;
			bx = 1;
			System.out.println("Error: not quadratic equation or monomial expression");
		}
		else
		{
			a=ax;
			b=bx;
		}
		c=cx;
	}
	
	public double getA()
	{
		return a;
	}
	
	public double getB()
	{
		return b;
	}
	
	public double getC()
	{
		return c;
	}
	
	public Point vertex()
	{
		double y=-c + ((b*b)/(4*a*a));
		double x = b/(2*a);
		
		Point vertex = new Point(x,y);
		return vertex;
		
	}
	
	public boolean discriminentBoolean()
	{
		if(b*b == 4*a*c || b*b > 4*a*c)
			return true;
		else
			return false;
		
	}
	
	/**
	 * 
	 * @return a point object, the first term is the coordinate of the first x intercept coordinate
	 * the second term is the coordinate of the second x intercept coordinate
	 * !!!!if return value is -999.999 then there is no return value!!!!
	 */
	
	public Point xIntercepts()
	{
		
		if(this.discriminentBoolean() == true)
		{
			double xMinus = (-b - Math.sqrt(b*b - 4*a*c))/2*a;
			double xPlus = (-b + Math.sqrt(b*b - 4*a*c))/2*a;
			Point xIntercepts = new Point(xMinus, xPlus);
			return xIntercepts;
		}
		else
			return new Point(-999.999,-999.999);
	}
	
	public Point yIntercept()
	{
		Point yIntercept = new Point(0, c);
		return yIntercept;
	}
	
	public Point symmetricalPoint()
	{
		Point vert = this.vertex();
		double x = vert.getX();
		x*=2;
		
		Point symPoint = new Point(x,this.yIntercept().getY());
		return symPoint;
	}
}
