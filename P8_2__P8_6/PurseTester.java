package P8_2__P8_6;

public class PurseTester
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Purse jons = new Purse();
		Purse andys = new Purse();
		
		jons.addCoin("Quarter");
		jons.addCoin("Dime");
		jons.addCoin("Nickel");
		jons.addCoin("Dime");
		
		andys.addCoin("Dime");
		andys.addCoin("Nickel");
		
		System.out.println(jons.toString()+"\n"+andys.toString());
		System.out.println("\n"+jons.reverse()+"\n"+andys.reverse());
		
		jons.transfer(andys);
		
		System.out.println("\n"+jons.toString()+"\n"+andys.toString());
		System.out.println("\n"+jons.reverse()+"\n"+andys.reverse());
		
		Purse ashwins = new Purse();
		Purse erics = new Purse();
		
		ashwins.addCoin("Quarter");
		ashwins.addCoin("Dime");
		ashwins.addCoin("Nickel");
		ashwins.addCoin("Dime");
		
		erics.addCoin("Quarter");
		erics.addCoin("Dime");
		erics.addCoin("Nickel");
		erics.addCoin("Dime");
		
		System.out.println("\n"+ashwins.sameContents(erics));
		System.out.println(ashwins.sameCoins(erics));
		
		ashwins.addCoin("Nickel");
		ashwins.addCoin("Dime");
		
		System.out.println("\n"+ashwins.sameContents(erics));
		System.out.println(ashwins.sameCoins(erics));
		
		erics.addCoin("Dime");
		erics.addCoin("Nickel");
		
		System.out.println("\n"+ashwins.sameContents(erics));
		System.out.println(ashwins.sameCoins(erics));
	}

}
