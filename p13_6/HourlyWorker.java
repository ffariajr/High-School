package p13_6;

/**
 * a class to manage a worker based on his wage, and overtime pay
 * @author Fernando Jr
 */
public class HourlyWorker extends Worker
{
	/**
	 * creates a new worker with a wage of $6.55 per hour
	 * @param name the employee's name
	 */
	public HourlyWorker(String name)
	{
		super(name);
	}
	
	/**
	 * creates a new worker with the desired pay per hour
	 * @param name the employee's name
	 * @param wage the employee's hourly wage
	 */
	public HourlyWorker(String name, double wage)
	{
		super(name, wage);
	}
	
	/**
	 * @return the amount of overtime worked
	 */
	public int getOvertime()
	{
		if(getHours() > 40)
			return getHours() - 40;
		return 0;
	}
	
	/**
	 * computes the pay
	 * any overtime work is paid in time and a half
	 */
	public double computePay()
	{
		return ((int)((super.computePay() + ((getOvertime()*.5)*getPay()))*100))/100.0;
	}
	
	/**
	 * returns a string representation of the employee
	 */
	public String toString()
	{
		return getName()+" is payed "+getPay()+" per hour.  He has worked "+getHours()+" hours so far.  He has worked overtime for "+getOvertime()+" hours.";
	}
}
