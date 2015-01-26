package project_diff;

import javax.swing.JOptionPane;

/**
 * @author Fernando Jr This class handles linear and quadratic equations
 */
public class UserInterface
{
	private LinearEquation line; // the LinearEquation Object
	private QuadraticEquation parabola; // the Quadratic Object
	private JOptionPane jay; // the JOptionPane Object
	
	/**
	 * asks if user wants to manage a linear or quadratic equation
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		UserInterface user = new UserInterface();
		JOptionPane jay = new JOptionPane();
		String[] choices = { "Linear Equation", "Quadratic Equation", "Cancel" };
		int ans = JOptionPane.showOptionDialog(jay, "What do you want to make?",
				"Equation Maker v1.1", JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, choices, choices[2]);
		if (ans == 0)
		{
			user.makeLinear();
			user.manageLinear();
		}
		else
			if (ans == 1)
			{
				user.makeQuadratic();
				user.manageQuadratic();
			}
			else
				user.menu(0);
	}
	
	/**
	 * ask the user the type of input they have for a linear Equation and
	 * initiates the Objects
	 */
	private void makeLinear()
	{
		String[] choices = { "Default Line", "Slope-Intercept", "Standard", "Point-Slope",
				"Cancel" };
		int ans = JOptionPane
				.showOptionDialog(
						jay,
						"How do you wan to input the equation?\nDefault makes a line with slope 1 and through the point (0,0).\nWith Slope-Intercept, you can input the slope and the y-intercept.\nWith Standard, you input the x Coefficient, the y Coefficient, then the constant.\nWith Point-Slope, you input the x coordinate, the x coordinate, then the slope.",
						"Input Format", JOptionPane.DEFAULT_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, choices, choices[4]);
		switch (ans)
		{
			case 0:
				line = new LinearEquation();
				break;
			case 1:
				double slope = askDouble("What is the slope?");
				double intercept = askDouble("What is the y intercept?");
				line = new LinearEquation(slope, intercept);
				break;
			case 2:
				boolean go = false;
				do
				{
					go = true;
					try
					{
						int xcoef = askInt("What is the x Coefficient?");
						int ycoef = askInt("What is the y Coefficient?");
						double constant = askDouble("What is the constant of the equation?");
						line = new LinearEquation(xcoef, ycoef, constant);
					}
					catch(IllegalArgumentException E)
					{
						print("Not a function, cannot divide by zero!");
						go = false;
					}
				}
				while(!go);
				break;
			case 3:
				double xpoint = askDouble("What is the x coordinate?");
				double ypoint = askDouble("what is the y coordinate?");
				double tempSlope = askDouble("What is the slope of the equation?");
				line = new LinearEquation(xpoint, ypoint, tempSlope);
				break;
			case 4:
			default:
				main(new String[0]);
		}
		return;
	}
	
	/**
	 * ask the user the type of input they have for a quadratic equation and
	 * initiates the Objects
	 */
	private void makeQuadratic()
	{
		String[] choices = { "Default Parabola", "Standard Parabola", "Linear Multiplication",
				"Cancel" };
		int ans = JOptionPane
				.showOptionDialog(
						jay,
						"Input the equation.\nWith the Default Parabola, you gat a parabola with vertex at (0,0) and the equation is y=xx\nWith the Standard Parabola, you can input the x squared coefficient, the x coefficient and the y intercept\nWith linear multipication, you can inut two linear equations and they will be multiplied together",
						"Input Format", JOptionPane.DEFAULT_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, choices, choices[3]);
		switch (ans)
		{
			case 0:
				parabola = new QuadraticEquation();
				break;
			case 1:
				boolean go = false;
				do
				{
					go = true;
					try
					{
						double xxcoef = askDouble("What is the x squared coefficient?");
						double xcoef = askDouble("What is the x coefficient?");
						double yint = askDouble("What is the y intercept?");
						parabola = new QuadraticEquation(xxcoef, xcoef, yint);
					}
					catch(IllegalArgumentException E)
					{
						print("Not a quadratic equation.");
						go = false;
					}
				}
				while(!go);
				break;
			case 2:
				makeLinear();
				LinearEquation line1 = line;
				makeLinear();
				LinearEquation line2 = line;
				parabola = new QuadraticEquation(line1, line2);
				break;
			case 3:
			default:
				main(new String[0]);
		}
		return;
	}
	
