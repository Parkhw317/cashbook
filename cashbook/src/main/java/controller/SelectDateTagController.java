package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HashtagDao;

@WebServlet("/SelectDateTagController")
public class SelectDateTagController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");

		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		System.out.println("[SelectDateTagController.java] 입력한 startDate는 : " + startDate);
		System.out.println("[SelectDateTagController.java] 입력한 endDate는 : " + endDate);
		
		HashtagDao hashtagDao = new HashtagDao();
		List<Map<String,Object>> dateTagList = hashtagDao.selectDateTagList(startDate, endDate);
		
		request.setAttribute("dateTagList", dateTagList);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		
		request.getRequestDispatcher("/WEB-INF/view/SelectDateTagList.jsp").forward(request, response);
		
	}

}
