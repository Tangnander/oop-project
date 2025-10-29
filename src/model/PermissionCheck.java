package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PermissionCheck {

    public static final String COMMA_DELIMITER = ",";

    private String requestedUserName;
    private String requestedPassword;

    public String[] readFromCSV() {
        String[] parseFromCsv;

        try (Scanner csvScanner = new Scanner(new File("src/userInfo.csv"))) {
            csvScanner.useDelimiter(COMMA_DELIMITER);

            while (csvScanner.hasNextLine()) {
                parseFromCsv = csvScanner.nextLine().split(COMMA_DELIMITER);
                String csvRedline = parseFromCsv[0];

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

    public boolean checkPassword() {
        boolean correctPassword = false;

        try {
            String[] userInfo = readFromCSV();

            if (requestedPassword.equals(userInfo[1])) {
                correctPassword = true;
            }
        } catch (NullPointerException e) {

        }
        return correctPassword;
    }

    //Testing if parsing works.
    public static void main(String[] args) {
        PermissionCheck pc = new PermissionCheck();

        pc.setRequestedLoginInfo("test2", "password");

        if (pc.checkPassword() == true) {
            System.out.println("Login Successful!");
        } else {
            System.out.println("Incorrect Username or Password!");
        }
    }

}
