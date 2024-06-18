package servlet;

import java.io.IOException;
import java.util.Date;
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
		}
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
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action.equals("comment_regist")) {		// 親ページからのコメントの登録処理
			request.setCharacterEncoding("UTF-8");
			String comment = request.getParameter("comment");
			Date date = new Date();
			
			CommentDao coDao = new CommentDao();
			
			if (coDao.insert(date, 0, comment)) {
				request.setAttribute("commentSuccess", true);
			} else {
				request.setAttribute("commentSuccess", false);
			}
		}
		else if (action.equals("housework_regist")){
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("id");
			String userID = loginUser.getUserId();
			
			HouseworkDao hDao = new HouseworkDao();
			List<HouseWork> houseList = hDao.select(loginUser.getUserId());
			request.setAttribute("houseList", houseList);
			
			request.setCharacterEncoding("UTF-8");
			
			String houseworkName = request.getParameter("houseworkName");
			String houseworkContents = new String(request.getParameter("houseworkContents").getBytes("ISO-8859-1"),"UTF-8");
			String houseworkPoint = request.getParameter("houseworkPoint");
			String icon = request.getParameter("icon");
			String iconDone = request.getParameter("iconDone");
			//String userId = request.getParameter("userId");
			String iconX = request.getParameter("iconX");
			String iconY = request.getParameter("iconY");
			
			HouseworkDao wDao = new HouseworkDao();
			if (new String(request.getParameter("submit").getBytes("ISO-8859-1"),"UTF-8").equals("更新")) {
				if (wDao.update(new HouseWork(houseworkName, houseworkContents, houseworkPoint,icon, iconDone, userID, iconX, iconY))) {
					request.setAttribute("result",
							new Result("更新成功！", "更新を実施しました", "/D2/ParentsServlet"));
				}else {
					request.setAttribute("result",
							new Result("更新失敗…","更新出来ませんでした", "/D2/ParentsServlet"));
				}
			}else {
				if(wDao.delete(houseworkName)) { // 削除成功
					request.setAttribute("result",
							new Result("削除成功！","削除を実施しました", "/D2/ParentsServlet"));
				} else { // 削除失敗
					request.setAttribute("result",
							new Result("削除失敗…","削除出来ませんでした", "/D2/ParentsServlet"));
				}
			}
		}else {
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("id");
			String userID = loginUser.getUserId();

			
			request.setCharacterEncoding("UTF-8");
			String childId_string = request.getParameter("childId");
			int childId = -1;
			if(childId_string != null) {
				childId = Integer.parseInt(request.getParameter("childId"));
			}
			Part childPicture = request.getPart("childPicture");
			childPicture.write("C:\\pleiades\\workspace\\D2\\WebContent\\upload\\" + childPicture.getSubmittedFileName());
			String childName = new String( request.getParameter("childName").getBytes("ISO-8859-1"),"UTF-8");
			//String userId = request.getParameter("userId");
			String rewardUmu = request.getParameter("rewardUmu");
			String rewardJouken = request.getParameter("rewardJouken");
			String rewardText = new String( request.getParameter("rewardText").getBytes("ISO-8859-1"),"UTF-8");
			
			
			ChildDao dDao = new ChildDao();
				
			if (new String(request.getParameter("submit").getBytes("ISO-8859-1"),"UTF-8").equals("更新")) {
				if (dDao.update(new Child(childId, childPicture.getSubmittedFileName(), childName, userID, rewardUmu, rewardJouken, rewardText))) { // 更新成功
					request.setAttribute("result",
							new Result("更新成功！", "更新を実施しました", "/D2/ParentsServlet"));
				} else { // 更新失敗
					request.setAttribute("result",
							new Result("更新失敗…","更新出来ませんでした", "/D2/ParentsServlet"));
				}
			} else if(new String(request.getParameter("submit").getBytes("ISO-8859-1"),"UTF-8").equals("登録")){
				if (dDao.insert(new Child(childId, childPicture.getSubmittedFileName(), childName, userID, rewardUmu, rewardJouken, rewardText))) { // 登録成功
					request.setAttribute("result",
							new Result("登録成功！", "登録を実施しました", "/D2/ParentsServlet"));
				} else { // 更新失敗
					request.setAttribute("result",
							new Result("登録失敗…","登録出来ませんでした", "/D2/ParentsServlet"));
				}
			} else {
				if (dDao.delete(childId)) { // 削除成功
					request.setAttribute("result",
							new Result("削除成功！","削除を実施しました", "/D2/ParentsServlet"));
				} else { // 削除失敗
					request.setAttribute("result",
							new Result("削除失敗…","削除出来ませんでした", "/D2/ParentsServlet"));
				}
			}
				
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
			dispatcher.forward(request, response);
		}
	}
}