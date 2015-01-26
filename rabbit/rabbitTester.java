package rabbit;


public class rabbitTester
{

	public static void main(String[] args)
	{
		rabbit bunny = new rabbit(2);			// i started the population at 2
		
		for(int x = 0; x!=10; x++)			//does the growth thing 10 times, one cycle is one month
		{
			
			System.out.println("size of rabbit population after "+x+" months " + bunny.getSize());
			bunny.waitMonth();
		}
		
		System.out.println("size of rabbit population after 10 months: "+bunny.getSize());

	}

}
