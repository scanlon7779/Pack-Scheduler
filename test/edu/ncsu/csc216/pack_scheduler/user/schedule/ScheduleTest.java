/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests Schedule class
 * @author Mitthu Tiwari
 * @version 1
 */

public class ScheduleTest {

	/**
	 * Set up method for test cases
	 * @throws Exception if error occurs in set up
	 */
	
	@Before
	public void setUp() throws Exception {
		//Blank
	}

	/** Test creating new schedule with constructor
	 */
	
	@Test
	public void testCreateSchedule() {
		Schedule schedule = new Schedule();
		assertEquals(0, schedule.getScheduledCourses().length);
		assertEquals("My Schedule", schedule.getTitle());
	}
	
	/** Test adding courses to schedule
	 */
	
	@Test
	public void testAddCourseToSchedule() {
		Schedule schedule = new Schedule();
		Course csc116 = new Course("CSC116", "Intro to Programming - Java", "001", 3, "jdyoung2", 100, "MW", 910, 1100);
		Course csc217 = new Course("CSC217", "Software Development Fundamentals Lab", "601", 1, "sesmith5", 50, "A");
		Course csc226 = new Course("CSC226", "Discrete Mathematics for Computer Scientists", "001", 3, "tmbarnes", 200, "MWF", 935, 1025);
		Course csc216 = new Course("CSC216", "Software Development Fundamentals", "002", 3, "ixdoming", 100, "MW", 1330, 1445);
		
		//Adding CSC116 to schedule
		assertTrue(schedule.canAdd(csc116));
		assertTrue(schedule.addCourseToSchedule(csc116));
		assertEquals(1, schedule.getScheduledCourses().length);
		assertEquals(3, schedule.getScheduleCredits());
		
		//Adding CSC217 to schedule
		assertTrue(schedule.canAdd(csc217));
		assertTrue(schedule.addCourseToSchedule(csc217));
		assertEquals(2, schedule.getScheduledCourses().length);
		assertEquals(4, schedule.getScheduleCredits());
		
		//Trying to add CSC226, which conflicts with CSC116, to schedule
		try {
			assertTrue(schedule.addCourseToSchedule(csc226));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("The course cannot be added due to a conflict.", e.getMessage());
		}
		
		//Adding CSC216 to schedule
		assertTrue(schedule.canAdd(csc216));
		assertTrue(schedule.addCourseToSchedule(csc216));
		assertEquals(3, schedule.getScheduledCourses().length);
		
		//Trying to add CSC216 to schedule again
		try {
			assertTrue(schedule.addCourseToSchedule(csc216));
		} catch (IllegalArgumentException exception) {
			assertEquals("You are already enrolled in CSC216", exception.getMessage());
		}
		
		//Checking that string array of courses contains 3 courses
		assertEquals(3, schedule.getScheduledCourses().length);
		
		//Checking that first row in string array of courses is CSC116
		assertEquals("CSC116", schedule.getScheduledCourses()[0][0]);
		assertEquals("001", schedule.getScheduledCourses()[0][1]);
		assertEquals("Intro to Programming - Java", schedule.getScheduledCourses()[0][2]);
		assertEquals("MW 9:10AM-11:00AM", schedule.getScheduledCourses()[0][3]);
		assertEquals("3", schedule.getScheduledCourses()[0][4]);
		
		//Checking that second row in string array of courses is CSC217
		assertEquals("CSC217", schedule.getScheduledCourses()[1][0]);
		assertEquals("601", schedule.getScheduledCourses()[1][1]);
		assertEquals("Software Development Fundamentals Lab", schedule.getScheduledCourses()[1][2]);
		assertEquals("Arranged", schedule.getScheduledCourses()[1][3]);
		assertEquals("1", schedule.getScheduledCourses()[1][4]);
		
		//Checking that third row in string array of courses is CSC216
		assertEquals("CSC216", schedule.getScheduledCourses()[2][0]);
		assertEquals("002", schedule.getScheduledCourses()[2][1]);
		assertEquals("Software Development Fundamentals", schedule.getScheduledCourses()[2][2]);
		assertEquals("MW 1:30PM-2:45PM", schedule.getScheduledCourses()[2][3]);
		assertEquals("3", schedule.getScheduledCourses()[2][4]);
		
		//Checking that total number of credits in schedule is 7
		assertEquals(7, schedule.getScheduleCredits());
	}
	
	/** Test removing courses from schedule
	 */
	
