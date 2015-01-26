package employee;

public class employee
{
	private String name;
	private double salary;
	
	public employee(String userName, double userSalary)
	{
		name = userName;
		salary = userSalary;
		
	}
	
	public String getName()
	{
		return name;
		
	}
	
	public double getSalary()
	{
		return salary;
		
	}
}
