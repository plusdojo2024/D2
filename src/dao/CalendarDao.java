package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Calendar;

public class CalendarDao {
	// ログインできるならtrueを返す
	public List<Calendar> isLoginOK(Calendar calendar) {
		Connection conn = null;
		List<Calendar> CalendarList =new ArrayList<Calendar>();

try {
	// JDBCドライバを読み込む
	Class.forName("org.h2.Driver");

	// データベースに接続する
	conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D2", "sa", "");

	// SELECT文を準備する
	String sql = "SELECT * FROM CALENDAR  WHERE date =? and child_id=? and	housework_name=?";
	PreparedStatement pStmt = conn.prepareStatement(sql);

	pStmt.setString(1, calendar.getDate());
	pStmt.setString(2,calendar.getChildId());
	pStmt.setString(3,calendar.getHouseworkName());

	// SELECT文を実行し、結果表を取得する
	ResultSet rs = pStmt.executeQuery();

	// 結果表をコレクションにコピーする ArratListに乗り換えてるらしい？
	while (rs.next()) {
		Calendar record = new Calendar(
		rs.getString ("date"),
		rs.getString ("ChildId"),
		rs.getString ("HouseworkName")

		);
		CalendarList .add(record);
	}
}
catch (SQLException e) {
	e.printStackTrace();
	CalendarList = false;
}
catch (ClassNotFoundException e) {
	e.printStackTrace();
	CalendarList = false;
}
finally {
	// データベースを切断
	if (conn != null) {
		try {
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			CalendarList = false;
		}
	}
}

// 結果を返す
return CalendarList;
}
}
