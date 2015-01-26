package trees_diff;

import java.util.TreeMap;

public class TreeCalc
{
	protected static Operator[] operators; 
	private Node root; 
	private String exp; 
	private TreeMap<String, Double> variables = new TreeMap<String, Double>(); 

	public TreeCalc()
	{
		init();
	}

	public TreeCalc(String s)
	{
		init();
		setExpression(s);
	}

	private void init()
	{
		if (operators == null) initializeOperators();
	}

	public void addVariable(String v, double val)
	{
		addVariable(v, new Double(val));
	}

	public void addVariable(String v, Double val)
	{
		variables.put(v, val);
	}

	public void setExpression(String s)
	{
		exp = s;
	}

	public void reset()
	{
		root = null;
		exp = null;
		variables = new TreeMap<String, Double>();
	}

	private boolean checkValidity(String val)
	{

		if (!val.contains(" ")) return false;

		for (int i = 0; i < val.length() - 1; i++)
		{
			if (Character.isLetter(val.charAt(i))) return false;

			if (val.charAt(i) == ' ') if (val.charAt(i + 1) == ' ')
				return false;

			else if (Character.isDigit(val.charAt(i))) if (!Character.isDigit(val.charAt(i + 1)) && val.charAt(i + 1) != ' ')
				return false;

			else if (val.charAt(i) != ' ' && !Character.isDigit(val.charAt(i))) if (val.charAt(i + 1) != ' ' || Character.isDigit(val.charAt(i + 1))) return false;

		}

		return true;
	}

	public Double evaluate()
	{
		if (exp == null || !checkValidity(exp)) return null;

		try
		{
			root = new Node(exp);
			return evaluate(root);
		}

		catch (Exception e)
		{
			throw new IllegalArgumentException();
		}
	}

	public Double evaluate(String exp)
	{
		setExpression(exp);
		return evaluate();

	}

	private Double evaluate(Node n)
	{
		if (n.hasOperator() && n.hasChild())
		{
			if (n.getOperator().getType() == 1)
				n.setValue(evaluateexpession(n.getOperator(), evaluate(n.getLeft()), null));

			else if (n.getOperator().getType() == 2) n.setValue(evaluateexpession(n.getOperator(), evaluate(n.getLeft()), evaluate(n.getRight())));
		}

		return n.getValue();
	}

	private Double evaluateexpession(Operator o, Double f1, Double f2)
	{
		String op = o.getOperatorValue();
		Double res = null;

		if ("+".equals(op))
			res = new Double(f1.doubleValue() + f2.doubleValue());

		else if ("-".equals(op))
			res = new Double(f1.doubleValue() - f2.doubleValue());

		else if ("*".equals(op))
			res = new Double(f1.doubleValue() * f2.doubleValue());

		else if ("/".equals(op))
			res = new Double(f1.doubleValue() / f2.doubleValue());

		else if ("^".equals(op))
			res = new Double(Math.pow(f1.doubleValue(), f2.doubleValue()));

		else if ("neg".equals(op))
			res = new Double(-f1.doubleValue());

		return res;
	}

	private void initializeOperators()
	{
		operators = new Operator[6];
		operators[0] = new Operator("+", 2, 0);
		operators[1] = new Operator("-", 2, 0);
		operators[2] = new Operator("*", 2, 10);
		operators[3] = new Operator("/", 2, 10);
		operators[4] = new Operator("^", 2, 10);
		operators[5] = new Operator("neg", 1, 20);
	}

	public Double getVariable(String s)
	{
		return variables.get(s);
	}

	private Double getDouble(String s)
	{
		if (s == null) return null;

		Double res = null;
		try
		{
			res = new Double(Double.parseDouble(s));
		}
		catch (Exception e)
		{
			return getVariable(s);
		}

		return res;
	}

	protected Operator[] getOperators()
	{
		return operators;
	}

	private class Operator
	{
		private String op;
		private int type, priority;

		public Operator(String o, int t, int p)
		{
			op = o;
			type = t;
			priority = p;
		}

		public String getOperatorValue()
		{
			return op;
		}

		public int getType()
		{
			return type;
		}

		public int getPriority()
		{
			return priority;
		}
	}

	private class Node
	{
		public String nString = null;
		public Operator nOperator = null;
		public Node nLeft = null, nRight = null;
		public int nLevel = 0;
		public Double nValue = null;

