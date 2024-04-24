package com.ajp.project;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Certificate")
public class Certificate extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//int flag=0;

	public static String ps1;
	public static String mo1;
	
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		String decy=null;
		String mob = req.getParameter("mob");
		String password = req.getParameter("password");
		ps1=password;
		mo1=mob;
		try {
			MessageDigest mdEnc = MessageDigest.getInstance("MD5");
			mdEnc.update(password.getBytes(), 0, password.length());
			decy = new BigInteger(1, mdEnc.digest()).toString(16);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		try {
			
			Connection connect = DbConnection.Connectiontodatabase();
			
			String query = "SELECT * FROM user_details WHERE MobileNo = '"+mob+"' and Password = '"+decy+"';";

			Statement st = connect.createStatement();
			ResultSet rs =  st.executeQuery(query);
			
			int flag = 0;
			String vac;
			while(rs.next()){
				flag = 1;	
				vac = rs.getString("Vaccine");
				
				if(vac!=null){
					res.sendRedirect("Certificate2.html");
				}
				else{
					res.sendRedirect("VaccinatedFirst.html");
				}
				
			}
				
			if(flag!=1){
				res.sendRedirect("CredIncorrect.html");
			}
			
		}
		 catch(Exception e) {}
	}

}