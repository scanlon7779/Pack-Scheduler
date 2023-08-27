package edu.ncsu.csc216.pack_scheduler.util;

/**
 * A recursive linked list of elements
 * @author colinscanlon
 * 
 * @param <E> the generic type of the list
 *
 */
public class LinkedListRecursive<E> {

	/** The list node for the front of the list */
	private ListNode front;
	/** The size of the list */
	private int size;
	
	/**
	 * Initial constructor for the LinkedListRecursive
	 */
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}
	
	/**
	 * gets the size of the list
	 * @return the size of the list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * returns true if the list is empty
	 * @return if the list is empty or not
	 */
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * checks if the list contains the given element already
	 * @param element the element to be chacked
	 * @return if the element is in the list or not
	 */
	public boolean contains(E element) {
		if (size == 0) {
			return false;
		}
		
		return front.contains(element);
	}
	
	/**
	 * adds the element to the end of the list
	 * @param element the element to be added
	 * @return if the element was added or not
	 */
	public boolean add(E element) {
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		
		if (size == 0) {
			front = new ListNode(element, null);
			size++;
			return true;
		}
		
		return front.add(element);
	}
	
	/**
	 * adds the element at the specified index
	 * @param idx the index to add at
	 * @param element the element to add
	 */
	public void add(int idx, E element) {
		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		
		if(element == null) {
			throw new NullPointerException();
		}
		
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException();
		}
		
		if (idx == 0) {
			front = new ListNode(element, null);
			size++;
		} else {
			front.add(idx, element);
		}
		
	}
	
	/**
	 * returns an element at the given index
	 * @param idx the index of the element to remove
	 * @return if the element was added or not
	 */
	public E get(int idx) {
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException();
		}
		
		return front.get(idx);
	}
	
	/**
	 * removes the element at the given index
	 * @param idx the index of the element to remove
	 * @return the data of the removed element
	 */
	public E remove(int idx) {
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException();
		}
		
		if (idx == 0) {
			E hold = front.data;
			front = front.next;
			size--;
			return hold;
		} else {
			return front.remove(idx);
		}
	}
	
	/**
	 * removes an element with the given data
	 * @param element the element to rmove
	 * @return if the element was removed or not
	 */
	public boolean remove(E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		
		if (element.equals(front.data)) {
			size--;
			front = front.next;
			return true;
		} else {
			return front.remove(element);
		}
	}
	
	/**
	 * Sets the data of an element at the given index
	 * @param idx the index to set the data at
	 * @param element the element to set to
	 * @return the data previously held in that index
	 */
	public E set(int idx, E element) {
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException();
		}
		if (element == null) {
			throw new NullPointerException();
		}
		
		if (idx == 0) {
			E hold = front.data;
			front.data = element;
			return hold;
		}
		
		return front.set(idx, element);
		
	}
	
/**
 * The private ListNode class that is delegated to	
 * @author colinscanlon
 *
 */
private class ListNode {
		
	    /** Data of an element */
		public E data;
		/** Next ListNode */
		public ListNode next;
		
		/**
		 * Constructs a listNode object with preset data
		 * @param data in the node
		 * @param next the next ListNode in the list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
		
		/**
		 * determines is the given data is in the list
		 * @param element the element to check
		 * @return if the element is in the list or not
		 */
		public boolean contains(E element) {
			ListNode current = front;
			while (current.next != null) {
				if(current.data.equals(element)) {
					return true;
				}
				current = current.next;
			}
			return false;
		}
		
		/**
		 * adds the given element to the end of the list
		 * @param element the element to add
		 * @return if the element was added or not
		 */
		public boolean add(E element) {
			ListNode current = front;
			while (current.next != null) {
				current = current.next;
			}
			current.next = new ListNode(element, null);
			size++;
			return true;
		}
		
		/**
		 * adds the element at a given index
		 * @param idx the index to add at
		 * @param element the element to add at the index
		 */
		public void add(int idx, E element) {
			ListNode current = front;
			for (int i = 1; i < idx; i++) {
				current = current.next;
			}
			
			if (current.next != null) {
				current.next = new ListNode(element, current.next);
				size++;
			} else {
				current.next = new ListNode(element, null);
				size++;
			}
		}
		
		/**
		 * gets the element at a given index
		 * @param idx the index to get data from
		 * @return the element at the given index
		 */
		public E get(int idx) {
			ListNode current = front;
			for (int i = 0; i < idx; i++) {
				current = current.next;
			}
			
			return current.data;
		}
		
		/**
		 * removes the element at a given index
		 * @param idx the index of the element to remove
		 * @return the data from the removed element
		 */
		public E remove(int idx) {
			ListNode current = front;
			for (int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			
			E hold = current.next.data;
			size--;
			current.next = current.next.next;
			return hold;
		}
		
		/**
		 * removes the given element from the list
		 * @param element the element to remove
		 * @return if the element was removed or not
		 */
		public boolean remove(E element) {
			ListNode current = front;
			for (int i = 0; i < size; i++) {
				if(current.data.equals(element)) {
					size--;
					return true;
					
				}
				current = current.next;
			}
			
			return false;
		}
		
		/**
		 * sets the element at the given index to the new element
		 * @param idx the index to set the data at
		 * @param element the element to set it to
		 * @return the element previouly held at that index
		 */
		public E set(int idx, E element) {
			ListNode current = front;
			for (int i = 0; i < idx; i++) {
				current = current.next;
			}
			E hold = current.data;
			current.data = element;
			return hold;
		}
		
	}
}
