package userInterfaces;

import javax.swing.*;
import storageObjects.Point;
import linearEquations.*;

// Graph them

public class interface1
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		String[] choices =
		{ "Quadratic", "Slope-Intercept", "Standard", "Point-Slope", "Quit" };
		JOptionPane Jay = new JOptionPane();
		int choice = JOptionPane.showOptionDialog(Jay, "What do you want to do?", "Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, choices[4]);

		String as, bs, cs, ds, es, fs, xs, ys, ms;
		double a, b, c, x, y, m, f;
		int d, e;

		switch (choice)
		{
			case 0:
				as = (String) JOptionPane.showInputDialog("Whats is your x squared cooeficient:");
				a = checkDouble(as, "Whats is your x squared cooeficient:");
				bs = (String) JOptionPane.showInputDialog("Whats is your x coeficient:");
				b = checkDouble(bs, "Whats is your x cooeficient:");
				cs = (String) JOptionPane.showInputDialog("Whats is your y Intercept:");
				c = checkDouble(cs, "Whats is your y Intercept:");
				Quadratic quadEquation = new Quadratic(a, b, c);
				quad(quadEquation);
				break;
			case 1:
				bs = (String) JOptionPane.showInputDialog("Whats is your slope:");
				b = checkDouble(bs, "Whats is your slope:");
				cs = (String) JOptionPane.showInputDialog("Whats is your y Intercept:");
				c = checkDouble(cs, "Whats is your y Intercept:");
				slopeIntercept SIEquation = new slopeIntercept(b, c);
				sI(SIEquation);
				break;
			case 2:
				ds = (String) JOptionPane.showInputDialog("Whats is your y coeficient:");
				d = checkInt(ds, "Whats is your y coeficient:");
				es = (String) JOptionPane.showInputDialog("Whats is your x coeficient:");
				e = checkInt(es, "Whats is your x coeficient:");
				fs = (String) JOptionPane.showInputDialog("Whats is your constant:");
				f = checkDouble(fs, "Whats is your constant:");
				standard SEquation = new standard(d, e, f);
				s(SEquation);
				break;
			case 3:
				xs = (String) JOptionPane.showInputDialog("Whats is your x coordinate:");
				x = checkDouble(xs, "Whats is your x coordinate:");
				ys = (String) JOptionPane.showInputDialog("Whats is your y coordinate:");
				y = checkDouble(ys, "Whats is your y coordinate:");
				ms = (String) JOptionPane.showInputDialog("Whats is your slope:");
				m = checkDouble(ms, "Whats is your slope:");
				Point userPoint = new Point(x, y);
				pointSlope PSEquation = new pointSlope(userPoint, m);
				pS(PSEquation);
				break;
			case 4:
				String[] yesOrNo =
				{ "Yes, I want to quit", "No, bring me back" };
				int ans = JOptionPane.showOptionDialog(Jay, "Are you Sure you want to quit?", "Wait...", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION, null, yesOrNo, "1");
				if (ans == 1)
					main(null);
				else JOptionPane.showMessageDialog(Jay, "Goodbye.");
				break;
		}

	}

	public static void quad(Quadratic quadEquation)
	{
		JOptionPane Jay = new JOptionPane();
		String[] choices =
		{ "a", "b", "c", "vertex", "discriminent", "x intercepts", "y intercept", "symmetrical point", "summary", "cancel" };
		int choice = JOptionPane.showOptionDialog(Jay, "What do you want to do?", "Sub Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, "cancel");

		switch (choice)
		{
			case 0:
				JOptionPane.showMessageDialog(Jay, quadEquation.getA());
				quad(quadEquation);
				break;
			case 1:
				JOptionPane.showMessageDialog(Jay, quadEquation.getB());
				quad(quadEquation);
				break;
			case 2:
				JOptionPane.showMessageDialog(Jay, quadEquation.getC());
				quad(quadEquation);
				break;
			case 3:
				JOptionPane.showMessageDialog(Jay, "(" + quadEquation.vertex().getX() + "," + quadEquation.vertex().getY() + ")");
				quad(quadEquation);
				break;
			case 4:
				JOptionPane.showMessageDialog(Jay, quadEquation.discriminentBoolean());
				quad(quadEquation);
				break;
			case 5:
				JOptionPane.showMessageDialog(Jay, "(" + quadEquation.xIntercepts().getX() + ",0.0) , (" + quadEquation.xIntercepts().getY() + ",0.0)");
				quad(quadEquation);
				break;
			case 6:
				JOptionPane.showMessageDialog(Jay, "(0.0," + quadEquation.yIntercept().getY() + ")");
				quad(quadEquation);
				break;
			case 7:
				JOptionPane.showMessageDialog(Jay, "(" + quadEquation.symmetricalPoint().getX() + "," + quadEquation.symmetricalPoint().getY() + ")");
				quad(quadEquation);
				break;
			case 8: // all
				JOptionPane.showMessageDialog(Jay, "Y = ax^2 + bx + c\na = " + quadEquation.getA() + "\nb = " + quadEquation.getB() + "\nc = " + quadEquation.getC() + "\n vertex: " + "(" + quadEquation.vertex().getX() + "," + quadEquation.vertex().getY() + ")" + "\n has x intercepts: " + quadEquation.discriminentBoolean() + "\n x intercept(s: " + "(" + quadEquation.xIntercepts().getX() + ",0.0) , (" + quadEquation.xIntercepts().getY() + ",0.0)" + "\n y intercept: " + "(0.0," + quadEquation.yIntercept().getY() + ")" + "\n symmetrical point to the y intercept: " + "(" + quadEquation.symmetricalPoint().getX() + "," + quadEquation.symmetricalPoint().getY() + ")");
				quad(quadEquation);
				break;
			case 9: // cancel
				main(null);
				break;

		}
	}

	public static void sI(slopeIntercept SIEquation)
	{
		JOptionPane Jay = new JOptionPane();
		String[] choices =
		{ "b", "c", "x intercept", "y intercept", "to standard form", "to point slope form", "summary", "cancel" };
		int choice = JOptionPane.showOptionDialog(Jay, "What do you want to do?", "Slope-Intercept Sub Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, "cancel");
		switch (choice)
		{
			case 0:
				JOptionPane.showMessageDialog(Jay, SIEquation.getB());
				sI(SIEquation);
				break;
			case 1:
				JOptionPane.showMessageDialog(Jay, SIEquation.getC());
				sI(SIEquation);
				break;
			case 2:
				JOptionPane.showMessageDialog(Jay, "(" + SIEquation.xIntercept().getX() + ",0.0)");
				sI(SIEquation);
				break;
			case 3:
				JOptionPane.showMessageDialog(Jay, "(0.0," + SIEquation.yIntercept().getY() + ")");
				sI(SIEquation);
				break;
			case 4:
				JOptionPane.showMessageDialog(Jay, "" + SIEquation.toStandard().getD() + "Y + " + SIEquation.toStandard().getE() + "X = " + SIEquation.toStandard().getf());
				s(SIEquation.toStandard());
				break;
			case 5:
				String x1s = (String) JOptionPane.showInputDialog("what is the x value of your point:");
				double x11 = checkDouble(x1s, "what is the x value of your point:");
				String y1s = (String) JOptionPane.showInputDialog("what is the y value of your point:");
				double y11 = checkDouble(y1s, "what is the y value of your point:");
				Point userPoint1 = new Point(x11, y11);
				JOptionPane.showMessageDialog(Jay, "Y - " + SIEquation.toPointSlope(userPoint1).getPoint().getY() + " = " + SIEquation.toPointSlope(userPoint1).getSlope() + "(X - " + SIEquation.toPointSlope(userPoint1).getPoint().getX() + ")");
				pS(SIEquation.toPointSlope(userPoint1));
				break;
			case 6: // all
				String x2s = (String) JOptionPane.showInputDialog("what is the x value of your point:");
				double x21 = checkDouble(x2s, "what is the x value of your point:");
				String y2s = (String) JOptionPane.showInputDialog("what is the y value of your point:");
				double y21 = checkDouble(y2s, "what is the y value of your point:");
				Point userPoint2 = new Point(x21, y21);
				JOptionPane.showMessageDialog(Jay, "Y = bx + c\nb = " + SIEquation.getB() + "\nc = " + SIEquation.getC() + "\nx intercept: " + "(" + SIEquation.xIntercept().getX() + ",0.0)" + "\ny intercept: " + "(0.0," + SIEquation.yIntercept().getY() + ")" + "\nstandard form: " + SIEquation.toStandard().getD() + "Y + " + SIEquation.toStandard().getE() + "X = " + SIEquation.toStandard().getf() + "\npoint slope form: " + "Y - " + SIEquation.toPointSlope(userPoint2).getPoint().getY() + " = " + SIEquation.toPointSlope(userPoint2).getSlope() + "(X - " + SIEquation.toPointSlope(userPoint2).getPoint().getX() + ")");
				sI(SIEquation);
				break;
			case 7: // cancel
				main(null);
				break;
		}
	}

	public static void s(standard SEquation)
	{
		JOptionPane Jay = new JOptionPane();
		String[] choices =
		{ "d", "e", "f", "to slope intercept form", "to point slope form", "summary", "cancel" };
		int choice = JOptionPane.showOptionDialog(Jay, "What do you want to do?", "Standard Sub Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, "cancel");
		switch (choice)
		{
			case 0:
				JOptionPane.showMessageDialog(Jay, SEquation.getD());
				s(SEquation);
				break;
			case 1:
				JOptionPane.showMessageDialog(Jay, SEquation.getE());
				s(SEquation);
				break;
			case 2:
				JOptionPane.showMessageDialog(Jay, SEquation.getf());
				s(SEquation);
				break;
			case 3:
				JOptionPane.showMessageDialog(Jay, "Y = " + SEquation.toSlopeIntercept().getB() + "X + " + SEquation.toSlopeIntercept().getC());
				sI(SEquation.toSlopeIntercept());
				break;
			case 4:
				String x1s = (String) JOptionPane.showInputDialog("what is the x value of your point:");
				double x11 = checkDouble(x1s, "what is the x value of your point:");
				String y1s = (String) JOptionPane.showInputDialog("what is the y value of your point:");
				double y11 = checkDouble(y1s, "what is the y value of your point:");
				Point userPoint1 = new Point(x11, y11);
				JOptionPane.showMessageDialog(Jay, "Y - " + SEquation.toPointSlope(userPoint1).getPoint().getY() + " = " + SEquation.toPointSlope(userPoint1).getSlope() + "(X - " + SEquation.toPointSlope(userPoint1).getPoint().getX() + ")");
				pS(SEquation.toPointSlope(userPoint1));
				break;
			case 5: // all
				String x2s = (String) JOptionPane.showInputDialog("what is the x value of your point:");
				double x21 = checkDouble(x2s, "what is the x value of your point:");
				String y2s = (String) JOptionPane.showInputDialog("what is the y value of your point:");
				double y21 = checkDouble(y2s, "what is the y value of your point:");
				Point userPoint2 = new Point(x21, y21);
				JOptionPane.showMessageDialog(Jay, "Dy + Ex = f\nD = " + SEquation.getD() + "\nE = " + SEquation.getE() + "\nf = " + SEquation.getf() + "\nto slope intercept form: " + "Y = " + SEquation.toSlopeIntercept().getB() + "X + " + SEquation.toSlopeIntercept().getC() + "\nto point slope form: " + "Y - " + SEquation.toPointSlope(userPoint2).getPoint().getY() + " = " + SEquation.toPointSlope(userPoint2).getSlope() + "(X - " + SEquation.toPointSlope(userPoint2).getPoint().getX() + ")");
				s(SEquation);
				break;
			case 6: // cancel
				main(null);
				break;
		}
	}

	public static void pS(pointSlope PSEquation)
	{
		JOptionPane Jay = new JOptionPane();
		String[] choices =
		{ "slope", "point", "to slope intercept form", "to standard form", "summary", "cancel" };
		int choice = JOptionPane.showOptionDialog(Jay, "What do you want to do?", "Point-Slope Sub Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, "cancel");
		switch (choice)
		{
			case 0:
				JOptionPane.showMessageDialog(Jay, PSEquation.getSlope());
				pS(PSEquation);
				break;
			case 1:
				JOptionPane.showMessageDialog(Jay, "(" + PSEquation.getPoint().getX() + "," + PSEquation.getPoint().getY() + ")");
				pS(PSEquation);
				break;
			case 2:
				JOptionPane.showMessageDialog(Jay, "Y = " + PSEquation.toSlopeIntercept().getB() + "X + " + PSEquation.toSlopeIntercept().getC());
				sI(PSEquation.toSlopeIntercept());
				break;
			case 3:
				JOptionPane.showMessageDialog(Jay, "" + PSEquation.toStandard().getD() + "Y + " + PSEquation.toStandard().getE() + "X = " + PSEquation.toStandard().getf());
				s(PSEquation.toStandard());
				break;
			case 4: // all
				JOptionPane.showMessageDialog(Jay, "Y-Y1 = m(X - X1)\nm = " + PSEquation.getSlope() + "\npoint: " + "(" + PSEquation.getPoint().getX() + "," + PSEquation.getPoint().getY() + ")" + "\nslope intercept form: " + "Y = " + PSEquation.toSlopeIntercept().getB() + "X + " + PSEquation.toSlopeIntercept().getC() + "\nstandard form: " + PSEquation.toStandard().getD() + "Y + " + PSEquation.toStandard().getE() + "X = " + PSEquation.toStandard().getf());
				pS(PSEquation);
				break;
			case 5: // cancel
				main(null);
				break;
		}
	}

	public static double checkDouble(String x, String varName)
	{
		double xans = 0;
		JOptionPane Jay = new JOptionPane();
		try
		{
			xans = Double.parseDouble(x);
		}
		catch (Exception q)
		{
			JOptionPane.showMessageDialog(Jay, "Error!!\n\nInputed Value is not in acceptable format!\nPlease enter a Double.");
			x = (String) JOptionPane.showInputDialog(varName);
			checkDouble(x, varName);
		}
		return xans;
	}

	public static int checkInt(String x, String varName)
	{
		int xans = 0;
		JOptionPane Jay = new JOptionPane();
		try
		{
			xans = Integer.parseInt(x);
		}
		catch (Exception q)
		{
			JOptionPane.showMessageDialog(Jay, "Error!!\n\nInputed Value is not in acceptable format!\nPlease enter an Integer.");
			x = (String) JOptionPane.showInputDialog(varName);
			checkInt(x, varName);
		}
		return xans;
	}

}
