**Longest common subsequence problem.**

examples :
	1. abcd, acbd --> LCS = 3 = "abd" || "ACD"
	2. abkl, adcb --> LCS = 2 = "ab"
	
Attempt 1 at recurrance relations
-----------------------------------
LCS(abcd, acbd) = 1 + LCS(bcd, cbd) = 1 + MAX{ LCS(bcd, bd), LCS(cd, cbd) } and so on

So the recurrance in equation would be : 
	LCS(S1ij, S2kl) = S1[i]==S2[j] ? 1 + LCS(S1i+1j, S2K+1l) : MAX { LCS(S1i+1j, S2kl), LCS(S1ij, S2k+1l) }
	
But this has repititive steps, and we aren't using memoization. To use memoization, you have to use something previously computed in a matrix.
So instead of using 2 indices each i.e. i,j and k,l we'll just use 1 index per string, i.e. the start index will always be 0 instead of i and k.


Attempt 2 :
----------------

Base cases :
LCS(0,0)=0, LCS(0, j)=0, LCS(i, 0)=0;

LCS(i, j) = S1[i]==S1[j] ? 1 + LCS(i-1, j-1) : MAX{ LCS(i-1, j), LCS(i, j-1) }

So we can handrun 2nd example as :
S1 = abkl, S2 = adcb

		a	d	c	b
S2	0	1	2	3	4
S1
0	0	0	0	0	0
a1	0	1	1	1	1
b2	0	1	1	1	2
k3	0	1	1	1	2
l4	0	1	1	1	2

