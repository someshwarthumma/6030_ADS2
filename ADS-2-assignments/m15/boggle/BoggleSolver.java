import java.util.ArrayList;
public class BoggleSolver {
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	String[] dict;
	int column;
	int rows;
	ArrayList<String> bag;
	BoggleBoard board;
	static TST<Integer> tst;
	public BoggleSolver(final String[] dictionary) {
		this.dict = dictionary;
		tst = new TST<Integer>();
		for(String each: dict){
			tst.put(each,0);
		}
	}
	// Returns the set of all valid words in the given Boggle board, as an Iterable.
	public Iterable<String> getAllValidWords(BoggleBoard board) {
		bag = new ArrayList<String>();
		this.board = board;
		column = board.cols();
		
		rows = board.rows();
		boolean[][] marked = new boolean[rows][column];
		for(int i=0; i < rows ; i++){
			for( int j=0; j< column; j++){
				marked = new boolean[board.rows()][board.cols()];
				dfs(board, i, j, board.getLetter(i, j)+"", marked);
			}
			marked = new boolean[board.rows()][board.cols()];
		}
		return bag;
	}
	private boolean isValid(String word){
		Queue<String> temp = tst.keysWithPrefix(word);
		if(temp.size()==0){
			return false;
		}
		return true;
	}
	private boolean checkIndex(int i, int j){
		if(i<0 || i >= rows || j <0 || j>= column){
			return false;
		}
		return true;
	}
	private String getChar(int i, int j){
		char a = board.getLetter(i, j);
		if(a == 'Q'){
			return "QU";
		}
		return a+"";
	}

	private void dfs(BoggleBoard board, int i, int j, String word, boolean[][] marked){
		if(i<0 || i >= rows || j <0 || j>= column){
			return;
		}
		//String word;
		
		marked[i][j] = true;
		if(tst.contains(word) && !bag.contains(word) && word.length() >2){
			bag.add(word);
		}
		if(isValid(word)){
			if(checkIndex(i+1,j+1) && !marked[i+1][j+1]){
				dfs(board, i+1, j+1, word+getChar(i+1, j+1), marked);
				marked[i+1][j+1] = false;
			}
			if(checkIndex(i-1, j-1) && !marked[i-1][j-1]){
				dfs(board, i-1, j-1, word+getChar(i-1, j-1), marked);
				marked[i-1][j-1] = false;
			}
			if(checkIndex(i-1, j+1) && !marked[i-1][j+1]){
				dfs(board, i-1, j+1, word+getChar(i-1, j+1), marked);
				marked[i-1][j+1] = false;
			}
			if(checkIndex(i+1, j-1) && !marked[i+1][j-1]){
				dfs(board, i+1, j+1, word+getChar(i+1, j-1), marked);
				marked[i+1][j-1] = false;
			}
			if(checkIndex(i-1, j) && !marked[i-1][j]){
				dfs(board, i-1, j, word+getChar(i-1, j), marked);
				marked[i-1][j] = false;
			}

			if(checkIndex(i+1, j) && !marked[i+1][j]){
				dfs(board, i+1, j, word+getChar(i+1, j), marked);
				marked[i+1][j] = false;
			}
			if(checkIndex(i, j+1) && !marked[i][j+1]){
				dfs(board, i, j+1, word+getChar(i, j+1), marked);
				marked[i][j+1] = false;
			}
			if(checkIndex(i, j-1) && !marked[i][j-1]){
				dfs(board, i, j-1, word+getChar(i, j-1), marked);
				marked[i][j-1] =false;
			}
			
			
			

		}
		//marked[i][j]= false;

		//marked[i][j] = true;
	}
	//private dfs(Grapgh g, String source)
	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A through Z.)
	public int scoreOf(String word) {
		int len = word.length();
		if(len>=8){
			return 11;
		} else if(len==7){
			return 5;
		} else if(len==6){
			return 3;
		} else if(len==5){
			return 2;
		} else if(len>=3){
			return 1;
		} else if(len>=0){
			return 0;
		}
		return 0;
	}
}