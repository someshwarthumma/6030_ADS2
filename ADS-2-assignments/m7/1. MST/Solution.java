import java.util.Scanner;
/**.
 * solution class
 */
final class Solution {
	/**.
	 * solution constructor
	 */
	private Solution() {
		//Solution constructor
	}
	/**.
	 * main method
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		int noOfVer = Integer.parseInt(scan.nextLine());
		int noOfEdges = Integer.parseInt(scan.nextLine());
		EdgeWeightedGraph g = new EdgeWeightedGraph(noOfVer);
		while (scan.hasNext()) {
			String[] tokens = scan.nextLine().split(" ");
			g.addEdge(new Edge(Integer.parseInt(
				tokens[0]), Integer.parseInt(
				tokens[1]), Double.parseDouble(
				tokens[2])));
		}
		KrushkalMST krushkal = new KrushkalMST(g);
		System.out.format("%.5f", krushkal.totalWeight());

	}
}