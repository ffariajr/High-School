package multithreading_diff;

import java.util.Date;

public class Input implements Runnable
{
	private Queue q;
	
	public Input(Queue q)
	{
		this.q=q;
	}

	public void run()
	{
		for(int x = 0;x<1000;x++)
		{
			int num = (int)(Math.random()*10)+1;
			q.offer(""+num);
			System.out.println(q.getSize()+"\t\t"+(q.getWeight()<=50));
		}
			
	}
}
