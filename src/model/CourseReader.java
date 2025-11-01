package model;

import java.io.*;
import java.util.*;

public class CourseReader {

    public static List<CourseTracker> readCourses(String fileName) {
        List<CourseTracker> courses = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                
                String courseCode = parts[0];
                String courseName = parts[1];
                double points = Double.parseDouble(parts[2]);
                String registrationCode = parts[3];
                int year = Integer.parseInt(parts[4]);
                int week = Integer.parseInt(parts[5]);
                int studyPlaces = Integer.parseInt(parts[6]);
                double studyPace = Double.parseDouble(parts[7]);
             
                studyPace = studyPace * 100;
              
                
                CourseTracker course = new CourseTracker(courseCode, courseName, points,
                                                         registrationCode, year, week, studyPlaces, studyPace);
                courses.add(course);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return courses;
    }
}
	