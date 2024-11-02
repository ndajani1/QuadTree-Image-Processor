// CIRCULAR FIFO QUEUE
// - initial capacity: 10
// - rule for growing: when there are no empty spots, the array capacity is doubled
// - rule for shrinking: the array never shrinks
// - the front of the queue is initially at index 0
// - after an expansion, the front of the queue MUST be reset at index 0
@SuppressWarnings("unchecked")
public class Queue<T>
{
    ////////////////////////////////////////////////
    /**   DO NOT MODIFY THIS FIELD               **/
    /**   This is the dynamic array you MUST     **/
    /**   use for holding the queue data         **/
    /**/  private T[] queue;                    /**/
    /**   You can add more private fields below  **/
    ////////////////////////////////////////////////
    private int front = 0; 
    private int back = -1; 
    private int size = 0; 
    private final int INITIAL_CAP = 10;

    // creates a CIRCULAR FIFO queue with initial capacity of 10
    // Time Complexity: O(1)
    public Queue()
    {
        this.queue = (T[])new Object[INITIAL_CAP]; // use of SuppressWarnings for unchecked cast
        this.front = 0;
        this.back = -1;
        this.size = 0;
    }

    // Returns true if the queue is empty, false otherwise
    // Time Complexity: O(1)
    public boolean isEmpty()
    {
        return size == 0;
    }

    // Adds an item to the back of the queue.
    // Time Complexity: O(1) amortized
    public void enqueue(T value)
    {
        if (size == queue.length) {
            expandCapacity();
        }
        back = (back + 1) % queue.length; // Circular increment
        queue[back] = value;
        size++;
    }

    // Inspects the front item in the queue without removing it.
    // Throws a RuntimeException if the queue is empty.
    // Time Complexity: O(1)
    public T peek()
    {
        if (isEmpty()) {
            throw new RuntimeException("Empty Queue");
        }
        return queue[front];
    }

    // Removes the front item from the queue.
    // Throws a RuntimeException if the queue is empty.
    // Time Complexity: O(1)
    public T dequeue()
    {
        if (isEmpty()) {
            throw new RuntimeException("Empty Queue");
        }
        T temp = queue[front];
        queue[front] = null; // Help with garbage collection
        front = (front + 1) % queue.length; // Circular increment
        size = size - 1;
        return temp;
    }

    private void expandCapacity() {
        T[] newQueue = (T[])new Object[queue.length * 2];
        // Copy elements to the new array, starting from 'front' to 'rear', circularly.
        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[(front + i) % queue.length];
        }
        queue = newQueue;
        front = 0; // Reset front
        back = size - 1; // Rear is now at the last element
    }

}
