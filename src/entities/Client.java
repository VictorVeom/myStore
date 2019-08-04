package entities;

import java.util.Date;

public class Client {
	
	private String name;
	private String email;
	private Date birhDate;
	public Client(String name, String email, Date birhDate) {
		super();
		this.name = name;
		this.email = email;
		this.birhDate = birhDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirhDate() {
		return birhDate;
	}
	public void setBirhDate(Date birhDate) {
		this.birhDate = birhDate;
	}

	
}
