package edu.ncsu.csc216.pack_scheduler.util;
/**
 * Holds a stack of linked elements
 * @author colinscanlon
 * @param <E>
 *
 */
public class LinkedStack<E> implements Stack<E> {

	/** The list for LinkedStack to keep track of **/
	LinkedAbstractList<E> list;
	
	LinkedStack(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
	}
	
	@Override
	public void push(E element) {
		list.add(0, element);
	}

	@Override
	public E pop() {
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
		list.setCapacity(capacity);
	}
	
	

}
