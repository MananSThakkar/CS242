/*************************************************************************
 *
 *  Pace University
 *  Fall 2018
 *  Algorithms and Computing Theory
 *
 *  Course: CS 242
 *  Team members: Manan Thakkar
 *  Collaborators:  NONE
 *  References: Github.com
 *  
 *  Assignment: Assignment 3 - Text Justification.
 *  Problem: Greedy algorithm 
 *  Description: Split method that, given as parameters the width ω and the array W, returns a list L of the indices of the 
 *  			array where each line should start to minimize the aggregated badness (i.e. the sum of the badness 
 *  			of all lines in such split)
 *
 *  Input: Number of Words and Width of the line.
 *  Output: Justified text file.
 *
 *  Data fields: None
 *  
 *  Methods:
 *  public static ArrayList<Integer> split(String[] W, int width) 
 *	public static ArrayList<Integer> MinimumBadness(String[] W, int width)
 *  private static int MemoizedMinmumBadness(String[] W, int i, int[] memo, int width)
 *
 *
 *************************************************************************/

package TextJustification;

import java.util.ArrayList;

public class Split {

		public static ArrayList<Integer> split(String[] W, int width) {
			return MinimumBadness(W, width);     //return a list of breakpoints L
		}

		public static ArrayList<Integer> MinimumBadness(String[] W, int width) {
			ArrayList<Integer> list = new ArrayList<Integer>();// a list to track the minimum aggregated badness
			int[] memo = new int[W.length];
			for (int i = 0; i < memo.length; i++) { 
				memo[i] = -1; // Initialize memo with a value of -1
			}

			MemoizedMinmumBadness(W, 0, memo, width);
			for (int j = 0; j < W.length; j++) {
				list.add(memo[j]);	// compute minimum aggregated badness of each,then put it in a list
			}
			ArrayList<Integer> L = new ArrayList<Integer>();//the list L to record all the breakpoints
			L.add(0);
			int i = 0;
			int index = 1;
			
			while (index < W.length) {
				boolean breakpoint = (list.get(i) == Badness.badness(W, i, index, width) + list.get(index));
				if (breakpoint) {
					L.add(index);
					i = index;
				}
				index++;
			}

			return L;  //return a List of breakpoints
			
		}

		private static int MemoizedMinmumBadness(String[] W, int i, int[] memo, int width) {
			int n = memo.length;
			if (memo[i] >= 0)
				return memo[i]; // if the aggregate badness is calculated before, then do not count again
			if (i == n)
				memo[i] = 0; // if the number of words = 0, then return badness to 0
			else {
				int min = Integer.MAX_VALUE; // Initialize the value for badness
				int temp;
				for (int j = i + 1; j < n; j++) { 
					temp = Badness.badness(W, i, j, width);
					temp += MemoizedMinmumBadness(W, j, memo, width);// Call itself recursively 
					if (temp < min)
						min = temp;
				}
				memo[i] = min;
			}
			return memo[i]; // output a list of minimum aggregate badness from i to n - 1.
		}
	}
