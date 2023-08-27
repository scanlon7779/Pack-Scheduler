package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Checks the throwing of the checked exception ConflictException in
 * the Activity class for activity objects. 
 * @author Manasa Chinta 
 */
public class ActivityTest {
	
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

	/** tests the implemented checkConflict() method in the Activity class to see if the 
	 * ConflictException is not thrown for two activities with the same meeting time but do not coincide
	 * because they take place on non-overlapping days
	 */
	
	@Test
	public void checkActivityConflict() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "TH", 1330, 1445);

		try {
			a1.checkConflict(a2);
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
		} catch (ConflictException e) {
			fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	} 
	
	/**
	 * Checks to see if ConflictExcepion is thrown with comparison of 
	 * 2 activities with overlapping days and times
	 */
	
	@Test
	public void testCheckConflictWithConflict() {
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "M", 1330, 1445);
	    try {
	        a1.checkConflict(a2);
	        fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
	    } catch (ConflictException e) {
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "M 1:30PM-2:45PM", a2.getMeetingString());
	    }
	}
	
	/**
	 * Checks to see if ConflictExcepion is thrown when this and the parameter are switched
	 * checks to see if the method is commutative
	 */
	
	@Test
	public void testCheckConflictSwitch() {
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "M", 1330, 1445);
	    try {
	        a2.checkConflict(a1);
	        fail("A ConflictException was NOT thrown when two Activities had a day/time conflict with first activity passed as a parameter.");
	    } catch (ConflictException e) {
	        assertEquals("Incorrect meeting string for this Activity.", "M 1:30PM-2:45PM", a2.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	    }
	}
	
	/**
	 * Checks to see if ConflictExcepion is thrown with one common meeting day
	 */
	
	@Test
	public void testCheckConflictOneDay() {
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "W", 1400, 1430);
	    try {
	        a1.checkConflict(a2);
	        fail("A ConflictException was NOT thrown when two Activities had a day/time conflict with first activity passed as a parameter.");
	    } catch (ConflictException e) {
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "W 2:00PM-2:30PM", a2.getMeetingString());
	    }
	} 
	
	
	/**
	 * Checks to see if ConflictExcepion is thrown when 2 activities take place on the same day with 
	 * overlapping times
	 */
	
	@Test
	public void testCheckConflictSameDay() {
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "H", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "H", 1345, 1500);
	    try {
	        a2.checkConflict(a1);
	        fail("A ConflictException was NOT thrown when two Activities had a day/time conflict with first activity passed as a parameter.");
	    } catch (ConflictException e) {
	        assertEquals("Incorrect meeting string for this Activity.", "H 1:30PM-2:45PM", a1.getMeetingString());	        
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "H 1:45PM-3:00PM", a2.getMeetingString());
	    }
	}

	/**
	 * Checks to see if ConflictExcepion is thrown when 2 activities take place on the same day when
	 * the end time for this activity is the start time for possibleConflictingActivity. Testing boundary conditions
	 */

	@Test
	public void testCheckConflictBoundary() {
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "H", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "H", 1445, 1545);
	    try {
	        a2.checkConflict(a1);
	        fail("A ConflictException was NOT thrown when two Activities had a day/time conflict with first activity passed as a parameter.");
	    } catch (ConflictException e) {
	        assertEquals("Incorrect meeting string for this Activity.", "H 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "H 2:45PM-3:45PM", a2.getMeetingString());
	    }
	} 
	
	/**
	 * Checks valid courses with non-conflicting times
	 */
	@Test
	public void testCheckConflictCorrect() {
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MT", 1700, 1800);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MT", 1500, 1600);
	    try {
	        a1.checkConflict(a2);
	        assertEquals("Incorrect meeting string for this Activity.", "MT 5:00PM-6:00PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "MT 3:00PM-4:00PM", a2.getMeetingString());
	    } catch (ConflictException e) {
	    	fail("A ConflictException was thrown when two Activities at different times on completely distinct days were compared.");
	    }
	}
	//test events
	/**
	 * Checks to see if ConflictExcepion is thrown when end time of one activity is less than the 
	 * other activity's start time
	 */
	@Test
	public void testCheckEndTimeLessThanOtherStart() {
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MT", 1300, 1400);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MT", 1500, 1600);
	    try {
	        a1.checkConflict(a2);
	        assertEquals("Incorrect meeting string for this Activity.", "MT 1:00PM-2:00PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "MT 3:00PM-4:00PM", a2.getMeetingString());
	    } catch (ConflictException e) {
	    	fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	}
	
	/**
	 * Checks to see if ConflictExcepion is not thrown with 1 arranged activity
	 */
	@Test
	public void testCheckOneArrangedValid() {
	    Activity a1 = new Course("CSC226", "Discrete Math", "001", 3, "sesmith5", 200, "A", 0, 0);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MT", 1500, 1600);
	    try {
	        a1.checkConflict(a2);
	        assertEquals("Incorrect meeting string for this Activity.", "Arranged", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "MT 3:00PM-4:00PM", a2.getMeetingString());
	    } catch (ConflictException e) {
	    	fail("A ConflictException was thrown when one activity is arranged");
	    }
	}
	
	/**
	 * Checks to see if ConflictExcepion is not thrown with 1 arranged activity-- (other)
	 */
	@Test
	public void testCheckOtherArrangedValid() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MT", 1500, 1600);
		Activity a2 = new Course("CSC226", "Discrete Math", "001", 3, "sesmith5", 200, "A", 0, 0);
	    try {
	        a2.checkConflict(a1);
	        assertEquals("Incorrect meeting string for this Activity.", "Arranged", a2.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "MT 3:00PM-4:00PM", a1.getMeetingString());
	    } catch (ConflictException e) {
	    	fail("A ConflictException was thrown when other activity is arranged");
	    }
	}
	
	/**
	 * Checks to see if ConflictExcepion is not thrown with 2 arranged activities
	 */
	@Test
	public void testCheck2ArrangedActivities() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "A", 0, 0);
		Activity a2 = new Course("CSC226", "Discrete Math", "001", 3, "sesmith5", 200, "A", 0, 0);
	    try {
	        a1.checkConflict(a2);
	        assertEquals("Incorrect meeting string for this Activity.", "Arranged", a2.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "Arranged", a1.getMeetingString());
	    } catch (ConflictException e) {
	    	fail("A ConflictException was thrown when two activity is arranged");
	    }
	} 
	
	/**
	 * Checks to see if IllegalArgumentException is thrown when
	 * attempting to set title to null
	 */
	@Test
	public void testNullTitle() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "A", 0, 0);
		
		 try {
	            a1.setTitle(null);
	            fail();
	     } catch (IllegalArgumentException e) {
	           assertEquals("Invalid title.", e.getMessage());
	     }
	}
	
	/**
	 *Checks to see if IllegalArgumentException is thrown when
	 * attempting to set title to empty
	 */
	@Test
	public void testEmptyTitle() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "A", 0, 0);
		
		 try {
	            a1.setTitle("");
	            fail();
	     } catch (IllegalArgumentException e) {
	           assertEquals("Invalid title.", e.getMessage());
	     }
	}
	
    
	/**
     * testing to see if IllegalArgumentException is thrown for invalid start times
     * start time less than 0 and start time greater than 2359
     */
	@Test
	public void testInvalidStartTimes() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MT", 1500, 1600);
	    try {
	        a1.setMeetingDaysAndTime("MT", -10, 1600);
	        fail();
	    } catch (IllegalArgumentException a) {
	        assertEquals("Invalid start time.", a.getMessage());
	    }
	    
	    try {
	        a1.setMeetingDaysAndTime("MT", 2360, 1600);
	        fail();
	    } catch (IllegalArgumentException b) {
	        assertEquals("Invalid start time.", b.getMessage());
	    }
	    
	    try {
	        a1.setMeetingDaysAndTime("MT", 1500, -37);
	        fail();
	    } catch (IllegalArgumentException a) {
	        assertEquals("Invalid end time.", a.getMessage());
	    }
	    
	    try {
	        a1.setMeetingDaysAndTime("MT", 1600, 2900);
	        fail();
	    } catch (IllegalArgumentException b) {
	        assertEquals("Invalid end time.", b.getMessage());
	    }
	    
	    try {
	        a1.setMeetingDaysAndTime("MT", 1700, 1600);
	        fail();
	    } catch (IllegalArgumentException b) {
	        assertEquals("End time cannot be before start time.", b.getMessage());
	    }
	}
	
	/**
     * Testing getMeetingString method
     */
	@Test
	public void testMeetingString() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MT", 1200, 1600);
	    
	        a1.getMeetingString();
	        assertEquals("MT 12:00PM-4:00PM", a1.getMeetingString());
	        
	        a1.setMeetingDaysAndTime("MT", 1230, 1400);
	        a1.getMeetingString();
	        assertEquals("MT 12:30PM-2:00PM", a1.getMeetingString());
	        
	        a1.setMeetingDaysAndTime("MT", 1100, 1200);
	        a1.getMeetingString();
	        assertEquals("MT 11:00AM-12:00PM", a1.getMeetingString());
	        
	        a1.setMeetingDaysAndTime("MT", 1100, 1245);
	        a1.getMeetingString();
	        assertEquals("MT 11:00AM-12:45PM", a1.getMeetingString());
	        
	     /*   try {
	        	a1.setMeetingDaysAndTime("MM", 1100, 1245);
	        	
	 	        a1.getMeetingString();
	 	        assertEquals("MT 11:00AM-12:45PM", a1.getMeetingString());
	        } catch (IllegalArgumentException i ) {
	        	
	        } */
	        
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
        Activity c9 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, 1400);
        Activity c10 = null;
        Object o = new Object(); 
        
        // Test for equality in both directions
        assertTrue(c1.equals(c2));
        assertTrue(c2.equals(c1));
        
        assertTrue(c1.equals(c1));
        assertFalse(c1.equals(c10));
        assertFalse(c1.equals(o));

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
        Activity c9 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, 1400);

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
     * Testing getTitle method
     */
	@Test
	public void testGetTitle() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MT", 1200, 1600);

	        assertEquals("Software Development Fundamentals", a1.getTitle());
	}
	
	 /**
     * Testing setMeetingDaysandTimes method
     */
	@Test
	public void testSetMeetingDaysandTimes() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MT", 1200, 1600);
		
		//testing start hour < 0
		
		try {
            a1.setMeetingDaysAndTime("MW", -10, 1100);
            fail();
		} catch (IllegalArgumentException e) {
           assertEquals("Invalid start time.", e.getMessage());
		}
		
		//testing start hour > 2359
		try {
            a1.setMeetingDaysAndTime("MW", 2500, 1100);
            fail();
		} catch (IllegalArgumentException e) {
           assertEquals("Invalid start time.", e.getMessage());
		}
		
		//valid start hour and end hour

            a1.setMeetingDaysAndTime("MW", 1100, 1300);
            assertEquals(a1.getStartTime(), 1100);
	}
	
	/** Tests two non-conflicting activities 
	 */
	
	@Test
	public void testCheckConflict() {
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "TH", 1330, 1445);
	    try {
	        a1.checkConflict(a2);
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	}
	
	/** Tests two conflicting activities using two Course objects
	 */ 
	
	@Test
	public void testCheckConflictWithConflict2() {
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "M", 1330, 1445);
	    try {
	        a1.checkConflict(a2);
	        fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
	    } catch (ConflictException e) {
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "M 1:30PM-2:45PM", a2.getMeetingString());
	    }
	}
	
	/** Tests two Course objects that are the same activity and therefore don't conflict
	 */
	
	@Test
	public void testCheckConflictSameActivityNoConflict() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330, 1445);
	    try {
	        a1.checkConflict(a2);
	        fail("A ConflictException was thrown when two Activities at the same time on the same days were compared.");
	    } catch (ConflictException e) {
	        assertEquals("Expected meeting string for this Activity.", "MW 1:30PM-2:45PM", a2.getMeetingString());
	        assertEquals("Expected meeting string for possibleConflictingActivity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	    }
	}
	
	/** Tests two arranged Courses for no conflicts
	 */
	
	@Test
	public void testCheckConflictArrangedCourses() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "601", 3, "jctetter", 100, "A");
	    Activity a2 = new Course("CSC217", "Software Development Fundamentals Lab", "601", 1, "sesmith5", 100, "A");
	    try {
	        a2.checkConflict(a1);
	    } catch (ConflictException e) {
	    	fail("A ConflictException was thrown when two Activities had a day/time conflict.");
	        assertEquals("Unexpected ConflictException thrown for arranged CSC 217", "Arranged", a2.getMeetingString());
	        assertEquals("Unexpected ConflictException thrown for arranged CSC 216", "Arranged", a1.getMeetingString());
	    }
	}
}

