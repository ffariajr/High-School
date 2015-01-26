package musicTransfer;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientDownload
{
	public static void main(String[] args)
	{
		
		System.out.println("Input file path");
		System.err.println("MUST be perfect!!!");
		Scanner myscanner = new Scanner(System.in);
		String path = myscanner.next();
		
		ClientDownload me = new ClientDownload(path);
		me.begin();
	}
	
	public ClientDownload(String path)
	{
		this.path=path;
	}
	
	private String path;
	
	public void begin()
	{
		Socket s = null;
		try
		{
			System.out.println("Trying to connect");
			s = new Socket("127.0.0.1", 2812);
		}
		catch (UnknownHostException e)
		{
			System.err.println("host is bad");
			System.exit(0);
		}
		catch (IOException e)
		{
			System.err.println("IOException");
			System.exit(0);
		}
		
		try
		{
			InputStream in = s.getInputStream();
			PrintWriter pr = new PrintWriter(s.getOutputStream());
			
			pr.print("start");
			
			pr.print("size");
			int size = in.read();
			
			FileOutputStream write = null;
			
			for(int x = 0; x< size;x++)
			{
				pr.print("next");
				while(true)
				{
					write = new FileOutputStream(path+"imported_file_"+x);
				
					int num = in.read();
				
					if(num == -1)
						break;
				
					write.write(num);
				}
			}
		}
		catch (IOException e)
		{
			System.err.println("IOException in run()");
			System.exit(0);
		}
	}
}
