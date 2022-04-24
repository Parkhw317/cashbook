package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HashtagDao;



@WebServlet("/TagOneListContoller")
public class TagOneListContoller extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) {
			//로그인 되지 않은 상태라면
			response.sendRedirect(request.getContextPath()+"/LoginController"); 
			return;
		
		}
			
	
		request.setCharacterEncoding("utf-8");	
		String tag = request.getParameter("tag");
	
		System.out.println("[TagOneListContoller.java] 선택한 tag는 : " + tag);
		
		HashtagDao hashtagDao = new HashtagDao();
		List<Map<String, Object>> tagOneList = hashtagDao.selectTagOneList(tag);
		
		request.setAttribute("tagOneList", tagOneList);
		request.setAttribute("tag", tag);

		request.getRequestDispatcher("/WEB-INF/view/TagOneList.jsp").forward(request, response);
		
	}

}
