package model;

import java.io.Serializable;

public class Child implements Serializable {
	private String ChildId;//子どものID
	private String ChildName;//子どもの名前
	private String ChildPicture;//子どもの写真
	private String ChildAge;//子どもの年齢
	private String ChildFavorite;//子どもの好きな物
	private String ChildSchool;//子どもの学校
	private String UserId;//ユーザーID
	private String RewardUmu;//報酬の有無
	private String RewardJouken;//報酬条件(☆の数)
	private String RewardText;	// 報酬文章


	//引数がないコンストラクタ(豆の仕様で必須)
	public Child() {

	}


	public Child(String childId, String childName, String childPicture, String childAge, String childFavorite,
			String childSchool, String userId, String rewardUmu, String rewardJouken, String rewardText) {
		this.ChildId = childId;
		this.ChildName = childName;
		this.ChildPicture = childPicture;
		this.ChildAge = childAge;
		this.ChildFavorite = childFavorite;
		this.ChildSchool = childSchool;
		this.UserId = userId;
		this.RewardUmu = rewardUmu;
		this.RewardJouken = rewardJouken;
		this.RewardText = rewardText;
	}

	public Child() {
		this.ChildId = "";
		this.ChildName = "";
		this.ChildPicture = "";
		this.ChildAge = "";
		this.ChildFavorite = "";
		this.ChildSchool = "";
		this.UserId = "";
		this.RewardUmu ="";
		this.RewardJouken = "";
		this.RewardText = "";
	}

	public String getChildId() {
		return ChildId;
	}

	public void setChildId(String childId) {
		this.ChildId = childId;
	}

	public String getChildName() {
		return ChildName;
	}

	public void setChildName(String childName) {
		this.ChildName = childName;
	}

	public String getChildPicture() {
		return ChildPicture;
	}

	public void setChildPicture(String childPicture) {
		this.ChildPicture = childPicture;
	}

	public String getChildAge() {
		return ChildAge;
	}

	public void setChildAge(String childAge) {
		this.ChildAge = childAge;
	}

	public String getChildFavorite() {
		return ChildFavorite;
	}

	public void setChildFavorite(String childFavorite) {
		this.ChildFavorite = childFavorite;
	}

	public String getChildSchool() {
		return ChildSchool;
	}

	public void setChildSchool(String childSchool) {
		this.ChildSchool = childSchool;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		this.UserId = userId;
	}

	public String getRewardUmu() {
		return RewardUmu;
	}

	public void setRewardUmu(String rewardUmu) {
		this.RewardUmu = rewardUmu;
	}

	public String getRewardJouken() {
		return RewardJouken;
	}

	public void setRewardJouken(String rewardJouken) {
		this.RewardJouken = rewardJouken;
	}

	public String getRewardText() {
		return RewardText;
	}

	public void setRewardText(String rewardText) {
		RewardText = rewardText;
	}




}
