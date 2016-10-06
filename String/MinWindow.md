# Finding the Minimum Window in S which Contains All Elements from T  
  
**problem statement :**  
Given a set T of characters and a string S, find the minimum window in S which will contain all the characters in T.

**Attempt 1**  
Naiive algorithm that first comes to mind after seeing this problem is to search for windows of string T, in S starting from every character in S. So starting from character i, once we find all characters contained in T, in S, we can stop the search at that index j and declare that one window is found. Check if this is the minimus length window yet found and update the counter accordingly.

_example_  
S : ADOBECODEBANC  
T : ABC  
min Window : BANC  


````*.java
public class Solution {
	static int[] findMinWindow(String s, String t){
		HashMap<Character,Integer> hmT = new HashMap<Character,Integer>();
		for(int i=0;i<t.length();i++){
			if(hmT.containsKey(t.charAt(i)))
				hmT.put(t.charAt(i),hmT.get(t.charAt(i))+1);
			else
				hmT.put(t.charAt(i),1);
		}
		int minWindow = Integer.MAX_VALUE;
		int[] returnArray = new int[2];
		for(int i=0;i<s.length();i++){
			HashMap<Character,Integer> hmtemp = new HashMap<Character,Integer>();
			for(Character c : hmT.keySet()){
				hmtemp.put(c,hmT.get(c));
			}
			int j=i;
			for(;j<s.length();j++){
				char currentChar = s.charAt(j);
				if(hmtemp.containsKey(currentChar)){
					int count = hmtemp.get(currentChar);
					if(count==1)
						hmtemp.remove(currentChar);
					else
						hmtemp.put(currentChar,count-1);
					
					if(hmtemp.size()==0)
						break;
				}
			}
			if(j<s.length() && j-i+1<minWindow){
				minWindow=j-i+1;
				returnArray[0]=i;
				returnArray[1]=j;
			}
		}
		return returnArray;
	}

    public static void main(String[] args) {
    	int[] min = findMinWindow("ADOBECODEBANC","ABC");
    	System.out.println("ADOBECODEBANC".substring(min[0], min[1]+1));
    }
}
````
Time complexity : O(N^2) where N is length of s. This is becaus starting from each character in S we search all characters till the end of string.


**Attempt 2**  
Given the repetitive nature of previous search, can we do better? Answer is yes.