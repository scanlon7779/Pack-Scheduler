package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests the InvalidTransitionException
 * @author mickeyleuang
 *
 */
public class InvalidTransitionExceptionTest {

	/**
	 * Tests an invalid transition exception with a custom message
	 */
	@Test
	public void testInvalidTransitionExceptionString() {
		InvalidTransitionException ite = new InvalidTransitionException("Custom exception message");
	    assertEquals("Custom exception message", ite.getMessage());
	}

	/**
	 * Tests the invalid transition exception with the default message
	 */
	@Test
	public void testInvalidTransitionException() {
		InvalidTransitionException ite = new InvalidTransitionException();
	    assertEquals("Invalid FSM Transition.", ite.getMessage());
	}

}
