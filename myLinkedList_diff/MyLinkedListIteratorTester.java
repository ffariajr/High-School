package myLinkedList_diff;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedListIteratorTester
{
	public static void main(String[] args)
	{
		MyLinkedListIterator list = new MyLinkedListIterator<String>();
		list.add("my");
		list.add("name");
		list.add("is");
		list.add("Fernando");
		
		ListIterator itr = list.listIterator();
	}
}
