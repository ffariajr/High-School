package ticktactoe;

import java.io.*;
import java.net.*;
import java.util.*;

public class TTTServer implements Runnable
{
	public static void main(String[] args)
	{
		TTTServer ttt = new TTTServer();
		Thread t = new Thread(ttt);
		t.start();
	}
	
	private ServerSocket ss;
	private ArrayList<Room> rooms;
	private ArrayList<Player> players;
	
	public TTTServer()
	{
		rooms = new ArrayList<Room>();
		players = new ArrayList<Player>();
		
		try
		{
			ss = new ServerSocket(8889);
		} catch (IOException e)
		{
			System.err.println("Server Terminated");
			e.printStackTrace();
			System.exit(0);
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
	
	private void kill()
	{
		for(Room r:rooms)
			r.close();
	}
	
	protected boolean canEnter(int num)
	{
		return rooms.get(num-1).isWaiting();
	}
	
	protected Room enterRoom(Player p, int num)
	{
		return rooms.get(num-1).add(p);
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
	
	protected ArrayList<Player> getHosts()
	{
		ArrayList<Player> hosts = new ArrayList<Player>();
		for(Room r:rooms)
			if(r.isWaiting())
				hosts.add(r.getHost());
		return hosts;
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
				Thread t = new Thread(new TTTClientHandler(this,s));
				t.start();
			} 
			catch (IOException e)
			{
				kill();
				e.printStackTrace();
			}
		}
	}
}