	/**
	 * after initializing the linear equation, asks what the user wants to get
	 * back from the linear equation
	 */
	private void manageLinear()
	{
		String[] choices = { "Slope", "Y Intercept", "X Intercept", "X Coefficient",
				"Y Coefficient", "Constant", "X Coordinate", "Y Coordinate", "Point",
				"Is Linear Function?", "Compare", "Print Slope-Intercept", "Print Point-Slope",
				"Print Sandard", "Help", "Cancel" };
		int ans = panelChoice(JOptionPane.showInputDialog(jay, "What do you want to do?",
				"Equation Maker v1.1: Linear Maker", JOptionPane.DEFAULT_OPTION, null, choices,
				choices[15]), choices);
		switch (ans)
		{
			case 0:
				print("" + line.getSlope());
				break;
			case 1:
				print("(0.0," + line.getYIntercept()+")");
				break;
			case 2:
				if(line.isLinearFunction())
					print("(" + line.getXIntercept()+",0.0)");
				else
					print("No x-intercepts, line is a constant function.");
				break;
			case 3:
				print("" + line.getXCoefficient());
				break;
			case 4:
				print("" + line.getYCoefficient());
				break;
			case 5:
				print("" + line.getConstant());
				break;
			case 6:
				print("" + line.getXCoordinate());
				break;
			case 7:
				print("" + line.getYCoordinate());
				break;
			case 8:
				print("("+line.getXCoordinate()+","+line.getYCoordinate()+")");
			case 9:
				if (line.isLinearFunction())
					print("The equation is a linear function.");
				else
					print("The equation is not a linear function.");
				break;
			case 10:
				if (linearEquals())
					print("The equations are equal.");
				else
					print("The equations are not equal.");
				break;
			case 11:
				print("" + line.slopeInterceptToString());
				break;
			case 12:
				print("" + line.pointSlopeToString());
				break;
			case 13:
				print("" + line.standardToString());
				break;
			case 14:
				print("The coefficients are taken from the equation in Standard Form (aX + bY = C).\nThe Coordinates are a single point on the equation, if you did not specify the point, the point will be the y intercept.\nIs Linear Function will tell the user if the line is a constant function (slope = 0).\nCompare will ask for you to make another line and wil tell you if the are equal.\nThe various prints simply print the equation in the respective formats.");
				break;
			case 15:
				menu(-1);
				break;
		}
		manageLinear();
	}

	/**
	 * after initializing the quadratic equation, asks what the user wants to
	 * get back from the quadratic equation
	 */
	private void manageQuadratic()
	{
		String[] choices = { "X squared Coefficients", "X Coefficient", "Y Intercept",
				"Discriminent", "X Intercepts", "Vertex", "Symmetrical Point", "Compare",
				"Print Equation", "Help", "Cancel" };
		int ans = panelChoice(JOptionPane.showInputDialog(jay, "What do you want to do?",
				"Equation Maker v1.1: Quadratic Maker", JOptionPane.DEFAULT_OPTION, null,
				choices, choices[10]), choices);
		switch (ans)
		{
			case 0:
				print("" + parabola.getXXCoefficient());
				break;
			case 1:
				print("" + parabola.getXCoefficient());
				break;
			case 2:
				print("" + parabola.getYIntercept());
				break;
			case 3:
				if (parabola.discriminentBoolean())
					print("The equation has at least one x intercept.");
				else
					print("The equation has no x intercepts.");
				break;
			case 4:
				if(parabola.discriminentBoolean())
					print("(" + parabola.getXIntercepts().x+",0.0),("+parabola.getXIntercepts().y+",0.0)");
				else
					print("No X Intercepts");
				break;
			case 5:
				print("(" + parabola.vertex().x+","+parabola.vertex().y+")");
				break;
			case 6:
				print("(" + parabola.symmetricalPoint().x+","+parabola.vertex().y+")");
				break;
			case 7:
				if (quadraticEquals())
					print("The equations are equal.");
				else
					print("The equations are not equal.");
				break;
			case 8:
				print("" + parabola.toString());
				break;
			case 9:
				print("The discriminent tells whether the equation has no x intercepts or at least one.\nThe symmetrical point is the point opposite from the y intercept across the vertex's axis.\nCompare asks for another Quadratic equation and returns if they are equal.");
				break;
			case 10:
				menu(1);
				break;
		}
		manageQuadratic();
	}
	
	/**
	 * find the index of the String the user chose in the String[]
	 * 
	 * @param object	the choice of the user as a String
	 * @param choices	the the String[] to search
	 * @return the index of the user's choice
	 */
	private int panelChoice(Object object, String[] choices)
	{
		String panel = (String) object;
		for (int x = 0; x < choices.length; x++)
		{
			if (panel == choices[x])
				return x;
		}
		return choices.length - 1;
	}
	
