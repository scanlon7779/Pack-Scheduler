/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Checks to see if a conflict occurs between an activity passed into the checkConflict method
 * as a parameter and the activity the aforementioned method is being called on
 * @author Mitthu Tiwari
 * @version 1
 */

public interface Conflict {

	/** 
	 * Checks to see if a conflict occurs between an activity passed into the checkConflict method
	 * as a parameter and the activity the aforementioned method is being called on
	 * @param possibleConflictingActivity the activity being compared
	 * @throws ConflictException if a conflict does occur
	 */
	
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
}
