package musicTransfer;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class DownloadServer
{
	private boolean quitted;
	
	public static void main(String[] args)
	{
		DownloadServer ds = new DownloadServer();
		ds.start();
	}
	
	
	public void start()
	{
		ServerSocket ss = null;
		try
		{
			ss = new ServerSocket(2812);
			System.out.println("waiting for connection...");
			Server sender = new Server(ss.accept());
			System.out.println("Connected and handling");
			sender.handle();
			ss.close();
		}
		catch (IOException e)
		{
		}
	}
	
	private class Server
	{
		private Socket s;
		
		public Server(Socket s)
		{
			this.s=s;
		}
		
		public void handle()
		{
			try
			{
				InputStream in = s.getInputStream();
				Scanner scan = new Scanner(in);
					
				OutputStream out = s.getOutputStream();
				JFileChooser j = new JFileChooser("C:/Documents and Settings/Fernando Jr/My Documents/My Music/");
				j.setMultiSelectionEnabled(true);
				j.showOpenDialog(j);
				File[] files = j.getSelectedFiles();
				
				System.out.print(files.length);
				
				out.write(files.length);
				out.flush();
				
				System.out.println(" files to upload");
				
				FileInputStream current = null;
				for(int x = 0;x<files.length;x++)
				{	
					current = new FileInputStream(files[x]);
					
					System.out.println("uploading next file");
					
					/*long filesize = files[x].length();
					int arrs = (int)(filesize / (long)Integer.MAX_VALUE);
					int rem = (int)(filesize % (long)Integer.MAX_VALUE);
					byte[][] b = new byte[arrs][Integer.MAX_VALUE];
					byte[] brem = new byte[rem];
					
					for(int one = 0;one<arrs; one++)
						current.read(b[one]);
					current.read(brem);
					*/
					
					DownloadBar bar = new DownloadBar(files[x].length());
					Thread t = new Thread(bar);
					t.start();
					
					for(long z = 0; z< files[x].length();z++)
					{
						out.write(current.read());
						bar.add();
					}
					
					t.interrupt();
						
					out.write(-1);
					out.flush();
					
					
					
					int xplus = x+1;
					
					System.out.println(""+xplus+" files uploaded");
					/*
					for(int one = 0;one<arrs; one++)
						out.write(b[one]);
					out.write(brem);
					out.flush();*/
				}
			}
			catch (IOException e)
			{
			}
			System.out.println("Done");
			
			try
			{
				s.close();
			}
			catch (IOException e)
			{
			}
		}
	}
}