# Longest increasing subsequence  
[geeks for geeks link](http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/)
[Hackerrank problem link](https://www.hackerrank.com/challenges/longest-increasing-subsequent)
  
**problem statement :**  
Find the length of the longest subsequence of a given sequence such that all elements of the subsequence are sorted in increasing order.  
_Example_ : length of LIS for { 10, 22, 9, 33, 21, 50, 41, 60, 80 } is 6 and LIS is {10, 22, 33, 50, 60, 80}.  

**Attempt 1**  
This problem screams dynamic programming. So let's try to come up with a recurrance.  
T(i) is the max length subsequence in string till index i.
```javascript
T(i) = max {  
				str[i]>str[i-1] ? T(i-1)+1 : T(i-1) ,
				for all k such that k<i, and a[k]<a[i] => T(k)+1
			}
````
Code :  
````*.java

List<Integer> longestIncreasingSubsequence(int[] arr){
	int[] tempLIS = new int[arr.length], previous = new int[arr.length];
	tempLIS[0] = 1;
	int maxLength = Integer.MIN_VALUE, bestIndex=0;
	for(int i=1;i<arr.length;i++){
		tempLIS[i] = 1;
		for(int j=0;j<i;j++){
			int current = tempLIS[j];
			if(arr[j]<arr[i] && tempLIS[j]+1>tempLIS[i]){
				tempLIS[i] = tempLIS[j]+1;
				previous[i] = j;
			}
		}
		if(tempLIS[i]>maxLength){
			maxLength = tempLIS[i];
			bestIndex = i;
		}
	}
	List<Integer> LIS = new ArrayList<Integer>();
	int i = bestIndex;
	while(tempLIS[i]!=1){
		LIS.add(arr[i]);
		i = previous[i];
	}
	LIS.add(arr[i]);
	return LIS;
}
````
Time complexity : O(n^2)


**Attempt 2**  
There exists a O(n log n) solution. It's well explained in this [link](http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/).  
Explanation on stack overflow : [link](http://stackoverflow.com/questions/2631726/how-to-determine-the-longest-increasing-subsequence-using-dynamic-programming)