package multithreading_diff;

import java.util.Date;

public class Output implements Runnable
{
	private Queue q;
	
	public Output(Queue q)
	{
		this.q=q;
	}

	public void run()
	{
		for(int x = 0;x<1000;x++)
		{
			q.pull();
			System.out.println(q.getSize()+"\t\t"+(q.getWeight()<=50));
		}
	}
}
