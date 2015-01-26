package hashing;

public class IntTree
{
	private IntNode root;

	public IntTree()
	{
		root = null;
	}

	public int sum()
	{
		return sum(root);
	}

	private int sum(IntNode node)
	{
		if (node == null) return 0;
		return sum(node.left) + node.val + sum(node.right);
	}

	public boolean similar(IntTree other)
	{
		return root.val == other.root.val && similar(root.left, other.root.left) && similar(root.right, other.root.right);
	}

	private boolean similar(IntNode one, IntNode two)
	{
		if (one == null && two == null) return true;
		if (one == null) return false;
		if (two == null) return false;

		return similar(one.left, two.left) && one.val == two.val && similar(one.right, two.right);
	}

	public void add(int num)
	{
		root = add(num, root);
	}

	private IntNode add(int num, IntNode node)
	{
		if (node == null)
			return new IntNode(num);
		else if (num < node.val)
			node.left = add(num, node.left);
		else node.right = add(num, node.right);
		return node;
	}

	public void remove(int num)
	{
		root = remove(num, root);
	}

	private IntNode remove(int num, IntNode node)
	{
		if (node == null)
			return null;
		else if (num < node.val)
			return node.left = remove(num, node.left);
		else if (num > node.val)
			return node.right = remove(num, node.left);
		else
		{
			if (node.left == null)
				return node.right;
			else if (node.right == null)
				return node.left;
			else
			{
				IntNode temp = node.left;
				while (temp.right != null)
					temp = temp.right;

				int hold = temp.val;
				temp.val = node.val;
				node.val = hold;

				return node.right = remove(num, node.right);
			}
		}
	}

	public void visitPostOrder(Visitor v)
	{
		visit(root, v);
	}

	private void visit(IntNode node, Visitor v)
	{
		if (node == null)
			return;
		visit(node.left, v);
		visit(node.right, v);
		v.visit(node);
	}

	public String toString()
	{
		return toString(root);
	}

	private String toString(IntNode node)
	{
		if (node == null) return "";
		return toString(node.left) + node.toString() + toString(node.right);
	}

	private class IntNode
	{
		public int val;
		public IntNode left, right;

		public IntNode(int value)
		{
			this(value, null, null);
		}

		public IntNode(int value, IntNode left, IntNode right)
		{
			val = value;
			this.left = left;
			this.right = right;
		}

		public String toString()
		{
			return " " + val + " ";
		}
	}
	
	public interface Visitor
	{
		void visit(IntNode node);
	}
}