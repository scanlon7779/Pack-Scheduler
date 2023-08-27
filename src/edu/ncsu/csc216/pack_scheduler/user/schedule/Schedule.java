/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Creates and manages student's schedule of courses
 * @author Mitthu Tiwari
 * @version 1
 */

public class Schedule {
	
	/** Array list of courses */
	private ArrayList<Course> schedule;
	
	/** Schedule title */
	private String title;
	
	/** Constructs schedule object
	 */
	
	public Schedule() {
		schedule = new ArrayList<Course>();
		title = "My Schedule";
	}
	
	/** Adds course to schedule
	 * @param c course being added to schedule
	 * @return true or false based on whether or not course gets added to schedule
	 * @throws IllegalArgumentException if course being added is duplicate of or conflicts with another course already in schedule
	 */
	
	public boolean addCourseToSchedule(Course c) {
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(c)) {
				throw new IllegalArgumentException("You are already enrolled in " + c.getName());
			}
			
			try {
				schedule.get(i).checkConflict(c);
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}
		
		schedule.add(c);
		return true;
	}
	
	/** Removes course from schedule
	 * @param c course being removed from schedule
	 * @return true or false based on whether or not course gets removed from schedule
	 */
	
	public boolean removeCourseFromSchedule(Course c) {
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).equals(c)) {
				schedule.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	/** Resets schedule by creating new array list of courses and setting schedule title equal to default title
	 */
	
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
		title = "My Schedule";
	}
	
	/** Returns 2D string array representation of courses in schedule with each row containing a course's name, section, title, 
	 * and meeting string
	 * @return 2D string array representation of courses in schedule
	 */
	
	public String[][] getScheduledCourses() {
		String[][] courses = new String[schedule.size()][5];
		for (int i = 0; i < schedule.size(); i++) {
			courses[i][0] = schedule.get(i).getName();
			courses[i][1] = schedule.get(i).getSection();
			courses[i][2] = schedule.get(i).getTitle();
			courses[i][3] = schedule.get(i).getMeetingString();
			courses[i][4] = "" + schedule.get(i).getCredits();
		}
		
		return courses;
	}
	
	/** Returns total number of credits in schedule
	 * @return total number of credits in schedule
	 */
	
	public int getScheduleCredits() {
		int totalCredits = 0;
		
		for (int i = 0; i < schedule.size(); i++) {
			totalCredits += schedule.get(i).getCredits();
		}
		
		return totalCredits;
	}
	
	/** Checks to see if course can be added to schedule based on whether or not it's null, already in the schedule, or conflicting
	 * with another course
	 * @param c course being checked
	 * @return true or false depending on whether or not course can be added to schedule
	 */
	
	public boolean canAdd(Course c) {
		if (c == null) {
			return false;
		}
		
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(c)) {
				return false;
			}
			
			try {
				schedule.get(i).checkConflict(c);
			} catch (ConflictException e) {
				return false;
			}
		}
		
		return true;
	}
	
	/** Sets schedule title
	 * @param title title being set
	 */
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	/** Returns schedule title
	 * @return schedule title
	 */
	
	public String getTitle() {
		return title;
	}
}
