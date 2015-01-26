package myArrayList;

public class MineArrayList<E>
{
	private Object[] a;
	private int size;

	public MineArrayList()
	{
		this(10);
	}

	public MineArrayList(int initialCapacity)
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
		if (index >= size) throw new IndexOutOfBoundsException();
		E temp = (E) a[index];
		a[index] = element;
		return temp;
	}

	public boolean contains(Object elem)
	{
		for (int x = 0; x<size;x++)
		{
			if (elem.equals(a[x])) return true;
		}
		return false;
	}

	public void trimToSize()
	{
		Object[] temp = new Object[size];
		System.arraycopy(a, 0, temp, 0, size);
		a = temp;
	}

	public void add(E elem)
	{
		this.add(size, elem);
	}

	public void add(int index, E element)
	{
		if (index < 0 || index > size()) throw new IndexOutOfBoundsException();
		if(a.length == size)
		{
			Object[] temp = new Object[size*2];
			System.arraycopy(a, 0, temp, 0, size);
			a = temp;
		}
		Object[] tempcopy = a;
		System.arraycopy(tempcopy, index, a, index+1, size-index);
		a[index] = element;
		size++;
	}

	@SuppressWarnings("unchecked")
	public E remove(int index)
	{
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		E temp = (E) a[index];
		Object[] tempcopy = a;
		System.arraycopy(tempcopy, index, a, index-1, size-index);
		size--;
		return temp;
	}

	public boolean remove(Object elem)
	{
		if(!contains(elem))
			return false;
		for(int x = 0; x < size;x++)
			if(a[x].equals(elem))
				this.remove(x);
		return true;
	}
}
