import java.util.Scanner;
public class Solution {
	public static void main(String[] args) {
		String[] words = loadWords();
		//Your code goes here...
		Scanner s = new Scanner(System.in);
		String subtring = s.nextLine();
		TST tst = new TST();
		for(String each: words){
			int len = each.length();
			for(int n = 0; n < each.length(); n++){
				tst.put(each.substring(n,len), 0);
			}
		}

		/*for(String every: tst.keysWithPrefix(subtring)){
			System.out.println(every);
		}*/
		System.out.println(tst.keysWithPrefix(subtring));

	}

	public static String[] loadWords() {
		In in = new In("/Files/dictionary-algs4.txt");
		String[] words = in.readAllStrings();
		return words;
	}
}