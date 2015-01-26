package pingpong;

import java.io.*;
import java.net.*;
import java.util.*;

public class PPClientHandler implements Runnable
{
	private PPServer ps;
	private Socket s;
	private PrintWriter out;
	private Scanner in;
	private String username;
	private InputStream input;
	private OutputStream output;
	
	
	public PPClientHandler(PPServer pp, Socket s)
	{
		ps=pp;
		this.s=s;
		
		try
		{
			input = s.getInputStream();
			output = s.getOutputStream();
			out = new PrintWriter(output);
			in = new Scanner(input);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void run()
	{	
		String user = "";
		//try
		//{
			System.out.println("running");
			
			System.out.println(in.hasNext());
			user = in.nextLine();
			out.println("valid");
			out.flush();
			
			System.out.println("here");
			System.out.println(user);
			
			Player p = new Player(user,s,input,output);
			
			System.out.println("new player made");
			
			
			//if(user.equals("mark"))
			//{
				out.println("valid");
				out.flush();
				Room r = new Room(ps);
				ps.addRoom(r);
				p.enterRoom(r);
				r.addPlayer(p);
				Thread t = new Thread(r);
				t.start();
				return;
			//}
			/*System.out.println("sending hosts");
			
			ArrayList<Room> rooms = ps.getRooms();
			
			for(Room r: rooms)
				if(!r.isFull())
				{
					out.println(r.toString());
					out.flush();
				}
			
			out.println("listend");
			out.flush();
			
			String choice = in.nextLine();
			
			if(choice.equals("new"))
			{
				out.println("valid");
				out.flush();
				Room r = new Room(ps);
				ps.addRoom(r);
				p.enterRoom(r);
				r.addPlayer(p);
				Thread t = new Thread(r);
				t.start();
			}
			else
			{
				if(choice.substring(0,3).equals("pic"))
				{
					System.out.println("pick");
					int num = Integer.parseInt(choice.substring(5));
					
					System.out.println("num");
					
					if(!ps.canEnter(num))
					{
						System.out.println("invalid");
						out.println("INVALID");
						out.flush();
					}
					else
					{
						Room ans = ps.getRoom(num);
						ans.addPlayer(p);
						
						p.enterRoom(ans);
						System.out.println("valid");
						out.println("VALID");
						out.flush();
						Thread t = new Thread(ans);
						t.start();
					}
				}
			}
		} 
		catch (NoSuchElementException e)
		{
			System.err.println("Connection terminated at the handler with: " + user );
			//e.printStackTrace();
		}*/
		}
}
