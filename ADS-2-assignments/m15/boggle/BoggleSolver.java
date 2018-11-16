import java.util.ArrayList;
public class BoggleSolver {
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	TST<Integer> tst;
	ArrayList<String> list;
	int rows;
	int columns;
	BoggleBoard board;
	public BoggleSolver(String[] dictionary) {
		tst = new TST<Integer>();
		list = new ArrayList<String>();
		for (int i = 0; i < dictionary.length; i++) {
			tst.put(dictionary[i], 0);
		}
	}

	private boolean isValid(String word) {
		return tst.isAPrefix(word);
		/*Queue<String> queue = tst.keysWithPrefix(word);
		if(queue.size()==0) {
			return false;
		}
		return true;*/
	}
	private String getString(int i, int j) {
		char a = board.getLetter(i, j);

		if (a == 'Q') {
			return "QU";
		}
		return a + "";
	}
	// Returns the set of all valid words in the given Boggle board, as an Iterable.
	public ArrayList<String> getAllValidWords(BoggleBoard board) {
		this.rows = board.rows();
		this.columns = board.cols();
		this.board = board;
		boolean[][] visited;
		visited = new boolean[board.rows()][board.cols()];
		for (int i = 0; i < board.rows(); i++) {
			for (int j = 0; j < board.cols(); j++) {
				if (checkIndex(i, j)) {
					dfs(board, i, j, getString(i, j), visited);
				}
			}
		}
		return list;
	}
	private boolean checkIndex(int i , int j) {
		if (i < 0 || i >= rows || j < 0 || j >= columns) {
			return false;
		}
		return true;
	}
	private void dfs(BoggleBoard board, int i, int j, String word, boolean[][] visited) {
		if (!isValid(word)) {
			return;
		}
		visited[i][j] = true;
		if (tst.contains(word) && (!list.contains(word)) && word.length() > 2) {
			list.add(word);
		}
		if (checkIndex(i + 1, j + 1) && !visited[i + 1][j + 1]) {
			dfs(board, i + 1, j + 1, word + getString(i + 1, j + 1), visited);
		}
		if (checkIndex(i - 1, j - 1) && !visited[i - 1][j - 1]) {
			dfs(board, i - 1, j - 1, word + getString(i - 1, j - 1), visited);
		}
		if ( checkIndex(i - 1, j + 1) && !visited[i - 1][j + 1]) {
			dfs(board, i - 1, j + 1, word + getString(i - 1, j + 1), visited);
		}
		if ( checkIndex(i + 1, j - 1) && !visited[i + 1][j - 1]) {
			dfs(board, i + 1, j - 1, word + getString(i + 1, j - 1), visited);
		}
		if ( checkIndex(i - 1, j) && !visited[i - 1][j]) {
			dfs(board, i - 1, j, word + getString(i - 1, j), visited);
		}
		if ( checkIndex(i + 1, j) && !visited[i + 1][j]) {
			dfs(board, i + 1, j, word + getString(i + 1, j), visited);
		}
		if ( checkIndex(i, j + 1) && !visited[i][j + 1]) {
			dfs(board, i, j + 1, word + getString(i, j + 1), visited);
		}
		if (checkIndex(i, j - 1) && !visited[i][j - 1]) {
			dfs(board, i, j - 1, word + getString(i, j - 1), visited);
		}
		visited[i][j] = false;
	}
	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A through Z.)
	public int scoreOf(String word) {
		int len = word.length();
		if (len >= 8) {
			return 11;
		} else if (len == 7) {
			return 5;
		} else if (len == 6) {
			return 3;
		} else if (len == 5) {
			return 2;
		} else if (len >= 3) {
			return 1;
		} else if (len >= 0) {
			return 0;
		}
		return 0;
	}
}
