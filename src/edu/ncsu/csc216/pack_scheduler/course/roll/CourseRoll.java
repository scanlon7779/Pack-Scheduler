/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * Creates and manages a course roll
 * @author Mitthu Tiwari
 * @version 1
 */

public class CourseRoll {
	
	/** Linked abstract list for roll containing students */
	private LinkedAbstractList<Student> roll;
	
	/** Enrollment capacity */
	private int enrollmentCap;
	
	/** The list of students in the waitlist **/
	private LinkedQueue<Student> waitlist = new LinkedQueue<Student>(10);
	
	/** Minimum number of students required for roll */
	private static final int MIN_ENROLLMENT = 10;
	
	/** Maximum number of students allowed in roll */
	private static final int MAX_ENROLLMENT = 250;
	
	/** Constructs new course roll
	 * @param capacity course's enrollment capacity
	 * @param course the course
	 */
	
	public CourseRoll(Course course, int capacity) {
		if (course == null) {
			throw new IllegalArgumentException();
		}
		roll = new LinkedAbstractList<Student>(capacity);
		setEnrollmentCap(capacity);
		enrollmentCap = getEnrollmentCap();
	}
	
	/** Sets course's enrollment capacity
	 * @param capacity capacity being set
	 * @throws IllegalArgumentException if capacity being set isn't within capacity limits
	 */
	
	public void setEnrollmentCap(int capacity) {
		if (capacity < MIN_ENROLLMENT || capacity > MAX_ENROLLMENT) {
			throw new IllegalArgumentException("Invalid course record");
		}
		
		if (roll != null && capacity >= roll.size()) {
			enrollmentCap = capacity;
		}
	}
	
	/** Enrolls student in course and adds them to course roll
	 * @param s student being enrolled
	 * @throws IllegalArgumentException if student object is null, there are no open seats left, student is already enrolled,
	 * or error occurs in adding student to course roll
	 */
	
	public void enroll(Student s) {
		if (s == null || !canEnroll(s)) {
			throw new IllegalArgumentException("The course cannot be added");
		}
		
		try {
			roll.add(s);
		}
		
		catch (IllegalArgumentException e) {
			if(waitlist.size() == 10) {
				throw new IllegalArgumentException();
			}
			waitlist.enqueue(s);
		}
	}
	
	/** Drops student from course and removes them from course roll
	 * @param s student being dropped
	 * @throws IllegalArgumentException if student object is null or error occurs in removing student from course roll
	 */
	
	public void drop(Student s) {
		if (s == null) {
			throw new IllegalArgumentException("The course cannot be removed");
		}
		
		try {
			for (int i = 0; i < roll.size(); i++) {
				if (s.equals(roll.get(i))) {
					roll.remove(i);
					if (waitlist.size() != 0) {
						roll.add(waitlist.dequeue());
					}
					return;
				}
			}
			for (int i = 0; i < waitlist.size(); i++) {
				Student hold = waitlist.dequeue();
				if (!s.equals(hold)) {
					waitlist.enqueue(hold);
				}
			}
		}
		
		catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Course cannot be removed from schedule.");
		}
	}
	
	/** Checks to see if student can enroll in course
	 * @param s student whose enrollment in course is being checked
	 * @return true or false depending on whether or not student is already enrolled in course and there are open seats
	 */
	
	public boolean canEnroll(Student s) {
		if (getOpenSeats() == 0) {
			return false;
		}
		
		for (int i = 0; i < roll.size(); i++) {
			if (s.equals(roll.get(i))) {
				return false;
			}
		}
		if (waitlist.size() >= 10) {
			return false;
		}
		boolean inWaitlist = false;
		for (int i = 0; i < waitlist.size(); i++) {
			Student hold = waitlist.dequeue();
			if (s.equals(hold)) {
				inWaitlist = true;
			}
			waitlist.enqueue(hold);
		}
		
		if (inWaitlist) {
			return false;
		}
		
		return true;
	}
	
	/** Returns enrollment capacity
	 * @return enrollment capacity
	 */
	
	public int getEnrollmentCap() {
		return enrollmentCap;
	}
	
	/** Returns number of open seats in course
	 * @return number of open seats in course
	 */
	
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}
	
	/**
	 * Gets the number of people on the wait list
	 * @return the number of people in the wait list
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}
}