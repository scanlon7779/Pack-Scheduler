package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Interface for handling the waitlist of students
 * @author colinscanlon
 * @param <E>
 *
 */
public interface Stack<E> {

	/**
	 * Adds the element to the top of the stack
	 * @param element the element to be added
	 */
	public void push(E element);
	
	/**
	 * Removes and returns the element at the top of the stack
	 * @return the element at the top of the stack
	 */
	public E pop();
	
	/**
	 * Returns true if the stack is empty
	 * @return if the stack is empty
	 */
	public boolean isEmpty();
	
	/**
	 * Returns the number of elements in the stack
	 * @return the size of the stack
	 */
	public int size();
	
	/**
	 * Sets the stack’s capacity
	 * @param capacity the new capacity of the stack
	 */
	void setCapacity(int capacity);
	
}
