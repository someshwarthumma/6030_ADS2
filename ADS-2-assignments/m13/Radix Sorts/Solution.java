import java.util.Scanner;
import java.util.Arrays;
class Solution {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int num = Integer.parseInt(scan.nextLine());
		String[] arr = new String[num];
		for(int i =0; i< num; i++){
			arr[i] = scan.nextLine();
		}
		int length = arr[0].length();
		LSD lsd = new LSD();
		lsd.sort(arr, length);
		System.out.println(Arrays.toString(arr));

	}

}

class LSD {
	public static void sort(String[] a, int w) {
		int n = a.length;
		int R = 256;   // extend ASCII alphabet size
		String[] aux = new String[n];

		for (int d = w - 1; d >= 0; d--) {
			// sort by key-indexed counting on dth character

			// compute frequency counts
			int[] count = new int[R + 1];
			for (int i = 0; i < n; i++)
				count[a[i].charAt(d) + 1]++;

			// compute cumulates
			for (int r = 0; r < R; r++)
				count[r + 1] += count[r];

			// move data
			for (int i = 0; i < n; i++)
				aux[count[a[i].charAt(d)]++] = a[i];

			// copy back
			for (int i = 0; i < n; i++)
				a[i] = aux[i];
		}
	}
}