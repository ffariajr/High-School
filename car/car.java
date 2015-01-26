package car;

public class car
{
	private double mpg;
	private double gas = 0;
	private double tank;
	
	public car(double efficiency, double userTank)
	{
		mpg = efficiency;
		tank = userTank;
	}
	
	public void addGas(double gasoline)
	{
		if (gasoline <= tank)
			gas += gasoline;
		else
			gas += tank;
	}
	
	public double getGas()
	{
		return gas;
		
	}
	
	public boolean drive(double miles)
	{
		
		
		if (miles > 0)
		{
			gas -= miles/mpg <= gas ? miles/mpg : gas;
		
		if(miles/mpg <= gas)
			return true;
		else
			return false;
		
		}
		else
			return false;
	
	}
	
	
	
}
