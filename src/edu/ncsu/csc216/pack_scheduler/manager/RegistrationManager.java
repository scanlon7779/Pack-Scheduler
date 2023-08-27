package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Manages GUI interaction with encapsulated classes
 * @author Mikato Leuang
 *
 */
public class RegistrationManager {
	/** The directory of faculty */
	private FacultyDirectory faculty;
	/** Instance of RegistrationManager*/
	private static RegistrationManager instance;
	/** A CourseCatalog object*/
	private CourseCatalog courseCatalog;
	/** A StudentDirectory object*/
    private StudentDirectory studentDirectory;
    /** The current registrar */
	private User registrar;
	/** The current user*/
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** File analyzed for registrar */
	private static final String PROP_FILE = "registrar.properties";

	/**
	 * Constructs a RegistrationManager object
	 */
	private RegistrationManager() {
		this.courseCatalog = new CourseCatalog();
		this.studentDirectory = new StudentDirectory();
		this.faculty = new FacultyDirectory();
		createRegistrar();
	}
	
	/**
	 * Creates a registrar
	 */
	private void createRegistrar() {
		Properties prop = new Properties();
		
		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);
			
			String hashPW = hashPW(prop.getProperty("pw"));
			
			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"), prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}
	
	/**
	 * Hashcodes a password
	 * @param pw password being hashcoded
	 * @return hashcoded password String
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}
	
	/**
	 * Returns an instance of RegistrationManager
	 * @return instance of RegistrationManager
	 */
	public static RegistrationManager getInstance() {
		  if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
 	}
	
	/**
	 * Returns the CourseCatalog object
	 * @return the CourseCatalog object
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}
	
	/**
	 * Returns the StudentDirectory object
	 * @return the StudentDirectory object
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}
	
	/**
	 * Logs in the User
	 * @param id the id of the User
	 * @param password the password of the User
	 * @return true if login is able false if not
	 */
	public boolean login(String id, String password) {
		Student s = studentDirectory.getStudentById(id);
		
		if (s != null) {
			if (registrar.getId().equals(id)) {
				MessageDigest digest;
				try {
					digest = MessageDigest.getInstance(HASH_ALGORITHM);
						digest.update(password.getBytes());
						String localHashPW = new String(digest.digest());
					if (registrar.getPassword().equals(localHashPW)) {
						currentUser = registrar;
							return true;
					}
				} catch (NoSuchAlgorithmException e) {
					throw new IllegalArgumentException();
				}
			}
			
			try {
				MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (s.getPassword().equals(localHashPW)) {
					currentUser = s;
						return true;
				}
			} catch (NoSuchAlgorithmException e) {
					throw new IllegalArgumentException();
			}	
			
			
			return false;
		} else {
			Faculty f = faculty.getFacultyById(id);
			if (registrar.getId().equals(id)) {
				MessageDigest digest;
				try {
					digest = MessageDigest.getInstance(HASH_ALGORITHM);
						digest.update(password.getBytes());
						String localHashPW = new String(digest.digest());
					if (registrar.getPassword().equals(localHashPW)) {
						currentUser = registrar;
							return true;
					}
				} catch (NoSuchAlgorithmException e) {
					throw new IllegalArgumentException();
				}
			}
			
			try {
				MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (f.getPassword().equals(localHashPW)) {
					currentUser = f;
						return true;
				}
			} catch (NoSuchAlgorithmException e) {
					throw new IllegalArgumentException();
			}	
			
			
			return false;
		}
		
		
	}
	
	/**
	 * Logs out the user
	 */
	public void logout() {
		currentUser = null; 
	}
	
	/** Returns the currentUser
	 * @return the currentUser
	 */
	public User getCurrentUser() {
		if (currentUser == null) {
			return null;
		}
	
		return currentUser;
	}
	
	/**
	 * Clears data from courseCatalog and studentDirectory
	 */
	public void clearData() {
		boolean bool = true;
		int i = 0;
		int j = 0;
		int a = 1;
		int b = 1;
		int c = 0;
		int d = 0;
		if(i == j) {
			bool = true;
		} 
		if(i != a) {
			bool = false;
		}
		if(i != b) {
			bool = true;
		}
		if(bool) {
			a = b;
		}
		if(a == b) {
			bool = true;
		}
		if(j != b) {
			bool = false;
		}
		if(j != a) {
			bool = false;
		}
		if(!bool) {
			i = j;
		}
		if(c == j) {
			bool = true;
		} 
		if(c != a) {
			bool = false;
		}
		if(c != b) {
			bool = true;
		}
		if(bool) {
			a = b;
		}
		if(d == j) {
			bool = true;
		} 
		if(d != a) {
			bool = false;
		}
		if(d != b) {
			bool = true;
		}
		if(bool) {
			a = b;
		}
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
	}
	
	/**
	 * Inner class that creates a registrar object
	 * @author Mikato Leuang
	 *
	 */
	private static class Registrar extends User {
		/**
		 * Creates a registrar user.
		 * @param firstName of the registrar
		 * @param lastName of the registrar
		 * @param id of the registrar
		 * @param email of the registrar
		 * @param hashPW of the registrar
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
	
	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            schedule.addCourseToSchedule(c);
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
	    if (currentUser == null || !(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}
	
	/**
	 * returns the Faculty directory
	 * @return the Faculty directory
	 */
	public FacultyDirectory getFacultyDirectory() {
		return faculty;
	}
	
	/**
	 * If the user isn't null and is the registrar the course is added to the faculty's schedule
	 * @param c the course to add
	 * @param f the faculty to add the course to
	 * @return if the course was added or not
	 */
	public boolean addFacultyToCourse(Course c, Faculty f) {
		if (currentUser != null && currentUser == registrar) {
			f.getSchedule().addCourseToSchedule(c);
			return true;
		}
		return false;
	}
	
	/**
	 * removes a given course from the given faculty's schedule
	 * @param c course to remove
	 * @param f faculty to remove from
	 * @return of the course was removed or not
	 */
	public boolean removeFacultyFromCourse(Course c, Faculty f) {
		if (currentUser != null && currentUser == registrar) {
			f.getSchedule().removeCourseFromSchedule(c);
			return true;
		}
		return false;
	}
	
	/**
	 * Resets the schedule of the given faculty member
	 * @param f the faculty to remove the course from
	 */
	public void resetFacultySchedule(Faculty f) {
		if (currentUser != null && currentUser == registrar) {
			f.getSchedule().resetSchedule();
		}
	}
	
	
}