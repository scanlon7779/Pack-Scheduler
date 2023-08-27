package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Student class constructs a 'student' which has a first and last name,
 * student id, email, and password
 * @author Manasa Chinta
 * @author colinscanlon
 */

public class Student extends User implements Comparable<Student> {
	/**maximum number of credits any student in the system may have*/
	public static final int MAX_CREDITS = 18;
	
	/** Student's maximum number of credits allowed */
	private int maxCredits;
	
	/** The students schedule **/
	private Schedule schedule = new Schedule();

	/**
	 * Constructs a 'student' with a name, id, email, password
	 * and number of credit hours they are taking
	 * @param firstName student's first name
	 * @param lastName student's last name
	 * @param id student's id
	 * @param email student's email
	 * @param password student's password
	 * @param maxCredits number of credit hours student is taking
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		super(firstName, lastName, id, email, password);
		setMaxCredits(maxCredits);
	}
	
	/**
	 * Creates a student with a first and last name, student id,
	 * email, password, and 18 credit hours
	 * @param firstName student's first name
	 * @param lastName student's last name
	 * @param id student's id
	 * @param email student's email
	 * @param password student's password
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, MAX_CREDITS);
	}
	/**
	 * returns the maximum number of credits
	 * @return the maxCredits maximum number of credit hours a student can take
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * sets number of credits for a student
	 * throws IllegalArgumentException if number of credits is not between 
	 * 3 and 18 inclusive
	 * @param maxCredits the maxCredits to set
	 * @throws IllegalArgumentException if number of credits is less than 3 or above 18
	 */
	public void setMaxCredits(int maxCredits) {
		
		if(maxCredits < 3 || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
			
		}
		this.maxCredits = maxCredits;
	}
	
	/** Checks to see if course can be added to schedule by calling Schedule class' canAdd() method and checking to see if total
	 * credits in schedule equals maximum credits for student
	 * @param c course being checked
	 * @return true or false based on whether or not course gets added to schedule
	 */
	public boolean canAdd(Course c) {
		if (!schedule.canAdd(c) || schedule.getScheduleCredits() == maxCredits) {
			return false;
		} 
		
		return true;
	}
	
	/** 
	 * Generates a hashCode for Student using all fields
	 * @return hash code for Student
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	/** 
	 * Compares a given object to this object for equality on all fields
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields
	 */
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
			return true;
		} else if (!super.equals(obj) || getClass() != obj.getClass()) {
			return false;
		}
		Student other = (Student) obj;
		if (maxCredits != other.maxCredits) {
			return maxCredits != other.maxCredits;
		}
		return true;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
	
		return super.getFirstName() + "," + super.getLastName() + "," + super.getId() + "," + super.getEmail() + "," 
		+ super.getPassword() + "," + maxCredits;
							
	}
	
	/**
	 * compares the fields of this object and the passed in object to 
	 * determine lexicographically if this Student is less than, equal to, or greater 
	 * than the Student object passed in
	 */
	@Override
	public int compareTo(Student s) {
	
		if(s == null) {
			throw new NullPointerException();
		}
		if(!(s.getClass().equals(getClass()))) { 
			throw new ClassCastException();
		} 
		
		//comparing last name

		if(super.getLastName().equals(s.getLastName()) && super.getFirstName().equals(s.getFirstName()) 
				&& super.getId().equals(s.getId())) {
			return 0;
		}
		if(super.getLastName().compareTo(s.getLastName()) > 0) {
			return super.getLastName().compareTo(s.getLastName());
		}
		if(super.getLastName().compareTo(s.getLastName()) < 0) {
			return super.getLastName().compareTo(s.getLastName());
		} 
		if(super.getFirstName().compareTo(s.getFirstName()) < 0) {
			return super.getFirstName().compareTo(s.getFirstName());
		}
		if(super.getFirstName().compareTo(s.getFirstName()) > 0) {
			return super.getFirstName().compareTo(s.getFirstName()); 
		}
		if(super.getId().compareTo(s.getId()) < 0) {
			return super.getId().compareTo(s.getId());
		}
		if(super.getId().compareTo(s.getId()) > 0) {
			return super.getId().compareTo(s.getId());
		}
		
		return -1;
	}
	
	/**
	 * Gets the schedule for the student 
	 * @return the students schedule
	 */
	public Schedule getSchedule() {
		return schedule;
	}

}
