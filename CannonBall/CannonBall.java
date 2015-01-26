package CannonBall;

public class CannonBall
{
	private double interval = 0.01,xvel = 0,yvel = 0,time = 0,xpos = 0,ypos = 0; 

	public CannonBall(double angle, double velocity) 
	{ 
		xvel = velocity * Math.cos(Math.toRadians(angle)); 
		yvel = velocity * Math.sin(Math.toRadians(angle)); 
	} 
	
	public double getX() 
	{ 
		return xpos; 
	} 

	public double getY()
	{ 
		return ypos; 
	} 

	public void run() 
	{ 

		if (ypos >= 0) 
		{ 
			yvel = yvel - 9.81 * interval; 
			xpos = xpos + xvel * interval; 
			ypos = ypos + yvel * interval; 
			time = time + interval; 
		} 
	} 

	public double getTime() 
	{ 
		return time; 
	} 

}
