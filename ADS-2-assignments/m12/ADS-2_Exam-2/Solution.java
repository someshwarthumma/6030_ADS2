import java.util.Scanner;
import java.util.ArrayList;
public class Solution {

	public static void main(String[] args) {
		// Self loops are not allowed...
		// Parallel Edges are allowed...
		// Take the Graph input here...
		Scanner s = new Scanner(System.in);
		int noOfCities = Integer.parseInt(s.nextLine());
		int noOfRoads = Integer.parseInt(s.nextLine());
		//System.out.println("Cities: "+noOfCities+" Roads: "+noOfRoads);
		EdgeWeightedGraph graph = new EdgeWeightedGraph(noOfCities);
		for(int i =0; i< noOfRoads;i++){
			String[] tok = s.nextLine().split(" ");
			graph.addEdge(new Edge(Integer.parseInt(tok[0]),
				Integer.parseInt(tok[1]),
				Double.parseDouble(tok[2])));
		}

		String caseToGo = s.nextLine();
		switch (caseToGo) {
		case "Graph":
			//Print the Graph Object.
			System.out.println(graph.toString());
			break;

		case "DirectedPaths":
			// Handle the case of DirectedPaths, where two integers are given.
			// First is the source and second is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			String[] data = s.nextLine().split(" ");
			int from = Integer.parseInt(data[0]);
			int to = Integer.parseInt(data[1]);
			DijkstraUndirectedSP dij = new DijkstraUndirectedSP(graph , from);
			if(dij.hasPathTo(to)){
				Double sum = 0.0;
				for(Edge e: dij.pathTo(to)){
					sum += e.weight();
				}
				System.out.println(sum);
			} else {
				System.out.println("No Path Found.");
			}
			break;

		case "ViaPaths":
			// Handle the case of ViaPaths, where three integers are given.
			// First is the source and second is the via is the one where path should pass throuh.
			// third is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			data = s.nextLine().split(" ");
			from = Integer.parseInt(data[0]);
			int via = Integer.parseInt(data[1]);
			to = Integer.parseInt(data[2]);
			DijkstraUndirectedSP djFV = new DijkstraUndirectedSP(graph , from);
			DijkstraUndirectedSP djVT = new DijkstraUndirectedSP(graph , via);
			if(djFV.hasPathTo(via) && djVT.hasPathTo(to)){
				//System.out.println("path found");
				Double sum = 0.0;
				ArrayList<Integer> list = new ArrayList<Integer>();
				/*for(Edge e : djFV.pathTo(via)){
					sum += e.weight();
					int v = e.either();
					list.add(e.other(v));
				}
				for(Edge e : djVT.pathTo(to)){
					sum += e.weight();
					int v = e.either();
					list.add(e.other(v));
				}*/
				DijkstraUndirectedSP dj = new DijkstraUndirectedSP(graph , from);
				for(Edge e: dj.pathTo(to)){
					//sum += e.weight();
					int v = e.either();
					list.add(e.other(v));
				}
				System.out.println(sum);
				for(int j = 0; j< list.size()-1; j++){
					System.out.print(list.get(j)+" ");
				}
				System.out.println(list.get(list.size()-1));

			} else {
				System.out.println("No Path Found.");
			}
			break;

		default:
			break;
		}

	}
}