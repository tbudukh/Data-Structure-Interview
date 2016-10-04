#Find insertion position  
**problem statement :**  
Given a  number and a  sorted array, find the best position to insert the number in array. If the number already exists then return the index.

**Attempt 1**  
This problem has binary search written all over it. One basic approach that would ocme to mind is scan all the elements and find the best position to insert, but the time complexity of such an algorithm would be O(n) in the worst case.  
Can we do better? Answer is yes, modified binary search. Here's my version :

````*.java
int findInsertionPoint(int[] arr, int number){
    int i=0,j=arr.length;
    while(i<j){
        int mid = i+(j-i)/2;
        if(number<=arr[mid]){
            if(mid==i || num>arr[mid-1])
                return mid;
            else
                j=mid-1;
        }
        else
            i=mid+1;
    }
    return j;
}
````
Comment : Above code doesn't differentiate between whether the number was found at j or needs insertion.  
Time complexity : O(log n) because it's a modified binary search.

**Optimization :**  
java.util.Arrays.binarySearch does the same job as this problem. It's much more optimized code. It returns a negative index if the element is not found and needs insertion.
````*.java
public static int binarySearch(int[] a, int key) {
	return binarySearch0(a, 0, a.length, key);
}

// Like public version, but without range checks.
private static int binarySearch0(int[] a, int fromIndex, int toIndex,
								 int key) {
	int low = fromIndex;
	int high = toIndex - 1;

	while (low <= high) {
		int mid = (low + high) >>> 1;
		int midVal = a[mid];

		if (midVal < key)
			low = mid + 1;
		else if (midVal > key)
			high = mid - 1;
		else
			return mid; // key found
	}
	return -(low + 1);  // key not found.
}
````