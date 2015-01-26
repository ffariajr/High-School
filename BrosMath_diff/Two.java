package BrosMath_diff;

public class Two 
{
	private int x,y;

	public Two()
	{
		x=0;
		y=0;
	}
	
	public Two(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int setX(int x)
	{
		int temp = x;
		this.x = x;
		return temp;
	}
	
	public int setY(int y)
	{
		int temp = y;
		this.y = y;
		return temp;
	}
}
