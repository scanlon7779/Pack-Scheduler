/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests ConflictException
 * @author Mitthu Tiwari
 * @version 1
 */

public class ConflictExceptionTest {

	/**
	 * Sets up test cases/test class
	 * @throws java.lang.Exception if conflict is found between two activities
	 */
	
	@Before
	public void setUp() throws Exception {
		//Blank
	}

	/**
	 * Test method for ConflictException constructor with String parameter
	 */
	
	@Test
	public void testConflictExceptionString() {
	    ConflictException ce = new ConflictException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	} 

	/**
	 * Test method for ConflictException constructor without String parameter
	 */
	
	@Test
	public void testConflictException() {
		ConflictException e = new ConflictException();
		assertEquals("Schedule conflict.", e.getMessage());
	}

}
