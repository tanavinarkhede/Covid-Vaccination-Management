package com.ajp.project;

import java.sql.*;

public class DbConnection{
	public static Connection Connectiontodatabase() throws Exception{

	
		String url="jdbc:mysql://localhost:3306/covid?autoReconnect=true&useSSL=false&allowPublicKeyRetrival=true";
		String user="root";
		String password = "Tiger@123";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection connect = DriverManager.getConnection(url,user,password);
		
		
		return connect;
		
	}
}