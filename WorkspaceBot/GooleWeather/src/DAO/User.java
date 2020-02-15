package DAO;

public class User {

	private Long id;
	private String name;
	private String surname;
	private boolean admin;
	private boolean pending;

	public User(Long id, String name, String surname, boolean admin, boolean pending) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.admin = admin;
		this.pending = pending;
	}

	public Long getUserID() {
		return id;
	}

	public void setUserID(Long userID) {
		this.id = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String nome) {
		this.name = nome;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String cognome) {
		this.surname = cognome;
	}

	public boolean getAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean getPending() {
		return pending;
	}

	public void setPending(boolean pending) {
		this.pending = pending;
	}

	@Override
	public String toString() {
		return "User [userID=" + id + ", nome=" + name + ", cognome=" + surname + ", Admin=" + admin + ", pending="
				+ pending + "]";
	}

}
