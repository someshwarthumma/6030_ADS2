import java.util.Scanner;
class Solution{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int noOfVer = Integer.parseInt(scan.nextLine());
		int noOfEdges = Integer.parseInt(scan.nextLine());
		EdgeWeightedGraph g = new EdgeWeightedGraph(noOfVer);
		while(scan.hasNext()){
			String[] tokens = scan.nextLine().split(" ");
			g.addEdge(new Edge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Double.parseDouble(tokens[2])));
		}
		KrushkalMST krushkal = new KrushkalMST(g);
		System.out.println(krushkal.totalWeight());

	}
}