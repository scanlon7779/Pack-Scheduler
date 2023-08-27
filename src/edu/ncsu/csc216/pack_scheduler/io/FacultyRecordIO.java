package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ListIterator;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Reads in faculty objects from a file and returns a linkedlist of faculty objects
 * @author colinscanlon
 *
 */
public class FacultyRecordIO {
	/** Processes line from student records input file and creates new Student object
	 * out of information read
	 * @param line line from student records input file
	 * @return Student object created from line
	 */
	
	private static Faculty processFaculty(String line) {
		Scanner lineScan = new Scanner(line.trim());
		lineScan.useDelimiter(",");
		String first = "";
		String last = "";
		String id = "";
		String email = "";
		String pwd = "";
		int maxCourses = 0;
		Faculty f1 = null;
		
		while (lineScan.hasNext()) {
			first = lineScan.next();
			last = lineScan.next();
			id = lineScan.next();
			email = lineScan.next();
			pwd = lineScan.next();
			if (lineScan.hasNextInt()) {
				maxCourses = lineScan.nextInt();
			}
		}
		
		lineScan.close();
		
		f1 = new Faculty(first, last, id, email, pwd, maxCourses);
		f1.setFirstName(first);
		f1.setLastName(last);
		f1.setEmail(email);
		f1.setPassword(pwd);
		f1.setMaxCourses(maxCourses);
		
		return f1;
	} 
	
	/** Reads in student records from file and adds them to sorted list of student records
	 * @param fileName name of file containing student records
	 * @return sorted list of student records arranged in alphabetical order by last name
	 * @throws FileNotFoundException if file doesn't exist
	 */
	
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		
		FileInputStream in = new FileInputStream(new File(fileName));
		Scanner input = new Scanner(in);
		LinkedList<Faculty> f = new LinkedList<Faculty>();
		Faculty readRecord = null;
		int count = 0;
		String line = "";
		while (input.hasNextLine()) {
			line = input.nextLine().trim();
			try {
				readRecord = processFaculty(line);
				f.add(count, readRecord);
				count++;
			} catch(IllegalArgumentException e) {
				//e.getMessage();
			}
		}
		input.close();
		return f;
	}
	
	/**
	 * Writes faculty infomration to a file
	 * @param fileName the name of the file to write to
	 * @param fList the list of faculty objects to write
	 * @throws IOException if the given file is unable to write to 
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> fList) throws IOException {
		PrintWriter output = new PrintWriter(new FileWriter(fileName));
		ListIterator<Faculty> iterator = fList.listIterator(0);
		while (iterator.hasNext()) {
			output.println(iterator.next().toString());
		}
		
		output.close();
	}

}
