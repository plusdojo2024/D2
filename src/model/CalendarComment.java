package model;
import java.io.Serializable;
import java.util.Date;

public class CalendarComment implements Serializable{
	private Date date;//日付
	private String childId;//子どものID
	private String comment ;// コメント
	public CalendarComment(Date date, String childId, String comment) {
		super();
		this.date = date;
		this.childId = childId;
		this.comment = comment;
	}
	public CalendarComment() {
		super();
		this.date = null;
		this.childId = "";
		this.comment = "";
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getChildId() {
		return childId;
	}
	public void setChildId(String childId) {
		this.childId = childId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}


}
