package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Maintains a list of elements in a LinkedAbstractList acting as a Queue
 * @author colinscanlon
 * @param <E> The generic type for LinkedQueue
 */
public class LinkedQueue<E> implements Queue<E> {

	/** The linked list to be maintained by LinkedQueue **/
	LinkedAbstractList<E> list;
	
	/**
	 * Constructor for LinkedQueue
	 * @param capacity the capacity for the LinkedQueue
	 */
	public LinkedQueue(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
	}
	
	@Override
	public void enqueue(E element) {
		list.add(0, element);
		
	}

	@Override
	public E dequeue() {
		return list.remove(list.size() - 1);
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
		list.setCapacity(capacity);
		
	}
	
	

}