	@Test
	public void testRemoveCourseFromSchedule() {
		Schedule schedule = new Schedule();
		Course csc116 = new Course("CSC116", "Intro to Programming - Java", "001", 3, "jdyoung2", 100, "MW", 910, 1100);
		Course csc217 = new Course("CSC217", "Software Development Fundamentals Lab", "601", 1, "sesmith5", 50, "A");
		Course csc216 = new Course("CSC216", "Software Development Fundamentals", "002", 3, "ixdoming", 100, "MW", 1330, 1445);
		
		//Adding all 3 course objects to schedule
		assertTrue(schedule.canAdd(csc116));
		assertTrue(schedule.addCourseToSchedule(csc116));
		assertTrue(schedule.canAdd(csc217));
		assertTrue(schedule.addCourseToSchedule(csc217));
		assertTrue(schedule.canAdd(csc216));
		assertTrue(schedule.addCourseToSchedule(csc216));
		assertEquals(3, schedule.getScheduledCourses().length);
		assertEquals(7, schedule.getScheduleCredits());
		
		//Removing CSC216 from schedule
		assertTrue(schedule.removeCourseFromSchedule(csc216));
		assertEquals(2, schedule.getScheduledCourses().length);
		assertEquals(4, schedule.getScheduleCredits());
		
		//Removing CSC217 from schedule
		assertTrue(schedule.removeCourseFromSchedule(csc217));
		assertEquals(1, schedule.getScheduledCourses().length);
		assertEquals(3, schedule.getScheduleCredits());
		
		//Removing CSC116 from schedule
		assertTrue(schedule.removeCourseFromSchedule(csc116));
		assertEquals(0, schedule.getScheduledCourses().length);
		assertEquals(0, schedule.getScheduleCredits());
		
		//Adding back CSC216 to schedule
		assertTrue(schedule.canAdd(csc216));
		assertTrue(schedule.addCourseToSchedule(csc216));
		assertEquals(1, schedule.getScheduledCourses().length);
		
		//Checking that string array of courses only contains CSC216 with the appropriate name, section, title, and meeting string
		assertEquals("CSC216", schedule.getScheduledCourses()[0][0]);
		assertEquals("002", schedule.getScheduledCourses()[0][1]);
		assertEquals("Software Development Fundamentals", schedule.getScheduledCourses()[0][2]);
		assertEquals("MW 1:30PM-2:45PM", schedule.getScheduledCourses()[0][3]);
		assertEquals("3", schedule.getScheduledCourses()[0][4]);
		
		//Checking that total number of credits in schedule is 3
		assertEquals(3, schedule.getScheduleCredits());
	}
	
	/** Test resetting schedule
	 */
	
	@Test
	public void testResetSchedule() {
		Schedule schedule = new Schedule();
		Course csc116 = new Course("CSC116", "Intro to Programming - Java", "001", 3, "jdyoung2", 100, "MW", 910, 1100);
		Course csc217 = new Course("CSC217", "Software Development Fundamentals Lab", "601", 1, "sesmith5", 50, "A");
		Course csc216 = new Course("CSC216", "Software Development Fundamentals", "002", 3, "ixdoming", 100, "MW", 1330, 1445);
		
		//Adding all 3 course objects to schedule
		assertTrue(schedule.canAdd(csc116));
		assertTrue(schedule.addCourseToSchedule(csc116));
		assertTrue(schedule.canAdd(csc217));
		assertTrue(schedule.addCourseToSchedule(csc217));
		assertTrue(schedule.canAdd(csc216));
		assertTrue(schedule.addCourseToSchedule(csc216));
		assertEquals(3, schedule.getScheduledCourses().length);
		assertEquals(7, schedule.getScheduleCredits());
		
		//Setting schedule title to new title
		schedule.setTitle("Fall 2020 Schedule");
		
		//Resetting schedule
		schedule.resetSchedule();
		
		//Checking that schedule size is 0
		assertEquals(0, schedule.getScheduledCourses().length);
		
		//Checking that schedule title got set to default title
		assertEquals("My Schedule", schedule.getTitle());
		
		//Checking that total number of credits in schedule is 0
		assertEquals(0, schedule.getScheduleCredits());
	}
	
	/** Test setting and getting schedule title
	 */
	
	@Test
	public void testSetAndGetSchedule() {
		Schedule schedule = new Schedule();
		schedule.setTitle("A Very Wholesome Schedule");
		assertEquals("A Very Wholesome Schedule", schedule.getTitle());
	}
}
