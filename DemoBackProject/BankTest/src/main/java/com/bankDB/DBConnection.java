package com.bankDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	static Connection connection;

	public static Connection getConnection() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/BankDB";
			String user = "root";
			String pass = "suraj";
			
			connection = DriverManager.getConnection(url, user, pass);
			System.out.println("connection completed");
		} catch (Exception e) {
			System.out.println(e);
		}

		return connection;
	}
}
