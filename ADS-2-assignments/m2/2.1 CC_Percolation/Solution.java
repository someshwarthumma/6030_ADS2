import java.util.Scanner;
class Solution {
    public static void main(String[] args) {
        int vertex;
        Scanner sc = new Scanner(System.in);
        vertex = sc.nextInt();
        Percolation p = new Percolation(vertex);
        boolean[][] grid = new boolean[vertex][vertex];
        Graph gr = new Graph(vertex * vertex + 2);
        try {
            while (true) {
                int row = sc.nextInt(), col = sc.nextInt();
                grid[row - 1][col - 1] = true;
            }
        } catch (Exception e) {
        } finally {
            System.out.println(p.checkPerc(grid, gr));
        }
    }
}
class Percolation {
    int vertex;
    Percolation(int ertex) {
        this.vertex = ertex;
    }
    public boolean checkPerc(boolean[][] grid , Graph gr) {
        for (int i = 0 ; i < vertex ; i++) {
            for (int j = 0 ; j < vertex; j++) {
                if (grid[i][j]) {

                    int tmp = trans(i, j);
                    if (i == 0) gr.addEdge(tmp , vertex * vertex);
                    if (i == vertex - 1)gr.addEdge(tmp , vertex * vertex + 1);
                    if (i - 1 >= 0 && grid[i - 1][j]) gr.addEdge(tmp , trans(i - 1, j));
                    if (i + 1 < vertex  && grid[i + 1][j]) gr.addEdge(tmp , trans(i + 1, j));
                    if (j - 1 >= 0 && grid[i][j - 1]) gr.addEdge(tmp , trans(i, j - 1));
                    if (j + 1 < vertex && grid[i][j + 1]) gr.addEdge(tmp , trans(i, j + 1));
                }
            }
        }
        DepthFirstPaths d = new DepthFirstPaths(gr, vertex * vertex);
        return d.hasPathTo(vertex * vertex + 1);
    }
    private int trans(int i , int j) {
        return i * vertex + j;
    }
}


