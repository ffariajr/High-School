package stringToNumbers;

public class stringToDouble
{
	public static double toDouble(String x)
	{
		double z = 1;
		x.trim();
		try
		{
			z = Double.parseDouble(x);
		}
		catch (Exception q)
		{
			char[] nums = new char[x.length()];
			int w = 0;
			for (int y = 0; y <= x.length(); y++)
			{
				w++;
				switch (x.charAt(w))
				{
					case '1':
						nums[y] = '1';
						break;
					case '2':
						nums[y] = '2';
						break;
					case '3':
						nums[y] = '3';
						break;
					case '4':
						nums[y] = '4';
						break;
					case '5':
						nums[y] = '5';
						break;
					case '6':
						nums[y] = '6';
						break;
					case '7':
						nums[y] = '7';
						break;
					case '8':
						nums[y] = '8';
						break;
					case '9':
						nums[y] = '9';
						break;
					case '0':
						nums[y] = '0';
						break;
					case '.':
						nums[y] = '.';
						break;
					default:
						y--;

				}
			}
			
			String trans = new String(nums);
			trans.trim();
			
			if(trans.isEmpty() == true)
				z=1;
		}

		return z;
	}

}
