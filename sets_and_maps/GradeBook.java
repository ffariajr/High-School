package sets_and_maps;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class GradeBook
{
	private Map<String, String> book;

	public GradeBook()
	{
		book = new TreeMap<String, String>();
	}

	public String toString()
	{
		String result = "";
		for (String key : book.keySet())
		{
			result += key.substring(0, 1).toUpperCase() + key.substring(1).toLowerCase() + ": " + book.get(key) + "\n";
		}
		return result;
	}

	public boolean add(String name, String grade)
	{
		grade = grade.toUpperCase();
		name = name.toUpperCase();
		Set<String> keys = book.keySet();
		boolean ans = keys.contains(name);
		book.put(name, grade);
		return !ans;
	}

	public boolean remove(String name)
	{
		name = name.toUpperCase();
		Set<String> keys = book.keySet();
		boolean ans = keys.contains(name);
		System.out.println(keys.contains(name));
		book.remove(name);
		return ans;
	}

	public boolean modify(String name, String newGrade)
	{
		name = name.toUpperCase();
		newGrade = newGrade.toUpperCase();
		Set<String> keys = book.keySet();
		if (!keys.contains(name)) return false;
		book.put(name, newGrade);
		return true;
	}

	private static void menu()
	{
		System.out.println("**********************************");
		System.out.println("1) add a student");
		System.out.println("2) modify a students grade");
		System.out.println("3) remove a student");
		System.out.println("4) print all students and grade");
		System.out.println("5) quit");
		System.out.println("**********************************");
		System.out.print("Select an option: ");

	}

	public static void main(String[] args)
	{
		GradeBook book = new GradeBook();
		System.out.println("Welcome to the new Infinite Campus");
		Scanner in = new Scanner(System.in);
		while (true)
		{
			menu();
			int ans;
			try
			{
				ans = Integer.parseInt(in.nextLine());
			}
			catch (Exception e)
			{
				ans = 5;
			}
			switch (ans)
			{
				case 1:
					System.out.print("Enter name: ");
					String name = in.nextLine();
					System.out.print("Enter grade: ");
					String grade = in.nextLine();
					boolean done1 = book.add(name, grade);
					System.out.println((done1 ? "Added successfully" : "Duplicate student"));
					break;
				case 2:
					System.out.print("Enter name: ");
					String name2 = in.nextLine();
					System.out.print("Enter new grade: ");
					String grade2 = in.nextLine();
					boolean done2 = book.modify(name2, grade2);
					System.out.println((done2 ? "Modified successfully" : "Student not found"));
					break;
				case 3:
					System.out.print("Enter name: ");
					String name3 = in.nextLine();
					boolean done3 = book.remove(name3);
					System.out.println((done3 ? "Removed successfully" : "Student not found"));
					break;
				case 4:
					System.out.println(book.toString());
					break;
				default:
					System.out.println("Goodbye");
					System.exit(0);
			}
		}
	}
}
