package ticktactoe;

import java.io.*;
import java.net.*;
import java.util.*;

public class Player
{
	private String username;
	private Room room;
	private Socket sock;
	private Scanner in;
	private PrintWriter out;
	private boolean host;
	
	public Player(String username, Socket sock, Scanner in, PrintWriter out)
	{
		host = false;
		this.username=username;
		this.sock=sock;
		this.in = in;
		this.out = out;
	}
	
	public void leaveRoom()
	{
		room = null;
	}
	
	public void send(String str)
	{
		out.println(str);
		out.flush();
	}
	
	public String username()
	{
		return username;
	}
	
	public void enterRoom(Room room)
	{
		this.room=room;
	}
	
	public void makeHost()
	{
		host=true;
	}
	
	public void close()
	{
		room = null;
		try
		{
			sock.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public int turn()
	{
		out.println("TURN");
		out.flush();
		
		System.out.println("turn sent");
		String move = "";
		try
		{
			move = in.nextLine();
		}
		catch(NoSuchElementException e)
		{
			closed(null);
		}
		
		System.out.println("response got");
		closed(move);
		System.out.println("response: "+move);
		int place = Integer.parseInt(move.substring(5));
		System.out.println("got move son");
		return place;
	}
	
	private void closed(String str)
	{
		if(str == null)
			room.left(this);
		else if(str.equals("CLOSE") || str.equals(""))
			room.left(this);
	}
	
	public String response()
	{
		String response = "";
		try
		{
			response = in.nextLine();
		}
		catch(NoSuchElementException e)
		{
			closed(null);
		}
		
		closed(response);
		
		if(host && response.equals("BOOT"))
			return "BOOT";
		else if(response.equals("REPEAT"))
			return "REPEAT";
		else
			return "END";
		
	}
	
	public void sendBoard(int[] board)
	{
		String str = "BOARD";
		for(int x:board)
			str += " "+x;
		out.println(str);
		out.flush();
	}
	
	public void saying()
	{
		if(in.hasNext())
		{
			String str = in.nextLine();
			closed(str);
		}
	}
	
	public boolean accept(String id)
	{
		out.println("REQUEST "+id);
		out.flush();
		
		String yes = "";
		try
		{
			yes = in.nextLine();
		}
		catch(NoSuchElementException e)
		{
			closed(null);
		}
		return yes.equals("ACCEPT");
	}
}
