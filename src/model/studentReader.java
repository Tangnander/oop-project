package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class studentReader {

	public static void main(String[] args) {
		List<Student> studentList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader("celebrities.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				// Dela upp raden på semikolon
				String[] parts = line.split(";");

				// Skapa CourseTracker-objekt (observera typer)

				String name = parts[0];
				String personalID = parts[1];
				String username = UsernameCreator.createUsername(name);
				String email = EmailCreator.createEmail(username);
				String password = "pass123";

				Student student = new Student(name, personalID, username, email, password);
				
				studentList.add(student);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Testa utskrift
		for (Person s : studentList) {
			System.out.println(s.getName() + " " + s.getPersonalID() + " " + s.getUsername() + " " +  s.getEmail() + " " + s.getPassword());

		}
	}
}
