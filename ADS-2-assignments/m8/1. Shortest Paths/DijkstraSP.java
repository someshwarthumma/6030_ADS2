public class DijkstraSP {
    private Edge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;
    public DijkstraSP(EdgeWeightedGraph g, int s) {
        int vertices = g.vertices();
        edgeTo = new Edge[vertices];
        distTo = new double[vertices];
        pq = new IndexMinPQ<Double>(vertices);
        for (int v = 0; v < vertices; v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;
        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (Edge e : g.adj(v))
                relax(e);
        }
    }

    private void relax(Edge e) {
        int v = e.either(), w = e.other(v);
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else pq.insert (w, distTo[w]);
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }
}