package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Holds a linked list directory of all faculty added to the system
 * @author colinscanlon
 *
 */
public class FacultyDirectory {

	/** The list of faculty members */
	private LinkedList<Faculty> facultyDirectory;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * Creates an empty faculty directory.
	 */
	public FacultyDirectory() {
		newFacultyDirectory();
	}
	
	/**
	 * Creates an empty linked list of faculty objects. 
	 */
	public void newFacultyDirectory() {
		facultyDirectory = new LinkedList<Faculty>();
	}
	
	/**
	 * Constructs the faculty directory by reading in faculty information
	 * from the given file.  Throws an IllegalArgumentException if the 
	 * file cannot be found.
	 * @param fileName file containing list of faculty members
	 */
	public void loadFacultyFromFile(String fileName) {
		try {
			facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Adds a faculty member to the list returns true if they are added
	 * @param firstName the first name of the faculty member
	 * @param lastName the last name of the faculty member
	 * @param id the id of the faculty member
	 * @param email the email of the faculty member
	 * @param password the password of the faculty member
	 * @param repeatPassword the repeatPassword of the faculty member
	 * @param maxCourses the max number of courses of the faculty member
	 * @return if the faculty member was added or not
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password, String repeatPassword, int maxCourses) {
		String hashPW = "";
		String repeatHashPW = "";
		if (password == null || repeatPassword == null || "".equals(password) || "".equals(repeatPassword)) {
			throw new IllegalArgumentException("Invalid password");
		}
		try { 
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(password.getBytes());
			hashPW = new String(digest1.digest());
			
			MessageDigest digest2 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest2.update(repeatPassword.getBytes());
			repeatHashPW = new String(digest2.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
		
		if (!hashPW.equals(repeatHashPW)) {
			throw new IllegalArgumentException("Passwords do not match");
		}
		
		
		Faculty faculty = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		int size = facultyDirectory.size();
		facultyDirectory.add(facultyDirectory.size(), faculty);
		if (size == facultyDirectory.size() + 1) {
			return true;
		}
		return false; 
	}
	
	/**
	 * Removes the faculty with the given id from the list of faculty with the given id.
	 * Returns true if the faculty is removed and false if the faculty is not in the list.
	 * @param id student's id
	 * @return true if removed
	 */
	public boolean removeFaculty(String id) {
		Iterator<Faculty> iterator = facultyDirectory.listIterator(0);
		for (int i = 0; i < facultyDirectory.size(); i++) {
			if(iterator.next().getId().equals(id)) {
				iterator.remove();
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Returns all faculty in the directory with a column for first name, last name, and id.
	 * @return String containing faculty's first name, last name, and id.
	 */
	public String[][] getFacultyDirectory() {
		String [][] directory = new String[facultyDirectory.size()][3];
		Iterator<Faculty> iterator = facultyDirectory.listIterator(0);
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = iterator.next();
			directory[i][0] = s.getFirstName();
			directory[i][1] = s.getLastName();
			directory[i][2] = s.getId();
		}
		return directory;
	}
	
	/**
	 * Saves all faculty members in the directory to a file.
	 * @param fileName name of file to save faculty to.
	 */
	public void saveFacultyDirectory(String fileName) {
		try {
			FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}
	
	/** 
	 * Returns faculty member that has id that matches method parameter
	 * @param id student id used to find students
	 * @return student with id that matches id passed in as parameter
	 */
	public Faculty getFacultyById(String id) {
		Iterator<Faculty> iterator = facultyDirectory.listIterator(0);
		for (int i = 0; i < facultyDirectory.size(); i++) {
			Faculty hold = iterator.next();
			if(iterator.next().getId().equals(id)) {
				return hold;
			}
		}
		return null;
	}
	
}
