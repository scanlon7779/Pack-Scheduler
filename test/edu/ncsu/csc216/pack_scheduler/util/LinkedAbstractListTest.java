/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;


import org.junit.Test;

/**
 * Tests LinkedAbstractList class
 * 
 * @author Mikato Leuang
 *
 */
public class LinkedAbstractListTest {

	/**
	 * Tests the size method of ArrayList
	 */
	@Test
	public void testSize() {
		//E item = (E)(new Object());
		//E[] list = (E[])(new Object[10]);
		LinkedAbstractList<String> linkedList = new LinkedAbstractList<String>(10);
		assertEquals(linkedList.size(), 0);
		linkedList.add(0, "yeet");
		assertEquals(1, linkedList.size());
	}

	/**
	 * Test the array list constructor
	 */
	@Test
	public void testLinkedAbstractList() {
		LinkedAbstractList<String> linkedList = new LinkedAbstractList<String>(10);
		assertEquals(0, linkedList.size());
		
		try {
			LinkedAbstractList<String> linkedList2 = new LinkedAbstractList<String>(-1);
			assertNull(linkedList2);
		} catch (IllegalArgumentException e) {
			// Success
			
		}
		
		LinkedAbstractList<String> linkedList3 = new LinkedAbstractList<String>(1);
		assertEquals(0, linkedList.size());
		linkedList3.setCapacity(2);
		linkedList3.add(0, "Hello");
		linkedList3.add(1, "Hello2");
		
		
		
	}

	/**
	 * Tests add at a specific index
	 */
	@Test
	public void testAddIntE() {
		LinkedAbstractList<String> arrayList = new LinkedAbstractList<String>(10);
		assertEquals(0, arrayList.size());
		arrayList.add(0, "chicken");
		assertEquals("chicken", arrayList.get(0));
		arrayList.add(0, "beef");
		assertEquals("beef", arrayList.get(0));
		
		LinkedAbstractList<String> arrayList2 = new LinkedAbstractList<String>(10);
		try {
			assertEquals(0, arrayList2.size());
			arrayList2.add(0, "chicken");
			arrayList2.add(1, null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(1, arrayList2.size());
			// Success 
		}
		
		LinkedAbstractList<String> arrayList3 = new LinkedAbstractList<String>(10);
		try {
			assertEquals(0, arrayList3.size());
			arrayList3.add(0, "chicken");
			arrayList3.add(1, "chicken");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1, arrayList3.size());
			// Success 
		}
		
		LinkedAbstractList<String> arrayList4 = new LinkedAbstractList<String>(10);
		try {
			assertEquals(0, arrayList4.size());
			arrayList4.add(0, "chicken");
			arrayList4.add(-1, "salad");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, arrayList4.size());
			// Success 
		}
		
	}

	/**
	 * Tests remove at a specific index
	 */
	@Test
	public void testRemoveInt() {
		LinkedAbstractList<String> arrayList = new LinkedAbstractList<String>(10);
		assertEquals(0, arrayList.size());
		arrayList.add(0, "chicken");
		assertEquals("chicken", arrayList.get(0));
		arrayList.add(0, "beef");
		assertEquals("beef", arrayList.get(0));
		arrayList.remove(0);
		assertEquals(1, arrayList.size());
		assertEquals("chicken", arrayList.get(0));
	}

	/**
	 * Test the get method
	 */
	@Test
	public void testGetInt() {
		LinkedAbstractList<String> arrayList = new LinkedAbstractList<String>(10);
		assertEquals(0, arrayList.size());
		arrayList.add(0, "chicken");
		assertEquals("chicken", arrayList.get(0));
	}

	/**
	 * Test the set method
	 */
	@Test
	public void testSetIntE() {
		LinkedAbstractList<String> arrayList = new LinkedAbstractList<String>(10);
		assertEquals(0, arrayList.size());
		arrayList.add(0, "chicken");
		assertEquals("chicken", arrayList.get(0));
		arrayList.set(0, "taco");
		assertEquals("taco", arrayList.get(0));
	}
}
