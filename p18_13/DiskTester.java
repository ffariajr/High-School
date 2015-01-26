package p18_13;

public class DiskTester
{

	public static void main(String[] args)
	{
		DiskMover test = new DiskMover(2,1,3);
		while(test.hasNextMove())
			System.out.println(test.nextMove());

	}

}
