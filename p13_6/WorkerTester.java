package p13_6;

public class WorkerTester
{
	private Worker w = new Worker("Mr. Smith", (Math.random()*5)+6.55);
	private HourlyWorker hw = new HourlyWorker("Mr. Rudnik", (Math.random()*5)+6.55);
	private SalariedWorker sw = new SalariedWorker("Mr. Horn", (Math.random()*5)+6.55);
	
	public static void main(String[] args)
	{
		WorkerTester a = new WorkerTester();
		a.test();
	}
	
	private void test()
	{
		print();
		
		w.work((int) (Math.random()*20)+30);
		hw.work((int) (Math.random()*20)+30);
		sw.work((int) (Math.random()*20)+30);
		
		print();
		
		w.newWeek();
		hw.newWeek();
		sw.newWeek();
		
		print();
	}
	private void print()
	{
		System.out.println(w.toString());
		System.out.println(w.computePay());
		System.out.println(hw.toString());
		System.out.println(hw.computePay());
		System.out.println(sw.toString());
		System.out.println(sw.computePay());
		System.out.println();
	}

}
