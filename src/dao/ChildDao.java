package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Child;

public class ChildDao{
	// 引数paramで検索項目を指定し、検索結果のリストを返す
	public List<Child> select(Child childac) {
		Connection conn = null;
		List<Child> userList = new ArrayList<Child>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D2", "sa", "");

			// SQL文を準備する
			String sql ="SELECT * FROM Child WHERE ChildPicture LIKE ? AND ChildName LIKE? AND UserId LIKE? AND RewardUmu LIKE? AND RewardJouken LIKE?  AND RewardText LIKE? ORDER BY ChildId";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SQL文を完成させる
			if (childac.getChildName() != null) {
				pStmt.setString(1, "%" + childac.getChildName() + "%");
			}
			else {
				pStmt.setString(1, "%");
			}


			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする ArratListに乗り換えてるらしい？
			while (rs.next()) {
				Child record = new Child(
				rs.getInt ("ChildId"),
				rs.getString ("ChildPicture"),
				rs.getString ("ChildName"),
				rs.getString ("UserId"),
				rs.getString ("RewardUmu "),
				rs.getString ("RewardJouken"),
				rs.getString ("RewardText")
				);
				userList.add(record);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			userList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			userList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					userList = null;
				}
			}
		}

		// 結果を返す
		return userList;
	}

	// 引数childacで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(Child childac) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D2", "sa", "");

			// SQL文を準備する（AUTO_INCREMENTのChildId列にはNULLを指定する）
			String sql = "INSERT INTO Bc VALUES (NULL,?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (childac.getChildPicture() != null && !childac.getChildPicture().equals("")) {
				pStmt.setString(1, childac.getChildPicture());
			}
			else {
				pStmt.setString(1, "（未設定）");
			}
			if (childac.getChildName() != null && !childac.getChildName().equals("")) {
				pStmt.setString(2, childac.getChildName());
			}
			else {
				pStmt.setString(2, "（未設定）");
			}
			if (childac.getUserId() != null && !childac.getUserId().equals("")) {
				pStmt.setString(3, childac.getUserId());
			}
			else {
				pStmt.setString(3, "");
			}
			if (childac.getRewardUmu() != null && !childac.getRewardUmu().equals("")) {
				pStmt.setString(4, childac.getRewardUmu());
			}
			else {
				pStmt.setString(4, "");
			}
			if (childac.getRewardJouken() != null && !childac.getRewardJouken().equals("")) {
				pStmt.setString(5, childac.getRewardJouken());
			}
			else {
				pStmt.setString(5, "");
			}
			if (childac.getRewardText() != null && !childac.getRewardText().equals("")) {
				pStmt.setString(6, childac.getRewardText());
			}
			else {
				pStmt.setString(6, "");
			}

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return result;
	}

	// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(Child childac) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む(更新する)
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D2", "sa", "");

			// SQL文を準備する
			String sql = "UPDATE Bc SET childId=?, childPicture=?, userId=?,rewardUmu=?, rewardJouken=?, rewardtext=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (childac.getChildPicture() != null && !childac.getChildPicture().equals("")) {
				pStmt.setString(1, childac.getChildPicture());
			}
			else {
				pStmt.setString(1, null);
			}
			if (childac.getChildName() != null && !childac.getChildName().equals("")) {
				pStmt.setString(2, childac.getChildName());
			}
			else {
				pStmt.setString(2, null);
			}
			if (childac.getUserId() != null && !childac.getUserId().equals("")) {
				pStmt.setString(3, childac.getUserId());
			}
			else {
				pStmt.setString(3, null);
			}
			if (childac.getRewardUmu() != null && !childac.getRewardUmu().equals("")) {
				pStmt.setString(4, childac.getRewardUmu());
			}
			else {
				pStmt.setString(4, null);
			}
			if (childac.getRewardJouken() != null && !childac.getRewardJouken().equals("")) {
				pStmt.setString(5, childac.getRewardJouken());
			}
			else {
				pStmt.setString(5, null);
			}
			if (childac.getRewardText() != null && !childac.getRewardText().equals("")) {
				pStmt.setString(6, childac.getRewardText());
			}
			else {
				pStmt.setString(6, null);
			}
			
			//自動採番の際は使用
			pStmt.setInt(7, childac.getChildId());

			// SQL文を実行する(更新は都度1件だけなのでそれをチェックする)
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return result;
	}

	// 引数childIdで指定されたレコードを削除し、成功したらtrueを返す
	public boolean delete(int childId) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D2", "sa", "");

			// SQL文を準備する(何番目のレコードかを見る)
			String sql = "DELETE FROM Child WHERE childId=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, childId);

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return result;
	}
}
