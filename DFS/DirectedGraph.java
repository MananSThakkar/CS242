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
 *  Assignment: Assignment 4 - Depth First Search (DFS).
 *  Problem: 
 *  Description: PUT A BRIEF DESCRIPTION HERE
 *
 *  Input: Number of Nodes and Number of Edges.
 *  Output: time taken by DFS (in nanoseconds).
 *
 *  Data fields:
 *  int numberOfVertices;
	private static ArrayList<ArrayList<Integer>> AdjacentList;
 *
 *  Methods:
 *  public DirectedGraph(int numberOfVertices)
 *	public int v()
 *	public static List<Integer> Adjacent(int Vertex)
 *	public void add(int S, int E)
 *	public boolean contains(int S, int E)
 *
 *   
 *
 *************************************************************************/


package DFS;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraph { // Representation of Directed Graph
							// Vertices are represented as integers
	

	int numberOfVertices;
	private static ArrayList<ArrayList<Integer>> AdjacentList;
	
	public DirectedGraph(int numberOfVertices) {
		this.numberOfVertices = numberOfVertices;
		AdjacentList = new ArrayList<>(numberOfVertices); //Initialize
		for(int i = 0; i < numberOfVertices; i++) {
			AdjacentList.add(new ArrayList<>());
		}
	}
	
	public int v() { // returns the number of vertices of the graph
		return numberOfVertices;
	}

	public static List<Integer> Adjacent(int Vertex){  //
		return AdjacentList.get(Vertex);
		
	}

	public void add(int S, int E) {  // 
		AdjacentList.get(S).add(E);
	}
	
	public boolean contains(int S, int E) {  //
		return AdjacentList.get(S).contains(E);
	}

}
