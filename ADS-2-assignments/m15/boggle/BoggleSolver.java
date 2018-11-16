import java.util.ArrayList;
/**.
 * boggle solver
 */
public class BoggleSolver {
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    /**.
     * Tst class object
     */
	private TST<Integer> tst;
    /**.
     * Arraylist
     */
	private ArrayList<String> list;
    /**.
     * rows
     */
	private int rows;
    /**.
     * columns
     */
	private int columns;
    /**.
     * boggle board
     */
	private BoggleBoard board;
    /**.
     * constructor of goggle solver
     *
     * @param      dictionary  The dictionary
     */
	public BoggleSolver(final String[] dictionary) {
		tst = new TST<Integer>();
		list = new ArrayList<String>();
		for (int i = 0; i < dictionary.length; i++) {
			tst.put(dictionary[i], 0);
		}
	}
    /**.
     * isValid method to check weather valid or not.
     *
     * @param      word  The word
     *
     * @return     True if valid, False otherwise.
     */
	private boolean isValid(String word) {
		return tst.isAPrefix(word);
		/*Queue<String> queue = tst.keysWithPrefix(word);
		if(queue.size()==0) {
			return false;
		}
		return true;*/
	}
    /**.
     * method to return the string
     * that is if the char at the given location is Q
     * then it will return the QU
     *
     * @param      i     { index of type int }
     * @param      j     { int }
     *
     * @return     The string.
     */
	private String getString(final int i, int j) {
		char a = board.getLetter(i, j);

		if (a == 'Q') {
			return "QU";
		}
		return a + "";
	}
    /**.
     * method to find all valid words
     *
     * @param      board  The board
     *
     * @return     All valid words.
     */
	public ArrayList<String> getAllValidWords(final BoggleBoard board) {
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
    /**.
     * method to check for the valid indx
     *
     * @param      i     { int }
     * @param      j     { int }
     *
     * @return     { boolean }
     */
	private boolean checkIndex(final int i , final int j) {
		if (i < 0 || i >= rows || j < 0 || j >= columns) {
			return false;
		}
		return true;
	}
    /**.
     * dfs method
     *
     * @param      board    The board
     * @param      i        { index of int}
     * @param      j        { index of int }
     * @param      word     The word of String
     * @param      visited  The visited boolean[][]
     */
	private void dfs(final BoggleBoard board, final int i, final int j, final String word, final boolean[][] visited) {
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
    /**.
     * method to find the score of the given word
     *
     * @param      word  The word
     *
     * @return     { int }
     */
	public int scoreOf(final String word) {
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
