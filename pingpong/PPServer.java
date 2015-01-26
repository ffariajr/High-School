package pingpong;

import java.io.*;
import java.net.*;
import java.util.*;

public class PPServer implements Runnable
{
	public static void main(String[] args)
	{
		PPServer pp = new PPServer();
		Thread t = new Thread(pp);
		t.start();
	}
	
	public ServerSocket ss;
	public ArrayList<Player> players;
	public ArrayList<Room> rooms;
	
	public PPServer()
	{
		players = new ArrayList<Player>();
		rooms = new ArrayList<Room>();
		
		try
		{
			ss = new ServerSocket(1234);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("ERROR: Server Terminated");
			System.exit(0);
		}
	}
	
	public void run()
	{
		Socket s;
		
		while(!Thread.interrupted())
		{
			try
			{
				System.out.println("waiting");
				s = ss.accept();
				System.out.println("accepted");
				Thread t = new Thread(new PPClientHandler(this,s));
				t.start();
			} 
			catch (IOException e)
			{
				kill();
				e.printStackTrace();
			}
		}
	}
	
	protected void addRoom(Room newRoom)
	{
		rooms.add(newRoom);
	}
	
	protected void removeRoom(Room newRoom)
	{
		rooms.remove(newRoom);
	}
	
	protected void addPlayer(Player player)
	{
		players.add(player);
	}
	
	protected void removePlayer(Player p)
	{
		players.remove(p);
	}
	
	protected ArrayList<Room> getRooms()
	{
		return rooms;
	}
	
	protected Room getRoom(int num)
	{
		return rooms.get(num-1);
	}
	
	private void kill()
	{
		for(Room r:rooms)
			r.close();
	}
	
	protected boolean canEnter(int num)
	{
		return !rooms.get(num-1).isFull();
	}
	
	public boolean contains(String name)
	{
		for(Player p:players)
		{
			if(p.username().equals(name))
				return true;
		}
		return false;
	}
}
