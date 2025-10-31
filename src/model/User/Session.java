package model;

import model.user.Person;
import model.user.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Session {

    private final String CSV_DELIMITER = ";";
    private final int PERSONAL_ID_INDEX = 1;

    private Person person;
    private String idNumber;

    public Session(String idNumber) {
        this.idNumber = idNumber;
    }

    public String[] matchWithID() {
        String[] readFromPersonList;

        try (Scanner csvScanner = new Scanner(new File("src/userInfo.txt"))) {
            csvScanner.useDelimiter(CSV_DELIMITER);

            while (csvScanner.hasNextLine()) {
                readFromPersonList = csvScanner.nextLine().split(CSV_DELIMITER);
                String csvRedline = readFromPersonList[PERSONAL_ID_INDEX];

                if (csvRedline.equals(idNumber)) {
                    return readFromPersonList;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public Student matchStudent() {
        Student student = null;
        List<Student> studentList = StudentReader.readStudentFile("src/celebrities.txt");

        for (int i = 0; i < studentList.size(); i++) {
            System.out.println(studentList.get(i).getPersonalID());
            if ((studentList.get(i).getPersonalID()).equals(this.idNumber)) {
                student = studentList.get(i);
                break;
            }
        }
        return student;
    }

}
 