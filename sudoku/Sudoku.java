package sudoku;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Random;

public class Sudoku extends JPanel
{	
	SudokuMaker puzzle;
	
	public Sudoku(int difficulty)
	{
		puzzle = new SudokuMaker(difficulty);
	}
	
	public static void main(String[] args) 
	{
		JOptionPane diff = new JOptionPane();
		String[] diffs = {"easy","normal","hard"};
		Object answe = diff.showInputDialog(null, "Select Difficulty","Difficulty", JOptionPane.QUESTION_MESSAGE, null, diffs, "easy");
		if(answe == null)
			throw new IllegalArgumentException("No Difficulty Selected");
		String answ = (String)answe;
		int ans = 1;
		switch(answ.charAt(0))
		{
			case 'e':
				ans = 0;
				break;
			case 'n':
				ans = 1;
				break;
			case 'h':
				ans = 2;
				break;
		}
		Sudoku gui = new Sudoku(ans);
		gui.go();
	}

	public void go() 
	{
		JFrame frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000,1010);
		frame.setVisible(true);
		frame.getContentPane().add(this,java.awt.BorderLayout.CENTER);
	}

	public void paintComponent(Graphics gr)
	{
		Graphics2D g = (Graphics2D)gr;
		
		g.setColor(Color.yellow);
		g.fillRect(0,0,1200,1200);
		g.setColor(Color.black);
		g.setFont(new Font(g.getFont().getName(), Font.BOLD, 30));
		g.drawString("Sudoku", 450, 40);
		
		for(int x = 0;x<10;x++)
		{
			g.drawLine(x*100+50, 50, x*100+50, 950);
			g.drawLine(50, x*100+50, 950, x*100+50);
		}
		
		for(int x = 0;x<4;x++)
		{
			g.drawLine(x*300+49, 50, x*300+49, 950);
			g.drawLine(50, x*300+49, 950, x*300+49);
			
			g.drawLine(x*300+51, 50, x*300+51, 950);
			g.drawLine(50, x*300+51, 950, x*300+51);
		}
		
		puzzle.makeNewPuzzle();
		puzzle.printAns();
	}	
}