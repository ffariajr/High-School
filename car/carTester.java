package car;


public class carTester
{
	public static void main(String[] args)
	{
		car myCar = new car(28, 16);
		
		myCar.addGas(20);
		System.out.println("" + myCar.getGas());
		boolean drove = myCar.drive(1000);
		System.out.println("" + myCar.getGas() +"\n"+ "did he drive the entire distance: " + drove);
		myCar.addGas(13);
		drove = myCar.drive(20);
		System.out.println("did he drive the entire distance: "+drove);
	}
}
