package project;

import java.awt.geom.Point2D;

/**
 * 
 * @author Fernando Jr
 * 
 *         a class which can take a linear equation
 * 
 *         of any type and return things from any type to the user
 */
public class LinearEquation
{
	private Point2D.Double thePoint; // holds the two points use
	private double slope; // the slope of the equation
	
	/**
	 * 
	 * an equation with slope 1 and passes through the point 0,0
	 */
	public LinearEquation()
	{
		slope = 1;
		thePoint = new Point2D.Double();
	}
	
	/**
	 * 
	 * takes in an equation of type slope intercept
	 * 
	 * (y = slope*x + yIntercept)
	 * 
	 * 
	 * 
	 * @param slope
	 *             the slope of the equation
	 * 
	 * @param yIntercept
	 *             the y intercept of the equation
	 */
	public LinearEquation(double slope, double yIntercept)
	{
		this.slope = slope;
		thePoint = new Point2D.Double(0, yIntercept);
	}
	
	/**
	 * 
	 * takes in an equation of type point slope
	 * 
	 * (y-y1 = slope(x-x1))
	 * 
	 * 
	 * 
	 * @param xCoordinate
	 *             the x coordinate in the point
	 * 
	 * @param yCoordinate
	 *             the y coordinate in the point
	 * 
	 * @param slope
	 *             the slope of the equation
	 */
	public LinearEquation(double xCoordinate, double yCoordinate, double slope)
	{
		thePoint = new Point2D.Double(xCoordinate, yCoordinate);
		this.slope = slope;
	}
	
	/**
	 * 
	 * takes an equation of type standard form
	 * 
	 * (yCoef*y + xCoef*x = constant)
	 * 
	 * 
	 * 
	 * @param xCoef
	 *             the coefficient of x
	 * 
	 * @param yCoef
	 *             the coefficient of y
	 * 
	 * @param constant
	 *             the constant on the other side of the equation
	 */
	public LinearEquation(int xCoef, int yCoef, double constant) throws IllegalArgumentException
	{
		if (yCoef == 0)
			throw new IllegalArgumentException("Not a funtion!");
		slope = (xCoef * -1.0) / yCoef;
		thePoint = new Point2D.Double(0, constant / yCoef);
	}
	
	/**
	 * 
	 * @return true if the equation is a constant function
	 * 
	 *         false if a linear function
	 */
	public boolean isLinearFunction()
	{
		return slope != 0;
	}
	
	/**
	 * 
	 * @return the y intercept of the equation
	 */
	public double getYIntercept()
	{
		return thePoint.getY() - thePoint.getX() * slope;
	}
	
	/**
	 * 
	 * @return the x intercept of the equation if the equation is not a
	 *         constant function
	 */
	public double getXIntercept() throws IllegalArgumentException
	{
		if (isLinearFunction())
			return thePoint.getX() - thePoint.getY() / slope;
		else
			throw new IllegalArgumentException("No x intercepts!!");
	}
	
	/**
	 * 
	 * @return the slope of the equation as a double
	 */
	public double getSlope()
	{
		return slope;
	}
	
	/**
	 * 
	 * if the constructor(double,double,double) was used
	 * 
	 * returns the x coordinate of the point passed in
	 * 
	 * 
	 * 
	 * if another constructor was used, the value of "0"
	 * 
	 * will be given
	 * 
	 * 
	 * 
	 * @return 0 or the x value of the point passed in
	 */
	public double getXCoordinate()
	{
		return thePoint.getX();
	}
	
	/**
	 * 
	 * if the constructor(double,double,double) was used
	 * 
	 * returns the y coordinate of the point passed in
	 * 
	 * 
	 * 
	 * if another constructor was used, the y intercept
	 * 
	 * will be given
	 * 
	 * 
	 * 
	 * @return the y intercept or the y value of the point passed in
	 */
	public double getYCoordinate()
	{
		return thePoint.getY();
	}
	
	/**
	 * 
	 * @return the coefficient of the x variable when in standard form
	 */
	public int getXCoefficient()
	{
		return (int) doubleToFraction(slope).getX();
	}
	
	/**
	 * 
	 * @return the coefficient of the y variable when in standard form
	 */
	public int getYCoefficient()
	{
		return (int) doubleToFraction(slope).getY();
	}
	
	/**
	 * 
	 * @return the constant of the the equation in standard form
	 */
	public double getConstant()
	{
		return this.getYIntercept() / doubleToFraction(slope).getY();
	}
	
	/**
	 * 
	 * @return the string representation of the equation in point slope format
	 */
	public String pointSlopeToString()
	{
		return "y - " + thePoint.getY() + " = " + slope + "(x - " + thePoint.getX() + ")";
	}
	
	/**
	 * 
	 * @return the string representation of the equation in slope intercept
	 *         format
	 */
	public String slopeInterceptToString()
	{
		return "y = " + slope + "x + " + getYIntercept();
	}
	
	/**
	 * 
	 * @return the string representation of the equation in standard form
	 */
	public String standardToString()
	{
		return "" + getYCoefficient() + "y + " + getXCoefficient() + "x = " + getConstant();
	}
	
	/**
	 * 
	 * @param x
	 *             (the slope)
	 * 
	 * @return a Point
	 * 
	 *         the x value of the point object is the numerator of
	 * 
	 *         the double passed in as an integer, and the y value
	 * 
	 *         is the denominator, also as an integer
	 */
	private Point2D.Double doubleToFraction(double x)
	{
		int integer = (int) x;
		String dec = String.valueOf(x - integer);
		int decimal = (int) ((x - integer) * (Math.pow(10, (dec.length() - 1))));
		int denom = (int) (Math.pow(10, (dec.length() - 1)));
		int neum = decimal + integer * denom;
		return simplify(new Point2D.Double(neum, denom));
	}
	
	/**
	 * simplifies a point passed in.
	 * 
	 * example: if a point(12,6) was passed in, the point(2,1)
	 * 
	 * would be returned
	 * 
	 * 
	 * @param x
	 *             (a fraction with numerator in x and denominator in y)
	 * @return a Point
	 */
	private Point2D.Double simplify(Point2D.Double x)
	{
		@SuppressWarnings("unused")
		boolean canSimp = true;
		int neum = (int) x.getX();
		int denom = (int) x.getY();
		for (int z = 2; z < Math.max(neum, denom); z++)
		{
			canSimp = true;
			do
			{
				if (neum % z == 0 && denom % z == 0)
				{
					neum /= z;
					denom /= z;
				}
				else
					canSimp = false;
			} while (canSimp = true);
		}
		return new Point2D.Double(neum, denom);
	}
	
	/**
	 * 
	 * @param other
	 * 
	 * @return true if both LinearEquation have the same equation
	 * 
	 *         false otherwise
	 */
	public boolean equals(LinearEquation other)
	{
		if (this.slope == other.slope && this.thePoint.x == other.thePoint.x
				&& this.thePoint.y == other.thePoint.y)
			return true;
		return false;
	}
}