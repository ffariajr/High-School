package hashing;

import java.util.Iterator;

public class BankAccountSet
{
	public static void main(String[] args)
	{
		HashSet names = new HashSet(7); // 13
		// is
		// a
		// prime

		names.add(new BankAccount(1,12));
		names.add(new BankAccount(2,23));
		names.add(new BankAccount(3,34));
		names.add(new BankAccount(4,45));
		names.add(new BankAccount(5,56));
		names.add(new BankAccount(6,67));
		names.add(new BankAccount(7,78));
		names.add(new BankAccount(8,89));
		names.add(new BankAccount(9,90));
		names.add(new BankAccount(10,0.1));
		

		Iterator iter = names.iterator();
		while (iter.hasNext())
			System.out.println(iter.next().hashCode());
	}

}
