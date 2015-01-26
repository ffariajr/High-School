package pingpong;

import java.awt.*;

public class temp implements Runnable
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
	
	private boolean wait;
	
	public temp(PPServer ps)
	{
		wait = false;
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
				p.position(thechar(x), getDefaultPosition(thechar(x)));
				break;
			}
		}	
	}
	
	private Point getDefaultPosition(char pos)
	{
		if(pos == 'l')
		{
			return new Point(1, 570);
		}
		else if(pos == 'r')
		{
			return new Point(591, 570);
		}
		else if(pos == 'u')
		{
			return new Point(570, 1);
		}
		else
		{
			return new Point(570, 591);
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
		System.out.println("began");
		while(!Thread.interrupted())
				for(Player p: players)
					if(p != null)
					{
						p.listen();
						ball.next();
					}
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
		//ps.removeRoom(this);
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
						System.out.println(p.place()+" : "+p.username()+" lost and the ball is reset");
						//p.lost();
						//reset();
					}
					else if(angle != -1)
						if(p.place() == 'l')
						{
							double temp = 180*2*angle - 90;
							ball.angle((int)temp);
							ball.bounceVertical();
							System.out.println(p.place()+" won");
						}
						else if(p.place() == 'r')
						{
							double temp = 180*2*angle + 90;
							ball.angle((int)temp);
							ball.bounceVertical();
							System.out.println(p.place()+" won");
						}
						else if(p.place() == 'u')
						{
							double temp = 180*2*angle + 180;
							ball.angle((int)temp);
							ball.bounceHorizontal();
							System.out.println(p.place()+" won");
						}
						else if(p.place() == 'd')
						{
							double temp = 180*2*angle;
							ball.angle((int)temp);
							ball.bounceHorizontal();
							System.out.println(p.place()+" won");
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
					Thread.sleep(1);
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
					//	System.out.println("sending ball ("+ball.x()+", "+ball.y()+") to "+p.toString());
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
