import java.util.NoSuchElementException;
/**.
 * Class for graph.
 */
public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");
    /**.
     * vertex
     */
    private final int v;
    /**.
     * edge
     */
    private int E;
    /**.
     * bag variable
     */
    private Bag<Integer>[] adj;
    /**.
     * Initializes an empty graph with vertices and 0 edges.
     * param V the number of vertices
     *
     * @param  v number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public Graph(final int v) {
        if (v < 0) throw new IllegalArgumentException(
            "Number of vertices must be nonnegative");
        this.v = v;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[v];
        for (int ve = 0; ve < v; ve++) {
            adj[ve] = new Bag<Integer>();
        }
    }
    /**.
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int V() {
        return v;
    }
    /**.
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int E() {
        return E;
    }
    /**.
     * method to validate the vertex
     *
     * @param      ve     { int }
     */
    private void validateVertex(final int ve) {
        if (ve < 0 || ve >= v)
            throw new IllegalArgumentException(
                "vertex " + ve + " is not between 0 and " + (v - 1));
    }
    /**.
     * Adds the undirected edge v-w to this graph.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IllegalArgumentException when illigal argument
     */
    public void addEdge(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }
    /**.
     * Returns the vertices adjacent to vertex {@code v}.
     *
     * @param  v the vertex
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }

    /**.
     * Returns the degree of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int degree(final int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**.
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        for (int i : adj[v]) {
            if (i == w) {
                return true;
            }
        }
        return false;
    }
    /**.
     * Returns a string representation of this graph.
     *
     * @return String
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(v + " vertices, " + E + " edges " + NEWLINE);
        for (int ve = 0; ve < v; ve++) {
            s.append(ve + ": ");
            for (int w : adj[ve]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}