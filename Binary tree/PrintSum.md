# Print paths summing to a value  
  
**problem statement :**  
Given a binary tree in which each node contains a value. Design an algorithm to print all paths which sum to a given value. The path does not need to start at the root. Forked paths are not allowed.  
Forked path is defined as a path that contain a highest node, and then have two branches that go down.  

**Solution :**  
One way to think about the solution is, by checking how many paths end on the current node. We pass an array of ancestors to each node, so that it can derive all the paths that end on itself and sum to target.
````*.java
class BinaryTree{
	int data;
	BinaryTree left,right;
	BinaryTree(int data){
		this.data = data;
		left = null;
		right = null;
	}
}
public void findSum(BinaryTree node, int sum, List<Integer> pathNodes){
	if(node != null && pathNodes == null){
		pathNodes = new ArrayList<Integer>();
	}
	pathNodes.add(node.data);
	findandPrintAllSummingNodes(pathNodes,sum);
	findSum(node.left,sum,pathNodes);
	findSum(node.right,sum,pathNodes);
	pathNodes.remove(pathNodes.size()-1);
}
private void findandPrintAllSummingNodes(List<Integer> buffer,int sum){
// This method prints all the paths which end at current node with sum
	int currentSum = 0;
	String printString = "";
	for(int i=buffer.size()-1;i--;i>=0){
		currentSum += buffer(i);
		printString += buffer(i) + " ";
		if(currentSum == sum)
			System.out.println(new StringBuffer(printString).reverse().toString());
	}
}
````
Time complexity O(n * log n) because for each node at depth d, we verify at max d nodes above it.