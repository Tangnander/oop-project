package model.User;

public class Student extends Person {

	private String program;

	public Student(String name, String personalID, String email, String username, String password, String role) {
		super(name, personalID, email, username, password, role);
	}
	
	public Student(String name, String personalID, String email, String username, String password, String role, String program) {
		super(name, personalID, username, email, role, password);
		this.program = program;
	}
	
	public String getProgram() {
		return program;
	}
}
