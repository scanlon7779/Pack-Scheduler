package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * The tests for ArrayList
 * @author colinscanlon
 *
 * @param <E> The element of the array list
 */
public class ArrayListTest<E> {

	/**
	 * Tests the size method of ArrayList
	 */
	@Test
	public void testSize() {
		//E item = (E)(new Object());
		//E[] list = (E[])(new Object[10]);
		ArrayList<String> arrayList = new ArrayList<String>();
		assertEquals(arrayList.size(), 0);
		arrayList.add(0, "yeet");
		assertEquals(1, arrayList.size());
	}

	/**
	 * Test the array list constructor
	 */
	@Test
	public void testArrayList() {
		ArrayList<String> arrayList = new ArrayList<String>();
		assertEquals(0, arrayList.size());
	}

	/**
	 * Tests add at a specific index
	 */
	@Test
	public void testAddIntE() {
		ArrayList<String> arrayList = new ArrayList<String>();
		assertEquals(0, arrayList.size());
		arrayList.add(0, "chicken");
		assertEquals("chicken", arrayList.get(0));
		arrayList.add(0, "beef");
		assertEquals("beef", arrayList.get(0));
		
		ArrayList<String> arrayList2 = new ArrayList<String>();
		try {
			assertEquals(0, arrayList2.size());
			arrayList2.add(0, "chicken");
			arrayList2.add(1, null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(1, arrayList2.size());
			// Success 
		}
		
		ArrayList<String> arrayList3 = new ArrayList<String>();
		try {
			assertEquals(0, arrayList3.size());
			arrayList3.add(0, "chicken");
			arrayList3.add(1, "chicken");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1, arrayList3.size());
			// Success 
		}
		
		ArrayList<String> arrayList4 = new ArrayList<String>();
		try {
			assertEquals(0, arrayList4.size());
			arrayList4.add(0, "chicken");
			arrayList4.add(-1, "salad");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, arrayList4.size());
			// Success 
		}
		
		ArrayList<String> arrayList5 = new ArrayList<String>();
		try {
			assertEquals(0, arrayList5.size());
			arrayList5.add(0, "chicken");
			arrayList5.add(1, "tofu");
			assertEquals(2, arrayList5.size());
		} catch (NullPointerException e) {
			fail();
			// Success 
		}
		
	}

	/**
	 * Tests remove at a specific index
	 */
	@Test
	public void testRemoveInt() {
		ArrayList<String> arrayList = new ArrayList<String>();
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
		ArrayList<String> arrayList = new ArrayList<String>();
		assertEquals(0, arrayList.size());
		arrayList.add(0, "chicken");
		assertEquals("chicken", arrayList.get(0));
	}

	/**
	 * Test the set method
	 */
	@Test
	public void testSetIntE() {
		ArrayList<String> arrayList = new ArrayList<String>();
		assertEquals(0, arrayList.size());
		arrayList.add(0, "chicken");
		assertEquals("chicken", arrayList.get(0));
		arrayList.set(0, "taco");
		assertEquals("taco", arrayList.get(0));
	}

}
