package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HouseworkDao;
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
		"ダミー"
     );
    houseworkDao.insert(HW);
    doGet(request, response);
}

}