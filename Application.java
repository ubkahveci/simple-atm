package simple_atm;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Application {

	private JLabel label;
	private JFrame frame;
	private JPanel panel;
	private JButton button;

	public static void main(String[] args) throws SQLException {

		new Application();

		boolean access = false;

		String usernameInput = JOptionPane.showInputDialog(null, "Enter your username: ");

//		// create a statement object to send queries to the database
		Statement statement = Connect.Statement();

//		execute the statement object to send the query to the database
//		(this is a selection query)
		ResultSet resultSet = statement
				.executeQuery("select * from credentials where username = '" + usernameInput + "';");

		while (resultSet.next()) {
			if (usernameInput.equals(resultSet.getString("username"))) {
				while (access == false) {
					System.out.println("Enter your password: ");
					// String passwordInput = scan.nextLine();
					String passwordInput = JOptionPane.showInputDialog(null, "Enter your password: ");
					if (passwordInput.equals(resultSet.getString("pass"))) {
						access = true;
						System.out.println("\n--- Welcome to Bank Binary, " + usernameInput + "---\n");
					} else {
						System.out.println("The password you entered does not match with the username you provided!\n");
						access = false;
					}
				}

				while (access) {
					User user = new User(usernameInput, resultSet.getString("pass"),
							Double.parseDouble(resultSet.getString("balance")));
					user.WhatToDo();
				}
			} else {
				System.out.println("Please enter a valid username! Application will now terminate!");
			}
		}

	}

}