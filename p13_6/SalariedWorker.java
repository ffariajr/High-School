package p13_6;

/**
 * a class the manages an employee paid by salary
 * @author Fernando Jr
 */
public class SalariedWorker extends Worker
{
	/**
	 * a worker that will be payed $262 every week
	 * @param name the employee's name
	 */
	public SalariedWorker(String name)
	{
		super(name);
	}
	
	/**
	 * a worker that will be payed the desired weekly salary
	 * @param name the employee's name
	 * @param hourlySalary the hourly salary of the employee
	 */
	public SalariedWorker(String name, double hourlySalary)
	{
		super(name, hourlySalary);
	}
	
	/**
	 * computes the pay for the worker
	 * pay is the hourly salary * 40
	 */
	public double computePay()
	{
		return getPay()*40;
	}
	
	
	public String toString()
	{
		return getName()+" is payed "+computePay()+" per week.  He has worked "+getHours()+" hours so far.";
	}
}
