package view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import model.CourseReader;
import model.CourseTracker;

public class CourseView extends JPanel {

    private JTextField searchField;
    private JList<CourseTracker> courseList;
    private DefaultListModel<CourseTracker> listModel;
    private List<CourseTracker> allCourses; // alla kurser sparas här

    public CourseView(String courseFile) {
        setLayout(new BorderLayout());

        // 🔹 Sökfält högst upp
        JPanel searchPanel = new JPanel(new BorderLayout());
        JLabel searchLabel = new JLabel("Sök kurs: ");
        searchField = new JTextField();
        searchPanel.add(searchLabel, BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        add(searchPanel, BorderLayout.NORTH);

        // 🔹 Lista över kurser
        listModel = new DefaultListModel<>();
        courseList = new JList<>(listModel);
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(courseList);
        add(scrollPane, BorderLayout.CENTER);

        // 🔹 Läs in alla kurser
        allCourses = CourseReader.readCourses(courseFile);
        setCourses(allCourses);

        // 🔹 Sökfunktion (uppdaterar i realtid)
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { filterCourses(); }
            @Override
            public void removeUpdate(DocumentEvent e) { filterCourses(); }
            @Override
            public void changedUpdate(DocumentEvent e) { filterCourses(); }
        });
    }

    private void filterCourses() {
        String text = searchField.getText().toLowerCase();

        List<CourseTracker> filtered = allCourses.stream()
            .filter(c ->
                c.getCourseName().toLowerCase().contains(text) ||
                c.getCourseCode().toLowerCase().contains(text) ||
                String.valueOf(c.getYear()).contains(text) ||
                c.getRegistrationCode().toLowerCase().contains(text)
            )
            .collect(Collectors.toList());

        setCourses(filtered);
    }

    public void setCourses(List<CourseTracker> courses) {
        listModel.clear();
        for (CourseTracker c : courses) {
            listModel.addElement(c);
        }
    }

    public CourseTracker getSelectedCourse() {
        return courseList.getSelectedValue();
    }
}
