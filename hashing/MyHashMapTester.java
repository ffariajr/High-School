package hashing;

import java.util.*;

public class MyHashMapTester
{
	public static void main(String[] args)
	{
		MyHashMap<Integer, Integer> theMap = new MyHashMap<Integer, Integer>();
		Random rand = new Random();

		System.out.println("Added to map:");
		for (int i = 0; i <= 25; i++)
		{
			int key = rand.nextInt(100);
			int value = rand.nextInt(100);

			System.out.println(key + " " + value);
			theMap.put(key, value);
		}

		System.out.println();
		System.out.println("Retrieved from map:");

		Set<Integer> theKeys = theMap.keySet();
		Iterator<Integer> theKeysItr = theKeys.iterator();
		while (theKeysItr.hasNext())
		{
			int key = theKeysItr.next();
			int value = theMap.get(key);
			System.out.println(key + " " + value);
		}
	}
}
