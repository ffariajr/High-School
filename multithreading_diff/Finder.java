package multithreading_diff;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Finder implements Runnable
{
	private Scanner s;
	private String word;
	
	public Finder(String filename, String word) throws FileNotFoundException
	{
		s = new Scanner(new File(filename));
		this.word=word;
	}
	
	public void run()
	{
		while(s.hasNext())
			System.out.println(nextocc());
	}
	
	private String nextocc()
	{	
		s.next();
		String ans = s.findInLine(word);
		return ans;
	}
}
