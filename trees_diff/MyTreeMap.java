package trees_diff;

import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

/**
 * An academic map that does not support null keys
 * or values. Implements a subset of the methods
 * of the java.util.TreeMap class and several
 * other methods. Methods behave as described in
 * the documentation for java.util.TreeMap with
 * the exception of guaranteed O(log n) runtime
 * for basic operations. Since this implementation
 * does not ensure that the tree is balanced, the
 * run time is potentially O(n).
 */
public class MyTreeMap<K extends Comparable<K>, V>
{	
	private TreeNode<Map.Entry<K, V>> root;
	private int size; // number of mappings
	private V temporary; // to hold the values to
						// be returned when
						// using recursive
						// put and remove

	public MyTreeMap()
	{
		root = null;
		size = 0;
		temporary = null;
	}

	public int size()
	{
		return size;
	}

	public boolean containsKey(K key)
	{
		if(key == null)
			throw new IllegalArgumentException("Null keys or values not allowed");
		return getNode(key) != null;
	}

	public V put(K key, V value)
	{
		if(key == null || value == null)
			throw new IllegalArgumentException("Null keys or values not allowed");
		root = put(new Entry<K, V>(key, value), root);
		if(temporary == null)
			size++;
		return temporary;
	}

	public V get(K key)
	{
		return getNode(key).getValue().getValue();
	}

	public V remove(K key)
	{
		if(key == null)
			throw new IllegalArgumentException("Null keys not allowed");
		root = remove(key, root);
		if(temporary != null)
			size--;
		return temporary;
	}

	public Set<K> keySet()
	{
		Set<K> set = new TreeSet<K>();
		keySet(root, set);
		return set;
	}

	/**
	 * @return the map's keys based on a preorder
	 *         traversal of the tree
	 */
	public String keysPreorder()
	{
		return keysPreorder(root);
	}

	private String keysPreorder(TreeNode<Map.Entry<K, V>> node)
	{
		if (node == null) return "";
		String ans = node.getValue().getKey().toString();
		String left = keysPreorder(node.getLeft());
		String right = keysPreorder(node.getRight());
		if (!left.equals("")) ans += ", " + left;
		if (!right.equals("")) ans += ", " + right;
		return ans;
	}

	/**
	 * @return the map's keys based on an inorder
	 *         traversal of the tree
	 */
	public String keysInorder()
	{
		return keysInorder(root);
	}

	private String keysInorder(TreeNode<Map.Entry<K, V>> node)
	{
		if(node == null)
			return "";
		
		String ans = node.getValue().getKey().toString();
		String left = keysInorder(node.getLeft());
		String right = keysInorder(node.getRight());
		
		if (!left.equals(""))
			ans = left + ", " + ans;
		if (!right.equals(""))
			ans += ", " + right;
		
		return ans;
	}

	/**
	 * @return the TreeNode (or null) to which
	 *         the pointer previously pointing to
	 *         node should point
	 */
	private TreeNode<Map.Entry<K, V>> put(Map.Entry<K, V> entry, TreeNode<Map.Entry<K, V>> node)
	{
		if (node == null)
		{
			temporary = null;
			return new TreeNode<Map.Entry<K, V>>(entry);
		}

		if (node.getValue().getKey().equals(entry.getKey()))
		{
			temporary = node.getValue().getValue();
			node.setValue(entry);
			return node;
		}

		if (node.getValue().getKey().compareTo(entry.getKey()) < 0)
		{
			node.setRight(put(entry, node.getRight()));
			return node;
		}
		else
		{
			node.setLeft(put(entry, node.getLeft()));
			return node;
		}
	}

	/**
	 * @return the TreeNode (or null) to which
	 *         the pointer previously pointing to
	 *         node should point
	 */
	private TreeNode<Map.Entry<K, V>> remove(K key, TreeNode<Map.Entry<K, V>> node)
	{
		if (node == null)
		{
			temporary = null;
			return null;
		}

		if (node.getValue().getKey().equals(key))
		{
			temporary = node.getValue().getValue();
			if(node.getLeft() == null)
				return node.getRight();
			if(node.getRight() == null)
				return node.getLeft();
			TreeNode<Map.Entry<K, V>> temp = node.getRight();
			while(temp.getLeft() != null)
				temp = temp.getLeft();
			Map.Entry<K, V> hold = temp.getValue();
			temp.setValue(node.getValue());
			node.setValue(hold);
			
			node = remove(key, node);
			
			return node;
		}

		if (node.getValue().getKey().compareTo(key) < 0)
		{
			node.setRight(remove(key, node.getRight()));
			return node;
		}
		else
		{
			node.setLeft(remove(key, node.getLeft()));
			return node;
		}
	}

	/**
	 * Appends the keys from the nodes in the
	 * tree rooted at node to specified set.
	 */
	private void keySet(TreeNode<Map.Entry<K, V>> node, Set<K> keys)
	{
		if(node == null)
			return;
		keys.add(node.getValue().getKey());
		keySet(node.getLeft(), keys);
		keySet(node.getRight(), keys);
	}

	/**
	 * @return the node associated with the
	 *         specified key, or null if the key
	 *         is not in the map.
	 */
	private TreeNode<Map.Entry<K, V>> getNode(K key)
	{
		return getNode(key, root);
	}

	/**
	 * @return the node in the tree rooted at
	 *         node associated with the specified
	 *         key, or null if the key is not in
	 *         the tree rooted at node.
	 */
	private TreeNode<Map.Entry<K, V>> getNode(K key, TreeNode<Map.Entry<K, V>> node)
	{
		if(node == null)
			return null;
		if(node.getValue().getKey().equals(key))
			return node;
		if(node.getValue().getKey().compareTo(key) > 0)
			return getNode(key, node.getLeft());
		else
			return getNode(key, node.getRight());
	}

	private class Entry<K extends Comparable<K>, V> implements Map.Entry<K, V>
	{
		private K key;
		private V value;

		public Entry(K Key, V Value)
		{
			key = Key;
			value = Value;
		}

		public K getKey()
		{
			return key;
		}

		public V getValue()
		{
			return value;
		}

		public V setValue(V Value)
		{
			V old = value;
			value = Value;
			return old;
		}
	}
}
