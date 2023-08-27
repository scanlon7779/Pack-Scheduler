package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ListIterator;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Tests for the FacultyRecordIO class
 * @author colinscanlon
 *
 */
public class FacultyRecordIOTest {
	
	/**
	 * Tests the readFacultyRecords method
	 */
	@Test
	public void testReadFacultyRecords() {
		try {
			LinkedList<Faculty> faculty = FacultyRecordIO.readFacultyRecords("test-files/faculty_records.txt");
			ListIterator<Faculty> li = faculty.listIterator(0);
			Faculty f1 = li.next();
			assertEquals("Ashely", f1.getFirstName());
			assertEquals("Witt", f1.getLastName());
			assertEquals(2, f1.getMaxCourses());
			
		} catch (FileNotFoundException e) {
			fail();
		}
	}
	
	/**
	 * Test writing faculty records to a file
	 */
	@Test
	public void testWriteFacultyRecords() {
		try {
			LinkedList<Faculty> faculty = FacultyRecordIO.readFacultyRecords("test-files/faculty_records.txt");
			try {
				FacultyRecordIO.writeFacultyRecords("test-files/faculty_records_actual.txt", faculty);
				checkFiles("text-files/expected_full_faculty_records.txt", "test-files/faculty_records_actual.txt");
			} catch (IOException e) {
				fail();
			}
		} catch (FileNotFoundException e) {
			fail();
		}
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			 
			int count = 1;
			
			while (expScanner.hasNextLine()) {
				assertEquals("Difference on line " + count, expScanner.nextLine(), actScanner.nextLine());
				count++;
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
