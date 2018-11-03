import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
class PageRank {
	Digraph g;
	int vertices;
	Double[] pgRank;
	HashMap <Integer, ArrayList<Integer>> revMap;


	PageRank(Digraph gr, HashMap <Integer, ArrayList<Integer>> revMap) {
		this.revMap = revMap;
		this.g = gr;
		this.vertices = gr.V();
		for (int i = 0; i < vertices; i++) {
			pgRank[i] = 1.0 / vertices;
		}
	}

	public void calcPageRank() {
		for (int i = 0; i < vertices; i++) {
			if (g.outdegree(i) == 0) {
				for (int j = 0; j < vertices ; j++) {
					if (i != j) {
						g.addEdge(i, j);
					}
				}
			}
		}
		for (int k = 0; k < 1000; k++) {
			for (int i = 0; i < vertices; i++) {
				ArrayList<Integer> inList = revMap.get(i);
				Double sum = pgRank[i];

				//int listSize = inList.size();
				for (int j = 0; j < vertices; j++) {
					if (inList.contains(j)) {
						sum = sum + pgRank[j] / g.outdegree(j);
					}
				}
				pgRank[i] = sum;
			}
		}
	}

	public Double getPageRank(int v){
		return pgRank[v];
	}

	public void printer(){

	}
}

class WebSearch {

}


public class Solution {
	static HashMap <Integer, ArrayList<Integer>> revMap = new HashMap <Integer, ArrayList<Integer>>();
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		// read the first line of the input to get the number of vertices
		ArrayList<Bag<Integer>> list = new ArrayList<Bag<Integer>>();

		int noOfVertices = Integer.parseInt(s.nextLine());
		Digraph g = new Digraph(noOfVertices);
		int count=0;
		for (int i = 0; i < noOfVertices; i++) {
			String[] tokens = s.nextLine().split(" ");
			Bag<Integer> bag = new Bag<Integer>();
			for (int j = 1 ; j < tokens.length; j++) {
				int id = Integer.parseInt(tokens[0]);
				int child = Integer.parseInt(tokens[j]);
				if (revMap.containsKey(child)) {
					ArrayList<Integer> arr = revMap.get(child);
					if (!arr.contains(id)) {
						arr.add(id);
						revMap.put(child, arr);
					}
				} else {
					ArrayList<Integer> n = new ArrayList<Integer>();
					n.add(id);
					revMap.put(child, n);
				}
				g.addEdge(id, child);
				count++;
				bag.add(j);
			}
			list.add(bag);

		}
		// iterate count of vertices times
		// to read the adjacency list from std input
		// and build the graph
		
		System.out.println(noOfVertices+" vertices, "+count+" edges");
		System.out.println("ITs done");
		g.toString();
		System.out.println("ITs done");

		PageRank pageRankObj = new PageRank(g, revMap);


		// Create page rank object and pass the graph object to the constructor
		pageRankObj.printer();

		// print the page rank object

		// This part is only for the final test case

		// File path to the web content
		String file = "WebContent.txt";

		// instantiate web search object
		// and pass the page rank object and the file path to the constructor

		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky

	}
}
