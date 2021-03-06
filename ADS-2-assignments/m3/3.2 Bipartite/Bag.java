import java.util.Iterator;
import java.util.NoSuchElementException;

/**.
 * Class for bag.
 *
 * @param      <Item>  The item
 */
public class Bag<Item> implements Iterable<Item> {
    /**.
     * { var_description }.
     */
    private int size;
    /**.
     * { var_description }.
     */
    private Node first;

    /**.
     * Class for node.
     */
    private class Node {
        /**.
         * { var_description }.
         */
        private Item item;
        /**.
         * { var_description }.
         */
        private Node next;
    }

    /**.
      * Create an empty stack.
      */
    public Bag() {
        first = null;
        size = 0;
    }
    /**.
     * Determines if empty.
     * Complexity (1)
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }
    /**.
     * { function_description }.
     * Complexity (1)
     *
     * @return     { description_of_the_return_value }
     */
    public int size() {
        return size;
    }
    /**.
     * { function_description }.
     * Complexity (1)
     *
     * @param      item  The item
     */
    public void add(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        size++;
    }
    /**.
     * { function_description }.
     * Complexity (1)
     *
     * @return     { description_of_the_return_value }
     */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }

    /**.
     * Class for list iterator.
     */
    private class ListIterator implements Iterator<Item> {
        /**.
         * { var_description }.
         */
        private Node current = first;
        /**.
         * Determines if it has next.
         * Complexity (1)
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext()  {
            return current != null;
        }
        /**.
         * { function_description }.
         * Complexity (1)
         */
        public void remove()      {
            throw new UnsupportedOperationException();
        }
        /**.
         * { function_description }.
         * Complexity (1)
         *
         * @return     { description_of_the_return_value }
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


