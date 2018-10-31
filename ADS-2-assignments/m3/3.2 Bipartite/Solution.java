import java.util.Scanner;
/**.
 * solution class
 */
final class Solution {
    /**.
     * Solution constructor
     */
    private Solution() {
        //Constructor
    }
    /**.
     * main method
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner s = new Scanner(System.in);
        int vertices = Integer.parseInt(s.nextLine());
        int edges = Integer.parseInt(s.nextLine());
        Graph g = new Graph(vertices);
        while (s.hasNext()) {
            String[] t = s.nextLine().split(" ");
            g.addEdge(Integer.
                parseInt(t[0]), Integer.
                parseInt(t[1]));
        }
        Bipartite b = new Bipartite(g);
        if (b.isBipartite()) {
            System.out.println("Graph is bipartite");
        } else {
            System.out.println("Graph is not a bipartite");
        }
    }
}
/**.
 * Biprtitte class
 */
class Bipartite {
    /**.
     * marked array
     */
    private boolean[] marked;
    /**.
     * color array
     */
    private boolean[] color;
    /**.
     * edgeTo array of int type
     */
    private int[] edgeTo;
    /**.
     * flag
     */
    private boolean flag;
    /**.
     * Bipartite
     *
     * @param      g     { parameter_description }
     */
    Bipartite(final Graph g) {
        marked = new boolean[g.vertices()];
        color = new boolean[g.vertices()];
        edgeTo = new int[g.vertices()];
        flag = true;
        for (int i = 0; i < g.vertices(); i++) {
            if (!marked[i]) {
                dfs(g, i);
            }
        }
    }
    /**.
     * dfs method
     *
     * @param      g       { parameter_description }
     * @param      vertex  The vertex
     */
    public void dfs(final Graph g, final int vertex) {
        marked[vertex] = true;
        if (!flag) {
            return;
        }
        for (int each : g.adj(vertex)) {
            if (!marked[each]) {
                color[each] = !color[vertex];
                edgeTo[each] = vertex;
                dfs(g, each);
            } else if (color[each] == color[vertex]) {
                flag = false;
            }
        }
    }
    /**.
     * check weather bipartite or not
     *
     *
     * @return     True if bipartite, False otherwise.
     */
    public boolean isBipartite() {
        return flag;
    }


}
