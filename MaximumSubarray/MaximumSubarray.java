

 /*Pace University
  *  Fall 2018
  *  Data Structures and Algorithms
  *
  *  Course: CS 242
  *  Author: Manan Thakkar
  *  Collaborators: none
  *  References: 1) Stack overflow (Divide and conquer)
  *              2) Geek for Geeks (Kadane) 
  *
  *  Assignment: 1
  *  Problem: Testing running times of methods for Maximum Subarray Problem.
  *  Description: This program measures the running times of of Brute Force, Divide and Conquer and Kandane methods
  *                on an array of numbers.
  *
  *  Input: array size
  *  Output: running times to method a number that is not in the array
  *
  *  Visible data fields:
  * none.
  *
  *  Visible methods:
  * public static void main(String[] args)
  * public static int BruteForce(int[] s, int n)
  * public static int DivideandConquer(int s[], int size)
  * public static int MaxCrossingSum(int s[], int low, 
          int mid, int high)
  * static int maxSubArraySum(int s[], int size)
  *
  *
  *   Remarks
  *   -------
  *
  *   Chart of running times observed in nanoseconds:
  *
  *   Size     |    Brute Force  |  Divide and Conquer  |   Kadane
  *  ----------------------------------------------------------------------
  *  10        |  41738          |     19054            |   7506
  *  ----------------------------------------------------------------------
  *  100       |  183233         |      91045           |   12895
  *  ----------------------------------------------------------------------
  *  1000      |  7984597        |      673598          |   37456
  *  ----------------------------------------------------------------------
  *  10000     |  46572172       |      2532538         |   335729
  *  ----------------------------------------------------------------------
  *  100000    |  4302548160     |      11821175        |   3257510
  *  ----------------------------------------------------------------------
  *
  *  
  *  
  *  After running all the test for Brute force, Divide and Conquer and Kadane's 
  *  Algorithm using different sizes of array, the numbers show significant 
  *  change between the algorithms. Brute force is increasing significantly 
  *  faster then Divide and Conquer and Kadane's Algorithm and similarly Divide 
  *  and Conquer is taking more time than Kadane's Algorithm. Brute force is
  *  increasing quadratically in comparison to Divide and Conquer.
  *
  *
  *************************************************************************/

 import java.util.*;
 import java.io.*;

 public class MaximumSubarray {
  public static void main(String[] args) {

   // input array size from user
   Scanner input = new Scanner(System.in);
   System.out.print("Enter array size: ");
   int size = input.nextInt();
   System.out.println();

   Random r = new Random();
   int[] S = new int[size];
   for (int i = 0; i < S.length; i++) {
    S[i] = (int) (Math.random() * 30) - (30 / 2);  // generate numbers between -15 t0 15
    // System.out.println(S[i]);
   }

   // prepare to measure the time elapsed again
   long startTime = System.nanoTime();
   //brute force for size
   BruteForce(S, size);
   // display the time elapsed
   System.out.println("The time taken by Brute force is " + (System.nanoTime() - startTime) + " nanoseconds.");

   startTime = System.nanoTime();
   //  divide and conquer for size
   DivideandConquer(S, 0, size-1);
   // display the time elapsed
   System.out.println("The time taken by Divide and Conquer is " + (System.nanoTime() - startTime) + " nanoseconds.");

   startTime = System.nanoTime();
   // Kadane for size
   maxSubArraySum(S, size);
   // display the time elapsed
   System.out.println("The time taken by Kadane's Algorithm is " + (System.nanoTime() - startTime) + " nanoseconds.");
  }


  /**************************************************************************/
  //Brute Force
  public static int BruteForce(int[] s, int n) {
   n = s.length;
   int max = Integer.MIN_VALUE;
   for (int i = 0; i < n; i++) {
    int sum = 0;
    for (int j = i; j < n; j++) {
     sum += s[j];
     if (sum > max)
      max = sum;
    }
   }
   return max;
  }


  /**************************************************************************/
  //Divive and Conquer
  public static int DivideandConquer(int[] s, int low, int high) {

	  { 
		    // Base Case: Only one element 
		    if (low == high){
		        return s[low]; 
		    }
		    
		    else{
		    // Find middle point 
		    int middle = (low + high)/2; 
		  
		    /* Return maximum of following three  
		    possible cases: 
		    a) Maximum subarray sum in left half 
		    b) Maximum subarray sum in right half 
		    c) Maximum subarray sum such that the  
		    subarray crosses the midpoint */
		    
		    int leftSum = DivideandConquer(s, low, middle);
			int rightSum = DivideandConquer(s, middle + 1, high);
			int crossSum = MaxCrossingSum(s, low, middle, high);
		
		    return Math.max(Math.max(leftSum, rightSum), crossSum) ;
		    }
  }


  }
  
  
  public static int MaxCrossingSum(int s[], int low, 
          int mid, int high) 
{ 
// Include elements on left of mid. 

	  int leftsum = Integer.MIN_VALUE;
	  int sum = 0; 
 
for (int i = mid; i >= low; i--) 
{ 
sum = sum + s[i]; 
if (sum > leftsum) 
leftsum = sum; 
} 

// Include elements on right of mid
int rightsum = Integer.MIN_VALUE;
int Sum = 0; 
for (int i = mid + 1; i <= high; i++) 
{ 
Sum = Sum + s[i]; 
if (Sum > rightsum) 
rightsum = Sum; 
} 

// Return sum of elements on left 
// and right of mid 
return leftsum + rightsum; 
} 

  /**************************************************************************/
  //KADANE
  static int maxSubArraySum(int s[], int size)

  {
   int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;

   for (int i = 0; i < size; i++)

   {

    max_ending_here = max_ending_here + s[i];

    if (max_so_far < max_ending_here)

     max_so_far = max_ending_here;

    if (max_ending_here < 0)

     max_ending_here = 0;

   }

   return max_so_far;

  }
 }

