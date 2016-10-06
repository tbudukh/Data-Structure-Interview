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
Given the repetitive nature of previous search, can we do better? Answer is yes. Here are the steps :  
* First find the first window in S that contains all characters of T. This can be done with the code snippet from attempt 1. Let's assume that the window is between i and j(inclusive). Note the window size. Let the character at position 'i' be C.
* From the first window, advance i to the next character such that it is in T. This is the start of the new window. Now we deleted the one character C from previous window. So we have to find another C, if it is not already present between i and j.
* In that case advance pointer j till we find C in the string S. And now i and j is your new window.

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
		
		int i=0,currentj=0;
		HashMap<Character,Integer> hmtemp = new HashMap<Character,Integer>();
		for(Character c : hmT.keySet()){
			hmtemp.put(c,hmT.get(c));
		}
		
		HashMap<Character,Integer> hmFound = new HashMap<Character,Integer>();
		
		//find the first window that has all of T's characters
		while(currentj<s.length()){
			char currentChar = s.charAt(currentj);
			if(hmFound.size()==0)
				i=currentj;
			// fill the hashmap of found characters in T.
			if(hmT.containsKey(currentChar)){
				if(hmFound.containsKey(currentChar))
					hmFound.put(currentChar, hmFound.get(currentChar)+1);
				else
					hmFound.put(currentChar, 1);
			}
			
			// This is to help find the window
			if(hmtemp.containsKey(currentChar)){
				int count = hmtemp.get(currentChar);
				if(count==1)
					hmtemp.remove(currentChar);
				else
					hmtemp.put(currentChar,count-1);
				
				if(hmtemp.size()==0)
					break;
			}
			currentj++;
		}
		
		if(currentj<s.length()){
			minWindow=currentj-i+1;
			returnArray[0]=i;
			returnArray[1]=currentj;
			
			while(i<s.length() && currentj<s.length()){
				char currentChar = s.charAt(i);
				if(!hmT.containsKey(currentChar))
					i++;
				else{
					//delete one character, that was present in T, from current window and launch search for that character if required.
					i++;
					while(!hmT.containsKey(s.charAt(i))){
						i++;
					}
					hmFound.put(currentChar,hmFound.get(currentChar)-1);
					if(hmFound.get(currentChar)<hmT.get(currentChar)){
						//launch a search from currentj forward to search currentChar.
						currentj++;
						while(currentj<s.length()){
							char currentCharNew = s.charAt(currentj);
							if(hmT.containsKey(currentCharNew)){
								if(hmFound.containsKey(currentCharNew))
									hmFound.put(currentCharNew, hmFound.get(currentCharNew)+1);
								else
									hmFound.put(currentCharNew, 1);
							}
							if(currentCharNew==currentChar)
								break;
							currentj++;
						}
					}
					if(currentj<s.length() && currentj-i+1<minWindow){
						minWindow=currentj-i+1;
						returnArray[0]=i;
						returnArray[1]=currentj;
					}
				}
				
			}
		}
		else
			return null;
		
		return returnArray;
	}

    public static void main(String[] args) {
    	int[] min = findMinWindow("ADOBECODEBANC","ABC");
    	System.out.println("ADOBECODEBANC".substring(min[0], min[1]+1));
    }
}
````
Time complexity : O(N) where N is the length of string S. This is because of the window logic.