package servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import model.Result;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/D2/LoginServlet");
			return;
		}

		User loginUser = (User) session.getAttribute("id");
		ChildDao cDao = new ChildDao();
		List<Child> userList = cDao.select(loginUser.getUserId());
		request.setAttribute("userList", userList);

		HouseworkDao hwDao = new HouseworkDao();
		List<HouseWork> cardList = hwDao.select(loginUser.getUserId());
		request.setAttribute("cardList", cardList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/icon.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<HouseWork> hwList = new ArrayList<>();
		for (int i = 1; i <= 6; i++) {
			String x = request.getParameter("my_x" + i);
			if (x == null) {
				break;
			}
			HouseWork hw = new HouseWork();
			hw.setIconX(request.getParameter("my_x" + i));
			hw.setIconY(request.getParameter("my_y" + i));
			hw.setHouseworkName(request.getParameter("my_z" + i));
			hwList.add(hw);
		}

		HouseworkDao hwDao = new HouseworkDao();
		if (request.getParameter("my_save").equals("保存")) {
			boolean result = false;
			for (HouseWork hw : hwList) {
				result = hwDao.updateXY(hw);
				if (result == false) {
					break;
				}
			}
			if (result) {
				request.setAttribute("result",
						new Result("保存成功！", "更新を実施しました", "/D2/IconServlet"));
			} else {
				request.setAttribute("result",
						new Result("保存失敗…", "更新出来ませんでした", "/D2/IconServlet"));
			}
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(request, response);
	}

	/*Date date = new Date();
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
			iconY);*/
	//HouseworkDao.insert(houseworkli);

	/*ChildDao childDao = new ChildDao();
	Child childac = new Child(
			0, //自動採番の際は0に変更(int)
			"ダミー",
			"ダミー",
			userID,
			"ダミー",
			"ダミー",
			"ダミー");
	childDao.insert(childac);*/



}
