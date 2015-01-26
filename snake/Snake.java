package snake;

import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Snake extends JComponent implements Runnable
{
	public static void main(String[] args)
	{
		int a = JOptionPane.showConfirmDialog(null, "Do you want walls?");
		if(a == JOptionPane.CANCEL_OPTION || a == JOptionPane.CLOSED_OPTION)
			System.exit(0);
		
		Snake s = new Snake(a==JOptionPane.OK_OPTION);
		
		s.play();
	}
	
	private JFrame f;
	private KeyListen keys;
	private long startTime;
	private int size;
	private int dir; //     0-up   1-right   2-down   3-left
	private boolean walls;
	private LinkedList<Point> snake;
	private Point food;
	private boolean lost;
	private Thread runner;
	private int nextDir;
	private boolean moved;
	private int number;
	private boolean initagain;
	
	private final int length = 25;
	private final int pixels = 25;
	
	public Snake(boolean walls)
	{	
		keys = new KeyListen(this);
		
		f = new JFrame();
		f.add(this);
		f.addKeyListener(keys);
		f.setBounds(250, 10, pixels*length+25, pixels*length+70);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Snake");
		f.setVisible(true);
		f.setResizable(false);
		
		startTime = (new GregorianCalendar()).getTimeInMillis();
		size = 2;
		dir = (int)(Math.random()*4);
		this.walls = walls;
		snake = new LinkedList<Point>();
		lost=false;
		moved = false;
		nextDir=-1;
		number = 0;
		initagain = false;
		
		runner = new Thread(this);
		runner.start();
		
		
		snake.add(new Point((int)(Math.random()*length),(int)(Math.random()*length)));
		Point head = snake.getFirst();
		Point tail = null;
		
		if(dir == 0)
			tail = new Point(head.x, head.y+1);
		if(dir == 1)
			tail = new Point(head.x-1, head.y);
		if(dir == 2)
			tail = new Point(head.x, head.y-1);
		if(dir == 3)
			tail = new Point(head.x+1, head.y);
		
		snake.add(tail);
		
		newFood();
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		
		g2.drawRect(10, 10, pixels*length, pixels*length);
		g2.drawString("Size: "+size, pixels*length/2-2, 10);
		
		if(number<100)
			g2.drawString(""+(10-(number/10)), pixels*length/2, pixels*length/2);
		
		g2.setColor(Color.red);
		g2.drawOval((food.x)*length+10, (food.y)*length+10, pixels, pixels);
		g2.drawRect((food.x)*length+10, (food.y)*length+10, pixels, pixels);
		g2.drawRect((food.x)*length+15, (food.y)*length+15, pixels*2/3, pixels*2/3);
		g2.drawRect((food.x)*length+20, (food.y)*length+20, pixels/3, pixels/3);
		g2.drawLine(food.x*length+10, food.y*length+10, food.x*length+10+pixels, food.y*length+10+pixels);
		g2.drawLine(food.x*length+10+pixels, food.y*length+10, food.x*length+10, food.y*length+10+pixels);
		
		g2.setColor(Color.red);
		for(Point p:snake)
			g2.fillOval(p.x*length+10, p.y*length+10, pixels, pixels);
		Point head = snake.getFirst();
		g2.setColor(Color.black);
		if(dir == 0 || dir == 2)
		{
			g2.fillOval(head.x*length+15, head.y*length+22, 5, 6);
			g2.fillOval(head.x*length+30, head.y*length+22, 5, 6);
		}
		if(dir == 1 || dir == 3)
		{
			g2.fillOval(head.x*length+22, head.y*length+15, 5, 6);
			g2.fillOval(head.x*length+22, head.y*length+30, 5, 6);
		}
	}
	
	public void dir(int dir)
	{
		if(this.dir-2==dir)
			return;
		if(moved)
		{
			this.dir=dir;
			nextDir=-1;
			moved = false;
		}
		else
			nextDir = dir;
		
	}
	
	private void newFood()
	{
		boolean stop = false;
		while(!stop)
		{
			food = new Point((int)(Math.random()*length),(int)(Math.random()*length));
			stop = true;
			for(Point p: snake)
				if(food.x == p.x && food.y == p.y)
					stop=false;
		}
	}
	
	private void move()
	{
		if(number<100)
		{
			number++;
			return;
		}
		
		moved = true;
		boolean moved = false;
		Point next = snake.getFirst();
		
		if(dir == 0)
			next = new Point(next.x, next.y-1);
		if(dir == 1)
			next = new Point(next.x+1, next.y);
		if(dir == 2)
			next = new Point(next.x, next.y+1);
		if(dir == 3)
			next = new Point(next.x-1, next.y);
		
		if(next.x < 0 || next.x > length || next.y < 0 || next.y > length)
			if(walls)
				lost=true;
			else
			{
				if(next.x < 0)
					next = new Point(length, next.y);
				if(next.x > length)
					next = new Point(0, next.y);
				if(next.y < 0)
					next = new Point(next.x, length);
				if(next.y > length)
					next = new Point(next.x, 0);
			}
		
		for(Point p:snake)
			if(next.x == p.x && next.y == p.y)
				lost=true;
		
		if(next.x == food.x && next.y == food.y)
		{
			snake.addFirst(next);
			size++;
			newFood();
			moved = true;
		}
				
		
		Point temp = snake.getFirst();
		snake.set(0, next);
		if(!moved)
			for(int x = 1;x<size;x++)
			{
				Point temp2 = snake.get(x);
				snake.set(x, temp);
				temp = temp2;
			}
		
	}
	
	public void play()
	{
		System.out.println("fuck");
		while(!lost)
			f.repaint();
		while(!initagain)
			Thread.yield();
		reinit();
	}
	
	public void run()
	{
		System.out.println("fuck");
		while(!lost)
		{
			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			if(nextDir!=-1)
				dir=nextDir;
			
			move();
		}
		//JOptionPane.showMessageDialog(null, "You lost!\nFinal score: "+size);
		JOptionPane.showMessageDialog(null, (String) new Object());
		
		int ans = JOptionPane.showConfirmDialog(null, "Do you want to play agian?");
		
		if(ans == JOptionPane.CLOSED_OPTION || ans == JOptionPane.CANCEL_OPTION || ans == JOptionPane.NO_OPTION)
			System.exit(0);
		initagain = true;
	}
	
	private void reinit()
	{
		startTime = (new GregorianCalendar()).getTimeInMillis();
		size = 2;
		dir = (int)(Math.random()*4);
		snake = new LinkedList<Point>();
		lost=false;
		moved = false;
		nextDir=-1;
		initagain = false;
		
		snake.add(new Point((int)(Math.random()*length),(int)(Math.random()*length)));
		Point head = snake.getFirst();
		Point tail = null;
		
		if(dir == 0)
			tail = new Point(head.x, head.y+1);
		if(dir == 1)
			tail = new Point(head.x-1, head.y);
		if(dir == 2)
			tail = new Point(head.x, head.y-1);
		if(dir == 3)
			tail = new Point(head.x+1, head.y);
		
		snake.add(tail);
		
		newFood();
		
		runner = new Thread(this);
		runner.start();
		play();
	}
	
	private class KeyListen extends KeyAdapter
	{
		private Snake s;
		
		public KeyListen(Snake s)
		{
			this.s=s;
		}
		
		public void keyPressed(KeyEvent arg0)
		{
			arg0.consume();
			if(arg0.getKeyCode() == KeyEvent.VK_UP)
				s.dir(0);
			if(arg0.getKeyCode() == KeyEvent.VK_RIGHT)
				s.dir(1);
			if(arg0.getKeyCode() == KeyEvent.VK_DOWN)
				s.dir(2);
			if(arg0.getKeyCode() == KeyEvent.VK_LEFT)
				s.dir(3);
		}

		public void keyReleased(KeyEvent arg0)
		{
		}

		public void keyTyped(KeyEvent arg0)
		{
		}
	}
}
