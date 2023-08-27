package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Tests CourseNameValidator class
 * @author colinscanlon
 * @author Mitthu Tiwari
 */
public class CourseNameValidatorTest {
	
	/**
	 * Testing valid course with one letter
	 */
	@Test
	public void testOneLetter() {
		CourseNameValidator cnv = new CourseNameValidator();
		try {
			assertTrue(cnv.isValid("C101"));

		} catch (InvalidTransitionException e) {
			fail();
		}

	}
	
	/**
	 * Testing valid course with two letters
	 */
	@Test
	public void testTwoLetters() {
		CourseNameValidator cnv = new CourseNameValidator();
		try {
			assertTrue(cnv.isValid("CS101"));

		} catch (InvalidTransitionException e) {
			fail();
		}
	}
	
	/**
	 * Testing valid course with three letters
	 */
	@Test
	public void testThreeLetters() {
		CourseNameValidator cnv = new CourseNameValidator();
		try {
			assertTrue(cnv.isValid("CSC101"));

		} catch (InvalidTransitionException e) {
			fail();
		}
	}
	
	/**
	 * Testing valid Course with four letters
	 */
	@Test
	public void testFourLetters() {
		CourseNameValidator cnv = new CourseNameValidator();
		try {
			assertTrue(cnv.isValid("CSCS101"));

		} catch (InvalidTransitionException e) {
			fail();
		}
	}
	
	/**
	 * Testing valid course with a suffix
	 */
	@Test
	public void testThreeLettersAndSuffix() {
		CourseNameValidator cnv = new CourseNameValidator();
		try {
			assertTrue(cnv.isValid("CSC101A"));

		} catch (InvalidTransitionException e) {
			fail();
		}
	}
	
	/**
	 * Testing a valid course with non alphanumeric character
	 */
	@Test
	public void testNonAlphanumeric() {
		CourseNameValidator cnv = new CourseNameValidator();
		try {
			cnv.isValid("@");
            fail();
		} catch (InvalidTransitionException e) {
            // Success
		}
	}
	
	/**
	 * Testing with no letters
	 */
	@Test
	public void testNoLetters() {
		CourseNameValidator cnv = new CourseNameValidator();
		try {
			cnv.isValid("101");
            fail();
		} catch (InvalidTransitionException e) {
            // Success
		}
	}
	
	/**
	 * Test with one letter and one digit
	 */
	@Test
	public void testOneLetterOneDigit() {
		CourseNameValidator cnv = new CourseNameValidator();
		try {
			cnv.isValid("C1C");
            fail();
		} catch (InvalidTransitionException e) {
            // Success
		}
	}

	/**
	 * Test with one letter and two digits
	 */
	@Test
	public void testOneLetterTwoDigits() {
		CourseNameValidator cnv = new CourseNameValidator();
		try {
			cnv.isValid("C10C");
            fail();
		} catch (InvalidTransitionException e) {
            // Success
		}
	}
	
	/**
	 * Test with one letter and four digits
	 */
	@Test
	public void testOneLetterFourDigits() {
		CourseNameValidator cnv = new CourseNameValidator();
		try {
			cnv.isValid("C1001C");
            fail();
		} catch (InvalidTransitionException e) {
            // Success
		}
	}
	
	/**
	 * Test with five letters
	 */
	@Test
	public void testFiveLetters() {
		CourseNameValidator cnv = new CourseNameValidator();
		try {
			cnv.isValid("CSCSC");
            fail();
		} catch (InvalidTransitionException e) {
            // Success
		}
	}
	
	/**
	 * Test with suffix and extra letter
	 */
	@Test
	public void testSuffixWithExtraLetter() {
		CourseNameValidator cnv = new CourseNameValidator();
		try {
			cnv.isValid("C101AB");
            fail();
		}  catch (InvalidTransitionException e) {
            // Success
		}
	}
	
	/**
	 * Test suffix and extra number
	 */
	@Test
	public void testSuffixWithExtraNumber() {
		CourseNameValidator cnv = new CourseNameValidator();
		try {
			cnv.isValid("C101A1");
            fail();
		} catch (InvalidTransitionException e) {
            // Success
		}
	}
	
	/**
	 * Test suffix with non alphanumeric char
	 */
	@Test
	public void testSuffixWithNonAlpha() {
		CourseNameValidator cnv = new CourseNameValidator();
		try {
			cnv.isValid("C10@");
            fail();
		} catch (InvalidTransitionException e) {
            // Success
		}
	}

	
	/**
	 * Test suffix with non alphanumeric char
	 */
	@Test
	public void testNonAlphaInLetters() {
		CourseNameValidator cnv = new CourseNameValidator();
		try {
			cnv.isValid("CS@101");
            fail();
		} catch (InvalidTransitionException e) {
            assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
	}
	
	

}