	/**
	 * asks the user for a new equation, makes it but does not store it, and
	 * checks if their equal
	 * 
	 * @return true if the equations are equal
	 */
	private boolean linearEquals()
	{
		LinearEquation line1 = line;
		makeLinear();
		LinearEquation line2 = line;
		line = line1;
		return line1.equals(line2);
	}
	
	/**
	 * asks the user for a new equation, makes it but does not store it, and
	 * checks if their equal
	 * 
	 * @return true if the equations are equal
	 */
	private boolean quadraticEquals()
	{
		QuadraticEquation parabola1 = parabola;
		makeQuadratic();
		QuadraticEquation parabola2 = parabola;
		parabola = parabola1;
		return parabola1.equals(parabola2);
	}
	
	/**
	 * asks the user if they want to quit or if they want to go to a different
	 * equation type
	 * 
	 * @param sentFrom
	 *             if the user decides to go back, they do not need to lose
	 *             their previous equation information. -1 if sent from
	 *             LinearEquation, 1 if sent from Quadratic, 0 if sent from
	 *             main
	 */
	private void menu(int sentFrom)
	{
		int ans = 0;
		if (sentFrom != 0)
		{
			String[] choices = { "Yes", "No", "Cancel" };
			ans = JOptionPane.showOptionDialog(jay, "Are you sure you want to quit?",
					"Equation Maker v1.1", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]);
			if (ans == 1)
			{
				String[] options = { "Linear Equation", "Quadratic Equation", "Main Menu", "Cancel" };
				int x;
				if (sentFrom == -1)
					x = 0;
				else
					x = 1;
				int ans2 = JOptionPane.showOptionDialog(jay, "Where do you want to go?",
						"Equation Maker v1.1 menu", JOptionPane.DEFAULT_OPTION,
						JOptionPane.YES_NO_CANCEL_OPTION, null, options, options[x]);
				if (ans2 == 0 && sentFrom == -1)
					manageLinear();
				if (ans2 == 1 && sentFrom == 1)
					manageQuadratic();
				if (ans2 == 0 && sentFrom != -1)
					makeLinear();
				if (ans2 == 1 && sentFrom != 1)
					makeQuadratic();
				if(ans2 == 2)
					main(new String[0]);
				if (ans2 == 3)
					ans = 2;
			}
			if (ans == 2)
			{
				if (sentFrom == -1)
					manageLinear();
				else
					manageQuadratic();
			}
			if (ans == 0)
			{
				JOptionPane.showMessageDialog(jay, "Goodbye", "Quit", JOptionPane.OK_OPTION);
				System.exit(1);
			}
		}
		else
		{
			String[] choices = { "Yes", "No" };
			ans = JOptionPane.showOptionDialog(jay, "Are you sure you want to quit?",
					"Equation Maker v1.1", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]);
			if (ans == 1)
				main(new String[0]);
			if (ans == 0)
			{
				JOptionPane.showMessageDialog(jay, "Goodbye", "Quit", JOptionPane.OK_OPTION);
				System.exit(1);
			}
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
		JOptionPane.showMessageDialog(jay, message, "Answer", JOptionPane.OK_OPTION);
		return;
	}
	
	/**
	 * asks the user for input as a double, and if not purely a rational number
	 * asks the user again
	 * 
	 * @param question
	 *             the question to ask the user
	 * @return the number needed, in the correct format, as a double
	 */
	private double askDouble(String question)
	{
		boolean parsed = false;
		double num = 0;
		while (!parsed)
		{
			try
			{
				num = Double.parseDouble(JOptionPane.showInputDialog(question));
				parsed = true;
			} catch (NumberFormatException E)
			{
				JOptionPane.showMessageDialog(jay, "Not a rational number, try again!");
			}
		}
		return num;
	}
	
	/**
	 * asks the user for input as an integer, and if not purely an integer asks
	 * the user again
	 * 
	 * @param question
	 *             the question to ask the user
	 * @return the number needed, in the correct format, as an integer
	 */
	private int askInt(String question)
	{
		boolean parsed = false;
		int num = 0;
		while (!parsed)
		{
			try
			{
				num = Integer.parseInt(JOptionPane.showInputDialog(question));
				parsed = true;
			} catch (NumberFormatException E)
			{
				JOptionPane.showMessageDialog(jay, "Not an integer, try again!");
			}
		}
		return num;
	}
}
