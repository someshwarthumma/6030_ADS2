import java.util.Iterator;
import java.util.NoSuchElementException;

/**.
 * bag class
 * Complexity O(1)
 *
 * @param      <Item>  The item
 */
public class Bag<Item> implements Iterable<Item> {
    /**.
     * variable N
     */
    private int n;         // number of elements in bag
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
      * Complexity O(1)
      */
    public Bag() {
        first = null;
        n = 0;
    }

    /**.
     * method to check empty
     * Complexity O(1)
     *
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**.
     * size method
     * Complexity O(1)
     *
     * @return     { int }
     */
    public int size() {
        return n;
    }

    /**.
     * { method to add item }
     * Complexity O(1)
     *
     * @param      item  Item
     */
    public void add(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }


    /**.
     * Iterator
     * Complexity O(1)
     *
     * @return     { Iterator }
     */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }

    /**.
     * class LIste Iteraator
     * Complexity O(1)
     */
    private class ListIterator implements Iterator<Item> {
        /**.
         * node
         */
        private Node current = first;
        /**.
         * method to check next
         * Complexity O(1)
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext() {
            return current != null;
        }
        /**.
         * { method to remove }
         * Complexity O(1)
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**.
         * method for next
         * Complexity O(1)
         *
         * @return     { Item }
         */
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}


