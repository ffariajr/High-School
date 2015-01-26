package werner_math_class__2_17;

import java.util.Scanner;

public class asciigetter_math
{


	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		
		System.out.println("input");
		
		char[] message = input.next().toUpperCase().toCharArray();
		
		for(int x = 0; x < message.length; x++)
		{
			System.out.print(" "+(int)message[x]);
		}
	}

}
