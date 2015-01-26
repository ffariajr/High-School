package tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JComponent
{
	/*
	 * x is 1
	 * o is -1
	 * 
	 * 
	 * 
	 */
	private static final long serialVersionUID = 995932718770849721L;

	public static void main(String[] args)
	{
		//play against computer
		int ans = JOptionPane.showConfirmDialog(null, "Play against computer?");
		//int ans  = JOptionPane.OK_OPTION;
		if(ans == JOptionPane.CANCEL_OPTION || ans == JOptionPane.CLOSED_OPTION)
			System.exit(0);
		
		//ask for difficulty
		String[] diffs = {"Easy", "Medium", "Hard"};
		Object diffans = JOptionPane.showInputDialog(null, "Select Difficulty", "Difficulty", JOptionPane.QUESTION_MESSAGE, null, diffs, diffs[1]);
		if(ans == JOptionPane.CANCEL_OPTION || ans == JOptionPane.CLOSED_OPTION)
			System.exit(0);
		if(diffans.equals(diffs[0]))
			BestMoveFinder.diff = 0;
		else if(diffans.equals(diffs[1]))
			BestMoveFinder.diff = 1;
		else
			BestMoveFinder.diff = 2;
		
		//set up game
		JFrame f = new JFrame();
		Board board = new Board(f, (ans==JOptionPane.OK_OPTION));
		f.setBounds(300, 100, 600, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.addMouseListener(board.getMouse());
		f.add(board);
		f.setVisible(true);
		board.play();
	}
	
	private int[] board;
	private boolean playerTurn;
	private boolean starter;
	private MouseListener mouse;
	private Rectangle r;
	private JFrame f;
	private boolean finished;
	private boolean comp;
	private Rectangle darken;
	
	public Board(JFrame f, boolean comp)
	{
		darken = new Rectangle();
		this.comp = comp;
		this.f=f;
		board = new int[9];
		//playerTurn = Math.random()>.5? true:false;                                 //change me mother fucker
		playerTurn = true;
		starter = !playerTurn;
		this.f.setTitle("TicTacToe - "+(playerTurn?"X":"O"));
		this.f.repaint();
		mouse = new Mouse();
		r = new Rectangle();
		finished = false;
	}
	
	public MouseListener getMouse()
	{
		return mouse;
	}
	
	public void put(int x, int y, boolean isX)
	{
		int col = x/(r.width/3);
		int row = y/(r.height/3);
		
		int box = col + 3*row;
		
		put(box, isX);
	}
	
	private void put(int box, boolean isX)
	{
		if(board[box] == 0 && !finished)
		{
			//System.err.println("playerTurn == "+playerTurn);								// error checker
			board[box] = isX?1:-1;
			playerTurn = !playerTurn;
			f.setTitle("TicTacToe - "+(playerTurn?"X":"O"));
		}
		f.repaint();
	}
	
	private void status(Graphics2D g2)
	{	
		g2.setColor(Color.RED);
		Stroke original = g2.getStroke();
		g2.setStroke(new BasicStroke(5));
		
		//left to right
		
		if((board[0] == 1 && board[1] == 1 && board[2] == 1) || (board[0] == -1 && board[1] == -1 && board[2] == -1))
		{
			finished = true;
			g2.drawLine(getMidX(0), getMidY(0), getMidX(2), getMidY(2));
		}
		else if((board[3] == 1 && board[4] == 1 && board[5] == 1) || (board[3] == -1 && board[4] == -1 && board[5] == -1))
		{
			finished = true;
			g2.drawLine(getMidX(3), getMidY(3), getMidX(5), getMidY(5));
		}
		else if((board[6] == 1 && board[7] == 1 && board[8] == 1) || (board[6] == -1 && board[7] == -1 && board[8] == -1))
		{
			finished = true;
			g2.drawLine(getMidX(6), getMidY(6), getMidX(8), getMidY(8));
		}
		
		//up to down
		
		else if((board[0] == 1 && board[3] == 1 && board[6] == 1) || (board[0] == -1 && board[3] == -1 && board[6] == -1))
		{
			finished = true;
			g2.drawLine(getMidX(0), getMidY(0), getMidX(6), getMidY(6));
		}
		else if((board[1] == 1 && board[4] == 1 && board[7] == 1) || (board[1] == -1 && board[4] == -1 && board[7] == -1))
		{
			finished = true;
			g2.drawLine(getMidX(1), getMidY(1), getMidX(7), getMidY(7));
		}
		else if((board[2] == 1 && board[5] == 1 && board[8] == 1) || (board[2] == -1 && board[5] == -1 && board[8] == -1))
		{
			finished = true;
			g2.drawLine(getMidX(2), getMidY(2), getMidX(8), getMidY(8));
		}
		
		//diagonals
		
		else if((board[0] == 1 && board[4] == 1 && board[8] == 1) || (board[0] == -1 && board[4] == -1 && board[8] == -1))
		{
			finished = true;
			g2.drawLine(getMidX(0), getMidY(0), getMidX(8), getMidY(8));
		}
		else if((board[2] == 1 && board[4] == 1 && board[6] == 1) || (board[2] == -1 && board[4] == -1 && board[6] == -1))
		{
			finished = true;
			g2.drawLine(getMidX(2), getMidY(2), getMidX(6), getMidY(6));
		}
		
		
		if(!finished) {
			int c = 0;
			for(int q : board)
				if(q == 0)
					c++;
			if(c == 0)
				finished = true;
		}
		
		g2.setStroke(original);
		g2.setColor(Color.BLACK);
	}
	
	private int getMidX(int box)
	{
		int w = r.width/3;
		int temp = box;
		
		switch(temp)
		{
			case 0:
			case 3:
			case 6:
				box = 0;
				break;
			case 1:
			case 4:
			case 7:
				box = 1;
				break;
			default:
				box = 2;
		}
		
		return box*w + w/2;
	}
	
	private int getMidY(int box)
	{
		int h = r.height/3;
		int temp = box;
		
		switch(temp)
		{
			case 0:
			case 1:
			case 2:
				box = 0;
				break;
			case 3:
			case 4:
			case 5:
				box = 1;
				break;
			default:
				box = 2;
		}
		
		return box*h + h/2;
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		r = g2.getClipBounds();
		int w = r.width;
		int h = r.height;
		
		g2.drawLine(w/3, 0, w/3, h);
		g2.drawLine(2*w/3, 0, 2*w/3, h);
		g2.drawLine(0, h/3, w, h/3);
		g2.drawLine(0, 2*h/3, w, 2*h/3);
		
		for(int z = 0; z<9;z++)
		{
			int row = 0;
			int col = 0;
			switch(z)
			{
				case 0:
				case 1:
				case 2:
					row = 0;
					break;
				case 3:
				case 4:
				case 5:
					row = 1;
					break;
				default:
					row = 2;
			}
			
			switch(z)
			{
				case 0:
				case 3:
				case 6:
					col = 0;
					break;
				case 1:
				case 4:
				case 7:
					col = 1;
					break;
				default:
					col = 2;
			}
			
			int modx = col*(w/3);
			int mody = row*(h/3);
			
			if(board[z] == 1)
				paintX(g2, modx, mody, w/3, h/3);
			else if(board[z] == -1)
				paintO(g2, modx, mody, w/3, h/3);
		}
		dark(g2);
		status(g2);
	}

	
	private void paintX(Graphics2D g2, int x, int y, int width, int height)
	{
		g2.drawLine(x, y, x+width, y+height);
		g2.drawLine(x, y+height, x+width, y);
	}
	
	private void paintO(Graphics2D g2, int x, int y, int width, int height)
	{
		g2.drawOval(x, y, width, height);
	}
	
	private void dark(Graphics2D g2)
	{
		if(darken == null)
			return;
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillRect(darken.x, darken.y, darken.width, darken.height);
		g2.setColor(Color.BLACK);
	}
	
	public void play()
	{
		while(!finished)
		{
			try
			{
				Thread.sleep(150);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			
			//System.err.print("_.");												// error checker
			
			if(!playerTurn && comp && !finished)
			{
				int[] copy = new int[9];
				System.arraycopy(board, 0, copy, 0, 9);
				int p = BestMoveFinder.find(copy, -1 );
				
				//System.err.println("computer placement: "+p);						// error checker
				
				put(p, false);
			}
			f.repaint();
		}
		startOver();
	}
	
	public void startOver()
	{
		int ans = JOptionPane.showConfirmDialog(null, "Play Again?");
		if(ans == JOptionPane.OK_OPTION)
		{
			board = new int[9];
			r = null;
			finished = false;
			darken = null;
			playerTurn = starter;
			starter = !starter;
			f.setTitle("TicTacToe - "+(playerTurn?"X":"O"));
			f.repaint();
			play();
		}
		else
			System.exit(0);
	}
	
	public class Mouse implements MouseListener
	{
		private int x;
		private int y;
		
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
			x = arg0.getX();
			y = arg0.getY();
			if(finished)
				startOver();
			int w = r.width;
			int h = r.height;
			
			int col = (arg0.getX()-5)/(w/3);
			int row = (arg0.getY()-50)/(h/3);
			
			darken = new Rectangle(col*(w/3), row*(h/3), w/3, h/3);
			f.repaint();
		}

		public void mouseReleased(MouseEvent arg0)
		{
			darken = null;
			
			int xt = arg0.getX();
			int yt = arg0.getY();
			
			if(!((xt == x-1 || xt == x+1 || xt == x) && (yt == y-1 || yt == y+1 || yt == y)))
				return;
				
			System.err.println("1");												// error checker
			
			if(playerTurn && !finished)
				put(arg0.getX()-5, arg0.getY()-50, true);
			if(!playerTurn && !finished && !comp)
				put(arg0.getX()-5, arg0.getY()-50, false);
		}
	}
}
