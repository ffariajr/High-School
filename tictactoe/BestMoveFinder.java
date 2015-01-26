package tictactoe;

/*
 * do instant wins prevent next turn instant
 * losses choose option that has most future wins
 * and least future losses
 */

public class BestMoveFinder {
	public static int diff = 0;
	
	public static int find(int[] copy, int i) {
		
		Move[] moves = createMoveTree(copy, i);
		
		if (moves == null)
			return -1;
		
		if (diff == 1)
			scoreMoves(moves, 100000000);
		else if (diff == 0)
			scoreMoves(moves, 10);
		else {
			scoreMoves(moves, 100000000);
		}
		
		int bScore = -1000000000;
		int bMove = -1;
		
		int qp = diff == 2?0:1;
		while (qp < 2) {
			for (int q = 0; q < 9; q++) {
				if (moves[q] != null)
					if (diff == 2 && qp == 0 && isTieable(moves[q])) {
						if (bScore < moves[q].score) {
							bScore = moves[q].score;
							bMove = q;
						}
					}
					else if(qp == 1) {
						if (bScore < moves[q].score) {
							bScore = moves[q].score;
							bMove = q;
						}
					}
			}
			if(bMove == -1)
				qp++;
			else
				qp=2;
			
		}
		
		printScores(moves);
		return bMove;
	}
	
	public static Move[] createMoveTree(int[] copy, int i) {
		Move[] m = new Move[9];
		// int c = 0;
		
		if (result(copy) != 0)
			return null;
		
		for (int q = 0; q < 9; q++) {
			if (copy[q] == 0) {
				// c++;
				m[q] = new Move();
				copy[q] = i;
				m[q].submoves = createMoveTree(copy, -i);
				copy[q] = 0;
			}
		}
		
		// if(c == 0)
		// return null;
		return m;
	}
	
	private static void scoreMoves(Move[] mov, int mul) {
		for (Move m : mov) {
			if (m != null) {
				if (m.submoves == null)
					m.score += mul;
				else {
					scoreMoves(m.submoves, -mul / 10);
					for (Move s : m.submoves)
						if (s != null)
							m.score += s.score;
				}
			}
		}
	}
	
	/*
	 * if any submoves have all negative score
	 * submoves, that move is a trap private
	 * static void deleteLosses(Move[] mov) {
	 * for(int q = 0; q < 9; q++) if(mov[q] !=
	 * null && !isTieable(mov[q])) mov[q] = null;
	 * }
	 */
	
	// returns true if this move does not lose in
	// the future
	private static boolean isTieable(Move mov) {
		if (mov.submoves == null || mov.nosubs())
			return true;
		
		for (Move sub : mov.submoves) {
			if (sub != null) {
				if (sub.submoves == null) {
					return false;
				}
				else if(sub.nosubs()) {
					return true;
				}
				else {
					boolean result = false;
					for (Move sm : sub.submoves) {
						if (sm != null) {
							result = result || isTieable(sm);
						}
					}
					if(!result)
						return false;
				}
			}
		}
		
		return true;
	}
	
