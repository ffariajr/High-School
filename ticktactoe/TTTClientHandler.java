package ticktactoe;

import java.io.*;
import java.net.*;
import java.util.*;

public class TTTClientHandler implements Runnable
{
	private TTTServer ts;
	private Socket s;
	private PrintWriter out;
	private Scanner in;
	private String username;
	
	public TTTClientHandler(TTTServer ts, Socket s)
	{
		this.ts=ts;
		this.s=s;
		
		try
		{
			out = new PrintWriter(s.getOutputStream());
			in = new Scanner(s.getInputStream());
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void sendHosts()
	{
		ArrayList<Player> hosts = ts.getHosts();
		System.out.println("hosts: "+hosts.size());
		for(Player pl:hosts)
		{
			out.println("HOST "+pl.username());
			out.flush();
		}
		out.println("LISTEND");
		out.flush();
		
		System.out.println("listend");
	}
	
	protected void runner(Player p)
	{
		boolean entered = false;
		while(!entered)
		{
			sendHosts();
			
			String input = in.nextLine();
			if(input.substring(0,3).equals("PIC"))
			{
				System.out.println("pick");
				int num = Integer.parseInt(input.substring(5));
				
				System.out.println("num");
				
				if(!ts.canEnter(num))
				{
					System.out.println("invalid 1");
					out.println("INVALID1");
					out.flush();
				}
				else
				{
					Room ans = ts.enterRoom(p, num);
					
					if(ans == null)
					{
						System.out.println("invalid 2");
						out.println("INVALID2");
						out.flush();
					}
					else
					{
						p.enterRoom(ans);
						System.out.println("valid");
						out.println("VALID");
						out.flush();
						entered = true;
						ans.begin();
					}
				}
			}
			else
			{
				System.out.println("new room");
				Room r = new Room(p, ts);
				ts.addRoom(r);
				out.println("VALID");
				out.flush();
				p.enterRoom(r);
				entered = true;
				System.out.println("begin");
				r.chill();
			}
		}
	}
	
	public void run()
	{
		String user = in.nextLine();
		while(ts.contains(user))
		{
			System.out.println("invalid");
			out.println("INVALID");
			out.flush();
			user = in.nextLine();
		}
		username = user;
		out.println("VALID");
		out.flush();
		Player p = new Player(username, s, in, out);
		ts.addPlayer(p);
		
		System.out.println("new player made");
		
		runner(p);
	}
}
