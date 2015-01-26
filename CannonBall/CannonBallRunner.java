package CannonBall;

public class CannonBallRunner
{
	public static void main(String[] args) 
	{ 

	CannonBall z = new CannonBall(10,20); 
	int x = 0; 

	while(z.getY() >= 0) 
	{ 
		x++; 
		z.run(); 

		if (x%100 == 0) 
		{ 
			System.out.println(""+z.getTime()+"seconds passed."); 
			System.out.println("X Coordinate -> "+z.getX()); 
			System.out.println("Y Coordinate -> "+z.getY()); 
		} 
	} 

	System.out.println(""+z.getTime()+"seconds passed."); 
	System.out.println("X Coordinate -> "+z.getX()); 
	System.out.println("Y Coordinate -> 0"); 
	}
}
