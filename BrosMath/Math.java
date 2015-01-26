package BrosMath;

import java.util.GregorianCalendar;
import java.util.Random;

import javax.swing.JOptionPane;

public class Math
{
	private static int askDiff()
	{
		JOptionPane j = new JOptionPane();
		String[] ops2 = { "Easy", "Medium", "Hard" };
		Object ans2 = j.showInputDialog(j, "Pick one", "Math Time", JOptionPane.QUESTION_MESSAGE, null, ops2, "Easy");
		if (ans2 == null)
		{
			j.showMessageDialog(j, "Goodbye");
			System.exit(0);
		}

		switch (((String) ans2).charAt(0))
		{
			case 'M':
				return 1;
			case 'H':
				return 2;
			default:
				return 0;
		}
	}

	private static int askTotal()
	{
		JOptionPane j = new JOptionPane();
		String[] ops = { "10", "20", "25", "30", "40", "50", "60", "70", "75", "80", "90", "100", "120", "125", "150", "175", "200", "225", "250", "300", };
		Object ans = j.showInputDialog(j, "How many problems", "Math", JOptionPane.QUESTION_MESSAGE, null, ops, "50");
		if (ans == null)
		{
			j.showMessageDialog(j, "Goodbye");
			System.exit(0);
		}
		return Integer.parseInt((String) ans);
	}

	private static boolean askTimed()
	{
		JOptionPane j = new JOptionPane();
		int ans = j.showConfirmDialog(j, "Do you want to be timed?");
		if (ans == JOptionPane.CANCEL_OPTION)
		{
			j.showMessageDialog(j, "Goodbye");
			System.exit(0);
		}
		return ans == JOptionPane.OK_OPTION;
	}

	private static int askHowLong()
	{
		JOptionPane j = new JOptionPane();
		String ans = j.showInputDialog(j, "How long (in seconds)?");
		if (ans == null)
		{
			j.showMessageDialog(j, "Goodbye");
			System.exit(0);
		}
		return Integer.parseInt(ans);
	}

	public static void main(String[] args)
	{
		JOptionPane j = new JOptionPane();
		String[] ops = {"Addition","Subtraction","Multiplication","Division","Quiz"};
		Object ans = j.showInputDialog(j, "Pick one", "Math Time", JOptionPane.QUESTION_MESSAGE, null, ops, "Quiz");
		if(ans == null)
		{
			j.showMessageDialog(j, "Goodbye");
			System.exit(0);
		}
		int timed;
		int diff = askDiff();
		int nums = askTotal();
		boolean isTimed = askTimed();
		if(isTimed);
			timed = askHowLong();
		if(!isTimed)
			timed = -1;	
			
		
		switch(((String)ans).charAt(0))
		{
			case 'A':
				add(diff,nums,timed);
				break;
			case 'S':
				sub(diff,nums,timed);
				break;
			case 'M':
				mult(diff,nums,timed);
				break;
			case 'D':
				div(diff,nums,timed);
				break;
			default:
				quiz(diff,nums,timed);
				break;
		}
		System.exit(0);
	}

	private static void add(int diff, int qs, int timed)
	{
		JOptionPane j = new JOptionPane();
		int total;
		switch (diff)
		{
			case 1:
				total = 99;
				break;
			case 2:
				total = 999;
				break;
			default:
				total = 19;
				break;
		}

		int[] num = new int[qs];
		int[] ans = new int[qs];

		Random rnd = new Random();

		for (int x = 0; x < qs; x++)
		{
			int num1 = rnd.nextInt(total);
			int num2 = rnd.nextInt(total);

			ans[x] = num1 > num2 ? num1 : num2;
			num[x] = num1 > num2 ? num2 : num1;
		}

		int[] inp = new int[qs];

		GregorianCalendar start = new GregorianCalendar();

		for (int x = 0; x < qs; x++)
		{
			inp[x] = Integer.parseInt((String) j.showInputDialog("" + num[x] + " + " + (ans[x] - num[x]) + " = "));
			if(timed != -1)
			{
				GregorianCalendar end = new GregorianCalendar();
				String secs = String.valueOf(((int) (end.getTimeInMillis() - start.getTimeInMillis()) / 1000));
				if(Integer.parseInt(secs) >= timed)
				{
					break;
				}
			}
		}
		String totalTime = "";
		if (timed == -1)
		{
			GregorianCalendar end = new GregorianCalendar();
			String mins = String.valueOf(((int) (end.getTimeInMillis() - start.getTimeInMillis()) / 1000) / 60);
			String secs = String.valueOf(((int) (end.getTimeInMillis() - start.getTimeInMillis()) / 1000) - (Integer.parseInt(mins) * 60));
			totalTime = "" + mins + " minutes, " + secs + " seconds";
		}
		
		String results = "Input            Right Answer\n";
		
		for(int x = 0;x<qs;x++)
		{
			results+=""+inp[x]+"            "+ans[x]+"\n";
		}
		
		if(timed == -1)
		{
			results+="You took "+totalTime;
		}
		
		j.showMessageDialog(j, results, "Math", JOptionPane.OK_OPTION);
	}

	private static void sub(int diff, int qs, int timed)
	{

	}

	private static void mult(int diff, int qs, int timed)
	{

	}

	private static void div(int diff, int qs, int timed)
	{

	}

	private static void quiz(int diff, int qs, int timed)
	{

	}
}
