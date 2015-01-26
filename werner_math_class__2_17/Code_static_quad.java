package werner_math_class__2_17;

import java.util.Scanner;

public class Code_static_quad
{

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		
		System.out.println("Input message to encode");
		
		char[] message = input.next().toUpperCase().toCharArray();
		
		int acoef = input.nextInt();
		int bcoef = input.nextInt();
		int yint = input.nextInt();
		
		for(int x = 0; x< message.length; x++)
		{
			System.out.print(""+domath(message[x],acoef,bcoef, yint)+"  ");
		}
		System.out.println("\n"+domath(' ',acoef,bcoef,yint));
	}
	
	public static int domath(char letter, int acoef, int bcoef, int yint)
	{
		return ((int)letter)*((int)letter)*acoef+((int)letter)*bcoef+yint;
	}
}