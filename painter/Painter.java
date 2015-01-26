package painter;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class Painter extends JComponent
{
	public static void main(String[] args)
	{
		JFrame f = new JFrame();
		
		int x = 300;
		int y = 300;
		
		f.setBounds(50, 50, x, y+10);
		Painter p = new Painter(f,x,y+10);
		f.add(p);
		f.addKeyListener(p.getListener());
		f.addMouseMotionListener(p.getListener());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Painter");
		f.setResizable(false);
		f.setVisible(true);
		p.start();
	}
	
	private static String getName(Color color)
	{
		Color col = color.brighter().brighter().brighter().brighter().brighter().brighter().brighter();
		
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

	private Point brush;
	private Color col;
	private int radius;
	private Color[][] map;
	private Listener listen;
	private boolean first;
	private JFrame f;
	private int width;
	private int height;
	
	public Painter(JFrame f, int width, int height)
	{
		this.f=f;
		this.width=width;
		this.height=height;
		brush = new Point(0,0);
		col = Color.BLACK;
		radius = 5;
		map = new Color[width][height];
		listen = new Listener(this);
		first = true;
	}
	
	public Listener getListener()
	{
		return listen;
	}
	
	public void start()
	{
		while(true)
			f.repaint();
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		
		if(first)
		{
			for(int x = 0;x<width;x++)
				for(int y = 0;y<height;y++)
					map[x][y] = Color.white;
			first = false;
		}
		
		for(int x = 0;x<width;x++)
			for(int y = 0;y<height;y++)
			{
				g2.setColor(map[x][y]);
				g2.drawLine(x, y, x, y);
			}
		
		
		g2.setColor(Color.white);
		g2.fillRect(0, 0, width, 10);
		g2.setColor(Color.black);
		g2.drawString("brush color: "+getName(col)+"    brush radius: "+radius+"    brush position: ( "+brush.x+" , "+brush.y+" )", 1, 10);			
	}
	
	private void brush(Point p)
	{
		brush = new Point(p.x-4, p.y-25);
	}
	
	private void points()
	{
		Shape s = new Ellipse2D.Double(brush.x-radius, brush.y-radius, radius*2, radius*2);
		
		for(int x = brush.x-radius-1;x<brush.x+radius+radius+1;x++)
			for(int y = brush.y-radius-1;y<brush.y+radius+radius+1;y++)
				if(s.contains(new Point(x,y)))
					map[x][y] = col;
	}
	
	public class Listener implements KeyListener, MouseMotionListener
	{
		private Painter p;
		
		public Listener(Painter p)
		{
			this.p=p;
		}
		
		public void keyPressed(KeyEvent arg0)
		{
		}

		public void keyReleased(KeyEvent arg0)
		{
		}

		public void keyTyped(KeyEvent arg0)
		{
			char key = arg0.getKeyChar();
			
			switch(key)
			{
				case '`':
					col = Color.white;
					break;
				case '1':
					col = Color.yellow;
					break;
				case '2':
					col = Color.orange;
					break;
				case '3':
					col = Color.magenta;
					break;
				case '4':
					col = Color.pink;
					break;
				case '5':
					col = Color.red;
					break;
				case '6':
					col = Color.blue;
					break;
				case '7':
					col = Color.cyan;
					break;
				case '8':
					col = Color.green;
					break;
				case '9':
					col = Color.gray;
					break;
				case '0':
					col = Color.black;
					break;
				case ',':
					radius--;
					 if(radius == 0)
						radius = 1;
					break;
				case '.':
					radius++;
					if(radius == 101)
						radius = 100;
					break;
				case '=':
					col=col.brighter();
					break;
				case '-':
					col=col.darker();
					 break;
				case '/':
					col=map[brush.x][brush.y];
					break;
			}
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
			p.brush(arg0.getPoint());
			p.points();
		}

		public void mouseReleased(MouseEvent arg0)
		{
		}

		public void mouseDragged(MouseEvent e)
		{
			p.brush(e.getPoint());
			p.points();
		}

		public void mouseMoved(MouseEvent e)
		{
			p.brush(e.getPoint());
		}	
	}
}
