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

@WebServlet("/Certificate2")
public class Certificate2 extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//int flag=0;
	String mob = Certificate.mo1;
	String password = Certificate.ps1;
	String name, email, gender, city, aadhar, dob, vaccine, dose1, dose2;
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		String dose = req.getParameter("dose1");
		String decy=null;
		
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
			
			String query = "SELECT * FROM user_details WHERE MobileNo = '"+mob+"';";

			Statement st = connect.createStatement();
			ResultSet rs =  st.executeQuery(query);
			
			 while(rs.next()){
				 name = rs.getString("Name");
			     email = rs.getString("Email");
			     dob = rs.getString("DOB");
			     gender = rs.getString("Gender");
			     city = rs.getString("City");
			     aadhar = rs.getString("Aadhar");
			     mob = rs.getString("MobileNo");
			     vaccine = rs.getString("Vaccine");
			     dose1 = rs.getString("Dose1");
			     dose2 = rs.getString("Dose2");
			 }
			 
			 if(dose.equals("Dose1")){
				 while(rs.next()){
					 name = rs.getString("Name");
				     email = rs.getString("Email");
				     dob = rs.getString("DOB");
				     gender = rs.getString("Gender");
				     city = rs.getString("City");
				     aadhar = rs.getString("Aadhar");
				     mob = rs.getString("MobileNo");
				     vaccine = rs.getString("Vaccine");
				     dose1 = rs.getString("Dose1");
				     dose2 = rs.getString("Dose2");
				 }
				 
				 res.getWriter().println("<html><head>");
				 res.getWriter().println("<style type='text/css'>");
				 
				 res.getWriter().println("<link href=assets/vendor/animate.css/animate.min.css rel=stylesheet><link href=assets/vendor/aos/aos.css rel=stylesheet><link href=assets/vendor/bootstrap/css/bootstrap.min.css rel=stylesheet><link href=assets/vendor/bootstrap-icons/bootstrap-icons.css rel=stylesheet><link href=assets/vendor/boxicons/css/boxicons.min.css rel=stylesheet><link href=assets/vendor/glightbox/css/glightbox.min.css rel=stylesheet><link href=assets/vendor/swiper/swiper-bundle.min.css rel=stylesheet>");
				 
				 res.getWriter().println("body, html {margin: 0; padding: 0;}");
				 res.getWriter().println("body {color: black;display: table;font-family: Georgia, serif;font-size: 24px; text-align: center;}");
				 res.getWriter().println(" .container {border: 20px solid tan; width: 800px;height: 600px; display: table-cell;vertical-align: middle; }");
				 res.getWriter().println(".logo {color: tan;top: 10px;}");
				 res.getWriter().println(".marquee {color: tan;font-size: 48px; margin: 20px;top: 50px;}");
				 res.getWriter().println(".assignment { bottom: 50px; rgb(32, 34, 51) }");
				 res.getWriter().println(".person {border-bottom: 2px solid black;font-size: 18px;font-style: italic;margin: 20px auto; width: 400px;}");
				 res.getWriter().println(".reason {margin: 20px;}");
				 res.getWriter().println("button[id=btn]{text-decoration: none; padding: 10px; color: green;cursor: pointer;font-size:20px;font-weight:bold; width: max-content");

				 res.getWriter().println(" </style></head>");
				 res.getWriter().println(" <body> <div class=container>");
				 res.getWriter().println("<div class=logo>Ministry of Helth and Family Welfare Government of India</div>");
				 res.getWriter().println("<div class=marquee>Certificate for COVID-19 Vaccination</div>");
				 res.getWriter().println("<div class=assignment>First Dose<br>Beneficiary Details</div>");
				 res.getWriter().println("<div class=person>Name: "+name+"</div>");
				 res.getWriter().println("<div class=person>Address: "+city+"</div>");
				 res.getWriter().println("<div class=person>Gender: "+gender+"</div>");
				 res.getWriter().println("<div class=person>Aadhar: "+aadhar+"</div>");
				 res.getWriter().println("<div class=person>First Dose: "+vaccine+"</div>");
				 
				 res.getWriter().println("<div class=reason>Together, India will defet COVID-19</div>");
				 res.getWriter().println("<br><button type=button id='btn' class=btn btn-success> <a href=index.html data-bs-toggle=tooltip data-bs-placement=top title=Sign if alredy has an account>Home Page</a></button>");

				 res.getWriter().println("</div>");
				 
				 res.getWriter().println("</body></html>");				 
			 }
			 
			 else{
				 String query1 = "SELECT * FROM user_details WHERE MobileNo = '"+mob+"' and Password = '"+decy+"';";
				 Statement st1 = connect.createStatement();
					ResultSet rs1 =  st1.executeQuery(query);
				 int flag = 0;
					String vac;
					while(rs1.next()){
						flag = 1;	
						vac = rs1.getString("Dose2");
						
						if(vac!=null){
							res.getWriter().println("<html><head>");
							 res.getWriter().println("<style type='text/css'>");
							 res.getWriter().println("body, html {margin: 0; padding: 0;}");
							 res.getWriter().println("body {color: black;display: table;font-family: Georgia, serif;font-size: 24px; text-align: center;}");
							 res.getWriter().println(" .container {border: 20px solid tan; width: 800px;height: 600px; display: table-cell;vertical-align: middle; }");
							 res.getWriter().println(".logo {color: tan;top: 10px;}");
							 res.getWriter().println(".marquee {color: tan;font-size: 48px; margin: 20px;top: 50px;}");
							 res.getWriter().println(".assignment { bottom: 50px; rgb(32, 34, 51) }");
							 res.getWriter().println(".person {border-bottom: 2px solid black;font-size: 18px;font-style: italic;margin: 20px auto; width: 400px;}");
							 res.getWriter().println(".reason {margin: 20px;}");
							 res.getWriter().println("button[id=btn]{text-decoration: none; padding: 10px; color: green;cursor: pointer;font-size:20px;font-weight:bold; width: max-content");
							 res.getWriter().println(" </style></head>");
							 res.getWriter().println(" <body> <div class=container>");
							 res.getWriter().println("<div class=logo>Ministry of Helth and Family Welfare Government of India</div>");
							 res.getWriter().println("<div class=marquee>Certificate for COVID-19 Vaccination</div>");
							 res.getWriter().println("<div class=assignment>Second Dose<br>Beneficiary Details</div>");
							 res.getWriter().println("<div class=person>Name: "+name+"</div>");
							 res.getWriter().println("<div class=person>Address: "+city+"</div>");
							 res.getWriter().println("<div class=person>Gender: "+gender+"</div>");
							 res.getWriter().println("<div class=person>Aadhar: "+aadhar+"</div>");
							 res.getWriter().println("<div class=person>Second Dose: "+vaccine+"</div>");

							 res.getWriter().println("<div class=reason>Together, India will defet COVID-19</div>");
							 res.getWriter().println("<br><button type=button id='btn' class=btn btn-success> <a href=index.html data-bs-toggle=tooltip data-bs-placement=top title=Sign if alredy has an account>Home Page</a></button>");

							 res.getWriter().println("</div>");
							 
							 res.getWriter().println("</body></html>");;
						}
						else{
							res.sendRedirect("SecFirst.html");
						}
						
					}
						
					if(flag!=1){
						res.sendRedirect("CredIncorrect.html");
					}
				 
			 }			 
			
		}
		 catch(Exception e) {}
	}

}