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
        case "Queries":
            try {
                WordNet wordnet1 = new WordNet(synsets,
                                          hypernyms);
                while (StdIn.hasNextLine()) {
                    String queryNoun = StdIn.readLine();
                    String[] queryArray = queryNoun.split(" ");
                    if (queryArray[0].equals("null")) {
                        throw new IllegalArgumentException(
                            "IllegalArgumentException");
                    }
                    System.out.println("distance = "
                        + wordnet1.distance(queryArray[0],
                        queryArray[1]) + ", ancestor = "
                        + wordnet1.sap(queryArray[0], queryArray[1]));
                }
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println(e.getMessage());
            }
            break;
        default:
            break;
        }
    }
}
