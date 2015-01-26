package polynomials_diff;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class Polynomial
{
	private LinkedList<PolyTerm> terms;
	private ListIterator itr;

	public Polynomial(double[] allTerms)
	{
		terms = new LinkedList<PolyTerm>();
		itr = terms.listIterator();
		for (int x = 0; x < allTerms.length; x++)
		{
			if (allTerms[x] != 0)
			{
				itr.add(new PolyTerm(allTerms[x], x));
			}
		}
	}

	private Polynomial(LinkedList<PolyTerm> list)
	{
		terms = list;
		itr = terms.listIterator();
	}

	private LinkedList<PolyTerm> getList()
	{
		return terms;
	}

	public int size()
	{
		return terms.size();
	}

	public String toString()
	{
		String result = "";
		itr = terms.listIterator(terms.size());
		while(true)
		{
			PolyTerm temp;
			try
			{
				temp = (PolyTerm) itr.previous();
			}
			catch(NoSuchElementException e)
			{
				break;
			}
			
			if(temp.toString() == "0")
				result = result;
			else if (result.length() == 0)
				if(!temp.isNegative())
					result = temp.toString();
				else
					result = "- " + temp.toString();
			else if (temp.isNegative())
				result = result + " - " + temp.toString();
			else result = result + " + " + temp.toString();
		}
		return result;
	}

	public Polynomial add(Polynomial other)
	{
		LinkedList<PolyTerm> holder = new LinkedList<PolyTerm>();
		ListIterator oitr = other.getList().listIterator();
		itr = terms.listIterator();
		while(true)
		{
			PolyTerm one = itr.hasNext()?((PolyTerm) itr.next()):null;
			PolyTerm two = oitr.hasNext()?((PolyTerm) oitr.next()):null;
			
			if (two == null && one == null)
				break;
			else if (two == null && one != null)
				holder.add(one);
			else if (one == null && two != null)
				holder.add(two);
			else if (one.compareExp(two) < 0)
			{
				holder.add(two);
				itr.previous();
			}
			else if (one.compareExp(two) == 0)
				holder.add(one.add(two));
			else 
			{
				holder.add(one);
				oitr.previous();
			}
		}
		return new Polynomial(holder);
	}

	public Polynomial multiply(Polynomial other)
	{
		LinkedList<PolyTerm> holder = new LinkedList<PolyTerm>();
		ListIterator oitr = other.getList().listIterator();
		itr = terms.listIterator();
		for (int x = 0; x < terms.size(); x++)
		{
			PolyTerm one = (PolyTerm) itr.next();
			oitr = other.getList().listIterator();
			for (int y = 0; y < other.size(); y++)
			{
				PolyTerm two = (PolyTerm) oitr.next();
				holder.add(one.multiply(two));
			}
		}
			
		Polynomial result = new Polynomial(holder);
		result.process();
		return result;
	}

	public Polynomial derivative()
	{
		LinkedList<PolyTerm> holder = new LinkedList<PolyTerm>();
		itr = terms.listIterator();
		for (int x = 0; x < terms.size(); x++)
		{
			PolyTerm temp = ((PolyTerm) itr.next());
			holder.add(temp.derive());
		}
		return new Polynomial(holder);
	}
	
	public void process()
	{
		
		for(int x = 0; x< terms.size();x++)
		{
			int place = x;
			PolyTerm small = terms.get(x);
			for(int z = x; z<terms.size();z++)
			{
				PolyTerm temp = terms.get(z);
				if(small.compareExp(temp) < 0)
				{
					small = temp;
					place = z;
				}	
			}
			swap(place,x);
		}
		
		int size = terms.size();
		for(int x = 1; x < size;x++)
		{
			PolyTerm one = terms.get(x);
			PolyTerm two = terms.get(x-1);
			
			if(one.compareExp(two) == 0)
			{
				terms.set(x, one.add(two));
				terms.remove(two);
				size--;
			}
		}
			
	}
	
	private void swap(int a, int b)
	{
		PolyTerm hold = terms.get(a);
		terms.set(a, terms.get(b));
		terms.set(b, hold);
	}
}