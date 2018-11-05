class KrushkalMST {
	Double weight = 0.0;
	private Queue<Edge> mst = new Queue<Edge>();
	KrushkalMST(EdgeWeightedGraph g){
		//weight = 0.0;
		MinPQ<Edge> pq = new MinPQ<Edge>();
		for(Edge e: g.edges()){
			pq.insert(e);
		}
		UF uf = new UF(g.vertices());
		while(!pq.isEmpty() && mst.size() < g.vertices() - 1){
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
			if(!uf.connected(v, w)){
				uf.union(v, w);
				//this is mst
				mst.enqueue(e);
				weight += e.weight();
			}
		}
	}
	public Double totalWeight(){
		return weight;
	}
	public Iterable<Edge> edges() {
        return mst;
    }
}