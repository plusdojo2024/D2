package dao;
//daoは通常のJavaとして作る(サーブレットとかjspではなく)
//データベースアクセスに関する処理を書く

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.HouseWork;

public class HouseworkDao {
	// 引数paramで検索項目を指定し、検索結果のリストを返す
	public List<HouseWork> select(HouseWork HW) {
		Connection conn = null;
		List<HouseWork> cardList = new ArrayList<HouseWork>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D2", "sa", "");

			// SQL文を準備する
			String sql ="SELECT * FROM Housework WHERE houseworkName = ? AND houseworkContents = ? AND houseworkPoint = ? AND icon = ? AND iconDone = ? AND userId = ? AND iconX = ? AND iconY = ?" ;

			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SQL文を完成させる
			if (HW.getHouseworkName() != null) {
				pStmt.setString(1, "%" + HW.getHouseworkName() + "%");
			}
			else {
				pStmt.setString(1, "%");
			}
			if (HW.getHouseworkContents() != null) {
				pStmt.setString(2, "%" + HW.getHouseworkContents() + "%");
			}
			else {
				pStmt.setString(2, "%");
			}
			if (HW.getHouseworkPoint() != null) {
				pStmt.setString(3, "%" + HW.getHouseworkPoint() + "%");
			}
			else {
				pStmt.setString(3, "%");
			}
			if (HW.getIcon() != null) {
				pStmt.setString(4, "%" + HW.getIcon() + "%");
			}
			else {
				pStmt.setString(4, "%");
			}
			if (HW.getIconDone() != null) {
				pStmt.setString(5, "%" + HW.getIconDone() + "%");
			}
			else {
				pStmt.setString(5, "%");
			}
			if (HW.getUserId() != null) {
				pStmt.setString(6, "%" + HW.getUserId() + "%");
			}
			else {
				pStmt.setString(6, "%");
			}
			if (HW.getIconX() != null) {
				pStmt.setString(7, "%" + HW.getIconX() + "%");
			}
			else {
				pStmt.setString(7, "%");
			}
			if (HW.getIconY() != null) {
				pStmt.setString(8, "%" + HW.getIconY() + "%");
			}
			else {
				pStmt.setString(8, "%");
			}


			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする ArratListに乗り換えてるらしい？
			while (rs.next()) {
				HouseWork record = new HouseWork(
				rs.getString ("houseworkName"),
				rs.getString ("houseworkContents"),
				rs.getString ("houseworkPoint"),
				rs.getString ("icon"),
				rs.getString("iconDone"),
				rs.getString ("userId"),
				rs.getString ("iconX"),
				rs.getString("iconY")
				);
				cardList.add(record);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			cardList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			cardList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					cardList = null;
				}
			}
		}

		// 結果を返す
		return cardList;
	}

	// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
	/*public boolean insert(Bc card) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

			// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
			String sql = "INSERT INTO Bc VALUES (NULL, ?, ?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (card.getCompany() != null && !card.getCompany().equals("")) {
				pStmt.setString(1, card.getCompany());
			}
			else {
				pStmt.setString(1, "（未設定）");
			}
			if (card.getDepartment() != null && !card.getDepartment().equals("")) {
				pStmt.setString(2, card.getDepartment());
			}
			else {
				pStmt.setString(2, "（未設定）");
			}
			if (card.getPosition() != null && !card.getPosition().equals("")) {
				pStmt.setString(3, card.getPosition());
			}
			else {
				pStmt.setString(3, "（未設定）");
			}
			if (card.getName() != null && !card.getName().equals("")) {
				pStmt.setString(4, card.getName());
			}
			else {
				pStmt.setString(4, "（未設定）");
			}
			if (card.getFurigana() != null && !card.getFurigana().equals("")) {
				pStmt.setString(5, card.getFurigana());
			}
			else {
				pStmt.setString(5, "（未設定）");
			}
			if (card.getPost() != null && !card.getPost().equals("")) {
				pStmt.setString(6, card.getPost());
			}
			else {
				pStmt.setString(6, "（未設定）");
			}
			if (card.getAddress() != null && !card.getAddress().equals("")) {
				pStmt.setString(7, card.getAddress());
			}
			else {
				pStmt.setString(7, "（未設定）");
			}
			if (card.getPhone() != null && !card.getPhone().equals("")) {
				pStmt.setString(8, card.getPhone());
			}
			else {
				pStmt.setString(8, "（未設定）");
			}
			if (card.getFax() != null && !card.getFax().equals("")) {
				pStmt.setString(9, card.getFax());
			}
			else {
				pStmt.setString(9, "（未設定）");
			}

			if (card.getEmail() != null && !card.getEmail().equals("")) {
				pStmt.setString(10, card.getEmail());
			}
			else {
				pStmt.setString(10, "（未設定）");
			}
			if (card.getStars() != null && !card.getStars().equals("")) {
				pStmt.setString(11, card.getStars());
			}
			else {
				pStmt.setString(11, "（未設定）");
			}
			if (card.getRemarks() != null && !card.getRemarks().equals("")) {
				pStmt.setString(12, card.getRemarks());
			}
			else {
				pStmt.setString(12, "（未設定）");
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
	}*/

	// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(HouseWork card) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む(更新するお)
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D2", "sa", "");

			// SQL文を準備する
			String sql = "UPDATE Bc SET houseworkContents=?, houseworkPoint=?,icon=?, iconDone=?, userId=?, iconX=?,iconY=? WHERE houseworkName=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (card.getHouseworkContents() != null && !card.getHouseworkContents().equals("")) {
				pStmt.setString(1, card.getHouseworkContents());
			}
			else {
				pStmt.setString(1, null);
			}
			if (card.getHouseworkPoint() != null && !card.getHouseworkPoint().equals("")) {
				pStmt.setString(2, card.getHouseworkPoint());
			}
			else {
				pStmt.setString(2, null);
			}
			if (card.getIcon() != null && !card.getIcon().equals("")) {
				pStmt.setString(3, card.getIcon());
			}
			else {
				pStmt.setString(3, null);
			}
			if (card.getIconDone() != null && !card.getIconDone().equals("")) {
				pStmt.setString(4, card.getIconDone());
			}
			else {
				pStmt.setString(4, null);
			}
			if (card.getUserId() != null && !card.getUserId().equals("")) {
				pStmt.setString(5, card.getUserId());
			}
			else {
				pStmt.setString(5, null);
			}
			if (card.getIconX() != null && !card.getIconX().equals("")) {
				pStmt.setString(6, card.getIconX());
			}
			else {
				pStmt.setString(6, null);
			}
			if (card.getIconY() != null && !card.getIconY().equals("")) {
				pStmt.setString(7, card.getIconY());
			}
			else {
				pStmt.setString(7, null);
			}

			pStmt.setString(8, card.getHouseworkName());

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

	// 引数numberで指定されたレコードを削除し、成功したらtrueを返す
	public boolean delete(String houseworkName) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む(削除するお)
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D2", "sa", "");

			// SQL文を準備する(何番目のレコードかを見る)
			String sql = "DELETE FROM Housework WHERE houseworkName=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, houseworkName);

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

