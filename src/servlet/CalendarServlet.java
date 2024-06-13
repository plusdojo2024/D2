package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CalendarDao;
import model.Calendar;
import model.Result;

/**
 * Servlet implementation class CalendarServlet
 */
@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String servletPath = request.getServletPath(); //今表示しようとしているサーブレット名
		Object login = session.getAttribute("id"); //doPostのログインOKで設定したセッションデータ
		if ("/LoginServlet".equals(servletPath)
				&& login != null) {
			// すでにログインしているので
			response.sendRedirect("./HomeServlet");
		} else {
			// ログインページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */

		CalendarDao cDao = new CalendarDao();
		List<Calendar> calendarList = cDao.select(new Calendar());



		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/calendar.jsp");
		dispatcher.forward(request, response);

		HttpSession session = request.getSession();
		//User loginUser = (User)session.getAttribute("id");
		//String userID = loginUser.getUserId();
		CalendarDao childDao = new CalendarDao();
		Calendar calendardm = new Calendar(
				//自動採番の際は0に変更(int)
				null,
				"ダミー",
				"ダミー"
		);
		CalendarDao.insert(calendardm);

		// リクエストパラメータを取得する
				request.setCharacterEncoding("UTF-8");
				String date = request.getParameter("date");
				String address = request.getParameter("childId");
				String company = request.getParameter("houseworkName");

				CalendarDao clDao = new CalendarDao();
				List<Calendar> userList = clDao.select(new Calendar(date,childId,houseworkName));

				//request.setAttribute("userList", userList);

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("calendarList", calendarList);

		// 更新または削除を行う
		CalendarDao dDao = new CalendarDao();
		if (request.getParameter("submit").equals("登録")) {
			if (dDao.update(new Calendar(date,childId,houseworkName))) {
				// 更新成功
				request.setAttribute("result",
				new Result("登録成功！"));
			}
			else {
				// 更新失敗
				request.setAttribute("result",
				new Result("登録失敗！"));
			}
		}
		/*else {
			if (dDao.delete(childId)) {	// 削除成功
				request.setAttribute("result",
				new Result("削除成功！"));
			}
			else {						// 削除失敗
				request.setAttribute("result",
				new Result("削除失敗！"));
			}
		}*/
	}






}
