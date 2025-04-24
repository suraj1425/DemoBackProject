package com.bank;

import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.bankDB.DBConnection;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/user-registration")
public class UserRegistration extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name=req.getParameter("name");
        
		String dobdate = req.getParameter("dob");
        
        String gender = req.getParameter("gender");
        
        String country = req.getParameter("country");
        
        String maritalstatus = req.getParameter("marital-status");
        
        long contact=Long.parseLong(req.getParameter("contact-number"));
        
        String email=req.getParameter("email");
        
        String residentialaddress=req.getParameter("residential-address");
        String permanentaddress=req.getParameter("permanent-address");
        String[] creation=name.split(" ");
        
        String password=creation[0]+dobdate.substring(0, 4);
        
        System.out.println("first last");
        String insert = "INSERT INTO userdata ( full_name, date_of_birth,gender,nationality,marital_status,contact_number,email,residential_address,permanent_address,password) VALUES (?,?,?,?,?,?,?,?,?,?)";
        
        try {
        	System.out.println("first");
        	Connection connection = DBConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, name);
			statement.setString(2,dobdate);
			statement.setString(3,gender);
			statement.setString(4,country);
			statement.setString(5,maritalstatus);
			statement.setLong(6,contact);
			statement.setString(7,email);
			statement.setString(8,residentialaddress);
			statement.setString(9,permanentaddress);
			statement.setString(10,password);
			
			statement.executeUpdate();
			System.out.println("last");
			
			connection.close();
			statement.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        PrintWriter out=resp.getWriter();
        resp.setContentType("text/html");
        out.print("<h2>Registration Completed</h2>");
        
        RequestDispatcher rd=req.getRequestDispatcher("/index.html");
        rd.include(req, resp);
        
        
        
        
        
	}
}
