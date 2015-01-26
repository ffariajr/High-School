package multithreading_diff;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Queue
{
	public static void main(String[]args)
	{
		Queue q = new Queue(10, 50);
		Input i = new Input(q);
		Output o = new Output(q);
		
		Thread t1 = new Thread(i);
		Thread t2 = new Thread(o);
		t1.start();
		t2.start();
	}
	
	private String[] arr;
	private int start;
	private int end;
	private int size;
	private Lock elementLock;
	private Condition emptyQueue;
	private Condition full;
	private Condition tooHeavy;
	private int weight;
	private int maxWeight;
	
	public Queue(int size, int maxWeight)
	{
		arr=new String[size];
		start=0;
		end=0;
		this.size=0;
		elementLock = new ReentrantLock();
		emptyQueue = elementLock.newCondition();
		full = elementLock.newCondition();
		tooHeavy = elementLock.newCondition();
		weight = 0;
		this.maxWeight = maxWeight;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public int getWeight()
	{
		return weight;
	}
	
	public void offer(String x)
	{
		elementLock.lock();
		try
		{
			while(size == arr.length)
				full.await();
			while((weight + Integer.parseInt(x)) > maxWeight)
				tooHeavy.await();
			arr[end] = x;
			end++;
			if(end == arr.length)
				end=0;
			size++;
			weight+=Integer.parseInt(x);
			emptyQueue.signalAll();
		}
		catch (InterruptedException e)
		{
		}
		finally
		{
			elementLock.unlock();
		}
	}
	
	public String pull()
	{
		String result = null;
		elementLock.lock();
		try
		{
			while(size == 0)
				emptyQueue.await();
			result = arr[start];
			arr[start] = null;
			start++;
			if(start == arr.length)
				start = 0;
			size--;
			if(result != null)
				weight-=Integer.parseInt(result);
			full.signalAll();
			tooHeavy.signalAll();
		}
		catch(InterruptedException e)
		{
		}
		finally
		{
			elementLock.unlock();
			return result;
		}
	}
	
	//System.out.println("z");
	
	public void print()
	{
		for(String x:arr)
			System.out.print(x+"  ");
		System.out.println();
	}
}
