package product;


public class productTester
{

	/**
	 * 
	 * this is a tester for the program product
	 * it makes two objects: game1 and game2
	 */
	public static void main(String[] args)
	{
		product game1 = new product("cod4", 59.99);
		product game2 = new product("halo3", 69.95);
		
		System.out.println(""+game1.getName()+"\n"+game1.getPrice()+"\n");
		System.out.println(""+game2.getName()+"\n"+game2.getPrice()+"\n");
		
		game1.setPrice(-5);
		game2.setPrice(-5);
		
		System.out.println(""+game1.getName()+"\n"+game1.getPrice()+"\n");
		System.out.println(""+game2.getName()+"\n"+game2.getPrice()+"\n");
		

	}

}
