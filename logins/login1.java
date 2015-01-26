package logins;

import javax.swing.JOptionPane;
import java.io.*;

public class login1
{
	static JOptionPane input = new JOptionPane();
	static String username,password,userpass,userinput;
	static int tries = 0;
	public static void main(String[] args) throws IOException
	{
		
		int newuser = JOptionPane.showConfirmDialog(input, "Are you a first time user?");
		if (newuser == 0)
			newuser();
		else
			olduser();
	
	
	}
	public static void newuser() throws IOException
	{
		
		
		username = JOptionPane.showInputDialog("Your Username will be used as the name of your file. \nEnter Username:");
		
		
		password = JOptionPane.showInputDialog("Enter your password:");
				
		FileWriter filepass = new FileWriter("C:/TxtFiles/" + username+"password.txt");
		filepass.write(password);
		filepass.close();
		
		FileWriter file = new FileWriter("C:/TxtFiles/" + username+".txt");
		int go = JOptionPane.showConfirmDialog(input, "Do you want to write to the file?");
		
		if(go == 0)
			file.write(JOptionPane.showInputDialog("Write:"));
		else
		{
			JOptionPane.showMessageDialog(input, "File Closed.");
			main(null);
		}
		
		file.close();
	}
	
	public static void olduser() throws IOException
	{
		
		
		username = JOptionPane.showInputDialog("Enter Username:");
	
	
		userinput = JOptionPane.showInputDialog("Enter your password:");
		
		
		FileReader filepass = new FileReader("C:/TxtFiles/" + username+"password.txt");
		
		char[] chars = null;
		filepass.read(chars);
		filepass.close();
		userpass = new String(chars);

		checkpass(userinput);
		
	}

	
	public static void checkpass(String userinput) throws IOException
	{
		if(userinput.equals(userpass))
			correctpass();
		else
		{
			tries++;
			if(tries == 5)
				safety();
			checkpass(JOptionPane.showInputDialog(input, "Error! \n!!Wrong Passcode!! \nEnter your password:"));
		}
	}
	
	public static void correctpass()
	{
		String[] option2 = {"Write", "Append","Print","Delete"};
	}
	
	public static void safety() throws IOException
	{
		FileReader backuptemp = new FileReader("C:/TxtFiles/" + username+".txt");
		FileWriter backup = new FileWriter("C:/Safe/"+username+".txt");
		char[] chars = null;
		backuptemp.read(chars);
		String backuptempcontents = new String(chars);
		backup.write(backuptempcontents);
		backup.close();
		backuptemp.close();
		FileWriter deleter = new FileWriter("C:/TxtFiles/" + username+".txt");
		deleter.close();
		FileWriter passdeleter = new FileWriter("C:/TxtFiles/" + username+"password.txt");
		passdeleter.close();
	}
}
