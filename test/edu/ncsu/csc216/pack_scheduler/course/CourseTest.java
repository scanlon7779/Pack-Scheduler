/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Course class.
 * 
 * Note that test methods for all getters have been omitted. They will be tested
 * through other methods.
 * 
 * @author Sarah Heckman
 */
public class CourseTest {

    /** Course name */
    private static final String NAME = "CSC216";
    /** Course title */
    private static final String TITLE = "Software Development Fundamentals";
    /** Course section */
    private static final String SECTION = "001";
    /** Course credits */
    private static final int CREDITS = 3;
    /** Course instructor id */
    private static final String INSTRUCTOR_ID = "sesmith5";
    /** Course course roll*/
    private static final int ENROLLMENT_CAP = 100;
    /** Course meeting days */
    private static final String MEETING_DAYS = "MW";
    /** Course start time */
    private static final int START_TIME = 1330;
    /** Course end time */
    private static final int END_TIME = 1445;

    /**
     * Tests the Course constructor with all field parameters.
     */
    @Test
    public void testCourseStringStringStringIntStringStringIntInt() {
        // Test a valid construction
        Course c = null;
        try {
            c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
            assertEquals(NAME, c.getName());
            assertEquals(TITLE, c.getTitle());
            assertEquals(SECTION, c.getSection());
            assertEquals(CREDITS, c.getCredits());
            assertEquals(INSTRUCTOR_ID, c.getInstructorId());
            assertEquals(MEETING_DAYS, c.getMeetingDays());
            assertEquals(START_TIME, c.getStartTime());
            assertEquals(END_TIME, c.getEndTime());
        } catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Tests the Course constructor with 4 parameters.
     */
    @Test
    public void testCourseStringStringStringIntStringString() {
        // Test a valid construction and make sure values are correct
        Course c = null;
        try {
            c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "A");
            assertEquals(NAME, c.getName());
            assertEquals(TITLE, c.getTitle());
            assertEquals(SECTION, c.getSection());
            assertEquals(CREDITS, c.getCredits());
            assertEquals(INSTRUCTOR_ID, c.getInstructorId());
            assertEquals("A", c.getMeetingDays());
            assertEquals(0, c.getStartTime());
            assertEquals(0, c.getEndTime());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    /**
     * Tests setName(). This can ONLY be done through the Course constructor
     */
    @Test
    public void testSetName() {

        // Testing valid names
        try {
            Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
            assertEquals("CSC216", c1.getName());

            Course c2 = new Course("E115", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
            assertEquals("E115", c2.getName());

            Course c3 = new Course("MA141", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME,
                    END_TIME);
            assertEquals("MA141", c3.getName());

            Course c4 = new Course("HESF101", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME,
                    END_TIME);
            assertEquals("HESF101", c4.getName());

            Course c5 = new Course("CSC116", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME,
                    END_TIME);
            assertEquals("CSC116", c5.getName());
        } catch (IllegalArgumentException e) {
            fail();
        }

        // Testing for null name - IAE should be thrown
        Activity c = null;
        try {
            c = new Course(null, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(c);
        }

        // Testing for empty string name - IAE should be thrown
        c = null;
        try {
            c = new Course("", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(c);
        }

        // Testing for name with length less than 5 - IAE should be thrown
        c = null;
        try {
            c = new Course("E11", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(c);
        }

        // Testing for name with length more than 8 - IAE should be thrown
        c = null;
        try {
            c = new Course("HESFQ101", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(c);
        }

        // Testing for name with length more than 8 - IAE should be thrown
        c = null;
        try {
            c = new Course("101", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(c);
        }

        // Testing for a name with no leading characters - IAE should be thrown
        c = null;
        try {
            c = new Course("101ext", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(c);
        }

        // Testing for a name with 5 characters - IAE should be thrown
        c = null;
        try {
            c = new Course("HESFQ101", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(c);
        }

        // Testing for a name with 2 digits - IAE should be thrown
        c = null;
        try {
            c = new Course("HESF01", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(c);
        }

        // Testing for a name with 4 digits - IAE should be thrown
        c = null;
        try {
            c = new Course("CSC2167", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(c);
        }

        // Testing for leading space - IAE should be thrown
        c = null;
        try {
            c = new Course(" CSC216", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(c);
        }

        // Testing for invalid whitespace character - IAE should be thrown
        c = null;
        try {
            c = new Course("CSC\t216", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(c);
        }
        
        // Testing for less than 1 letter
        c = null;
        try {
            c = new Course("216111", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
            fail();
        } catch (IllegalArgumentException e) {
        	assertNull(c);
        }
        
     // Testing for less than 4 letter
        c = null;
        try {
            c = new Course("manasa", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
            fail();
        } catch (IllegalArgumentException e) {
        	assertNull(c);
        }
    }

    /**
     * Tests setTitle().
     */
    @Test
    public void testSetTitle() {
        Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
        assertEquals(NAME, c.getName());
        assertEquals(TITLE, c.getTitle());
        assertEquals(SECTION, c.getSection());
        assertEquals(CREDITS, c.getCredits());
        assertEquals(INSTRUCTOR_ID, c.getInstructorId());
        assertEquals(MEETING_DAYS, c.getMeetingDays());
        assertEquals(START_TIME, c.getStartTime());
        assertEquals(END_TIME, c.getEndTime());

        // Test that setting the title to null doesn't change the title (or anything
        // else).
        try {
            c.setTitle(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(NAME, c.getName());
            assertEquals(TITLE, c.getTitle());
            assertEquals(SECTION, c.getSection());
            assertEquals(CREDITS, c.getCredits());
            assertEquals(INSTRUCTOR_ID, c.getInstructorId());
            assertEquals(MEETING_DAYS, c.getMeetingDays());
            assertEquals(START_TIME, c.getStartTime());
            assertEquals(END_TIME, c.getEndTime());
            assertEquals("Invalid title.", e.getMessage());
        }

        // Test that setting the title to "" doesn't change the title (or anything
        // else).
        try {
            c.setTitle("");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(NAME, c.getName());
            assertEquals(TITLE, c.getTitle());
            assertEquals(SECTION, c.getSection());
            assertEquals(CREDITS, c.getCredits());
            assertEquals(INSTRUCTOR_ID, c.getInstructorId());
            assertEquals(MEETING_DAYS, c.getMeetingDays());
            assertEquals(START_TIME, c.getStartTime());
            assertEquals(END_TIME, c.getEndTime());
            assertEquals("Invalid title.", e.getMessage());
        }

        // Valid set
        c.setTitle("A new title");
        assertEquals(NAME, c.getName());
        assertEquals("A new title", c.getTitle());
        assertEquals(SECTION, c.getSection());
        assertEquals(CREDITS, c.getCredits());
        assertEquals(INSTRUCTOR_ID, c.getInstructorId());
        assertEquals(MEETING_DAYS, c.getMeetingDays());
        assertEquals(START_TIME, c.getStartTime());
        assertEquals(END_TIME, c.getEndTime());
    }

    /**
     * Tests setSection().
     */
    @Test
    public void testSetSection() {
        Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
        assertEquals(NAME, c.getName());
        assertEquals(TITLE, c.getTitle());
        assertEquals(SECTION, c.getSection());
        assertEquals(CREDITS, c.getCredits());
        assertEquals(INSTRUCTOR_ID, c.getInstructorId());
        assertEquals(MEETING_DAYS, c.getMeetingDays());
        assertEquals(START_TIME, c.getStartTime());
        assertEquals(END_TIME, c.getEndTime());

        // Test that setting the section to null doesn't change the section (or anything
        // else).
        try {
            c.setSection(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(NAME, c.getName());
            assertEquals(TITLE, c.getTitle());
            assertEquals(SECTION, c.getSection());
            assertEquals(CREDITS, c.getCredits());
            assertEquals(INSTRUCTOR_ID, c.getInstructorId());
            assertEquals(MEETING_DAYS, c.getMeetingDays());
            assertEquals(START_TIME, c.getStartTime());
            assertEquals(END_TIME, c.getEndTime());
            assertEquals("Invalid section.", e.getMessage());
        }

        // Test that setting the section to "" doesn't change the section (or anything
        // else).
        try { 
            c.setSection("");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(NAME, c.getName());
            assertEquals(TITLE, c.getTitle());
            assertEquals(SECTION, c.getSection());
            assertEquals(CREDITS, c.getCredits());
            assertEquals(INSTRUCTOR_ID, c.getInstructorId());
            assertEquals(MEETING_DAYS, c.getMeetingDays());
            assertEquals(START_TIME, c.getStartTime());
            assertEquals(END_TIME, c.getEndTime());
            assertEquals("Invalid section.", e.getMessage());
        }

        // Test that setting the section to "00" doesn't change the section (or anything
        // else).
        try {
            c.setSection("00");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(NAME, c.getName());
            assertEquals(TITLE, c.getTitle());
            assertEquals(SECTION, c.getSection());
            assertEquals(CREDITS, c.getCredits());
            assertEquals(INSTRUCTOR_ID, c.getInstructorId());
            assertEquals(MEETING_DAYS, c.getMeetingDays());
            assertEquals(START_TIME, c.getStartTime());
            assertEquals(END_TIME, c.getEndTime());
            assertEquals("Invalid section.", e.getMessage());
        }

        // Test that setting the section to "0012" doesn't change the section (or
        // anything else).
        try {
            c.setSection("0012");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(NAME, c.getName());
            assertEquals(TITLE, c.getTitle());
            assertEquals(SECTION, c.getSection());
            assertEquals(CREDITS, c.getCredits());
            assertEquals(INSTRUCTOR_ID, c.getInstructorId());
            assertEquals(MEETING_DAYS, c.getMeetingDays());
            assertEquals(START_TIME, c.getStartTime());
            assertEquals(END_TIME, c.getEndTime());
            assertEquals("Invalid section.", e.getMessage());
        }

        // Test valid section
        c.setSection("002");
        assertEquals(NAME, c.getName());
        assertEquals(TITLE, c.getTitle());
        assertEquals("002", c.getSection());
        assertEquals(CREDITS, c.getCredits());
        assertEquals(INSTRUCTOR_ID, c.getInstructorId());
        assertEquals(MEETING_DAYS, c.getMeetingDays());
        assertEquals(START_TIME, c.getStartTime());
        assertEquals(END_TIME, c.getEndTime());
        
        //test section with letter in it
        try {
            c.setSection("m12");
            fail();
        } catch (IllegalArgumentException e) {
        	 assertEquals("Section should be three digits.", e.getMessage());

        }
    }

    /**
     * Tests that the credits are set correctly.
     */
    @Test
    public void testSetCredits() {
        Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
        assertEquals(NAME, c.getName());
        assertEquals(TITLE, c.getTitle());
        assertEquals(SECTION, c.getSection());
        assertEquals(CREDITS, c.getCredits());
        assertEquals(INSTRUCTOR_ID, c.getInstructorId());
        assertEquals(MEETING_DAYS, c.getMeetingDays());
        assertEquals(START_TIME, c.getStartTime());
        assertEquals(END_TIME, c.getEndTime());

        // Test that setting the credits to 0 doesn't change the credits (or anything
        // else).
        try {
            c.setCredits(0);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(NAME, c.getName());
            assertEquals(TITLE, c.getTitle());
            assertEquals(SECTION, c.getSection());
            assertEquals(CREDITS, c.getCredits());
            assertEquals(INSTRUCTOR_ID, c.getInstructorId());
            assertEquals(MEETING_DAYS, c.getMeetingDays());
            assertEquals(START_TIME, c.getStartTime());
            assertEquals(END_TIME, c.getEndTime());
            assertEquals("Credits should be between 1 and 5, inclusive.", e.getMessage());
        }

        // Test that setting the credits to 6 doesn't change the credits (or anything
        // else).
        try {
            c.setCredits(6);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(NAME, c.getName());
            assertEquals(TITLE, c.getTitle());
            assertEquals(SECTION, c.getSection());
            assertEquals(CREDITS, c.getCredits());
            assertEquals(INSTRUCTOR_ID, c.getInstructorId());
            assertEquals(MEETING_DAYS, c.getMeetingDays());
            assertEquals(START_TIME, c.getStartTime());
            assertEquals(END_TIME, c.getEndTime());
            assertEquals("Credits should be between 1 and 5, inclusive.", e.getMessage());
        }

        // Test valid credits
        c.setCredits(3);
        assertEquals(NAME, c.getName());
        assertEquals(TITLE, c.getTitle());
        assertEquals(SECTION, c.getSection());
        assertEquals(3, c.getCredits());
        assertEquals(INSTRUCTOR_ID, c.getInstructorId());
        assertEquals(MEETING_DAYS, c.getMeetingDays());
        assertEquals(START_TIME, c.getStartTime());
        assertEquals(END_TIME, c.getEndTime());
    }

    /**
     * Tests that the instructor id is set correctly.
     */
    @Test
    public void testSetInstructorId() {
        Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
        assertEquals(NAME, c.getName());
        assertEquals(TITLE, c.getTitle());
        assertEquals(SECTION, c.getSection());
        assertEquals(CREDITS, c.getCredits());
        assertEquals(INSTRUCTOR_ID, c.getInstructorId());
        assertEquals(MEETING_DAYS, c.getMeetingDays());
        assertEquals(START_TIME, c.getStartTime());
        assertEquals(END_TIME, c.getEndTime());


        // Test that setting the instructor id to "" doesn't change the instructor id
        // (or anything else).
        try {
            c.setInstructorId("");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(NAME, c.getName());
            assertEquals(TITLE, c.getTitle());
            assertEquals(SECTION, c.getSection());
            assertEquals(CREDITS, c.getCredits());
            assertEquals(INSTRUCTOR_ID, c.getInstructorId());
            assertEquals(MEETING_DAYS, c.getMeetingDays());
            assertEquals(START_TIME, c.getStartTime());
            assertEquals(END_TIME, c.getEndTime());
            assertEquals("Invalid instructor id.", e.getMessage());
        }

        // Valid set
        c.setInstructorId("jtking");
        assertEquals(NAME, c.getName());
        assertEquals(TITLE, c.getTitle());
        assertEquals(SECTION, c.getSection());
        assertEquals(CREDITS, c.getCredits());
        assertEquals("jtking", c.getInstructorId());
        assertEquals(MEETING_DAYS, c.getMeetingDays());
        assertEquals(START_TIME, c.getStartTime());
        assertEquals(END_TIME, c.getEndTime());
    }

    /**
     * Tests that meeting days and course times are set correctly.
     */
    @Test
    public void testSetMeetingDaysAndTimes() {
        // The code below is commented out until you make some changes to Course.
        // Once those are made, remove the line of code fail() and uncomment the
        // provided tests.

        // Test valid course with meeting times (not arranged)
        Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
        assertEquals(NAME, c1.getName());
        assertEquals(TITLE, c1.getTitle());
        assertEquals(SECTION, c1.getSection());
        assertEquals(CREDITS, c1.getCredits());
        assertEquals(INSTRUCTOR_ID, c1.getInstructorId());
        assertEquals(MEETING_DAYS, c1.getMeetingDays());
        assertEquals(START_TIME, c1.getStartTime());
        assertEquals(END_TIME, c1.getEndTime());

        // Test valid course with arranged
        Course c2 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "A");
        assertEquals(NAME, c2.getName());
        assertEquals(TITLE, c2.getTitle());
        assertEquals(SECTION, c2.getSection());
        assertEquals(CREDITS, c2.getCredits());
        assertEquals(INSTRUCTOR_ID, c2.getInstructorId());
        assertEquals("A", c2.getMeetingDays());
        assertEquals(0, c2.getStartTime());
        assertEquals(0, c2.getEndTime());

        c1.setMeetingDaysAndTime("A", 1300, 1445);
        assertEquals("A", c1.getMeetingDays());
        assertEquals(0, c1.getStartTime());
        assertEquals(0, c1.getEndTime());

        c1.setMeetingDaysAndTime("TH", 1300, 1445);
        assertEquals("TH", c1.getMeetingDays());
        assertEquals(1300, c1.getStartTime());
        assertEquals(END_TIME, c1.getEndTime());

        c2.setMeetingDaysAndTime("MF", 1015, 1130);
        assertEquals("MF", c2.getMeetingDays());
        assertEquals(1015, c2.getStartTime());
        assertEquals(1130, c2.getEndTime());

        // Test invalid meeting days strings
        try {
            // Note that start time should NOT change to 1330 if meeting string is invalid!
            c1.setMeetingDaysAndTime("AM", 1330, 1445);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("TH", c1.getMeetingDays());
            assertEquals(1300, c1.getStartTime());
            assertEquals(1445, c1.getEndTime());
            assertEquals("Invalid meeting days.", e.getMessage());
        }

        try {
            // Note that start time should NOT change to 1330 if meeting string is invalid!
            c1.setMeetingDaysAndTime("XYZ", 1330, 1445);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("TH", c1.getMeetingDays());
            assertEquals(1300, c1.getStartTime());
            assertEquals(1445, c1.getEndTime());
            assertEquals("Invalid meeting days.", e.getMessage());
        }

        try {
            // Note that start time should NOT change to 1330 if meeting string is invalid!
            c1.setMeetingDaysAndTime("m", 1330, 1445);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("TH", c1.getMeetingDays());
            assertEquals(1300, c1.getStartTime());
            assertEquals(1445, c1.getEndTime());
            assertEquals("Invalid meeting days.", e.getMessage());
        }

        try {
            // Note that start time should NOT change to 1330 if meeting string is invalid!
            c1.setMeetingDaysAndTime("MTWS", 1330, 1445);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("TH", c1.getMeetingDays());
            assertEquals(1300, c1.getStartTime());
            assertEquals(1445, c1.getEndTime());
            assertEquals("Invalid meeting days.", e.getMessage());
        }

        try {
            // Note that start time should NOT change to 1330 if meeting string is invalid!
            c1.setMeetingDaysAndTime("MWM", 1330, 1445);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("TH", c1.getMeetingDays());
            assertEquals(1300, c1.getStartTime());
            assertEquals(1445, c1.getEndTime());
            assertEquals("Invalid meeting days.", e.getMessage());
        }

        try {
            // Note that start time should NOT change to 1330 if meeting string is invalid!
            c1.setMeetingDaysAndTime("TMT", 1330, 1445);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("TH", c1.getMeetingDays());
            assertEquals(1300, c1.getStartTime());
            assertEquals(1445, c1.getEndTime());
            assertEquals("Invalid meeting days.", e.getMessage());
        }

        try {
            // Note that start time should NOT change to 1330 if meeting string is invalid!
            c1.setMeetingDaysAndTime("WFW", 1330, 1445);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("TH", c1.getMeetingDays());
            assertEquals(1300, c1.getStartTime());
            assertEquals(1445, c1.getEndTime());
            assertEquals("Invalid meeting days.", e.getMessage());
        }

        try {
            // Note that start time should NOT change to 1330 if meeting string is invalid!
            c1.setMeetingDaysAndTime("MHH", 1330, 1445);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("TH", c1.getMeetingDays());
            assertEquals(1300, c1.getStartTime());
            assertEquals(1445, c1.getEndTime());
            assertEquals("Invalid meeting days.", e.getMessage());
        }

        try {
            // Note that start time should NOT change to 1330 if meeting string is invalid!
            c1.setMeetingDaysAndTime("MWFTF", 1330, 1445);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("TH", c1.getMeetingDays());
            assertEquals(1300, c1.getStartTime());
            assertEquals(1445, c1.getEndTime());
            assertEquals("Invalid meeting days.", e.getMessage());
        }

        // Test invalid start times
        try {
            // Note that meeting days should NOT change if times are invalid!
            c1.setMeetingDaysAndTime("MW", -1, 1445);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid start time.", e.getMessage());
            assertEquals("TH", c1.getMeetingDays());
            assertEquals(1300, c1.getStartTime());
            assertEquals(1445, c1.getEndTime());
        }

        try {
            // Note that meeting days should NOT change if times are invalid!
            c1.setMeetingDaysAndTime("MW", 1330, -1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid end time.", e.getMessage());
            assertEquals("TH", c1.getMeetingDays());
            assertEquals(1300, c1.getStartTime());
            assertEquals(1445, c1.getEndTime());
        }

        try {
            // Note that meeting days should NOT change if times are invalid!
            c1.setMeetingDaysAndTime("MW", 2400, 1445);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid start time.", e.getMessage());
            assertEquals("TH", c1.getMeetingDays());
            assertEquals(1300, c1.getStartTime());
            assertEquals(1445, c1.getEndTime());
        }

        try {
            // Note that meeting days should NOT change if times are invalid!
            c1.setMeetingDaysAndTime("MW", 1330, 2400);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid end time.", e.getMessage());
            assertEquals("TH", c1.getMeetingDays());
            assertEquals(1300, c1.getStartTime());
            assertEquals(1445, c1.getEndTime());
        }

        try {
            // Note that meeting days should NOT change if times are invalid!
            c1.setMeetingDaysAndTime("MW", 1360, 1445);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid start time.", e.getMessage());
            assertEquals("TH", c1.getMeetingDays());
            assertEquals(1300, c1.getStartTime());
            assertEquals(1445, c1.getEndTime());
        }

        try {
            // Note that meeting days should NOT change if times are invalid!
            c1.setMeetingDaysAndTime("MW", 1330, 1360);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid end time.", e.getMessage());
            assertEquals("TH", c1.getMeetingDays());
            assertEquals(1300, c1.getStartTime());
            assertEquals(1445, c1.getEndTime());
        }

        try {
            // Note that meeting days should NOT change if times are invalid!
            c1.setMeetingDaysAndTime("MW", 2300, 1445);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("End time cannot be before start time.", e.getMessage());
            assertEquals("TH", c1.getMeetingDays());
            assertEquals(1300, c1.getStartTime());
            assertEquals(1445, c1.getEndTime());
        }

        try {
            // Note that meeting days should NOT change if times are invalid!
            c1.setMeetingDaysAndTime("MW", 1330, 1200);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("End time cannot be before start time.", e.getMessage());
            assertEquals("TH", c1.getMeetingDays());
            assertEquals(1300, c1.getStartTime());
            assertEquals(1445, c1.getEndTime());
        }
    }

    /**
     * Tests that getMeetingString() works correctly
     */
    @Test
    public void testGetMeetingString() {
        // The code below is commented out until you make some changes to Course.
        // Once those are made, remove the line of code fail() and uncomment the
        // provided tests.

        Activity c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
        assertEquals("MW 1:30PM-2:45PM", c1.getMeetingString());
        Activity c2 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 900, 1035);
        assertEquals("MW 9:00AM-10:35AM", c2.getMeetingString());
        Activity c3 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "A");
        assertEquals("Arranged", c3.getMeetingString());
        Activity c4 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "TH", 1145, 1425);
        assertEquals("TH 11:45AM-2:25PM", c4.getMeetingString());
    }

    /**
     * Tests that the equals method works for all Course fields.
     */
    @Test
    public void testEqualsObject() {
        Activity c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
        Activity c2 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
        Activity c3 = new Course(NAME, "Different", SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
        Activity c4 = new Course(NAME, TITLE, "002", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
        Activity c5 = new Course(NAME, TITLE, SECTION, 5, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
        Activity c6 = new Course(NAME, TITLE, SECTION, CREDITS, "Different", ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
        Activity c7 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "TH", START_TIME, END_TIME);
        Activity c8 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 830, END_TIME);
        Activity c9 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP,  MEETING_DAYS, START_TIME, 1400);

        // Test for equality in both directions
        assertTrue(c1.equals(c2));
        assertTrue(c2.equals(c1));

        // Test for each of the fields
        assertFalse(c1.equals(c3));
        assertFalse(c1.equals(c4));
        assertFalse(c1.equals(c5));
        assertFalse(c1.equals(c6));
        assertFalse(c1.equals(c7));
        assertFalse(c1.equals(c8));
        assertFalse(c1.equals(c9));
    }

    /**
     * Tests that hashCode works correctly.
     */
    @Test
    public void testHashCode() {
    	Activity c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
        Activity c2 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
        Activity c3 = new Course(NAME, "Different", SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
        Activity c4 = new Course(NAME, TITLE, "002", CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
        Activity c5 = new Course(NAME, TITLE, SECTION, 5, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
        Activity c6 = new Course(NAME, TITLE, SECTION, CREDITS, "Different", ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
        Activity c7 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "TH", START_TIME, END_TIME);
        Activity c8 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, 830, END_TIME);
        Activity c9 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP,  MEETING_DAYS, START_TIME, 1400);

        // Test for the same hash code for the same values
        assertEquals(c1.hashCode(), c2.hashCode());

        // Test for each of the fields
        assertNotEquals(c1.hashCode(), c3.hashCode());
        assertNotEquals(c1.hashCode(), c4.hashCode());
        assertNotEquals(c1.hashCode(), c5.hashCode());
        assertNotEquals(c1.hashCode(), c6.hashCode());
        assertNotEquals(c1.hashCode(), c7.hashCode());
        assertNotEquals(c1.hashCode(), c8.hashCode());
        assertNotEquals(c1.hashCode(), c9.hashCode());
    }

    /**
     * Tests that toString returns the correct comma-separated value.
     */
    @Test
    public void testToString() {
        Activity c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
        String s1 = "CSC216,Software Development Fundamentals,001,3,sesmith5,100,MW,1330,1445";
        assertEquals(s1, c1.toString());

        Activity c2 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, "A");
        String s2 = "CSC216,Software Development Fundamentals,001,3,sesmith5,100,A";
        assertEquals(s2, c2.toString());
    }

    /**
     * Tests compareTo() method
     */
    
    @Test
    public void testCompareTo() {
    	Course a = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "TH", 1330, 1445);
    	Course b = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "TH", 1330, 1445);
    	Course diff216Section = new Course("CSC216", "Software Development Fundamentals", "002", 3, "ixdoming", 100, "MW", 1330, 1445);
    	Course d = new Course("CSC116", "Intro to Programming - Java", "001", 3, "jdyoung2", 125, "MW", 910, 1100);
    	Course e = new Course("CSC116", "Intro to Programming - Java", "003", 3, "tbdimitr", 125, "TH", 1120, 1310);
    	Course f = new Course("CSC226", "Discrete Mathematics for Computer Scientists", "001", 2, "tmbarnes", 25, "MWF", 935, 1025);
    	Course g = new Course("CSC217", "Software Development Fundamentals Lab", "601", 1, "sesmith5", 50, "A");
    	Course x = null;
    	
    	/*
    	 * Actual order:
    	d: "CSC 116", "Intro to Programming - Java", "001", 3, "jdyoung2", "MW", 910, 1100
    	e: "CSC 116", "Intro to Programming - Java", "003", 3, "tbdimitr", "TH", 1120, 1310
    	a and b: "CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330, 1445
    	diff216Section: "CSC 216", "Software Development Fundamentals", "002", 3, "ixdoming", "MW", 1330, 1445
    	g: "CSC 217", "Software Development Fundamentals Lab", "601", 1, "sesmith5", "A"
    	f: "CSC 226", "Discrete Mathematics for Computer Scientists", "001", 3, "tmbarnes", "MWF", 935, 1025
    	*/
    	
    	assertSame(a.compareTo(b), 0);
    	assertTrue(b.compareTo(diff216Section) < 0);
    	assertTrue(diff216Section.compareTo(f) < 0);
    	assertSame(f.compareTo(f), 0);
    	assertFalse(g.compareTo(a) < 0);
    	assertTrue(g.compareTo(diff216Section) > 0);
    	assertFalse(d.compareTo(e) > 0);
    	assertTrue(e.compareTo(a) < 0);
    	assertTrue(d.compareTo(f) < 0);
    	
    	try {
    		e.compareTo(x);
    	} catch (NullPointerException exception) {
    		return;
    	}
    }
}