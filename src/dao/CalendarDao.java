package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Calendar;

public class CalendarDao {
	// 引数paramで検索項目を指定し、検索結果のリストを返す
	//データ型List<Calendar>、メソッド名select、引数型Calendar、引数名card
	public List<Calendar> select(Calendar dates) {
		Connection conn = null;
		List<Calendar> datesList = new ArrayList<Calendar>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D2", "sa", "");

			// SELECT文を準備する
			String sql = "SELECT * FROM CALENDAR  WHERE click_date =? and click_child=? and	click_housework=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//clickDate clickChild clickHousework

			pStmt.setDate(1, (Date) dates.getclickDate());
			pStmt.setString(2, dates.getclickChild());
			pStmt.setString(3, dates.getclickHousework());

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Calendar record = new Calendar(
						rs.getDate("clickDate"),
						rs.getString("clickChild"),
						rs.getString("clickHousework")

				);
				datesList.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			datesList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			datesList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					datesList = null;
				}
			}
		}
		// 結果を返す
		return datesList;
	}

	public List<Calendar> select(int year ,int month ) {
		Connection conn = null;
		List<Calendar> datesList = new ArrayList<Calendar>();
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.set(java.util.Calendar.YEAR, year);
        c.set(java.util.Calendar.MONTH, month-1);
        c.set(java.util.Calendar.DATE, 1);
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D2", "sa", "");

			// SELECT文を準備する
			String sql = "SELECT * FROM CALENDAR  WHERE click_date >=? and click_date < ? ORDER BY click_date, click_housework";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//clickDate clickChild clickHousework

			pStmt.setDate(1, new java.sql.Date(c.getTimeInMillis()));
			c.add(java.util.Calendar.MONTH,1);

			pStmt.setDate(2, new java.sql.Date(c.getTimeInMillis()));


			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Calendar record = new Calendar(
						rs.getDate("click_Date"),
						rs.getString("click_Child"),
						rs.getString("click_Housework")

				);
				datesList.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			datesList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			datesList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					datesList = null;
				}
			}
		}
		// 結果を返す
		return datesList;
	}

	public boolean insert(Calendar ca) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
