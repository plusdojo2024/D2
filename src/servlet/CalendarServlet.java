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

import dao.CalendarDao;
import dao.CommentDao;
import model.Calendar;
import model.CalendarComment;
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
       //リクエストパラメータから年と月を取得し、それが存在する場合はそれを基にjava.util.Calendar を使用して日付を設定。
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        //現在日時を取得
        Date date = new Date();
        //月と年が入力されているとき、文字列のyearとmonthをCalendarクラスのyearとmonthにint型で代入している
        if(year != null && month != null && ! year.equals("")&& ! month.equals("")) {
        	java.util.Calendar c = java.util.Calendar.getInstance();
            c.set(java.util.Calendar.YEAR, Integer.parseInt(year));
            c.set(java.util.Calendar.MONTH, Integer.parseInt(month)-1);
            c.set(java.util.Calendar.DATE, 1);
            date = c.getTime();
        }
        //SimpleDateFormat を使用して、日付から年と月を取得。
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy");//yyyy-MM-dd
		int datey = Integer.parseInt(fmt.format(date));
		fmt = new SimpleDateFormat("MM");
		int datem = Integer.parseInt(fmt.format(date));


        //commentテーブルから日付、ユーザーID、コメントを取得　
        CommentDao coDao = new CommentDao();
        List<CalendarComment> commentList = coDao.select(loginUser.getUserId(),datey,datem);

        request.setAttribute("commentList", commentList);

        //houseworkテーブルから家事の名前、各日のポイントを取得

        CalendarDao CaDao = new CalendarDao();
        List<Calendar> datesList = CaDao.select(datey, datem);

        request.setAttribute("datesList",datesList);
        request.setAttribute("currentMonth",datem);
        request.setAttribute("currentYear",datey);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/calendar.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

    }
}
