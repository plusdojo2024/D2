package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Result;
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
		if (session.getAttribute("pc") != null) {
			response.sendRedirect("./LockServlet");
			return;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/lock.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");

			String pc = request.getParameter("pc");

			HttpSession session = request.getSession();
			User loginUser = (User)session.getAttribute("id");
            String pascord = loginUser.getPasscord();


			// ログイン処理を行う

			if (pascord.equals(pc)) { // ログイン成功

				response.sendRedirect("./ParentsServlet");
			} else { // ログイン失敗
				//エラーメッセージを出すならここで設定する　jspで表示する
				// リクエストスコープに、タイトル、メッセージ、戻り先を格納する
				request.setAttribute("result",
						new Result("失敗...", "パスコードが間違っています。", "/D2/LockServlet"));

				// 結果ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
				dispatcher.forward(request, response);
			}
		}


}
