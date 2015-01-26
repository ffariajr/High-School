package drunkard;

public class Drunkard
{
	private int xpos,ypos;
	double distance = 0;
	
	/**
	 * constructs a drunkard that starts at (0, 0)
	 */
	public Drunkard()
	{
		xpos = 0;
		ypos = 0;
	}
	
	/**
	 * moves the drunk dude in a random direction 1 space
	 * @param direction
	 */
	public void move()
	{
		int direction = (int) (Math.random() * 5);
		switch(direction)
		{
			case 0:
				ypos = ypos - 1;
			case 1:
				xpos = xpos + 1;
			case 2:
				ypos = ypos + 1;
			case 3:
				xpos = xpos - 1;
		}
		distance = Math.sqrt(xpos*xpos - ypos*ypos);
	}
	
	/**
	 * @return the x coordinate
	 */
	public int getX()
	{
		return xpos;
	}

	/**
	 * @return the y coordinate
	 */
	public int getY()
	{
		return ypos;
	}
}
