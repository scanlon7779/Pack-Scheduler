/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Validates course names
 * @author Mitthu Tiwari
 * @author colinscanlon
 * @version 1
 */

public class CourseNameValidator {
	
	/** Keeps track of number of letters in course name */
	private int letterCount;
	
	/** Keeps track of number of digits in course name */
	private int digitCount;
	
	/** Keeps track of the number of letters in a suffix */
	private int suffixCount;
	
	/** accounts for any extra digits **/
	private int suffixDigit;
	
	/** Keeps track of character's current state */
	private State currentState;
	
	/** Minimum number of course characters */
	private static final int MIN_COURSE_CHARACTERS = 4;
	
	/** Maximum number of course characters */
	private static final int MAX_COURSE_CHARACTERS = 8;
	
	/** Checks to see if course name is valid or not
	 * @param courseName name of course passed in as method parameter
	 * @return true or false depending on whether or not course name is valid
	 * @throws InvalidTransitionException  if there are any format issues or non digit or letter characters
	 */
	
	public boolean isValid(String courseName) throws InvalidTransitionException {
		if (courseName.length() >= MIN_COURSE_CHARACTERS && courseName.length() <= MAX_COURSE_CHARACTERS) {
			currentState = new InitialState();
			currentState.onDigit();
			currentState.onLetter();
			
			for (int i = 0; i < courseName.length(); i++) {
				if (Character.isLetter(courseName.charAt(i))) {
					if(digitCount >= 1 && digitCount < 3) {
						throw new InvalidTransitionException("Course name must have 3 digits.");
					}
					if (digitCount == 3) {
						currentState = new SuffixState();
						currentState.onLetter();
					} else {
						currentState = new LetterState();
						currentState.onLetter();
						currentState.onDigit();
					}
				} else if (Character.isDigit(courseName.charAt(i))) {
					if(letterCount < 1) {
						throw new InvalidTransitionException("Course name must start with a letter.");
					}
					if(suffixCount > 0) {
						currentState = new SuffixState();
						currentState.onDigit();
					} else {
						currentState = new NumberState();
						currentState.onDigit();
						currentState.onLetter();
					}
				} else {
					currentState.onOther();
				}
			}
			
			if (digitCount != 3) {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
			
			if (letterCount < 1 || letterCount > 4) {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
			
			if (suffixCount > 1) {
				throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
			}
			
			if (suffixDigit != 0) {
				throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
			}
			
			return true;
		} else {
			currentState = new InitialState();
			currentState.onOther();
			return false;

		}
		
	}
	
	/** Abstract class for defining state of character in course name 
	 * @author Mitthu Tiwari
	 * @version 1
	 */
	
	public abstract class State {
		
		/** Handles letter input from course name 
		 */
		
		public abstract void onLetter();
		
		/** Handles digit input from course name 
		 */
		
		public abstract void onDigit();
		
		/** Handles any other input from course name 
		 * @throws InvalidTransitionException with the message "Course name can only contain letters and digits."
		 */
		
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
	
	/** Defines initial state of all characters in course name 
	 * @author Mitthu Tiwari
	 * @version 1
	 */
	
	public class InitialState extends State {
		
		/** Sets letterCount to 0 due to being in initial state
		 * Overrides onLetter() method in State to implement it
		 */
		
		@Override
		public void onLetter() {
			letterCount = 0;
		}
		
		/** Sets digitCount to 0 due to being in initial state
		 * Overrides onDigit() method in State to implement it
		 */
		
		@Override
		public void onDigit() {
			digitCount = 0;
		}
		
	}
	
	/** Defines letter state of character in course name 
	 * @author Mitthu Tiwari
	 * @version 1
	 */
	
	public class LetterState extends State {
		
		/** Increments letterCount while letterCount is within its limits due to being in letter state
		 * Overrides onLetter() method in State to implement it
		 */
		
		@Override
		public void onLetter() {
			letterCount++;
		}
		
		/** Does nothing to digit count by adding 0 to it due to being in letter state
		 * Overrides onDigit() method in State to implement it
		 */
		
		@Override
		public void onDigit() {
			digitCount += 0;
		}
		
		
	}
	
	/** Defines number state of character in course name 
	 * @author Mitthu Tiwari
	 * @version 1
	 */
	
	public class NumberState extends State {
		
		/** Does nothing to letterCount by adding 0 to it due to being in number state
		 * Overrides onLetter() method in State to implement it
		 */
		
		@Override
		public void onLetter() {
			letterCount += 0;
		}
		
		/** Increments digitCount while digitCount is within its limits due to being in number state
		 * Overrides onDigit() method in State to implement it
		 */
		
		@Override
		public void onDigit() {
			digitCount++;
			
		}
		
	}
	
	/** Defines suffix state of character in course name 
	 * @author Mitthu Tiwari
	 * @version 1
	 */
	
	public class SuffixState extends State {
				
		
		/** Sets letterCount equal to maximum number of letters in suffix due to being in suffix state
		 * Overrides onLetter() method in State to implement it
		 */
		
		@Override
		public void onLetter() {
			suffixCount++;
		}
		
		/** Does nothing to digit count by adding 0 to it due to being in suffix state
		 * Overrides onDigit() method in State to implement it
		 */
		
		@Override
		public void onDigit() {
			suffixDigit++;
		}
		
	}
}
