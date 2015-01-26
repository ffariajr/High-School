package myLinkedList_diff;

import java.util.NoSuchElementException;

public class MyLinkedList<E>
{
	private ListNode<E> first, last;
	private int size = 0;

	public MyLinkedList()
	{
		first = null;
		last = null;
	}

	public boolean add(E o)
	{
		ListNode<E> temp = new ListNode<E>(o);
		if (size == 0)
		{
			first = temp;
		}
		if (size != 0)
		{
			last.next = temp;
			temp.prev = last;
		}
		last = temp;
		size++;
		return true;
	}

	public void add(int index, E element)
	{
		ListNode<E> temp = new ListNode<E>(element);
		if (index == size || index > size)
		{
			add(element);
			return;
		}
		ListNode<E> hold = getNode(index);
		temp.next = hold;
		temp.prev = hold.prev;
		hold.prev.next = temp;
		hold.prev = temp;
		size++;
	}

	private ListNode<E> getNode(int index)
	{
		if (index == size)
		{
			return last;
		}
		ListNode<E> hold = first;
		for (int x = 0; x <= size; x++)
		{
			if (index == x) return hold;
			hold = hold.next;
		}
		throw new IllegalArgumentException();
	}

	public void addFirst(E o)
	{
		ListNode<E> temp = new ListNode<E>(o);
		first.prev = temp;
		temp.next = first;
		first = temp;
		size++;
	}

	public void addLast(E o)
	{
		add(o);
	}

	public E getFirst()
	{
		return first.value;
	}

	public E getLast()
	{
		return last.value;
	}

	public E removeFirst()
	{
		E result = first.value;
		first = first.next;
		if(first != null)
			first.prev = null;
		size--;
		return result;
	}

	public E removeLast()
	{
		E result = last.value;
		last = last.prev;
		if(last != null)
			last.next = null;
		size--;
		if(size == 0)
			first = null;
		return result;
	}

	public void clear()
	{
		first = null;
		last = null;
		size = 0;
	}

	public E get(int index)
	{
		ListNode<E> hold = first;
		for (int x = 0; x < size; x++)
		{
			if (index == x)
			{
				return hold.value;
			}
			hold = hold.next;
		}
		throw new IllegalArgumentException();
	}

	public boolean isEmpty()
	{
		return size == 0;
	}

	public E remove()
	{
		return removeLast();
	}

	public E remove(int index)
	{
		if(index == size-1 || size == 1)
			return remove();
		if(index == 0)
			return removeFirst();
		ListNode<E> hold = getNode(index);
		E result = hold.value;
		hold.next.prev = hold.prev;
		hold.prev.next = hold.next;
		size--;
		return result;
	}

	public E set(int index, E element)
	{
		ListNode<E> hold = getNode(index);
		E result = hold.value;
		hold.value = element;
		return result;
	}

	public int size()
	{
		return size;
	}

	public String toString()
	{
		String result = "";
		ListNode<E> pos = first;
		for (int x = 0; x < size; x++)
		{
			result = result + pos.value.toString() + ", ";
			pos = pos.next;
		}
		return result;
	}

	private class ListNode<E>
	{
		public E value;
		public ListNode<E> prev;
		public ListNode<E> next;

		public ListNode(E initValue)
		{
			value = initValue;
			prev = null;
			next = null;
		}

		private ListNode(E initValue, ListNode<E> initPrev, ListNode<E> initNext)
		{
			value = initValue;
			prev = initPrev;
			next = initNext;
		}

		private void setPrev(ListNode<E> prev)
		{
			this.prev = prev;
		}

		private void setNext(ListNode<E> next)
		{
			this.next = next;
		}

		private void setValue(E value)
		{
			this.value = value;
		}
	}
}
