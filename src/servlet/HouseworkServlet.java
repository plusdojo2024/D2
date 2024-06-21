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
		request.setCharacterEncoding("UTF-8");
		List<HouseWork> hwList = new ArrayList<>();
		for (int i = 1; i <= 6; i++) {
			String x = request.getParameter("my_p" + i);

			if (x == null) {
				break;
			}
			HouseWork hw = new HouseWork();
			hw.setHouseworkName(request.getParameter("my_z" + i));
			hw.setHouseworkCheck(true);
			hwList.add(hw);
		}
		HouseworkDao hwDao = new HouseworkDao();
		if (request.getParameter("my_save").equals("ホームにもどる")) {
			boolean result = false;
			for (HouseWork hw : hwList) {
				result = hwDao.updateF(hw);
				if (result == false) {
					break;
				}
			}
			if (result) {
				request.setAttribute("result",
						new Result("保存成功！", "更新を実施しました", "/D2/HouseworkServlet"));
			} else {
				request.setAttribute("result",
						new Result("保存失敗…", "更新出来ませんでした", "/D2/HouseworkServlet"));
			}
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(request, response);
	}

 }

