package sets_and_maps;

import java.awt.Color;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ColorFinder
{
	private Set<Color> set;

	public ColorFinder()
	{
		set = new HashSet<Color>();
		set.add(Color.black);
		set.add(Color.blue);
		set.add(Color.green);
		set.add(Color.yellow);
		set.add(Color.red);
		set.add(Color.white);
		set.add(Color.magenta);
		set.add(Color.pink);
		set.add(Color.cyan);
		set.add(Color.darkGray);
		set.add(Color.gray);
		set.add(Color.lightGray);
		set.add(Color.orange);
	}

	public boolean contains(Color test)
	{
		return set.contains(test);
	}

	public static void main(String[] args)
	{
		System.out.println("java.awt.Color Checker");
		System.out.println("enter -1 in any field to quit");
		System.out.println("enter only decimals");
		Scanner in = new Scanner(System.in);
		ColorFinder list = new ColorFinder();
		while (true)
		{
			try
			{
				System.out.print("enter red value: ");
				float one = Float.parseFloat(in.nextLine());
				if (one == -1) break;
				System.out.print("enter green value: ");
				float two = Float.parseFloat(in.nextLine());
				if (two == -1) break;
				System.out.print("enter blue value: ");
				float thr = Float.parseFloat(in.nextLine());
				if (thr == -1) break;

				String ans = list.contains(new Color(one, two, thr)) ? "does" : "does not";

				System.out.println("the class java.awt.Color " + ans + " contain the color");

			}
			catch (Exception e)
			{
				System.out.println("Bad input");
			}
		}
		System.out.println("Bye");
	}
}