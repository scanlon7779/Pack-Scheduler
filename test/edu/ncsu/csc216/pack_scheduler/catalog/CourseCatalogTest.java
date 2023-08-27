package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests CourseCatalog class 
 * @author Mitthu Tiwari
 * @version 1
 */

public class CourseCatalogTest {

	/** Valid course records */
	private String validTestFile = "test-files/course_records.txt";
	
	/** Invalid course records */
	private String invalidTestFile = "test-files/invalid_course_records.txt";
	
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
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	
	/**
	 * Set up method which resets course_records.txt for use in other tests
	 * @throws Exception if error occurs in file handling
	 */
	
	@Before
	public void setUp() throws Exception {
		//Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests CourseCatalog().
	 */
	
	@Test
	public void testCourseCatalog() {
		//Test with invalid file.  Should have an empty catalog and schedule. 
		CourseCatalog cc1 = new CourseCatalog();
		cc1.loadCoursesFromFile(invalidTestFile);
		assertEquals(0, cc1.getCourseCatalog().length);
		cc1.saveCourseCatalog("test-files/actual_empty_export.txt");
		checkFiles("test-files/expected_empty_export.txt", "test-files/actual_empty_export.txt");
		
		//Test with valid file containing 14 courses.  Will test other methods in other tests.
		CourseCatalog cc2 = new CourseCatalog();
		cc2.loadCoursesFromFile(validTestFile);
		assertEquals(13, cc2.getCourseCatalog().length);		
	}
	
	/**
	 * Test CourseCatalog.getCourseFromCatalog().
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(validTestFile);
		
		//Attempt to get a course that doesn't exist
		assertNull(cc.getCourseFromCatalog("CSC 492", "001"));
		
		//Attempt to get a course that does exist
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(c, cc.getCourseFromCatalog("CSC216", "001"));
	}
	
	/**
	 * Test CourseCatalog.loadCoursesFromFile
	 */
	@Test
	public void testLoadCoursesFromFile() {
		try {
			CourseCatalog cc = new CourseCatalog();
			cc.loadCoursesFromFile("xyz");
			
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Cannot find file.");
		}
	
	}
	
	/**
	 * Test CourseCatalog.addCourseToCatalog().
	 */

	@Test
	public void testAddCourseToSchedule() {
		CourseCatalog cc = new CourseCatalog();
		
	 	assertTrue(cc.addCourseToCatalog("CSC492", "Senior Design Project", "001", 3, "ixdoming", 50, "TH", 935, 1125));
		assertEquals(1, cc.getCourseCatalog().length);
		
		cc.loadCoursesFromFile(validTestFile);
		System.out.println("course catalog cc length: " + cc.getCourseCatalog().length); 
	
		
		assertFalse(cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME));

		//Attempt to add a course that already exists, even if different section
		
		//test adding valid course
		CourseCatalog valid = new CourseCatalog();
		valid.loadCoursesFromFile(validTestFile);
		//assertTrue(valid.addCourseToCatalog("CSC 236", "Computer Organization and Assembly", "002", 3, "sesmith", "MW", 1300, 1400));
		//assertEquals(15, valid.getCourseCatalog().length);
		
		//add valid different 216 course with 003 

		boolean a = cc.addCourseToCatalog(NAME, TITLE, "003", CREDITS, "ixdoming", ENROLLMENT_CAP, "MW", START_TIME, END_TIME);
		assertTrue(a);
	}
	
	/**
	 * Test WolfScheduler.removeCourse().
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		
		CourseCatalog cat = new CourseCatalog();
		cat.loadCoursesFromFile(validTestFile);
		assertTrue(cat.removeCourseFromCatalog("CSC316", "001"));

		
	}
	
	/**
	 * Test CourseCatalog.getCourseCatalog().
	 */
	
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(validTestFile);
		
		//Get the catalog and make sure contents are correct
		//Name, section, title
		String [][] catalog = cc.getCourseCatalog();
		//Row 0
		assertEquals("CSC116", catalog[0][0]);
		assertEquals("001", catalog[0][1]);
		assertEquals("Intro to Programming - Java", catalog[0][2]);
		//Row 1
		assertEquals("CSC116", catalog[1][0]);
		assertEquals("002", catalog[1][1]);
		assertEquals("Intro to Programming - Java", catalog[1][2]);
		//Row 2
		assertEquals("CSC116", catalog[2][0]);
		assertEquals("003", catalog[2][1]);
		assertEquals("Intro to Programming - Java", catalog[2][2]);
		//Row 3
		assertEquals("CSC216", catalog[3][0]);
		assertEquals("001", catalog[3][1]);
		assertEquals("Software Development Fundamentals", catalog[3][2]);
		//Row 4
		assertEquals("CSC216", catalog[4][0]);
		assertEquals("002", catalog[4][1]);
		assertEquals("Software Development Fundamentals", catalog[4][2]);
		//Row 5
		assertEquals("CSC216", catalog[5][0]);
		assertEquals("601", catalog[5][1]);
		assertEquals("Software Development Fundamentals", catalog[5][2]);
		//Row 6
		assertEquals("CSC217", catalog[6][0]);
		assertEquals("202", catalog[6][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[6][2]);
		//Row 7
		assertEquals("CSC217", catalog[7][0]);
		assertEquals("211", catalog[7][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[7][2]);
		//Row 8
		assertEquals("CSC217", catalog[8][0]);
		assertEquals("223", catalog[8][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[8][2]);
		//Row 9
		assertEquals("CSC217", catalog[9][0]);
		assertEquals("601", catalog[9][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[9][2]);
		//Row 10
		assertEquals("CSC226", catalog[10][0]);
		assertEquals("001", catalog[10][1]);
		assertEquals("Discrete Mathematics for Computer Scientists", catalog[10][2]);
		//Row 11
		assertEquals("CSC230", catalog[11][0]);
		assertEquals("001", catalog[11][1]);
		assertEquals("C and Software Tools", catalog[11][2]);
		//Row 12
		assertEquals("CSC316", catalog[12][0]);
		assertEquals("001", catalog[12][1]);
		assertEquals("Data Structures and Algorithms", catalog[12][2]);
	}
	
	/**
	 * Test WolfScheduler.exportSchedule().
	 */
	@Test
	public void testSaveCourseCatalog() {
		//Test that empty schedule exports correctly
		CourseCatalog cc = new CourseCatalog();
		cc.saveCourseCatalog("test-files/actual_empty_export.txt");
		checkFiles("test-files/expected_empty_export.txt", "test-files/actual_empty_export.txt");
		
		cc.loadCoursesFromFile(validTestFile);
		cc.saveCourseCatalog("test-files/actual_schedule_export.txt");
		checkFiles("test-files/expected_schedule_export.txt", "test-files/actual_schedule_export.txt");
		
		//Get the catalog and make sure contents are correct
		//Name, section, title
		String [][] catalog = cc.getCourseCatalog();
		//Row 0
		assertEquals("CSC116", catalog[0][0]);
		assertEquals("001", catalog[0][1]);
		assertEquals("Intro to Programming - Java", catalog[0][2]);
		
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (actScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			if (expScanner.hasNextLine()) {
				fail();
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
	
}