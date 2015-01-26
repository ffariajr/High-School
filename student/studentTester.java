package student;


public class studentTester
{

	private static student me = new student("Fernando");
	
	public static void main(String[] args)
	{
		
		print();
		
		me.addQuiz(12,24);
		
		print();
		
		me.addQuiz(25, 25);
		
		print();
		
		me.addQuiz(2,0);
		
		print();
		
		me.addQuiz(0, 10);
		
		print();
				
	}

	private static void print()
	{
		System.out.println(""+me.getName()+"\naverage: "+ me.getAverage()+"\ntotal grade: "+me.getTotalGrade()+"\ntotal value: "+ me.getTotalValue()+ "\nnum of quizes: "+ me.getNum());
	}
}
