package pingpong;

import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Point2D.Double;

import javax.swing.JOptionPane;

public class Ball
{
	public Point2D.Double ball;
	private int angle;
	private double speed;
	
	public Ball(Point p)
	{
		ball = new Point2D.Double(p.x, p.y);
		angle = (int) (Math.random()*360);
		speed = .5;
	}
	
	public Point point()
	{
		return new Point((int)ball.x, (int)ball.y);
	}
	
	public int x()
	{
		return (int) ball.x;
	}
	
	public int y()
	{
		return (int) ball.y;
	}
	
	public void speed(int speed)
	{
		this.speed = speed;
	}
	
	public void angle(int angle)
	{
		this.angle = angle;
		while(angle < 0)
			angle += 360;
		while(angle >= 360)
			angle -= 360;
	}
	
	public void bounceHorizontal()
	{
		//negy = -negy;
		
		if(ball.x > 600)
			ball.x = 600;
		if(ball.x < 0)
			ball.x = 0;
		if(ball.y > 600)
			ball.y = 600;
		if(ball.y < 0)
			ball.y = 0;
		
		
		if(bounced)
			return;
		bounced = true;
		//System.out.print("                     horizontaly, current angle: "+angle+"  new angle: ");
		
		angle = 360-angle;
		
		//System.out.println(angle);
		
		if(angle < 0)
			angle += 360;
		if(angle >= 360)
			angle -= 360;
	}
	
	public int getangle()
	{
		return angle;
	}
	
	public void bounceVertical()
	{
		//negx = -negx;
		
		
		if(ball.x > 600)
			ball.x = 600;
		if(ball.x < 0)
			ball.x = 0;
		if(ball.y > 600)
			ball.y = 600;
		if(ball.y < 0)
			ball.y = 0;
		
		if(bounced)
			return;
		bounced = true;
		//System.out.print("                     verticaly, current angle: "+angle+"  new angle: ");
		
		angle = 180-angle;
		
		//System.out.println(angle);
		
		if(angle < 0)
			angle += 360;
		if(angle >= 360)
			angle -= 360;
	}
	
	private boolean bounced;
	
	private int counter;
	private void change()
	{
		counter++;
		
		if(counter < 5000)
			return;
		int x = Integer.parseInt(JOptionPane.showInputDialog("x"));
		int y = Integer.parseInt(JOptionPane.showInputDialog("y"));
		
		int angle =  Integer.parseInt(JOptionPane.showInputDialog("angle"));
		
		ball.x = x;
		ball.y = y;
		counter = 0;
		this.angle = angle;
	}
	
	public Point next()
	{		
		/*int x = Integer.parseInt(JOptionPane.showInputDialog("x"));
		int y = Integer.parseInt(JOptionPane.showInputDialog("y"));
		
		int angle =  Integer.parseInt(JOptionPane.showInputDialog("angle"));
		
		ball.x = x;
		ball.y = y;
		
		this.angle = angle;
		
		return null;*/
		
		change();
		
		double x = ball.x;
		double y = ball.y;
		
	
		bounced = false;
		
		//System.out.println("                                current x: "+x+"     current y: "+y);
		
		double newx = Math.cos((Math.PI*angle)/180.0) * speed;
		double newy = -Math.sin((Math.PI*angle)/180.0) * speed;
		
		//System.out.println("                                new x: "+(x+newx)+"     new y: "+(y+newy));
		
		ball.x = newx+x;//new Point2D.Double(newx+x, newy+y);
		ball.y = newy+y;
		
		
		return new Point((int)Math.round(ball.x), (int)Math.round(ball.y));
	}
	
	public void reset(Point p)
	{
		ball.setLocation(p.x, p.y);
		angle = (int) (Math.random()*360);
	}
}
