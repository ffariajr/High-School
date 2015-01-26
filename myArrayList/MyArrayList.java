package myArrayList;

import java.util.Arrays;


public class MyArrayList<E>
{
	private Object[] a;
	private int size;

	public MyArrayList()
	{
		this(10);
	}

	public MyArrayList(int initialCapacity)
	{
		if (initialCapacity < 0) throw new IllegalArgumentException();
		a = new Object[initialCapacity];
	}

	public int size()
	{
		return size;
	}

	@SuppressWarnings("unchecked")
	public E get(int index)
	{
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		return (E) a[index];
	}

	@SuppressWarnings("unchecked")
	public E set(int index, E element)
	{
		if (index >= size) throw new IndexOutOfBoundsException("Ileagal Index: " + index);
		E hold = (E) a[index];
		a[index] = element;
		return hold;
	}

	public boolean contains(Object elem)
	{
		if (first(elem) >= 0)
			return true;
		else return false;
	}

	public void trimToSize()
	{
		Object[] save = new Object[size];
		System.arraycopy(a, 0, save, 0, size);
		a = save;
	}

	public void add(E elem)
	{
		this.add(size, elem);
	}

	public void add(int index, E element)
	{
		if (index < 0 || index > size()) throw new IndexOutOfBoundsException("Ileagal Index: " + index);
		if (size >= a.length) incrementArray();
		System.arraycopy(a, index, a, index + 1, size - (index));
		a[index] = element;
		size = size + 1;
	}

	@SuppressWarnings("unchecked")
	public E remove(int index)
	{
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Ileagal Index: " + index);
		Object[] copy = new Object[a.length];
		E removeable = (E) a[index];
		System.arraycopy(a, 0, copy, 0, index);
		System.arraycopy(a, index + 1, copy, index, a.length - (index + 1));
		a = copy;
		size = size - 1;
		return removeable;
	}

	public boolean remove(Object elem)
	{
		if (contains(elem))
		{
			remove(first(elem));
			return true;
		}
		return false;
	}

	public String toString()
	{
		Object[] copy = new Object[size];
		System.arraycopy(a, 0, copy, 0, size);
		return Arrays.toString(copy);
	}

	private void incrementArray()
	{
		Object[] copy = new Object[a.length + 5];
		System.arraycopy(a, 0, copy, 0, a.length);
		a = copy;
	}

	public int first(Object o)
	{
		if (o == null)
		{
			for (int i = 0; i < size; i++)
				if (a[i] == null) return i;
		}
		else
		{
			for (int i = 0; i < size; i++)
				if (o.equals(a[i])) return i;
		}
		return -1;
	}
}
