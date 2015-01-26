package P8_10;

import java.util.Scanner;

public class AltSum
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		
		System.out.println("How many numbers do you want to input?");
		
		int numOfnum = input.nextInt();
		
		int[] nums;
		
		try
		{
			nums = new int[numOfnum];
		}
		catch (Exception q)
		{
			System.out.println("such stupidity...\nEnter ten(10) numbers:");
			nums = new int[10];
			numOfnum = 10;
		}
		int sum = 0;
		System.out.println("Enter "+numOfnum+" numbers");
		for(int x = 0; x<numOfnum; x++)
		{
			nums[x] = input.nextInt();
			sum += x%2 == 0?nums[x]:-nums[x];
		}
		
		System.out.println(sum);
		
		

	}
}

