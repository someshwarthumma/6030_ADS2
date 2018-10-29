import java.util.Scanner;
/**.
 * Interface for graph.
 */
interface Graph {
    /**.
     * variable for v
     *
     * @return     { int }
     */
    int v();
    /**.
     * edge variable
     *
     * @return     { int }
     */
    int e();
    /**.
     * method to add the edge
     *
     * @param      v     { vertex one }
     * @param      w     { vertex two }
     */
    void addEdge(int v, int w);
    /**.
     * Iterable object
     *
     * @param      v     { vertex }
     *
     * @return     { int }
     */
    Iterable<Integer> adj(int v);
    /**.
     * methd to check the edge
     *
     * @param      v     { vetex }
     * @param      w     { vertex }
     *
     * @return     True if has edge, False otherwise.
     */
    boolean hasEdge(int v, int w);
}
/**.
 * Class for graph adt.
 */
class GraphADT implements Graph {
    /**.
     * vertes node
     */
    private int ver;
    /**.
     * edge variable
     */
    private int ed;
    /**.
     * bag variable
     */
    private Bag<Integer>[] adj;
    /**.
     * grapgh Adt
     */
    protected GraphADT() {

    }
    /**.
     * grapgh constructor
     *
     * @param      v     { int }
     */
    GraphADT(final int v) {
        this.ver = v;
        this.ed = 0;
        adj = (Bag<Integer>[]) new Bag[ver];
        for (int i = 0; i < ver; i++) {
            adj[i] = new Bag<Integer>();
        }
    }
    /**.
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int v() {
        return ver;
    }

    /**.
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int e() {
        return ed;
    }
    /**.
     * Adds an edge.
     *
     * @param      v     { vertex }
     * @param      w     { vertex }
     */
    public void addEdge(final int v, final int w) {
        if (v == w) {
            return;
        }
        if (!hasEdge(v, w)) {
            ed++;
        }
        adj[v].add(w);
        adj[w].add(v);
    }
    /**.
     * { function_description }
     *
     * @param      v     { vertex }
     *
     * @return     { int }
     */
    public Iterable<Integer> adj(final int v) {
        return adj[v];
    }
    /**.
     * Determines if it has edge.
     *
     * @param      v     { vertex }
     * @param      w     { vertex }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        for (int k : adj[v]) {
                if (k == w) {
                    return true;
                }
        }
        return false;
    }
    /**.
     * To display the list.
     *
     * @param      v2          { vertex }
     * @param      e2          { edge }
     * @param      tokens     The tokens
     *
     * @throws     Exception  { if no edges are present }
     */
    public void listdisplay(final int v2,
        final int e2, final String[] tokens) throws Exception {
        if (e2 <= 1 && v2 <= 1) {
            System.out.println(v() + " vertices" + ", " + e() + " edges");
            throw new Exception("No edges");
        } else {
            System.out.println(v() + " vertices" + ", " + e() + " edges");
            for (int i = 0; i < tokens.length; i++) {
            String str = "";
            str = tokens[i] + ": ";
            for (int k : adj(i)) {
                str = str + tokens[k] + " ";
            }
            System.out.println(str);
            }
        }
    }

    /**.
     * to display the matrix.
     *
     * @param      v1          { vertex }
     * @param      e1          { edge }
     *
     * @throws     Exception  { no edges }
     */
    public void matrixdisplay(final int v1, final int e1) throws Exception {
        if (e1 <= 1 && v1 <= 1) {
            System.out.println(v() + " vertices" + ", " + e() + " edges");
            throw new Exception("No edges");
        } else {
            System.out.println(v() + " vertices" + ", " + e() + " edges");
            int[][] disp = new int[ver][ver];
            for (int i = 0; i  < ver; i++) {
                for (int j = 0; j < ver; j++) {
                    if (hasEdge(i, j)) {
                        disp[i][j] = 1;
                    }
                }
            }

            for (int i = 0; i < ver; i++) {
                for (int j = 0; j < ver; j++) {
                    System.out.print(disp[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
/**.
 * Client class.
 */
public final class Solution {
    /**.
     * Constructs the object.
     */
    protected Solution() {
        //Empty Constructer.
    }
    /**.
     * Client function.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        GraphADT graph = new GraphADT();
        String str = scan.nextLine();
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        String[] data = scan.nextLine().split(",");
        graph = new GraphADT(vertices);
        // System.out.println(Arrays.toString(data));
        while (scan.hasNext()) {
            String connect = scan.nextLine();
            String[] connector = connect.split(" ");
            // System.out.println(Arrays.toString(connector));
            graph.addEdge(Integer.parseInt(connector[0]),
            Integer.parseInt(connector[1]));
        }
        switch (str) {
            case "List":
            try {
                graph.listdisplay(vertices, edges, data);
            } catch (Exception p) {
                System.out.println(p.getMessage());
            }
            break;
            case "Matrix":
            try {
                graph.matrixdisplay(vertices, edges);
            } catch (Exception p) {
                System.out.println(p.getMessage());
            }
            break;
            default:
            break;
        }
    }
}
