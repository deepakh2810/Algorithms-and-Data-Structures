import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
//Implement a linear time algorithm for the following problem: https: kth smallest element unsorted array at O(log n).
public class KthsmallestElement{
	  
	static void insSort(int[] arr, int l, int h) {//insertion sort to sort a group of 5 
		if (l == h)
			return;
		for (int j = l + 1; j <= h; j++) {
			int i = l;
			while (arr[j] < arr[i]) {
				swap(arr, i++, j);
			}
		}
	}
	
    static void merge(int arr[], int l, int m, int r) 
    { 
        // Find sizes of two subarrays to be merged 
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        //M and N are Temporary arrays to cpy data
        int M[] = new int [n1]; 
        int N[] = new int [n2]; 
        for (int i=0; i<n1; ++i) 
            M[i] = arr[l + i]; 
        for (int j=0; j<n2; ++j) 
            N[j] = arr[m + 1+ j]; 
  
        int i=0,j=0;      
        int k=l; 
        while (i <n1 && j< n2) 
        { 
            if (M[i]<=N[j]) 
            { 
                arr[k]=M[i]; 
                i++; 
            } 
            else
            { 
                arr[k]=N[j]; 
                j++; 
            } 
            k++; 
        } 
  
        // copy elements in M and N if left
        while (i<n1) 
        { 
            arr[k]=M[i]; 
            i++; 
            k++; 
        } 
        while (j < n2) 
        { 
            arr[k]=N[j]; 
            j++; 
            k++; 
        } 
    } 
   
    //main MergeSort Function
    static void sort(int arr[], int l, int r) 
    { 
        if (l < r) 
        { 
            //  middle point 
            int m = (l+r)/2; 
  
            // Sort first and second halves 
            sort(arr, l, m); 
            sort(arr , m+1, r); 
  
            // Merge the sorted halves 
            merge(arr, l, m, r); 
        } 
    } 
    
    

	 static int ith_Element(int[] a, int l, int h, int i) {
		if (i>0 && i<=h-l+1) {
			int n =h-l+1;
            
			// divide array into groups of 5 elements and then find the median 
			int j, m[] = new int[(n+4)/5]; 
			for (j = 0;j<n/5;j++)
				m[j] = med(a,l+j*5,l+j*5+5-1);
			if (j*5< n)
			{
				m[j] = med(a,l+j*5,l+j*5+n%5-1);
				j++;
			}
			int medOfMed = (j==1)?m[j-1]:ith_Element(m,0,j-1,j/2);
			int pos = partition(a,l,h,medOfMed);
			if (pos-l== i-1)
				return a[pos];
			if (pos-l>i-1) 
				return ith_Element(a,l,pos-1,i);

			// Else recursuvely search the right array.
			return ith_Element(a,pos+1,h,i-pos+l-1);
		}
		return Integer.MIN_VALUE;
	}

	 static int med(int[] arr, int l, int h) {
		insSort(arr,l,h);
		return arr[(h+l)/2];
	}

     //partition at pivot position
	 static  int partition(int[] ar, int l, int h, int pivot) {
		int i = l - 1;
		// find the index of the GIVEN pivot element and move it to the end
		for (i = l; i < h; i++) {
			if (ar[i] == pivot)
				break;
		}
		swap(ar, i, h);
		i = l - 1;
		for (int j=l;j<h;j++) {
			if (pivot>ar[j]) {
				swap(ar,++i,j);
			}
		}
		swap(ar,++i,h);
		return i;
	}


     static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	
	
    //Function to convert Float to Double.
	public static Double getFloatAsDouble(Float val) {
		    return Double.valueOf(val.toString());
	}
	
	
	//Function to calculate Average
		public static Double calcAverage(Double Total) {
		    return Total/3;
		}
		
	
		
		
		static double getTotal(ArrayList<Double> timeArrayList) {
			Double total=0.0;
			for(int m=0;m<timeArrayList.size();m++)
			{
			    total+=timeArrayList.get(m);
			}
			
			return total;
		}

	
		 public static void main(String[] args) throws FileNotFoundException 
		    {
				// TODO Auto-generated method stub
		    		Scanner reader = new Scanner(new File("/Users/dhanuman/Documents/CSCI-505_WORKSPACE/AA_PROG/src/input.txt"));
		    		ArrayList<Integer> mainArrayList = new ArrayList<Integer>();
		    		
                    ArrayList<Double> avgTimeArrayListforMS = new ArrayList<Double>();
//		            ArrayList<Double> avgTimeArrayListforDC = new ArrayList<Double>();    		
		    		while (reader.hasNextInt()){
	    			mainArrayList.add(reader.nextInt()); 
                    }reader.close();
                  
    		        for(int j=5000;j<=100000;j=j+5000)
		    		{
		    			int ar[] = new int[j]; 
		    			for(int k=0;k<j;k++)
		    			{
		    				ar[k]=mainArrayList.get(k);//array to take in different number of inputs at different times.
		    			}
		    		ArrayList<Double> timeArrayListforMergeSort = new ArrayList<Double>();//a temporary array list to store the 3 run times.
		    		Double runTimeforMergeSort=0.0; 
	    			int lengthofArray=ar.length;
	    			for(int l=0;l<=2;l++)//In order to run each input 3 times, this loop is used.
	    			{
	    				long startTimeforMergeSort = System.currentTimeMillis();
	    				sort(ar,0,lengthofArray-1);
	    				long endTimeforMergeSort = System.currentTimeMillis();
	    				
	    				float timeforMergeSort=endTimeforMergeSort-startTimeforMergeSort;//time taken by the Algorithm is End time - start time
	    		
	    				runTimeforMergeSort=getFloatAsDouble(timeforMergeSort);
	    				timeArrayListforMergeSort.add(runTimeforMergeSort);
	    			}
	    			Double timeTotalforMergeSort=getTotal(timeArrayListforMergeSort);
	                Double avgforMS=calcAverage(timeTotalforMergeSort);
	    		    avgTimeArrayListforMS.add(avgforMS);
	                timeArrayListforMergeSort.clear();//clear the timeArrayList so it can have the next 3 values.
	    	    }
		    	
		    		int []Input= {5000,10000,15000,20000,25000,30000,35000,40000,45000,50000,55000,60000,65000,70000,75000,80000,85000,90000,95000,100000};
		    	    for(int n=0;n<avgTimeArrayListforMS.size();n++)
					System.out.println("Average time for  by using Merge Sort for "+Input[n]+" inputs 3 times  is :"+avgTimeArrayListforMS.get(n)+"ms ");
		
		    	    int[] ar = {40,34,56,78,96,55,32,12,8,15,65,7};
		    	    int valueOfi=5;
		    		int ResultElement = ith_Element(ar,0,ar.length-1,valueOfi);
		            System.out.println(ResultElement);
					
		    
		    }

}
