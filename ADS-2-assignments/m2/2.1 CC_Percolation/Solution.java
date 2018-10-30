import java.util.Scanner;
/**.
 * Solution class
 */
class Solution {
    /**.
     * Constructor
     */
    private Solution(){
        //solution constructor
    }
    /**.
     * main method
     *
     * @param      args  The arguments
     */
    public static void main(String[] args) {
        /**
         * vertex variable
         */
        //private int vertex;
        Scanner sc = new Scanner(System.in);
        int vertex = sc.nextInt();
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
/**.
 * percolation class
 */
class Percolation {
    private int vertex;
    Percolation(int ertex) {
        this.vertex = ertex;
    }
    public boolean checkPerc(boolean[][] grid , Graph gr) {
        for (int i = 0 ; i < vertex ; i++) {
            for (int j = 0 ; j < vertex; j++) {
                if (grid[i][j]) {

                    int tmp = trans(i, j);
                    if (i == 0) {
                        gr.addEdge(tmp , vertex * vertex);
                    }
                    if (i == vertex - 1) {
                        gr.addEdge(tmp , vertex * vertex + 1);
                    }
                    if (i - 1 >= 0 && grid[i - 1][j]) {
                        gr.addEdge(tmp , trans(i - 1, j));
                    }
                    if (i + 1 < vertex  && grid[i + 1][j]) {
                        gr.addEdge(tmp , trans(i + 1, j));
                    }
                    if (j - 1 >= 0 && grid[i][j - 1]) {
                        gr.addEdge(tmp , trans(i, j - 1));
                    }
                    if (j + 1 < vertex && grid[i][j + 1]) {
                        gr.addEdge(tmp , trans(i, j + 1));
                    }
                }
            }
        }
        DepthFirstPaths d = new DepthFirstPaths(gr, vertex * vertex);
        return d.hasPathTo(vertex * vertex + 1);
    }
    /**.
     * methdo to translate the 2-d array to one d array
     *
     * @param      i     { index }
     * @param      j     { index }
     *
     * @return     { int }
     */
    private int trans(int i , int j) {
        return i * vertex + j;
    }
}


