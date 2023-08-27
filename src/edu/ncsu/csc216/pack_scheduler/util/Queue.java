package edu.ncsu.csc216.pack_scheduler.util;

/**
 * The Queue interface to be implemented by queue classes
 * @author colinscanlon
 *
 * @param <E> The generic type of the queue
 */
public interface Queue<E> {
	/**
	 * Adds the element to the back of the Queue
	 * @param element The element to be added
	 * @throws IllegalArgumentException if there is no room
	 */
	public void enqueue(E element);
	
	
	/**
	 * Removes and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 */
	public E dequeue();
	
	/**
	 * Returns true if the Queue is empty
	 * @return if the queue is empty or not
	 */
	public boolean isEmpty();
	
	/**
	 * Returns the number of elements in the Queue
	 * @return number of elements in the queue
	 */
	public int size();
	
	/**
	 * Sets the Queue’s capacity
	 * @param capacity the new capacity of the list
	 * @throws IllegalArgumentException if the number is negative or less than the number of elements in the Queue 
	 */
	public void setCapacity(int capacity);
	
	

}
