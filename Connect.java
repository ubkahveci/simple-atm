package simple_atm_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {

	public static Statement Statement() {

		String url = "jdbc:mysql://localhost:3306/user_database";

		Statement statement = null;
		try {
			// establish connection object using credentials
			Connection conn = DriverManager.getConnection(url, "root", "Revolution90.");

			// create a statement object to send queries to the database
			statement = conn.createStatement();

		} catch (SQLException e) {
			System.out.println(e);
		}

		return statement;

	}

}
