package sudoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class SudokuMaker
{
	private int[][] ans, puz, inp;
	private int diff;

	/**
	 * constructs an new puzzle make but does not
	 * make a new puzzle or answer key
	 * 
	 * @param difficulty
	 *             , the puzzles difficulty 0
	 *             indicates 46/81 numbers given
	 *             1 indicates 32/81 numbers
	 *             given 2 indicates 24/81
	 *             numbers given
	 */
	public SudokuMaker(int difficulty)
	{
		if (diff < 3 && diff >= 0)
			diff = difficulty;
		else throw new IllegalArgumentException();
		inp = new int[9][9];
	}

	/**
	 * changes the difficulty for the next puzzle
	 * made by a call to makeNewPuzzle() does not
	 * change the current puzzle
	 * 
	 * @param newDifficulty
	 *             , the new difficulty 0
	 *             indicates 46/81 numbers given
	 *             1 indicates 32/81 numbers
	 *             given 2 indicates 24/81
	 *             numbers given
	 */
	public void changeDifficulty(int newDifficulty)
	{
		if (diff < 3 && diff >= 0)
			diff = newDifficulty;
		else throw new IllegalArgumentException();
		diff = newDifficulty;
	}

	/**
	 * returns the puzzle to show to user
	 * 
	 * @return the puzzle with the number of
	 *         numbers already given by the
	 *         difficulty at the time the puzzle
	 *         was made
	 */
	public int[][] getPuz()
	{
		return puz;
	}

	/**
	 * returns the current puzzle to show to user
	 * 
	 * @return the original puzzle (the getPuz())
	 *         and all the inputs from the user
	 */
	public int[][] getCurrent()
	{
		int[][] result = new int[9][9];
		for (int x = 0; x < 9; x++)
			for (int y = 0; y < 9; y++)
				result[x][y] = puz[x][y] == 0 ? inp[x][y] : puz[x][y];
		return result;
	}

	/**
	 * input a number at any location on the
	 * puzzle if the number is in the wrong
	 * position, it will be added, but a call to
	 * getCurrent() will ignore it
	 * 
	 * @param x
	 *             the column (1-9)
	 * @param y
	 *             the row (1-9)
	 * @param num
	 *             the number at that position
	 * @return whether it is correct
	 */
	public boolean input(int x, int y, int num)
	{
		inp[x - 1][y - 1] = num;
		return num == ans[x - 1][y - 1];
	}

	/**
	 * returns the answer
	 * 
	 * @return a grid with true and false values
	 *         if a position has a false value,
	 *         it is not correct
	 */
	public boolean[][] getAns()
	{
		boolean[][] result = new boolean[9][9];
		for (int x = 0; x < 9; x++)
			for (int y = 0; y < 9; y++)
				if (inp[x][y] != 0 && inp[x][y] != ans[x][y])
					result[x][y] = false;
				else result[x][y] = true;
		return result;
	}

	/**
	 * prints the grid with System.out
	 */
	public void printAns()
	{
		if (ans == null) throw new IllegalStateException("No Puzzle Assembled");
		for (int x = 0; x < 9; x++)
		{
			for (int y = 0; y < 9; y++)
				System.out.print(ans[x][y] + " ");
			System.out.println();
		}
	}

	/**
	 * assembles a new puzzle with the current
	 * difficulty
	 */
	public void makeNewPuzzle()
	{
		makeAns();
		makePuzz();
	}

	private int findCube(int row, int col)
	{
		if (row == 0 || row == 1 || row == 2)
		{
			if (col == 0 || col == 1 || col == 2)
				return 0;
			else if (col == 3 || col == 4 || col == 5)
				return 1;
			else if (col == 6 || col == 7 || col == 8) return 2;
		}
		else if (row == 3 || row == 4 || row == 5)
		{
			if (col == 0 || col == 1 || col == 2)
				return 3;
			else if (col == 3 || col == 4 || col == 5)
				return 4;
			else if (col == 6 || col == 7 || col == 8) return 5;
		}
		else if (row == 6 || row == 7 || row == 8)
		{
			if (col == 0 || col == 1 || col == 2)
				return 6;
			else if (col == 3 || col == 4 || col == 5)
				return 7;
			else if (col == 6 || col == 7 || col == 8) return 8;
		}
		return -1;
	}

	private void makePuzz()
	{
		puz = new int[9][9];
	}

	private void makeAns()
	{
		ans = new int[9][9];
		HashSet<Integer>[] cols = new HashSet[9];
		HashSet<Integer>[] rows = new HashSet[9];
		HashSet<Integer>[] cube = new HashSet[9];

		for (int x = 0; x < 9; x++)
		{
			cols[x] = new HashSet<Integer>();
			rows[x] = new HashSet<Integer>();
			cube[x] = new HashSet<Integer>();
		}

		Random rnd = new Random();

		for (int z = 0; z < 9; z++)
		{
			for (int num = 1; num < 10; num++)
			{
				cols[z].add(new Integer(num));

				ArrayList<Integer> temp = new ArrayList<Integer>(9);
				for (int q = 0; q < 9; q++)
					temp.add(new Integer(q));

				for (int t = 0; t < 9; t++)
				{
					int hold = rnd.nextInt(temp.size());
					int row = temp.get(hold);
					if (!(rows[row].contains(new Integer(num))) && !(cube[findCube(row, z)].contains(new Integer(num))) && ans[z][row] == 0)
					{
						rows[row].add(new Integer(num));
						cube[findCube(row,z)].add(new Integer(num));
						ans[z][row] = num;
						break;
					}
					else temp.remove(hold);
				}
			}
		}
		/*
		 * for(int z =0;z<9;z++)
		 * System.out.print(rows[z]);
		 * System.out.println(); for(int z
		 * =0;z<9;z++)
		 * System.out.print(cols[z]);
		 * System.out.println(); for(int z
		 * =0;z<9;z++)
		 * System.out.print(cube[z]);
		 * System.out.println();
		 */

	}
}