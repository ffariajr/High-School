package Fun_Stuff;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

public class PicturesFolderOrganizer
{

	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		String homedir = "C://Documents and Settings/Nando/Pictures/4chan/";
		
		String destdir = "C://Documents and Settings/Nando/Pictures/gif/";
		
		String extension = "gif";
		
		
		
		File f = new File(homedir);

		File[] fs = f.listFiles();

		File newdir = new File(destdir);
		newdir.mkdir();

		for (File z : fs)
		{
			System.out.println(z);
			String temp = z.toString();
			int len = temp.length();
			String pic = "";

			if (extension.equals(temp.substring(len - 3)))
			{
				for (int q = len - 5; q >= 0; q--)
					if (temp.charAt(q) == '\\') 
					{
						pic = temp.substring(q + 1);
						break;
					}
					

				File newgif = new File(destdir + pic);
				newgif.createNewFile();
				newgif.setWritable(true);

				FileImageInputStream in = new FileImageInputStream(z);

				FileImageOutputStream out = new FileImageOutputStream(newgif);
								
				for (long q = 0; q < in.length(); q++)
				{
					int bytes = in.read();
					out.write(bytes);
				}

				in.close();
				out.close();
				
				z.deleteOnExit();
			}

		}
	}
}