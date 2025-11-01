package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.Controller;
import model.user.Student;

public class LoginFrame extends JFrame {

	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JLabel messageLabel;

	private Controller controller;

	public LoginFrame() {

		controller = new Controller(this);

		setTitle("Högskolan i Gävle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 400);
		setLocationRelativeTo(null);

		URL iconURL = getClass().getResource("/view/images/ikon.png");
		if (iconURL != null) {
			ImageIcon icon = new ImageIcon(iconURL);
			setIconImage(icon.getImage());
		} else {
			System.out.println("Varning: Ikonen hittades inte!");
		}

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// User name
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(new JLabel("Användarnamn:"), gbc);
		usernameField = new JTextField(15);
		gbc.gridx = 1;
		add(usernameField, gbc);

		// Password
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(new JLabel("Lösenord:"), gbc);
		passwordField = new JPasswordField(15);
		gbc.gridx = 1;
		add(passwordField, gbc);

		// Message
		messageLabel = new JLabel("", SwingConstants.CENTER);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		add(messageLabel, gbc);

		// Button
		loginButton = new JButton("Logga in");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		add(loginButton, gbc);

		// Event
		loginButton.addActionListener(e -> controller.handshakeLoginClick());
		setVisible(true);

		// Event för login-knappen
		//loginButton.addActionListener(e -> controller.handshakeLoginClick());

		// Event för Enter-tangent i passwordfältet
		passwordField.addActionListener(e -> controller.handshakeLoginClick());

	}

	public String getUsername() {
		return usernameField.getText();
	}
	
	public Student getStudent() {
		return controller.getCurrentUser();
	}

	public String getPassword() {
		return new String(passwordField.getPassword());
	}

	public void showMessage(String message, Color color) {
		messageLabel.setText(message);
		messageLabel.setForeground(color);
	}

	public void closeWindow() {
		dispose();
	}
}
