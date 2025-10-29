package model;

public abstract class Person implements UserType {
	
	protected String personalID;
	protected String name;
	protected String username;
	protected String password;
	protected String email;

	public Person(String name, String personalID, String email, String username, String password) {
		this.name = name;
		this.personalID = personalID;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public Person(String name, String personalID) {
		this.name = name;
		this.personalID = personalID;
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
