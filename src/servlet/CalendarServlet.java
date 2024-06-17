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
import dao.CommentDao;
import dao.HouseworkDao;
import model.CalendarComment;
import model.HouseWork;
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

        /*ChildDao cDao = new ChildDao();
        List<Child> userList = cDao.select(loginUser.getUserId());
        request.setAttribute("userList", userList);*/
        //select(loginUser.getUserId(),Integer.parseInt(datey),Integer.parseInt(datem)

        //commentテーブルから日付、ユーザーID、コメントを取得　コメント本体はどこに？
        CommentDao coDao = new CommentDao();
        List<CalendarComment> commentList = coDao.select(null, 0, 0);
        request.setAttribute("commentList", commentList);

        //houseworkテーブルから家事の名前、各日のポイントを取得
        HouseworkDao hoDao = new HouseworkDao();
        List<HouseWork> housweworkList = hoDao.select(HouseWork.getHouseworkName(),HouseWork.getgetHouseworkPoint());
        request.setAttribute("HouseWorkList",housweworkList);

        /*現在日時を取得
        Date date = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy");//yyyy-MM-dd
		String datey = fmt.format(date);
		fmt = new SimpleDateFormat("MM");
		String datem = fmt.format(date);*/




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

		//カレンダーテーブルに家事達成時の日付、ユーザーID、家事名をしまう
		request.setCharacterEncoding("UTF-8");
		//フォームからのデータを取得
		String clickDate = request.getParameter("clickDate");
		String clickChild = request.getParameter("clickChild");
		String clickHousework = request.getParameter("clickHousework");
		//インスタンスを生成し、データを設定する
		CalendarDao caDao = new CalendarDao();
		if (caDao.insert(clickDate,clickChild,	clickHousework)) {	// 登録成功
			request.setAttribute("houseworkresult", true);
		} else {
			request.setAttribute("houseworkresult", false);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/calendar.jsp");
			dispatcher.forward(request, response);

		doGet(request, response);
    }
}
