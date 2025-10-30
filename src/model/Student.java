package model;

public class Student extends Person {

	private String program;

	public Student(String name, String personalID, String email, String username, String password) {
		super(name, personalID, email, username, password);
	}
	
	public Student(String name, String personalID, String email, String username, String password, String program) {
		super(name, personalID, username, email, password);
		this.program = program;
	}
	
	public String getProgram() {
		return program;
	}
}
