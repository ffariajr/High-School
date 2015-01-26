package trees_diff;

import java.util.ArrayList;
import java.util.Iterator;


public class CalcTester
{
	public static void main(String[] args)
	{
		// Yes, they're parallel ArrayLists. Get
		// over it.
		ArrayList<String> testStrings = new ArrayList<String>();
		ArrayList<Double> expectedResults = new ArrayList<Double>(); // null
															// means
															// exception

		testStrings.add("5 * ( 5 )");
		expectedResults.add(25.0);

		// Set 1: simple order of operations
		testStrings.add("5 + 2 * 3");
		expectedResults.add(11.0);

		testStrings.add("5 + 4 * 3");
		expectedResults.add(17.0);

		testStrings.add("-1.2 / -1.2 + 4");
		expectedResults.add(5.0);

		testStrings.add("2 ^ 3");
		expectedResults.add(8.0);

		testStrings.add("2 ^ 3.1");
		expectedResults.add(8.574187700290345);

		testStrings.add("22 / 7");
		expectedResults.add(3.142857142857143);

		// Set 2: divide by 0
		testStrings.add("5 / 0");
		expectedResults.add(Double.POSITIVE_INFINITY);

		// Set 3: invalid input
		testStrings.add("5/0");
		expectedResults.add(null);

		testStrings.add("22 / ");
		expectedResults.add(null);

		// Set 4: parentheses
		testStrings.add("( 5 + 4 ) * 3");
		expectedResults.add(27.0);

		testStrings.add("2 + 6 * ( 4 - 2 )");
		expectedResults.add(14.0);

		testStrings.add("( 36 - 6 ) / ( 12 + 3 )");
		expectedResults.add(2.0);

		testStrings.add("2 * ( 13 - ( 1 + 6 ) )");
		expectedResults.add(12.0);

		testStrings.add("15 / ( ( ( 4 + 1 ) * 3 ) - 15 )");
		expectedResults.add(Double.POSITIVE_INFINITY);

		System.out.println("Start of Tests");
		Iterator<Double> itr = expectedResults.iterator();
		TreeCalc expr = new TreeCalc();
		for (String test : testStrings)
		{
			Double desiredResult = itr.next();
			Double actualResult = 0.0;

			try
			{
				actualResult = expr.evaluate(test);
				expr.reset();
			}
			catch (IllegalArgumentException e)
			{
				actualResult = null;
			}

			if ((desiredResult == null && actualResult != null) || (desiredResult != null && !desiredResult.equals(actualResult)))
			{
				System.out.println("Failed test: " + test);
				System.out.println("Expt result: " + desiredResult);
				System.out.println("Actu result: " + actualResult);
				System.out.println("");
			}
			else
			{
				System.out.println("Passed test: " + test);
			}
		}
		System.out.println("End of Tests");
	}
}
