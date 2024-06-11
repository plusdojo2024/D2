package model;
import java.io.Serializable;

public class CalendarComment implements Serializable{
	private String Date;//日付
	private String ChildId;//子どものID
	private String Comment ;// コメント

	public CalendarComment(String date, String childId, String comment) {
		this.Date = date;
		this.ChildId = childId;
		this.Comment = comment;
	}

	public CalendarComment() {
		this.Date = "";
		this.ChildId = "";
		this.Comment = "";
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		this.Date = date;
	}

	public String getChildId() {
		return ChildId;
	}

	public void setChildId(String childId) {
		this.ChildId = childId;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		this.Comment = comment;
	}


}
