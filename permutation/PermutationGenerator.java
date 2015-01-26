package permutation;

public class PermutationGenerator
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Permutation a = new Permutation();
		
		int[] ans = a.permutationGenerator();
		
		for(int x = 0; x <= 9; x++)
		{
			System.out.print(" " + ans[x]);
		}

	}

}
