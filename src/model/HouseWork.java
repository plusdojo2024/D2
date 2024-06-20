package model;
import java.io.Serializable;

public class HouseWork implements Serializable {
	private String houseworkName;//家事の名前
	private String houseworkContents;//家事内容
	private String houseworkPoint;//お手伝いポイント
	private String icon;//未処理のアイコン
	private String iconDone;//処理済みのアイコン
	private String userId;//ログインID
	private String iconX;//移動したアイコンのX座標
	private String iconY;	// 移動したアイコンのY座標
	private Boolean houseworkCheck;

	public HouseWork() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public HouseWork(String houseworkName, String houseworkContents, String houseworkPoint, String icon, String iconDone,
			String userId, String iconX, String iconY, Boolean houseworkCheck) {
		this.houseworkName = houseworkName;
		this.houseworkContents = houseworkContents;
		this.houseworkPoint = houseworkPoint;
		this.icon = icon;
		this.iconDone = iconDone;
		this.userId = userId;
		this.iconX = iconX;
		this.iconY = iconY;
		this.houseworkCheck = houseworkCheck;
	}

	public HouseWork(String houseworkName, String houseworkContents, String houseworkPoint, 
			String userId) {
		this.houseworkName = houseworkName;
		this.houseworkContents = houseworkContents;
		this.houseworkPoint = houseworkPoint;
		this.userId = userId;
	}
	
	public HouseWork(String houseworkName, String houseworkContents, String userId, Boolean houseworkCheck) {
		this.houseworkName = houseworkName;
		this.houseworkContents = houseworkContents;
		this.userId = userId;
		this.houseworkCheck = houseworkCheck;
	}
	
	/*public HouseWork() {
		this.houseworkName =  "";
		this.houseworkContents =  "";
		this.houseworkPoint =  "";
		this.icon =  "";
		this.iconDone =  "";
		this.userId = "";
		this.iconX =  "";
		this.iconY =  "";
	}*/


	public String getHouseworkName() {
		return houseworkName;
	}


	public void setHouseworkName(String houseworkName) {
		this.houseworkName = houseworkName;
	}


	public String getHouseworkContents() {
		return houseworkContents;
	}


	public void setHouseworkContents(String houseworkContents) {
		this.houseworkContents = houseworkContents;
	}


	public String getHouseworkPoint() {
		return houseworkPoint;
	}


	public void setHouseworkPoint(String houseworkPoint) {
		this.houseworkPoint = houseworkPoint;
	}


	public String getIcon() {
		return icon;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}


	public String getIconDone() {
		return iconDone;
	}


	public void setIconDone(String iconDone) {
		this.iconDone = iconDone;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getIconX() {
		return iconX;
	}


	public void setIconX(String iconX) {
		this.iconX = iconX;
	}


	public String getIconY() {
		return iconY;
	}


	public void setIconY(String iconY) {
		this.iconY = iconY;
	}

	public Boolean getHouseworkCheck() {
		return houseworkCheck;
	}

	public void setHouseworkCheck(Boolean houseworkCheck) {
		this.houseworkCheck = houseworkCheck;
	}







}
