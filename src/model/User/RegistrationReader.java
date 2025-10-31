package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationReader {

	private String username;
	private String registrationCode;
	private String year;
	private String completed;
	private boolean completedBoolean;
	private String grade;

	public List<String> readStudentFile(String fileName) {

		List<String> gradeList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				// Dela upp raden genom semikolon
				String[] parts = line.split(";");

				// Skapa CourseTracker-objekt (observera typer)

				username = parts[0];
				registrationCode = parts[1];
				year = parts[2];
				completed = parts[3];
				grade = parts[4];

				if (completed.equalsIgnoreCase("true")) {
					completedBoolean = true;
				} else {
					completedBoolean = false;
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return gradeList;

	}

	public String getUsername() {
		return username;
	}

	public String getRegistrationCode() {
		return registrationCode;
	}

	public String getYear() {
		return year;
	}

	public boolean isCompletedBoolean() {
		return completedBoolean;
	}

	public String getGrade() {
		return grade;
	}

}