package model;
import java.io.Serializable;
import java.util.Date;

public class CalendarComment implements Serializable{
	private Date date;//日付
	private String userId;//子どものID
	private String comment ;// コメント
	public CalendarComment(Date date, String userId, String comment) {
		super();
		this.date = date;
		this.userId = userId;
		this.comment = comment;
	}
	public CalendarComment() {
		super();
		this.date = null;
		this.userId = "";
		this.comment = "";
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUserId() {
		return userId;
	}
	public void setChildId(String userId) {
		this.userId = userId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}


}
