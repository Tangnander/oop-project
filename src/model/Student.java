package model;

public class Student extends Person {

	private String program;

	public Student(String personalID, String name, String email, String username, String password, String program) {
		super(personalID, name, email, username, password);
		this.program = program;
	}

	public String getProgram() {
		return program;
	}
}
