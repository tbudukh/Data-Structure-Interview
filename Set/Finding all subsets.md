#Finding all subsets of a given set in Java  
**problem statement :**  
Given a set of distinct integers, nums, return all possible subsets.
Note: The solution set must not contain duplicate subsets.

Input: 
S = {a, b, c, d}
Output:
{}, {a} , {b}, {c}, {d}, {a,b}, {a,c},
{a,d}, {b,c}, {b,d}, {c,d}, {a,b,c}, 
{a,b,d}, {a,c,d}, {b,c,d}, {a,b,c,d}
  

**Solution**  
Given a set, there are 2^(size of set) such subsets. So we can notice that the output can be represented by numbers from 0 to 2^(size)-1. 
For example : for {a,b,c} subsets are --  
| Number | Set     |
|-------:|---------|
| 000    | []      |
| 001    | [a]     |
| 010    | [b]     |
| 011    | [a,b]   |
| 100    | [c]     |
| 101    | [a,c]   |
| 110    | [b,c]   |
| 111    | [a,b,c] |

For the solution, we have to generate numbers from 0 to 2^(size)-1 and test which bits are 1.  
  
````*.java
public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> returnList = new ArrayList<List<Integer>>();
        int size = nums.length;
        for(int i=0;i<(1<<size);i++){
            List<Integer> lis = new ArrayList<Integer>();
            for(int j=0;j<size;j++){
                if((i&(1<<j))>0){
                    lis.add(nums[j]);
                }
            }
            returnList.add(lis);
        }
        return returnList;
    }
}
````
Worst case complexity : O(2^(n))