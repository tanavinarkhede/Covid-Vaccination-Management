package com.ajp.project;

import java.io.IOException;


import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Dose")
public class Vaccine extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("resource")
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		String mob=Login.mob;
		String dd = "Vaccinated";
		
		String dose = req.getParameter("dose1");

		String vacc = req.getParameter("vaccine");
		
		 String query = null;
		
		try {
			java.sql.Connection connect = DbConnection.Connectiontodatabase();
			Statement st = connect.createStatement();
			ResultSet r;			
			
			//String sql = "Select Dose1, Dose2 from user_details";
			//ResultSet r = st.executeQuery(sql);
			
			
			if(dose!=null){
				if(dose.equals("Dose1")){
					String update = vacc;
					
					query ="UPDATE user_details SET Dose1 = '"+dd+"', Vaccine = '"+update+"' WHERE MobileNo ="+"'"+mob+"'";
				}
				else if(dose.equals("Dose2")){
//					System.out.println("hi");
					String sql = "Select Vaccine from user_details WHERE MobileNo ="+"'"+mob+"'";
					r = st.executeQuery(sql);
//					System.out.println("ins");
					while(r.next()){
						//String dose1 = r.getString("Dose1");	
						String Vaccination = r.getString("Vaccine");
						
						if(Vaccination==null){
							
							res.sendRedirect("TakeDose1.html");
						}
						else{	
							String sql1 = "Select Vaccine from user_details WHERE MobileNo ="+"'"+mob+"'";
							r = st.executeQuery(sql1);
							while(r.next()){
								String d = r.getString("Vaccine");
								if(d.equals(vacc)){
									query ="UPDATE user_details SET Dose2 = '"+dd+"' WHERE MobileNo ="+"'"+mob+"'";
								}
								else{
									res.sendRedirect("Proper_Vaccine.html");
								}
							}
														
						}
					}
					
				}
			}
			
			
			if(query!=null){
				int rs = st.executeUpdate(query);
				res.sendRedirect("Done.html");
			}
			else{
//				System.out.println("Same");	
				res.sendRedirect("Main.html");
			}
						
		} catch (Exception e) {}
	}

}
