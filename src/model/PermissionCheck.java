package model;

import model.User.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PermissionCheck {

    private final String COMMA_DELIMITER = ",";
    private final int USER_NAME_INDEX = 0;
    private final int PASSWORD_INDEX = 1;
    private final int PERSONAL_ID_INDEX = 2;
    private final int ROLE_INDEX = 3;

    private String requestedUserName;
    private String requestedPassword;

    private String idNumber;
    private String userRole;
    private Student currentUser;

    //Parses the data from userInfo.csv to retrieve userdata to compare against
    //when checking passwords and username.
    public String[] readFromCSV() {
        String[] parseFromCsv;

        try (Scanner csvScanner = new Scanner(new File("src/userInfo.csv"))) {
            csvScanner.useDelimiter(COMMA_DELIMITER);

            while (csvScanner.hasNextLine()) {
                parseFromCsv = csvScanner.nextLine().split(COMMA_DELIMITER);
                String csvRedline = parseFromCsv[USER_NAME_INDEX];

                if (csvRedline.equals(requestedUserName)) {
                    return parseFromCsv;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void setRequestedLoginInfo(String username, String password) {
        this.requestedUserName = username;
        this.requestedPassword = password;
    }

    public String getRequestedUserName() {
        return requestedUserName;
    }

    //Compares the requested password with the one defined in userInfo.csv.
    public boolean checkPassword() {
        boolean correctPassword = false;

        try {
            String[] userInfo = readFromCSV();

            if (requestedPassword.equals(userInfo[PASSWORD_INDEX])) {
                correctPassword = true;
                this.idNumber = userInfo[PERSONAL_ID_INDEX];
                setUserRole(userInfo[ROLE_INDEX]);
            }
        } catch (NullPointerException e) {

        }
        return correctPassword;
    }

    public String getIdNumber() {
        return idNumber;
    }

    private void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }

    //Matches id numbers to retrieve correct user.
    public Student login() {
        Session currentSession = new Session(getIdNumber());
        this.currentUser = currentSession.matchStudent();

        return this.currentUser;
    }

    //Testing if parsing works.
    public static void main(String[] args) {
        PermissionCheck pc = new PermissionCheck();

        pc.setRequestedLoginInfo("test", "password");

        if (pc.checkPassword() == true) {
            System.out.println("Login Successful!");
        } else {
            System.out.println("Incorrect Username or Password!");
        }
        System.out.println(pc.getUserRole());

        System.out.println(pc.login());
    }

}


