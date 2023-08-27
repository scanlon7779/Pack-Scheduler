/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests CourseRoll class
 * @author Mitthu Tiwari
 * @version 1
 */

public class CourseRollTest {

	/** Setup method for setting up test cases
	 * @throws Exception if error occurs in setup
	 */
	
	@Before
	public void setUp() throws Exception {
		//Blank
	}

	/** Test creating new course roll
	 */
	
	@Test
	public void testNewCourseRollAndSetEnrollmentCap() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "A");
		//CourseRoll roll = new CourseRoll(15); //Update as below
		CourseRoll roll = c.getCourseRoll();
		assertEquals(15, roll.getEnrollmentCap());
		roll.setEnrollmentCap(20);
		assertEquals(20, roll.getEnrollmentCap());
		assertEquals(20, roll.getOpenSeats());
	}
	
	/** Test enrolling students in course
	 */
	
	@Test
	public void testEnroll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "A");
		//CourseRoll roll = new CourseRoll(15); //Update as below
		CourseRoll roll = c.getCourseRoll();
		assertEquals(15, roll.getEnrollmentCap());
		assertEquals(15, roll.getOpenSeats());
		Student s1 = new Student("Steve", "Rogers", "srogers", "srogers@avengers.org", "Avengingw/AP@ssion", 15);
		Student s2 = new Student("Tony", "Stark", "tstark", "tstark@avengers.org", "man0firOn", 12);
		
		//Enrolling first student
		assertTrue(roll.canEnroll(s1));
		roll.enroll(s1);
		assertEquals(14, roll.getOpenSeats());
		
		//Enrolling second student
		assertTrue(roll.canEnroll(s2));
		roll.enroll(s2);
		assertEquals(13, roll.getOpenSeats());
	}
	
	/** Test dropping students from course
	 */
	
	@Test
	public void testDrop() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "A");
		//CourseRoll roll = new CourseRoll(15); //Update as below
		CourseRoll roll = c.getCourseRoll();
		assertEquals(15, roll.getEnrollmentCap());
		assertEquals(15, roll.getOpenSeats());
		Student s1 = new Student("Steve", "Rogers", "srogers", "srogers@avengers.org", "Avengingw/AP@ssion", 15);
		Student s2 = new Student("Tony", "Stark", "tstark", "tstark@avengers.org", "man0firOn", 12);
		Student s3 = new Student("Tchalla", "King of Wakanda", "tkowakanda", "tkowakanda@avengers.org", "WAKANDA4EVER", 18);
		
		//Enroll students
		assertTrue(roll.canEnroll(s1));
		roll.enroll(s1);
		assertEquals(14, roll.getOpenSeats());
		assertTrue(roll.canEnroll(s2));
		roll.enroll(s2);
		assertEquals(13, roll.getOpenSeats());
		
		//Drop students
		roll.drop(s1);
		assertEquals(14, roll.getOpenSeats());
		roll.drop(s2);
		assertEquals(15, roll.getOpenSeats());
		
		//Enroll a new student after dropping first two
		assertTrue(roll.canEnroll(s3));
		roll.enroll(s3);
		assertEquals(14, roll.getOpenSeats());
	}
	
	/** Test setting invalid enrollment capacity
	 */
	
	@Test
	public void testSettingInvalidEnrollmentCapacity() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		//CourseRoll roll = new CourseRoll(10); //Update as below
		CourseRoll roll = c.getCourseRoll();
		assertEquals(10, roll.getEnrollmentCap());
		assertEquals(10, roll.getOpenSeats());
		
		try {
			roll.setEnrollmentCap(5);
			fail();
		}
		
		catch (IllegalArgumentException e) {
			assertEquals("Invalid course record", e.getMessage());
		}
	}
	
	/** Test enrolling same student twice
	 */
	
	@Test
	public void testEnrollingSameStudentTwice() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "A");
		//CourseRoll roll = new CourseRoll(15); //Update as below
		CourseRoll roll = c.getCourseRoll();
		assertEquals(15, roll.getEnrollmentCap());
		assertEquals(15, roll.getOpenSeats());
		Student s = new Student("Tchalla", "King of Wakanda", "tkowakanda", "tkowakanda@avengers.org", "WAKANDA4EVER", 18);
		roll.enroll(s);
		
		try {
			roll.enroll(s);
		}
		
		catch (IllegalArgumentException e) {
			assertEquals("The course cannot be added", e.getMessage());
		}
	}
	
	/** Test dropping same student twice
	 */
	
	@Test
	public void testDroppingSameStudentTwice() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 15, "A");
		//CourseRoll roll = new CourseRoll(15); //Update as below
		CourseRoll roll = c.getCourseRoll();
		assertEquals(15, roll.getEnrollmentCap());
		assertEquals(15, roll.getOpenSeats());
		Student s = new Student("Tony", "Stark", "tstark", "tstark@avengers.org", "man0firOn", 12);
		roll.drop(s);
		
		try {
			roll.drop(s);
		}
		
		catch (IllegalArgumentException e) {
			assertEquals("Course cannot be removed from schedule.", e.getMessage());
		}
	}
}
