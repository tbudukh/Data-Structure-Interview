#Lowest common Ancestor  
**problem statement :**  
Given a binary tree (not a binary search tree) and two values say n1 and n2, write a program to find the least common ancestor.  

**Attempt 1**  
Assumption is that either both the nodes are present in the tree or none of the nodes are present. There are two main cases :
* Node 1 is present on the left side of a node and node 2 is on the right side. In that case current node is the LCA.
* Both node 1 and node 2 are present on one side. Node 1 to the left/right of node 2, or vice versa. In that case the upper node is the LCA.

````*.java
Class Tree{
    Tree left, right;
    int data;
	
	Tree LCA(Tree t, int p, int q){
		if(t!=null && t.data!=p && t.data!=q){
			Tree left = LCA(t.left, p, q);
			Tree left = LCA(t.right, p, q);
			if(left==null && right==null)
				return null;
			else if(left!=null)
				return left;
			else if(right!=null)
				return right;
		}
		return t;
	}
}
````
worst case complexity : O(n)

**Attempt 2**  
Now what if we are not allowed to make the assumption we mnade in attempt 1 ? i.e. we don't know if only 1 node is present in the tree or both or none.
````*.java
Class Tree{
    Tree left, right;
    int data;
	boolean no1Found, no2Found;
	
	Tree LCA(Tree t, int p, int q){
		Tree lca = LCAUtil(t, p, q);
		if(lca!=null && no1Found && no2Found)
			return lca;
		retunr null;
	}
	
	private Tree LCAUtil(Tree t, int p, int q){
		if(t!=null){
			if(t.data==p){
				no1Found = true;
				if(no2Found)
					return t;
			}
			else if(t.data==q){
				no2Found = true;
				if(no1Found)
					return t;
			}
			
			Tree left = LCAUtil(t.left, p, q);
			Tree right = LCAUtil(t.right, p, q);
			
			if((t.data==p || t.data==q) || (t.left!=null && t.right!=null))
				return t;
			return (left!=null ? left : right);
		}
		return null;
	}
}
````
Worst case complexity : O(n)