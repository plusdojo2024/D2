package model;

import java.io.Serializable;

public class Calendar implements Serializable{
	private String date;	// 日付
	private String ChildId;	// 子どもの識別ID
	private String HouseworkName;//家事の名前

	public Calendar(String date, String ChildId, String HouseworkName ) {
		this.date = date;
		this.ChildId = ChildId;
		this.HouseworkName = HouseworkName;
	}


	public Calendar() {
		this.date = "";
		this.ChildId = "";
		this.HouseworkName = "";
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getChildId() {
		return ChildId;
	}

	public void setChildId(String childId) {
		ChildId = childId;
	}

	public String getHouseworkName() {
		return HouseworkName;
	}

	public void setHouseworkName(String houseworkName) {
		HouseworkName = houseworkName;
	}

}
