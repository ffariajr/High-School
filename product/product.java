package product;

public class product
{
	private String name;
	private double price = 0;
	
	
	/**
	 * 
	 * @param userName is the name of the product the user made
	 * @param userPrice is the price of the product
	 */
	public product(String userName, double userPrice)
	{
		name = userName;
		price = userPrice;
	}
	
	/**
	 * 
	 * @return the name of the product
	 */
			
	public String getName()
	{
		return name;
	}
	
	/**
	 * 
	 * @return the price
	 */
	public double getPrice()
	{
		return price;
	}
	
	/**
	 * 
	 * @param modifier is a positive or negative number to change the current price
	 */
	public void setPrice(double modifier)
	{
		price += modifier;
	}
	

	
}
