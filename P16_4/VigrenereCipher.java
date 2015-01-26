package P16_4;

import java.io.*;
import java.util.*;

/**
 * decrypts and encrypts
 */
public class VigrenereCipher
{
	private String cipher;

	/**
	 * makes a new cipher with the keyword
	 * 
	 * @param cipher
	 */
	public VigrenereCipher(String cipher)
	{
		this.cipher = cipher.toUpperCase().trim();
	}

	/**
	 * encrypts the file
	 * 
	 * @param read
	 *             the file to read
	 * @param save
	 *             the file to save to
	 * @throws FileNotFoundException
	 */
	public void encrypt(File read, File save) throws FileNotFoundException
	{
		code(read, save, true);
	}

	/**
	 * decrypts the file
	 * 
	 * @param read
	 *             the file to read
	 * @param save
	 *             the file to save to
	 * @throws FileNotFoundException
	 */
	public void decrypt(File read, File save) throws FileNotFoundException
	{
		code(read, save, false);
	}

	/**
	 * does the actual ecryption
	 * 
	 * @param read
	 *             the file to read
	 * @param save
	 *             the file to save to
	 * @param encrypt
	 *             if true, encrypts, else
	 *             decrypts
	 * @throws FileNotFoundException
	 */
	private void code(File read, File save, boolean encrypt) throws FileNotFoundException
	{
		Scanner reader = new Scanner(read);
		PrintWriter writer = new PrintWriter(save);
		String current;
		int count = 0;
		while (reader.hasNextLine())
		{
			current = reader.nextLine();
			current = current.toUpperCase();
			for (int i = 0; i < current.length(); i++)
			{
				char temp = Char(current.charAt(i), cipher.charAt(count % cipher.length()), encrypt);
				writer.write(temp);
				if (temp != ' ') count++;
			}
			writer.println();
		}
		reader.close();
		writer.close();
	}

	/**
	 * returns the letter to encrypt or decrypt
	 * 
	 * @param letter
	 *             letter to decrypt/encrypt
	 * @param codeChar
	 *             keyword char
	 * @param encrypt
	 *             if true, encrypts, else
	 *             decrypts
	 * @return the encrypted or decrypted char
	 */
	private char Char(char letter, char codeChar, boolean encrypt)
	{
		if (letter > 'Z' || letter < 'A') 
			return ' ';

		if (encrypt)
		{
			if ((int) letter - (int) 'A' + (int) codeChar > (int) 'Z')
				return (char) ((int) codeChar - ((int) 'Z' - (int) letter) - 1);
			else 
				return (char) (((int) letter - (int) 'A') + (int) codeChar);
		}
		else 
			return Char(letter, Char('A', (char) (((int) 'Z' - (int) codeChar) + 1 + (int) 'A'), true), true);
	}
}
