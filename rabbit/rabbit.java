package rabbit;

public class rabbit
{
	/**
	 * size is the size of the population
	 * older is the population that is able to mate
	 * newer is the newest generation
	 */
	private int size, older, newer;
	
	/**
	 * 
	 * @param initialSize is the size at which the user chooses to make the population begin
	 */
	public rabbit(int initialSize)
	{
		size = initialSize;
		newer = Math.abs(size);			//just in case the user gives a negative number
		if(size%2==1)					//just in case the user gives an odd number
			size--;
	}

	/**
	 * 
	 * @return the current size of the population
	 */
	public int getSize()
	{
		return size;
	}

	/**
	 * a purpose is to conserve the value of newer before changing it
	 * i made the new generation not be able to mate
	 */
	public void waitMonth()
	{
		int a;
		a = newer;
		newer = older;
		older +=a;
		size = older + newer;
	}






}
