package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainWindow extends JPanel {
	
	// Main Panels.
	private JPanel panel1 = new JPanel();
	private JPanel panel3 = new JPanel();
	
    public MainWindow() {
        setLayout(new BorderLayout());
        
		add(panel1,BorderLayout.WEST);
		add(panel3,BorderLayout.CENTER);
		
		// Panels Block.
		panel1.setBackground(Color.BLUE);
		panel1.setPreferredSize(new Dimension(200,0));
		panel3.setBackground(Color.RED);
		
    }
}