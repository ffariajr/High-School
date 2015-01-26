package project;

import java.awt.geom.Point2D;
import javax.swing.JOptionPane;

/**
 * @author Fernando Jr This class handles linear
 *         and quadratic equations
 */
public class UserInterface
{
	private LinearEquation line; // the
							// LinearEquation
							// Object
	private Quadratic parabola; // the Quadratic
							// Object
	private JOptionPane jay; // the JOptionPane
						// Object

	/**
	 * asks if user wants to manage a linear or
	 * quadratic equation
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		UserInterface user = new UserInterface();
		JOptionPane jay = new JOptionPane();
		String[] choices =
		{ "Linear\nEquation", "Quadratic\nEquation", "cancel" };
		int ans = JOptionPane.showOptionDialog(jay, "What do you want to make?", "Equation Maker v1.1", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, choices[2]);
		if (ans == 0)
			user.makeLinear();
		else if (ans == 1)
			user.makeQuadratic();
		else user.menu(0);
	}

	/**
	 * ask the user the type of input they have
	 * for a linear Equation and initiates the
	 * Objects
	 */
	private void makeLinear()
	{
	}

	/**
	 * ask the user the type of input they have
	 * for a quadratic equation and initiates the
	 * Objects
	 */
	private void makeQuadratic()
	{
	}

	/**
	 * after initializing the linear equation,
	 * asks what the user wants to get back from
	 * the linear equation
	 */
	private void manageLinear()
	{
	}

	/**
	 * after initializing the quadratic equation,
	 * asks what the user wants to get back from
	 * the quadratic equation
	 */
	private void manageQuadratic()
	{
	}

	/**
	 * asks the user for a new equation, makes it
	 * but does not store it, and checks if their
	 * equal
	 * 
	 * @return true if the equations are equal
	 */
	private boolean linearEquals()
	{
		return false;
	}

	/**
	 * asks the user for a new equation, makes it
	 * but does not store it, and checks if their
	 * equal
	 * 
	 * @return true if the equations are equal
	 */
	private boolean quadraticEquals()
	{
		return false;
	}

	/**
	 * asks the user if they want to quit or if
	 * they want to go to a different equation
	 * type
	 * 
	 * @param sentFrom
	 *             if the user decides to go
	 *             back, they do not need to lose
	 *             their previous equation
	 *             information -1 if sent from
	 *             LinearEquation, 1 if sent from
	 *             Quadratic 0 if sent from main
	 */
	private void menu(int sentFrom)
	{
		int ans = 0;
		if (sentFrom != 0)
		{
			String[] choices =
			{ "Yes", "No", "Cancel" };
			ans = JOptionPane.showOptionDialog(jay, "Are you sure you want to quit?", "Equation Maker v1.1", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]);
			if (ans == 1)
			{
				String[] options =
				{ "Linear\nEquation", "Quadratic\nEquation", "Cancel" };
				int x;
				if (sentFrom == -1)
					x = 0;
				else x = 1;
				int ans2 = JOptionPane.showOptionDialog(jay, "Where do yo want to go?", "Equation Maker v1.1 menu", JOptionPane.DEFAULT_OPTION, JOptionPane.YES_NO_CANCEL_OPTION, null, options, options[x]);
				if(ans2 == 0 && sentFrom == -1)
					manageLinear();
				if(ans2 == 1 && sentFrom == 1)
					manageQuadratic();
				if(ans2 == 0 && sentFrom != -1)
					makeLinear();
				if(ans2 == 1 && sentFrom != 1)
					makeQuadratic();
				if(ans2 == 2)
					ans = 2;	
			}

			if (ans == 2)
			{
				if (sentFrom == -1)
					manageLinear();
				else 
					manageQuadratic();
			}
			if (ans == 0) JOptionPane.showMessageDialog(jay, "Goodbye", "Quit", JOptionPane.OK_OPTION);

		}
		else
		{
			String[] choices =
			{ "Yes", "No" };
			ans = JOptionPane.showOptionDialog(jay, "Are you sure you want to quit?", "Equation Maker v1.1", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]);
			if (ans == 1) main(new String[0]);
			if (ans == 0) JOptionPane.showMessageDialog(jay, "Goodbye", "Quit", JOptionPane.OK_OPTION);

		}

	}

	/**
	 * outputs what is passed in on a new window
	 * 
	 * @param message
	 *             the message to show
	 */
	private void print(String message)
	{
	}
}