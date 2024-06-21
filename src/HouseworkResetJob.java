import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

		public class HouseworkResetJob {

		    // H2データベースの接続設定
		    private static final String JDBC_URL = "jdbc:h2:file:C:/pleiades/workspace/data/D2";
		    private static final String USER = "sa";
		    private static final String PASSWORD = "";

		    public static void main(String[] args) {
		        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

		        // 毎日0時0分に実行するジョブ
		        scheduler.scheduleAtFixedRate(() -> resetHouseworkCheck(), calculateDelayToMidnight(), 1, TimeUnit.DAYS);
		    }

		    private static void resetHouseworkCheck() {
		        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
		            String sql = "UPDATE Housework SET Housework_check = FALSE";
		            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
		                int rowsUpdated = stmt.executeUpdate();
		                System.out.println("Housework_check column reset successfully. Rows updated: " + rowsUpdated);
		            }
		        } catch (SQLException e) {
		            System.err.println("Error resetting Housework_check column: " + e.getMessage());
		        }
		    }

		    // 次の0時0分までの遅延時間を計算するメソッド
		    private static long calculateDelayToMidnight() {
		        // 現在時刻を取得
		        long now = System.currentTimeMillis();
		        // 今日の0時0分の時刻を計算
		        long midnight = now - (now % (24 * 60 * 60 * 1000));
		        // 明日の0時0分までの時間を計算
		        return midnight + TimeUnit.DAYS.toMillis(1) - now;
		    }

	}
