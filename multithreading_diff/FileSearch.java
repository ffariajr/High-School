package multithreading_diff;

import java.io.FileNotFoundException;

public class FileSearch
{
	public static void main(String[] args) throws FileNotFoundException
	{
		if(args[0] != "java" || args[1] != "Find")
			throw new IllegalArgumentException();
		
		for(int x = 3;x<args.length;x++)
		{
			Thread temp = new Thread(new Finder(args[x], args[2]));
			temp.start();
		}
	}
}
