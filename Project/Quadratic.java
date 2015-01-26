package project;

import java.awt.geom.Point2D;

/**
 * @author Fernando Jr
 * a class which takes in a quadratic equation or two linear equations
 * and returns information from them
 */
public class Quadratic
{
	private double a,b,c;
	
	/**
	 * this gets passed terms when the quadratic is in the form of y = axx + bx + c
	 * a =1, b=1, c=0
	 */
	public Quadratic()
	{
		a=1;
		b=1;
		c=0;
	}
	
	/**
	 * this gets passed terms when the quadratic is in the form of y = axx + bx + c
	 * pass in a then b then c
	 * @param axx the coefficient of the x^2 value
	 * @param bx the coefficient of the x value
	 * @param c the constant (y-intercept)
	 * @throws IllegalArgumentException (if axx is 0, the equation is not quadratic)
	 */
	public Quadratic(double axx, double bx, double c) throws IllegalArgumentException
	{
		if(axx == 0)
			throw new IllegalArgumentException("Not a quadratic equation!");
		a = axx;
		b = bx;
		this.c = c;
	}
	
	/**
	 * takes in two linear equations and multiplies them to form a quadratic equation
	 * @param e1 the first LinearEquation
	 * @param e2 the second LinearEquation
	 */
	public Quadratic(LinearEquation e1, LinearEquation e2)
	{
		a = e1.getSlope()*e2.getSlope();
		b = (e1.getSlope()*e2.getYIntercept())+(e2.getSlope()*e1.getYIntercept());
		c = e1.getYIntercept()*e2.getYIntercept();
	}
	
	/**
	 * @return the x squared coefficient
	 */
	public double getXXCoefficient()
	{
		return a;
	}
	
	/**
	 * @return the x coefficient
	 */
	public double getXCoefficient()
	{
		return b;
	}
	
	/**
	 * @return the y intercept
	 */
	public double getYIntercpet()
	{
		return c;
	}
	
	/**
	 * @return the vertex of the equation as a Point2D.Double 
	 */
	public Point2D.Double vertex()
	{
		double y=-c + ((b*b)/(4*a*a));
		double x = b/(2*a);
		
		Point2D.Double vertex = new Point2D.Double(x,y);
		return vertex;
		
	}
	
	/**
	 * @return true if there is at least one x intercept
	 * false if there are no x intercepts
	 */
	public boolean discriminentBoolean()
	{
		if(b*b == 4*a*c || b*b > 4*a*c)
			return true;
		else
			return false;
		
	}
	
	public boolean hasXIntercepts()
	{
		return this.discriminentBoolean();
	}
	
	/**
	 * @return a Point2D.Double object, the first term is the coordinate of the first x
	 *  intercept coordinate the second term is the coordinate of the second x 
	 *  intercept coordinate 
	 *  !!!!if return value is -999.999 then there are no x intercepts
	 */
	public Point2D.Double xIntercepts()
	{
		
		if(this.discriminentBoolean())
		{
			double xMinus = (-b - Math.sqrt(b*b - 4*a*c))/2*a;
			double xPlus = (-b + Math.sqrt(b*b - 4*a*c))/2*a;
			Point2D.Double xIntercepts = new Point2D.Double(xMinus, xPlus);
			return xIntercepts;
		}
		else
			return new Point2D.Double(-999.999,-999.999);
	}
	
	/**
	 * @return the y intercept of the equation
	 */
	public double yIntercept()
	{
		return c;
	}
	
	/**
	 * @return the symmetrical point to the vertex across the y axis
	 */
	public Point2D.Double symmetricalPoint()
	{
		Point2D.Double vert = this.vertex();
		double x = vert.getX();
		x*=2;
		
		Point2D.Double symPoint = new Point2D.Double(x,this.yIntercept());
		return symPoint;
	}
	
	/**
	 * returns the string representation of the quadratic equation
	 */
	public String toString()
	{
		return "y = "+a+"xx + "+b+"x + "+c;
	}
	
	/**
	 * @param other
	 * @return true if both equations are the same
	 * false otherwise
	 */
	public boolean equals(Quadratic other)
	{
		if(this.a == other.a && this.b == other.b && this.c == other.c)
			return true;
		return false;
	}
}
