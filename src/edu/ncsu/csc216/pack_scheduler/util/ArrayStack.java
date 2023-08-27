package edu.ncsu.csc216.pack_scheduler.util;
/**
 * Holds a stack of elements as an array list
 * @author colinscanlon
 * @param <E> The elements in the list
 */
public class ArrayStack<E> implements Stack<E> {

	/** The list held by arrayStack **/
	ArrayList<E> list;
	
	/** The capacity of the arrayStack **/
	int capacity;
	
	ArrayStack(int capacity) {
		list = new ArrayList<E>();
		this.capacity = capacity;
	}
	
	@Override
	public void push(E element) {
		list.add(element);
		
	}

	@Override
	public E pop() {
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
		this.capacity = capacity;
		
	}

}
