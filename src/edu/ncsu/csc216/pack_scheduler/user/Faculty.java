package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * Faculty class that is a subclass of user and holds a number of courses being taught
 * @author colinscanlon
 *
 */
public class Faculty extends User {
	/** The max courses for the Faculty object */
	private int maxCourses;
	
	/** The minimum number of courses a faculty member has */
	private static final int MIN_COURSES = 1;
	
	/** The maximum number of courses a faculty memeber can have */
	private static final int MAX_COURSES = 3;
	
	/** The schedule for a faculty member **/
	private FacultySchedule schedule;

	/**
	 * Constructor for a faculty object 
	 * @param firstName the first name of the faculty member
	 * @param lastName the last name of the faculty member
	 * @param id the id of the faculty member
	 * @param email the email for the faculty member
	 * @param password the password of the faculty member
	 * @param maxCourses the max number of courses the faculty member can teach
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourses) {
		super(firstName, lastName, id, email, password);
		setMaxCourses(maxCourses);
		schedule = new FacultySchedule(id);
	}
	
	/**
	 * Gets the max number of courses a faculty member can teach
	 * @return the maxCourses
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/**
	 * Sets the max courses for the faculty member that must be between 1 and 3
	 * @param maxCourses the maxCourses to set
	 * @throws IllegalArgumentException if the maxCourses is not between 1 and 3
	 */
	public void setMaxCourses(int maxCourses) {
		if (maxCourses < MIN_COURSES || maxCourses > MAX_COURSES) {
			throw new IllegalArgumentException();
		}
		this.maxCourses = maxCourses;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (maxCourses != other.maxCourses)
			return false;
		return true;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
	
		return super.getFirstName() + "," + super.getLastName() + "," + super.getId() + "," + super.getEmail() + "," 
		+ super.getPassword() + "," + maxCourses;
							
	}
	
	/**
	 * gets the schedule of the faculty member
	 * @return the faculty schedule
	 */
	public FacultySchedule getSchedule() {
		return schedule;
	}
	
	/**
	 * Checks if the faculty schedule is overloaded
	 * @return if the schedule is overloaded or not
	 */
	public boolean isOverloaded() {
		if (schedule.getNumScheduledCourses() > MAX_COURSES) {
			return true;
		}
		return false;
	}

}
