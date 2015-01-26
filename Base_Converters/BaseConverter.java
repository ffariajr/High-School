package Base_Converters;

public class BaseConverter implements Comparable
{
	private int base;
	private double number;
	private int numsbase;
	private String ans;
	
	public BaseConverter(double number, int yourNumsBase, int toBase) 
	{
		base = toBase;
		numsbase = yourNumsBase;
		this.number = number;
		ans = convert();
	}
	
	public int getToBase()
	{
		return base;
	}
	
	public int getYourNumsBase()
	{
		return numsbase;
	}
	
	public double getNumber()
	{
		return number;
	}
	
	public String getAnswer()
	{
		return ans;
	}
	
	public void setNumber(int number, int yourNumsBase)
	{
		this.number = number;
		numsbase = yourNumsBase;
		ans = convert();
	}
	
	public void setToBase(int toBase)
	{
		base = toBase;
		ans = convert();
	}
	
	private String convert()
	{
		
			return null;
	}
	
	private double baseTen(double num)
	{
		if(num < 10)
			return num;
		
		String snum = (new Double(num)).toString();
		int digit = snum.charAt(snum.length()-1);
		
		return digit*Math.pow(10,snum.length()-1) + baseTen(num-(digit*Math.pow(10,snum.length()-1)));
	}

	public String toString()
	{
		return ans;
	}
	
	public int compareTo(Object o)
	{
		if((o instanceof String) || (o instanceof BaseConverter))
			return o.toString().compareTo(this.toString());
		else
			throw new IllegalArgumentException("Cannot compare BaseConverter to "+o.getClass());
	}
}
