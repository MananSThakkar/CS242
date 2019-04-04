/*************************************************************************
 *
 *  Pace University
 *  Fall 2018
 *  Algorithms and Computing Theory
 *
 *  Course: CS 242
 *  Team members: Manan Thakkar
 *  Collaborators:  NONE
 *  References: https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/
 *
 *  Assignment: Assignment 4 - Depth First Search (DFS).
 *  Problem: PUT THE MAIN PROBLEM NAME HERE
 *  Description: PUT A BRIEF DESCRIPTION HERE
 *
 *  Input: Number of Nodes and Number of Edges.
 *  Output: time taken by DFS (in nanoseconds).
 *
 *  Data fields:
 *  private static int White;
 *	private static int Gray;
 *	private static int Black;
 *	private static int [] Color;
 *	private static int [] Pi;
 *	private static int [] Discovered;
 *	private static int [] Finished;
 *	private static int Time = 0;
 *
 *  Methods:
 *  public static void DFS(DirectedGraph graph)
 *  private static void DFS_Visit(DirectedGraph graph, int u)
 *  private static int[] createArray(int Length, int value)
 *  public static DirectedGraph createGraph(int nodes ,int edges)
 *  public static void main(String[] args)
 *
 *  |              |   |E| = |V| - 1   |   |E| = [(|V| - 1)^(3/2)] |   |E| = (|V| - 1)^2   |
 *  |   |V| = 10   |      113369       |           199093          |        749808         |
 *  |  |V| = 100   |      362588       |           796262          |        2173544        |
 *  | |V| = 1000   |      1636155      |           6058200         |        53101225       |
 *  
 *
 *	The loop on lines 75–79 of DFS take time O(V) /, exclusive of the time to execute the calls to DFS_VISIT.
 *	The procedure DFS_VISIT is called exactly once for each vertex  v belongs to V , since the vertex u on which DFS_VISIT
 *	is invoked must be white and the first thing DFS_VISIT does is paint vertex u gray.
 *	During an execution of DFS_VISIT(G, v) the loop on lines 91–96 executes all the lists in the adjacency list so it takes
 *	O(E). so the running time of DFS will be O(V+E). 
 *
 *************************************************************************/



package DFS;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DFS {
	
	private static int White = 0;
	private static int Gray = 1;
	private static int Black = 2;
	private static int [] Color;
	private static int [] Pi;
	private static int [] Discovered;
	private static int [] Finished;
	private static int Time = 0;
	static int NIL = -1;

	
	public static void DFS(DirectedGraph graph) {
		int v = graph.v(); //range of Vertices

		Pi = createArray(v, NIL);  // initial value of PI is NIL
		Color = createArray(v, White);
		
		Discovered = createArray(v, 0); // initial value of array of color is white for all Vertices.
		Finished = createArray(v, 0);
		
		for(int u = 0; u < v; u++) {  // for all Vertices that has not been visited and color is White, then Visit.
			if(Color[u] == White) {
				DFS_Visit(graph, u);
			}
		}	
	}
	
	private static void DFS_Visit(DirectedGraph graph, int u) {
		Time = Time + 1; //increment time as soon as the vertex is Discovered
		Discovered[u] = Time;  //assign the time to the discovery time of vertex.
		Color[u] = Gray;  // change the color of vertex to Gray from White to mark that it has been discovered.
		
		List<Integer> Adjacent = graph.Adjacent(u);
		for(int Vertex : Adjacent) {  // for all the vertices in the graph 
			if(Color[Vertex] == White) {  // if the vertex color is White (means not visited)
				Pi[Vertex] = u;  // indicate that vertex v is visited 
				DFS_Visit(graph, Vertex); // visit the vertex
			}
		}
		Color[u] = Black; // // change the color of vertex to Black from Gray to mark that it has been Finished.
		Time = Time + 1; //increment time as soon as the vertex is Finished
		Finished[u] = Time;  //assign the time to the finished time of vertex.
	}
	
	private static int[] createArray(int Length, int value) {  //Create an array to store the vertices.
		int[] arr = new int[Length];  //initialize
		int i = 0;
		while(i < Length){
			arr[i] = value;
			i++;
		}
		return arr;
	}
	
	public static DirectedGraph createGraph(int nodes ,int edges) { //Create an adjacency list choosing the edges at random
		DirectedGraph graph = new DirectedGraph(nodes);  //initialize
		Random r = new Random();
		int i = 0;
		while( i < edges) {
			int S = r.nextInt(nodes);  
			int E = r.nextInt(nodes);
			
			if(S == E) {
				if(graph.contains(S, E)) {
				continue;
			}
			graph.add(S,E);
			i++;
			}
		}
		return graph;
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); //Initialize the Scanner for user input 
		System.out.print("Enter the number of nodes: ");  // user input for nodes.
		int nodes = sc.nextInt();
		System.out.print("Enter the number of edges: ");  // user input for edges.
		int  edges = sc.nextInt();
	
		DirectedGraph graph = createGraph(nodes , edges); // initalize createGraph method that will create a graph with user input nodes and edges.
		long startTime = System.nanoTime();
		//DFS for size
		DFS(graph);  // create a directed graph using the user input of nodes and edges.
		// display the time elapsed
		System.out.println("The time taken by DFS is " + (System.nanoTime() - startTime) + " nanoseconds."); 
	}

}
