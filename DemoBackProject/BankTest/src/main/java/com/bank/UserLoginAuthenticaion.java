package com.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bankDB.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login-page")
public class UserLoginAuthenticaion extends HttpServlet {

	Connection connection;
	PreparedStatement statement;
	ResultSet set;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
				String dataemail="";
		String datapassword="";

		String email = req.getParameter("username");
		String password = req.getParameter("password");
		try {
			connection = DBConnection.getConnection();

			String selectquery = "SELECT email, password FROM userdata WHERE email ='" + email + "'";
//			System.out.println(selectquery);
			statement = connection.prepareStatement(selectquery);

			set = statement.executeQuery();
			
			
			PrintWriter out=resp.getWriter();
	        resp.setContentType("text/html");
	        
	        
	        
	        
			if (set.next()) {
				dataemail = set.getString(1);
				datapassword = set.getString(2);

				if (datapassword.equals(password)) {

					System.out.println("Login Successfull");
					System.out.println("if if email " + dataemail + "pass " + datapassword + "pass enter : " + password);
					closeConnection();
				} else {
					System.out.println("password is worng");
					out.print("<h2>password is worng</h2>");
					RequestDispatcher rd=req.getRequestDispatcher("/index.html");
					rd.include(req, resp);
					System.out.println("if else email " + dataemail + "pass" + datapassword + "pass enter : " + password);
					closeConnection();
				}

			} else {
				System.out.println("Wrong Email");
				out.print("<h2>Wrong Email</h2>");
				RequestDispatcher rd=req.getRequestDispatcher("/index.html");
				rd.include(req, resp);

				System.out.println(" else email " + dataemail + "pass" + datapassword + "pass enter : " + password);
				closeConnection();
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public void closeConnection() {
		try {
			statement.close();
			set.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
