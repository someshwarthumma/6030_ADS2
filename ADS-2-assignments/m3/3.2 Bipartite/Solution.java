import java.util.Scanner;
class Solution{
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int vertices = Integer.parseInt(s.nextLine());
		int edges = Integer.parseInt(s.nextLine());
		Graph g = new Graph(vertices);
		Bipartite b = new Bipartite(g);
		if (b.isBipartitie()) {
			System.out.println("Graph is bipartite");
		} else {
			System.out.println("Graph is not a bipartite");
		}
	}
}

class Bipartite {
	boolean[] marked;
	boolean[] color;
	int[] edgeTo;
	Stack<Integer> stack;
	boolean flag;
	Bipartite(Graph g) {
		marked = new boolean[g.vertices()];
		color = new boolean[g.vertices()];
		edgeTo = new int[g.vertices()];
		flag = true;
		for (int i = 0 ; i < g.vertices(); i++) {
			if (!marked[i]) {
				dfs(g, i);
			}
		}
	}

	private void dfs(final Graph graph, final int vertex) {
        marked[vertex] = true;
        for (int each : graph.adj(vertex)) {
            if (!flag) {
                return;
            }
            if (!marked[each]) {
                color[each] = !color[vertex];
                edgeTo[each] = vertex;
                dfs(graph, each);
            } else if (color[each] == color[vertex]) {
                flag = false;
                /*stack = new Stack<Integer>();
                stack.push(each);
                for (int j = vertex; j != each; j = edgeTo[j]) {
                    stack.push(j);
                }
                stack.push(each);*/
            }
        }
    }    

	public boolean isBipartitie(){
		return flag;
	}


}