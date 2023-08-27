package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for the faculty class
 * @author colinscanlon
 *
 */
public class FacultyTest {

	/**
	 * Testing hashCode method in Student.java without null parameters
	 * If 2 objects are equal to each other, they have the same hash code.
	 */
	@Test
	public void testHashCode() {
		
		User a = new Faculty("Manasa", "Chinta", "mschinta", "mschinta@ncsu.edu", "hashedpassword", 2); 
		User b = new Faculty("Manasa", "Chinta", "mschinta", "mschinta@ncsu.edu", "hashedpassword", 2);
		User c = new Faculty("John", "doe", "jdoe", "jdoe@ncsu.edu", "hashedpassword", 1);
		
		// Test for the same hash code for the same values
        assertEquals(a.hashCode(), b.hashCode());

        // Test for each of the fields
        assertNotEquals(a.hashCode(), c.hashCode());
        assertNotEquals(b.hashCode(), c.hashCode());
		
	}
	
	/**
	 * testing that the Student constructor with parameters: first name, last name, 
	 * email, id, password, and max credits are catching throwIllegalExceptions 
	 * for invalid arguments passed to the get and set methods
	 */ 
	@Test
	public void testStudentStringStringStringStringStringInt() {
		//test for null first name -- should throw an exception
		Faculty a = null;
		try {
            a = new Faculty(null, "last", "id", "email@ncsu.edu", "hashedpassword", 1);
        } catch (IllegalArgumentException e) {
            assertNull(a);
            assertEquals("Invalid first name", e.getMessage());
        }
		
		//test for empty string first name -- should throw an exception
		a = null;
		try {
            a = new Faculty("", "last", "id", "email@ncsu.edu", "hashedpassword", 1);
        } catch (IllegalArgumentException e) {
            assertNull(a);
            assertEquals("Invalid first name", e.getMessage());
        }
		
		//tests null last name
		a = null;
		try {
			a = new Faculty("Manasa", null, "id", "email@ncsu.edu", "hashedpassword", 3);		
		} catch (IllegalArgumentException e) {
			assertNull(a);
		}
				
		//test for empty last name -- should throw an exception
		a = null;
		try {
			a = new Faculty("Manasa", "", "id", "email@ncsu.edu", "hashedpassword", 2);
        } catch (IllegalArgumentException e) {
        	assertNull(a);
            assertEquals("Invalid last name", e.getMessage());	        
        }
		
		//test for null Id-- should throw an exception
		a = null;
		try {
			a = new Faculty("Manasa", "Chinta", null, "email@ncsu.edu", "hashedpassword", 1);
		} catch (IllegalArgumentException e) {
			assertNull(a);
			assertEquals("Invalid id", e.getMessage());	        
		}
		
		
		//test for null Id-- should throw an exception
		a = null;
		try {
			a = new Faculty("Manasa", "Chinta", "", "email@ncsu.edu", "hashedpassword", 1);
		} catch (IllegalArgumentException e) {
			assertNull(a);
		   	assertEquals("Invalid id", e.getMessage());	        
		}
		   	
		//test null email
		a = null;
		try {	 
			a = new Faculty("Manasa", "Chinta", "mschinta", null, "hashedpassword", 3);
		} catch (IllegalArgumentException e) {
			assertNull(a);
			assertEquals("Invalid email", e.getMessage());
		 }
				
		//test empty email, length = 0
		a = null;
		try {
			a = new Faculty("Manasa", "Chinta", "mschinta", "", "hashedpassword", 1);
		} catch (IllegalArgumentException e) {
	        assertEquals("Invalid email", e.getMessage());
		}
		 
		//test more than one @ in email
		a = null;
		try {
			a = new Faculty("Manasa", "Chinta", "mschinta", "m@chint@email.com", "hashedpassword", 1);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid email", e.getMessage());
		}
	
		//test dot before @ in email
		a = null;
		try {
			a = new Faculty("Manasa", "Chinta", "mschinta", "ms.chinta@emailcom", "hashedpassword", 1);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid email", e.getMessage());
		}
		
		//test no dot in email
		a = null;
		try {
			a = new Faculty("Manasa", "Chinta", "mschinta", "mschinta@emailcom", "hashedpassword", 1);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid email", e.getMessage());
		}
		
		//test null password
		a = null;
		try {	 
			a = new Faculty("Manasa", "Chinta", "mschinta", "mschinta@email.com", null, 1);
		} catch (IllegalArgumentException e) {
			assertNull(a);
		   	assertEquals("Invalid password", e.getMessage());	 
		}
		
		//test empty password, length = 0
		a = null;
		try {
			a = new Faculty("Manasa", "Chinta", "mschinta", "mschinta@email.com", "", 1);
		} catch (IllegalArgumentException e) {
	        assertEquals("Invalid password", e.getMessage());
		}
		
		//test number of credits less than 3
		a = null;
		try {
			a = new Faculty("Manasa", "Chinta", "mschinta", "mschinta@email.com", "hashedpassword", 1);
		} catch (IllegalArgumentException e) {
	        assertEquals("Invalid max credits", e.getMessage());
		}
	
		//test number of credits greater than 18
		a = null;
		try {
			a = new Faculty("Manasa", "Chinta", "mschinta", "mschinta@email.com", "hashedpassword", 2);
		} catch (IllegalArgumentException e) {
	        assertEquals("Invalid max credits", e.getMessage());
		}

		
		//test a completely valid Student construction
		a = null;
        a = new Faculty("Manasa", "Chinta", "mschinta", "mschinta@ncsu.edu", "hashedpassword", 1);
        assertEquals("Manasa", a.getFirstName());
        assertEquals("Chinta", a.getLastName());
        assertEquals("mschinta", a.getId());
        assertEquals("mschinta@ncsu.edu", a.getEmail());
        assertEquals("hashedpassword", a.getPassword());
        assertEquals(1, a.getMaxCourses());

	}
	
