package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
//import java.util.Date; // これは java.util.Date を使う場合のみ必要です
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CalendarDao;
import dao.ChildDao;
import dao.HouseworkDao;
import model.Calendar;
import model.Child;
import model.HouseWork;
import model.Result;
import model.User;

@WebServlet("/HouseworkServlet")
public class HouseworkServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // H2 Databaseの接続情報
    private static final String JDBC_URL = "jdbc:h2:file:C:/pleiades/workspace/data/D2"; // H2のデータベースファイルパス
    private static final String DB_USER = "sa"; // デフォルトのユーザー名
    private static final String DB_PASSWORD = ""; // デフォルトのパスワード

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("id") == null) {
            response.sendRedirect("/D2/LoginServlet");
            return;
        }

        User loginUser = (User) session.getAttribute("id");
        session.removeAttribute("pc");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // H2 Databaseに接続
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            // 今日の日付を取得
            LocalDate today = LocalDate.now();
            String clickDate = today.format(DateTimeFormatter.ISO_DATE);

            // セッションスコープからclickChildを取得
            String  clickChild = request.getParameter("childName");
            String saveToSessionParam = request.getParameter("saveToSession");

            if (saveToSessionParam != null && saveToSessionParam.equals("true")) {
                //HttpSession session = request.getSession();
                session.setAttribute("selectedChildName",  clickChild);
            }

            //sql文
            String sql = "SELECT AVG(CAST(D.REWARD_JOUKEN AS DOUBLE)) AS avg_reward, " +
                         "SUM(CAST(H.HOUSEWORK_POINT AS DOUBLE)) AS sum_housework " +
                         "FROM CALENDAR AS C " +
                         "INNER JOIN HOUSEWORK AS H ON H.HOUSEWORK_NAME = C.CLICK_HOUSEWORK " +
                         "INNER JOIN CHILD AS D ON D.CHILD_NAME = C.CLICK_CHILD " +
                         "WHERE C.CLICK_DATE = ? AND C.CLICK_CHILD = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, clickDate);
            pstmt.setString(2, clickChild);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                double avgReward = rs.getDouble("avg_reward");
                double sumHousework = rs.getDouble("sum_housework");

                if (sumHousework > avgReward) {
                    session.setAttribute("mw", true);
                } else {
                    session.setAttribute("mw", false);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        ChildDao cDao = new ChildDao();
        List<Child> userList = cDao.select(loginUser.getUserId());
        request.setAttribute("userList", userList);

        HouseworkDao hwDao = new HouseworkDao();
        List<HouseWork> cardList = hwDao.select(loginUser.getUserId());
        request.setAttribute("cardList", cardList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/housework.jsp");
        dispatcher.forward(request, response);
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<HouseWork> hwList = new ArrayList<>();
		for (int i = 1; i <= 7; i++) {
			String x = request.getParameter("my_p" + i);

			if (x == null) {
				continue;
			}
			HouseWork hw = new HouseWork();
			hw.setHouseworkName(request.getParameter("my_z" + i));
			hw.setHouseworkCheck(true);
			hwList.add(hw);
		}


		if (request.getParameter("my_save").equals("かくていする")) {
			boolean result = false;

			HouseworkDao hwDao = new HouseworkDao();
			CalendarDao caDao = new CalendarDao();
			/*String date1 = request.getParameter("date");
			java.sql.Date clickDate= java.sql.Date.valueOf(date1);
			Child child = (Child)session.getAttribute("id");
			String clickChild = request.getChildName();
			//String action = request.getParameter("action");
			//User loginUser = (User) session.getAttribute("id");
			//String userID = loginUser.getUserId();*/


			//やった家事の名前を取得
			List<HouseWork> selectDone =hwDao.selectDone();


			// 現在の日付を取得 (java.util.Date)
	        java.util.Date utilDate = new java.util.Date();

	        // java.util.Date から java.sql.Date への変換
	        java.sql.Date clickDate = new java.sql.Date(utilDate.getTime());

            // セッションスコープからclickChildを取得
		    //HttpSession session = request.getSession();
           // String clickChild = (String) session.getAttribute("cn");

	     // セッションスコープからclickChildを取得
            String  clickChild = request.getParameter("childName");
            String saveToSessionParam = request.getParameter("saveToSession");

            if (saveToSessionParam != null && saveToSessionParam.equals("true")) {
                HttpSession session = request.getSession();
                session.setAttribute("selectedChildName",  clickChild);
            }

			if (request.getParameter("my_save").equals("かくていする")) {
				//boolean result = false;



				// インサートが成功した場合の処理が続く




			for (HouseWork hw : hwList) {
				result = hwDao.updateF(hw);
				if (result == false) {
					break;
				}
				String hwName =hw.getHouseworkName();
				boolean isDone = false;
				for (HouseWork item : selectDone) {
					if(hwName.equals(item.getHouseworkName())) {
						isDone =true;
						break;
					}
				}
				if(!isDone) {
					Calendar cal = new Calendar(clickDate, clickChild, hwName);
					if (!caDao.insert(cal)) {
					    result =false;
					    break;

					}
				}
			}
			if (result) {
				request.setAttribute("result",
						new Result("保存成功！", "更新を実施しました", "/D2/HomeServlet"));
			} else {
				request.setAttribute("result",
						new Result("保存失敗…", "更新出来ませんでした", "/D2/HomeServlet"));
			}
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(request, response);
	}

 }
	}