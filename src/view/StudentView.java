package view;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.StudentReader;
import model.user.Student;

public class StudentView extends JPanel {

    private JTextField searchField;
    private JList<Student> studentList;
    private DefaultListModel<Student> listModel;
    private List<Student> allStudents; // Alla studenter sparas här

    public StudentView(String userFile) {
        setLayout(new BorderLayout());

        // 🔹 Sökfält högst upp
        JPanel searchPanel = new JPanel(new BorderLayout());
        JLabel searchLabel = new JLabel("Sök person: ");
        searchField = new JTextField();
        searchPanel.add(searchLabel, BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        add(searchPanel, BorderLayout.NORTH);

        // 🔹 Lista över studenter
        listModel = new DefaultListModel<>();
        studentList = new JList<>(listModel);
        studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(studentList);
        add(scrollPane, BorderLayout.CENTER);

        // 🔹 Läs in alla studenter
        allStudents = StudentReader.readStudentFile(userFile);
        setStudents(allStudents);

        // 🔹 Sökfunktion (uppdaterar i realtid)
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { filterStudents(); }
            @Override
            public void removeUpdate(DocumentEvent e) { filterStudents(); }
            @Override
            public void changedUpdate(DocumentEvent e) { filterStudents(); }
        });

        // 🔹 Klick på student i listan → visa popup med info
        studentList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // dubbelklick
                    Student selected = studentList.getSelectedValue();
                    if (selected != null) {
                        showStudentInfo(selected);
                    }
                }
            }
        });
    }

    // 🔹 Filtrerar studenter baserat på sökfältet
    private void filterStudents() {
        String text = searchField.getText().toLowerCase();

        List<Student> filtered = allStudents.stream()
            .filter(s ->
                s.getName().toLowerCase().contains(text) ||
                s.getEmail().toLowerCase().contains(text) ||
                String.valueOf(s.getRole()).toLowerCase().contains(text)
            )
            .collect(Collectors.toList());

        setStudents(filtered);
    }

    // 🔹 Uppdaterar listan som visas
    public void setStudents(List<Student> students) {
        listModel.clear();
        for (Student s : students) {
            listModel.addElement(s);
        }
    }

    // 🔹 Returnerar vald student
    public Student getSelectedStudent() {
        return studentList.getSelectedValue();
    }

    // 🔹 Visar info om en student i popup
    private void showStudentInfo(Student student) {
        String studentInfo = String.format(
            "Namn: %s\n" +
            "E-mailadress: %s\n" +
            "Roll: %s\n" +
            "Personnummer: %s",
            student.getName(),
            student.getEmail(),
            student.getRole(),
            student.getPersonalID()
        );

        JOptionPane.showMessageDialog(
            this,
            studentInfo,
            "Studentinformation",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
