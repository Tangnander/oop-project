package controller;

import java.awt.Color;

import javax.swing.JOptionPane;

import model.PermissionCheck;
import view.LoginFrame;
import view.MainFrame;
import model.user.Student;

public class Controller {

	private PermissionCheck pCheck;
	private LoginFrame logInF;
	
	public Controller(LoginFrame login) {
		this.logInF = login;
		this.pCheck = new PermissionCheck();
	}
	public void handshakeLoginClick() {
		String username = logInF.getUsername();
		String password = logInF.getPassword();
		String name = logInF.getName();
		
		if(HandshakeLogin(username, password)) {
			//logInF.showMessage("Loggar in...", Color.GREEN);
			//JOptionPane.showMessageDialog(logInF, "Welcome, " + pCheck.getUserRole() + "!");
			openMainWindow();
		}
	}
	
	// login logic.
	public boolean HandshakeLogin(String username, String password) {
		pCheck.setRequestedLoginInfo(username, password);
		if (pCheck.checkPassword()) {
			Student loggedInUser = pCheck.login();
			System.out.println("Login correct: " + pCheck.getUserRole());
			System.out.println("Current user: " + loggedInUser);
			return true;
		} else {
			System.out.println("Wrong username or password!");
			return false;
		}
	}
    private void openMainWindow() {
        logInF.closeWindow();
        new MainFrame(); 
    }
    
	public String getLoggedUserRole() {
		return pCheck.getUserRole();
	}
	
	public Student getCurrentUser() {
		return pCheck.login();
	}
}	