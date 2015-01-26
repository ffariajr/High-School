package hashing;

import java.util.Iterator;

public class StudentSet
{
	public static void main(String[] args)
	{
		HashSet names = new HashSet(13); // 13
		// is
		// a
		// prime

		names.add(new Student("Fernando","Faria",0));
		names.add(new Student("Felipe","Faria",1));
		names.add(new Student("Mr","Horn",2));
		names.add(new Student("Brandon","Horn",3));
		names.add(new Student("Tim","Dicks",4));
		names.add(new Student("Brian","Hiscock",5));
		names.add(new Student("Tom","Dickbutt",6));
		names.add(new Student("Armaan","Ambati",7));
		names.add(new Student("Andy","Slepman",8));
		names.add(new Student("Beth","Horn",9));
		names.add(new Student("cat1","Horn",10));
		names.add(new Student("cat2","Horn",11));
		names.add(new Student("Fernando","Faria",0));

		Iterator iter = names.iterator();
		while (iter.hasNext())
			System.out.println(iter.next());
		
		names.debug();
	}
}
