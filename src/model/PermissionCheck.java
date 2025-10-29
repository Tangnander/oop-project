package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PermissionCheck {

    public static final String COMMA_DELIMITER = ",";

    private String requestedUserName = "test";
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
        throw new RuntimeException("User not found");
    }

    public void setRequestedLoginInfo(String username, String password) {
        this.requestedUserName = username;
        this.requestedPassword = password;
    }

    public String getRequestedUserName() {
        return requestedUserName;
    }

    //Testing if parsing works.
    public static void main(String[] args) {
        PermissionCheck pc = new PermissionCheck();

        for (String pw : pc.readFromCSV()) {
            System.out.println(pw);
        }
    }

}
