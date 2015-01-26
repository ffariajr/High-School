package voteCount;


public class voteCountTester
{

	
	public static void main(String[] args)
	{
			
		int a = (int) (Math.random()*1000);
		int b = (int) (Math.random()*1000);
		
		System.out.println("demo votes = "+a+"   repub votes = "+b +"\n-----------------------------------------");
		
		voteCount votes = new voteCount();
		
		for(int x = 1; x <= a; x++)
			votes.demoVote();
		
		for(int x = 1; x <= b; x++)
			votes.repubVote();
		
		System.out.println("Democratic votes: "+votes.getDemoVotes()+"\tRepublican votes: "+votes.getRepubVotes());
		
		votes.clearState();
		
		System.out.println("Democratic votes: "+votes.getDemoVotes()+"\tRepublican votes: "+votes.getRepubVotes());
		
		votes.demoVote(758);
		votes.repubVote(359);
		
		System.out.println("Democratic votes: "+votes.getDemoVotes()+"\tRepublican votes: "+votes.getRepubVotes());
		
	}

}
