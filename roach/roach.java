package roach;

public class roach
{
	private int size;
	
	public roach(int initialSize)
	{
		size = initialSize;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public void grow()
	{
		size *=2;
	}
	
	public void insecticide()
	{
		size = (int) Math.round(size*.9);
	}
	
	
	
}
