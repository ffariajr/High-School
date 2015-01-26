package discreteMathProb;

public class City
{	
	public static void main(String[] args)
	{
		setDistance();
		int[] distances = new int[24];
		String[] cities = new String[24];
		int index = 0;
		
		for(int x1 = 1; x1<5;x1++)
		{
			for(int x2 = 1; x2<5;x2++)
			{
				if(x2 != x1)
				{
					for(int x3 = 1;x3<5;x3++)
					{
						if(x3 != x2 && x3 != x1)
						{
							for(int x4 =1;x4<5;x4++)
							{
								if(x4 != x3 && x4 != x2 && x4 != x1)
								{
									distances[index] = sum(x1,x2,x3,x4);
									cities[index] = toString(x1,x2,x3,x4);
									index++;
								}
							}
						}
					}
				}
			}
		}
		
	
		int smallest = 23;
		int biggest = 23;
		int total = 0;
		for(int x = 22; x>-1;x--)
		{
			if(distances[x] < distances[smallest])
				smallest = x;
			if(distances[x] > distances[biggest])
				biggest = x;
			total+=distances[x];
		}
		total /=24;
		
		System.out.println("Smallest:\t" + cities[smallest] +"\t"+ distances[smallest]);
		System.out.println("Greatest:\t" + cities[biggest] +"\t"+ distances[biggest]);
		System.out.println("Average:\t" + total);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("City Order\tTotal Distance\t|\tCity Order\tTotal Distance\t|\tCity Order\tTotal Distance\t|\tCity Order\tTotal Distance");
		System.out.println("\t\t\t\t|\t\t\t\t\t|\t\t\t\t\t|");
		for(int x = 0; x<6;x++)
		{
			System.out.println(cities[x] +"\t\t"+ distances[x] +"\t\t|\t"+ cities[x+6] +"\t\t"+ distances[x+6] +"\t\t|\t"+ cities[x+12] +"\t\t"+ distances[x+12] +"\t\t|\t"+ cities[x+18] +"\t\t"+ distances[x+18]);
		}
		
	}
	
	private static int sum(int x1, int x2, int x3, int x4)
	{
		return distance[0][x1]+distance[x1][x2]+distance[x2][x3]+distance[x3][x4]+distance[x4][0];
	}
	
	private static String toString(int x1, int x2, int x3, int x4)
	{
		char[] temp = { 'a', (char) (x1+97),(char) (x2+97),(char) (x3+97),(char) (x4+97), 'a'};
		return (new String(temp)).toUpperCase();
	}
	
	private static int[][] distance = new int[5][5];
	
	public static void setDistance()
	{
		distance[0][1] = 25;
		distance[0][2] = 22;
		distance[0][3] = 17;
		distance[0][4] = 21;
		distance[1][0] = 25;
		distance[1][2] = 24;
		distance[1][3] = 19;
		distance[1][4] = 18;
		distance[2][0] = 22;
		distance[2][1] = 24;
		distance[2][3] = 20;
		distance[2][4] = 16;
		distance[3][0] = 17;
		distance[3][1] = 19;
		distance[3][2] = 20;
		distance[3][4] = 15;
		distance[4][0] = 21;
		distance[4][1] = 18;
		distance[4][2] = 16;
		distance[4][3] = 15;
	}
}
