package model;

import java.io.Serializable;
import java.util.Date;

public class Calendar implements Serializable{
	private Date date;	// 日付
	private String childId;	// 子どもの識別ID
	private String houseworkName;//家事の名前

	public Calendar(Date date, String childId, String houseworkName ) {
		this.date = date;
		this.childId = childId;
		this.houseworkName = houseworkName;
	}


	public Calendar() {
		this.date = null;
		this.childId = "";
		this.houseworkName = "";
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

	public String getHouseworkName() {
		return houseworkName;
	}

	public void setHouseworkName(String houseworkName) {
		this.houseworkName = houseworkName;
	}

}
