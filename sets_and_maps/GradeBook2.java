package sets_and_maps;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class GradeBook2
{
	private int id;
	private Map<Student, String> book;

	public GradeBook2()
	{
		book = new TreeMap<Student, String>();
		id = 0;
	}

	public int nextValidId()
	{
		return id++;
	}

	public String toString()
	{
		String result = "";
		for (Student key : book.keySet())
		{
			result += key.toString() + ": " + book.get(key) + "\n";
		}
		return result;
	}

	public boolean add(Student name, String grade)
	{
		grade = grade.toUpperCase();
		Set<Student> keys = book.keySet();
		boolean ans = keys.contains(name);
		book.put(name, grade);
		return !ans;
	}

	public boolean remove(Student name)
	{
		Set<Student> keys = book.keySet();
		boolean ans = keys.contains(name);
		book.remove(name);
		return ans;
	}

	public boolean modify(Student name, String newGrade)
	{
		newGrade = newGrade.toUpperCase();
		Set<Student> keys = book.keySet();
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
		GradeBook2 book = new GradeBook2();
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
					System.out.print("Enter first name: ");
					String firname1 = in.nextLine();
					System.out.print("Enter last name: ");
					String lasname1 = in.nextLine();
					System.out.print("Enter grade: ");
					String grade = in.nextLine();
					boolean done1 = book.add(new Student(firname1, lasname1, book.nextValidId()), grade);
					System.out.println((done1 ? "Added successfully" : "Duplicate student"));
					break;
				case 2:
					System.out.print("Enter first name: ");
					String firname2 = in.nextLine();
					System.out.print("Enter last name: ");
					String lasname2 = in.nextLine();
					System.out.print("Enter id number: ");
					String id1 = in.nextLine();
					System.out.print("Enter new grade: ");
					String grade2 = in.nextLine();
					Student kid1 = new Student(firname2, lasname2, Integer.parseInt(id1));
					boolean done2 = book.modify(kid1, grade2);
					System.out.println((done2 ? "Modified successfully" : "Student not found"));
					break;
				case 3:
					System.out.print("Enter first name: ");
					String firname3 = in.nextLine();
					System.out.print("Enter last name: ");
					String lasname3 = in.nextLine();
					System.out.print("Enter id number: ");
					String id2 = in.nextLine();
					Student kid2 = new Student(firname3, lasname3, Integer.parseInt(id2));
					boolean done3 = book.remove(kid2);
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
