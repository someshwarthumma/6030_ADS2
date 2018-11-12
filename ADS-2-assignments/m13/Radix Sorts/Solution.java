import java.util.Scanner;
import java.util.Arrays;
/**.
 * solution class
 */
final class Solution {
    /**.
     * Solution constructor
     */
    private Solution() {
        //Constructor
    }
    /**.
     * main methdo to handle the input testcases
     * time complexity is o(N) + O(W + N)
     * as it reads the inputs and sort
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = Integer.parseInt(scan.nextLine());
        String[] arr = new String[num];
        for (int i = 0; i < num; i++) {
            arr[i] = scan.nextLine();
        }
        int length = arr[0].length();
        LSD lsd = new LSD();
        lsd.sort(arr, length);
        System.out.println(Arrays.toString(arr));

    }

}
/**.
 * class to implement the lsd sort
 * time complexity is O(W *N)
 * W is the fixed length
 * N is the array length
 */
class LSD {
    /**.
     * Constructs the object.
     */
    LSD() {
        //constructor
    }
    /**.
     * sort method
     *
     * @param      a     { String array }
     * @param      w     { int }
     */
    public static void sort(final String[] a, final int w) {
        /**.
         * length of the given array
         */
        int n = a.length;
        final int t = 256;
        int re = t;   // extend ASCII alphabet size
        String[] aux = new String[n];
        for (int d = w - 1; d >= 0; d--) {
            // sort by key-indexed counting on dth character
            // compute frequency counts
            int[] count = new int[re + 1];
            for (int i = 0; i < n; i++) {
                count[a[i].charAt(d) + 1]++;
            }
            // compute cumulates
            for (int r = 0; r < re; r++) {
                count[r + 1] += count[r];
            }
            // move data
            for (int i = 0; i < n; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            // copy back
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
    }
}