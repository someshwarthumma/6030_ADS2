public class SAP {
    /**
     *variable description;
     */
    private final Digraph graph;
    /**
     *variable description;
     */
    private int distanceGlobal;
    /**
     * Constructs the object.
     *
     * @param      graph  The graph
     */
    public SAP(final Digraph graph) {
        this.graph = graph;
        this.distanceGlobal = 0;
    }
    /**
     * shortest path finding method.
     *
     * @param      v  Iterable integer.
     * @param      w  Iterable integer.
     *
     * @return  distance
     */
    public int length(final Iterable<Integer> v,
                      final Iterable<Integer> w) {
        ancestor(v, w);
        return distanceGlobal;
    }
    /**
     * ancestor finding method.
     *
     * @param      v  Iterable integer.
     * @param      w  Iterable integer.
     *
     * @return ancestor
     */

    public int ancestor(
        final Iterable<Integer> v, final Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfsV =
            new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths bfsW =
            new BreadthFirstDirectedPaths(graph, w);
        distanceGlobal = Integer.MAX_VALUE;
        int ancestors = -1;
        for (int i = 0; i < graph.v(); i++) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                int distance = bfsV.distTo(i) + bfsW.distTo(i);
                if (distance < distanceGlobal) {
                    //shortPath = distance;
                    distanceGlobal = distance;
                    ancestors = i;
                }
                // return ancestor;
            }
        }
        return ancestors;
    }
}