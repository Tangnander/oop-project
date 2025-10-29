package model;

public class Teacher extends Person {

	private String program;
	
	public Teacher(String personalID, String name, String email, String username, String password, String program) {
		super(personalID, name, email, username, password);
		this.program = program;
	}
	
	public String getProgram() {
		return program;
	}
}
