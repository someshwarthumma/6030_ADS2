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
     *
     * @param  ve the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public Digraph(final int ve) {
        if (ve < 0) throw new IllegalArgumentException("Number is negative");
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
     *
     * @return the number of vertices in this digraph
     */
    public int V() {
        return vertex;
    }

    /**.
     * Returns the number of edges in this digraph.
     *
     * @return the number of edges in this digraph
     */
    public int E() {
        return edge;
    }


    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(final int v) {
        if (v < 0 || v >= vertex)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (vertex - 1));
    }

    /**.
     * Adds the directed edge v→w to this digraph.
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
     * Returns the vertices adjacent from vertex {@code v} in this digraph.
     *
     * @param  v the vertex
     * @return the vertices adjacent , as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }

    /**.
     * Returns the number of directed edges incident from vertex {@code v}.
     * This is known as the <em>outdegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**.
     * Returns the number of directed edges incident to vertex {@code v}.
     * This is known as the <em>indegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the indegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    /**.
     * Returns the reverse of the digraph.
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