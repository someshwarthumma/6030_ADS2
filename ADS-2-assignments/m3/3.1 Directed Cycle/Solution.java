import java.util.Scanner;
/**.
 * Class for solution.
 */
final class Solution {
    /**.
     * solution
     */
    private Solution() {
        //Constructor
    }
    /**.
     * { main method to handle the input testcases }
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertex = Integer.parseInt(scan.nextLine());
        int edge = Integer.parseInt(scan.nextLine());
        Digraph diGraph = new Digraph(vertex);
        while (scan.hasNext()) {
            String[] tokens = scan.nextLine().split(" ");
            diGraph.addEdge(Integer.
                parseInt(tokens[0]), Integer.
                parseInt(tokens[1]));
        }
        DirectedCycle dc = new DirectedCycle(diGraph);
        if (dc.hasCycle()) {
            System.out.println("Cycle exists.");
        } else {
            System.out.println("Cycle doesn't exists.");
        }
    }
}
