import java.util.Iterator;
import java.util.NoSuchElementException;

/**.
 *  Bag class
 */
public class Bag<Item> implements Iterable<Item> {
    /**.
     * variable N
     */
    private int N;         // number of elements in bag
    /**.
     * First Node
     */
    private Node first;    // beginning of bag
    /**.
     * Node class
     */
    private class Node {
        /**.
         * item
         */
        private Item item;
        /**.
         * next node
         */
        private Node next;
    }

    /**.
      * Create an empty stack.
      */
    public Bag() {
        first = null;
        N = 0;
    }

    /**.
      * Is the BAG empty?
      */
    public boolean isEmpty() {
        return first == null;
    }

    /**
      * Return the number of items in the bag.
      */
    public int size() {
        return N;
    }

    /**.
      * Add the item to the bag.
      */
    public void add(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }


    /**.
      * Return an iterator that iterates over the items in the bag.
      */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        /**.
         * Node
         */
        private Node current = first;
        /**.
         * method to check next
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext() {
            return current != null;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**.
         * method for next
         *
         * @return     { description_of_the_return_value }
         */
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
