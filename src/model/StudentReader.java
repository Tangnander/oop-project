package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.user.Student;

public class StudentReader {

	public static List<Student> readStudentFile(String fileName) {

		List<Student> studentList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				// Dela upp raden genom semikolon
				String[] parts = line.split(";");

				// Skapa CourseTracker-objekt (observera typer)

				String name = parts[0];
				String personalID = parts[1];
				String username = UsernameCreator.createUsername(name);
				String email = EmailCreator.createEmail(username);
				String password = "pass123";
				String role = "Student";

				Student student = new Student(name, personalID, username, email, password, role);

				studentList.add(student);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return studentList;

	}

}