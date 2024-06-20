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
import dao.ChildDao;
import dao.HouseworkDao;
import model.Calendar;
import model.Child;
import model.HouseWork;
import model.User;

/**
 * Servlet implementation class HouseworkServlet
 */
@WebServlet("/HouseworkServlet")
public class HouseworkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HouseworkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/D2/LoginServlet");
			return;
		}

//		Child child = (Child)session.getAttribute("id");
//		HouseWork hw = (HouseWork)session.getAttribute("id");
		//String userId = (String)session.getAttribute("id");
		//Child child = new Child(userId);

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
		// TODO Auto-generated method stub
		doGet(request, response);


    HttpSession session = request.getSession();
    User loginUser = (User)session.getAttribute("id");
    String userID = loginUser.getUserId();
    HouseworkDao houseworkDao = new HouseworkDao();
    HouseWork HW = new HouseWork(
		"ダミー",//自動採番の際は0に変更(int)
		"ダミー",
		"ダミー",
		"ダミー",
		"ダミー",
		userID,
		"ダミー",
		"ダミー",
		false
     );
    houseworkDao.insert(HW);
    doGet(request, response);

    //カレンダーテーブルに家事達成時の日付、ユーザーID、家事名をしまう
		request.setCharacterEncoding("UTF-8");
		//フォームからのデータを取得
		String clickDate = request.getParameter("clickDate");
		String clickChild = request.getParameter("clickChild");
		String clickHousework = request.getParameter("clickHousework");
		//インスタンスを生成し、データを設定する
		CalendarDao caDao = new CalendarDao();
		Calendar Ca =new Calendar(
				null,
				"ダミー",
				"ダミー");
		if (caDao.insert(Ca)) {	// 登録成功
			request.setAttribute("houseworkresult", true);
		} else {
			request.setAttribute("houseworkresult", false);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/calendar.jsp");
			dispatcher.forward(request, response);

		doGet(request, response);
  }
}
