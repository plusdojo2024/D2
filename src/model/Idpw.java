package model;

import java.io.Serializable;

public class Idpw implements Serializable {
	private String id; // ID
	private String pw; // PW
	private String pc; // PC

	public Idpw(String id, String pw, String pc) {
		this.id = id;
		this.pw = pw;
		this.pc = pc;
	}

	public Idpw() {
		this.id = "";
		this.pw = "";
		this.pc = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getPc() {
		return pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}

}
