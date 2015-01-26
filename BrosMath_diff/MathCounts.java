package BrosMath_diff;



import java.util.GregorianCalendar;

import javax.swing.JOptionPane;



public class MathCounts

{



	private static JOptionPane j;	

	/**

	 * @param args

	 */

	public static void main(String[] args)

	{

		

		int qus = qs();

		

		

		int q1s = 0, q2s = 0, ans = 0,userans = 0;

		boolean gotit = false;

		String finalans = "Input              right answer";

		double rightnum = 0;

		

		GregorianCalendar start = new GregorianCalendar();

		for(int x = 0; x!=qus; x++)

		{

			ans = (int) Math.round(Math.random()*18);

			q1s = getnum(ans);

			

			q2s = plus(q1s,ans);

			userans = askPlus(q1s,q2s);

			

			gotit = userans == ans?true:false;

			rightnum += gotit == true?1:0;

			

			if(gotit == false)

				finalans += "\n"+q1s+" + "+q2s+" = "+userans+"            "+ans;

		} 

		

		JOptionPane.showMessageDialog(j, "You are finished!!!!");

		GregorianCalendar end = new GregorianCalendar();

		

		String mins = String.valueOf(((int)(end.getTimeInMillis() - start.getTimeInMillis())/1000)/60);

		String secs = String.valueOf(((int)(end.getTimeInMillis() - start.getTimeInMillis())/1000)- (Integer.parseInt(mins)*60));

		

		String totaltime = mins + " minutes, "+ secs+ " seconds";

		

		

		finalans += "\n\nTotal time spent: "+totaltime+".";

		

		finalans += "\n\nPercentage: "+(int) ((rightnum/qus)*100)+"%";

		

		JOptionPane.showMessageDialog(j, finalans);

		

	}

	public static int plus(int q1s, int ans)

	{

		return ans - q1s;

	}

	

	public static int askPlus(int q1, int q2)

	{

		

		String ans = JOptionPane.showInputDialog(""+q1+" + "+q2+" = ?");

		int userans;

		try

		{

			userans = Integer.parseInt(ans);

		}

		catch(Exception q)

		{

			userans = askPlus(q1,q2);

		}

		return userans;

	}

	

	public static int qs()

	{

		String qnum = JOptionPane.showInputDialog("How many questions do you want?");

		int qs = 100;

		try

		{

			qs = Integer.parseInt(qnum);

		}

		catch(Exception q)

		{

			JOptionPane.showMessageDialog(j, "Error!!! Wrong input value!!");

			qs();

		}

		return qs;

	}

	

	public static int getnum(int max)

	{

		int num;

		do

		{

			num = (int) Math.round(Math.random()*9);

		}while(num > max || max - num >= 9);

		return num;

	}



}