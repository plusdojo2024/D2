package model;




import java.io.Serializable;
import java.util.Date;

public class Calendar implements Serializable{
	private Date clickDate;	// 日付
	private String clickChild;	// 子どもの識別ID
	private String clickHousework;//家事の名前

	public Calendar(Date clickDate, String clickChild, String clickHousework ) {
		this.clickDate = clickDate;
		this.clickChild = clickChild;
		this.clickHousework = clickHousework;
	}


	public Calendar() {
		this.clickDate = null;
		this.clickChild = "";
		this.clickHousework = "";
	}

	public Date getclickDate() {
		return clickDate;
	}

	public void setDate(Date clickDate) {
		this.clickDate = clickDate;
	}

	public String getclickChild() {
		return clickChild;
	}

	public void setclickChild(String clickChild) {
		this.clickChild = clickChild;
	}

	public String getclickHousework() {
		return clickHousework;
	}

	public void setHouseworkName(String clickHousework) {
		this.clickHousework = clickHousework;
	}

}
