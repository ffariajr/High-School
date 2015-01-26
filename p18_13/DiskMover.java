package p18_13;

public class DiskMover
{
	private int disks, from, to, position;
	private DiskMover tail;

	public DiskMover(int numDisks, int source, int destination)
	{
		if (numDisks <= 0) 
			throw new IllegalArgumentException("No disks?");
		if (source != 1 && source != 2 && source != 3) 
			throw new IllegalArgumentException("Wrong source specified!");
		if (destination != 1 && destination != 2 && destination != 3) 
			throw new IllegalArgumentException("Wrong destination specified!");
		disks = numDisks;
		from = source;
		to = destination;
		position = 1;
		tail = null;
	}

	public boolean hasNextMove()
	{
		return position != 4;

	}

	public String nextMove()
	{
		if (!hasNextMove()) 
			throw new IllegalArgumentException("No more moves!");
		int other;
		if (to + from == 5)
			other = 1;
		else if (to + from == 4)
			other = 2;
		else 
			other = 3;

		if (disks == 1) 
		{
			position = 4;
			return print(from, to);
		}
			

		if(tail == null)
			position++;

		switch (position)
		{
			case 1:
				tail = new DiskMover(disks - 1, from, other);
				return tail.nextMove();
			case 2:
				return print(from, to);
			case 3:
				tail = new DiskMover(disks-1, other, to);
				return tail.nextMove();
		}
		
		return "No more moves left";

	}

	private String print(int from, int to)
	{
		return "Move a disk from peg " + from + " to peg " + to + ".";
	}
}
