package de.hdm.tellme.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatenbankVerbindung {
	private static boolean UseLocalConnection = true;
	private static Connection getNewConnection() {
		
		
		Connection con = null;
		try {
			if (UseLocalConnection) {
				con = DriverManager.getConnection("jdbc:mysql://feltrin-immobilien.de:3306/db_tellme", "tellme", "M22azd0!");

			} else {
				con = DriverManager.getConnection("jdbc:google:rdbms://xx:xx/xx", "root", "");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;

}
}
