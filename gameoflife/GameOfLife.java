package gameoflife;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameOfLife extends JComponent
{
	public static void main(String[] args)
	{
		int x;
		GameOfLife g;
		try
		{
			x = Integer.parseInt(JOptionPane.showInputDialog("Number of squares"));
			g = new GameOfLife(x);
		}
		catch(Exception e)
		{
			g = new GameOfLife();
		}
		//GameOfLife g = new GameOfLife();
		g.play();
	}
	
	private JFrame f;
	private boolean[][] map;
	private Mouse mice;
	private int itr;
	private int pieces;
	private boolean go;
	private int state;  //if -1 pause, if 0 step, if 1 play 
	private boolean[][] maptemp;
	private int generations;
	private int squares;
	private int add;
	
	public GameOfLife()
	{
		this(55);
	}
	
	public GameOfLife(int size)
	{	if(size<55)
			size=55;
		squares = size;
		map = new boolean[squares][squares];
		f = new JFrame();
		//f.setBounds(100, 100, squares0*10+7, squares*10+75);            //school
		f.setBounds(100, 100, squares*10+7, squares*10+100);              //home
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setVisible(true);
		f.add(this);
		mice = new Mouse();
		f.addMouseListener(mice);
		f.addMouseMotionListener(mice);
		f.setTitle("Game Of Life");
		itr = 0;
		pieces = 0;
		go = false;
		state=-1;
		generations=0;
		add = 50;
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.blue);
		for(int x=0;x<squares;x++)
			for(int y=0;y<squares;y++)
				if(map[x][y])
					g2.fillOval(x*10, y*10, 10, 10);
		
		g2.setColor(Color.black);
		for(int x=0;x<squares+1;x++)
			g2.drawLine(x*10, 0, x*10, squares*10);
		for(int y=0;y<squares+1;y++)
			g2.drawLine(0, y*10, squares*10, y*10);
		
		g2.setColor(Color.blue);
		g2.fillRect(10, squares*10, 100, squares);
		g2.setColor(Color.black);
		g2.drawString("Fill Random", 30, squares*10+25);
		
		g2.setColor(Color.red);
		g2.fillRect(200, squares*10, 75, squares);
		g2.setColor(Color.black);
		g2.drawString("Play", 225, squares*10+25);
		
		g2.setColor(Color.blue);
		g2.fillRect(300, squares*10, 75, squares);
		g2.setColor(Color.black);
		g2.drawString("Clear", 325, squares*10+25);
		
		g2.setColor(Color.red);
		g2.fillRect(400, squares*10, 75, squares);
		g2.setColor(Color.black);
		g2.drawString("Next", 422, squares*10+25);
		
		g2.setColor(Color.red);
		g2.fillRect(500, squares*10, 15, 15);
		g2.setColor(Color.black);
		g2.drawString("F", 505, squares*10+13);
		
		g2.setColor(Color.red);
		g2.fillRect(500, squares*10+30, 15, 15);
		g2.setColor(Color.black);
		g2.drawString("S", 505, squares*10+25+16);
		
		g2.drawString("p="+pieces, 125, squares*10+10);
		g2.drawString(theState(), 125, squares*10+25);
		g2.drawString("g="+generations, 125, squares*10+40);
		g2.drawString("s="+(40-(add/5)), 500, squares*10+25);
	}
	
	private String theState()
	{
		if(state == 1)
			return "playing";
		return "paused";
	}
	
	public boolean spawn(int x, int y)
	{
		if(map[x][y])
			return false;
		pieces++;
		return (map[x][y] = true);
	}
	
	public boolean die(int x, int y)
	{
		if(!map[x][y])
			return false;
		pieces--;
		return !(map[x][y] = false);
	}
	
	private void killAll()
	{
		map = new boolean[squares][squares];
		pieces = 0;
		generations = 0;
		state = -1;
	}
	
	private void actAll()
	{
		maptemp = new boolean[squares][squares];
		for(int x = 0;x<squares;x++)
			for(int y = 0;y<squares;y++)
				maptemp[x][y] = map[x][y];
		
		generations++;
		for(int x = 0;x< squares;x++)
			for(int y = 0;y<squares;y++)
				act(x,y);
	}
	
	/**
	 * runs check and removes or places a piece as directed by check
	 * 
	 * @param x the x position in the map
	 * @param y the y position in the map
	 */
	private void act(int x, int y)
	{
		boolean c = check(x,y);
		if(map[x][y] && !c)
			die(x,y);
		if(!map[x][y] && c)
			spawn(x,y);
		//System.out.println(""+c+"  @  ( "+x+" , "+y+" ) ");
	}
	
	/**
	 * checks what to do to a specific piece
	 * 
	 * @param x the x position in the map
	 * @param y the y position in the map
	 * @return if(map[x][y])
	 * 			if(is over-populated or under-populated)
	 * 				false
	 * 			else true
	 * 		else
	 * 			if(over-populated)
	 * 				true
	 * 			else
	 * 				false
	 */
	private boolean check(int x, int y)
	{
		double sum = 0;
		
		for(int xt = x-1;xt<x+2;xt++)
			for(int yt = y-1;yt<y+2;yt++)
			{
				try
				{
					if(maptemp[xt][yt])
						sum++;
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					//sum+=.5;
				}
			}
		
		if(maptemp[x][y])
			sum--;
		
		if(maptemp[x][y] && (sum < 2 || sum >= 3.5))
			return false;
			
		if(!maptemp[x][y] && sum != 3)
			return false;
		
		return true;	
	}
	
	private void fillRandom()
	{
		for(int x = 0;x<squares;x++)
			for(int y = 0;y<squares;y++)
				if(Math.random() > .85)
					spawn(x,y);
	}
	
	public void play()
	{
		int adder = 0;
		while(true)
		{
			if(state == 0)
			{
				actAll();
				System.out.println("acted");
				state=-1;
			}
			if(state == 1)
			{
				actAll();
				adder = add;
			}
			f.repaint();
			
			try
			{
				Thread.sleep(adder);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			adder = 0;
			if(pieces == 0)
				state = -1;
		}
	}
	
	private class Mouse implements MouseListener, MouseMotionListener
	{	
		private Point getPoint(Point p)
		{
			//p = new Point(p.x-3,p.y-25);                       //school
			p = new Point(p.x-4,p.y-25);                       //home
			return p;
		}
		
		public void mouseClicked(MouseEvent arg0)
		{
		}

		public void mouseEntered(MouseEvent arg0)
		{
		}

		public void mouseExited(MouseEvent arg0)
		{
		}

		public void mousePressed(MouseEvent arg0)
		{
			arg0.consume();
			Point m = arg0.getPoint();
			m = getPoint(m);
			
			if(m.x >= 10 && m.x <= 110 && m.y>squares*10)
			{
				System.out.println("Fill random");
				fillRandom();
			}
			else if(m.x >= 200 && m.x <= 275 && m.y>squares*10)
			{
				System.out.print("play ");
				if(state == 1)
					state = -1;
				else
					state = 1;
				System.out.println(state);
			}
			else if(m.x >= 300 && m.x <= 375 && m.y>squares*10)
			{
				System.out.println("clear all");
				killAll();
			}
			else	if(m.x >= 400 && m.x <= 475 && m.y>squares*10)
			{
				System.out.print("next ");
				state = 0;
				System.out.println(state);
			}
			else	if(m.x >= 500 && m.x <= 515 && m.y>squares*10 && m.y<squares*10+15)
			{
				System.out.print("faster ");
				state = -1;
				add -=5;
				if(add < 0)
					add=0;
				System.out.println(state);
			}
			else	if(m.x >= 500 && m.x <= 515 && m.y>squares*10+30 && m.y<squares*10+45)
			{
				System.out.print("slower ");
				state = -1;
				add +=5;
				if(add > 200)
					add = 200;
				System.out.println(state);
			}
			else
			{
				state = -1;
				System.out.println("square");
				int x = m.x/10;
				int y = m.y/10;
				
				if(x<0 || x>squares || y<0 || y>squares)
					return;
				
				if(!die(x,y))
					spawn(x,y);
				
				if(map[x][y])
					put = true;
				else
					put = false;
			}
		}

		public void mouseReleased(MouseEvent arg0)
		{
		}

		private boolean put;
		
		public void mouseDragged(MouseEvent arg0)
		{
			arg0.consume();
			Point m = arg0.getPoint();
			m = getPoint(m);
			
			System.out.println("square dragged");
			int x = m.x/10;
			int y = m.y/10;
				
			if(x<0 || x>squares || y<0 || y>squares)
				return;
				
			if(put)
				spawn(x,y);
			else
				die(x,y);
		}

		public void mouseMoved(MouseEvent arg0)
		{
		}
	}
}
