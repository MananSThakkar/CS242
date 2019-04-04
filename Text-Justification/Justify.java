/*************************************************************************
 *
 *  Pace University
 *  Fall 2018
 *  Algorithms and Computing Theory
 *
 *  Course: CS 242
 *  Team members: Manan Thakkar
 *  Collaborators:  NONE
 *  References: http://qa.geeksforgeeks.org/3710/qa.geeksforgeeks.org/3710/justify-the-text.html
 *  			http://yihuad.blogspot.com/2013/11/text-justification-leetcode.html
 *
 *  Assignment: Assignment 3 - Text Justification.
 *  Problem: Justify text using Split ,badness and Justify methods
 *  Description: “justify” method that, given as parameters the width ω, the array of words W, and the list of breakpoints L, creates
 *  				a text file, call it for instance just.txt, justified accordingly.  That is, for each line, your code should spread 
 *                 the words evenly in the width of ω characters. 
 *
 *  Input: Number of Words and Width of the line.
 *  Output: Justified text file.
 *
 *  Data fields: None
 *  
 *  Methods:
 *  public static String justify(String[]W, int w, ArrayList<Integer> L)
 *	private static String justifyLine(int i, int j, String[] W, int w)
 *
 *	
 *
 *************************************************************************/

package TextJustification;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Justify {
	
	
	public static String justify(String[]W, int w, ArrayList<Integer> L) {
		StringBuilder Justified = new StringBuilder();
		for(int k = 0; k < L.size(); k++) {
			int i = L.get(k); // head of line
			int j = W.length; // tail of line according to width.
			if(k < L.size() - 1) {
				j = L.get(k +1);
			}
			Justified.append(justifyLine(i, j, W, w) + "\n");	
		}
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter ("just.txt"));
			bw.write(Justified.toString());
			bw.close();
		} catch (Exception e ) {
			e.printStackTrace();
		}
		return Justified.toString();
	}
	
	private static String justifyLine(int i, int j, String[] W, int w) {
		int Spaces = j - i;  //  Space between words.
		int extraSpaces = (w - Badness.l(W, i, j));
		int totalSpaces = extraSpaces + Spaces-1;
		double avgSpaces = 0;
		
		if(Spaces != 1) {
			avgSpaces = Math.floorDiv(totalSpaces, (Spaces - 1));
		} else {
			avgSpaces = totalSpaces;
		}
		
		String spaces="";
		String remindSpaces = "";
		int spacesRemainded;
		
		if (Spaces == 1) {
			spacesRemainded = totalSpaces - spaces.length();
		} else {
			spacesRemainded = totalSpaces % (Spaces-1);
		}
		
		int k = 0;
		while(k < avgSpaces) {
			spaces = spaces+" ";
			k++;
		}
		
		int l = 0;
		while(l< spacesRemainded) {
			remindSpaces = remindSpaces + " ";
			l++;
		}
		
		String JustifiedLine = "";  // add equal spaces to all words in line
		int m = i;
        while(m < j){
        	
        	if(m != j - 1) {
        		W[m] = W[m] + spaces;
        	}
        	m++;
        } 
     
        if(spacesRemainded!=0) {  // Add remind spaces to first words in line
        	W[i] = W[i] + remindSpaces;
		}
        
        int n = i;
        while(n < j){
			 JustifiedLine = JustifiedLine + W[n];
			 n++;
        }
        return JustifiedLine;
		
	}

}

