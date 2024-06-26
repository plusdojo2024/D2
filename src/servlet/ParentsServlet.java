package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.ChildDao;
import dao.CommentDao;
import dao.HouseworkDao;
import model.CalendarComment;
import model.Child;
import model.HouseWork;
import model.Result;
import model.User;

/**
 * Servlet implementation class ParentsServlet
 */
@WebServlet("/ParentsServlet")
@MultipartConfig()
public class ParentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ParentsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ログインしてなかったら戻る
		HttpSession session = request.getSession();

		if (session.getAttribute("id") == null) {
			response.sendRedirect("/D2/LoginServlet");
			return;
		}else if (session.getAttribute("pc") == null) {
			response.sendRedirect("./LockServlet");
			return;
		}
		//session.removeAttribute("pc");

		User loginUser = (User)session.getAttribute("id");

		ChildDao cDao = new ChildDao();
		List<Child> userList = cDao.select(loginUser.getUserId());
		request.setAttribute("userList", userList);

		HouseworkDao hDao = new HouseworkDao();
		List<HouseWork> houseList = hDao.select(loginUser.getUserId());
		request.setAttribute("houseList", houseList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/parents.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action.equals("comment_regist")) {		// 親ページからのコメントの登録処理
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("id");
			String userID = loginUser.getUserId();
			String date1 = request.getParameter("date");
			java.sql.Date date= java.sql.Date.valueOf(date1);
			String comment =request.getParameter("comment");
			CommentDao coDao = new CommentDao();
			// submit パラメータの文字コードの問題を修正するため、値を直接比較する
			if (request.getParameter("submit").equals("登録")) {
	            if (coDao.insert(new CalendarComment(date, userID, comment))) {
	                request.setAttribute("result",
	                        new Result("登録成功！", "本日のコメントを登録しました😊", "/D2/ParentsServlet"));
	            } else {
	                request.setAttribute("result",
	                        new Result("登録失敗…", "コメントを登録できませんでした😢コメントの登録は一日一回です。", "/D2/ParentsServlet"));
	            }
	        }
		} else if (action.equals("housework_regist")){
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("id");
			String userID = loginUser.getUserId();
			HouseworkDao hDao = new HouseworkDao();
			List<HouseWork> houseList = hDao.select(loginUser.getUserId());
			request.setAttribute("houseList", houseList);

			request.setCharacterEncoding("UTF-8");

			String houseworkName = request.getParameter("houseworkName");
			String houseworkContents =request.getParameter("houseworkContents");
			String houseworkPoint = request.getParameter("houseworkPoint");

			HouseworkDao wDao = new HouseworkDao();
			if (request.getParameter("submit").equals("更新")) {
				if (wDao.updateHW(new HouseWork(houseworkName, houseworkContents, houseworkPoint,userID))) {
					request.setAttribute("result",
							new Result("更新成功！", "更新を実施しました", "/D2/ParentsServlet"));
				}else {
					request.setAttribute("result",
							new Result("更新失敗…","更新出来ませんでした", "/D2/ParentsServlet"));
				}
			}else if(request.getParameter("submit").equals("削除")){
				if(wDao.delete(houseworkName)) { // 削除成功
					request.setAttribute("result",
							new Result("削除成功！","削除を実施しました", "/D2/ParentsServlet"));
				} else { // 削除失敗
					request.setAttribute("result",
							new Result("削除失敗…","削除出来ませんでした", "/D2/ParentsServlet"));
				}
			}

		} else if (action.equals("child_regist")) {
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("id");
			String userID = loginUser.getUserId();


			request.setCharacterEncoding("UTF-8");
			Part childPicture = request.getPart("childPicture");
			if (childPicture.getSubmittedFileName() != "") {
				childPicture.write("C:\\pleiades\\workspace\\D2\\WebContent\\upload\\"+ childPicture.getSubmittedFileName());
			} 
			String childName =request.getParameter("childName");
			//String userId = request.getParameter("userId");
			String rewardUmu = request.getParameter("rewardUmu");
			String rewardJouken = request.getParameter("rewardJouken");
			String rewardText = request.getParameter("rewardText");


			ChildDao dDao = new ChildDao();

			 if(request.getParameter("submit").equals("登録")){
				if (dDao.insert(new Child(0, childPicture.getSubmittedFileName(), childName, userID, rewardUmu, rewardJouken, rewardText))) { // 登録成功
					request.setAttribute("result",
							new Result("登録成功！", "登録を実施しました", "/D2/ParentsServlet"));
				} else { // 更新失敗
					request.setAttribute("result",
							new Result("登録失敗…","登録出来ませんでした", "/D2/ParentsServlet"));
				}
			}
		} else {
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("id");
			String userID = loginUser.getUserId();
			int childId = Integer.parseInt(request.getParameter("childId"));
			request.setCharacterEncoding("UTF-8");
			Part childPicture = request.getPart("childPicture");
			if (childPicture.getSubmittedFileName() != "") {
				childPicture.write("C:\\pleiades\\workspace\\D2\\WebContent\\upload\\"+ childPicture.getSubmittedFileName());
			} 
			String childName = request.getParameter("childName");
			//String userId = request.getParameter("userId");
			String rewardUmu = request.getParameter("rewardUmu");
			String rewardJouken = request.getParameter("rewardJouken");
			String rewardText = request.getParameter("rewardText");


			ChildDao dDao = new ChildDao();

			if (request.getParameter("submit").equals("更新")) {
				if (dDao.update(new Child(childId, childPicture.getSubmittedFileName(), childName, userID, rewardUmu, rewardJouken, rewardText))) { // 更新成功
					request.setAttribute("result",
							new Result("更新成功！", "更新を実施しました", "/D2/ParentsServlet"));
				} else { // 更新失敗
					request.setAttribute("result",
							new Result("更新失敗…","更新出来ませんでした", "/D2/ParentsServlet"));
				}
			} else if(request.getParameter("submit").equals("削除")){
				if (dDao.delete(childName)) { // 削除成功
					request.setAttribute("result",
							new Result("削除成功！","削除を実施しました", "/D2/ParentsServlet"));
				} else { // 削除失敗
					request.setAttribute("result",
							new Result("削除失敗…","削除出来ませんでした", "/D2/ParentsServlet"));
				}
			}
		}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
			dispatcher.forward(request, response);

	}
}