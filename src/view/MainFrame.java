package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.*;

import controller.Controller;

public class MainFrame extends JFrame{
	
    private CardLayout cardLayout; // Easier to change window.
    private JPanel mainPanel;      
    private JMenu menu;
    private JPanel panel1 = new JPanel();
    private JMenuItem mainItem;
    private JMenuItem courseItem;
    private JMenuItem studentItem;
    private JMenuItem exitItem;

	public MainFrame() { // String username

		// Frame Block.
		setTitle("Högskolan i Gävle");
		
		URL iconURL = getClass().getResource("/view/images/ikon.png");
		if (iconURL != null) {
			ImageIcon icon = new ImageIcon(iconURL);
			setIconImage(icon.getImage());
		} else {
			System.out.println("Varning: Ikonen hittades inte!");
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setSize(1500,900);
		getContentPane().setBackground(new Color(50,50,50));
		setVisible(true);
		
		  // Huvudtitel
	    JLabel titleLabel = new JLabel("Välkommen");
	    titleLabel.setForeground(Color.WHITE); // vit text
	    titleLabel.setHorizontalAlignment(SwingConstants.LEFT); // vänsterjusterad
	    titleLabel.setFont(titleLabel.getFont().deriveFont(24f)); // större font
	    panel1.add(titleLabel, BorderLayout.WEST);
		
		add(panel1,BorderLayout.NORTH);
		panel1.setLayout(new BorderLayout());
		//panel1.setBackground(Color.YELLOW);
		panel1.setPreferredSize(new Dimension(0,20));
		// Sub Panels.
		
        // Main Panel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);

        // Add Windows
        mainPanel.add(new MainWindow(), "MainWindow");
        mainPanel.add(new CourseView("src/courses.txt", null), "CourseWindow");
        mainPanel.add(new StudentView("src/celebrities.txt"), "StudentWindow");

        // Menu
        JMenuBar menuBar = new JMenuBar();
        panel1.add(menuBar);

        menu = new JMenu("Menu");

        mainItem = new JMenuItem("Home");
        courseItem = new JMenuItem("Courses");
        studentItem = new JMenuItem("Students");
        exitItem = new JMenuItem("Exit");

        menu.add(mainItem);
        menu.add(courseItem);
        menu.add(studentItem);
        menu.add(exitItem);
        menuBar.add(menu);
        
        setJMenuBar(menuBar);

        // Menu Listeners - Move to controller
        mainItem.addActionListener(e -> cardLayout.show(mainPanel, "MainWindow"));
        courseItem.addActionListener(e -> cardLayout.show(mainPanel, "CourseWindow"));
        studentItem.addActionListener(e -> cardLayout.show(mainPanel, "StudentWindow"));
        
        
        
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitItem) {
			System.exit(0);
		}
	}
		
}
