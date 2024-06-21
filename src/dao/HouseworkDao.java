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
	public List<HouseWork> select(String userId) {
		Connection conn = null;
		List<HouseWork> cardList = new ArrayList<HouseWork>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D2", "sa", "");

			// SQL文を準備する
			String sql ="SELECT * FROM Housework WHERE user_Id = ? ORDER BY housework_name";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userId);



			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする ArratListに乗り換えてるらしい？
			while (rs.next()) {
				HouseWork record = new HouseWork(
				rs.getString ("housework_name"),
				rs.getString ("housework_contents"),
				rs.getString ("housework_point"),
				rs.getString ("icon"),
				rs.getString("icon_done"),
				rs.getString ("user_id"),
				rs.getString ("icon_x"),
				rs.getString("icon_y"),
				rs.getBoolean("housework_check")
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
	public boolean insert(HouseWork HW ) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D2", "sa", "");

			// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
			String sql = "INSERT INTO Housework VALUES ( NULL,?,?,?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			/*
			if (HW.getHouseworkName() != null && !HW.getHouseworkName().equals("")) {
				pStmt.setString(1, HW.getHouseworkName());
			}
			else {
				pStmt.setString(1, "（未設定）");
			}*/
			if (HW.getHouseworkContents() != null && !HW.getHouseworkContents().equals("")) {
				pStmt.setString(1, HW.getHouseworkContents());
			}
			else {
				pStmt.setString(1, "（未設定）");
			}
			if (HW.getHouseworkPoint() != null && !HW.getHouseworkPoint().equals("")) {
				pStmt.setString(2, HW.getHouseworkPoint());
			}
			else {
				pStmt.setString(2, "（未設定）");
			}
			if (HW.getIcon() != null && !HW.getIcon().equals("")) {
				pStmt.setString(3, HW.getIcon());
			}
			else {
				pStmt.setString(3, "（未設定）");
			}
			if (HW.getIconDone() != null && !HW.getIconDone().equals("")) {
				pStmt.setString(4, HW.getIconDone());
			}
			else {
				pStmt.setString(4, "（未設定）");
			}
			if (HW.getUserId() != null && !HW.getUserId().equals("")) {
				pStmt.setString(5, HW.getUserId());
			}
			else {
				pStmt.setString(5, "（未設定）");
			}
			if (HW.getIconX() != null && !HW.getIconX().equals("")) {
				pStmt.setString(6, HW.getIconX());
			}
			else {
				pStmt.setString(6, "（未設定）");
			}
			if (HW.getIconY() != null && !HW.getIconY().equals("")) {
				pStmt.setString(7, HW.getIconY());
			}
			else {
				pStmt.setString(7, "（未設定）");
			}
			if (HW.getHouseworkCheck() != null) {
			    pStmt.setBoolean(7, HW.getHouseworkCheck());
			} else {
			    pStmt.setBoolean(7, false); // もしnullの場合はfalseとしてセットする
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
	public boolean updateHW(HouseWork houseWork) {
	    Connection conn = null;
	    PreparedStatement pStmt = null;
	    boolean result = false;

	    try {
	        Class.forName("org.h2.Driver");
	        conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D2", "sa", "");

	        String sql = "UPDATE Housework SET housework_contents=?, housework_point=? WHERE housework_name=? AND user_id=?";
	        pStmt = conn.prepareStatement(sql);

	        // 各フィールドの更新を判定してセットする
	        if (houseWork.getHouseworkContents() != null && !houseWork.getHouseworkContents().equals("")) {
	        	pStmt.setString(1, houseWork.getHouseworkContents());
	        } else {
	            pStmt.setString(1, houseWork.getHouseworkContents());
	        }
	        if (houseWork.getHouseworkPoint() != null && !houseWork.getHouseworkPoint().equals("")) {
	        	pStmt.setString(2, houseWork.getHouseworkPoint());
	        } else {
	            pStmt.setString(2, houseWork.getHouseworkPoint());
	        }

	        pStmt.setString(3, houseWork.getHouseworkName());
	        pStmt.setString(4, houseWork.getUserId());

	        if (pStmt.executeUpdate() == 1) {
	            result = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } finally {
	        if (pStmt != null) {
	            try {
	                pStmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
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
			String sql = "DELETE FROM Housework WHERE housework_name=?";
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

	public boolean updateXY(HouseWork card) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む(更新する)
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D2", "sa", "");

			// SQL文を準備する
			String sql = "UPDATE Housework SET icon_X=?,icon_Y=? WHERE housework_Name=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる

			if (card.getIconX() != null && !card.getIconX().equals("")) {
				pStmt.setString(1, card.getIconX());
			}
			else {
				pStmt.setString(1, null);
			}
			if (card.getIconY() != null && !card.getIconY().equals("")) {
				pStmt.setString(2, card.getIconY());
			}
			else {
				pStmt.setString(2, null);
			}

			pStmt.setString(3, card.getHouseworkName());

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

	public boolean updateF(HouseWork card) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む(更新する)
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D2", "sa", "");

			// SQL文を準備する
			String sql = "UPDATE Housework SET housework_check=? WHERE housework_Name=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (card.getHouseworkCheck() != null && !card.getHouseworkCheck().equals(false)) {
				pStmt.setBoolean(1, card.getHouseworkCheck());
			}
			else {
				pStmt.setBoolean(1, true);
			}
			pStmt.setString(2, card.getHouseworkName());

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

	public boolean updateRE(HouseWork card) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む(更新する)
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D2", "sa", "");

			// SQL文を準備する
			String sql = "UPDATE Housework SET housework_check=? WHERE housework_Name=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (card.getHouseworkCheck() != null && !card.getHouseworkCheck().equals(true)) {
				pStmt.setBoolean(1, card.getHouseworkCheck());
			}
			else {
				pStmt.setBoolean(1, false);
			}
			pStmt.setString(2, card.getHouseworkName());

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

}



