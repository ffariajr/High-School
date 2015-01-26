package deviation;

public class DeviationRunner
{
	public static void main(String[] args) 
	{
		Deviation num = new Deviation();
		num.newNum(2);
		num.newNum(3);
		num.newNum(4);
		System.out.println("Number of Values -> " + num.getAmount());
		System.out.println("Algebraic Mean -> " + num.getMean());
		System.out.println("Standard Deviation = " + num.getDeviation());

	}
}
