package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;

import java.util.ListIterator;

import org.junit.Test;

/**
 * Test for the LinkedList class
 * @author colinscanlon
 *
 */
public class LinkedListTest {

	/**
	 * Tests the LinkedList constructor
	 */
	@Test
	public void testLinkedList() {
		LinkedList<String> list = new LinkedList<String>();
		assertEquals(0, list.size());
	}
	
	/**
	 * Tests the add method
	 */
	@Test
	public void testAdd() {
		LinkedList<String> list = new LinkedList<String>();
		list.add(0, "First");
		list.add(1, "Second");
		list.add(2, "Third");
		assertEquals(3, list.size());
		System.out.println(list.listIterator(0).next());
		System.out.println(list.listIterator(1).next());
		System.out.println(list.listIterator(2).next());

		assertEquals("First", list.listIterator(0).next());
		assertEquals("Second", list.listIterator(1).next());
		assertEquals("Third", list.listIterator(2).next());
	}
	
	/**
	 * Tests the set method
	 */
	@Test
	public void testSet() {
		LinkedList<String> list = new LinkedList<String>();
		list.add(0, "First");
		list.add(1, "Second");
		list.add(2, "Third");
		assertEquals(3, list.size());
		assertEquals("First", list.listIterator(0).next());
		list.set(0, "Zero");
		assertEquals(3, list.size());
		assertEquals("Zero", list.listIterator(0).next());
	}
	
	/**
	 * Tests the remove method in the list
	 */
	@Test
	public void testRemove() {
		LinkedList<String> list = new LinkedList<String>();
		list.add(0, "First");
		list.add(1, "Second");
		list.add(2, "Third");
		assertEquals(3, list.size());
		ListIterator<String> li = list.listIterator();
		li.next();
		li.remove();
		assertEquals(2, list.size());
		
		
	}
	
}
