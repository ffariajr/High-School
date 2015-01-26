package logins;

import java.io.*;

public class FileMaker
{
	private boolean hasPass, hasInfo;
	private int tries = 0;
	private FileWriter file;
	
	public FileMaker(String filename) throws IOException
	{
		file = new FileWriter("C:/TxtFiles/"+filename+".txt");
		FileWriter nameadd = new FileWriter("C:/TxtFiles/filenames.txt", true);
		CharSequence letters = filename;
		nameadd.append("\n"+letters);
		
	}
	
	public static String[] getFileNames() throws IOException
	{
		FileReader tempfile = new FileReader("C:/TxtFiles/filenames.txt");
		char[] letters = null;
		tempfile.read(letters);
		String[] filenames = null;
		String letter = new String(letters);
		
		int place = letter.indexOf(" ");
		
		for(int x = 0;x <= letter.length();x++)
		{
			
		}
		return filenames;
		
		
	}
}
