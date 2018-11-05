class EdgeWeightedGraph {
	private static final String NEWLINE = System.getProperty("line.separator");
	private int vertices;
	private int edges;
	private Bag<Edge>[] adj;
	EdgeWeightedGraph(int ver){
		this.vertices = ver;
		this.edges = 0;
		adj = (Bag<Edge>[]) new Bag[vertices];
		for(int i=0; i< vertices; i++){
			adj[i]= new Bag<Edge>();
		}
	}

	public int vertices(){
		return this.vertices;
	}
	public int noOfEdges(){
		return this.edges;
	}

	public void addEdge(Edge e){
		int first = e.either();
		int sec = e.other(first);
		adj[first].add(e);
		adj[sec].add(e);
		edges++;
	}

	public Iterable<Edge> adj(int v) {
        return adj[v];
    }
    public int degree(int ver){
    	return this.adj[ver].size();
    }
    public Iterable<Edge> edges(){
    	Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < vertices; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
                // only add one copy of each self loop (self loops will be consecutive)
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices + " " + edges + NEWLINE);
        for (int v = 0; v < vertices; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}