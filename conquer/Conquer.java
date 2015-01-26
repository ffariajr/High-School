package conquer;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;




/* yao
 * dedicated keys for player 1 player 2 and player 3 and their power ups
 */

public class Conquer extends JComponent
{
	public static void main(String[] args)
	{
		int ans = JOptionPane.showConfirmDialog(null, "3 players?");
		
		if(ans == JOptionPane.CANCEL_OPTION)
			System.exit(0);
		
		boolean three;
		
		if(ans == JOptionPane.YES_OPTION)
			three = true;
		else
			three = false;
		
		
		Color[] colors = {Color.RED,Color.BLUE,Color.BLACK,Color.GREEN,Color.ORANGE,Color.YELLOW,Color.GRAY,Color.MAGENTA,Color.CYAN,Color.PINK};
		String[] strs = new String[colors.length];
		for(int x = 0;x<colors.length;x++)
			strs[x] = getName(colors[x]);
		int p1 = JOptionPane.showOptionDialog(null, "Player 1: Choose color", "Conquer", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, strs, 0);
		if(p1 == -1)
			System.exit(0);
	
		
		
		Color[] colors2 = new Color[colors.length-1];
		int place = 0;
		for(int x = 0;x<10;x++)
		{
			if(!colors[x].equals(colors[p1]))
			{
				colors2[place] = colors[x];
				place++;
			}
		}
		String[] strs2 = new String[colors2.length];
		for(int x = 0;x<colors2.length;x++)
			strs2[x] = getName(colors2[x]);
		int p2 = JOptionPane.showOptionDialog(null, "Player 2: Choose color", "Conquer", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, strs2, 0);
		if(p2 == -1)
			System.exit(0);
		
		Color[] colors3 = null;
		int p3 = 0;
		if(three)
		{
			colors3 = new Color[colors2.length-1];
			int place2 = 0;
			for(int x = 0;x<9;x++)
			{
				if(!colors2[x].equals(colors2[p2]))
				{
					colors3[place2] = colors2[x];
					place2++;
				}
			}
			String[] strs3 = new String[colors3.length];
			for(int x = 0;x<colors3.length;x++)
				strs3[x] = getName(colors3[x]);
			p3 = JOptionPane.showOptionDialog(null, "Player 3: Choose color", "Conquer", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, strs3, 0);
			if(p3 == -1)
				System.exit(0);
		}
		
		int time = Integer.parseInt(JOptionPane.showInputDialog("How Long in seconds?"))*1000;
		
		JFrame f = new JFrame();
		
		Conquer c;
		
		if(three)
			c = new Conquer(f, colors[p1], colors2[p2], colors3[p3], time);
		else
			c = new Conquer(f, colors[p1], colors2[p2], time);
		
		//Conquer c = new Conquer(f);
		KeyListener keys = c.getKey();
		f.add(c);
		f.addKeyListener(keys);
		f.setBounds(new Rectangle(100,100,600,600));
		f.setTitle("Conquer");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setVisible(true);
		c.start();
	}
	
	private static void print(String msg)
	{
		JOptionPane.showMessageDialog(null, msg);
	}
	
