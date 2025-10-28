package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class MainTab extends JTabbedPane {
	
    public MainTab() { 
    } 
    	
        private JPanel createLoginTab() {
    	 JPanel loginTab = new JPanel();
    	 loginTab.setLayout(null);
    	 
    	 loginTab.setBackground(Color.WHITE);
	 
    	 
    	 return loginTab;
    } 	
} 	
