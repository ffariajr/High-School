package werner_math_class__2_17;



public class Code_static_line
{

	public static void main(String[] args)
	{
		
		
		
		
		double[] message = {3,81,1,6.240251469,1051.399937,.6666666666};
		
		char[] letters = new char[message.length];
		
		for(int x = 0; x< message.length; x++)
		{
			double m = message[x];
			double a = Math.log10(m);
			double b = 3*a;
			double c = Math.log10(3);
			double d = b/c;
			double e = d-9;
			int z = (int) e;
			letters[x]=(char) ((char) (z+65));
			System.out.print(" "+(z));
		}
		for(int x = 0; x< message.length; x++)
		{
			System.out.print(letters[x]);
		}
	}

}