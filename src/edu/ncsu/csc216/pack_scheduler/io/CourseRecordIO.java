/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

//import edu.ncsu.csc216.pack_scheduler.course.Activity;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Reads Course records from given text file and writes Course records to output file
 * @author Mitthu Tiwari
 * @version 1
 */

public class CourseRecordIO {

	/**
     * Reads Course records from file and generates list of valid courses
     * @param fileName file to read Course records from
     * @return list of valid Courses
     * @throws FileNotFoundException if file cannot be found or read
     */
	
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Course> courses = new SortedList<Course>(); 
	    Course course = null;
	    while (fileReader.hasNextLine()) { 
	        try { 
	            course = readCourse(fileReader.nextLine()); 
	            boolean duplicate = false;
	            for (int i = 0; i < courses.size(); i++) {
	                Course current = courses.get(i);
	                if (course.getName().equals(current.getName()) 
	                		&& course.getSection().equals(current.getSection())) {
	                    duplicate = true;
						break; 
	                }
	            }
	            
	            if (!duplicate) {
	                courses.add(course);
	            }
	        } catch (IllegalArgumentException e) {
	            course = null;
	        }
	    }
	    
	    fileReader.close();
	    return courses;
	}

	/** Processes each individual line of input file as a Course object
	 * @param line line of input file representing Course object
	 * @return Course object processed from line of input file
	 */
	
	private static Course readCourse(String line) {
		RegistrationManager rm = RegistrationManager.getInstance();
		Scanner lineScan = new Scanner(line);
		lineScan.useDelimiter(",");
		String name = "";
		String title = "";
		String section = "";
		int credits = 0;
		String instructorId = null;
		int enrollmentCap = 0;
		String meetingDays = "";
		int startTime = 0;
		int endTime = 0;
		Course c = null;

		try {
			while(lineScan.hasNext()) {
				name = lineScan.next();
				title = lineScan.next();
				section = lineScan.next();
				if (lineScan.hasNextInt()) {
					credits = lineScan.nextInt();
				} 
				if (lineScan.hasNext()) {
					instructorId = lineScan.next();
				}
				if (lineScan.hasNextInt()) {
					enrollmentCap = lineScan.nextInt();
				}
				meetingDays = lineScan.next();
				
				if (!"A".equals(meetingDays)) {
					startTime = lineScan.nextInt();
					endTime = lineScan.nextInt();
					c = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
					Faculty f = rm.getFacultyDirectory().getFacultyById(instructorId);
					if (f != null) {
						f.getSchedule().addCourseToSchedule(c);
					}
				} else {
					if (lineScan.hasNext()) {
						lineScan.close();
						throw new IllegalArgumentException();
					}
					lineScan.close();
					c = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays);
					Faculty f = rm.getFacultyDirectory().getFacultyById(instructorId);
					if (f != null) {
						f.getSchedule().addCourseToSchedule(c);
					}
				}
				
				return c;
			}
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
		
		lineScan.close(); 
		return null;
	}
	
	/**
	 * Writes given list of Courses to output file
	 * @param fileName file to write schedule of Courses to
	 * @param courses list of Courses to write to output file
	 * @throws IOException if unable to write to file
	 */
	
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
		Course c = null;
		PrintStream fileWriter = new PrintStream(new File(fileName));
		for (int i = 0; i < courses.size(); i++) {
			c = courses.get(i);
    		fileWriter.println(c.toString());
    	}
	
		fileWriter.close();	
	}
}
