package pingpong;

import java.awt.*;

public class Room implements Runnable
{
	private Player[] players;
	private Ball ball;
	//private int[][] board;
	int numplayers;
	private PPServer ps;
	private int[] score;
	int difficulty;
	
	
	private Thread sender;
	private Sender sendclass;
	
	private Thread player;
	private Play play;
	
	private Thread listens;
	
	private boolean newAdded;
	private Player newGuy;
	
	public Room(PPServer ps)
	{
		ball = new Ball(new Point(300, 300));
		//board = new int[600][600];
		numplayers = 0;
		players = new Player[4];
		this.ps=ps;
		score = new int[4];
		
		sendclass = new Sender();
		sender = new Thread(sendclass);
		sender.start();
		
		play = new Play();
		player = new Thread(play);
		player.start();
		
		listens = new Thread(this);
		listens.start();
	}
	
	public String toString()
	{
		String str = "";
		for(Player p:players)
			if(p != null)
				str = p.username() + ", ";
		return str.substring(0, str.length()-2);
	}
	
	public void diff(int newDifficulty)
	{
		ball.speed(difficulty);
		difficulty = newDifficulty;
	}
	
	public void addPlayer(Player p)
	{
		while(true)
		{
			int x = (int)(Math.random() * 4);
			if(players[x] == null)
			{
				players[x] = p;
				numplayers++;
				score = new int[4];
				p.position('d'/*thechar(x)*/, getDefaultPosition('d'/*thechar(x)*/));
				newGuy = p;
				break;
			}
		}	
	}
	
	private Point getDefaultPosition(char pos)
	{
		if(pos == 'l')
		{
			return new Point(50, 270);
		}
		else if(pos == 'r')
		{
			return new Point(550, 270);
		}
		else if(pos == 'u')
		{
			return new Point(270, 50);
		}
		else if(pos == 'd')
		{
			return new Point(270, 550);
		}
		else 
		{
			System.err.println("WTF");
			System.exit(0);
			return null;
		}
	}
	
	private char thechar(int num)
	{
		switch(num)
		{
			case 0:
				return 'u';
			case 1:
				return 'r';
			case 2:
				return 'd';
			case 3:
				return 'l';
		}
		return 'x';
	}
	
	public void run()
	{
		Thread[] t = new Thread[4];
		int q = 0;
		for(Player p: players)
			if(players != null)
			{
				t[q] = new Thread(p);
				t[q++].start();
			}
		
		while(!Thread.interrupted())
			if(newAdded)
			{
				t[q] = new Thread(newGuy);
				t[q++].start();
				newGuy = null;
			}
			else
				Thread.yield();
		
		/*System.out.println("began");
		while(!Thread.interrupted())
			for(Player p: players)
				if(p != null)
					p.listen();*/
	}
	
	private void reset()
	{
		ball.reset(new Point(300,300));
		for(Player p: players)
			if(p != null)
				p.reset();
	}
	
	/*public void sendEverything()
	{
		for(Player p: players)
			if(p != null)
			{
				System.out.println("sending ball ("+ball.x()+", "+ball.y()+") to "+p.toString());
				p.send("ball ("+ball.x()+", "+ball.y()+")");
				
				for(int q = 0; q<4;q++)
					if(players[q] != null)
					{
						System.out.println("sending "+thechar(q)+"paddle ("+players[q].x()+", "+players[q].y()+") to "+p.toString());
						p.send(""+thechar(q)+"paddle ("+players[q].x()+", "+players[q].y()+")");
					}
					else
					{
						System.out.println("sending "+thechar(q)+"paddle (,) to "+p.toString());
						p.send(""+thechar(q)+"paddle (,)");
					}
				
				for(int q = 0; q<4;q++)
				{
					p.send(""+thechar(q)+"paddle "+p.score());
				}
			}
	}*/
	
	public void leave(Player me)
	{
		for(int x = 0;x<4;x++)
			if(me == players[x])
			{
				players[x].close(ps);
				players[x] = null;
			}
	}

	public void close()
	{
		for(Player p:players)
			p.close(ps);
		ps.removeRoom(this);
	}

	public boolean isFull()
	{
		return numplayers == 4;
	}
	
	public class Play implements Runnable
	{
		public void run()
		{
			while(!Thread.interrupted())
			{
			ball.next();
			
			for(Player p: players)
				if(p != null)
				{
					double angle = p.hitAngle(ball);
					if(angle == 2)
					{
						//System.out.println(p.username()+" lost and the ball is reset");
						p.lost();
						//reset();
						if(ball.x() < 0)
							ball.bounceVertical();
						if(ball.y() < 0)
							ball.bounceHorizontal();
						if(ball.x() > 600)
							ball.bounceVertical();
						if(ball.y() > 600)
							ball.bounceHorizontal();
					}
					else if(angle != -2)
						if(p.place() == 'l')
						{
							
							double temp = 180.0*angle - 90;
							ball.angle((int)temp);
							//ball.bounceVertical();
							
						}
						else if(p.place() == 'r')
						{
							double temp = 180.0*angle + 90;
							ball.angle((int)temp);
							//ball.bounceVertical();
						}
						else if(p.place() == 'u')
						{
							//if(ball.y() < p.y())
								//ball.ball.y = p.y()+20;
							
							double temp = 180.0*angle + 180;
							ball.angle((int)temp);
							
							//ball.bounceHorizontal();
						}
						else if(p.place() == 'd')
						{
							System.err.println("number from hitangle: "+angle);
							System.err.println("balls new angle: "+(180.0*angle));
							System.err.println("balls old angle: "+ball.getangle());           //here
							
							double temp = 180.0*angle;
							ball.angle((int)temp);
							//ball.bounceHorizontal();
							System.err.println("balls new angle: "+ball.getangle());           //here
						}
				}
				else
				{
					if(ball.x() < 0)
						ball.bounceVertical();
					if(ball.y() < 0)
						ball.bounceHorizontal();
					if(ball.x() > 600)
						ball.bounceVertical();
					if(ball.y() > 600)
						ball.bounceHorizontal();
				}
			
			
				try
				{
					Thread.sleep(3);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	public class Sender implements Runnable
	{
		public void run()
		{
			while(!Thread.interrupted())
			{
				for(Player p: players)
					if(p != null)
					{
						//System.out.println("sending ball ("+ball.x()+", "+ball.y()+") to "+p.toString());
						p.send("ball ("+ball.x()+", "+ball.y()+")");
					
						for(int q = 0; q<4;q++)
							if(players[q] != null)
							{
								//System.out.println("sending "+thechar(q)+"paddle ("+players[q].x()+", "+players[q].y()+") to "+p.toString());
								p.send(""+thechar(q)+"paddle ("+players[q].x()+", "+players[q].y()+")");
							}
							else
							{
								//System.out.println("sending "+thechar(q)+"paddle (,) to "+p.toString());
								p.send(""+thechar(q)+"paddle (,)");
							}
					
						for(int q = 0; q<4;q++)
						{
							p.send(""+thechar(q)+"paddle "+p.score());
						}
					}
				try
				{
					Thread.sleep(1);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
