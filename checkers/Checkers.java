package checkers;

import java.awt.*;
import javax.swing.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

public class Checkers extends JApplet implements MouseListener
{
	/**
	 * @boy harold
	 */
	private static final long serialVersionUID = 112229L;
	private int [][] spots = new int[8][8];
	private int turn = 1;

	
	

	/**
	 * you know what this does
	 * initializes the Apples
	 */
	public void init()
	{
		JRootPane rootPane = this.getRootPane();
		rootPane.putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
		addMouseListener(this);
		
	}
	
	/**
	 * this was a hard method to write, i tried to keep it simple and short
	 * starts the Apples
	 */
	public void start()
	{
		//helo welcom 2 mi proram i maed it
		//hope u lik
		
		for(int x = 0; x<8;x+=2)
			for(int y = 0;y<3;y++)
			{
				if(y == 1)
					x++;
				spots[x][y] = -1;
				if(y == 1)
					x--;
			}
				
				
		for(int x = 0; x<8;x+=2)
			for(int y = 5;y<8;y++)
			{
				if(y == 5 || y == 7)
					x++;
				spots[x][y] = 1;
				if(y == 5 || y ==7)
					x--;
			}
	}
	
	
	////////////////////////////@author andy slepman and fernando;;;;;;;;;;;;;;;;;;;;;;

	
	/**
	 * paints it, paints it real good
	 * @elephant tony
	 */
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		Rectangle boardmayka;
		
		Rectangle erase = new Rectangle(0,0,getWidth(), getHeight());
		g2.setColor(Color.white);
		g2.fill(erase);
		//so...
		for(int x = 0; x<8;x++)
		{
			for(int y = 0;y<8;y=y+2)
			{
				boardmayka = new Rectangle(getWidth()*x/8, getHeight()*y/8,getWidth()/8, getHeight()/8);
				if(x%2 == 0)
					g2.setColor(Color.white);
				else
					g2.setColor(Color.gray);
				g2.fill(boardmayka);
				boardmayka = new Rectangle(getWidth()*x/8, getHeight()*(y+1)/8,getWidth()/8, getHeight()/8);
				if(x%2 == 0)
					g2.setColor(Color.gray);
				else
					g2.setColor(Color.white);
				g2.fill(boardmayka);
			}
		}
		
		//alright!!!
		
