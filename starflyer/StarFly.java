package starflyer;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class StarFly extends JComponent
{
	private static final long serialVersionUID = -7017343024408021747L;

	public static void main(String[] args)
	{
		String[] ops = {"Easy","Medium","Hard"};
		Object diff = JOptionPane.showInputDialog(null, "Star Fly", "Choose difficulty!", JOptionPane.QUESTION_MESSAGE, null, ops, "Medium");
		
		int hard = 0;
		if(diff == null)
			System.exit(0);
		if(diff.equals("Easy"))
			hard = 1;
		if(diff.equals("Medium"))
			hard = 2;
		if(diff.equals("Hard"))
			hard = 3;
		
		JFrame f = new JFrame();
		StarFly sf = new StarFly(f, hard);
		f.setBounds(100, 100, 600, 600);
		f.add(sf);
		f.addKeyListener(sf.getKey());
		f.setTitle("Star Flyer");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setVisible(true);
		sf.start();
	}
	
	/*private static String getName(Color col)
	{
		if(col.equals(Color.black))
			return "black";
		else if(col.equals(Color.blue))
			return "blue";
		else if(col.equals(Color.yellow))
			return "yellow";
		else if(col.equals(Color.red))
			return "red";
		else if(col.equals(Color.pink))
			return "pink";
		else if(col.equals(Color.green))
			return "green";
		else if(col.equals(Color.magenta))
			return "magenta";
		else if(col.equals(Color.cyan))
			return "cyan";
		else if(col.equals(Color.orange))
			return "orange";
		else if(col.equals(Color.gray))
			return "grey";
		return "white";
	}*/
	
	private JFrame f;
	private long startTime;
	private Rocks rocks;
	private int speed;
	private int x;
	private int y;
	private Key keys;
	private boolean first;
	private Rectangle rect;
	private boolean paused;
	private int count;
	private boolean vince;  //invincibility
	private int vincetotal;
	private int powernum;
	private int power;      //attack charge
	private int powertotal;
	private boolean emp;
	private int[][] bullet; //[0-3][0-1] are bullets,  [0-3][2] is if used
	private int bulletwait;
	private int bulletwaittime;
	
 	public StarFly(JFrame f, int diff)
	{
		count = 10;
		x = 300;
		y = 450;
		this.f=f;
		startTime=(new GregorianCalendar()).getTimeInMillis();
		int numrocks = diff >= 2 ? 50:20;
		speed= diff >= 3 ? 6:3;
		keys = new Key(this);
		rocks = new Rocks(numrocks);
		paused=false;
		vince = false;
		vincetotal = diff == 1? 600:400;
		powertotal = diff < 3? 800:1000;
		power = 0;
		emp = false;
		bullet = new int[12][3];
		bulletwait = 0;
		if(diff == 1)
			bulletwaittime = 15;
		else if(diff == 2)
			bulletwaittime = 20;
		else
			bulletwaittime = 25;
	}
	
	private Key getKey()
	{
		return keys;
	}
	
	public void paint(Graphics g)
	{
		keys.makeMoves();
		rocks.move();
		Graphics2D g2 = (Graphics2D) g;
		rect = g2.getClipBounds();
		
		int w = g2.getClipBounds().width;
		int h = g2.getClipBounds().height;
		
		if(first)
		{
			x = w/2;
			y = h-30;
			first = false;
		}
		
		if(emp)
		{
			g2.setColor(Color.blue.darker().darker().darker().darker());
			if(power > 1)
				emp = false;
			power = 0;
		}
		else
			g2.setColor(Color.black);
		g2.fillRect(0,0,w,h);
		
		g2.setColor(Color.lightGray);
		for(Object r:rocks.toRock())
			if((Rock) r != null)
				g2.fillOval(((Rock)r).x(), ((Rock)r).y(), 10, 10);
		drawShip(g2);
		paintBullet(g2);
		paintInstead(g2);
	}
	
	private void paintBullet(Graphics2D g2)
	{
		for(int z = 0;z<bullet.length;z++)
		{
			g2.setColor(Color.yellow);
			g2.drawLine(bullet[z][0], bullet[z][1], bullet[z][0], bullet[z][1]-5);
		}
	}
	
 	public void pop(Rock r)
	{
		rocks.remove(r);
	}
	
	public void start()
	{
		int counter = 0;
		boolean[] increases = new boolean[5];
		while(true)
		{
			if(!paused)
			{
				f.repaint();
				counter++;
			}
				
			if(counter == count)
			{
				rocks.add(new Rock((int)(Math.random()*580+10),speed,600,this));
				counter = 0;
			}
			
			powers();
			
			if(rocks.hit(new Point(x,y)) && !paused && !vince)
			{
				paused = true;
				String name = "";
				long endtime = (new GregorianCalendar()).getTimeInMillis();
				long totaltime = endtime-startTime;
				
				long seconds = (totaltime/1000)%60;
				long minutes = (totaltime/1000)/60;
				
				String sent = "";
				
				if(minutes > 1)
					sent = "You survived for "+minutes+" minutes and ";
				else if(minutes == 1)
					sent = "You survived for 1 minute and ";
				else
					sent = "You survived for ";
				
				if(seconds > 1)
					sent = sent+seconds+" seconds!!!";
				else if(seconds == 1)
					sent = sent+"1 second!!!";
				else
					sent = sent+"0 seconds???";
				sent = sent+"\nEnter your name:";
				
				name = JOptionPane.showInputDialog(sent);
				highScores((int)(totaltime/1000), name);
			}
			
			long endtime = (new GregorianCalendar()).getTimeInMillis();
			if(endtime - startTime > 30000 && !increases[0])
			{
				rocks.increase();
				count = 9;
				increases[0] = true;
			}
			
			if(endtime - startTime > 60000 && !increases[1])
			{
				rocks.increase();
				increases[1] = true;
			}
			if(endtime - startTime > 120000 && !increases[2])
			{
				rocks.increase();
				count = 8;
				increases[2] = true;
			}
			if(endtime - startTime > 150000 && !increases[3])
			{
				rocks.increase();
				increases[3] = true;
			}
			if(endtime - startTime > 180000 && !increases[4])
			{
				rocks.increase();
				count = 7;
				increases[4] = true;
			}
			
			try
			{
				if(!paused)
					Thread.sleep(6);
			}
			catch(InterruptedException e)
			{
				System.exit(0);
			}
		}
	}
	
	public void highScores(int time, String name)
	{
		//            1) Fernando Faria - 1
		if(name != null)
		{
			try
			{
				File file = new File("C:/Users/Nando/Desktop/HighScoreStarFly.txt");
				BufferedReader read = new BufferedReader(new FileReader(file));
				
				//String [] arr = new String[10];
				String[] names = new String[10];
				int[] times = new int[10];
				
				for(int z = 0;z<10;z++)
				{
					String str = read.readLine();
					names[z] = str.substring(3, str.indexOf('-')-1);
					times[z] = Integer.parseInt(str.substring(str.indexOf('-')+2));
				}
				
				@SuppressWarnings("unused")
				int t = 0;
				@SuppressWarnings("unused") 
				String s = "";
				for(int z = 0;z<10;z++)
					if(time > times[z])
					{
						s = names[z];
						t = times[z];
						names[z] = name;
						times[z] = time;
					}
				
				file.delete();
				file.createNewFile();
				
				BufferedWriter write = new BufferedWriter(new FileWriter(file));
				for(int z = 0;z<10;z++)
					write.append(""+z+") "+names[z]+" - "+times[z]+"\n");
				
			} catch (IOException e)
			{
				JOptionPane.showMessageDialog(null, "Error", "High scores unavailable at this time", JOptionPane.ERROR_MESSAGE, null);
			}	
		}
		
		f.repaint();
		
		int ans = JOptionPane.showConfirmDialog(null, "Play again???");
		if(ans != JOptionPane.YES_OPTION)
			System.exit(0);
		else
			reinit();
	}
	
	private void reinit()
	{
		startTime = (new GregorianCalendar()).getTimeInMillis();
		int len = rocks.getLength();
		rocks = new Rocks(len);
		x = 300;
		y = 450;
		first = true;
		paused = false;
		count = 10;
		hold1 = 0;
		hold2 = 0;
		hold3 = 0;
		power = 0;
		try
		{
			Thread.sleep(250);
		}
		catch (InterruptedException e)
		{
			System.exit(0);
		}
		start();
	}
	
	private void powers()
	{
		if(power != -1)
			power++;
		
		if(power == powertotal)
			power = -1;
		
		if(vince)
		{
			powernum++;
			if(powernum >= vincetotal)
				invincible();
		}
		
		bulletwait++;
		
		for(int z = 0;z<bullet.length;z++)
		{
			Rock temp = rocks.hitshot(new Point(bullet[z][0],bullet[z][1]));
			if(temp == null)
				bullet[z][1] -=3;
			else
			{
				rocks.remove(temp);
				bullet[z][0] = 0;
				bullet[z][1] = 0;
				bullet[z][2] = 0;
			}
				
			
			if(bullet[z][1] < 4)
			{
				bullet[z][0] = 0;
				bullet[z][1] = 0;
				bullet[z][2] = 0;
			}
		}
	}
	
	public void paintInstead(Graphics g2)
	{
		if(!paused)
			return;
		
		g2.setColor(Color.white);
		g2.fillRect(0, 0, 600, 600);
		
		try
		{
			File file = new File("E:/HighScoreStarFly.txt");
			BufferedReader read = new BufferedReader(new FileReader(file));
			
			g2.drawString("Rank | Name    |    Time", 250, 100);
			for(int z = 0;z<10;z++)
			{
				String str = read.readLine();
				g2.drawString(str, 250, 100+10*z);
			}			
		} catch (IOException e)
		{
			g2.drawString("Error: Highscores unavailable at this time", 250, 100);
			//JOptionPane.showMessageDialog(null, "Error", "High scores unavailable at this time", JOptionPane.ERROR_MESSAGE, null);
		}	
	}
	
	public void moveLeft()
	{
		x--;
		check();
	}
	
	public void moveRight()
	{
		x++;
		check();
	}
	
	public void moveUp()
	{
		y--;
		check();
	}
	
	public void moveDown()
	{
		y++;
		check();
	}
	
	private void check()
	{
		while(x-10 < 0)
		{
			x++;
			if(hold1 == KeyEvent.VK_LEFT)
				hold1 = 0;
			if(hold2 == KeyEvent.VK_LEFT)
				hold2 = 0;
			if(hold3 == KeyEvent.VK_LEFT)
				hold3 = 0;
		}
		
		while(x+10 > rect.width)
		{
			x--;
			if(hold1 == KeyEvent.VK_RIGHT)
				hold1 = 0;
			if(hold2 == KeyEvent.VK_RIGHT)
				hold2 = 0;
			if(hold3 == KeyEvent.VK_RIGHT)
				hold3 = 0;
		}
		
		while(y < 0)
		{
			y++;
			if(hold1 == KeyEvent.VK_UP)
				hold1 = 0;
			if(hold2 == KeyEvent.VK_UP)
				hold2 = 0;
			if(hold3 == KeyEvent.VK_UP)
				hold3 = 0;
		}
		
		while(y+30 > rect.height)
		{
			y--;
			if(hold1 == KeyEvent.VK_DOWN)
				hold1 = 0;
			if(hold2 == KeyEvent.VK_DOWN)
				hold2 = 0;
			if(hold3 == KeyEvent.VK_DOWN)
				hold3 = 0;
		}
	}
	
	private void drawShip(Graphics2D g2)
	{
		int[] xpoints = {x-8, x-10, x, x+10, x+8};
		int[] ypoints = {y+30, y+10, y, y+10, y+30};
		
		if(vince)
			g2.setColor(Color.cyan);
		else
			g2.setColor(Color.blue.darker());
		
		g2.fillPolygon(xpoints, ypoints, 5);
		
		int[] xpoints2 = {x-4, x-8, x, x+8, x+4};
		int[] ypoints2 = {y+30, y+10, y+1, y+10, y+30};
		
		if(vince)
			g2.setColor(Color.magenta);
		else
			g2.setColor(Color.red.darker());
		
		g2.fillPolygon(xpoints2, ypoints2, 5);
		
		int[] xpoints3 = {x-1, x-2, x, x+2, x+1};
		int[] ypoints3 = {y+30, y+14, y, y+14, y+30};
		
		g2.setColor(Color.black);
		
		g2.fillPolygon(xpoints3, ypoints3, 5);
		
		if(power != -1)
		{
			int powernumber = (int) ((power*1.0)/powertotal * 30);
			g2.setColor(Color.yellow);
			g2.drawLine(x, y, x, y+powernumber);
		}
	}
	
	private void invincible()
	{
		if(power != -1)
			return;
		
		if(powernum >= vincetotal-5)
		{
			vince = false;
			powernum = 0;
			power = 0;
		}
		else
			vince = true;
	}
	
	private void shoot()
	{
		if(bulletwait < bulletwaittime)
			return;
		bulletwait = 0;
		
		for(int z = 0;z<bullet.length;z++)
			if(bullet[z][2] == 0)
			{
				bullet[z][0] = x;
				bullet[z][1] = y;
				bullet[z][2] = 1;
				return;
			}
	}
	
	private void EMP()
	{
		if(power == -1)
		{
			emp = true;
			rocks.emp();
		}
	}
	
	public int hold1;
	public int hold2;
	public int hold3;
	public int hold4;
	
	public class Key implements KeyListener
	{
		private StarFly sf;
		
		public Key(StarFly sf)
		{
			this.sf=sf;
		}
		
		public void keyPressed(KeyEvent arg0)
		{
			if(hold1 == 0)
				hold1 = arg0.getKeyCode();
			else if(hold2 == 0)
				hold2 = arg0.getKeyCode();
			else if(hold3 == 0)
				hold3 = arg0.getKeyCode();
			else if(hold4 == 0)
				hold4 = arg0.getKeyCode();
			checkem();
		}

		public void keyReleased(KeyEvent arg0)
		{	
			if(hold1 == arg0.getKeyCode())
				hold1 = 0;
			if(hold2 == arg0.getKeyCode())
				hold2 = 0;
			if(hold3 == arg0.getKeyCode())
				hold3 = 0;
			if(hold4 == arg0.getKeyCode())
				hold4 = 0;
			checkem();
		}
		
		public void checkem()
		{
			if(hold1 == hold2)
				hold2 = 0;
			if(hold1 == hold3)
				hold3 = 0;
			if(hold1 == hold4)
				hold4 = 0;
			if(hold2 == hold3)
				hold3 = 0;
			if(hold2 == hold4)
				hold4 = 0;
			if(hold3 == hold4)
				hold4 = 0;
		}

		public void keyTyped(KeyEvent arg0)
		{
		}
		
		private void makeMoves()
		{
			checkem();
			if(hold1 == KeyEvent.VK_LEFT)
				sf.moveLeft();			
			if(hold1 == KeyEvent.VK_RIGHT)
				sf.moveRight();
			if(hold1 == KeyEvent.VK_UP)
				sf.moveUp();
			if(hold1 == KeyEvent.VK_DOWN)
				sf.moveDown();
			if(hold1 == KeyEvent.VK_A)
				sf.invincible();
			if(hold1 == KeyEvent.VK_D)
				sf.EMP();
			if(hold1 == KeyEvent.VK_S)
				sf.shoot();
			
			if(hold2 == KeyEvent.VK_LEFT)
				sf.moveLeft();			
			if(hold2 == KeyEvent.VK_RIGHT)
				sf.moveRight();
			if(hold2 == KeyEvent.VK_UP)
				sf.moveUp();
			if(hold2 == KeyEvent.VK_DOWN)
				sf.moveDown();
			if(hold2 == KeyEvent.VK_A)
				sf.invincible();
			if(hold2 == KeyEvent.VK_D)
				sf.EMP();
			if(hold2 == KeyEvent.VK_S)
				sf.shoot();
			
			if(hold3 == KeyEvent.VK_LEFT)
				sf.moveLeft();			
			if(hold3 == KeyEvent.VK_RIGHT)
				sf.moveRight();
			if(hold3 == KeyEvent.VK_UP)
				sf.moveUp();
			if(hold3 == KeyEvent.VK_DOWN)
				sf.moveDown();
			if(hold3 == KeyEvent.VK_A)
				sf.invincible();	
			if(hold3 == KeyEvent.VK_D)
				sf.EMP();
			if(hold3 == KeyEvent.VK_S)
				sf.shoot();
			
			if(hold4 == KeyEvent.VK_LEFT)
				sf.moveLeft();			
			if(hold4 == KeyEvent.VK_RIGHT)
				sf.moveRight();
			if(hold4 == KeyEvent.VK_UP)
				sf.moveUp();
			if(hold4 == KeyEvent.VK_DOWN)
				sf.moveDown();
			if(hold4 == KeyEvent.VK_A)
				sf.invincible();	
			if(hold4 == KeyEvent.VK_D)
				sf.EMP();
			if(hold4 == KeyEvent.VK_S)
				sf.shoot();
		}
	}
}
