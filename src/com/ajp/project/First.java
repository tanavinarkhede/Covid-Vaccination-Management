package com.ajp.project;

import java.io.IOException;



import java.sql.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class First extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name, email, dob, gender, city, aadhar, mobile, pass;
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		
		try {
			
			java.sql.Connection connect = DbConnection.Connectiontodatabase();
		
			name = req.getParameter("name");
			email = req.getParameter("email");
			dob = req.getParameter("dob");
			gender = req.getParameter("gender");
			city = req.getParameter("city");
			aadhar = req.getParameter("aadhar");
			mobile = req.getParameter("mob");
			pass = req.getParameter("password");
			
			
			PreparedStatement stmt=connect.prepareStatement("insert into user_details(Name, Email, DOB, Gender, City, Aadhar, MobileNO, Password) values(?,?,?,?,?,?,?,MD5(?))");
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setString(3, dob);
			stmt.setString(4, gender);
			stmt.setString(5, city);
			stmt.setString(6, aadhar);
			stmt.setString(7, mobile);
			stmt.setString(8, pass);
			int i = stmt.executeUpdate();

			if(i>0){
				res.sendRedirect("Success.html");
			}
			
			
			stmt.close();
			connect.close();
			
		} catch (Exception e) {}
		
	}

}