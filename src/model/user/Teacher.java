package model.user;

public class Teacher extends Person {

    private String program;

    public Teacher(String name, String personalID, String email, String username, String password, String program, String role) {
        super(name, personalID, email, username, password, role);
        this.program = program;
    }

    public String getProgram() {
        return program;
    }

}