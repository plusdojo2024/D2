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

import model.Bc;

public class BcDAO {
	// 引数paramで検索項目を指定し、検索結果のリストを返す
	public List<Bc> select(Bc card) {
		Connection conn = null;
		List<Bc> cardList = new ArrayList<Bc>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

			// SQL文を準備する
			String sql ="SELECT * FROM Bc WHERE name LIKE ? AND address LIKE ? ORDER BY number";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SQL文を完成させる
			if (card.getName() != null) {
				pStmt.setString(1, "%" + card.getName() + "%");
			}
			else {
				pStmt.setString(1, "%");
			}
			if (card.getAddress() != null) {
				pStmt.setString(2, "%" + card.getAddress() + "%");
			}
			else {
				pStmt.setString(2, "%");
			}

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする ArratListに乗り換えてるらしい？
			while (rs.next()) {
				Bc record = new Bc(
				rs.getInt ("number"),
				rs.getString ("company"),
				rs.getString ("department"),
				rs.getString ("position"),
				rs.getString("name"),
				rs.getString ("furigana"),
				rs.getString ("post"),
				rs.getString("address"),
				rs.getString ("phone"),
				rs.getString ("fax"),
				rs.getString ("email"),
				rs.getString ("stars"),
				rs.getString ("remarks")

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
	public boolean insert(Bc card) {
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
	}

	// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(Bc card) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む(更新するお)
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

			// SQL文を準備する
			String sql = "UPDATE Bc SET company=?, department=?, position=?,name=?, furigana=?, post=?, address=?,phone=?, fax=?, email=?,stars=?, remarks=? WHERE number=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (card.getCompany() != null && !card.getCompany().equals("")) {
				pStmt.setString(1, card.getCompany());
			}
			else {
				pStmt.setString(1, null);
			}
			if (card.getDepartment() != null && !card.getDepartment().equals("")) {
				pStmt.setString(2, card.getDepartment());
			}
			else {
				pStmt.setString(2, null);
			}
			if (card.getPosition() != null && !card.getPosition().equals("")) {
				pStmt.setString(3, card.getPosition());
			}
			else {
				pStmt.setString(3, null);
			}
			if (card.getName() != null && !card.getName().equals("")) {
				pStmt.setString(4, card.getName());
			}
			else {
				pStmt.setString(4, null);
			}
			if (card.getFurigana() != null && !card.getFurigana().equals("")) {
				pStmt.setString(5, card.getFurigana());
			}
			else {
				pStmt.setString(5, null);
			}
			if (card.getPost() != null && !card.getPost().equals("")) {
				pStmt.setString(6, card.getPost());
			}
			else {
				pStmt.setString(6, null);
			}
			if (card.getAddress() != null && !card.getAddress().equals("")) {
				pStmt.setString(7, card.getAddress());
			}
			else {
				pStmt.setString(7, null);
			}
			if (card.getPhone() != null && !card.getPhone().equals("")) {
				pStmt.setString(8, card.getPhone());
			}
			else {
				pStmt.setString(8, null);
			}
			if (card.getFax() != null && !card.getFax().equals("")) {
				pStmt.setString(9, card.getFax());
			}
			else {
				pStmt.setString(9, null);
			}
			if (card.getEmail() != null && !card.getEmail().equals("")) {
				pStmt.setString(10, card.getEmail());
			}
			else {
				pStmt.setString(10, null);
			}
			if (card.getStars() != null && !card.getStars().equals("")) {
				pStmt.setString(11, card.getStars());
			}
			else {
				pStmt.setString(11, null);
			}
			if (card.getRemarks() != null && !card.getRemarks().equals("")) {
				pStmt.setString(12, card.getRemarks());
			}
			else {
				pStmt.setString(12, null);
			}

			pStmt.setInt(13, card.getNumber());

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
	public boolean delete(int number) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む(削除するお)
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

			// SQL文を準備する(何番目のレコードかを見る)
			String sql = "DELETE FROM Bc WHERE number=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, number);

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

