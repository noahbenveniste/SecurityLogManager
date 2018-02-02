package edu.ncsu.csc316.security_log.util;

/**
 * A custom implemented array list class that is able to adjust size
 * automatically, elements can be added to the front, end or middle. Duplicate
 * elements in the array are automatically rejected form the ArrayList.
 * 
 * @param <E>
 *            Specifies that the ArrayList can contain any object type
 *            
 * @author Noah Benveniste
 * @author Kevin Hildner
 * 
 */
public class ArrayList<E extends Comparable<? super E>> {
    /**
     * The array's current size, based on the number of non-null elements present
     */
    private static final int INIT_SIZE = 1000;
    /** The underlying array for the ArrayList */
    private E[] list;
    /** The number of elements that has been placed in the array */
    private int size;
    /** The total capacity of the underlying array */
    private int capacity;

    /**
     * Constructs an empty ArrayList with a desired initial max capacity.
     * Useful if the client knows ahead of time how much space needs to
     * be allocated to the array. Can save on runtime by not needing to
     * generate a new array and copy the contents of the old array over
     * when capacity is reached.
     * @param capacity the maximum initial capacity of the array list
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        this.size = 0;
        this.list = (E[]) new Comparable[capacity];
        this.capacity = list.length;
    }

    /**
     * Constructor that initializes array capacity to a default size.
     */
    public ArrayList() {
        this(INIT_SIZE);
    }
    /**
     * Adds a desired element to the ArrayList at a specified index, right-shifting
     * necessary values
     * 
     * @param idx
     *            the zero-based index to add the new element at
     * @param element
     *            the element to add to the ArrayList
     * @throws NullPointerException
     *             if the added element is null
     * @throws IndexOutOfBoundsException
     *             if the index is less than zero or greater than the ArrayList's
     *             size
     * @throws IllegalArgumentException
     *             if the added element is a duplicate of an element already in the
     *             list
     */
    public void add(int idx, E element) {
        if (element == null) {
            throw new NullPointerException("Cannot add null elements");
        }
        if (idx < 0 || idx > this.size()) {
            throw new IndexOutOfBoundsException("Index is outside the accepatble range");
        }
        for (int i = 0; i < this.size(); i++) {
            if (this.list[i].equals(element)) {
                throw new IllegalArgumentException("Cannot add repeat elements");
            }
        }
        if (this.size() == this.capacity) { // Grow the array if list is full
            this.growArray();
        }
        if (idx == this.size()) { // Adding an element to the end of the list
            list[idx] = element;
        } else { // Add an element to the front or middle of the list
            // Right shift all values before the index to insert the new element
            for (int i = this.size; i > idx; i--) {
                list[i] = list[i - 1];
            }
            // Add the element to the desired index
            list[idx] = element;
        }
        // Increment the size of the ArrayList
        this.size++;
    }

    /**
     * Used to grow the array if size == capacity; Doubles the capacity by default
     */
    @SuppressWarnings("unchecked")
    private void growArray() {
        // Update capacity
        this.capacity *= 2;
        // Create a new object array of double the capacity of the current array
        Object[] o = new Object[this.capacity];
        // Cast to generic type
        E[] temp = (E[]) o;
        // Assign the elements from the old array to the same index in the new array
        for (int i = 0; i < this.size(); i++) {
            temp[i] = this.list[i];
        }
        // Assign the new array to the list field
        this.list = temp;
    }

    /**
     * Removes an element from the ArrayList at a specified index, left-shifting all
     * remaining elements and returning the removed element
     * 
     * @param idx
     *            indicates the index of the element to remove from the ArrayList
     * @return the removed element
     * @throws IndexOutOfBoundsException
     *             if the specified index is out of bounds
     */
    public E remove(int idx) {
        if (idx < 0 || idx >= this.size()) {
            throw new IndexOutOfBoundsException("Index is outside the accepatble range");
        }
        // Get the element at the specified index
        E temp = list[idx];
        for (int i = idx; i < this.size() - 1; i++) {
            list[i] = list[i + 1];
        }
        // Set the repeated element at the end of the list to null
        list[this.size() - 1] = null;
        // Decrement the size
        this.size--;
        // Return the removed element
        return temp;
    }

    /**
     * Overwrites the element at a specified index with a new element
     * 
     * @param idx
     *            the index in the array that is being over written
     * @param element
     *            the new value/object for that position in the array
     * @return the old element at the specified index
     * @throws NullPointerException
     *             if the added element is null
     * @throws IndexOutOfBoundsException
     *             if the index is less than zero or greater than the ArrayList's
     *             size
     * @throws IllegalArgumentException
     *             if the added element is a duplicate of an element already in the
     *             list
     */
    public E set(int idx, E element) {
        if (element == null) {
            throw new NullPointerException("Cannot set null elements");
        }
        if (idx < 0 || idx >= this.size()) {
            throw new IndexOutOfBoundsException("Index is outside the acceptable range");
        }
        for (int i = 0; i < this.size(); i++) {
            if (this.list[i].equals(element)) {
                throw new IllegalArgumentException("Cannot add duplicate elements");
            }
        }
        E temp = list[idx];
        list[idx] = element;
        return temp;
    }

    /**
     * Gets an element at a specified index
     * 
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException
     *             if the specified index is out of bounds
     */
    public E get(int idx) {
        if (idx < 0 || idx >= this.size()) {
            throw new IndexOutOfBoundsException("Index is outside the acceptable range");
        }
        return this.list[idx];
    }

    /**
     * Getter for the size field
     * 
     * @return the size of the ArrayList i.e. how many elements it contains
     */
    public int size() {
        return this.size;
    }
    
    /**
     * 
     * Precondition: Only functions properly if the list is maintained in sorted order
     * @param e
     * @return
     */
    public int contains(E e) {
        if (size == 0) {
            return -1;
        } else {
            return binarySearch(e, 0, size - 1);
        }
    }
    
    /**
     * Recursive binary search utilized by contains()
     * @param e the element to search for
     * @return the index of the specified element if it is in the list, -1 if it isn't found.
     */
    private int binarySearch(E e, int low, int high) {
        int pivot = (high + low) / 2;
        // Base case 1: size is 0, element was not found
        if (low > high) {
            return -1;
        // Base case 2: found the element at the pivot index
        } else if (list[pivot].equals(e)) {
            return pivot;
        // Recursive case 1: e < list[pivot]: search sublist left of the pivot
        } else if (e.compareTo(list[pivot]) < 0) {
            return binarySearch(e, low, pivot - 1);
        // Recursive case 2: e > list[pivot]: search sublist right of the pivot
        } else if (e.compareTo(list[pivot]) > 0) {
            return binarySearch(e, pivot + 1, high);
        // Catch-all for unexpected failure/bugs.
        } else {
            throw new IllegalArgumentException("Binary search failed unexpectedly.");
        }
    }
    
}