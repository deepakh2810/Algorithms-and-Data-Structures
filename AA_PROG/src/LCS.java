public class LCS {

		//Prints the subsequence with highest length 
		static void lcs(String A, String B, int m, int n) 
		{ 
			int[][] L = new int[m+1][n+1]; 

			// Following steps build L[m+1][n+1] in bottom up fashion. 
			for (int i=0; i<=m; i++) 
			{ 
				for (int j=0;j<=n;j++) 
				{ 
					if (i == 0||j == 0) 
						L[i][j] = 0; 
					else if (A.charAt(i-1) == B.charAt(j-1)) 
						L[i][j] = L[i-1][j-1] + 1; 
					else
						L[i][j] = Math.max(L[i-1][j],L[i][j-1]); 
				} 
			} 
 
			int index = L[m][n]; 
			int temp = index; 

			//character array to store the lcs string 
			char[] lcs=new char[index+1]; 
			lcs[index] = ' '; //Using this as a  terminating character 

			// Start from the right-most-bottom-most corner and one by one store characters in lcs[] 
			int i = m, j = n; 
			while (i > 0 && j > 0) 
			{ 
				if (A.charAt(i-1) == B.charAt(j-1)) 
				{ 
					lcs[index-1] = A.charAt(i-1);  
					i--; 
					j--; 
					index--;	 
				} 
				else if (L[i-1][j] > L[i][j-1]) 
					i--; 
				else
					j--; 
			} 

			// Print the longest Common Subsequence
			System.out.print("The LongestCommonSubsequence is :"); 
			for(int k=0;k<=temp;k++) 
				System.out.print(lcs[k]); 
		} 
		
		public static void main (String[] args) 
		{ 
			String A = "ABCBDAB"; 
			String B = "BDCABA"; 
			int m = A.length();//length of first String
			int n = B.length();//length of second string
			lcs(A, B, m, n); 
		} 
} 

