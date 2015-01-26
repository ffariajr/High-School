package myLinkedList_diff;

public class MyLinkedListTester
{
	public static void main(String[] args)
	{
		MyLinkedList<String> test = new MyLinkedList<String>();
		test.add("Horn");
		System.out.println(test.toString());
		System.out.println(test.isEmpty());
		test.clear();
		System.out.println(test.toString());
		System.out.println(test.isEmpty());
		test.add("name");
		test.addFirst("my");
		test.addLast("fernando");
		test.add(2, "is");
		System.out.println(test.toString());
		System.out.println(test.getFirst().toString());
		System.out.println(test.getLast().toString());
		System.out.println(test.get(1).toString());
		System.out.println(test.size());
		test.set(3, "Earl");
		test.addFirst("Hello");
		System.out.println(test.toString());
		test.removeFirst();
		test.removeLast();
		System.out.println(test.toString());
		test.remove();
		System.out.println(test.toString());
		test.remove(1);
		System.out.println(test.toString());
		test.remove();
		System.out.println(test.toString());
		
		
	}
}
