package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * A linked list that implements the abstract sequential list with a generic type E
 * @author colinscanlon
 *
 * @param <E> The generic type of the list
 */
public class LinkedList<E> extends AbstractSequentialList<E> {

	/** The list node for the first item in the list **/
	private ListNode front;
	/** The list node for the last item in the list **/
	private ListNode back;
	/** The size of the list **/
	private int size;
	
	/** 
	 * The constructor for the LinkedList
	 */
	public LinkedList() {
		this.front = new ListNode(null);
		this.back = new ListNode(null);
		front.next = back;
		back.prev = front;
		size = 0;
	}

	
	private class ListNode {
		
		/** Data of an element */
		public E data;
		/** Next ListNode */
		public ListNode next;
		/** Previous ListNode */
		public ListNode prev;
		
		public ListNode(E data) {
			
		}
		
		/**
		 * Constructs a listNode object with preset data
		 * @param data in the node
		 * @param prev the previous ListNode in the list
		 * @param next the next ListNode in the list
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}
	
	/**
	 * The list iterator for the linked list
	 * @author colinscanlon
	 *
	 */
	private class LinkedListIterator implements ListIterator<E> {
		/** the ListNode that would be returned on a call to previous() */
		ListNode previous;
		/** represents the ListNode that would be returned on a call to next() */
		ListNode next;
		/**  the index that would be returned on a call to previousIndex() */
		int previousIndex;
		/** the index that would be returned on a call to nextIndex() */
		int nextIndex;
		/** the ListNode that was returned on the last call */
		ListNode lastRetrieved;
		
		/**
		 * Constructor for the linked list iterator that sets next to the item at the index and previous to index - 1
		 * @param index the index of the linked list iterator to set at
		 */
		public LinkedListIterator(int index) { 
			   if (index < 0 || index > size) {
				   throw new IndexOutOfBoundsException();
			   }
			   next = front;
			   previous = next;
			   for (int i = 0; i <= index; i++ ) {
				   previous = next;
				   next = next.next;
			   }
			   
			   this.previousIndex = index - 1;
			   this.nextIndex = index;
			   this.lastRetrieved = null;
		}

		@Override
		public boolean hasNext() {
			if (next != null) {
				return true;
			}
			return false;
		}

		@Override
		public E next() {
			if (next == null) {
				throw new NoSuchElementException();
			}
			lastRetrieved = next;
			previous = next;
			next = next.next;
			return next.data;
		}

		@Override
		public boolean hasPrevious() {
			if (previous != null) {
				return true;
			}
			return false;
		}

		@Override
		public E previous() {
			if (previous == null) {
				throw new NoSuchElementException();
			}
			lastRetrieved = previous;
			next = previous;
			previous = previous.prev;
			return previous.data;
		}

		@Override
		public int nextIndex() {
			return nextIndex;
		}

		@Override
		public int previousIndex() {
			return previousIndex;
		}

		@Override
		public void remove() {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			
			if (lastRetrieved.equals(previous)) {
				next.prev = previous.prev;
				previous.prev.next = next;
				size--;
			} else {
				previous.next = next.next;
				next.next.prev = previous;
			}
			
		}

		@Override
		public void set(E e) {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			
			if (e == null) {
				throw new NullPointerException();
			}
			
			if (lastRetrieved.equals(previous)) {
				ListNode hold = new ListNode(e, previous.prev, next);
				next.prev = hold;
				previous.prev.next = hold;
			} else {
				ListNode hold = new ListNode(e, previous, next.next);
				next.next.prev = hold;
				previous.next = hold;
			}
			
		}

		@Override
		public void add(E e) {
			if (e == null) {
				throw new NullPointerException();
			}
			previous.next = new ListNode(e, previous, next);
			next.prev = previous.next;
			lastRetrieved = null;
			size++;
		}
		
	}
	
	@Override
	public ListIterator<E> listIterator(int index) {
		return new LinkedListIterator(index);
	}

	@Override
	public void add(int index, E element) {
		super.add(index, element);
	}

	@Override
	public E set(int index, E element) {
		if(contains(element)) {
			throw new IllegalArgumentException();
		}
		return super.set(index, element);
	}

	@Override
	public int size() {
		return size;
	}

}
