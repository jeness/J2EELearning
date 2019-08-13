package com.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
	static Connection con;
	private static final String URL = "jdbc:mysql://localhost:3306/personinfo";
	private static final String USER = "root";
	private static final String PASSWORD="123456";
	
	static { //static block, execute first, before all methods
			try {
				//1. db driver loaded
				Class.forName("com.mysql.cj.jdbc.Driver");
				//2. get connection
				con = DriverManager.getConnection(URL, USER,PASSWORD);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static Connection getConnection() {
		return con;
	}
	
	public static void main(String[] args) throws SQLException {
		
		
		try {
			//1. db driver loaded
			Class.forName("com.mysql.cj.jdbc.Driver");
			//2. get connection
			con = DriverManager.getConnection(URL, USER,PASSWORD);
			if(con != null) {
				System.out.println("Connected!!!");
			}
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select user_name, age from personaldata");
			
			while(rs.next()) {
				System.out.println(rs.getString("user_name") + "," + rs.getInt("age"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
