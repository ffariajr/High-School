package polynomials_diff;

public class PolynomialTester
{
	public static void main(String[] args)
	{
		test1();
		//test2();
	}

	public static void test1()
	{
		Polynomial one = new Polynomial(new double[] { 9, 0, -3, 0, 5 });
		Polynomial two = new Polynomial(new double[] { -8, 0, -2, 0, 0, 6 });
		System.out.println(one);
		System.out.println(two);
		System.out.println(one.add(two));
		System.out.println(one.multiply(two));
		System.out.println(one.derivative());

		/*
		 * 5.0x^4 - 3.0x^2 + 9.0 
		 * 6.0x^5 - 2.0x^2 - 8.0 
		 * 6.0x^5 + 5.0x^4 - 5.0x^2 + 1.0
		 * 30.0x^9 - 18.0x^7 - 10.0x^6 + 54.0x^5 - 34.0x^4 + 6.0x^2 - 72.0 
		 * 20.0x^3 - 6.0x
		 */
	}

	public static void test2()
	{
		Polynomial one = new Polynomial(new double[] { 9, 0, -3, 0, 0, 5 });
		Polynomial two = new Polynomial(new double[] { -8, 0, -2, 0, 0, -6 });
		System.out.println(one);
		System.out.println(two);
		System.out.println(one.add(two));
		System.out.println(one.multiply(two));
		System.out.println(one.derivative());
	}

}
