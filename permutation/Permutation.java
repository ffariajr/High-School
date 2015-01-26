package permutation;

import java.util.ArrayList;

public class Permutation
{
	public Permutation()
	{
	}

	@SuppressWarnings("unchecked")
	public int[] permutationGenerator()
	{
		int[] ans = new int[10];
		ArrayList<String> array = new ArrayList();
		for(int x = 0;x <= 9; x++)
			array.add(x, Integer.toString(x+1));
		int r;
		
		for(int x = 0; x <= 9; x++)
		{
			r = (int) (Math.random() * (10 - x));
			ans[x] = Integer.parseInt(array.get(r));
			array.remove(r);
		}

		return ans;
	}

}
