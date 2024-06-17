package servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ChildDao;
import dao.HouseworkDao;
import model.Child;
import model.HouseWork;
import model.User;

/**
 * Servlet implementation class IconServlet
 */
@WebServlet("/IconServlet")
public class IconServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */

    public IconServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/D2/LoginServlet");
			return;
		}

		User loginUser = (User)session.getAttribute("id");
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
		Date date = new Date();
		String houseworkName = request.getParameter("houseworkName");
		String houseworkContets = request.getParameter("houseworkContets");
		String houseworkPoint = request.getParameter("houseworkPoint");
		String icon = request.getParameter("icon");
		String iconDone = request.getParameter("iconDone");
		String iconX = request.getParameter("iconX");
		String iconY = request.getParameter("iconY");

		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("id");
		String userID = loginUser.getUserId();
		HouseworkDao houseworkDao = new HouseworkDao();
		HouseWork houseworkli = new HouseWork(
				houseworkName,//家事の名前
				houseworkContets,//家事内容
				houseworkPoint,//お手伝いポイント
				icon,//未処理のアイコン
				iconDone,//処理済みのアイコン
				userID,//ログインID
				iconX,//移動したアイコンのX座標
				iconY);
		//HouseworkDao.insert(houseworkli);

		ChildDao childDao = new ChildDao();
		Child childac = new Child(
				0, //自動採番の際は0に変更(int)
				"ダミー",
				"ダミー",
				userID,
				"ダミー",
				"ダミー",
				"ダミー");
		childDao.insert(childac);


		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
