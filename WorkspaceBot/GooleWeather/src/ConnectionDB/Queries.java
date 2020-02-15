package ConnectionDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.User;

public class Queries {

	/**
	 * ritorna una lista di tutti gli utenti registrati
	 * 
	 * @return
	 */
	public synchronized static List<User> getUsers() {
		List<User> users = new ArrayList<>();
		try (final Connection c = PostgreSQLJDBC.getConnectionDb();
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");) {

			while (rs.next()) {
				users.add(new User(rs.getLong("id"), rs.getString("name"), rs.getString("surname"), rs.getBoolean(
						"admin"), rs.getBoolean("pending")));
			}
			// rs.close();
			// stmt.close();
			// c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return users;
	}

	/**
	 * ritorna una lista di tutti gli utenti amministatori
	 * 
	 * @return
	 */
	public synchronized static List<User> getAdminUsers() {
		List<User> users = new ArrayList<>();
		try (final Connection c = PostgreSQLJDBC.getConnectionDb();
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM USERS WHERE users.admin = true");) {

			while (rs.next()) {
				users.add(new User(rs.getLong("id"), rs.getString("name"), rs.getString("surname"), rs.getBoolean(
						"admin"), rs.getBoolean("pending")));
			}
			// rs.close();
			// stmt.close();
			// c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return users;
	}

	/**
	 * ritorna una lista di tutti gli utenti normali
	 * 
	 * @return
	 */
	public synchronized static List<User> getNormalUsers() {
		List<User> users = new ArrayList<>();
		try (final Connection c = PostgreSQLJDBC.getConnectionDb();
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM USERS WHERE users.admin = false");) {

			while (rs.next()) {
				users.add(new User(rs.getLong("id"), rs.getString("name"), rs.getString("surname"), rs.getBoolean(
						"admin"), rs.getBoolean("pending")));
			}
			// rs.close();
			// stmt.close();
			// c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return users;
	}

	/**
	 * Dato userId controlla se l'utente è stato registrato
	 * 
	 * @param userId
	 * @return
	 */
	public synchronized static User getUserIfExsist(Long userId) {
		User utente = null;
		try (final Connection c = PostgreSQLJDBC.getConnectionDb();
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM public.users where users.id= " + userId);) {
			if (rs.next())
				utente = new User(rs.getLong("id"), rs.getString("name"), rs.getString("surname"), rs.getBoolean(
						"admin"), rs.getBoolean("pending"));
			// rs.close();
			// stmt.close();
			// c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		return utente;
	}

	// ######################################### INSERT ############################################################

	public synchronized static void RegisterPendings(Long id) {
		String sql = "UPDATE users \n" + "SET pending = false \n" + "WHERE id = " + id + " ;";
		try (final Connection c = PostgreSQLJDBC.getConnectionDb(); Statement stmt = c.createStatement();) {
			stmt.executeUpdate(sql);
			// stmt.close();
			// c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records updated successfully");
	}

	/**
	 * Inserisce uno nuovo user
	 * 
	 * @param user
	 */
	public synchronized static void InsertUser(User user) {
		String sql = "INSERT INTO public.users(id, name, surname, admin, pending) VALUES( " + user.getUserID() + ", '"
				+ user.getName() + "', '" + user.getSurname() + "' ,'" + user.getAdmin() + "','" + user.getPending()
				+ "')";
		try (final Connection c = PostgreSQLJDBC.getConnectionDb(); Statement stmt = c.createStatement();) {
			stmt.executeUpdate(sql);
			// stmt.close();
			// c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	/**
	 * Cancella la tabela UserAdmin
	 * 
	 * @param userAdmin
	 */
	public synchronized static void deleteTableUserAdmin() {
		final Connection c = PostgreSQLJDBC.getConnectionDb();
		Statement stmt = null;
		try {

			stmt = c.createStatement();
			String sql = "DELETE FROM public.adminuser";
			stmt.executeUpdate(sql);
			// stmt.close();
			// c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Deleted table useradmin done successfully");
	}

}
