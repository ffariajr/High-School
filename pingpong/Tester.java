package pingpong;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Tester
{
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		Socket s = new Socket("localHost", 1234);
		Scanner in = new Scanner(s.getInputStream());
		PrintWriter out = new PrintWriter(s.getOutputStream());
		
		out.println("f");
		out.flush();
		
		System.out.println(in.nextLine());
		
		out.println("new");
		out.flush();
		
		while(true)
			if(in.hasNext())
				System.out.print(in.nextLine());
	}
}
