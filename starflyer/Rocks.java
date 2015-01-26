package starflyer;

import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.*;

public class Rocks
{
	private int size;
	private LinkedList<Rock> rocks;
	private int length;
	
	public Rocks(int size)
	{
		length=size;
		this.size=0;
		rocks = new LinkedList<Rock>();
	}
	
	public boolean add(Rock r)
	{
		if(size == length)
			return false;
		rocks.add(r);
		size++;
		return true;
		
	}
	
	public boolean remove(Rock r)
	{
		if(size == 0)
			return false;
		rocks.remove(r);
		size--;
		return true;
	}
	
	public Object[] toRock()
	{
		return rocks.toArray();
	}
	
	public void move()
	{
		for(Rock r:rocks)
			r.next();
	}
	
	public int getLength()
	{
		return length;
	}
	
	public void increase()
	{
		length +=5;
	}
	
	public boolean hit(Point p)
	{
		for(Rock r:rocks)
			if((new Ellipse2D.Double(r.x(),r.y(),10,10)).contains(p.x, p.y))
				return true;
			else if((new Ellipse2D.Double(r.x(),r.y(),10,10)).contains(p.x-10, p.y+10))
				return true;
			else if((new Ellipse2D.Double(r.x(),r.y(),10,10)).contains(p.x+10, p.y+10))
				return true;
		return false;
	}
	
	public Rock hitshot(Point p)
	{
		for(Rock r:rocks)
			if((new Ellipse2D.Double(r.x(),r.y(),10,10)).contains(p.x, p.y))
				return r;
		return null;
	}
	
	public void emp()
	{
		for(Rock r:rocks)
		{
			boolean x = Math.random()>.15;
			if(x)
				remove(r);
		}
	}
}
