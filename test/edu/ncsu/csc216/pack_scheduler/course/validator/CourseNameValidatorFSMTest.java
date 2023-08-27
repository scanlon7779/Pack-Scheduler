package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Test for the CourseNameValidatorFSM Class
 * @author colinscanlon
 *
 */
public class CourseNameValidatorFSMTest {

	/**
	 * Test with one letter
	 */
	@Test
	public void testOneLetter() {
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();
		try {
			assertTrue(fsm.isValid("C101"));

		} catch (InvalidTransitionException e) {
			fail();
		}

	}
	
	/**
	 * Test with two letters
	 */
	@Test
	public void testTwoLetters() {
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();
		try {
			assertTrue(fsm.isValid("CS101"));

		} catch (InvalidTransitionException e) {
			fail();
		}
	}
	
	/**
	 * Valid course with three letters
	 */
	@Test
	public void testThreeLetters() {
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();
		try {
			assertTrue(fsm.isValid("CSC101"));

		} catch (InvalidTransitionException e) {
			fail();
		}
	}
	
	/**
	 * Test valid course with four letters
	 */
	@Test
	public void testFourLetters() {
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();
		try {
			assertTrue(fsm.isValid("CSCS101"));

		} catch (InvalidTransitionException e) {
			fail();
		}
	}
	
	/**
	 * Test valid course with suffix
	 */
	@Test
	public void testThreeLettersAndSuffix() {
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();
		try {
			assertTrue(fsm.isValid("CSC101A"));

		} catch (InvalidTransitionException e) {
			fail();
		}
	}
	
	/**
	 * Test with an Invalid char
	 */
	@Test
	public void testNonAlphanumeric() {
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();
		try {
			fsm.isValid("@");
            fail();
		} catch (InvalidTransitionException e) {
            // Success
		}
	}
	
	/**
	 * testing no letters
	 */
	@Test
	public void testNoLetters() {
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();
		try {
			fsm.isValid("101");
            fail();
		} catch (InvalidTransitionException e) {
            // Success
		}
	}
	
	/**
	 * Testing one letter and one digit 
	 */
	@Test
	public void testOneLetterOneDigit() {
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();
		try {
			fsm.isValid("C1C");
            fail();
		} catch (InvalidTransitionException e) {
            // Success
		}
	}
	
	/**
	 * Testing one letter and two digits
	 */
	@Test
	public void testOneLetterTwoDigits() {
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();
		try {
			fsm.isValid("C10C");
            fail();
		} catch (InvalidTransitionException e) {
            // Success
		}
	}
	
	/**
	 * Testing one letter and four digits
	 */
	@Test
	public void testOneLetterFourDigits() {
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();
		try {
			fsm.isValid("C1001C");
            fail();
		} catch (InvalidTransitionException e) {
            // Success
		}
	}
	
	/**
	 * Testing five letters
	 */
	@Test
	public void testFiveLetters() {
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();
		try {
			fsm.isValid("CSCSC");
            fail();
		} catch (InvalidTransitionException e) {
            // Success
		}
	}
	
	/**
	 * Testing suffix with extra letter
	 */
	@Test
	public void testSuffixWithExtraLetter() {
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();
		try {
			fsm.isValid("C101AB");
            fail();
		}  catch (InvalidTransitionException e) {
            // Success
		}
	}
	
	/**
	 * Testing suffix with extra number 
	 */
	@Test
	public void testSuffixWithExtraNumber() {
		CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();
		try {
			fsm.isValid("C101A1");
            fail();
		} catch (InvalidTransitionException e) {
            // Success
		}
	}
	
	
	
	
}
