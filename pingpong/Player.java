package pingpong;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Player implements Runnable
{
	private String username;
	private Point position;
	private final int length = 60;
	private final int width = 8;
	private char pos;
	private Socket s;
	private PrintWriter out;
	private Scanner in;
	private final int bounds = 600;
	private Room r;
	private Point origin;
	private int score;

	public Player(String username, Socket s, InputStream in, OutputStream out)
	{
		this.username = username;
		this.s = s;
		this.in = new Scanner(in);
		this.out = new PrintWriter(out);
		score = 0;
	}

	public void lost()
	{
		score++;
	}

	public int score()
	{
		return score;
	}

	public int x()
	{
		return position.x;
	}

	public int y()
	{
		return position.y;
	}

	public void enterRoom(Room r)
	{
		this.r = r;
	}

	public void reset()
	{
		position = origin;
	}

	public char place()
	{
		return pos;
	}

	public void position(char position)
	{
		pos = position;
		System.out.println("sending position " + pos);
		out.println(pos);
		out.flush();
	}

	public void send(String str)
	{
		out.println(str);
		out.flush();
	}

	public void close(PPServer ps)
	{
		try
		{
			s.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		ps.removePlayer(this);
	}

	public void position(char pos, Point position)
	{
		position(pos);
		this.position = position;
		origin = position;
	}

	public String username()
	{
		return null;
	}

	public void run()
	{
		while (!Thread.interrupted())
			listen();
	}

	public Point listen()
	{
		String next = "";

		try
		{

			if (in.hasNextLine())
				next = in.nextLine();
			else return null;
		}
		/*
		 * catch(NoSuchElementException e) {
		 * System.err.println(username+
		 * " did not send anything :("); return
		 * null; //e.printStackTrace(); }
		 */
		catch (IllegalStateException e)
		{
			r.leave(this);
		}

		if (next == null || !next.contains("paddle")) return null;

		if (next.equals("QUIT")) r.leave(this);

		// System.out.println(next);

		Scanner parser = new Scanner(next);

		int[] coords = new int[2];
		int i = 0;
		while (parser.hasNextInt())
		{
			coords[i] = parser.nextInt();
			i++;
		}

		int x = coords[0];
		int y = coords[1];

		try
		{
			parser.next();
			String xval = parser.next().replace("(", "");
			xval = xval.replace(",", "");
			x = Integer.parseInt(xval);

			String yval = parser.next().replace(")", "");
			y = Integer.parseInt(yval);
		}
		catch (Exception e)
		{
			x = -1;
		}

		if (x != -1) position.setLocation(x, y);
		// System.err.println(""+x+" "+y);
		return position;
	}

	public double hitAngle(Point ballpoint, Ball ball)
	{
		// return 2 if lost point
		// return -2 if still in play
		// return 0<=num<=1 for the position the
		// ball hit the paddle

		if (pos == 'l') if (ballpoint.x <= position.x + 8 && ballpoint.x >= position.x && ballpoint.y >= position.y && ballpoint.y <= position.y + 60)
			if (ball.getangle() < 90 || ball.getangle() > 270)
				return -(ballpoint.y - position.y) / 60.0;
			else return (ballpoint.y - position.y) / 60.0;
		else if (ballpoint.x > position.x + 8)
			return -2;
		else if (ballpoint.x < position.x) return 2;

		if (pos == 'r') if (ballpoint.x >= position.x && ballpoint.x <= position.x + 8 && ballpoint.y >= position.y && ballpoint.y <= position.y + 60)
			if (ball.getangle() > 90 || ball.getangle() < 270)
				return -(ballpoint.y - position.y) / 60.0;
			else return (ballpoint.y - position.y) / 60.0;
		else if (ballpoint.x < position.x)
			return -2;
		else if (ballpoint.x > position.x) return 2;

		// System.out.println("ball: x"+ball.x+" y:"+ball.y);
		// System.out.println("paddle x:"+position.x+" y:"+position.y);

		if (pos == 'u') if (ballpoint.y <= position.y + 8 && ballpoint.y >= position.y && ballpoint.x >= position.x && ballpoint.x <= position.x + 60)
			if (ball.getangle() > 180)
				return -(ballpoint.x - position.x) / 60.0;
			else return (ballpoint.x - position.x) / 60.0;
		else if (ballpoint.y > position.y + 8)
			return -2;
		else if (ballpoint.y < position.y) return 2;

		if (pos == 'd') if (ballpoint.y >= position.y && ballpoint.y <= position.y + 8 && ballpoint.x >= position.x && ballpoint.x <= position.x + 60)
			if (ball.getangle() < 180)
				return (ballpoint.x - position.x) / 60.0;
			else return -(ballpoint.x - position.x) / 60.0;
		else if (ballpoint.y < position.y)
			return -2;
		else if (ballpoint.y > position.y) return 2;

		return -2;

		// return 2 if lost point
		// return -1 if still in play
		// return 0<=num<=1 for the position the
		// ball hit the paddle

		/*
		 * if(pos == 'l') if(ball.x > position.x
		 * ball.x > && ball.y > position.y &&
		 * ball.y < position.y+60) return
		 * (ball.y-position.y)/60; else
		 * if(ball.x > position.x+8) return -1;
		 * else if(ball.x < position.x+8) return
		 * 2; if(pos == 'r') if(ball.x <
		 * position.x) return -1; else if(ball.x
		 * > position.x) return 2; else
		 * if(ball.x < position.x+8 && ball.y >
		 * position.y && ball.y < position.y+60)
		 * return (ball.y-position.y)/60; if(pos
		 * == 'u') if(ball.y > position.y+8)
		 * return -1; else if(ball.y <
		 * position.y+8) return 2; else
		 * if(ball.y )
		 */

		// return -1;

		/*
		 * int absoluteball; if(pos == 'l' ||
		 * pos == 'r') { int moder = bounds -
		 * position.y; absoluteball = ball.y -
		 * moder; if(pos =='l' && (ball.y+5 <
		 * position.y || ball.y-5 >
		 * position.y+length) && ball.x-5 <
		 * position.x) return 2; if(pos =='r' &&
		 * (ball.y+5 < position.y || ball.y-5 >
		 * position.y+length) && ball.x+5 >
		 * position.x+width) return 2; if((pos
		 * == 'l' && ball.x-5 >
		 * position.x+width) || (pos == 'r' &&
		 * ball.x+5 < position.x)) return -1; }
		 * else { int moder = bounds -
		 * position.x; absoluteball = ball.x -
		 * moder; if(pos =='u' && (ball.x+5 <
		 * position.x || ball.x-5 >
		 * position.x+length) && ball.y-5 <
		 * position.y) return 2; if(pos =='d' &&
		 * (ball.x+5 < position.x || ball.x-5 >
		 * position.x+length) && ball.y+5 >
		 * position.x+width) return 2; if((pos
		 * == 'u' && ball.y-5 >
		 * position.y+width) || (pos == 'd' &&
		 * ball.y+5 < position.y)) return -1; }
		 * if(absoluteball < 0 || absoluteball >
		 * 60) return -1; return absoluteball /
		 * 60.0;
		 */
	}

	public double hitAngle(Ball ball)
	{
		return hitAngle(ball.point(), ball);

	}

	public boolean hit(Point ball)
	{
		if (pos == 'l' && ball.x - 5 <= position.x + width && ball.y > position.y && ball.y < position.y - length) return true;
		if (pos == 'r' && ball.x + 5 >= position.x && ball.y > position.y && ball.y < position.y - length) return true;
		if (pos == 'u' && ball.y - 5 <= position.y + width && ball.x > position.x && ball.x < position.x + length) return true;
		if (pos == 'd' && ball.y - 5 >= position.y && ball.x > position.x && ball.x < position.x + length) return true;
		return false;
	}

	public String toString()
	{
		return username;
	}
}
