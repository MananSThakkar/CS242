/*************************************************************************
 *
 *  Pace University
 *  Fall 2018
 *  Algorithms and Computing Theory
 *
 *  Course: CS 242
 *  Team members: Manan Thakkar
 *  Collaborators:  NONE
 *  References: 
 *  
 *  Assignment: Assignment 3 - Text Justification.
 *  Problem: Greedy algorithm 
 *  Description: main method that prompts the user to enter a number of words n and a page width ω as a number of characters. Then,
 *  			create the array W[0. . . n−1] of strings and fill it with random strings.The length of each string should be 
 * 				 chosen  at  random  in  the  range [1, 15].  Notice that, to the extent to evaluate the result of justification,
 * 				the actual characters used are irrelevant. For instance, you could choose all the characters to be the same, as 
 * 				in “aaaaaaaaaaa”.  Then,  main method call the method “split” to obtain the list of breakpoints L, and 
 *				subsequently call the method “justify” passing W, ω, andLas parameters. Finally, output the array W to a second textile 
 *				in a single line, call it for instance unjust.txt
 *
 *  Input: Number of Words and Width of the line.
 *  Output: Justified text file.
 *
 *  Data fields: None
 *  
 *  Methods:
 *  public static String genStrA(int length). 
 *	public static void main(String[] args).
 *
 *	Q5) The algorithm of Text Justification shows how two files unjust.txt and just.txt differ from each other.
 *		The text file unjust.txt uses greedy algorithm where it puts a new word of a paragraph into a new line 
 *		whereas the just.txt shows to use the width of the line to put maximum number of words on one line.
 *
 *		*** Coping and pasting it into the MS Word file also shows no change for me, so I conclude that MS Word uses the greedy approach rather than justify the text automatically.
 *
 *************************************************************************/

package TextJustification;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	public static String genStrA(int length) {
		String str = "qwertyuiopQWERTYUIOPlkjhgfdsaLKJHGFDSAzxcvbnmZXCVBNM";// Define string with length of 52
		Random r = new Random();										//Random number generator
		StringBuffer stringbuffer = new StringBuffer();
		int i = 0;
		while( i < length) {							
			int number = r.nextInt(52);					//generate a number and put matching char.			
			stringbuffer.append(str.charAt(number));     //The resulting number is loaded into String Buffer
			i++;
		}
		
		return stringbuffer.toString(); 
	}
	
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of words: ");
		int numofWords = sc.nextInt();          //number of words
		System.out.println("Enter page width: ");
		int width = sc.nextInt();
		// width  as a number of characters
		String[] Words = new String[numofWords];      
		for (int i = 0; i < Words.length; i++) {
			int numLetters = (int)(Math.random() * Math.min(width, 15) + 1);
			Words[i] = genStrA(numLetters);
		}
		
		BufferedWriter bufferedWriter;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter("unjust.txt"));
			for(int i= 0; i < Words.length;i++) {
				bufferedWriter.write(Words[i] + "\n");
			}
			bufferedWriter.close();
		}catch (IOException e){
			e.printStackTrace();
		}
			ArrayList<Integer> list = Split.split(Words, width);
			String Justified = Justify.justify(Words, width, list);
			System.out.println("Justified Text: ");
			System.out.println(Justified);
			
	}	

	
}
