package com.ajp.project;
import java.io.IOException;
import java.sql.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/show")
public class Data extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

		public void service(HttpServletRequest req, HttpServletResponse res) throws IOException{
			
			try{
			
				Connection connect = DbConnection.Connectiontodatabase();
				String query = "SELECT Name, Email, DOB, Gender, city, Aadhar, MobileNO, Vaccine, Dose1, Dose2 from user_details";
				
				Statement st = connect.createStatement();
				ResultSet rs =  st.executeQuery(query);

				res.getWriter().println("<html>");
				res.getWriter().println("<head>");
				res.getWriter().println("<title>Registration Data </title>");
				res.getWriter().println("<style>");		
//				res.getWriter().println("body{ background-color: gray; background: url(AdminL.jpeg) no-repeat center center fixed;    } ");
				
				res.getWriter().println(" #one{ position: absolute; top: 65%; left: 41%}");
				res.getWriter().println(" #two{ position: absolute; top: 65%; left: 51%}");	
				res.getWriter().println(" #name{width=250px}");	
				
				res.getWriter().println(" .com{color: white; height: 30px; text-decoration: none; background-color: cyan; color:black; border-radius: 8px;  }");				
				res.getWriter().println("<html><head><style>button[type=submit]{padding:5px;color: blue; cursor: pointer;font-size:20px;font-weight:bold;width:100px}"
						+ "button[id=btn]{padding: 10px; color: green;cursor: pointer;font-size:20px;font-weight:bold; width: max-content}</style></head><body><br><h1 align='center' >Vaccinated Users</h1><br>");
				res.getWriter().println("<table align='center' border='1'><tr><td>Name</td><td>Email</td><td>DOB</td>"
						+ "<td>Gender</td><td>City</td><td>Aadhar</td><td>Mobile NO</td><td>Vaccine</td><td>First Dose</td><td>Second Dose</td></tr>");
				
				
				int fl=0;
			    while(rs.next()){
			    	String name = rs.getString("Name");
			    	String email = rs.getString("Email");
			    	String dob = rs.getString("DOB");
			    	String gender = rs.getString("Gender");
			    	String city = rs.getString("City");
			    	String aadhar = rs.getString("Aadhar");
			    	String mob = rs.getString("MobileNo");
			    	String Vaccination = rs.getString("Vaccine");
			    	String dose1 = rs.getString("Dose1");
			    	String dose2 = rs.getString("Dose2");
			    	fl=1;
			    	if(Vaccination!=null)
			    	res.getWriter().println("<tr><td class=new>"+name+"</td><td class=new>"+email+"</td><td class=new>"+dob+"</td><td class=new>"+gender+"</td><td class=new>"+city+"</td><td class=new>"+aadhar+"</td><td class=new>"+mob+"</td><td class=new>"+Vaccination+"</td><td class=new>"+dose1+"</td><td class=new>"+dose2+"</td></tr>");
			    	
			    }
			    if(fl==0){
			    	res.sendRedirect("FDFail.html");
			    }
			   
			    res.getWriter().println(" <button id=one class=com type=button class=btn btn-primary> <a class=com href=index.html>Go To Home </a></button>");
			    res.getWriter().println(" <button id=two class=com type=button class=btn btn-primary> <a class=com href=Info.jsp>Go To Admin Panel </a></button>");
			  
			    res.getWriter().println("</body>");
			    res.getWriter().println("</html>");
				
			}catch(Exception e){}
			
		}
			
	}	