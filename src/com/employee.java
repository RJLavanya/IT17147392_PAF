package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class employee {

	public Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gbcompany", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String insertEmployee(String name, String address, String contact, String dob) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "insert into employee(`employeeId`,`employeeName`,`employeeAddress`,`employeeContact`,`employeeDob`)"
					+ "values ( ?,  ?,  ?,  ?,  ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, contact);
			preparedStmt.setString(5, dob);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newEmployees = readEmployee();
			output = "{\"status\":\"success\", \"data\": \"" + newEmployees + "\"}";
			
		} catch (Exception e) {
			
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting the Employee.\"}";
			System.err.println(e.getMessage());
		}
		return output;

	}
	
	public String readEmployee() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Employee Name</th><th>Address</th><th>Contact</th><th>Date of Birth</th><th>Update</th><th>Remove</th></tr>"; 
			
			String query = "select * from employee";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) {
				String employeeId = Integer.toString(rs.getInt("employeeId"));
				String employeeName = rs.getString("employeeName");
				String employeeAddress = rs.getString("employeeAddress");
				String employeeContact = rs.getString("employeeContact");
				String employeeDob = rs.getString("employeeDob");
				
				// Add into the html table
				
				
				
				output += "<tr><td><input id='hidEmployeeIDUpdate' name='hidEmployeeIDUpdate' type='hidden' value='"+ employeeId + "'>" + employeeName + "</td>";
				output += "<td>" + employeeAddress + "</td>";
				output += "<td>" + employeeContact + "</td>";
				output += "<td>" + employeeDob + "</td>";
				// buttons
				
				   output += "<td><input name='btnUpdate' type='button' value='Update'class='btnUpdate btn btn-secondary'></td>"
	                        + "<td><input name='btnRemove'type='button' value='Remove'class='btnRemove btn btn-danger' data-employeeid='"
	                        + employeeId + "'>" + "</td></tr>";
				
				
			}
			con.close();
			
			// Complete the html table
			output += "</table>";
		} 
		catch (Exception e) {
			output = "Error while reading the Employees.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String updateEmployee(String employeeId, String name, String address, String contact, String dob) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE employee SET employeeName=?,employeeAddress=?,employeeContact=?,employeeDob=? WHERE employeeId=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, address);
			preparedStmt.setString(3, contact);
			preparedStmt.setString(4, dob);
			preparedStmt.setInt(5, Integer.parseInt(employeeId));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newEmployees = readEmployee();
			output = "{\"status\":\"success\", \"data\": \"" + newEmployees + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the Employee.\"}";
					System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteEmployee(String employeeId) {
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			
			// create a prepared statement
			String query = "delete from employee where employeeId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(employeeId));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newEmployees = readEmployee();
			output = "{\"status\":\"success\", \"data\": \"" + newEmployees + "\"}";
		} 
		catch (Exception e) 
		{
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the Employee.\"}";
					System.err.println(e.getMessage());
		}
		return output;
	}

}