	private static String getName(Color col)
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
	}
	
	private JFrame f;
	public int[][] map;
	private Key keys;
	private Color p1c;
	private Color p2c;
	private Color p3c;
	public int p1n;
	public int p2n;
	public int p3n;
	public Point p1;
	public Point p2;
	public Point p3;
	public Rectangle rect;
	public boolean ended;
	private boolean finalized;
	private long startTime;
	private int time;
	public final boolean three;
	private JOptionPane directions;
	
	public Conquer(JFrame f)
	{
		this(f, Color.red, Color.blue, 60000);
	}
	
	public Conquer(JFrame f, Color p1, Color p2, int time)
	{
		this(f,p1,p2,null,time);
	}
	
	public Conquer(JFrame f, Color p1, Color p2, Color p3, int time)
	{
		if(p3 == null)
			three = false;
		else 
			three = true;
		
		
		this.time= time==0?-1:time;
		this.f=f;
		p1c = p1;
		p2c = p2;
		p3c = p3;
		map = new int[200][200];
		p1n = 0;
		p2n = 0;
		p3n = 0;
		
		int x1 = (int) (Math.random()*(200));
		int y1 = (int) (Math.random()*(200));
		int x2 = (int) (Math.random()*(200));
		int y2 = (int) (Math.random()*(200));
		int x3 = (int) (Math.random()*(200));
		int y3 = (int) (Math.random()*(200));
		
		this.p1 = new Point(x1,y1);
		this.p2 = new Point(x2,y2);
		this.p3 = new Point(x3,y3);
		map[x1][y1] = 1;
		map[x2][y2] = 2;
		map[x3][y3] = 3;
		
		keys = new Key();
		keys.game(this);
		startTime = System.currentTimeMillis();
		
		directions = new JOptionPane();
		
		String dir = "";
		
		if(three)
			dir = "             Player 1:        Player 2:        Player 3:\n" +
					"Move      WASD             8456              IJKL\n" +
					"Teleport  Q E                 7 9                 U O\n" +
					"Row/Col   Z X                 1 2                 M <\n" +
					"Box           C                     3                    >\n" +
					"Box Him    V                    0                   /\n" +
					"Fill              F                     .                     ;\n" +
					"Clear        R                     +                     P\n";
		else
			dir = "             Player 1:        Player 2:\n" +
					"Move      WASD             8456\n" +
					"Teleport  Q E                 7 9\n" +
					"Row/Col   Z X                 1 2\n" +
					"Box           C                     3\n" +
					"Box Him    V                    0\n" +
					"Fill              F                     .\n" +
					"Clear        R                     +\n";
		
		directions.showMessageDialog(this, dir);
	}
	
	private void reinit()
	{
		map = new int[200][200];
		p1n = 0;
		p2n = 0;
		p3n = 0;
		
		int x1 = (int) (Math.random()*(200));
		int y1 = (int) (Math.random()*(200));
		int x2 = (int) (Math.random()*(200));
		int y2 = (int) (Math.random()*(200));
		int x3 = (int) (Math.random()*(200));
		int y3 = (int) (Math.random()*(200));
		
		this.p1 = new Point(x1,y1);
		this.p2 = new Point(x2,y2);
		this.p3 = new Point(x3,y3);
		map[x1][y1] = 1;
		map[x2][y2] = 2;
		map[x3][y3] = 3;
		
		startTime = System.currentTimeMillis();
		ended = false;
		finalized = false;
	}
	
	public KeyListener getKey()
	{
		return keys;
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		rect = g2.getClipBounds();
		
		for(int x = 0;x<map.length;x++)
		{
			for(int y = 0;y<map.length;y++)
			{
				if(map[x][y] == 1)
					g2.setColor(p1c);
				else if(map[x][y] == 2)
					g2.setColor(p2c);
				else if(map[x][y] == 3 && three)
					g2.setColor(p3c);
				else
					g2.setColor(Color.WHITE);
				
				g2.fillRect(x*3, y*3, 3, 3);
			}
		}
		
		g2.setColor(p1c);
		g2.fillRect(p1.x*3, p1.y*3, 3, 3);
		g2.setColor(p2c);
		g2.fillRect(p2.x*3, p2.y*3, 3, 3);
		if(three)
		{
			g2.setColor(p3c);
			g2.fillRect(p3.x*3, p3.y*3, 3, 3);
		}
		
		if(ended)
			end(g2);
	}
	
	public void start()
	{
		while(true)
		{
			long endTime = System.currentTimeMillis();
			if(time != -1 && endTime-startTime > time && !ended)
				ended = true;
			else if(finalized)
			{
				int ans = JOptionPane.showConfirmDialog(null, "Play again???");
				if(ans == JOptionPane.OK_OPTION)
					reinit();
				if(ans == JOptionPane.CLOSED_OPTION || ans == JOptionPane.NO_OPTION || ans == JOptionPane.CANCEL_OPTION)
					System.exit(0);
			}
			else
				f.repaint();
		}
	}
	
	public void end(Graphics2D g2)
	{
		p1n=0;
		p2n=0;
		p3n=0;
		
		for(int[] x: map)
			for(int y:x)
				if(y == 1)
					p1n++;
				else if(y == 2)
					p2n++;
				else if(y == 3)
					p3n++;
		
		g2.setColor(Color.white);
		g2.fillRect(rect.width/2 - 20, rect.height/2 - 20, 150, 60);
		g2.setColor(Color.black);
		g2.drawRect(rect.width/2 - 20, rect.height/2 - 20, 150, 60);
		g2.setColor(p1c);
		g2.drawString("Player 1: "+p1n, rect.width/2 + 10, rect.height/2);
		g2.setColor(p2c);
		g2.drawString("Player 2: "+p2n, rect.width/2 + 10, rect.height/2 + 15);
		if(three)
		{
			g2.setColor(p3c);
			g2.drawString("Player 3: "+p3n, rect.width/2 + 10, rect.height/2 + 30);
		}
		finalized = true;
	}
	
	public void moveP1up()
	{
		if(finalized)
			return;
		p1.setLocation(p1.x,p1.y-1);
		check();
		map[p1.x][p1.y] = 1;
	}
	public void moveP1down()
	{
		if(finalized)
			return;
		p1.setLocation(p1.x,p1.y+1);
		check();
		map[p1.x][p1.y] = 1;
	}
	public void moveP1left()
	{
		if(finalized)
			return;
		p1.setLocation(p1.x-1,p1.y);
		check();
		map[p1.x][p1.y] = 1;
	}
	public void moveP1right()
	{
		if(finalized)
			return;
		p1.setLocation(p1.x+1,p1.y);
		check();
		map[p1.x][p1.y] = 1;
	}
	
	public void moveP2up()
	{
		if(finalized)
			return;
		p2.setLocation(p2.x,p2.y-1);
		check();
		map[p2.x][p2.y] = 2;
	}
	public void moveP2down()
	{
		if(finalized)
			return;
		p2.setLocation(p2.x,p2.y+1);
		check();
		map[p2.x][p2.y] = 2;
	}
	public void moveP2left()
	{
		if(finalized)
			return;
		p2.setLocation(p2.x-1,p2.y);
		check();
		map[p2.x][p2.y] = 2;
	}
	public void moveP2right()
	{
		if(finalized)
			return;
		p2.setLocation(p2.x+1,p2.y);
		check();
		map[p2.x][p2.y] = 2;
	}
	
	public void moveP3up()
	{
		if(!three)
			return;
		if(finalized)
			return;
		p3.setLocation(p3.x,p3.y-1);
		check();
		map[p3.x][p3.y] = 3;
	}
	public void moveP3down()
	{
		if(!three)
			return;
		if(finalized)
			return;
		p3.setLocation(p3.x,p3.y+1);
		check();
		map[p3.x][p3.y] = 3;
	}
	public void moveP3left()
	{
		if(!three)
			return;
		if(finalized)
			return;
		p3.setLocation(p3.x-1,p3.y);
		check();
		map[p3.x][p3.y] = 3;
	}
	public void moveP3right()
	{
		if(!three)
			return;
		if(finalized)
			return;
		p3.setLocation(p3.x+1,p3.y);
		check();
		map[p3.x][p3.y] = 3;
	}
	
	private void check()
	{
		int max = map.length;
		if(p1.x<0)
			p1.x = max-1;
		if(p1.x>max-1)
			p1.x = 0;
		if(p1.y<0)
			p1.y = max-1;
		if(p1.y>max-1)
			p1.y = 0;
		
		if(p2.x<0)
			p2.x = max-1;
		if(p2.x>max-1)
			p2.x = 0;
		if(p2.y<0)
			p2.y = max-1;
		if(p2.y>max-1)
			p2.y = 0;
		
		if(p3.x<0)
			p3.x = max-1;
		if(p3.x>max-1)
			p3.x = 0;
		if(p3.y<0)
			p3.y = max-1;
		if(p3.y>max-1)
			p3.y = 0;
	}
	
	private int p1boxme;
	private int p2boxme;
	private int p3boxme;
	public void boxMe(int pone)
	{	
		if(pone == 3 && !three)
			return;
		
		if(finalized)
			return;
		if(pone == 1 && p1boxme == 25)
			return;
		
		if(pone == 2 && p2boxme == 25)
			return;
		
		if(pone == 3 && p3boxme == 25)
			return;
		
		if(pone == 1)
			p1boxme++;
		else if(pone == 2)
			p2boxme++;
		else
			p3boxme++;
		
		if(pone == 1)
			for(int x = p1.x-10;x<p1.x+11;x++)
				for(int y = p1.y-10;y<p1.y+11;y++)
					map[x][y] = 1;
		else if(pone == 2)
			for(int x = p2.x-10;x<p2.x+11;x++)
				for(int y = p2.y-10;y<p2.y+11;y++)
					map[x][y] = 2;
		else
			for(int x = p3.x-10;x<p3.x+11;x++)
				for(int y = p3.y-10;y<p3.y+11;y++)
					map[x][y] = 3;
	}
	
	private int p1column;
	private int p2column;
	private int p3column;
	public void column(int pone)
	{
		if(pone == 3 && !three)
			return;
		
		if(finalized)
			return;
		if(pone == 1 && p1column == 15)
			return;
		
		if(pone == 2 && p2column == 15)
			return;
		
		if(pone == 3 && p3column == 15)
			return;
		
		if(pone == 1)
			p1column++;
		else if(pone == 2)
			p2column++;
		else
			p3column++;
		
		if(pone == 1)
			for(int x = p1.x-4;x<p1.x+5;x++)
				for(int y = 0;y<200;y++)
					map[x][y] = 1;
		else if(pone == 2)
			for(int x = p2.x-4;x<p2.x+5;x++)
				for(int y = 0;y<200;y++)
					map[x][y] = 2;
		else
			for(int x = p3.x-4;x<p3.x+5;x++)
				for(int y = 0;y<200;y++)
					map[x][y] = 3;

	}
	
	private int p1row;
	private int p2row;
	private int p3row;
	public void row(int pone)
	{
		if(pone == 3 && !three)
			return;
		
		if(finalized)
			return;
		if(pone ==1 && p1row == 15)
			return;
		
		if(pone ==2 && p2row == 15)
			return;
		
		if(pone ==3 && p3row == 15)
			return;
		
		if(pone == 1)
			p1row++;
		else if(pone == 2)
			p2row++;
		else
			p3row++;
		
		if(pone == 1)
			for(int x = 0;x<200;x++)
				for(int y = p1.y-4;y<p1.y+5;y++)
					map[x][y] = 1;
		else if(pone == 2)
			for(int x = 0;x<200;x++)
				for(int y = p2.y-4;y<p2.y+5;y++)
					map[x][y] = 2;
		else
			for(int x = 0;x<200;x++)
				for(int y = p3.y-4;y<p3.y+5;y++)
					map[x][y] = 3;

	}
	
	private int p1sendback;
	private int p2sendback;
	private int p3sendback;
	public void sendBack(int pone)
	{
		if(pone == 3 && !three)
			return;
		
		if(finalized)
			return;
		if(pone == 1)
		{
			if(p1sendback != 30)
			{
				map[100][100] = 1;
				p2.setLocation(100,100);
				p3.setLocation(100,100);
				p1sendback++;
			}
		}
		else if(pone == 2)
		{
			if(p2sendback != 30)
			{
				map[100][100] = 2;
				p1.setLocation(100,100);
				p3.setLocation(100,100);
				p2sendback++;
			}
		}
		else
		{
			if(p3sendback != 30)
			{
				map[100][100] = 3;
				p1.setLocation(100,100);
				p2.setLocation(100,100);
				p3sendback++;
			}
		}
	}
	
	private int p1boxhim;
	private int p2boxhim;
	private int p3boxhim;
	public void boxHim(int pone)
	{
		if(pone == 3 && !three)
			return;
		
		if(finalized)
			return;
		if(pone ==1 && p1boxhim == 20)
			return;
		
		if(pone ==2 && p2boxhim == 20)
			return;
		
		if(pone ==3 && p3boxhim == 20)
			return;
		
		if(pone == 1)
			p1boxhim++;
		else if(pone ==2)
			p2boxhim++;
		else
			p3boxhim++;
		
		if(pone == 1)
		{
			for(int x = p2.x-10;x<p2.x+11;x++)
				for(int y = p2.y-10;y<p2.y+11;y++)
					map[x][y] = 1;
			if(three)
				for(int x = p3.x-10;x<p3.x+11;x++)
					for(int y = p3.y-10;y<p3.y+11;y++)
						map[x][y] = 1;
		}	
		else if(pone == 2)
		{
			for(int x = p1.x-10;x<p1.x+11;x++)
				for(int y = p1.y-10;y<p1.y+11;y++)
					map[x][y] = 2;
			if(three)
				for(int x = p3.x-10;x<p3.x+11;x++)
					for(int y = p3.y-10;y<p3.y+11;y++)
						map[x][y] = 2;
		}
		else
		{
			for(int x = p1.x-10;x<p1.x+11;x++)
				for(int y = p1.y-10;y<p1.y+11;y++)
					map[x][y] = 3;
			for(int x = p2.x-10;x<p2.x+11;x++)
				for(int y = p2.y-10;y<p2.y+11;y++)
					map[x][y] = 3;
		}
	}
	
	private int p1sendrnd;
	private int p2sendrnd;
	private int p3sendrnd;
	public void sendRnd(int pone)
	{
		if(pone == 3 && !three)
			return;
		
		if(finalized)
			return;
		int x = (int) (Math.random()*(200));
		int y = (int) (Math.random()*(200));
		int x2 = (int) (Math.random()*(200));
		int y2 = (int) (Math.random()*(200));
		
		if(pone == 1)
		{
			if(p1sendrnd != 45)
			{
				map[x][y] = 1;
				p2.setLocation(x,y);
				p3.setLocation(x2,y2);
				p1sendrnd++;
			}
		}
		else if(pone == 2)
		{
			if(p2sendrnd != 45)
			{
				map[x][y] = 2;
				p1.setLocation(x,y);
				p3.setLocation(x2,y2);
				p2sendrnd++;
			}
		}
		else
		{
			if(p3sendrnd != 45)
			{
				map[x][y] = 3;
				p1.setLocation(x,y);
				p2.setLocation(x2,y2);
				p3sendrnd++;
			}
		}
	}
	
	private int p1clear;
	private int p2clear;
	private int p3clear;
	public void clear(int pone)
	{
		if(finalized)
			return;
		if(pone == 3 && !three)
			return;
		
		if(pone == 1)
			if(p1clear != 3)
			{
				for(int x = 0;x<200;x++)
					for(int y = 0;y<200;y++)
						map[x][y] = 0;
				p1clear++;
			}

		if(pone == 2)
			if(p2clear != 3)
			{
				for(int x = 0;x<200;x++)
					for(int y = 0;y<200;y++)
						map[x][y] = 0;
				p2clear++;
			}	
		
		if(pone == 3)
			if(p3clear != 3)
			{
				for(int x = 0;x<200;x++)
					for(int y = 0;y<200;y++)
						map[x][y] = 0;
				p3clear++;
			}	
	}
	
	private int p1fill;
	private int p2fill;
	private int p3fill;
	public void fill(int pone)
	{
		if(finalized)
			return;
		if(pone == 3 && !three)
			return;
		
		if(pone == 1)
			if(p1fill != 1)
			{
				for(int x = 0;x<200;x++)
					for(int y = 0;y<200;y++)
						map[x][y] = 1;
				p1fill++;
			}

		if(pone == 2)
			if(p2fill != 1)
			{
				for(int x = 0;x<200;x++)
					for(int y = 0;y<200;y++)
						map[x][y] = 2;
				p2fill++;
			}	
		
		if(pone == 3)
			if(p3fill != 1)
			{
				for(int x = 0;x<200;x++)
					for(int y = 0;y<200;y++)
						map[x][y] = 3;
				p3fill++;
			}	
	}
	
	public class Key extends KeyAdapter
	{	
		private Conquer c;
		
		public Key()
		{
			hold1 = ' ';
			hold2 = ' ';
			hold3 = ' ';
			hold4 = ' ';
			hold5 = ' ';
			hold6 = ' ';
			hold7 = ' ';
			hold8 = ' ';
			hold9 = ' ';
		}
		
		public void game(Conquer c)
		{
			this.c=c;
		}
		
		private char hold1;
		private char hold2;
		private char hold3;
		private char hold4;
		private char hold5;
		private char hold6;
		private char hold7;
		private char hold8;
		private char hold9;
		
		public void keyPressed(KeyEvent arg0)
		{
			char key = arg0.getKeyChar();
			arg0.consume();
			
			if(key == 'q')
				c.sendBack(1);
			else if(key == '7')
				c.sendBack(2);
			else if(key == 'u')
				c.sendBack(3);
			else if(key == 'e')
				c.sendRnd(1);
			else if(key == '9')
				c.sendRnd(2);
			else if(key == 'o')
				c.sendRnd(3);
			else if(key == 'c')
				c.boxMe(1);
			else if(key == '3')
				c.boxMe(2);
			else if(key == '.')
				c.boxMe(3);
			else if(key == 'z')
				c.column(1);
			else if(key == 'm')
				c.column(3);
			else if(key == '1')
				c.column(2);
			else if(key == 'x')
				c.row(1);
			else if(key == ',')
				c.row(3);
			else if(key == '2')
				c.row(2);
			else if(key == 'v')
				c.boxHim(1);
			else if(key == '0')
				c.boxHim(2);
			else if(key == '/')
				c.boxHim(3);
			else if(key == 'f')
				c.fill(1);
			else if(key == '*')
				c.fill(2);
			else if(key == ';')
				c.fill(3);
			else if(key == 'r')
				c.clear(1);
			else if(key == '+')
				c.clear(2);
			else if(key == 'p')
				c.clear(3);
			else if(arg0.isControlDown() && arg0.isAltDown() && arg0.isShiftDown())
				c.ended = true;
			else
			{
				if(hold1 == ' ' || hold1 == key)
					hold1 = key;
				else if(hold2 == ' ' || hold2 == key)
					hold2 = key;
				else if(hold3 == ' ' || hold3 == key)
					hold3 = key;
				else if(hold4 == ' ' || hold4 == key)
					hold4 = key;
				else if(hold5 == ' ' || hold5 == key)
					hold5 = key;
				else if(hold6 == ' ' || hold6 == key)
					hold6 = key;
				else if(hold7 == ' ' || hold7 == key)
					hold7 = key;
				else if(hold8 == ' ' || hold8 == key)
					hold8 = key;
				else if(hold9 == ' ' || hold9 == key)
					hold9 = key;
			}
			makeMoves();
		}
		
		public void makeMoves()
		{
			char k = ' ';
			
			for(int x = 0; x<9;x++)
			{
				if(x == 0)
					k = hold1;
				else if(x == 1)
					k = hold2;
				else if(x == 2)
					k = hold3;
				else if(x == 3)
					k = hold4;
				else if(x == 4)
					k = hold5;
				else if(x == 5)
					k = hold6;
				else if(x == 6)
					k = hold7;
				else if(x == 7)
					k = hold8;
				else if(x == 8)
					k = hold9;
				
				if(k == 'w')
					c.moveP1up();
				if(k == 's')
					c.moveP1down();
				if(k == 'a')
					c.moveP1left();
				if(k == 'd')
					c.moveP1right();
		
				if(k == '8')
					c.moveP2up();
				if(k == '5')
					c.moveP2down();
				if(k == '4')
					c.moveP2left();
				if(k == '6')
					c.moveP2right();

				if(k == 'i')
					c.moveP3up();
				if(k == 'k')
					c.moveP3down();
				if(k == 'j')
					c.moveP3left();
				if(k == 'l')
					c.moveP3right();
			}
		}

		public void keyReleased(KeyEvent arg0)
		{
			char key = arg0.getKeyChar();
			arg0.consume();
			
			if(hold1 == key)
				hold1 = ' ';
			if(hold2 == key)
				hold2 = ' ';
			if(hold3 == key)
				hold3 = ' ';
			if(hold4 == key)
				hold4 = ' ';
			if(hold5 == key)
				hold5 = ' ';
			if(hold6 == key)
				hold6 = ' ';
			if(hold7 == key)
				hold7 = ' ';
			if(hold8 == key)
				hold8 = ' ';
			if(hold9 == key)
				hold9 = ' ';
			makeMoves();
		}
		
		public void keyTyped(KeyEvent arg0)
		{
		}
	}
}