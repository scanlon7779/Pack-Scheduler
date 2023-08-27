package edu.ncsu.csc216.pack_scheduler.course;


/** Creates an Activity object
 * Contains methods and variables Event and Course classes have in common
 * @author Mitthu Tiwari
 * @version 1
 */

public abstract class Activity implements Conflict {

	/** Activity's title */
	private String title;
	
	/** Activity's meeting days */
	private String meetingDays;
	
	/** Activity's starting time */
	private int startTime;
	
	/** Activity's ending time */
	private int endTime;
	
	/** Maximum time in day */
	private static final int UPPER_HOUR = 24;
	
	/** Maximum time in an hour */
	private static final int UPPER_MINUTE = 60;
	
	/* Number of hours in half of a day */
	/* private static final int HALF_OF_DAY = 12; */
	
	/* Class constant for ten minutes used in getMeetingString() method */
	/* private static final int TEN_MINUTES = 10; */

	/** Constructor of Activity used by Event and Course classes 
	 * @param title activity title
	 * @param meetingDays days activity occurs
	 * @param startTime activity start time
	 * @param endTime activity end time
	 */
	
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		this.title = title;
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}
	
	/** Checks to see if the current Activity object conflicts with the method parameter Activity object
	 * Overrides checkConflict method in Conflict interface to implement method
	 * @param possibleConflictingActivity activity being compared to current Activity object
	 * @throws ConflictException if meeting days as well as times of activities overlap
	 */
	
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		int same = 0;
		
		if (this.getMeetingDays().equals("A") || possibleConflictingActivity.getMeetingDays().equals("A")) {
			return;
		}
		
		for (int i = 0; i < this.getMeetingDays().length(); i++) {
			for (int j = 0; j < possibleConflictingActivity.getMeetingDays().length(); j++) {
				if (this.getMeetingDays().charAt(i) == possibleConflictingActivity.getMeetingDays().charAt(j)) {
					same++;
					
					if (this.getStartTime() == possibleConflictingActivity.getEndTime() || this.getEndTime() 
							== possibleConflictingActivity.getStartTime()) {
						throw new ConflictException();
					} else if (possibleConflictingActivity.getStartTime() > this.getStartTime() 
							&& possibleConflictingActivity.getStartTime() < this.getEndTime()) {
						throw new ConflictException();
					} else if (possibleConflictingActivity.getStartTime() < this.getStartTime() 
							&& possibleConflictingActivity.getEndTime() < this.getEndTime() 
							&& possibleConflictingActivity.getEndTime() > this.getStartTime()) {
						throw new ConflictException();
					} else if (possibleConflictingActivity.getStartTime() > this.getStartTime() 
							&& possibleConflictingActivity.getEndTime() < this.getEndTime()) {
						throw new ConflictException();
					} else if (possibleConflictingActivity.getStartTime() < this.getStartTime() 
							&& possibleConflictingActivity.getEndTime() > this.getEndTime()) {
						throw new ConflictException();
					} else if (possibleConflictingActivity.getStartTime() == this.getStartTime() 
							&& (possibleConflictingActivity.getEndTime() < this.getEndTime() 
							|| possibleConflictingActivity.getEndTime() > this.getEndTime())) {
						throw new ConflictException();
					} else if (possibleConflictingActivity.getEndTime() == this.getEndTime() 
							&& (possibleConflictingActivity.getStartTime() > this.getStartTime() 
							|| possibleConflictingActivity.getStartTime() < this.getStartTime())) {
						throw new ConflictException();
					} else if (this.getStartTime() == possibleConflictingActivity.getStartTime() 
							&& this.getEndTime() == possibleConflictingActivity.getEndTime() 
							&& !this.getTitle().equals(possibleConflictingActivity.getTitle())) {
							throw new ConflictException();
					} else if (this.getStartTime() == possibleConflictingActivity.getStartTime() && this.getEndTime() 
							== possibleConflictingActivity.getEndTime() 
							&& this.getMeetingDays().equals(possibleConflictingActivity.getMeetingDays()) 
							&& this.getTitle().equals(possibleConflictingActivity.getTitle())) {
						throw new ConflictException();
					}
				}
			}
		}
		
		if (this.getMeetingDays().length() != possibleConflictingActivity.getMeetingDays().length() && same >= 1
				&& this.getStartTime() == possibleConflictingActivity.getStartTime() && this.getEndTime() 
				== possibleConflictingActivity.getEndTime()) {
			throw new ConflictException();
		}
	}

	/** Returns short array with only some of fields of Course class 
	 * @return short array with only some of fields of Course class 
	 */
	
	public abstract String[] getShortDisplayArray();
	
	/** Returns array with all of fields of Course class except startTime and endTime 
	 * @return array with all of fields of Course class except startTime and endTime 
	 */
	
	public abstract String[] getLongDisplayArray();
	
	/** Checks to see if an activity is a duplicate of an already existing one
	 * @param activity activity being checked
	 * @return true or false depending on whether activity is duplicate of an already 
	 * existing one
	 */
	
	public abstract boolean isDuplicate(Activity activity);
	
	/**
	 * Returns course title
	 * @return course title
	 */
	
	public String getTitle() {
		return title;
	}

	/**
	 * Sets course title to title passed as parameter if title passed is valid
	 * @param title course title to set
	 * @throws IllegalArgumentException if title passed is invalid
	 */
	
	public void setTitle(String title) {
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
		
		this.title = title;
	}

	/** Sets course meeting days and start and end times to meeting days, start time, and end time passed as parameters
	 * if those parameters are valid
	 * @param meetingDays meeting days to set
	 * @param startTime course start time to set
	 * @param endTime course end time to set
	 * @throws IllegalArgumentException if any of parameters passed are invalid
	 */
	
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		int startHours = startTime / 100;
		int startMinutes = startTime % 100;
		int endHours = endTime / 100;
		int endMinutes = endTime % 100;
		
		/* if (meetingDays == null ) {
			throw new IllegalArgumentException("Invalid meeting days.");
		}
		if ("".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days.");
		} */
			
		if (startHours < 0 || startHours >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid start time.");
		}
		
		if (startMinutes < 0 || startMinutes >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid start time.");
		}
		
		if (endHours < 0 || endHours >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid end time.");
		}
		
		if (endMinutes < 0 || endMinutes >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid end time.");
		}
		
		if (endHours < startHours) {
			throw new IllegalArgumentException("End time cannot be before start time.");
		}
		
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/** Converts military meeting time and returns string representation of meeting days and time in standard time
	 * @return string representation of meeting days and time in standard time
	 */
	
	public String getMeetingString() {
		String meetingTimes = "";
		
		if("A".equals(meetingDays)) {
			meetingTimes = "Arranged";
			return meetingTimes;
		}
		
		int startHour = startTime / 100;
		int startMin = startTime % 100;
		int endHour = endTime / 100;
		int endMin = endTime % 100;
		
		String startTimeOfDay = "AM";
		String endTimeOfDay = "AM";
		
		
		if(startHour == 12) {
			startTimeOfDay = "PM";
		}
		
		if(endHour == 12) {
			endTimeOfDay = "PM";
		}
		
		if(startHour == 12 && startMin > 0) {
			startTimeOfDay = "PM";
		}
		if(endHour == 12 && endMin > 0) {
			endTimeOfDay = "PM";
		}
		if(startHour > 12) {
			startHour -= 12;
			startTimeOfDay = "PM";
		}
		if(endHour > 12) {
			endHour -= 12;
			endTimeOfDay = "PM";
		}

		//MW 1:30PM-2:45PM
		meetingTimes = meetingDays + " " + startHour + ":";
		if(startMin < 10) {
			meetingTimes += "0" + startMin;
		} else {
			meetingTimes += startMin;
		}
		meetingTimes += startTimeOfDay + "-" + endHour + ":";
		
		if(endMin < 10) {
			meetingTimes += "0" + endMin;
		} else {
			meetingTimes += endMin;
		}
		meetingTimes += endTimeOfDay;
		
		return meetingTimes;
	}
	
	/*
	public String getMeetingString() {
		String arranged = "";
		int startHrs = this.startTime / 100;
		int startMins = this.startTime % 100;
		int endHrs = this.endTime / 100;
		int endMins = this.endTime % 100;
		int standardStartHrs = 0;
		int standardStartMins = startMins;
		int standardEndHrs = 0;
		int standardEndMins = endMins;
		String amorpmStart = "";
		String amorpmEnd = "";
		String startM = "";
		String endM = "";
		String days = this.meetingDays;
		String meeting = "";
		
		if (days == null || "".equals(days)) {
			throw new IllegalArgumentException("Invalid meeting days.");
		} else if ("A".equals(days)) {
			standardStartHrs = 0;
			standardStartMins = 0;
			standardEndHrs = 0;
			standardEndMins = 0;
			this.startTime = 0;
			this.endTime = 0;
			arranged = "Arranged";
			return arranged;
		} else {
			int m = 0;
			int t = 0;
			int w = 0;
			int th = 0;
			int f = 0;
			for (int i = 0; i < days.length(); i++) {
				if ('M' == days.charAt(i)) {
					m++;
				} else if ('T' == days.charAt(i)) {
					t++;
				} else if ('W' == days.charAt(i)) {
					w++;
				} else if ('H' == days.charAt(i)) {
					th++;
				} else if ('F' == days.charAt(i)) {
					f++;
				} else if (days.charAt(i) != 'A' && days.charAt(i) != 'M' && days.charAt(i) != 'T'
						&& days.charAt(i) != 'W' && days.charAt(i) != 'H' && days.charAt(i) != 'F') {
					throw new IllegalArgumentException("Invalid meeting days.");
				}
			}
			
			if (m > 1 || t > 1 || w > 1 || th > 1 || f > 1) {
				throw new IllegalArgumentException("Invalid meeting days.");
			}
		}
			
		if (startHrs == 0) {
			standardStartHrs = HALF_OF_DAY;
			amorpmStart = "AM";
		} else if (startHrs > 0 && startHrs < HALF_OF_DAY) {
			standardStartHrs = startHrs;
			amorpmStart = "AM";
		} else if (startHrs == HALF_OF_DAY) {
			standardStartHrs = HALF_OF_DAY;
			amorpmStart = "PM";
		} else if (startHrs > HALF_OF_DAY) {
			standardStartHrs = startHrs - HALF_OF_DAY;
			amorpmStart = "PM";
		}
		
		if (endHrs == 0) {
			standardEndHrs = HALF_OF_DAY;
			amorpmEnd = "AM";
		} else if (endHrs > 0 && endHrs < HALF_OF_DAY) {
			standardEndHrs = endHrs;
			amorpmEnd = "AM";
		} else if (endHrs == HALF_OF_DAY) {
			standardEndHrs = HALF_OF_DAY;
			amorpmEnd = "PM";
		} else if (endHrs > HALF_OF_DAY) {
			standardEndHrs = endHrs - HALF_OF_DAY;
			amorpmEnd = "PM";
		}
		
		if (standardStartMins < TEN_MINUTES) {
			startM = "0" + standardStartMins;
		} else {
			startM = "" + standardStartMins;
		}
		
		if (standardEndMins < TEN_MINUTES) {
			endM = "0" + standardEndMins;
		} else {
			endM = "" + standardEndMins;
		}
		
		meeting = days + " " + standardStartHrs + ":" + startM + amorpmStart + "-" + standardEndHrs + ":" + endM + amorpmEnd;
		return meeting;
	}
*/
	/**
	 * Returns course meeting days
	 * @return course meeting days
	 */
	
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns start time of course meeting
	 * @return start time of course meeting
	 */
	
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns end time of course meeting
	 * @return end time of course meeting
	 */
	
	public int getEndTime() {
		return endTime;
	}

	/** Generates hashCode for Activity using fields
	 * Overrides Object class' hashCode method
	 * @return hashCode for Activity
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		/*
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} */
		
		if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}