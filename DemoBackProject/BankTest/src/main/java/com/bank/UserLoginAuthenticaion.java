package com.bank;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.Session;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;


import com.bankDB.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login-page")
@WebListener
public class UserLoginAuthenticaion extends HttpServlet implements ServletContextListener{

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

			String selectquery = "SELECT account_no , full_name , date_of_birth , gender, email, password FROM userdata WHERE email ='" + email + "'";
//			System.out.println(selectquery);
			statement = connection.prepareStatement(selectquery);

			set = statement.executeQuery();
			
			
			PrintWriter out=resp.getWriter();
	        resp.setContentType("text/html");
	        
	        
	        
	        
			if (set.next()) {
				dataemail = set.getString("email");
				datapassword = set.getString("password");

				if (datapassword.equals(password)) {

					System.out.println("Login Successfull");
					
					UserInfo user=new UserInfo(set.getInt("account_no"),set.getString("email"),set.getString("gender"),set.getString("full_name"),set.getString("date_of_birth"));
					closeConnection();

					
					HttpSession session=req.getSession();
					session.setAttribute("username",user);
					
					
					
					
					
					
					
					
					resp.sendRedirect("jsp/profile.jsp");
					
					System.out.println("if if email " + dataemail + "pass " + datapassword + "pass enter : " + password);
					
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            AbandonedConnectionCleanupThread.checkedShutdown();

            
            } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // No init logic needed for this case
    }
}
	
	
	
	
	
	
	
	

