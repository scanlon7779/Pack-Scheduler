/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * ConflictException class for throwing custom exception when conflict between activities occurs
 * @author Mitthu Tiwari
 * @version 1
 */

public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/** Constructor with exception message as parameter
	 * @param message exception message
	 */
	
	public ConflictException(String message) {
		super(message);
	}
	
	/** Parameterless constructor */
	
	public ConflictException() {
		this("Schedule conflict.");
	}
}
