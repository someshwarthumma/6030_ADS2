import java.util.Iterator;
import java.util.NoSuchElementException;
/**.
 * List of .
 *
 * @param      <Item>  The item
 */
public class Queue<Item> implements Iterable<Item> {
    /**.
     * Integer variable.
     */
    private int n;
    /**.
     * Node first.
     */
    private Node first;
    /**
     * Node last.
     */
    private Node last;
    /**.
     * Class for node.
     */
    private class Node {
        /**.
         * Item variable.
         */
        private Item item;
        /**.
         * Node variable.
         */
        private Node next;
    }
    /**.
     * Create an empty queue.
     */
    public Queue() {
        first = null;
        last  = null;
    }
    /**.
     * Is the queue empty?
     * Complexity is O(1)
     *
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }
    /**.
     * Return the number of items in the queue.
     * Complexity is O(1)
     * @return     number of elements.
     */
    public  int size() {
        return n;
    }
    /**.
     * Return the item least recently added to the queue. Throw an exception if
     * the queue is empty.
     * Complexity is O(1)
     *
     * @return     first item.
     */
    public Item peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }
        return first.item;
    }
    /**.
     * Add the item to the queue.
     * Complexity is O(1)
     *
     * @param      item  The item
     */
    public void enqueue(final Item item) {
        Node x = new Node();
        x.item = item;
        if (isEmpty()) {
            first = x;
            last = x;
        } else {
            last.next = x;
            last = x;
        }
        n++;
    }

    /**.
     * Remove and return the item on the queue least recently added. Throw an
     * exception if the queue is empty.
     * Complexity is O(1)
     *
     * @return     item.
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        }
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    /**.
     * Return string representation.
     * Complexity is O(N)
     *
     * @return     String representation of the object.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + "\n");
        }
        return s.toString();
    }
    /**.
     * Return an iterator that iterates over the items on the queue in FIFO
     * order.
     * Complexity is O(1)
     *
     * @return     list iterator.
     */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }
    /**.
     * Class for list iterator.
     * Complexity is O(1)
     */
    private class ListIterator implements Iterator<Item> {
        /**.
         * Node variables.
         */
        private Node current = first;
        /**.
         * Determines if it has next.
         * Complexity is O(1)
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext() {
            return current != null;
        }
        /**.
         * remove.
         * Complexity is O(1)
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**.
         * Item.
         * Complexity is O(1)
         *
         * @return     item.
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



