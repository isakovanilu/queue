import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> front; // front of the deque
    private Node<Item> back; // back of the deque
    private int size; // size of the deque

    // Node class for doubly linked list
    private static class Node<Item> {
        Item item; // data stored in the node
        Node<Item> next; // reference to the next node
        Node<Item> prev; // reference to the previous node
    }

    // Constructor to initialize an empty deque
    public Deque() {
        front = null;
        back = null;
        size = 0;
    }

    // Check if the deque is empty
    public boolean isEmpty() {
        return size == 0; // returns true if there are no elements in the deque
    }

    // Return the number of items in the deque
    public int size() {
        return size;
    }

    // Add an item to the front of the deque
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Cannot add null item");

        Node<Item> oldFront = front; // store current front node
        front = new Node<>(); // create new node to add at front
        front.item = item; // set the item in the new front node
        front.next = oldFront; // link new node to the old front
        front.prev = null; // previous node is null since it's the new front

        if (isEmpty()) {
            back = front; // if deque was empty, front and back are the same
        } else {
            oldFront.prev = front; // link old front's previous to new front
        }

        size++; // increment size of deque
    }

    // Add an item to the back of the deque
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Cannot add null item");

        Node<Item> oldBack = back; // store current back node
        back = new Node<>(); // create new node to add at back
        back.item = item; // set the item in the new back node
        back.prev = oldBack; // link new node to the old back
        back.next = null; // next node is null since it's the new back

        if (isEmpty()) {
            front = back; // if deque was empty, front and back are the same
        } else {
            oldBack.next = back; // link old back's next to new back
        }

        size++; // increment size of deque
    }

    // Remove and return the item from the front of the deque
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");

        Item item = front.item; // store the item at the front to return
        front = front.next; // move front to the next node
        size--; // decrement size of deque

        if (isEmpty()) {
            back = null; // if deque is empty after removing, set back to null
        } else {
            front.prev = null; // set new front's previous to null
        }

        return item; // return the removed item
    }

    // Remove and return the item from the back of the deque
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");

        Item item = back.item; // store the item at the back to return
        back = back.prev; // move back to the previous node
        size--; // decrement size of deque

        if (isEmpty()) {
            front = null; // if deque is empty after removing, set front to null
        } else {
            back.next = null; // set new back's next to null
        }

        return item; // return the removed item
    }

    // Return an iterator to iterate through items from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator<>(front); // start iterating from the front
    }

    // Iterator class for traversing the deque
    private class DequeIterator<Item> implements Iterator<Item> {
        private Node<Item> current; // current node in the iteration

        // Constructor initializes the iterator at the front of the deque
        public DequeIterator(Node<Item> front) {
            current = front;
        }

        // Check if there are more items to iterate over
        public boolean hasNext() {
            return current != null;
        }

        // Get the next item in the iteration
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item; // store the current item
            current = current.next; // move to the next node
            return item; // return the stored item
        }
    }

    // Unit testing the Deque
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();

        // Test adding elements to the front and back
        deque.addFirst(1); // deque: 1
        deque.addLast(2); // deque: 1 2
        deque.addFirst(0); // deque: 0 1 2
        deque.addLast(3); // deque: 0 1 2 3

        // Print all elements in deque
        System.out.println("Deque contents:");
        for (int item : deque) {
            System.out.print(item + " ");
        }
        System.out.println();

        // Test removing elements from the front and back
        System.out.println("Removed from front: " + deque.removeFirst()); // removes 0
        System.out.println("Removed from back: " + deque.removeLast()); // removes 3
        System.out.println("Current size: " + deque.size()); // size is now 2

        // Print remaining elements in deque
        System.out.println("Deque contents after removal:");
        for (int item : deque) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}
