package starflyer;

import java.awt.Point;

public class Rock
{
	private Point place;
	private int speed;
	private int bounds;
	private StarFly sf;
	
	public Rock(int xpos, int speed, int bounds, StarFly sf)
	{
		int mod = (int) (Math.random()*3);
		mod = mod-1;
		this.speed = speed+mod;
		this.bounds = bounds;
		this.sf=sf;
		
		place = new Point(xpos,0);
	}
	
	public void next()
	{
		place = new Point(place.x, place.y+speed);
		if(place.y >= bounds)
			sf.pop(this);
	}
	
	public int x()
	{
		return place.x;
	}
	
	public int y()
	{
		return place.y;
	}
}
