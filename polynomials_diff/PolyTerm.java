package polynomials_diff;

public class PolyTerm
{
	private double coef;
	private int exp;

	public PolyTerm(double coeficient, int exponent)
	{
		coef = coeficient;
		exp = exponent;
	}

	public int getExp()
	{
		return exp;
	}

	public double getCoef()
	{
		return coef;
	}

	public boolean like(PolyTerm other)
	{
		return other.getExp() == exp;
	}

	public String toString()
	{
		if(coef == 0) return "0";
		if (exp == 0) return "" + Math.abs(coef);
		if (exp == 1) return "" + Math.abs(coef) + "x";
		return "" + Math.abs(coef) + "x^" + exp;
	}

	public PolyTerm add(PolyTerm other)
	{
		if (!like(other)) throw new IllegalArgumentException();
		return new PolyTerm(coef + other.getCoef(), exp);
	}

	public int compareExp(PolyTerm other)
	{
		if (exp > other.getExp())
			return -1;
		else if (exp < other.getExp())
			return 1;
		else return 0;
	}

	public boolean isNegative()
	{
		return coef < 0;
	}

	public PolyTerm multiply(PolyTerm other)
	{
		return new PolyTerm(coef * other.getCoef(), exp + other.getExp());
	}

	public PolyTerm derive()
	{
		return new PolyTerm(coef * exp, exp - 1);
	}
}