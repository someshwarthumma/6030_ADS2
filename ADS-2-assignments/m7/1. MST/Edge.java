class Edge implements Comparable<Edge>{
	private int firstVertex;
	private int secondVertex;
	private Double weight;
	Edge(final int v, final int w, final Double wei){
		this.firstVertex = v;
		this.secondVertex = w;
		this.weight = wei;
	}

	public Double weight(){
		return this.weight;
	}
	public int either(){
		return firstVertex;
	}
	public int other(int v){
		if(firstVertex == v){
			return secondVertex;
		}
		return firstVertex;
	}
	public int compareTo(Edge that){
		return this.weight().compareTo(that.weight());
	}
}