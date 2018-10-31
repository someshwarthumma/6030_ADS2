/**.
 * digraph class
 */
public class Digraph {
    /**.
     * newline
     */
    private static final String NEWLINE = System.getProperty("line.separator");
    /**.
     * vertex variable
     */
    private final int vertex;
    /**.
     * edge variable
     */
    private int edge;
    /**.
     * bag variable
     */
    private Bag<Integer>[] adj;
    /**.
     * indegree
     */
    private int[] indegree;
    /**.
     * Initializes an empty digraph with <em>V</em> vertices.
     * complexity is O(N)
     *
     * @param  ve the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public Digraph(final int ve) {
        if (ve < 0) {
            throw new IllegalArgumentException("Number is negative");
        }
        this.vertex = ve;
        this.edge = 0;
        indegree = new int[vertex];
        adj = (Bag<Integer>[]) new Bag[vertex];
        for (int v = 0; v < vertex; v++) {
            adj[v] = new Bag<Integer>();
        }
    }
    /**.
     * Returns the number of vertices in this digraph.
     * complexity is O(1)
     *
     * @return the number of vertices in this digraph
     */
    public int v() {
        return vertex;
    }

    /**.
     * Returns the number of edges in this digraph.
     * complexity is O(1)
     *
     * @return the number of edges in this digraph
     */
    public int e() {
        return edge;
    }
    /**.
     * method to validate vertex
     * complexity is O(1)
     *
     * @param      v     { parameter_description }
     */
    private void validateVertex(final int v) {
        if (v < 0 || v >= vertex) {
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (vertex - 1));
        }
    }

    /**.
     * Adds the directed edge v→w to this digraph.
     * complexity is O(1)
     *
     * @param  v the tail vertex
     * @param  w the head vertex
     * @throws IllegalArgumentException unless both
     */
    public void addEdge(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        indegree[w]++;
        edge++;
    }

    /**.
     * { adj method }
     * complexity is O(1)
     *
     * @param      v     { int }
     *
     * @return     { Iterator }
     */
    public Iterable<Integer> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }

    /**.
     * { out degree method }
     * complexity is O(1)
     *
     * @param      v     { v }
     *
     * @return     { int }
     */
    public int outdegree(final int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**.
     * { method for in degree }
     * complexity is O(1)
     *
     * @param      v     { v }
     *
     * @return     { int }
     */
    public int indegree(final int v) {
        validateVertex(v);
        return indegree[v];
    }

    /**.
     * Returns the reverse of the digraph.
     * complexity is O(N^2)
     *
     * @return the reverse of the digraph
     */
    public Digraph reverse() {
        Digraph reverse = new Digraph(vertex);
        for (int v = 0; v < vertex; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

    /**.
     * Returns a string representation of the graph.
     * complexity is O(N^2)
     *
     * @return String
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertex + " vertices, " + edge + " edges " + NEWLINE);
        for (int v = 0; v < vertex; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

}
