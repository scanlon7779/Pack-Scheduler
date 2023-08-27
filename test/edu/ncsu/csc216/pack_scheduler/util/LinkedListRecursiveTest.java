/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for the LinkedListRecursive class
 * @author colinscanlon
 *
 */
public class LinkedListRecursiveTest {

	/**
	 * Tests the constructor for LinkedListRecursive
	 */
	@Test
	public void testLinkedListRecursive() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		assertEquals(0, list.size());
	}
	
	/**
	 * Tests the isEmpty method
	 */
	@Test
	public void testIsEmpty() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		assertTrue(list.isEmpty());
		list.add("one");
		assertFalse(list.isEmpty());
	}
	
	/**
	 * testing the two add methods
	 */
	@Test
	public void testAdd() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		list.add("First");
		list.add("Second");
		list.add("Third");
		assertEquals(3, list.size());
		assertEquals("First", list.get(0));
		assertEquals("Second", list.get(1));
		assertEquals("Third", list.get(2));
		
		LinkedListRecursive<String> list2 = new LinkedListRecursive<String>();
		list2.add(0, "First");
		list2.add(1, "Second");
		list2.add(2, "Third");
		assertEquals(3, list2.size());
		assertEquals("First", list2.get(0));
		assertEquals("Second", list2.get(1));
		assertEquals("Third", list2.get(2));
	}
	
	/**
	 * Testing the two remove methods
	 */
	@Test
	public void testRemove() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		list.add("First");
		list.add("Second");
		list.add("Third");
		assertEquals(3, list.size());
		list.remove(0);
		assertEquals(2, list.size());
		assertEquals("Second", list.get(0));
		assertEquals("Third", list.get(1));
		list.remove(1);
		assertEquals(1, list.size());
		assertEquals("Second", list.get(0));	
		
		LinkedListRecursive<String> list2 = new LinkedListRecursive<String>();
		list2.add("First");
		list2.add("Second");
		list2.add("Third");
		list2.remove("First");
		assertEquals(2, list2.size());
		assertEquals("Second", list2.get(0));
		assertEquals("Third", list2.get(1));
		
	}
	
	/**
	 * Tests the set method
	 */
	@Test
	public void testSet() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		list.add("First");
		list.add("Second");
		list.add("Third");
		list.set(0, "Zero");
		assertEquals(3, list.size());
		assertEquals("Zero", list.get(0));
		assertEquals("Second", list.get(1));
		assertEquals("Third", list.get(2));
	}
	
	
}
