package edu.ncsu.csc216.pack_scheduler.util;
/**
 * Maintains a list of elements in an ArrayList acting as a Queue
 * @author colinscanlon
 *
 * @param <E> The generic type for ArrayQueue
 */
public class ArrayQueue<E> implements Queue<E> {

	/** The list to be maintained by ArrayQueue **/
	ArrayList<E> list;
	
	/** The capacity of the ArrayQueue **/
	int capacity;
	
	/**
	 * The constructor for ArrayQueue that creates a new ArrayList
	 * @param capacity the capacity of the list
	 */
	public ArrayQueue(int capacity) {
		list = new ArrayList<E>();
		this.capacity = capacity;
	}
	
	@Override
	public void enqueue(E element) {
		list.add(element);
	}

	@Override
	public E dequeue() {
		return list.remove(0);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public void setCapacity(int capacity) {
		this.capacity = capacity;
		
	}

}
