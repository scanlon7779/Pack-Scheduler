/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * The InvalidTransitionException for a problem in changing states
 * @author mickeyleuang
 *
 */
public class InvalidTransitionException extends Exception {
	
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Designates a message for the InvalidTransitionException
	 * @param message the exception message being displayed.
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}

	/**
	 * Specifies a default message for the InvalidTransitionException.
	 */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}
}
