package student;

public class student
{
	private double totalGrade = 0, avgGrade = 0, totalValue = 0;
	private int quizNum = 0;
	private String name;
	
	
	public student(String userName)
	{
		name = userName;
	}
	
	public void addQuiz(double score, double value)
	{
		
		totalValue += value;
		totalGrade += score;
		
		if (value >0)
		{
			quizNum++;
			avgGrade = totalGrade/totalValue;
		}
		else
			avgGrade = (score + totalGrade)/totalValue;
		
	}
	
	public double getAverage()
	{
		return avgGrade;
	}
	
	public int getNum()
	{
		return quizNum;
	}
	
	public double getTotalGrade()
	{
		return totalGrade;
	}
	
	public double getTotalValue()
	{
		return totalValue;
	}
	
	public String getName()
	{
		return name;
	}
	
	
}
