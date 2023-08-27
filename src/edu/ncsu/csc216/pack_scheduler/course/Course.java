/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Creates a Course object
 * @author Mitthu Tiwari
 * @version 1.0
 */

public class Course extends Activity implements Comparable<Course> {
	
	/** Course's name */
	private String name;
	
	/** Course's section */
	private String section;
	
	/** Course's credit hours */
	private int credits;
	
	/** Course's instructor */
	private String instructorId;
	
	/** CourseRoll roll */
	private CourseRoll roll;
	
	/** Number of digits in course's section number */
	private static final int SECTION_LENGTH = 3;
	
	/** Minimum number of credit hours student can take */
	private static final int MAX_CREDITS = 5;
	
	/** Maximum number of credit hours student can take */
	private static final int MIN_CREDITS = 1;
	
	/**
	 * Constructor for Course object
	 * @param name course name
	 * @param title course title
	 * @param section course section
	 * @param credits course credits
	 * @param instructorId instructor's ID
	 * @param enrollmentCap course enrollment cap
	 * @param meetingDays course meeting days
	 * @param startTime course meeting start time
	 * @param endTime course meeting end time
	 */
	
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		roll = new CourseRoll(this, enrollmentCap);
	}

	/**
	 * Constructor for Course object for courses that are arranged
	 * @param name course name
	 * @param title course title
	 * @param section course section
	 * @param credits course credits
	 * @param instructorId instructor's ID
	 * @param enrollmentCap course enrollment cap
	 * @param meetingDays course meeting days
	 */
	
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
		roll = new CourseRoll(this, enrollmentCap);
	}

	/**
	 * Returns course name
	 * @return course name
	 */
	
	public String getName() {
		return name;
	}

	/**
	 * Sets course name to name passed as parameter after checking if passed name is valid
	 * @param name course name to set
	 * @throws IllegalArgumentException if name passed is invalid
	 */
	
	private void setName(String name) {
		CourseNameValidator cnv = new CourseNameValidator();
		if (name == null) {
			throw new IllegalArgumentException();
		}
		try {
			cnv.isValid(name);
			this.name = name;
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Name's length should be between 5 and 8, inclusive.");
		}
	}

	/**
	 * Returns course section
	 * @return course section
	 */
	
	public String getSection() {
		return section;
	}

	/**
	 * Sets course section to section passed as parameter if section passed is valid
	 * @param section course section to set
	 * @throws IllegalArgumentException if section passed is invalid
	 */
	
	public void setSection(String section) {
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}
		
		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Section should be three digits.");
			}
		}
		
		this.section = section;
	}

	/**
	 * Returns course credits
	 * @return course credits
	 */
	
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets course credits to amount of credits passed as parameter if amount of credits passed is valid
	 * @param credits course credits to set
	 * @throws IllegalArgumentException if amount of credits passed is invalid
	 */
	
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Credits should be between 1 and 5, inclusive.");
		}
		
		this.credits = credits;
	}

	/**
	 * Returns instructor ID
	 * @return instructor ID
	 */
	
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets instructor ID to instructor ID passed as parameter if instructor ID passed is valid
	 * @param instructorId instructor ID to set
	 * @throws IllegalArgumentException if instructor ID passed is invalid
	 */
	
	public void setInstructorId(String instructorId) {
		if ("".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		
		
		this.instructorId = instructorId;
	}

	/** Generates hashCode for Course using fields
	 * Overrides Object class' hashCode method
	 * @return hashCode for Course
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/** Compares this object to another passed as parameter to this method
	 * Overrides Object class' equals method
	 * @param obj object being compared
	 * @return true if objects are equal and have same fields
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Creates String representation of Course fields
	 * Overrides Object class' toString method
	 * @return String representation of Course fields
	 */
	
	@Override
	public String toString() {
		if ("A".equals(getMeetingDays())) {
	        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getCourseRoll().getEnrollmentCap() + "," + getMeetingDays();
	    }
		
	    return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getCourseRoll().getEnrollmentCap() + "," + getMeetingDays() + "," 
	    + getStartTime() + "," + getEndTime();
	}

	/** Returns short array with only some of fields of Course class 
	 * Overrides Activity class' getShortDisplayArray method to implement method functionality
	 * @return short array with only some of fields of Course class 
	 */
	
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplay = {name, section, getTitle(), getCourseRoll().getOpenSeats() + "", getMeetingString()};
		return shortDisplay;
	}

	/** Returns array with all of fields of Course class except startTime and endTime 
	 * Overrides Activity class' getLongDisplayArray method to implement method functionality
	 * @return array with all of fields of Course class except startTime and endTime 
	 */
	
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplay = {name, section, getTitle(), "" + credits, instructorId, getMeetingString(), ""};
		return longDisplay;
	}

	/** Sets course's meeting days and time
	 * Overrides Activity class' setMeetingDaysAndTime method to implement Course class-specific
	 * functionality
	 * @param meetingDays course meetingDays
	 * @param startTime course start time
	 * @param endTime course end time
	 */
	
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days.");
		}
		
		if ("A".equals(meetingDays)) {
			super.setMeetingDaysAndTime("A", 0, 0);
		} else { 
			int mon = 0;
			int tues = 0;
			int wed = 0;
			int thurs = 0;
			int fri = 0;
			for (int i = 0; i < meetingDays.length(); i++) {
				if ('M' == meetingDays.charAt(i)) {
					mon++;
				} else if ('T' == meetingDays.charAt(i)) {
					tues++;
				} else if ('W' == meetingDays.charAt(i)) {
					wed++;
				} else if ('H' == meetingDays.charAt(i)) {
					thurs++;
				} else if ('F' == meetingDays.charAt(i)) {
					fri++;
				} else if (meetingDays.charAt(i) != 'M' && meetingDays.charAt(i) != 'T'
						&& meetingDays.charAt(i) != 'W' && meetingDays.charAt(i) != 'H' 
						&& meetingDays.charAt(i) != 'F') {
					throw new IllegalArgumentException("Invalid meeting days.");
				}
			}
			
			if (mon > 1 || tues > 1 || wed > 1 || thurs > 1 || fri > 1) {
				throw new IllegalArgumentException("Invalid meeting days.");
			} 
			
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}
	}
	
	/** Checks to see if course name already exists in schedule
	 * @param activity activity whose title the course name is being compared to to check for
	 * duplication
	 * @return true or false depending on whether or not course name already exists in schedule
	 */
	
	public boolean isDuplicate(Activity activity) {
		if(activity instanceof Course) {
			Course c = (Course) activity;
			if (getName().equals(c.getName()) && getSection().equals(c.getSection())) {
				return true;
			}
		}
				
		return false;
	}
	
	/** Compares this object with another Course object to see if they're at the same place in a certain order
	 * @param c Course object with which this object is being compared to
	 * @return integer value based on whether this object is less than, greater than, or equal to the other Course object 
	 * Method is based off of compareTo() method in Student class
	 */
	
	public int compareTo(Course c) {
		if (c == null) {
			throw new NullPointerException();
		} else if (!c.getClass().equals(this.getClass())) {
			throw new ClassCastException();
		} else {
			if(name.equals(c.getName()) && section.equals(c.getSection())) {
				return 0;
			} else if(name.compareTo(c.getName()) > 0) {
				return name.compareTo(c.getName());
			} else if(name.compareTo(c.getName()) < 0) {
				return name.compareTo(c.getName());
			} else if(section.compareTo(c.getSection()) < 0) {
				return section.compareTo(c.getSection());
			} else if(section.compareTo(c.getSection()) > 0) {
				return section.compareTo(c.getSection());
			}
			
			return -1;
		}
	}
	
	/**
	 * Returns the courses roll
	 * @return roll of the course
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}
}
