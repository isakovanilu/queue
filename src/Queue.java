public class Queue {
    private int front, rear, size; // front tracks the first element, rear tracks the last element, size tracks the current number of elements
    private int capacity; // maximum capacity of the queue
    private int[] array; // array to store queue elements

    // Constructor to initialize the queue with a specific capacity
    public Queue(int capacity) {
        this.capacity = capacity;
        this.front = this.size = 0; // initialize front and size to 0, since the queue starts empty
        this.rear = capacity - 1; // initialize rear to the last position for circular behavior
        this.array = new int[this.capacity]; // initialize the array with the given capacity
    }

    // Method to check if the queue is full
    public boolean isFull() {
        return size == capacity; // returns true if size equals capacity
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return size == 0; // returns true if size is 0
    }

    // Method to add an item to the queue (enqueue)
    public void enqueue(int item) {
        if (isFull()) { // check if the queue is full before adding
            System.out.println("Queue is full");
            return; // exit the method if the queue is full
        }
        // Move rear to the next position in a circular manner
        rear = (rear + 1) % capacity;
        array[rear] = item; // add the item at the new rear position
        size++; // increment the size to reflect the new item
        System.out.println(item + " enqueued to queue"); // confirm the enqueue operation
    }

    // Method to remove an item from the queue (dequeue)
    public int dequeue() {
        if (isEmpty()) { // check if the queue is empty before removing
            System.out.println("Queue is empty");
            return Integer.MIN_VALUE; // return an error code if the queue is empty
        }
        int item = array[front]; // store the front item to return later
        front = (front + 1) % capacity; // move front to the next position in a circular manner
        size--; // decrement the size to reflect the removed item
        return item; // return the dequeued item
    }

    // Method to get the front item of the queue without removing it
    public int peek() {
        if (isEmpty()) { // check if the queue is empty
            System.out.println("Queue is empty");
            return Integer.MIN_VALUE; // return an error code if empty
        }
        return array[front]; // return the front item
    }

    // Method to get the rear item of the queue without removing it
    public int rear() {
        if (isEmpty()) { // check if the queue is empty
            System.out.println("Queue is empty");
            return Integer.MIN_VALUE; // return an error code if empty
        }
        return array[rear]; // return the rear item
    }

    // Main method to demonstrate queue operations
    public static void main(String[] args) {
        Queue queue = new Queue(5); // create a queue with capacity of 5

        // Add elements to the queue
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);

        // Remove and print an element from the queue
        System.out.println("Dequeued item: " + queue.dequeue());

        // Display the current front item of the queue
        System.out.println("Front item: " + queue.peek());

        // Display the current rear item of the queue
        System.out.println("Rear item: " + queue.rear());
    }
}
