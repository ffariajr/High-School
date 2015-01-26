package hashing;

public class Student implements Comparable
{
	private int id;
	private String first;
	private String last;
	private static final int HASH_MULTIPLIER = 113;
	
	public Student(String first, String last, int id)
	{
		this.id = id;
		this.first = first.toLowerCase();
		this.last = last.toLowerCase();
	}

	public String last()
	{
		return last;
	}

	public String first()
	{
		return first;
	}

	public Student(String fullname, int id)
	{
		this(fullname.substring(fullname.indexOf(",") + 2), fullname.substring(0, fullname.indexOf(",")), id);
	}

	public String toString()
	{
		return name() + ", [" + id + "]";
	}

	public String name()
	{
		return last.substring(0, 1).toUpperCase() + last.substring(1) + ", " + first.substring(0, 1).toUpperCase() + first.substring(1);
	}

	public int id()
	{
		return id;
	}

	public int compareTo(Object another)
	{
		Student other = (Student) another;
		if (name().equals(other.name()))
		{
			if (id < other.id()) return -1;
			if (id > other.id())
				return 1;
			else return 0;
		}
		if (last.equals(other.last())) return first.compareTo(other.first());
		return last.compareTo(other.last());
	}
	
	public boolean equals(Student another)
	{
		return equals((Object)another);
	}
	
	public boolean equals(Object another)
	{
		if(!(another instanceof Student))
			return false;
		Student other = (Student)another;
		return ((first.equals(other.first))&&(last.equals(other.last))&&(id == other.id));
	}
	
	public int hashCode()
	{
		int num = HASH_MULTIPLIER;
		return  ((first.hashCode()*num)+(last.hashCode()*num))+(id*num);
		
	}
}