package model;
import java.io.Serializable;

public class HouseWork implements Serializable {
	private String HouseworkName;//家事の名前
	private String HouseworkContents;//家事内容
	private String HouseworkPoint;//お手伝いポイント
	private String Icon;//未処理のアイコン
	private String IonDone;//処理済みのアイコン
	private String UserId;//ログインID
	private String IconX;//移動したアイコンのX座標
	private String IconY;	// 移動したアイコンのY座標



	public HouseWork(String houseworkName, String houseworkContents, String houseworkPoint, String icon, String ionDone,
			String userId, String iconX, String iconY) {
		this.HouseworkName = houseworkName;
		this.HouseworkContents = houseworkContents;
		this.HouseworkPoint = houseworkPoint;
		this.Icon = icon;
		this.IonDone = ionDone;
		this.UserId = userId;
		this.IconX = iconX;
		this.IconY = iconY;
	}


	public HouseWork() {
		this.HouseworkName =  "";
		this.HouseworkContents =  "";
		this.HouseworkPoint =  "";
		this.Icon =  "";
		this.IonDone =  "";
		this.UserId = "";
		this.IconX =  "";
		this.IconY =  "";
	}


	public String getHouseworkName() {
		return HouseworkName;
	}


	public void setHouseworkName(String houseworkName) {
		this.HouseworkName = houseworkName;
	}


	public String getHouseworkContents() {
		return HouseworkContents;
	}


	public void setHouseworkContents(String houseworkContents) {
		this.HouseworkContents = houseworkContents;
	}


	public String getHouseworkPoint() {
		return HouseworkPoint;
	}


	public void setHouseworkPoint(String houseworkPoint) {
		this.HouseworkPoint = houseworkPoint;
	}


	public String getIcon() {
		return Icon;
	}


	public void setIcon(String icon) {
		this.Icon = icon;
	}


	public String getIonDone() {
		return IonDone;
	}


	public void setIonDone(String ionDone) {
		this.IonDone = ionDone;
	}


	public String getUserId() {
		return UserId;
	}


	public void setUserId(String userId) {
		this.UserId = userId;
	}


	public String getIconX() {
		return IconX;
	}


	public void setIconX(String iconX) {
		this.IconX = iconX;
	}


	public String getIconY() {
		return IconY;
	}


	public void setIconY(String iconY) {
		this.IconY = iconY;
	}




}