	private static void printScores(Move[] m) {
		System.err.println("");
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (m[x * 3 + y] != null)
					System.err.print("\t" + isTieable(m[x * 3 + y]) + "\t");
				else
					System.err.print("\t\t");
			}
			System.err.println("");
		}
		
		System.err.println("");
		System.err.println("");
	}
	
	/**
	 * returns 1 if X won, or -1 if O won, or 0
	 * if not end
	 * 
	 * @param arr
	 * @return
	 */
	private static int result(int[] arr) {
		if ((arr[0] == 1 && arr[1] == 1 && arr[2] == 1) || (arr[0] == -1 && arr[1] == -1 && arr[2] == -1))
			return arr[0];
		if ((arr[3] == 1 && arr[4] == 1 && arr[5] == 1) || (arr[3] == -1 && arr[4] == -1 && arr[5] == -1))
			return arr[3];
		if ((arr[6] == 1 && arr[7] == 1 && arr[8] == 1) || (arr[6] == -1 && arr[7] == -1 && arr[8] == -1))
			return arr[6];
		if ((arr[0] == 1 && arr[3] == 1 && arr[6] == 1) || (arr[0] == -1 && arr[3] == -1 && arr[6] == -1))
			return arr[0];
		if ((arr[1] == 1 && arr[4] == 1 && arr[7] == 1) || (arr[1] == -1 && arr[4] == -1 && arr[7] == -1))
			return arr[1];
		if ((arr[2] == 1 && arr[5] == 1 && arr[8] == 1) || (arr[2] == -1 && arr[5] == -1 && arr[8] == -1))
			return arr[2];
		if ((arr[0] == 1 && arr[4] == 1 && arr[8] == 1) || (arr[0] == -1 && arr[4] == -1 && arr[8] == -1))
			return arr[0];
		if ((arr[2] == 1 && arr[4] == 1 && arr[6] == 1) || (arr[2] == -1 && arr[4] == -1 && arr[6] == -1))
			return arr[2];
		return 0;
	}
	
	/**
	 * true if arr filled and no wins
	 * 
	 * @param arr
	 * @return
	 */
	/*
	 * private static boolean tied(int[] arr) {
	 * if(result(arr) != 0) return false; for(int
	 * x : arr) if(x == 0) return false; return
	 * true; }
	 */
	
	/*
	 * public static int find(int[] arr, int
	 * turn) { moves last = onlyOne(arr, turn);
	 * if(last != null) return last.square;
	 * ArrayList<moves> arm = new
	 * ArrayList<moves>(); //find instant wins
	 * this turn for(int x=0;x<9;x++) { if(arr[x]
	 * == 0) { arr[x] = turn; if(result(arr) ==
	 * turn) { //find instant win arr[x] = 0;
	 * return x; } arr[x] = 0; } } //find instant
	 * losses next turn (opponents) for(int
	 * x=0;x<9;x++) { if(arr[x] == 0) { arr[x] =
	 * -turn; if(result(arr) == -turn) { arr[x] =
	 * 0; return x; } arr[x] = 0; } } //find
	 * other best move for(int x=0;x<9;x++) {
	 * if(arr[x] == 0) { arr[x] = turn;
	 * arm.add(findMove(arr, turn*-1, x)); arr[x]
	 * = 0; } } return chooseWinner(arm).square;
	 * } private static moves
	 * chooseWinner(ArrayList<moves> arm) {
	 * //finds future wins if(arm.size() == 0)
	 * return null; moves winner = arm.get(0);
	 * for(int x = 1; x < arm.size(); x++)
	 * if(arm.get(x).WIN) return arm.get(x); else
	 * if(arm.get(x).losses <= winner.losses)
	 * if(arm.get(x).wins == winner.wins &&
	 * arm.get(x).losses == winner.losses &&
	 * !arm.get(x).LOSE) winner = arm.get(x);
	 * return winner; } /**
	 * @param arr
	 * @return
	 * @precondition
	 * @postcondition null if more than 1 move
	 * left, otherwise that moves
	 */
	/*
	 * private static moves onlyOne(int[] arr,
	 * int turn) { int tempholder = -1; for(int x
	 * =0;x<9;x++) if(arr[x] == 0) if(tempholder
	 * == -1) tempholder = x; else tempholder =
	 * -2; if(tempholder >= 0) return new
	 * moves(tempholder, 0); return null; }
	 */
	
	/**
	 * this forms a move based on board.square
	 * having value turn, determines the best
	 * move, and returns it. best move is found
	 * based on 2 rules in order: if an instant
	 * win is found. if there is a move that has
	 * many wins and fewer losses, fewer losses
	 * preferred.
	 * 
	 * @param arr
	 * @param turn
	 * @param square
	 * @return the best move :)
	 */
	/*
	 * private static moves findMove(int[] arr,
	 * int turn, int square) { moves me = new
	 * moves(square, turn); ArrayList<moves> arm
	 * = new ArrayList<moves>(); //only one space
	 * left moves last = onlyOne(arr, turn);
	 * if(last != null) return last; //find
	 * instant wins, adds a win to this move
	 * for(int x = 0; x < 9; x++) { if(arr[x] ==
	 * 0) { arr[x] = turn; if(result(arr) == 1)
	 * me.won(); else if(result(arr) == -1)
	 * me.lost(); else arm.add(findMove(arr,
	 * turn*-1, x)); arr[x] = 0; } } //count
	 * future solutions for(int x = 0; x <
	 * arm.size(); x++) { for(int z = 0; z <
	 * arm.get(x).maxValwl(); z++) {
	 * if(arm.get(x).wins >= z*2) //half as much
	 * for future solutions me.won();
	 * if(arm.get(x).losses >= z*2) me.lost(); }
	 * } return me; }
	 */
	
	/**
	 * finds best move
	 * 
	 * @param arr
	 * @param turn
	 * @return
	 */
	/*
	 * public static int find(int[] arr, int
	 * turn) { int tempholder = -1; for(int x
	 * =0;x<9;x++) if(arr[x] == 0) if(tempholder
	 * == -1) tempholder = x; else tempholder =
	 * -2; if(tempholder >= 0) return tempholder;
	 * moves[] m = new moves[0]; for(int x = 0; x
	 * < 9; x++) { if(arr[x] != 0) { moves nm =
	 * new moves(x); finder(arr, turn, nm); m =
	 * adder(m, nm); } } moves max = null;
	 * for(moves temp : m) if(max == null) max =
	 * temp; else max =
	 * max.better(temp)?max:temp; return
	 * max.place(); }
	 */
	
	/**
	 * finds a value for the turn in the place
	 * 
	 * @param arr
	 * @param turn
	 * @param place
	 * @param m
	 */
	/*
	 * private static void finder(int[] arr, int
	 * turn, moves m) { arr[m.square] = turn; int
	 * temp = result(arr); if(temp == 1)
	 * m.winner(); else if(temp == -1) m.loser();
	 * if(tied(arr)) return; moves[] muvin = new
	 * moves[0]; for(int x = 0; x < 9; x++) {
	 * if(arr[x] != 0) { moves nm = new moves(x);
	 * finder(arr, turn*-1, nm); arr[x] = 0;
	 * muvin = adder(muvin, nm); } } int totals =
	 * 0; for(moves q : muvin) { if(q.LOSE)
	 * m.lost(); else if(q.WIN) m.won(); else
	 * if(q.value() == 0) m.tied(); else totals
	 * += q.value(); } m.adder(totals); }
	 */
	
	/*
	 * public static int find(int[] arr) { int
	 * empties = 0; for(int x:arr) if(x == 0)
	 * empties++; if(empties == 1) for(int x = 0;
	 * x < 9; x++) if(arr[x] == 0) return x;
	 * return find(arr, -2, 9); }
	 */
	
	/*
	 * private static int find(int[] arr, int
	 * who, int distance) { int empties = 0;
	 * for(int x:arr) if(x == 0) empties++; int
	 * ans = value(arr)*distance; if(ans !=0)
	 * return ans; if(empties == 0) return
	 * value(arr)*distance; int gval = 0; int
	 * gplace = 0; for(int x = 0;x<empties;x++) {
	 * int val = 0; if(arr[x] == 0) { arr[x] =
	 * who; val = find(arr, -who, distance--);
	 * arr[x] = 0; } if(Math.abs(val) >= gval) {
	 * gval = val; gplace = x; } } return gplace;
	 * }
	 */
	
	/*
	 * private static int value(int[] arr) {
	 * if((arr[0]*arr[0] == 1 && arr[1]*arr[1] ==
	 * 1 && arr[2]*arr[2] == 1)) return -arr[0];
	 * if((arr[3] == 1 && arr[4] == 1 && arr[5]
	 * == 1) || (arr[3] == -1 && arr[4] == -1 &&
	 * arr[5] == -1)) return -arr[3]; if((arr[6]
	 * == 1 && arr[7] == 1 && arr[8] == 1) ||
	 * (arr[6] == -1 && arr[7] == -1 && arr[8] ==
	 * -1)) return -arr[6]; if((arr[0] == 1 &&
	 * arr[3] == 1 && arr[6] == 1) || (arr[0] ==
	 * -1 && arr[3] == -1 && arr[6] == -1))
	 * return -arr[0]; if((arr[1] == 1 && arr[4]
	 * == 1 && arr[7] == 1) || (arr[1] == -1 &&
	 * arr[4] == -1 && arr[7] == -1)) return
	 * -arr[1]; if((arr[2] == 1 && arr[5] == 1 &&
	 * arr[8] == 1) || (arr[2] == -1 && arr[5] ==
	 * -1 && arr[8] == -1)) return -arr[2];
	 * if((arr[0] == 1 && arr[4] == 1 && arr[8]
	 * == 1) || (arr[0] == -1 && arr[4] == -1 &&
	 * arr[8] == -1)) return -arr[0]; if((arr[2]
	 * == 1 && arr[4] == 1 && arr[6] == 1) ||
	 * (arr[2] == -1 && arr[3] == -1 && arr[4] ==
	 * -1)) return -arr[2]; return 0; }
	 */
	
	/**
	 * adds an object to an array
	 * 
	 * @param mov
	 * @param m
	 * @return
	 */
	/*
	 * private static moves[] adder(moves[] mov,
	 * moves m) { moves[] temp = new
	 * moves[mov.length+1]; for(int x =
	 * 0;x<mov.length;x++) temp[x] = mov[x];
	 * temp[temp.length] = m; return temp; }
	 */
	
	/**
	 * holds a square and its value
	 * 
	 * @author Nando
	 */
	/*
	 * private static class moves { private int
	 * square, turn; private int wins, losses,
	 * ties; boolean WIN, LOSE; private double
	 * endadder; private moves(int place, int
	 * turn) { square = place; wins = 0; losses =
	 * 0; ties = 0; WIN = false; LOSE = false;
	 * endadder = 0; this.turn = turn; } private
	 * double value() { if(WIN) return 100;
	 * if(LOSE) return -100; if(wins+losses+ties
	 * != 0) return (wins*1.0 -
	 * losses)/(wins+losses+ties*1.0) + endadder;
	 * else return endadder; } private int
	 * place() { return square; } private void
	 * won() { wins++; } private void lost() {
	 * losses++; } private void tied() { ties++;
	 * } private void winner() { WIN = true; }
	 * private void loser() { LOSE = true; }
	 * private void adder(double add) { endadder
	 * += add; } private boolean better(moves m)
	 * { if(value() >= m.value()) return true;
	 * return false; } public int maxValwl() {
	 * if(wins>losses) return wins; return
	 * losses; } }
	 */
}
