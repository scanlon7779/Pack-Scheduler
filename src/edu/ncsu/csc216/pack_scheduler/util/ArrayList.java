/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Defines a custom ArrayList to hold elements
 * @author Mikato Leuang
 * @author colinscanlon
 * @param <E> the element of the array list
 * 
 */
public class ArrayList<E> extends AbstractList<E> {
	/** The initial size of the array **/
	private static final int INIT_SIZE = 10;
	/** The list held by the class **/
	private E[] list;
	
	/** The size of the list **/
	private int size;
	
	/**
	 * Constructs an ArrayList with a set size
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		//E item = (E)(new Object());
		list = (E[])(new Object[INIT_SIZE]);
		size = 0;
		//add size implementation
	}
	
	/**
	 * Adds an element to the ArrayList at a given index
	 * @param index that the element is added at in the ArrayList
	 * @param element that is added to the ArrayList
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void add(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		
		for (int i = 0; i < size; i++) {
			if (list[i].equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if(index < size) {
			for(int i = size - 1; i >= index; i--) {
				list[i + 1] = list[i];
			}
		}
		size++;
		if(list.length > size) {
			E[] l = (E[])(new Object[list.length * 2]);
			for(int i = 0; i < list.length; i++) {
				l[i] = list[i];
			}
			list = l;
		}
		list[index] = element;
		
	}
	
	/**
	 * Removes an object at the index in the ArrayList
	 * @param index where the element is removed
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		E e = list[index];
		for(int i = index; i < size - 1; i++) {
			list[i] = list[i + 1];
		}
		list[size - 1] = null;
		size--;
		return e;
	}
	
	/**
	 * Returns the value of the element at the given index for an ArrayList
	 * @param index for the object returned
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		return(E) list[index];
	}
	
	/**
	 * Sets the value of an element at the given index for an ArrayList
	 * @param index where the element is set
	 * @param element to be set
	 * @return E the element set at the index
	 */
	public E set(int index, E element) {
		if(element == null) {
			throw new NullPointerException();
		}
		for (int i = 0; i < size; i++) {
			if (list[i].equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		E e = list[index];
		list[index] = element;
		return e;
	}
	
	/**
	 * Returns the size of an ArrayList
	 */
	@Override
	public int size() {
		return size;
	}
	
}
