package database_work;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database_work._classes.Date;

public class _database_functions {
	
	// Function for creating database, it is ONLY used if the program is first time started
	public static void database_create(Connection _connection) throws SQLException {
		// Declare statement for the query
		Statement _statement = _connection.createStatement();
		
		// Execute the query to CREATE A NEW SCHEMA
		_statement.executeUpdate("CREATE SCHEMA `student_info`;");
		
		// Execute the query to CREATE NEW TABLE
		_statement.executeUpdate("CREATE TABLE STUDENT_INFO.STUDENTS ("
				+ "student_id INT NOT NULL AUTO_INCREMENT,"
				+ "student_name VARCHAR(24) NOT NULL,"
				+ "student_lastname VARCHAR(24) NOT NULL,"
				+ "student_grade FLOAT NOT NULL,"
				+ "student_birthdate DATE NOT NULL,"
				+ "PRIMARY KEY (student_id)"
				+ ");"
			);
		
		// Afterwards, close the statement so it doesnt waste memory
		_statement.close();
	}
	
	// Function to add a member to database with connection
	public static void database_addmember(Connection _connection, String name, String lastname, Float grade, Date birthdate) throws SQLException {
		// Declare statement for the query
		Statement _statement = _connection.createStatement();
		
		// Get the date
		String date = birthdate.year + "-" + birthdate.month + "-" + birthdate.day;
		// Execute the query to insert into table new data
		_statement.executeUpdate("INSERT INTO STUDENT_INFO.STUDENTS (student_name, student_lastname, student_grade, student_birthdate) VALUES"
				+ " ('" + name + "', '" + lastname + "', '" + grade + "', '" + date + "');");
		// Close the statement
		_statement.close();
	}
	
	// Function to output all data to CMD
	public static void database_cmdoutput(Connection _connection) throws SQLException {
		// Declare a new statement for the query
		Statement _statement = _connection.createStatement();
		// Get new RESULTSET which is an array of all information
		ResultSet select_query = _statement.executeQuery("SELECT * FROM STUDENT_INFO.STUDENTS");
		
		// While there is data
		while(select_query.next()) {
			// Get the query
			ResultSet st = select_query;
			// Print it to command line
			System.out.println("ID: " + st.getInt(1) 
				+ ", NAME: " + st.getString(2) 
				+ ", LASTNAME: " + st.getString(3) 
				+ ", GRADE: " + st.getFloat(4) 
				+ ", BIRTH DATE: " + st.getDate(5)
			);
		}
		// Close the statement
		_statement.close();
	}
	
	// Function to clear the database of all data
	public static void database_clear(Connection _connection) throws SQLException {
		// Declare a new statement for the query
		Statement _statement = _connection.createStatement();
		
		// Delete from the table where student_id is bigged than 0, and that is everywhere
		_statement.executeUpdate("DELETE FROM STUDENT_INFO.STUDENTS WHERE STUDENT_ID > 0");
		
		// Set the new autoincrement to start from 1
		_statement.executeUpdate("ALTER TABLE STUDENT_INFO.STUDENTS AUTO_INCREMENT = 1");
		
	}
	
	// Function to return if the database is empty
	public static Boolean database_empty(Connection _connection) throws SQLException {
		// First declare a new Bool isEmpty and set to false at start
		Boolean isEmpty = false;
		
		// Declare a new statement for the query
		Statement _statement = _connection.createStatement();
		// Get new RESULTSET which is an array of all information
		ResultSet select_query = _statement.executeQuery("SELECT * FROM STUDENT_INFO.STUDENTS");
		
		// if there is no next, it means its empty
		if(select_query.next() == false) {
			// set empty to true
			isEmpty = true;
		}
		// close the statement
		_statement.close();
		// return the isEmpty
		return isEmpty;
	}
	
	public static ArrayList<String> database_output(Connection _connection) throws SQLException {
		// Declare a new statement for the query
		Statement _statement = _connection.createStatement();
		// Get new RESULTSET which is an array of all information
		ResultSet select_query = _statement.executeQuery("SELECT * FROM STUDENT_INFO.STUDENTS");
		// Declare a new dynamic array of results, which can be shrunken and other way around
		ArrayList<String> results = new ArrayList<String>();
		
		// While there is some information
		while(select_query.next()) {
			// Declare a new resultset
			ResultSet st = select_query;
			// add that to results
			results.add(st.getInt(1) 
				+ ":   " + (st.getDate(5)).toString().replace("-", "/")
				+ ",    " + st.getFloat(4) 
				+ ",    " + st.getString(2) 
				+ " " + st.getString(3));
		}
		
		// Close the statement
		_statement.close();
		// Return the dynamic array
		return results;
	}
	
	// Function to close the database of all entries
	public static void database_close(Connection _connection) throws SQLException {
		// Close the connection
		_connection.close();
		
	}
}
