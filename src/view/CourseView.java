package view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.Controller;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;
import model.CourseReader;
import model.CourseTracker;
import model.user.Student;

import java.io.File;
import java.io.IOException;

public class CourseView extends JPanel {

	private JTextField searchField;
	private JList<CourseTracker> courseList;
	private DefaultListModel<CourseTracker> listModel;
	private List<CourseTracker> allCourses; // alla kurser sparas här
	private Student currentStudent;
	

	public CourseView(String courseFile, Student loggedInStudent) {
		this.currentStudent = loggedInStudent; 
		setLayout(new BorderLayout());

		// 🔹 Sökfält högst upp
		JPanel searchPanel = new JPanel(new BorderLayout());
		JLabel searchLabel = new JLabel("Sök kurs: ");
		searchField = new JTextField();
		searchPanel.add(searchLabel, BorderLayout.WEST);
		searchPanel.add(searchField, BorderLayout.CENTER);
		add(searchPanel, BorderLayout.NORTH);

		// Lista över kurser
		listModel = new DefaultListModel<>();
		courseList = new JList<>(listModel);
		courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(courseList);
		add(scrollPane, BorderLayout.CENTER);

		// Läs in alla kurser
		allCourses = CourseReader.readCourses(courseFile);
		setCourses(allCourses);

		// Sökfunktion (uppdaterar i realtid)
		searchField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				filterCourses();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				filterCourses();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				filterCourses();
			}
		});

		// Klick på kurs i listan → visa popup med info
		courseList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Dubbelklick eller enkelklick – välj vad du vill
				if (e.getClickCount() == 2) { // dubbelklick
					CourseTracker selected = courseList.getSelectedValue();
					if (selected != null) {
						showCourseInfo(selected);
					}
				}
			}
		});
	}

	private void filterCourses() {
		String text = searchField.getText().toLowerCase();

		List<CourseTracker> filtered = allCourses.stream()
				.filter(c -> c.getCourseName().toLowerCase().contains(text)
						|| c.getCourseCode().toLowerCase().contains(text) || String.valueOf(c.getYear()).contains(text)
						|| c.getRegistrationCode().toLowerCase().contains(text))
				.collect(Collectors.toList());

		setCourses(filtered);
	}

	public void setCourses(List<CourseTracker> courses) {
		listModel.clear();
		for (CourseTracker c : courses) {
			listModel.addElement(c);
		}
	}
	
	public void setCurrentUser(Student student) {
		currentStudent = student;
	}

	public CourseTracker getSelectedCourse() {
		return courseList.getSelectedValue();
	}

	private void showCourseInfo(CourseTracker course) {
	
		String info = String.format(
				"Kursinformation:\n\n" + "Kursnamn: %s\n" + "Kurskod: %s\n" + "Anmälningskod: %s\n" + "År: %d\n"
						+ "Vecka: %d\n" + "Studieplatser: %d\n" + "Högskolepoäng: %.1f hp\n" + "Studietakt: %.0f%%",
				course.getCourseName(), course.getCourseCode(), course.getRegistrationCode(), course.getYear(),
				course.getWeek(), course.getStudyPlaces(), course.getPoints(), course.getStudyPace());
		
		// Definiera knappar
		Object[] options = { "Registrera", "Stäng" };

		int choice = JOptionPane.showOptionDialog(this, info, "Kursinformation", JOptionPane.YES_NO_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, options[1] // default button
		);

		// Hantera knapptryck
		if (choice == JOptionPane.YES_OPTION) {
			try {
				File myObj = new File("%s.txt", currentStudent.getUsername()); // Create File object
				if (myObj.createNewFile()) { // Try to create the file
					System.out.println("File created: " + myObj.getName());
				} else {
					System.out.println("File already exists.");
				}
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace(); // Print error details
			}
		}

	}
}
