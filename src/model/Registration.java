package model;

public class Registration {
    private String registrationCode;
    private int year;
    private String personalID;
    private String grade; // 

    public Registration(String registrationCode, int year, String personalID, String grade) {
        this.registrationCode = registrationCode;
        this.year = year;
        this.personalID = personalID;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return personalID + ";" + registrationCode + ";" + year + ";" + grade;
    }
}
