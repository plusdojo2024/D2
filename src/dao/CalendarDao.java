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
	String sql = "SELECT * FROM CALENDAR  WHERE date =? and child_id=? and	housework_name=?";
	PreparedStatement pStmt = conn.prepareStatement(sql);

	pStmt.setDate(1, (Date) dates.getDate());
	pStmt.setString(2,dates.getChildId());
	pStmt.setString(3,dates.getHouseworkName());

	// SELECT文を実行し、結果表を取得する
	ResultSet rs = pStmt.executeQuery();

	// 結果表をコレクションにコピーする ArratListに乗り換えてるらしい？
	while (rs.next()) {
		Calendar record = new Calendar(
		rs.getDate ("date"),
		rs.getString ("ChildId"),
		rs.getString ("HouseworkName")

		);
		datesList .add(record);
	}
}
catch (SQLException e) {
	e.printStackTrace();
	datesList = null;
}
catch (ClassNotFoundException e) {
	e.printStackTrace();
	datesList = null;
}
finally {
	// データベースを切断
	if (conn != null) {
		try {
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			datesList = null;
		}
	}
}
// 結果を返す
return datesList;
}

	public static void insert(Calendar calendardm) {
		// TODO 自動生成されたメソッド・スタブ

	}
}
