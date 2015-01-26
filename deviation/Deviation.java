package deviation;

public class Deviation
{
	private int num = 0;
	private double sum = 0, squareSum = 0;
	
	/**
	 * constructs a Mean object with no parameters
	 */
	public Deviation()
	{
	}
	
	/**
	 * gets a new number and calculates the total sum, the sum of the squares, and the number of numbers
	 * @param number
	 */
	public void newNum(double number)
	{
		sum = sum + number;
		squareSum = squareSum + (number * number);
		num++;
	}
	
	/**
	 * @return how many numbers are there
	 */
	public int getAmount()
	{
		return num;
	}
	
	/**
	 * @return the average of all the numbers
	 */
	public double getMean()
	{
		return sum / num;
	}
	
	/**
	 * computes and returns the Standard Deviation of the numbers inputed
	 * @return
	 */
	public double getDeviation()
	{
		return Math.sqrt(((sum*sum) - (1 / num) * (sum*sum))/(num-1));
	}
}
