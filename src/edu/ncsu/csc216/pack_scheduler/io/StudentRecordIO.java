package edu.ncsu.csc216.pack_scheduler.io;

import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
//import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.FileInputStream;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/** Class for handling file input and output of student records
 * @author Mitthu Tiwari
 * @version 1
 */

public class StudentRecordIO {

	/** Processes line from student records input file and creates new Student object
	 * out of information read
	 * @param line line from student records input file
	 * @return Student object created from line
	 */
	
	private static Student processStudent(String line) {
		Scanner lineScan = new Scanner(line.trim());
		lineScan.useDelimiter(",");
		String first = "";
		String last = "";
		String id = "";
		String email = "";
		String pwd = "";
		int maxCreds = 0;
		Student student = null;
		
		while (lineScan.hasNext()) {
			first = lineScan.next();
			last = lineScan.next();
			id = lineScan.next();
			email = lineScan.next();
			pwd = lineScan.next();
			if (lineScan.hasNextInt()) {
				maxCreds = lineScan.nextInt();
			}
		}
		
		lineScan.close();
		
		student = new Student(first, last, id, email, pwd, maxCreds);
		student.setFirstName(first);
		student.setLastName(last);
		student.setEmail(email);
		student.setPassword(pwd);
		student.setMaxCredits(maxCreds);
		
		return student;
	} 
	
	/** Reads in student records from file and adds them to sorted list of student records
	 * @param fileName name of file containing student records
	 * @return sorted list of student records arranged in alphabetical order by last name
	 * @throws FileNotFoundException if file doesn't exist
	 */
	
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		
		FileInputStream in = new FileInputStream(new File(fileName));
		Scanner input = new Scanner(in);
		SortedList<Student> students = new SortedList<Student>();
		Student readRecord = null;
		String line = "";
		while (input.hasNextLine()) {
			line = input.nextLine().trim();
			try {
				readRecord = processStudent(line);
				students.add(readRecord);
			} catch(IllegalArgumentException e) {
				//e.getMessage();
			}
		}
		input.close();
		return students;
	}

	/** Writes student records sorted in alphabetical order into file
	 * @param fileName name of file that will contain student records
	 * @param studentDirectory sorted list of student records arranged in alphabetical order by last name
	 * @throws IOException if method can't write to file
	 */
	
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		//PrintWriter and FileWriter suggested by TA during office hours
		PrintWriter output = new PrintWriter(new FileWriter(fileName));
		for (int i = 0; i < studentDirectory.size(); i++) {
			//Got idea to use trim method from Java String API
			output.println(studentDirectory.get(i).toString().trim());
		}
		
		output.close();
		
		/*
		if filename exists {
			do printstream
		}*/
		
		//PrintStream output = new PrintStream(fileName);
		
		/*for (int i = 0; i < studentDirectory.size(); i++) {
			//Got idea to use trim method from Java String API
			output.println(studentDirectory.get(i).toString().trim());
		}*/
	}
}
