package P8_2__P8_6;

import java.util.ArrayList;

public class Purse
{
	private ArrayList<String> coins = new ArrayList<String>();
	
	public Purse()
	{
	}
	
	public void addCoin(String coinName)
	{
		coins.add(coinName);
	}
	
	public String toString()
	{
		if(coins.size() == 0) return "Purse[]";
		String[] coinarray = new String[coins.size()+1];
		coinarray[0] = "Purse[";
		for(int x = 1;x <= coins.size()-1; x++)
		{
			coinarray[x] = coinarray[x-1]+coins.get(x-1)+", ";
		}
		coinarray[coins.size()] = coinarray[coins.size()-1]+coins.get(coins.size()-1)+"]";
		
			return coinarray[coinarray.length-1];
	}
	
	public String reverse()
	{
		if(coins.size() == 0) return "Purse[]";
		String[] coinarray = new String[coins.size()+1];
		coinarray[coins.size()] = "Purse[";
		for(int x = coins.size()-1;x > 0; x--)
		{
			coinarray[x] = coinarray[x+1]+coins.get(x)+", ";
		}
		coinarray[0] = coinarray[1] + coins.get(0)+"]";
		
			return coinarray[0];
	}
	
	public void transfer(Purse other)
	{
		int originalsize = other.coins.size();
		for(int x = 0;x < originalsize;x++)
		{
			this.coins.add(other.coins.remove(0));
		}
	}
	
	public boolean sameContents(Purse other)
	{
		if(this.toString().equalsIgnoreCase(other.toString())) 
			return true;
		return false;
	}
	
	
	public boolean sameCoins(Purse other)
	{
		int thisquarters = 0,thisdimes = 0,thisnickels = 0,thispennys = 0,otherquarters = 0,otherdimes = 0,othernickels = 0,otherpennys = 0;
		if(this.coins.size() != other.coins.size()) return false;
		for(int x = 0; x < this.coins.size(); x++)
		{
			switch(this.coins.get(x).charAt(0))
			{
				case 'q':
					thisquarters +=1;
					break;
				case 'd':
					thisdimes +=1;
					break;
				case 'n':
					thisnickels +=1;
					break;
				case 'p':
					thispennys +=1;
					break;
			}
			switch(other.coins.get(x).charAt(0))
			{
				case 'q':
					otherquarters +=1;
					break;
				case 'd':
					otherdimes +=1;
					break;
				case 'n':
					othernickels +=1;
					break;
				case 'p':
					otherpennys +=1;
					break;
			}
		}
		if(thisquarters != otherquarters || thisdimes != otherdimes || thisnickels != othernickels || thispennys != otherpennys)
			return false;
		
		return true;
	
	}
}
