package ConnectionDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreSQLJDBC {

	public static Connection getConnectionDb() {
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");

			c = DriverManager.getConnection(
					"jdbc:postgresql://ec2-54-247-125-38.eu-west-1.compute.amazonaws.com:5432/d4h6o6i3q0t2f8",
					"prnacrarxzlowv", "5b74d6be7bb630b4c0e87c31364164f20724b400766ad4e99da5f4cc95105ff7");

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		return c;
	}
}