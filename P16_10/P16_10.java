package P16_10;

import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class P16_10
{
	public static void main(String[] args) throws FileNotFoundException, IOException
	{

		JFileChooser fileSelector = new JFileChooser();
		fileSelector.addChoosableFileFilter(new JavaFileFilter());
		fileSelector.showOpenDialog(null);
		RandomAccessFile fileIO = new RandomAccessFile(fileSelector.getSelectedFile(), "rw");
		int currentByte = 0;
		while (currentByte < fileIO.length())
		{
			fileIO.seek(currentByte);
			StringBuffer y = new StringBuffer(fileIO.readLine()).reverse();
			fileIO.seek(currentByte);
			fileIO.writeBytes(y.toString());
			currentByte = currentByte + y.toString().length() - 1 + 2;
		}
		fileIO.close();

	}
}

class JavaFileFilter extends FileFilter
{
	public boolean accept(File file)
	{
		if (file.isDirectory()) return true;
		return file.getName().toLowerCase().endsWith(".java");
	}

	public String getDescription()
	{
		return "Java File (*.java)";
	}
}
