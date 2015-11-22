package de.hdm.tellme.server.db;

import com.google.appengine.api.rdbms.AppEngineDriver;
import java.sql.*;

public class DatenbankVerbindung {

	private static Connection con = null;

	public static Connection connection() {

		if (con == null) {

			try {
				DriverManager.registerDriver(new AppEngineDriver());

				con = DriverManager.getConnection(
						"jdbc:mysql://feltrin-immobilien.de:3306/db_tellme",
						"tellme", "M22azd0!");

			}

			catch (SQLException e1) {
				con = null;

				e1.printStackTrace();
			}
		}

		return con;
	}

}