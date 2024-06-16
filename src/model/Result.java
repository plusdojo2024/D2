package model;

import java.io.Serializable;

public class Result implements Serializable {
	private String title;
	private String message;		// メッセージ
	private String backTo;		// 戻り先

	public Result() {
		this(null,null,null);
	}

	public Result(String title, String message, String backTo) {
		super();
		this.title = title;
		this.message = message;
		this.backTo = backTo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBackTo() {
		return backTo;
	}

	public void setBackTo(String backTo) {
		this.backTo = backTo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
