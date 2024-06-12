
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CalendarComment;

public class CommentDao {
    private static final String SELECT_ALL_COMMENTS_SQL = "SELECT * FROM comment";

    public List<CalendarComment> getAllComments() {
        List<CalendarComment> commentList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // JDBCドライバを読み込む
            Class.forName("org.h2.Driver");

            // データベースに接続する
            conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D2", "sa", "");

            // SQL文を準備する
            stmt = conn.prepareStatement(SELECT_ALL_COMMENTS_SQL);

            // SQL文を実行し、結果表を取得する
            rs = stmt.executeQuery();

            // 結果表をコレクションにコピーする
            while (rs.next()) {
                CalendarComment comment = new CalendarComment(
                    rs.getString("date"),
                    rs.getString("child_id"),
                    rs.getString("comment")
                );
                commentList.add(comment);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // 例外処理が必要
        } finally {
            // リソースを解放する
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
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

        // 結果を返す
        return commentList;
    }
}