		//dang boy lookit dat checka board
		for(int x = 0; x<8;x++)	
		{
			for(int y = 0;y<8;y++)
				if(spots[x][y] <0)
				{
					Ellipse2D circle = new Ellipse2D.Double(getWidth()*x/8, getHeight()*y/8,getWidth()/8, getHeight()/8);
					//hilarious
					g2.setColor(Color.black);
					g2.fill(circle);
					//System.out.println('helo'');;
					//oh no horrible code	
				}
				else if(spots[x][y] >0)
				{
					Ellipse2D circle = new Ellipse2D.Double(getWidth()*x/8, getHeight()*y/8,getWidth()/8, getHeight()/8);
					g2.setColor(Color.red);
					g2.fill(circle);
				}
			}	
		}
	

	
	/**
	 * listens for the mouse, and does some stuffs with it
	 */
	public void mouseClicked(MouseEvent e)
	{
	}
	
	boolean done =false;
	public void mouseEntered(MouseEvent e) 
	{
		//while(done)
			//done = JOptionPane.showConfirmDialog(null, "yes?","Question", JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.OK_OPTION? false:true;
	}
	
	/**
	 * @zebra jake
	 */
	public void mouseExited(MouseEvent e) 
	{
		done = true;
		if(pressed)
		{
			spots[placex][placey] = spot;
			pressed = false;
			repaint();
		}
	}

	int spot,placex,placey;
	boolean pressed;
	public void mousePressed(MouseEvent e) 
	{
		int x = e.getX();
		int y = e.getY();
		
		x = x/(getWidth()/8);
		y = y/(getHeight()/8);
		
		if((turn == 1 && spots[x][y]>0)||(turn == -1 && spots[x][y]<0))
		{
			//if(where(true, false, true, false) >-1)
			//	if(x != where(true, false, true, false) || y != where(true, false, false, true))
			//		return;
			pressed = true;
			spot = spots[x][y];
			spots[x][y] = 0;
			placex = x;
			placey = y;
			repaint();
			turn *=-1;
		}
	}
	
	/**
	 * finds the place where the piece must be moved to
	 * ******((((((only one true must be passed in for each pair)))))*****
	 * @param giveNow give the value (x or y) for the pressed
	 * @param giveLater give the value (x or y) for the release
	 * 
	 * @param giveX give x value
	 * @param giveY give y value
	 * 
	 * @return -1 if no moves must be made 
	 */
	private int where(boolean giveNow, boolean giveLater, boolean giveX, boolean giveY)
	{
		int resultx = 0;
		int resulty = 0;
		
		for(int x =0;x<8;x++)
		{
			for(int y =0;y<8;y++)
			{
				for(int xs = x-1;xs<x+2;x++)
				{
					for(int ys = y-1;ys<y+2;y++)
					{
						if(xs>-1 && xs<8 && ys>-1 &&ys<8)
						{
							if(enemy(spots[x][y], spots[xs][ys]) && blank(x,y,xs,ys) && ((turn == 1 && spots[x][y]>0)||(turn == -1 && spots[x][y]<0)))
							{
								if(giveNow && giveX)
									return x;
								else if(giveNow && giveY)
									return y;
								else if(giveLater && giveX)
									return xs;
								else if(giveLater && giveY)
									return ys;
							}
						}
					}
				}
			}
		}
		return -1;
	}
	
	public void mouseReleased(MouseEvent e) 
	{
		
		int x = e.getX();
		int y = e.getY();
		
		x = x/(getWidth()/8);
		y = y/(getHeight()/8);
		
		//if(where(false, true, true, false) >-1)
		//	if(x != where(false, true, true, false) || y != where(false, true, false, true))
		//	{
		//		pressed = false;
		//		turn*=-1;
		//		spots[placex][placey] = spot;
		//		return;
		//	}
		
		
		if(pressed && (((spot == -1 && y == placey+1 && (x == placex-1 || x == placex+1)) || (spot == -2 && (y == placey-1 || y == placey+1)&& (x == placex-1 || x == placex +1))) || ((spot == 1 && y == placey-1 && (x == placex-1 || x == placex+1)) || (spot == 2 && (y == placey-1 || y == placey+1) && (x==placex+1 || x == placex-1)))) && spots[x][y] == 0)
		{
			if((spot == -1 && y == 7)|| (spot == 1 && y== 0))
				spot += spot;
			spots[x][y] = spot;
		}
		else if(pressed && enemy(spot, spots[x][y]) && blank(placex, placey, x, y)&& (((spot == -1 && y == placey+1 && (x == placex-1 || x == placex+1)) || (spot == -2 && (y == placey-1 || y == placey+1)&& (x == placex-1 || x == placex +1))) || ((spot == 1 && y == placey-1 && (x == placex-1 || x == placex+1)) || (spot == 2 && (y == placey-1 || y == placey+1) && (x==placex+1 || x == placex-1)))))
		{
			int x2 = x + (x-placex);
			int y2 = y + (y-placey);
			if((spot == -1 && y2 == 7)|| (spot == 1 && y2== 0))
				spot += spot;
			spots[x2][y2] = spot;
			spots[x][y] = 0;
		}
		else if(pressed)
		{
			spots[placex][placey] = spot;
			turn*=-1;
		}
		pressed = false;
		repaint();
	}
	
	private boolean enemy(int spot, int other)
	{
		return ((spot<0 && other>0) || (spot>0 && other<0));		
	}
	
	private boolean blank(int placex, int placey, int nextx, int nexty)
	{
		int x2 = nextx + (nextx-placex);
		int y2 = nexty + (nexty-placey);
		
		return (x2 > -1 && x2 < 8 && y2 > -1 && y2 < 8 && spots[x2][y2] == 0);
	}
	
	/*
	 * finishes the progruhm
	 * @return progruhm
	 */
}
