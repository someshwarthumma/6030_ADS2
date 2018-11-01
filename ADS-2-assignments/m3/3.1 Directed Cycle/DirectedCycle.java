/**
 * Class for directed cycle.
 */
public class DirectedCycle {
    /**
     * { marked }.
     */
    private boolean[] marked;
    /**
     * { edgeto variable }.
     */
    private int[] edgeTo;
    /**
     * { onstack variable }.
     */
    private boolean[] onStack;
    /**
     * { stack variable }.
     */
    private Stack<Integer> cycle;
    boolean flag;

    /**
     * Determines whether the digraph {@code G} has a directed cycle and, if so
     * finds such a cycle.
     * complexity is O(1)
     * @param graph the digraph
     */
    public DirectedCycle(final Digraph graph) {
        flag = false;
        marked  = new boolean[graph.v()];
        onStack = new boolean[graph.v()];
        edgeTo  = new int[graph.v()];
        for (int v = 0; v < graph.v(); v++) {
            if (!marked[v] && cycle == null) {
                dfs(graph, v);
            }
        }
    }
    /**
     * { dfs method }.
     * complexity is O(V+E) where E=edges V=vertices
     *
     * @param      graph     { graph }
     * @param      v     { int }
     */
    private void dfs(final Digraph graph, final int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : graph.adj(v)) {
            if(flag==true){
                return;
            }

            // short circuit if directed cycle found
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(graph, w);
            } else if (onStack[w]) {

                cycle = new Stack<Integer>();
                flag = true;
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);

            }
        }
        onStack[v] = false;
    }
    /**
     * Determines if it has cycle.
     * complexity is O(1)
     *
     * @return     True if has cycle, False otherwise.
     */
    public boolean hasCycle() {
        return flag;
        //return cycle != null;
    }

    /**
     * Returns a string representation of the object.
     * complexity is O(1)
     *
     * @return     String representation of the object.
     */
    public String toString() {
        if (hasCycle()) {
            return "Cycle exists.";
        } else {
            return "Cycle doesn't exists.";
        }

    }
}


