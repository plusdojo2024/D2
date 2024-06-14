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

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class LockServlet
 */
@WebServlet("/LockServlet")
public class LockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LockServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.getAttribute("pc") == null) {
			response.sendRedirect("/D2/LockServlet");
			return;
		}
		User loginUser = (User)session.getAttribute("pc");

		UserDao uDao = new UserDao();
		List<User> usersList = uDao.select(loginUser.getUserId());
		request.setAttribute("usersList", usersList);


		String servletPath = request.getServletPath(); //今表示しようとしているサーブレット名
		Object login = session.getAttribute("pc"); //doPostのログインOKで設定したセッションデータ
		if ("/LockServlet".equals(servletPath)
				&& login != null) {
			// すでにログインしているので
			response.sendRedirect("./ParentsServlet");
		} else {
			// ログインページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/lock.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");
			/*String id = request.getParameter("id");
			String pw = request.getParameter("pw");*/
			String pc = request.getParameter("pc");

			// ログイン処理を行う
			UserDao userDao = new UserDao();
			User loginUser = userDao.select(pc);
			if (loginUser != null
					&& loginUser.getPasscord().equals(pc)) { // ログイン成功

				response.sendRedirect("./ParentsServlet");
			} else { // ログイン失敗
				// リクエストスコープに、タイトル、メッセージ、戻り先を格納する
				//request.setAttribute("result",
						//new Result("ログイン失敗！", "IDまたはPWに間違いがあります。", "/D2/LoginServlet"));

				// 結果ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/lock.jsp");
				dispatcher.forward(request, response);
			}
		}


}
