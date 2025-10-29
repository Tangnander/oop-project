package model;

import java.io.*;
import java.util.*;

public class CourseReader {

    public static void main(String[] args) {
        List<CourseTracker> courses = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("courses.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Dela upp raden på semikolon
                String[] parts = line.split(";");
                
                // Skapa CourseTracker-objekt (observera typer)
                String courseCode = parts[0]; 						//kurskod 
                String courseName = parts[1]; 						//kursnamn
                double points = Double.parseDouble(parts[2]);		//hösgskolepoäng
                String registrationCode = parts[3];					//anmälningskod
                int year = Integer.parseInt(parts[4]);				//år
                int week = Integer.parseInt(parts[5]);				//vecka
                int studyPlaces = Integer.parseInt(parts[6]);		//studietillfälle
                double studyPace = Double.parseDouble(parts[7]);	//studietakt
             
                CourseTracker course = new CourseTracker(courseCode, courseName, points,
                                                         registrationCode, year, week, studyPlaces, studyPace);
                courses.add(course);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Testa utskrift
        for (CourseTracker c : courses) {
        	System.out.println("Kurskod;Kursnamn;Högskolepoäng;Anmälningskod;Årtal;Vecka;Studieplatser;Studietakt");
        	System.out.println(c.getCourseCode() + c.getCourseName() + c.getPoints() + c.getRegistrationCode() 
        							+ c.getYear() + c.getWeek() + c.getStudyPlaces() + c.getStudyPace());
            
        }
    }
}
