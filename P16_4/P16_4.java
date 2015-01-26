package P16_4;

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public class P16_4
{
	public static void main(String[] args) throws FileNotFoundException
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("C:\\"));
		fileChooser.addChoosableFileFilter((new TextFileFilter()));
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		int option = JOptionPane.showOptionDialog(null, "Choose One", "Vigrenere Cipher", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]
		{ "Encrypt", "Decrypt" }, null);
		VigrenereCipher cipher = new VigrenereCipher("Hello Mr. Horn");

		JOptionPane.showMessageDialog(null, "Open Text File To read from");
		fileChooser.showOpenDialog(null);
		File open = fileChooser.getSelectedFile();

		JOptionPane.showMessageDialog(null, "Save Text File To write to");
		fileChooser.showSaveDialog(null);
		File save = fileChooser.getSelectedFile();

		if (option > 0)
			cipher.decrypt(open, save);
		else 
			cipher.encrypt(open, save);

		JOptionPane.showMessageDialog(null, "Done");
	}
}

class TextFileFilter extends FileFilter
{
	public boolean accept(File file)
	{
		if (file.isDirectory()) 
			return true;
		return file.getName().toLowerCase().endsWith(".txt");
	}

	public String getDescription()
	{
		return "Text File (*.txt)";
	}
}