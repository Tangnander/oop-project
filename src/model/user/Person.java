package model.user;

public abstract class Person implements UserType {

    protected String name;
    protected String personalID;
    protected String email;
    protected String username;
    protected String password;
    protected String role;


    public Person(String name, String personalID, String username, String email, String password, String role) {
        this.name = name;
        this.personalID = personalID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;

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
    public String getRole() {
        return role;
    }

}
