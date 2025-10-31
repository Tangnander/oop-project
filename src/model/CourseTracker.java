package model;

public class CourseTracker {

	private String courseCode;
	private String courseName;
	private double points;
	private String registrationCode;
	private int year;
	private int week;
	private int studyPlaces;
	private double studyPace;
	
	 public CourseTracker(String courseCode, String courseName, double points,
             String registrationCode, int year, int week, int studyPlaces, double studyPace) {
		 
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.points = points;
		this.registrationCode = registrationCode;
		this.year = year;
		this.week = week;
		this.studyPlaces = studyPlaces;
		this.studyPace = studyPace;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public double getPoints() {
		return points;
	}

	public String getRegistrationCode() {
		return registrationCode;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getWeek() {
		return week;
	}

	public int getStudyPlaces() {
		return studyPlaces;
	}

	public double getStudyPace() {
		return studyPace;
	}

	@Override
	public String toString() {
		return courseCode + " - " + courseName + " - " + registrationCode + " - " + year;
	}

}
