package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/** Tests StudentRecordIO class using both valid and invalid text files
 * @author Mitthu Tiwari
 * @version 1
 */

public class StudentRecordIOTest {

	/** First student in string array of students used in test cases*/
	private String validStudent0 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	
	/** Second student in string array of students used in test cases*/
	private String validStudent1 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	
	/** Third student in string array of students used in test cases*/
	private String validStudent2 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	
	/** Fourth student in string array of students used in test cases*/
	private String validStudent3 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	
	/** Fifth student in string array of students used in test cases*/
	private String validStudent4 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	
	/** Sixth student in string array of students used in test cases*/
	private String validStudent5 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	
	/** Seventh student in string array of students used in test cases*/
	private String validStudent6 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	
	/** Eighth student in string array of students used in test cases*/
	private String validStudent7 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	
	/** Ninth student in string array of students used in test cases*/
	private String validStudent8 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	
	/** Tenth student in string array of students used in test cases*/
	private String validStudent9 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";
	
	/** String array of students arranged in alphabetical order by last name used in test cases */
	private String [] validStudents = {validStudent3, validStudent6, validStudent4, validStudent5, validStudent2, validStudent8,
			                      validStudent0, validStudent9, validStudent1, validStudent7};

	/** Hash-coded password */
	private String hashPW;
	
	/** Hash algorithm for hashing password */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/** Set up method which hashes passwords of students in students string array */
	
	@Before
	public void setUp() {
		try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = new String(digest.digest());
	        
	        for (int i = 0; i < validStudents.length; i++) {
	            validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}

	/** Checks to see if actual output file of student records matches expected 
	 * output file of student records
	 * @param expFile expected output file of student records
	 * @param actFile actual output file of student records
	 */ 
	
	private void checkFiles(String expFile, String actFile) {
	    try {
	        Scanner expScanner = new Scanner(new FileInputStream(expFile));
	        Scanner actScanner = new Scanner(new FileInputStream(actFile));

	        while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
	        	//Got idea to use trim method from Java String API
	            String exp = expScanner.nextLine().trim();
	            String act = actScanner.nextLine().trim();
	            assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
	        }
	        if (expScanner.hasNextLine()) {
	            fail("The expected results expect another line " + expScanner.nextLine());
	        }
	        if (actScanner.hasNextLine()) {
	            fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
	        }
	        
	        expScanner.close();
	        actScanner.close();
	    } catch (IOException e) {
	        fail("Error reading files.");
	    }
	}
	
	/** Tests readStudentRecords() method
	 */
	
	@Test
	public void testReadStudentRecords() {
		try {
			SortedList<Student> students = StudentRecordIO.readStudentRecords("test-files/student_records.txt");
			assertEquals(10, students.size());
			for (int i = 0; i < validStudents.length; i++) {
				assertEquals(validStudents[i], students.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
		}
	}
	
	/** Tests readStudentRecords() method with invalid input filename
	 */
	
	@Test
	public void testReadInvalidStudentRecords() {
		try {
			SortedList<Student> students = StudentRecordIO.readStudentRecords("test-files/invalid_student_records.txt");
			assertEquals(0, students.size());
		} catch (FileNotFoundException e) {
			fail(e.getMessage());
		}
	}
	
	/** Tests readStudentRecords() method with nonexistent input file
	 */
	
	@Test
	public void testReadStudentRecordsFromNonexistentFile() {
		try {
			SortedList<Student> students = StudentRecordIO.readStudentRecords("test-files\nonexistent_student_records.txt");
			assertEquals(0, students.size());
		} catch (FileNotFoundException e) {
			assertEquals("test-files\nonexistent_student_records.txt (No such file or directory)", e.getMessage());
		}
	}

	/** Tests writeStudentRecords() method by trying to write to directory user doesn't have permission to write to
	 */
	
	@Test
	public void testWriteStudentRecordsNoPermissions() {
	    SortedList<Student> students = new SortedList<Student>();
	    students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
	    //Assumption that you are using a hash of "pw" stored in hashPW
	    
	    try {
	        StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students);
	        fail("Attempted to write to a directory location that doesn't exist or without the appropriate permissions and the write happened.");
	    } catch (IOException e) {
	        assertEquals("/home/sesmith5/actual_student_records.txt (Permission denied)", e.getMessage());

	    }
	    
	}
	
	/** Tests writeStudentRecords() method by adding to sorted list of students arranged in alphabetical order by last name
	 */
	
	@Test
	public void testWriteStudentRecords() { 
		SortedList<Student> students = new SortedList<Student>();
		students.add(new Student("Demetrius", "Austin", "daustin", "Curabitur.egestas.nunc@placeratorcilacus.co.uk", hashPW, 18));
		students.add(new Student("Lane", "Berg", "lberg", "sociis@non.org", hashPW, 14));
		students.add(new Student("Raymond", "Brennan", "rbrennan", "litora.torquent@pellentesquemassalobortis.ca", hashPW, 12));
		students.add(new Student("Emerald", "Frost", "efrost", "adipiscing@acipsumPhasellus.edu", hashPW, 3));
		students.add(new Student("Shannon", "Hansen", "shansen", "convallis.est.vitae@arcu.ca", hashPW, 14));
		students.add(new Student("Althea", "Hicks", "ahicks", "Phasellus.dapibus@luctusfelis.com", hashPW, 11));
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
		students.add(new Student("Dylan", "Nolan", "dnolan", "placerat.Cras.dictum@dictum.net", hashPW, 5));
		students.add(new Student("Cassandra", "Schwartz", "cschwartz", "semper@imperdietornare.co.uk", hashPW, 4));
		students.add(new Student("Griffith", "Stone", "gstone", "porta@magnamalesuadavel.net", hashPW, 17));
		
		try {
			StudentRecordIO.writeStudentRecords("test-files/actual_student_records.txt", students);
		} catch (IOException e) {
			fail(e.getMessage());
		}
		
		checkFiles("test-files/expected_full_student_records.txt", "test-files/actual_student_records.txt");
	}
	
	/** Tests writeStudentRecords() method by trying to add invalid student record to sorted list of students
	 */
	
	@Test
	public void testWriteInvalidStudentRecord() {
	    SortedList<Student> students = new SortedList<Student>();
	    students.add(new Student("Tony", "Stark", "tstark", "tstark@avengers.org", "password", 6));
	    
	    try {
	        StudentRecordIO.writeStudentRecords("test-files/actual_student_records.txt", students);
	    } catch (IOException e) {
	        assertEquals("Invalid record", e.getMessage());
	    }
	}
}