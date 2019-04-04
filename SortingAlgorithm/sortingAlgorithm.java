package SortingAlgorithmRunTIme;

/*Pace University
 *  Fall 2018
 *  Data Structures and Algorithms
 *
 *  Course: CS 242
 *  Author: Manan Thakkar
 *  Collaborators: none
 *  References: none 
 *
 *  Assignment: Assignment 2 - Compare running time two Sorting Alogrithm (Quick Sort and Bucket Sort).
 *  Problem: Testing running times of methods for Sorting Algorithms.
 *  Description: This program measures the running times of Quick Sort, Bucket Sort
 *                on an array of numbers.
 *
 *  Input: Array size and Repetitions of numbers in an array
 *  Output: Running times to method a number that is not in the array
 *
 *  Visible data fields:
 * none.
 *
 * Visible methods:
 * public static void main(String[] args)
 * public static void QuickSort(int A[], int p, int r)
 * public static void BucketSort(int[] A)
 * 
 * Private methods:
 * private static int partition(int[] A, int p, int r)
 *
 *	Quick Sort: 
 * |  	 |	n	  |  n1	  |   n2 	|    n3	  |   n4     |    n5     |      n6      |
 * | r 	 | r/n	  |  10	  |  100 	|   1000  | 10000    |  100000   |   1000000    |
 * -------------------------------------------------------------------------------------
 * | r1  |  2	  | 52695 | 84771   | 1601961 | 3243857  | 21761201  |  131801075   |
 * | r2  |  6	  | 43744 | 76565   | 607580  | 4069605  | 23555065  |  131420048   |
 * | r3  |  10    | 43680 | 87785   | 930767  | 5561813  | 29110421  |  125489310   |
 * | r4  |  50    |   --  | 104648  | 1501364 | 9696280  | 24726804  |  178059390   |
 * | r5  |  100   |   --  | 171051  | 2931738 | 12137157 | 31975960  |  242668786   |
 * | r6  |  500   |   --  |   --    | 4885835 | 18231439 | 79950781  |  922386934   |
 * | r7  |  1000  |   --  |   --    | 7648961 | 35919988 | 533780041 |  1596393747  |
 * | r8  |  5000  |   --  |   --    |   --    | 38387531 | 293040280 |  8405871277  |
 * | r9  |  10000 |   --  |   --    |   --    | 21571176 | 728806332 |  overflow    |
 * 
 * 
 * Bucket Sort: 
 * | 	 |	n	  |  n1   |   n2 	|    n3	  |    n4    |    n5     |      n6      |
 * | r 	 | r/n	  |  10	  |  100 	|   1000  |  10000   |  100000   |   1000000    |
 * -----------------------------------------------------------------------------------
 * | r1  |  2	  | 12156 | 20243   | 113035  | 1108202  | 12065455  |  45537693    |
 * | r2  |  6	  | 8550  | 17023   | 102085  | 957841   | 10958586  |  31542362    |
 * | r3  |  10    | 8942  | 19140   | 148337  | 898334   | 13476334  |  37529886    |
 * | r4  |  50    |   --  | 15781   | 115366  | 863520   | 7648142   |  26712561    |
 * | r5  |  100   |   --  | 19979   | 99110   | 989616   | 9865759   |  27870237    |
 * | r6  |  500   |   --  |   --    | 118516  | 861562   | 9270704   |  28142223    |
 * | r7  |  1000  |   --  |   --    | 95433   | 877910   | 10217403  |  21933106    |
 * | r8  |  5000  |   --  |   --    |   --    | 1009745  | 9785616   |  21662405    |
 * | r9  |  10000 |   --  |   --    |   --    | 861666   | 728806332 |  overflow    |
 * 
 * {****** Note: - "--" means r (repetitions) > n (size) which is not possible. ******} 
 * 
 * 
 * Comparing QuickSort and BucketSort using the running time of each, it shows that Bucket Sort is much faster than QucikSort and bucket sort is running
 * on expected time O(V+E) compared to Quicksort which is running on O(nlogn)
 * 
 * Comparing on both on changing the values of r (repetitions). the change in repetitions does not show a lot of changes in running time but it show 
 * a gradually decreasing graph in the running times of both algorithm which is expected.
 * 
 */

import java.util.*;
import java.io.*;

public class sortingAlgorithm {
	
	public static void main(String args[]){
		Scanner input = new Scanner(System.in);
		   System.out.print("Enter array size: ");
		   int size = input.nextInt();
		   System.out.println();
		   System.out.print("Enter repetitions: ");
		   int r = input.nextInt();
		   System.out.println();
		   
		   int i = 0;
		   int j = 0;
		   int[] quickSort = new int[size];   // create array for Quick Sort
		   double[] bucketSort =  new double[size]; // create array for Bucket Sort
		   
		   while(i< size) {
			 int num = (int) (Math.random() * (size));
			 int repetitions = (int) (Math.random() * r);
			 while(i < size && j < repetitions) {
				 quickSort[i] = num;
				 bucketSort[i] = num;
				 i++;
				 j++;	 
			 }
			 j = 0;
		   }

		   // prepare to measure the time elapsed again
		   long startTime = System.nanoTime();
		   //quicksort for size
		   QuickSort(quickSort, 0, quickSort.length-1);
		   // display the time elapsed
		   System.out.println("The time taken by Quick Sort is " + (System.nanoTime() - startTime) + " nanoseconds.");
		   
		   startTime = System.nanoTime();
		   //bucket sort for size
		   BucketSort(bucketSort);
		   // display the time elapsed
		   System.out.println("The time taken by Bucket Sort is " + (System.nanoTime() - startTime) + " nanoseconds.");
	}

	public static void QuickSort(int[] A, int p, int r) {
	    if (p < r) {
	        int q = partition(A, p, r);
	 
	        QuickSort(A, p, q-1);
	        QuickSort(A, q+1, r);
	    }
	}

	private static int partition(int[] A, int p, int r) {
		int x = A[r];
	    int i = p-1;
	 
	    for (int j = p; j < (r - 1); j++) {
	        if (A[j] <= x) {
	            i++;
	 
	            int swap = A[i];
	            A[i] = A[j];
	            A[j] = swap;
	        }
	    }
	 
	    int swap = A[i+1];
	    A[i+1] = A[r];
	    A[r] = swap;
	 
	    return i+1;
	}
	
	
	public static void BucketSort(double[] A){
		  int n = A.length;
	      int [] B = new int[n+1];

	      for (int i=0; i<B.length; i++){
	         B[i] = 0;
	      }

	      for (int i=1; i<n; i++){
	         B[(int) A[i]]++;
	      }
	      
	      int k = 0;
	      for (int i = 0; i < B.length; i++) {
	         for (int j = 0; j < B[i]; j++){
	            A[k++] = i;
	         }
	      }
	  }
}
