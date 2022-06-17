package simple_atm_project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class User {
	static Scanner sc = new Scanner(System.in);
	static Statement statement = Connect.Statement();

	String name;
	String password;
	double balance;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() throws NumberFormatException, SQLException {

		ResultSet resultSet = statement.executeQuery("select * from credentials where username='" + this.name + "';");
		return Double.parseDouble(resultSet.getString("balance"));
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public User(String name, String password, double balance) {
		super();
		this.name = name;
		this.password = password;
		this.balance = balance;
	}

//	public void ViewBalance() {
//		System.out.println("Balance: " + this.balance + " Euro(s)");
//	}

	public void WithdrawMoney() throws SQLException {

		boolean success = false;

		while (success == false) {
			System.out.println("How much money do you like to withdraw?\n");
			double moneyToWithdraw = Double.parseDouble(sc.nextLine());
			if (moneyToWithdraw <= this.balance) {
				this.balance -= moneyToWithdraw;
				int resultSet = statement.executeUpdate(
						"update credentials set balance=" + this.balance + " where username='" + this.name + "';");
				success = true;
				System.out.println("New balance after transaction :" + this.balance + " Euro(s)");
			} else {
				double result = this.getBalance();
				System.out.println("\nMaximum amount of money you can withdraw is " + result
						+ " Euro(s). Please try again!");
			}
		}

	}

	public void DepositMoney() throws SQLException {

		boolean success = false;

		while (success == false) {
			System.out.println("How much money do you like to deposit?\n");
			double moneyToWithdraw = Double.parseDouble(sc.nextLine());
			if (moneyToWithdraw <= 100000.0) {
				this.balance += moneyToWithdraw;
				int resultSet = statement.executeUpdate(
						"update credentials set balance=" + this.balance + " where username='" + this.name + "';");
				success = true;
				System.out.println("New balance after transaction :" + this.balance + " Euro(s)");
			} else {
				System.out.println("\nMaximum amount of money you can deposit is " + 100000 + " Euro(s)!");
			}
		}

	}

	public void ChangePassword() throws SQLException {

		System.out.println("Enter your new password: \n");
		String newPassword = sc.nextLine();
		int resultSet = statement
				.executeUpdate("update credentials set pass=" + newPassword + " where username='" + this.name + "';");
		this.password = newPassword;
		System.out.println("Your password has been changed succesfully!\n");
	}

	public void SendMoney() throws SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the amount of money that you want to send: ");
		double money = Double.parseDouble(scan.nextLine());
		System.out.println("Who would would you like to sent the money to?");
		ResultSet resultSet = statement.executeQuery("select * from credentials;");
		while(resultSet.next()) {
			System.out.println(resultSet.getString("username"));
		}
		String userInput = scan.nextLine();
		System.out.println("Sending money...\n");
	}

	public void WhatToDo() throws NumberFormatException, SQLException {
		System.out.println("What do you want to do?\n" + "1- View balance...\n" + "2- Withdraw money...\n"
				+ "3- Deposit money...\n" + "4- Change card password...\n" + "5- Send money...\n" + "6- Exit"
				+ "\n\nPlease enter the number next to the action you want to perform!");

		Scanner sc = new Scanner(System.in);
		int action = Integer.parseInt(sc.nextLine());
		System.out.println("\n");
		switch (action) {
		case 1: {
			this.getBalance();
		}
			break;
		case 2: {
			this.WithdrawMoney();
		}
			break;
		case 3: {
			this.DepositMoney();
		}
			break;
		case 4: {
			this.ChangePassword();
		}
			break;
		case 5: {
			this.SendMoney();
		}
			break;
		case 6: {
			System.exit(0);
		}
			break;
		default:
			System.out.println("Wrong input. Please enter a number between 1 and 6!");
			break;
		}
	}
}