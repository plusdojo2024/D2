package model;

import java.io.Serializable;

public class SetteiComment implements Serializable{
	private String Date;//日付
	private String ChildId;//子どものID
	private String Comment ;// コメント

	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getChildId() {
		return ChildId;
	}
	public void setChildId(String childId) {
		ChildId = childId;
	}
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}


}
