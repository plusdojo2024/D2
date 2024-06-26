
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CalendarComment;

public class CommentDao {

    public List<CalendarComment> select(String userId, int year ,int month) {
        List<CalendarComment> commentList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int nextYear = year;
        int nextMonth = month;
        if(nextMonth == 12) {
        	nextYear += 1;
        	nextMonth = 1;
        }else {
        	nextMonth += 1;
        }

        try {
            // JDBCドライバを読み込む
            Class.forName("org.h2.Driver");

            // データベースに接続する
            conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D2", "sa", "");

            // SQL文を準備する
            String sql ="SELECT * FROM comment WHERE user_id = ? AND date < ? AND date >= ? ORDER BY date";
            stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			stmt.setDate(2, Date.valueOf(nextYear+"-"+nextMonth+"-1"));
			stmt.setDate(3, Date.valueOf(year+"-"+month+"-1"));//yyyy-mm-1

            // SQL文を実行し、結果表を取得する
            rs = stmt.executeQuery();

            // 結果表をコレクションにコピーする
            while (rs.next()) {
                CalendarComment comment = new CalendarComment(
                    rs.getDate("date"),
                    rs.getString("user_id"),
                    rs.getString("comment")
                );
                commentList.add(comment);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // 例外処理が必要
        } finally {
            // リソースを解放する
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // 結果を返す
        return commentList;
    }

	public boolean insert (CalendarComment comm) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D2", "sa", "");

			String sql = "INSERT INTO Comment VALUES (?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setDate(1, (Date) comm.getDate());
				
			pStmt.setString(2, comm.getUserId());
				
			pStmt.setString(3, comm.getComment());

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

	public boolean insert(java.util.Date date, String userID, String comment) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
