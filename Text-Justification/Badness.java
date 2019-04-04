/*************************************************************************
 *
 *  Pace University
 *  Fall 2018
 *  Algorithms and Computing Theory
 *
 *  Course: CS 242
 *  Team members: Manan Thakkar
 *  Collaborators:  NONE
 *  References: None
 *
 *  Assignment: Assignment 3 - Text Justification.
 *  Problem: Greedy algorithm 
 *  Description: Badness method that, given a width ω as a number of characters, an array W[0. . . n−1] of strings, and two indices i and j such that 
 *  			0 ≤ i ≤ n − 1 and 1 ≤ j ≤ n and uses the function l Where l(W, i, j)  is  the  total  length  in  characters  of  the  words  from index
 *  			i to j − 1 in  the array W,  taking into account that  one space must be left in between each pair of consecutive words.
 *
 *  Input: Number of Words and Width of the line.
 *  Output: Justified text file.
 *
 *  Data fields: None
 *  
 *  Methods:
 *  public static int badness(String[] W, int i, int j, int width). 
 *	public static int l(String[] W, int i, int j)
 *
 *	
 *
 *************************************************************************/


package TextJustification;

public class Badness {
	
	public static int badness(String[] W, int i, int j, int width) {
		int badness = width - l(W, i, j); // Calculate (width - the total length of characters of words from index i to j-1)
		if (badness >= 0)
			return (int) Math.pow(badness, 3);// If temp is greater than 0 return x^3
		else
		return Integer.MAX_VALUE;// if not then return an infinite number
	}

	public static int l(String[] W, int i, int j) {

		int length = 0; // the total length in characters of the words
		int spaces = 0;
		for (int k = i; k < j; k++) {
			length += W[k].length();// Accumulate the length of each line of the text Without spaces
		}
		spaces = j - i - 1;
		return length + spaces;
	}


}
