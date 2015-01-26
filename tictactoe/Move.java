package tictactoe;

public class Move {
	public Move[] submoves;
	public int score;
	
	public Move() {
		submoves = new Move[9];
		score = 0;
	}
	
	public boolean nosubs() {
		int c = 0;
		if(submoves == null)
			return true;
		
		for(Move m : submoves) 
			if(m != null)
				c++;
		return c == 0;
	}
}
