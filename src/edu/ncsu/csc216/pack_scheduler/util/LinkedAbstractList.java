/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Creates a custom Linked List
 * @author Mikato Leuang
 * @param <E> Linked List of type element
 */
public class LinkedAbstractList<E> extends AbstractList<E> {

	/** Front node of the LinkedList */
	private ListNode front;
	
	/** points to the last element in the list **/
	private ListNode back;
	
	/** Size of the LinkedList */
	private int size;
	/** The capacity of the LinkedList */
	private int capacity;
	
	private class ListNode {
	
		/** Data of an element */
		public E data;
		/** Next ListNode */
		public ListNode next;
		
		/**
		 * Constructs a listNode object with preset data
		 * @param data in the node
		 */
		public ListNode(E data) {
			this.data = data;
			this.next = null;
		}
		/**
		 * Constructs a ListNode object with data and the next node in the list
		 * @param data in the node
		 * @param next the next node in the list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
	
	/**
	 * Constructs a LinkedAbstractList object
	 * @param capacity of the list
	 */
	public LinkedAbstractList(int capacity) {
		front = null;
		size = 0;
		if (capacity > 0 || capacity == 0) {
			this.capacity = capacity;
		} else if (capacity < 0){
			throw new IllegalArgumentException();
		} else if (capacity < size) {
			throw new IllegalArgumentException();
		}
		//add capacity
	}
	
	/**
	 * Adds an element to the LinkedAbstractList
	 * @param index where element is added
	 * @param element element that is being added
	 */
	@Override
	public void add(int index, E element) {
		if (size == capacity) {
			throw new IllegalArgumentException();
		}
		if (element == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		
		//duplicate check
		ListNode dupCheck = front;
		for (int i = 0; i < size; i++) {
			if (dupCheck.data.equals(element)) {
				throw new IllegalArgumentException();
			} else {
				dupCheck = dupCheck.next;
			}
		}
		
		if (index == 0) {
			front = new ListNode(element, front);
		} else {
			ListNode current = front;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			current.next = new ListNode(element, current.next);
		}
		if (size == 0) {
			front = new ListNode(element);
		}
		size++;
		
		ListNode hold = front;
		for (int i = 0; i < size; i++) {
			hold = hold.next;
		}
		back = hold;
	}
	
	/**
	 * Removes an element in the list
	 * @param index the index of the element being removed
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E remove (int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if(index == size) {
			ListNode current = front;
			for (int i = 0; i < index - 2; i++) {
				current = current.next;
			}
			back = current;
		}
		if (index == 0) {
			Object hold = front.data;
			front = front.next;
			size--;
			return (E) hold;
		} else {
			ListNode current = front;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			Object hold = current.next.data;
			current.next = current.next.next;
			size--;
			return (E) hold;
		}
		
	}
	
	/**
	 * Sets the given element at an index to another element
	 * @param index of element being set
	 * @param element that is being set
	 * @return element that is set
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E set(int index, E element) {
		if(element == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
	
		//duplicate check
		ListNode dupCheck = front;
		for (int i = 0; i < size; i++) {
			if (dupCheck.data.equals(element)) {
				throw new IllegalArgumentException();
			}
			
			if (i == index) {
				dupCheck.data = element;
			}
		}
		
		/*
		ListNode current = front;
		for (int i = 0; i < index - 1; i++) {
			current = current.next;
		}
		*/
		
		/* for (int i = 0; i < index - 1; i++) {
			if (i == index) {
				current.next.data = element;
			}
		}
		return (E) current.next; */
		
		return (E) dupCheck.next;
	}
	
	/**
	 * Returns the data of an element in the list
	 * @param index element is being returned from
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		ListNode current = front;
		for (int i = 0; i < index - 1; i++) {
			current = current.next;
		}
		return current.data;
		
	}

	/**
	 * Returns the size of the list
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Sets a new capacity for the list
	 * @param capacity the new capacity for the list
	 */
	public void setCapacity(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}

}
