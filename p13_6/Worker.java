package p13_6;

/**
 * a class designed to manage a workers pay and hours worked
 * @author Fernando Jr
 */
public class Worker
{
	private String name;
	private double pay;
	private int hours;
	
	/**
	 * creates a new worker with $6.55 per hour
	 * @param name the employee's name
	 */
	public Worker(String name)
	{
		this(name,6.55);
	}
	
	/**
	 * creates a new worker with the desired pay
	 * @param name the employee's name
	 * @param pay the employee's pay
	 */
	public Worker(String name, double pay)
	{
		this.name = name;
		this.pay = ((int)(pay*100))/100.0;
		hours = 0;
	}
	
	/**
	 * @return the name of the employee
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * @return the number of hours worked in the current week
	 */
	public int getHours()
	{
		return hours;
	}
	
	/**
	 * @return the pay per hour of the employee
	 */
	public double getPay()
	{
		return pay;
	}
	
	/**
	 * adds one hour of work
	 */
	public void work()
	{
		work(1);
	}
	
	/**
	 * adds the desired hours of work
	 * @param numHours the number of hours worked
	 */
	public void work(int numHours)
	{
		hours += numHours;
	}
	
	/**
	 * resets the number of hours for a new week
	 */
	public void newWeek()
	{
		hours = 0;
	}
	
	/**
	 * computes the pay for the current week for the employee
	 * @return the pay for the current week
	 */
	public double computePay()
	{
		return ((int)(hours*pay*100))/100.0;
	}
	
	/**
	 * returns a string representation of the employee
	 */
	public String toString()
	{
		return getName()+" is payed "+getPay()+" per hour.  He has worked "+getHours()+" hours so far.";
	}
}
