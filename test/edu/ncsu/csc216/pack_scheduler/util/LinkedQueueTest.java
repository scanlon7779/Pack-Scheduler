package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Test for the LinkedQueue class
 * @author colinscanlon
 *
 */
public class LinkedQueueTest {
	/**
	 * Tests the enqueue method
	 */
	@Test
	public void testEnqueue() {
		LinkedQueue<String> list = new LinkedQueue<String>(10);
		list.enqueue("First");
		assertEquals(1, list.size());
		list.enqueue("Second");
		assertEquals(2, list.size());
		list.enqueue("Third");
		assertEquals(3, list.size());
		list.enqueue("Fourth");
		assertEquals(4, list.size());
		list.enqueue("Fifth");
		assertEquals(5, list.size());
	}
	
	/**
	 * test the dequeue method
	 */
	@Test
	public void testDequeue() {
		LinkedQueue<String> list = new LinkedQueue<String>(10);
		list.enqueue("first");
		assertEquals(1, list.size());
		assertEquals("first", list.dequeue());
		
		// removing multiple 
		list.enqueue("First");
		list.enqueue("Second");
		list.enqueue("Third");
		list.enqueue("Fourth");
		assertEquals(4, list.size());
		assertEquals("First", list.dequeue());
		assertEquals("Second", list.dequeue());
		assertEquals("Third", list.dequeue());
		assertEquals("Fourth", list.dequeue());
		
		//Adding and removing
		list.enqueue("First");
		list.enqueue("Second");
		list.enqueue("Third");
		assertEquals("First", list.dequeue());
		list.enqueue("Fourth");
		assertEquals("Second", list.dequeue());
		list.enqueue("Fifth");
		assertEquals("Third", list.dequeue());
		assertEquals("Fourth", list.dequeue());
		assertEquals("Fifth", list.dequeue());

	}
	
	/**
	 * Tests that objects are added to the front of the list
	 */
	@Test
	public void testIsEmpty() {
		LinkedQueue<String> list = new LinkedQueue<String>(10);
		assertTrue(list.isEmpty());
		list.enqueue("First");
		list.enqueue("Second");
		list.enqueue("Third");
		list.enqueue("Fourth");
		assertEquals("First", list.dequeue());
		assertEquals("Second", list.dequeue());
		assertEquals("Third", list.dequeue());
		assertEquals("Fourth", list.dequeue());
		assertTrue(list.isEmpty());
		
		try {
			list.dequeue();
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertTrue(list.isEmpty());
		}
		
		list.setCapacity(15);
	}

}
