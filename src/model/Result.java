package model;

import java.io.Serializable;

public class Result implements Serializable {
	private String message;		// メッセージ

	public Result() {
		this(null);
	}

	public Result( String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
