
package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc217.collections.list.SortedList;
//import edu.ncsu.csc216.collections.list.SortedList;

/**
 * Creates and modifies course catalog
 * @author Mitthu Tiwari
 * @version 1
 */

public class CourseCatalog {
	
	/** Sorted list of courses for course catalog */
	private SortedList<Course> catalog;
	
	/** Creates an empty catalog
	 */
	
	public CourseCatalog() {
		this.catalog = new SortedList<Course>();
	}
	
	/** Creates an empty catalog
	 */
	public void newCourseCatalog() {
		this.catalog = new SortedList<Course>();
	}
	
	/** Loads course records into catalog
	 * @param fileName file from which course records are being read
	 * @throws IllegalArgumentException if file cannot be found
	 */
	
	public void loadCoursesFromFile(String fileName) {
		try {
			SortedList<Course> courses = CourseRecordIO.readCourseRecords(fileName);
			for (int i = 0; i < courses.size(); i++) {
				this.catalog.add(courses.get(i));
			}
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}
	
	/** Checks to see if course that is method parameter is in catalog or a duplicate of 
	 * another course already in catalog before adding course to catalog
	 * @param name name of course being added
	 * @param title title of course being added
	 * @param section section of course being added
	 * @param credits number of credits of course being added
	 * @param instructorId instructor ID of course being added
	 * @param enrollmentCap enrollment cap of course being added
	 * @param meetingDays meeting days of course being added
	 * @param startTime start time of course being added
	 * @param endTime end time of course being added
	 * @return true if course exists in catalog, isn't in catalog yet, and gets added and false otherwise
	 * @throws IllegalArgumentException if course being added is duplicate of course already in catalog
	 */
	
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays, int startTime, int endTime) {
		
		int size = this.catalog.size();
		Course c = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
		
		for (int i = 0; i < size; i++) {
			if (catalog.get(i).isDuplicate(c)) {
				return false;
				// throw new IllegalArgumentException("Catalog already contains " + name);
			
			}
		}
		
		catalog.add(c);
		return true;
		
	}
	
	/** Removes course from catalog
	 * @param name name of course you want to remove
	 * @param section section of course you want to remove
	 * @return true or false depending on whether or not course gets removed
	 */
	
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int i = 0; i < this.catalog.size(); i++) {
			if (name.equals(this.catalog.get(i).getName()) && section.equals(this.catalog.get(i).getSection())) {
				this.catalog.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	/** Returns course with name and section that are specified as method parameters from course catalog
	 * @param name course name
	 * @param section course section
	 * @return course with name and section that are specified as method parameters
	 */
	
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < this.catalog.size(); i++) {
			if (name.equals(this.catalog.get(i).getName()) && section.equals(this.catalog.get(i).getSection())) {
				return catalog.get(i);
			}
		}
		
		return null;
	}
	
	/** Returns 2D string array of catalog or empty array if there are no courses in catalog
	 * Each row in array is a course displaying its name, section, course title, and meeting string
	 * @return 2D string array or empty array depending on whether or not there are courses in catalog
	 */
	
	public String[][] getCourseCatalog() {
		String name = "";
		String section = "";
		String courseTitle = "";
		String meetingString = "";
		String[][] courseCatalog = new String[this.catalog.size()][4];
		
		for (int i = 0; i < this.catalog.size(); i++) {
			name = this.catalog.get(i).getName();
			section = this.catalog.get(i).getSection();
			courseTitle = this.catalog.get(i).getTitle();
			meetingString = this.catalog.get(i).getMeetingString();
			courseCatalog[i][0] = name;
			courseCatalog[i][1] = section;
			courseCatalog[i][2] = courseTitle;
			courseCatalog[i][3] = meetingString;
		}
		
		if (this.catalog.size() == 0) {
			return courseCatalog;
		}
		
		return courseCatalog;
	}
	
	/** Writes catalog to output file specified as method parameter
	 * @param fileName file to which catalog is being written to
	 * @throws IllegalArgumentException if file cannot be saved
	 */
	
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}
}