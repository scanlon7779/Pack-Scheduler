package edu.ncsu.csc216.pack_scheduler.user;

/**
 * Creates a User object
 * @author Mitthu Tiwari
 *
 */
public abstract class User {

	/** Student's first name */
	private String firstName;
	/** Student's last name */
	private String lastName;
	/** Student's id. */
	private String id;
	/** Student's email */
	private String email;
	/** Student's password */
	private String password;

	/**
	 * Constructs a User
	 * @param firstName of the user
	 * @param lastName of the user
	 * @param id of the user
	 * @param email of the user
	 * @param password of the user
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
	}

	/**
	 * returns the first name of the student
	 * @return firstName first name of the student
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * sets the first name of the student
	 * Throws IllegalArgumentException if first name is null
	 * or is an empty string
	 * @param firstName to set first name of student
	 * @throws IllegalArgumentException if first name is null or an empty string
	 */
	public void setFirstName(String firstName) {
		
		if(firstName == null || firstName.length() == 0) {
			throw new IllegalArgumentException("Invalid first name");
		}
	
		this.firstName = firstName;
	}

	/**
	 * returns the last name of the student
	 * @return lastName the last name of the student
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * sets the last name of the student
	 * Throws IllegalArgumentException if last name is null
	 * or is an empty string
	 * @param lastName the lastName to set
	 * @throws IllegalArgumentException if last name is null or an empty string
	 */
	public void setLastName(String lastName) {
		if(lastName == null || lastName.length() == 0) {
			throw new IllegalArgumentException("Invalid last name");
		}
		
		this.lastName = lastName;
	}

	/**
	 * returns student's id
	 * @return id the id of the student
	 */
	public String getId() {
		
		return id;
	}

	/**
	 * sets the student id
	 * @param id the id to set
	 * @throws IllegalArgumentException if id is null or an empty string 
	 */
	protected void setId(String id) {
		if(id == null || id.length() == 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		
		this.id = id;
	}

	/**
	 * returns student's email
	 * @return the email student's ncsu email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the student's email and throws an IllegalArgumentException
	 * if email is invalid: if it is null or an empty string, if it does not contain
	 * one '@' or one '.' or if '.' occurs before '@'
	 * @param email the email to set
	 * @throws IllegalArgumentException if email is invalid
	 */
	public void setEmail(String email) {
		if(email == null || email.length() == 0) {
			throw new IllegalArgumentException("Invalid email");
		}
		int atCount = 0;
		int dotCount = 0;
		int indexOfAt = 0;
		int indexOfLastDot = 0;
		
	
		for(int i = 0; i < email.length(); i++) {
			char e = email.charAt(i);
			if(e == '@') {
				atCount++;
				indexOfAt = i;
			}
			if(e == '.') {
				dotCount++;
				indexOfLastDot = i;
			}
		}
		
		
		if(dotCount < 1 || atCount != 1 || indexOfLastDot < indexOfAt) {
	
			throw new IllegalArgumentException("Invalid email");	
		}
	
		this.email = email;
	}

	/**
	 * returns the student's password
	 * @return the password the student's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * sets the student's password
	 * throws an IllegalArgumentException if the password is null or an empty string
	 * @param password the password to set
	 * @throws IllegalArgumentException if password is null or an empty string
	 */
	public void setPassword(String password) {
		if(password == null || password.length() == 0) {
			throw new IllegalArgumentException("Invalid password");
		}
		
		this.password = password;
	}
	
	/** 
	 * Generates a hashCode for User using all fields
	 * @return hash code for User
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/** 
	 * Compares a given object to this object for equality on all fields
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

}