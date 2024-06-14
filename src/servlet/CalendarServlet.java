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

import dao.CalendarDao;
import dao.ChildDao;
import dao.CommentDao;
import model.Calendar;
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
        List<CalendarComment> commentList = coDao.select(loginUser.getUserId());
        request.setAttribute("commentList", commentList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/calendar.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub

        HttpSession session = request.getSession();
        //User loginUser = (User)session.getAttribute("id");
        //String userID = loginUser.getUserId();
        CalendarDao childDao = new CalendarDao();
        Calendar calendardm = new Calendar(
                //自動採番の際は0に変更(int)
                null,
                "ダミー",
                "ダミー"
        );
        CalendarDao.insert(calendardm);

        request.setCharacterEncoding("UTF-8");
        int childId = Integer.parseInt(request.getParameter("childId"));
        String userId = request.getParameter("userId");
        String comment = request.getParameter("comment");
        Date date = new Date();
        String houseworkPoint = request.getParameter("houseworkPoint");

        CommentDao coDao = new CommentDao();
        if (coDao.insert(date, 0, comment)) {
            request.setAttribute("commentSuccess", true);
        } else {
            request.setAttribute("commentSuccess", false);
        }
    }
}
