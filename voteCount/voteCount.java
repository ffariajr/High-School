package voteCount;

public class voteCount
{
	private int demo, repub;
	
	
	
	public voteCount()
	{
	}
	
	public int getDemoVotes()
	{
		return demo;
	}
	
	public int getRepubVotes()
	{
		return repub;
	}
	
	public void repubVote(int votes)
	{
		repub+=votes;
	}
	
	public void repubVote()
	{
		repub++;
	}
	
	public void demoVote(int votes)
	{
		demo+=votes;
	}
	
	public void demoVote()
	{
		demo++;
	}
	
	public void clearState()
	{
		demo = 0;
		repub = 0;
	}
}