	/**
	 * tests the toString to determine if objects are printing
	 * in the correct format
	 */
	@Test
	public void testToString() {
		User a = new Faculty("Manasa", "Chinta", "mschinta", "mschinta@ncsu.edu", "hashedpassword", 1);
		String expecA = "Manasa,Chinta,mschinta,mschinta@ncsu.edu,hashedpassword,1";
		assertEquals(expecA, a.toString());
	}
	
	/**
	 * test that objects which are the same or have certain parameters
	 * which are equal are recognized as such in
	 * equals()
	 */
	@Test
	public void testEqualsObject() {
		User a = new Faculty("Manasa", "Chinta", "mschinta", "mschinta@ncsu.edu", "hashedpassword", 3);
		User b = new Faculty("Manasa", "Chinta", "mschinta", "mschinta@ncsu.edu", "hashedpassword", 3);
		User c = new Faculty("John", "doe", "jdoe", "jdoe@ncsu.edu", "hashedpassword", 1);
		User e = new Faculty("Jackie", "Chinta", "mschinta", "mschinta@ncsu.edu", "hashedpassword", 3);
		User f = new Faculty("Jackie", "Chinta", "jchinta", "mschinta@ncsu.edu", "hashedpassword", 3);
		User g = new Faculty("Jackie", "ludo", "jchinta", "mschinta@ncsu.edu", "hashedpassword", 3);
	
		User j = null;
		
		Object d = new Object();
		assertFalse(a.equals(j));

		//Test that classes are same
		assertFalse(a.equals(d));

        // Test for equality in both directions
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
        assertTrue(a.equals(a));
       
        //Test for inequality
        assertFalse(a.equals(c));
        assertFalse(c.equals(a));
        
        //Test for first names
        assertFalse(a.equals(e));
        assertFalse(e.equals(a));
        
        //test for different id's
        assertFalse(e.equals(f));
        assertFalse(f.equals(e));   
        
        //test for different last names
        assertFalse(f.equals(g));
        assertFalse(g.equals(f));         
        
	}
	/**
	 * Tests for setting the max number of courses in a faculty object
	 */
	@Test
	public void testSetMaxCourses() {
		Faculty a = new Faculty("Manasa", "Chinta", "mschinta", "mschinta@ncsu.edu", "hashedpassword", 2);
		try {
			a.setMaxCourses(6);
		} catch(IllegalArgumentException e) {
			// Success
		}
		
		try {
			a.setMaxCourses(-1);
		} catch(IllegalArgumentException e) {
			// Success
		}
	}
}
