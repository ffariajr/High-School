package roach;


public class roachTester
{

	static roach bug = new roach(10);
	
	public static void main(String[] args)
	{
		
		System.out.println(cycle());
		System.out.println(cycle());
		System.out.println(cycle());

	}
	
	
	
	
	public static int cycle()
	{
		bug.grow();
		bug.insecticide();
		return bug.getSize();
	}

}
