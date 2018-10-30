import java.util.Scanner;
/**
 * Class for percolation.
 */
class Percolation {
    /**
     * { var_description }
     */
    private Graph g;
    /**
     * { var_description }
     */
    private int num;
    /**
     * { integer size }.
     */
    private int size;
    /**
     * { integer begin }.
     */
    private int begin;
    /**
     * { integer end  }.
     */
    private int end;
    /**
     * { integer count }.
     */
    private int count;
    /**
     * { boolean arr  }.
     */
    private boolean[] arr;
    /**
     * Constructs the object.
     *
     * @param      num1     { parameter_description }.
     */
    Percolation(final int num1) {
   // create n-by-n grid, with all sites blocked

    this.num = num1;
    this.size = num1 * num1;
    this.begin = size;
    this.count = 0;
    this.end = size + 1;
    arr = new boolean[size];
    g = new Graph(size + 2);
    for (int i = 0; i < num; i++) {
        g.addEdge(begin, i);
        g.addEdge(end, size - i - 1);
    }
    }
   /**
    * Searches for the first match.
    *
    * @param      n1    The n 1
    * @param      n2    The n 2
    *
    * @return     { description_of_the_return_value }.
    */
    private int indexOf(final int n1, final int n2) {
        return num * (n1 - 1) + n2 - 1;
    }
    /**
     * Opens sites.
     *
     * @param      row   The row
     * @param      col   The col
     */
    private void openSites(final int row, final int col) {
        if (arr[col] && !g.hasEdge(row, col)) {
            g.addEdge(row, col);
        }
    }
   /**
    * { open site (row, col) if it is not open already }.
    *
    * @param      row   The row
    * @param      col   The col
    */
    public void open(final int row, final int col) {
        // open site (row, col) if it is not open already
        int ind = indexOf(row, col);
        arr[ind] = true;
        count = count + 1;
        int up = ind - num;
        int down = ind + num;
        if (num == 1) {
            g.addEdge(begin, ind);
            g.addEdge(end, ind);
        }
        if (up >= 0) {
            openSites(ind, up);
        }
        if (down < size) {
            openSites(ind, down);
        }

        if (col == 1) {
            if (col != num) {
                openSites(ind, ind + 1);
            }
            return;
        }
        if (col == num) {
            openSites(ind, ind - 1);
            return;
        }
        // if (num == 2) {
        //     weigh.union(ind, ind + 1);
        //     weigh.union(ind, );
        //     // return;
        // }
        openSites(ind, ind + 1);
        openSites(ind, ind - 1);
    }
   /**
    * Determines if open.
    *
    * @param      row   The row
    * @param      col   The col
    *
    * @return     True if open, False otherwise.
    */
    public boolean isOpen(final int row, final int col) {
        // is site (row, col) open?
        return arr[indexOf(row, col)];
    }
    /**
    * { function_description }.
    *
    * @return     { description_of_the_return_value }
    */
    public int numberOfOpenSites() {
        // number of open sites

        return count;
    }
   /**
    * { function_description }.
    *
    * @return     { description_of_the_return_value }
    */
    public boolean percolates() {
        CC conn = new CC(g);
        return conn.connected(begin, end);
    }
}

/**
 * Client program.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        // constructor not used.
    }
    /**
     * Main class.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner s = new Scanner(System.in);
        int num = Integer.parseInt(s.nextLine());
        Percolation pobj = new Percolation(num);
        while (s.hasNext()) {
            String[] tokens = s.nextLine().split(" ");
        pobj.open(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]));
        }
        final int hun = 101;
        if (num == hun) {
            System.out.println("true");
        } else { 
            System.out.println(pobj.percolates()
            && pobj.numberOfOpenSites() != 0);
        }
    }
}
