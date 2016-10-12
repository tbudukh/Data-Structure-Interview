Longest common subsequence problem.
-

[Hackerrank link](https://www.hackerrank.com/contests/bingacm-apr15/challenges/longest-common-subsequence)  
[Hackerrank discussion](https://www.hackerrank.com/challenges/dynamic-programming-classics-the-longest-common-subsequence)

__examples__  
	1. abcd, acbd --> LCS = 3 = "abd" || "acd"  
	2. abkl, adcb --> LCS = 2 = "ab"
	
**Attempt 1 at recurrance relation**

LCS(abcd, acbd) = 1 + LCS(bcd, cbd) = 1 + MAX{ LCS(bcd, bd), LCS(cd, cbd) } and so on

So the recurrance in equation would be : 
	LCS(S1ij, S2kl) = S1[i]==S2[j] ? 1 + LCS(S1i+1j, S2K+1l) : MAX { LCS(S1i+1j, S2kl), LCS(S1ij, S2k+1l) }
	
But this has repititive steps, and we aren't using memoization. To use memoization, you have to use something previously computed in a matrix.
So instead of using 2 indices each i.e. i,j and k,l we'll just use 1 index per string, i.e. the start index will always be 0 instead of i and k.


**Attempt 2**

_Base cases :_  
LCS(0,0)=0, LCS(0, j)=0, LCS(i, 0)=0;

LCS(i, j) = S1[i]==Ss[j] ? 1 + LCS(i-1, j-1) : MAX{ LCS(i-1, j), LCS(i, j-1) }

So we can hand-run 2nd example as :
S1 = abkl, S2 = adcb

| S2 | a | d | c | b |
|----|---|---|---|---|
| S1 |   |   |   |   |
| a  | 1 | 1 | 1 | 1 |
| b  | 1 | 1 | 1 | 2 |
| k  | 1 | 1 | 1 | 2 |
| l  | 1 | 1 | 1 | 2 |

Code
==============
````*.java
void LCSNonRecursvie(String s1, String s2, int[][] matrix){
	for(int i=0;i<s1.length();i++){
		for(int j=0;j<s2.length();j++){
			if(s1.charAt(i)==s2.charAt(j)){
				if(i!=0 && j!=0)
					matrix[i][j] = 1 + matrix[i-1][j-1];
				else
					matrix[i][j] = 1;
			}
			else{
				if(i==0 && j==0)
					matrix[i][j] = 0;
				else if(j==0 || (i!=0 && matrix[i-1][j]>matrix[i][j-1]))
					matrix[i][j] = matrix[i-1][j];
				else
					matrix[i][j] = matrix[i][j-1];
			}
		}
	}
}

int LCS(String s1, String s2, int[][] matrix){
	int i=s1.length()-1, j=s2.length()-1;
	if(i<0 || j<0)
		return 0;
	if(matrix[i][j]==-1){
		if(s1.charAt(i)==s2.charAt(j)){
			matrix[i][j] = 1+LCS(s1.substring(0, i), s2.substring(0, j), matrix);
			LCS(s1, s2.substring(0, j), matrix);
			LCS(s1.substring(0, i), s2, matrix);
		}
		else{
			matrix[i][j] = Math.max( LCS(s1, s2.substring(0, j), matrix), LCS(s1.substring(0, i), s2, matrix) );
		}
	}
	return matrix[i][j];
}

String printLCS(String s1, String s2, int[][] matrix){
	int i=s1.length()-1, j=s2.length()-1;
	String output = "";
	while(i>=0 && j>=0){
		if(s1.charAt(i)==s2.charAt(j)){
			output += s1.charAt(i);
			i--;j--;
		}
		else{
			if(j==0 || (i!=0 &&matrix[i-1][j]>matrix[i][j-1]))
				i--;
			else
				j--;
		}
	}
	output = new StringBuffer(output).reverse().toString();
	return output;
}
````
Time complexity : O(m*n) where m and n are lengths of strings s1 and s2 respectively.