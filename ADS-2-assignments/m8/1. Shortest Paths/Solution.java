import java.util.Scanner;
import java.util.HashMap;
class Solution{
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String[] details = s.nextLine().split(" ");
		int vertices = Integer.parseInt(details[0]);
		int edges = Integer.parseInt(details[1]);
		HashMap<String, Integer> map = new HashMap<String, Integer>(vertices);
		String[] tokens = s.nextLine().split(" ");
		for(int i = 0; i< vertices; i++){
			map.put(tokens[i], i);
		}
		EdgeWeightedGraph graph = new EdgeWeightedGraph(vertices);
		for(int i =0; i< edges; i++){
			String[] tok = s.nextLine().split(" ");
			graph.addEdge(new Edge(map.get(tok[0]), map.get(tok[1]), Double.parseDouble(tok[2])));
		}
		operations(graph , s, map);
	}

	public static void operations(EdgeWeightedGraph g, Scanner s, HashMap<String, Integer> map){
		int noOfQueries = Integer.parseInt(s.nextLine());
		for(int i = 0; i< noOfQueries; i++){
			String[] tok = s.nextLine().split(" ");
			DijkstraUndirectedSP sp = new DijkstraUndirectedSP(g, map.get(tok[0]));
			System.out.println((int)sp.distTo(map.get(tok[1])));
		}
	}
}