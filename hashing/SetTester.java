package hashing;

import java.util.Iterator;
import java.util.Set;

/**
 * This program tests the hash set class.
 */
public class SetTester
{
	public static void main(String[] args)
	{
		HashSet names = new HashSet(101); // 101
									// is
									// a
									// prime

		names.add("Sue");
		names.add("Harry");
		names.add("Nina");
		names.add("Susannah");
		names.add("Larry");
		names.add("Eve");
		names.add("Sarah");
		names.add("Adam");
		names.add("Tony");
		names.add("Katherine");
		names.add("Juliet");
		names.add("Romeo");
		names.remove("Romeo");
		names.remove("George");

		Iterator iter = names.iterator();
		while (iter.hasNext())
			System.out.println(iter.next());
		
		names.debug();
	}
}
