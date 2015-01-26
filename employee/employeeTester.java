package employee;


public class employeeTester
{
	public static void main(String[] args)
	{
		employee worker = new employee("jim", 1.5);
		
		System.out.println("" + worker.getName());
		System.out.println("" + worker.getSalary());
		
		
	}
}
