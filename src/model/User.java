package model;

import java.io.Serializable;

public class User implements Serializable {
	private String userId;
	private String password;
	private String passcord;

	public User() {
		super();
		this.userId = "";
		this.password = "";
		this.passcord = "";
	}

	public User(String userId, String password, String passcord) {
		super();
		this.userId = userId;
		this.password = password;
		this.passcord = passcord;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasscord() {
		return passcord;
	}

	public void setPasscord(String passcord) {
		this.passcord = passcord;
	}


}
