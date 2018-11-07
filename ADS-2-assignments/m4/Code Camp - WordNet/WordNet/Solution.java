/**
 * class for Solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //empty constructor.
    }
    /**
     * Client program.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        String synsets = StdIn.readLine();
        String hypernyms = StdIn.readLine();
        String type = StdIn.readLine();
        switch (type) {
        case "Graph":
            try {
                WordNet wordnet = new WordNet(synsets, hypernyms);
                wordnet.display();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            break;
        default:
            break;
        }
    }
}
