public class BST<T extends Comparable<T> >
{
	private class BSTNode
	{
		T data;
		private BSTNode left;
		private BSTNode right;
		BSTNode(T data)
		{
			this.data = data;
			left = null;
			right = null;
		}
	}
	
	private BSTNode root;
	private int level=0;

	public BST()
	{
		root = null;
	}
	
	public T find(T target)
	{
		BSTNode current = root;
		
		while (true)
		{
			if (current == null)
				return null;
			else if (current.data.compareTo(target) == 0)
				return current.data;
			if (target.compareTo(current.data) > 0)
			{
				current = current.right;
			}
			else  // target.compareTo(current.data) < 0
			{
				current = current.left;
			}
		}
	}//find()
	
	public void insert(T item)
	{
		BSTNode newnode = new BSTNode(item);
		
		if (root == null)
		{
			root = newnode;
			return;
		}
		
		BSTNode current = root;
		
		while (true)
		{
			if (item.compareTo(current.data) == 0)
			{
				/// Already present!
				break;
			}
			else if (item.compareTo(current.data) > 0)
			{
				if (current.right == null)
				{
					current.right = newnode;
					break;
				}
				else
					current = current.right;
			}
			else // target.compareTo(current.data) < 0
			{
				if (current.left == null)
				{
					current.left = newnode;
					break;
				}
				else
					current = current.left;
			}
		}//while(true)
	}//insert()
	
	public boolean delete(T target)
	{
		T newVertex = null;
		BSTNode previous = null;
		boolean result = false;
		String previousDir = "";
		BSTNode rightSubtreeMin = null;
		
		BSTNode current = root;
		
		while (true)
		{
			if (current == null)
			{
				result = false;
			}
			else if (current.data.compareTo(target) == 0)
			{//FOUND node
				//Node has 0 children
				if(current.left==null && current.right==null)
				{
					System.out.println("Delete():0 children. Deleted:"+current.data);
					if(previousDir=="R")
						previous.right = null;
					else
						previous.left = null;
					
					result = true;
					break;
				} else//Node has 1 child
					if( (current.left==null && current.right!=null) || (current.left!=null && current.right==null) ) 
					{
						System.out.println("Delete():1 child. Deleted:"+current.data);
						if(current.right!=null)
						{
							System.out.println("Right node exists: previousDir:"+previousDir);
							if(previousDir.equals("R"))
								previous.right = current.right;
							else
								previous.left = current.right;
						} else
						{
							System.out.println("Left node exists: previousDir:"+previousDir);
							if(previousDir.equals("R"))
								previous.right = current.left;
							else
								previous.left = current.left;
						}
						result = true;
						break;
					} else//Node has 2 children
					{
						System.out.println("Delete():2 child. Deleted:"+current.data);
						
						BSTNode prev2children = current;
						rightSubtreeMin = current.right;
						while(true)
						{
							if(rightSubtreeMin.left != null)
							{
								prev2children = rightSubtreeMin;
								rightSubtreeMin = rightSubtreeMin.left;
							}
							else
							{
								break;
							}
							
						}
						current.data = rightSubtreeMin.data;
						rightSubtreeMin.data = null;
						prev2children.left = null;
						
						result=true;
						break;
					}
			}
			if (target.compareTo(current.data) > 0)
			{
				previous = current;
				previousDir = "R";
				current = current.right;
			}
			else  // target.compareTo(current.data) < 0
			{
				previous = current;
				previousDir = "L";
				current = current.left;
			}
		}
		
		return result;
	}//delete()
	
	public void printTree()
	{
		traverseTree(root,0);
	}
	
	public void traverseTree(BSTNode node, int lvl)
	{
		if (node == null)
		{
			//System.out.println("Level:"+lvl+"  No child");
			return;
		}
		else
		{
			System.out.println("Level:"+lvl+"  Node: "+node.data);
			traverseTree(node.left,lvl+1);
			traverseTree(node.right,lvl+1);
		}

	}//traverseTree
}//public class BST
