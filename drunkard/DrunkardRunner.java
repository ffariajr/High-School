package drunkard;

public class DrunkardRunner
{
	public static void main(String[] args) 
	{
		Drunkard guy = new Drunkard();   //makes a new drunkard
		int x = 1; 
		while (x <= 100)	
		{			
			guy.move();
			System.out.println("("+guy.getX() + ", " + guy.getY() + ")       Distance= "+guy.distance);
			x++;
		}
	}
}
