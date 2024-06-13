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
import dao.CommentDao;
import model.Child;
import model.Result;
import model.User;
/**
 * Servlet implementation class ParentsServlet
 */
@WebServlet("/ParentsServlet")
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ログインしてなかったら戻る
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/D2/LoginServlet");
			return;
		}

		ChildDao cDao = new ChildDao();
		List<Child> userList = cDao.select(new Child());

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("userList", userList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/parents.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("id");
		String userID = loginUser.getUserId();
		ChildDao childDao = new ChildDao();
		Child childac = new Child(
				0, //自動採番の際は0に変更(int)
				"ダミー",
				"ダミー",
				userID,
				"ダミー",
				"ダミー",
				"ダミー"
		);
		childDao.insert(childac);

		request.setCharacterEncoding("UTF-8");
		int childId = Integer.parseInt(request.getParameter("childId"));
		String childPicture = request.getParameter("childPicture");
		String childName= request.getParameter("childName");
		String userId= request.getParameter("userId");
		String rewardUmu= request.getParameter("rewardUmu");
		String rewardJouken= request.getParameter("rewardJouken");
		String rewardText= request.getParameter("rewardText");
        String comment = request.getParameter("comment");
	    Date date = new Date();


	        // 親ページからのコメントの登録処理
	    CommentDao coDao = new CommentDao();
	        if (coDao.insert(date,0,comment)) {
	            request.setAttribute("commentSuccess", true);
	        } else {
	            request.setAttribute("commentSuccess", false);
	        }

		ChildDao cDao = new ChildDao();
		List<Child> userList = cDao.select(new Child(0, childPicture, childName, userId, rewardUmu, rewardJouken, rewardText));

		request.setAttribute("userList", userList);

		// 更新または削除を行う
				ChildDao dDao = new ChildDao();
				if (request.getParameter("submit").equals("更新")) {
					if (dDao.update(new Child(childId, childPicture, childName, userId, rewardUmu, rewardJouken, rewardText))) {	// 更新成功
						request.setAttribute("result",
						new Result("更新成功！"));
					}
					else {												// 更新失敗
						request.setAttribute("result",
						new Result("更新失敗！"));
					}
				}
				else {
					if (dDao.delete(childId)) {	// 削除成功
						request.setAttribute("result",
						new Result("削除成功！"));
					}
					else {						// 削除失敗
						request.setAttribute("result",
						new Result("削除失敗！"));
					}
				}

		doGet(request, response);
	}





}




