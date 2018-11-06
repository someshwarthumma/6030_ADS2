import java.util.Scanner;
import java.util.HashMap;
/**.
 * solution class
 */
final class Solution {
    /**.
     * cosntructor
     */
    private Solution() {
        //constructor
    }
    /**.
     * main method
     * Complexity is O(N) to read the inputs
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner s = new Scanner(System.in);
        String[] details = s.nextLine().split(" ");
        int vertices = Integer.parseInt(details[0]);
        int edges = Integer.parseInt(details[1]);
        HashMap<String,
        Integer> map = new HashMap<String,
        Integer>(vertices);
        String[] tokens = s.nextLine().split(" ");
        for (int i = 0; i < vertices; i++) {
            map.put(tokens[i], i);
        }
        EdgeWeightedGraph graph = new EdgeWeightedGraph(vertices);
        for (int i = 0; i < edges; i++) {
            String[] tok = s.nextLine().split(" ");
            graph.addEdge(new Edge(map.get(
                tok[0]), map.get(tok[1]),
            Double.parseDouble(tok[2])));
        }
        shortestpath(graph, s, map);
    }
    /**.
     * method to read the query
     * and peform the operation
     * Complexity is N*E*Log(V);
     * N is no of inputs
     * E = edges
     * V = verteces
     * @param      g     { EdgeWeightedGraph }
     * @param      s     { scanner}
     * @param      map   Hash map
     */
    public static void shortestpath(
        final EdgeWeightedGraph g,
        final Scanner s,
        final HashMap<String, Integer> map) {
        int noOfQueries = Integer.parseInt(s.nextLine());
        for (int i = 0; i < noOfQueries; i++) {
            String[] tok = s.nextLine().split(" ");
            DijkstraUndirectedSP sp = new DijkstraUndirectedSP(
                g, map.get(tok[0]));
            System.out.println((int) sp.distTo(map.get(tok[1])));
        }
    }
}