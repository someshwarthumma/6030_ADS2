import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;


public class Solution {

	// Don't modify this method.
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String cases = scan.nextLine();

		switch (cases) {
		case "loadDictionary":
			// input000.txt and output000.txt
			BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
			while (scan.hasNextLine()) {
				String key = scan.nextLine();
				System.out.println(hash.get(key));
			}
			break;

		case "getAllPrefixes":
			// input001.txt and output001.txt
			T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
			while (scan.hasNextLine()) {
				String prefix = scan.nextLine();
				for (String each : t9.getAllWords(prefix)) {
					System.out.println(each);
				}
			}
			break;

		case "potentialWords":
			// input002.txt and output002.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			int count = 0;
			while (scan.hasNextLine()) {
				String t9Signature = scan.nextLine();
				for (String each : t9.potentialWords(t9Signature)) {
					count++;
					System.out.println(each);
				}
			}
			if (count == 0) {
				System.out.println("No valid words found.");
			}
			break;

		case "topK":
			// input003.txt and output003.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			Bag<String> bag = new Bag<String>();
			int k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				bag.add(line);
			}
			for (String each : t9.getSuggestions(bag, k)) {
				System.out.println(each);
			}

			break;

		case "t9Signature":
			// input004.txt and output004.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			bag = new Bag<String>();
			k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				for (String each : t9.t9(line, k)) {
					System.out.println(each);
				}
			}
			break;

		default:
			break;

		}
	}

	// Don't modify this method.
	public static String[] toReadFile(String file) {
		In in = new In(file);
		return in.readAllStrings();
	}

	public static BinarySearchST<String, Integer> loadDictionary(String file) {
		BinarySearchST<String, Integer>  st = new BinarySearchST<String, Integer>();
		String[] words = toReadFile(file);
		/*String[] temp = new String[words.length];
		ArrayList<String> list = new ArrayList<String>();
		int i=0;
		for(String each: words){
			each = each.toLowerCase();
			temp[i] = each;
			if(!list.contains(each)){
				list.add(each);
			}
			i++;

		}*/
		
		for(String each: words){
			each = each.toLowerCase();
			if(st.contains(each)){
				st.put(each, st.get(each) + 1);
			} else{
				st.put(each, 1);
			}

		}
		// your code goes here
		return st;
	}

}

class T9 {
	BinarySearchST<String, Integer> st;
		TST<Integer> tst;
	public T9(BinarySearchST<String, Integer> s) {
		// your code goes here
		st = s;
		tst = new TST<Integer>();
		for(String key: st.keys()){
			tst.put(key, st.get(key));
		}
	}

	// get all the prefixes that match with given prefix.
	public Iterable<String> getAllWords(String prefix) {
		// your code goes here
		return tst.keysWithPrefix(prefix);
	}

	public Iterable<String> potentialWords(String t9Signature) {
		// your code goes here
		return null;
	}

	// return all possibilities(words), find top k with highest frequency.
	public Iterable<String> getSuggestions(Iterable<String> words, int k) {
		// your code goes here
		/*MaxPQ<Integer> pq = new MaxPQ<Integer>();
		for(String each: words){
			int val = tst.get(each);
			pq.insert(val);
		}
		Bag<String> bag = new Bag<String>();
		for(int i =0;i<k;i++){
			int max = pq.delMax();
			for(String each: words){
				if(max == tst.get(each)){
					bag.add(each);
				}
			}
		}*/

		MaxPQ<Word> p = new MaxPQ<Word>();
		for(String each: words){
			int val = tst.get(each);
			p.insert(new Word(each,val));
		}
		Bag<String> bag = new Bag<String>();
		for(int i =0;i<k;i++){
			Word max = p.delMax();
			bag.add(max.getKey());
		}
		return bag; 
	}

	// final output
	// Don't modify this method.
	public Iterable<String> t9(String t9Signature, int k) {
		return getSuggestions(potentialWords(t9Signature), k);
	}

}
class Word implements Comparable<Word> {
	Integer val;
	String key;
	Word(String k, Integer v){
		this.val = v;
		this.key = k;
	}
	public String getKey(){
		return key;
	}
	public Integer getVal(){
		return val;
	}
	public int compareTo(Word that){
		return val.compareTo(that.getVal());
	}
}
