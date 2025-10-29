package model;

public abstract class Person implements UserType {

	protected String personalID;
	protected String name;
	protected String username;
	protected String password;
	protected String email;

	public Person(String personalID, String name, String email, String username, String password) {
		this.personalID = personalID;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public String getPersonalID() {
		return personalID;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	// från interfacet UserType

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void getPermissions() {
	}

}
