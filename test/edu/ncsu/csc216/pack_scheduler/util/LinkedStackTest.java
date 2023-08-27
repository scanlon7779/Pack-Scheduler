package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests the LinkedStack class methods
 * @author colinscanlon
 *
 */
public class LinkedStackTest {

	/**
	 * Tests that objects are popped in the correct order
	 */
	@Test
	public void testPop() {
		LinkedStack<String> list = new LinkedStack<String>(10);
		list.push("first");
		assertEquals(1, list.size());
		assertEquals("first", list.pop());
		
		// popping multiple 
		list.push("First");
		list.push("Second");
		list.push("Third");
		list.push("Fourth");
		assertEquals(4, list.size());
		assertEquals("Fourth", list.pop());
		assertEquals("Third", list.pop());
		assertEquals("Second", list.pop());
		assertEquals("First", list.pop());
		
		//Adding and removing
		list.push("First");
		list.push("Second");
		list.push("Third");
		assertEquals("Third", list.pop());
		list.push("Fourth");
		assertEquals("Fourth", list.pop());
		list.push("Fifth");
		assertEquals("Fifth", list.pop());
		assertEquals("Second", list.pop());
		assertEquals("First", list.pop());
	}
	
	/**
	 * Tests that objects are added to the front of the list
	 */
	@Test
	public void testPush() {
		LinkedStack<String> list = new LinkedStack<String>(10);
		list.push("First");
		assertEquals(1, list.size());
		list.push("Second");
		assertEquals(2, list.size());
		list.push("Third");
		assertEquals(3, list.size());
		list.push("Fourth");
		assertEquals(4, list.size());
		list.push("Fifth");
		assertEquals(5, list.size());
	}
	/**
	 * Tests that objects are added to the front of the list
	 */
	@Test
	public void testIsEmpty() {
		LinkedStack<String> list = new LinkedStack<String>(10);
		assertTrue(list.isEmpty());
		list.push("First");
		list.push("Second");
		list.push("Third");
		list.push("Fourth");
		assertEquals("Fourth", list.pop());
		assertEquals("Third", list.pop());
		assertEquals("Second", list.pop());
		assertEquals("First", list.pop());
		assertTrue(list.isEmpty());
		
		try {
			list.pop();
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertTrue(list.isEmpty());
		}
		
		list.setCapacity(15);
	}
}
