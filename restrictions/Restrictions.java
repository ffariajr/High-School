package restrictions;

import java.io.*;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

public class Restrictions
{
	public static void main(String[] args)
	{	
		File makelist = new File("C://Users/Nando/Desktop/Restrictions/makelist.bat");
		Runtime rt = Runtime.getRuntime();
		
		try
		{
			rt.exec("cmd /c start C://Users/Nando/Desktop/Restrictions/makelist.bat");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public class Program
	{
		private GregorianCalendar cal;
		private long lastChecked;
		private Prog prog;
		private String name;
		private long currentTime;
		private long allowedTime;
		private boolean allowed;
		
		public Program(Prog program)
		{
			prog = program;
			name = prog.toString();
			cal = new GregorianCalendar();
			lastChecked = cal.getTimeInMillis();
			allowedTime = prog.getAllowedTime();
			allowed = true;
		}
		
		public void inUse()
		{
			long temp = cal.getTimeInMillis();
			currentTime = temp - lastChecked;
			lastChecked = temp;
		}
		
		public boolean isAllowed()
		{
			return allowed;
		}
		
		public boolean check()
		{
			if(currentTime >= allowedTime)
			{
				return (allowed = false);
			}
			return true;
		}
	}
	
	public static enum Prog
	{	
		B (60*60*1000), FreeCell (20*60*1000);
		
		private long allowedTime;
		
		Prog(long t)
		{
			allowedTime = t;
		}
		
		public long getAllowedTime()
		{
			return allowedTime;
		}
	}
}