		public Node(String s) throws Exception
		{
			init(null, s, 0);
		}

		public Node(Node parent, String s, int level) throws IllegalArgumentException
		{
			init(parent, s, level);
		}

		private void init(Node parent, String s, int level) throws IllegalArgumentException
		{
			s = removeIllegalCharacters(s);
			s = removeBrackets(s);
			s = addZero(s);

			if (checkBrackets(s) != 0) throw new IllegalArgumentException("Wrong number of brackets in [" + s + "]");

			nString = s;
			nValue = getDouble(s);
			nLevel = level;

			int sLength = s.length();
			int inBrackets = 0;
			int startOperator = 0;

			for (int i = 0; i < sLength; i++)
			{
				if (s.charAt(i) == '(')
					inBrackets++;

				else if (s.charAt(i) == ')')
					inBrackets--;

				else
				{
					if (inBrackets == 0)
					{
						Operator o = getOperator(nString, i);
						if (o != null)
						{
							if (nOperator == null || nOperator.getPriority() >= o.getPriority())
							{
								nOperator = o;
								startOperator = i;
							}
						}
					}
				}
			}

			if (nOperator != null)
			{
				if (startOperator == 0 && nOperator.getType() == 1)
				{
					if (checkBrackets(s.substring(nOperator.getOperatorValue().length())) == 0)
					{
						nLeft = new Node(this, s.substring(nOperator.getOperatorValue().length()), nLevel + 1);
						nRight = null;

						return;
					}

					else throw new IllegalArgumentException("error during parsing- " + "missing brackets in [" + s + "]");
				}

				else if (startOperator > 0 && nOperator.getType() == 2)
				{
					nLeft = new Node(this, s.substring(0, startOperator), nLevel + 1);
					nRight = new Node(this, s.substring(startOperator + nOperator.getOperatorValue().length()), nLevel + 1);
				}
			}
		}

		private Operator getOperator(String s, int start)
		{
			Operator[] operators = getOperators();
			String temp = s.substring(start);
			temp = getNextWord(temp);

			for (int i = 0; i < operators.length; i++)
			{
				if (temp.startsWith(operators[i].getOperatorValue())) return operators[i];
			}

			return null;
		}

		private String getNextWord(String s)
		{
			int sLength = s.length();

			for (int i = 1; i < sLength; i++)
			{
				char c = s.charAt(i);
				if ((c > 'z' || c < 'a') && (c > '9' || c < '0')) return s.substring(0, i);
			}

			return s;
		}

		private int checkBrackets(String s)
		{
			int sLength = s.length();
			int inBracket = 0;

			for (int i = 0; i < sLength; i++)
			{
				if (s.charAt(i) == '(' && inBracket >= 0)
					inBracket++;

				else if (s.charAt(i) == ')') inBracket--;
			}

			return inBracket;
		}

		protected String addZero(String s)
		{

			for (int j = 0; j < operators.length; j++)
				s = s.replace(operators[j].getOperatorValue() + "-", operators[j].getOperatorValue() + "neg");

			if (s.startsWith("+") || s.startsWith("-"))
			{
				int sLength = s.length();
				for (int i = 0; i < sLength; i++)
				{
					if (getOperator(s, i) != null) return "0" + s;

				}
			}

			return s;
		}

		protected boolean hasChild()
		{
			return (nLeft != null || nRight != null);
		}

		protected boolean hasOperator()
		{
			return (nOperator != null);
		}

		protected boolean hasLeft()
		{
			return (nLeft != null);
		}

		protected Node getLeft()
		{
			return nLeft;
		}

		protected boolean hasRight()
		{
			return (nRight != null);
		}

		protected Node getRight()
		{
			return nRight;
		}

		protected Operator getOperator()
		{
			return nOperator;
		}

		protected Double getValue()
		{
			return nValue;
		}

		protected void setValue(Double f)
		{
			nValue = f;
		}

		protected String getString()
		{
			return nString;
		}

		public String removeBrackets(String s)
		{
			String res = s;

			if (s.length() > 2 && res.startsWith("(") && res.endsWith(")") && checkBrackets(s.substring(1, s.length() - 1)) == 0)
			{
				res = res.substring(1, res.length() - 1);
			}

			if (res != s)
				return removeBrackets(res);

			else return res;
		}

		public String removeIllegalCharacters(String s)
		{
			return s.replace(" ", "");
		}
	}
}