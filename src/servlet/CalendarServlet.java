package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import model.CalendarComment;
import model.Child;
import model.User;

/**
 * Servlet implementation class CalendarServlet
 */
@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarServlet() {
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
        User loginUser = (User) session.getAttribute("id");

        ChildDao cDao = new ChildDao();
        List<Child> userList = cDao.select(loginUser.getUserId());
        request.setAttribute("userList", userList);

        CommentDao coDao = new CommentDao();
        //現在日時を取得
        Date date = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy");//yyyy-MM-dd
		String datey = fmt.format(date);
		fmt = new SimpleDateFormat("MM");
		String datem = fmt.format(date);

        List<CalendarComment> commentList = coDao.select(loginUser.getUserId(),Integer.parseInt(datey),Integer.parseInt(datem));
        request.setAttribute("commentList", commentList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/calendar.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/D2/LoginServlet");
			return;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/calendar.jsp");
			dispatcher.forward(request, response);

		doGet(request, response);
    }
}
