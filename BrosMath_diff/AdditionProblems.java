package BrosMath_diff;

import java.util.ArrayList;

public class AdditionProblems
{
	private Two[] q1,q2;
	private ArrayList ask1,ask2;
	
	public AdditionProblems()
	{
		
		for(int x = 0; x <= 9; x++)
		{
			q1[x] = new Two(x,10);
			q2[x] = new Two(x,10);
		}
		makeNums();
	}
	
	private Two[] makeNums()
	{
		Two[] questions = new Two[100];
		for(int x = 0; x<100; x++)
		{
			questions[x].setX(getNum(true));
			questions[x].setY(getNum(false));
		}
		return questions;
			
	}
	
	private int getNum(boolean which)
	{
		boolean found = false;
		while(!found)
		{
			int x = (int) (Math.random()*10);
			if(which)
			{
				if(q1[x].getY()>0)
				{
					q1[x].setY(q1[x].getY()-1);
					return q1[x].getX();
				}
			}
			else
			{
				if(q2[x].getY()>0)
				{
					q2[x].setY(q2[x].getY()-1);
					return q2[x].getX();
				}
			}
		}
		return 0;
	}
	
	
}
