package ticktactoe;

public class Room
{
	private Player host;
	private Player client;
	private int[] board;
	private boolean hostTurn;
	private TTTServer ts;
	private boolean xturn;
	
	public Room(Player host, TTTServer ts)
	{
		this.ts = ts;
		host.makeHost();
		this.host=host;
		board = new int[9];
		hostTurn = (((int)(Math.random()*2)) == 0);
		xturn = true;
		host.enterRoom(this);
	}
	
	public void close()
	{
		System.out.println("host close");
		host.close();
		ts.removePlayer(host);
		ts.removeRoom(this);
		host = null;
		System.out.println("host traces removed");
		if(client != null)
		{
			client.close();
			ts.removePlayer(client);
			client = null;
		}
	}
	
	private int sendTurn()
	{
		System.out.println("sending turn");
		int move;
		if(hostTurn)
		{
			move = host.turn();
		}
		else
		{
			move = client.turn();
		}
		System.out.println("turn got");
		return move;
	}
	
	public boolean isWaiting()
	{
		return client == null;
	}
	
	public Player getHost()
	{
		return host;
	}
	
	public Player getClient()
	{
		return client;
	}
	
	public boolean contains(String name)
	{
		if(client != null)
			return client.equals(name) || host.equals(name);
		return host.equals(name);
	}
	
	public void begin()
	{
		System.out.println("start");
		sendAll("START");
		sendBoardAll();
		System.out.println("board sent");
		while(status() == 'p')
		{
			put(sendTurn());
			sendBoardAll();
		}
		System.out.println("game end");
		sendStatus();
	}
	
	private void sendBoardAll()
	{
		host.sendBoard(board);
		client.sendBoard(board);
		System.out.println("board resent");
	}
	
	private void sendStatus()
	{
		String win = winBox();
		if(status() == 'h')
		{
			client.send("LOSS "+win);
			host.send("WIN "+win);
		}
		else if(status() == 'c')
		{
			host.send("LOSS "+win);
			client.send("WIN "+win);
		}
		else if(status() == 't')
		{
			sendAll("TIE");
		}
		
		String msg1 = host.response();
		String msg2 = client.response();
		
		if(msg1.equals("REPEAT") && msg2.equals("REPEAT"))
		{
			board = new int[9];
			hostTurn = (((int)(Math.random()*2)) == 0);
			begin();
		}
		else if(msg1.equals("BOOT"))
		{
			board = new int[9];
			hostTurn = (((int)(Math.random()*2)) == 0);
			client.send("BOOTED");
			
			client.leaveRoom();
			client.close();
			client = null;
			chill();
		}
		else
		{
			if(msg2.equals("END"))
			{
				client.close();
				client = null;
				chill();
			}
			if(msg1.equals("END"))
			{
				client.leaveRoom();
				client.close();
				client = null;
				close();
			}
		}
	}
	
	private String winBox()
	{
		if((board[0] == 1 && board[1] == 1 && board[2] == 1) || (board[0] == 2 && board[1] == 2 && board[2] == 2))
			return "0 2";
		else if((board[3] == 1 && board[4] == 1 && board[5] == 1) || (board[3] == 2 && board[4] == 2 && board[5] == 2))
			return "3 5";
		else if((board[6] == 1 && board[7] == 1 && board[8] == 1) || (board[6] == 2 && board[7] == 2 && board[8] == 2))
			return "6 8";
		else if((board[0] == 1 && board[3] == 1 && board[6] == 1) || (board[0] == 2 && board[3] == 2 && board[6] == 2))
			return "0 6";
		else if((board[1] == 1 && board[4] == 1 && board[7] == 1) || (board[1] == 2 && board[4] == 2 && board[7] == 2))
			return "1 7";
		else if((board[2] == 1 && board[5] == 1 && board[8] == 1) || (board[2] == 2 && board[5] == 2 && board[8] == 2))
			return "2 8";
		else if((board[0] == 1 && board[4] == 1 && board[8] == 1) || (board[0] == 2 && board[4] == 2 && board[8] == 2))
			return "0 8";
		else if((board[2] == 1 && board[4] == 1 && board[6] == 1) || (board[2] == 2 && board[4] == 2 && board[6] == 2))
			return "2 6";
		return null;
	}
	
	private char status()
	{
		if((board[0] == 1 && board[1] == 1 && board[2] == 1) || (board[3] == 1 && board[4] == 1 && board[5] == 1) || (board[6] == 1 && board[7] == 1 && board[8] == 1) || (board[0] == 1 && board[4] == 1 && board[8] == 1) || (board[2] == 1 && board[4] == 1 && board[6] == 1) || (board[0] == 1 && board[3] == 1 && board[6] == 1) || (board[1] == 1 && board[4] == 1 && board[7] == 1) || (board[2] == 1 && board[5] == 1 && board[8] == 1))
			return 'h';
		else if((board[0] == 2 && board[1] == 2 && board[2] == 2) || (board[3] == 2 && board[4] == 2 && board[5] == 2) || (board[6] == 2 && board[7] == 2 && board[8] == 2) || (board[0] == 2 && board[4] == 2 && board[8] == 2) || (board[2] == 2 && board[4] == 2 && board[6] == 2) || (board[0] == 2 && board[3] == 2 && board[6] == 2) || (board[1] == 2 && board[4] == 2 && board[7] == 2) || (board[2] == 2 && board[5] == 2 && board[8] == 2))
			return 'c';
		else if(board[0] != 0 && board[1] != 0 && board[2] != 0 && board[3] != 0 && board[4] != 0 && board[5] != 0 && board[6] != 0 && board[7] != 0 && board[8] != 0)
			return 't';
		else
			return 'p';
	}
	
	public int[] getBoard()
	{
		return board;
	}
	
	public void put(int pos)
	{
		pos -=1;
		System.out.println("put");
		if(pos <0 || pos > 8)
		{
			if(hostTurn)
				host.send("INVALID");
			else
				client.send("INVALID");
			return;
		}
		if(board[pos] != 0)
		{
			if(hostTurn)
				host.send("INVALID");
			else
				client.send("INVALID");
			return;
		}
		System.out.println(""+pos+": "+(xturn?1:2));
		board[pos] = xturn? 1:2;
		xturn = !xturn;
		hostTurn = !hostTurn;
	}
	
	private void sendAll(String str)
	{
		host.send(str);
		client.send(str);
	}
	
	public void chill()
	{
		System.out.println("Chilling");
		if(client == null)
			Thread.yield();
	}
	
	public void left(Player p)
	{
		if(p == client)
		{
			System.out.println("Client left");
			host.send("HOST");
			ts.removePlayer(client);
			client.close();
			client = null;
			chill();
		}
		else if(p == host)
		{
			if(client != null)
			{
				System.out.println("host left");
				client.send("HOST");
				ts.removePlayer(host);
				host.close();
				host = client;
				client = null;
				chill();
			}
			else
			{
				System.out.println("both left");
				this.close();
			}
		}
	}
	
	public Room add(Player p)
	{
		boolean accepted = host.accept(p.username());
		if(accepted)
		{
			client = p;
			client.enterRoom(this);
		}
		return accepted == true?this:null;
	}
}
