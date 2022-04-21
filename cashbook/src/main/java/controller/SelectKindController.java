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

@WebServlet("/SelectKindController")
public class SelectKindController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");

		String kind = request.getParameter("kind");
	
		System.out.println("[SelectKindController.java] 선택한 kind는 : " + kind);
		
		HashtagDao hashtagDao = new HashtagDao();
		List<Map<String, Object>> kindList = hashtagDao.selectKindRankList(kind);
		
		request.setAttribute("kindList", kindList);
		request.setAttribute("kind", kind);

		request.getRequestDispatcher("/WEB-INF/view/SelectKindTagList.jsp").forward(request, response);
		
		
	}

}
