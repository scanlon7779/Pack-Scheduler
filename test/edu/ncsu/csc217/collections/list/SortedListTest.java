package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * tests functionality of SortedList class and its methods
 * @author Manasa Chinta
 */
public class SortedListTest {

	/**
	 * tests constructor for SortedList
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		
		//testing size 0 for newly constructed object, is empty
		SortedList<String> empty = new SortedList<String>();
		assertEquals(0, empty.size());
		assertTrue(empty.isEmpty());
		
		//Test that the list grows by adding at least 11 elements
		list.add("apple");
		list.add("apricot");
		list.add("banana");
		list.add("cantaloupe");
		list.add("blueberry");
		list.add("date");
		list.add("fig");
		list.add("strawberry");
		list.add("raspberry");
		list.add("grape");
		list.add("blackberry");
		assertEquals(11, list.size());
		
	}
	/**
	 * tests add() method for a sorted list with valid input strings
	 * and invalid strings null and duplicate elements
	 * throws NullPointerException if specified element is null
	 * throws IllegalArgumentException if specified element is a duplicate of another element
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		//Test adding to the front
		list.add("apple");
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));
		
		//adding other fruits which will come after apple and banana
		list.add("cherry");
		assertEquals(3, list.size());
		assertEquals("cherry", list.get(2));
		
		list.add("date");
		assertEquals(4, list.size());
		assertEquals("date", list.get(3));
		
		//Test adding to the middle
		list.add("cantaloupe");
		assertEquals(5, list.size());
		assertEquals("cantaloupe", list.get(2));
		
		//Test adding fruit to the end
		list.add("fig");
		assertEquals(6, list.size());
		assertEquals("fig", list.get(5));
		
		//Test adding a null element
		try {
			list.add(null);
		} catch (NullPointerException n) {
			//assertTrue(true);
		}
		
		//Test adding a duplicate element
		try {
			list.add("cantaloupe");
		} catch (IllegalArgumentException i) {
			//assertTrue(true);
		}
	
	}
	/**
	 * tests get() method of SortedList for valid and invalid commands
	 * throws IndexOutOfBoundsException if index being called is less than 0 or greater
	 * than or equal to list size
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		//Test getting an element from an empty list
		try {
			list.get(0);
			fail();
		} catch (IndexOutOfBoundsException i){
			//assertTrue(true);
		}
		
		//Add some elements to the list
		list.add("apple");
		list.add("banana");
		list.add("cantaloupe");
		list.add("date");
		list.add("fig");
		
		//Test getting an element at an index < 0
		try {
			list.get(-1);
		} catch (IndexOutOfBoundsException i) {
			//assertTrue(true);
			assertEquals("apple", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("cantaloupe", list.get(2));
			assertEquals("date", list.get(3));
			assertEquals("fig", list.get(4));	
		}
		
		//Test getting an element at size
		try {
			list.get(5);
		} catch (IndexOutOfBoundsException i) {
			//assertTrue(true);
			assertEquals("apple", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("cantaloupe", list.get(2));
			assertEquals("date", list.get(3));
			assertEquals("fig", list.get(4));	
		}

		//test getting from front of list
		String a = "apple";
		assertEquals(a, list.get(0));
		
		//test getting from middle of list
		String b = "cantaloupe";
		assertEquals(b, list.get(2));
		
		//test getting from end of list
		String c = "fig";
		assertEquals(c, list.get(4));
	}
	
	/**
	 * tests the remove() method in SortedList for valid and invalid commands
	 * throws an IndexOutOfBounds exception if the index is less than 0
	 * or greater than or equal to the list size
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//Test removing from an empty list
		try {
			list.remove(0);	
			fail();
		} catch (IndexOutOfBoundsException i){
			//assertTrue(true);
		}

		//Add some elements to the list - at least 4
		list.add("apple");
		list.add("banana");
		list.add("cantaloupe");
		list.add("date");
		list.add("fig");
		
		//Test removing an element at an index < 0
		try {
			list.remove(-1);
		} catch (IndexOutOfBoundsException i) {
			//assertTrue(true);
			assertEquals("apple", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("cantaloupe", list.get(2));
			assertEquals("date", list.get(3));
			assertEquals("fig", list.get(4));	
		}
		
		//Test removing an element at size
		try {
			list.remove(5);
		} catch (IndexOutOfBoundsException i) {
			//assertTrue(true);
			assertEquals("apple", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("cantaloupe", list.get(2));
			assertEquals("date", list.get(3));
			assertEquals("fig", list.get(4));	
		}
		
		//Test removing a middle element
		list.remove(2);
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("date", list.get(2));
		assertEquals("fig", list.get(3));	

		//Test removing the last element
		list.remove(3);
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("date", list.get(2));
		
		//Test removing the first element
		list.remove(0);
		assertEquals("banana", list.get(0));
		assertEquals("date", list.get(1));
		
		//Test removing the last element
		list.remove(1);
		assertEquals("banana", list.get(0));

	}
	
	/**
	 * tests indexOf() method in SortedList. Should return the index of the
	 * specified element or return -1 if it doesn't exist
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		//Test indexOf on an empty list
		assertEquals(-1, list.indexOf("apple"));
		
		//Add some elements
		list.add("apple");
		list.add("banana");
		list.add("cantaloupe");
		list.add("date");
		list.add("fig");
		
		//Test various calls to indexOf for elements in the list
		//and not in the list
		
		assertEquals(0, list.indexOf("apple"));
		assertEquals(1, list.indexOf("banana"));
		assertEquals(2, list.indexOf("cantaloupe"));
		assertEquals(3, list.indexOf("date"));
		assertEquals(4, list.indexOf("fig"));
		
		//not in list
		assertEquals(-1, list.indexOf("cranberry"));
		assertEquals(-1, list.indexOf("dragon fruit"));
		assertEquals(-1, list.indexOf("orange"));
		
		//Test checking the index of null
		try {
			list.indexOf(null);
		} catch (NullPointerException n) {
			//assertTrue(true);
		}
	}
	
	/**
	 * clears all elements in the list
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		//Add some elements
		list.add("apple");
		list.add("banana");
		list.add("cantaloupe");
		list.add("date");
		list.add("fig");
		
		//Clear the list
		list.clear();
		
		//Test that the list is empty
		assertSame(list.size(), 0);
	}
	
	/**
	 * tests the isEmpty() method in SortedList
	 * should clear the elements in the list
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		//Test that the list starts empty
		assertSame(list.size(), 0);
		
		//Add at least one element
		list.add("apple");
		list.add("banana");
		list.add("cantaloupe");
		list.add("date");
		list.add("fig");
		
		//Check that the list is no longer empty
		assertSame(list.size(), 5);
		assertEquals(0, list.indexOf("apple"));
		assertEquals(1, list.indexOf("banana"));
		assertEquals(2, list.indexOf("cantaloupe"));
		assertEquals(3, list.indexOf("date"));
		assertEquals(4, list.indexOf("fig"));
	}

	/**
	 * tests the contains() method in SortedList to check for a specified
	 * element in the list
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//Test the empty list case
		assertFalse(list.contains("apple"));
		assertSame(list.size(), 0);
		
		//Add some elements
		list.add("apple");
		list.add("banana");
		list.add("cantaloupe");
		list.add("date");
		list.add("fig");
		
		//Test some true and false cases
		assertTrue(list.contains("apple"));
		assertTrue(list.contains("date"));
		assertTrue(list.contains("fig"));
		assertFalse(list.contains("fruit"));
	}
	
	/**
	 * test that two sorted lists are equal in 
	 * the equals() method in sorted list
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//Make two lists the same and one list different
		list1.add("apple");
		list1.add("banana");
		list1.add("cantaloupe");
		list1.add("date");
		list1.add("fig");
		
		list2.add("apple");
		list2.add("banana");
		list2.add("cantaloupe");
		list2.add("date");
		list2.add("fig");
		
		list3.add("orange");
		list3.add("dragon fruit");
		list3.add("star fruit");
		list3.add("mango");
		list3.add("tomato");
		
		//Test for equality and non-equality
		assertEquals(list1, list2);
		assertFalse(list1.equals(list3));
	}
	
	/**
	 * Testing hashCode method in SortedList without null parameters
	 * If 2 objects are equal to each other, they have the same hash code.
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//Make two lists the same and one list different
		list1.add("apple");
		list1.add("banana");
		list1.add("cantaloupe");
		list1.add("date");
		list1.add("fig");
		
		list2.add("apple");
		list2.add("banana");
		list2.add("cantaloupe");
		list2.add("date");
		list2.add("fig");
		
		list3.add("orange");
		list3.add("dragon fruit");
		list3.add("star fruit");
		list3.add("mango");
		list3.add("tomato");
		
		//Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());

        // Test for each of the fields
        assertNotEquals(list1.hashCode(), list3.hashCode());
        assertNotEquals(list3.hashCode(), list1.hashCode());
	}
}
 