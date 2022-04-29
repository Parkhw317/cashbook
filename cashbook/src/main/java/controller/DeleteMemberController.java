package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;


@WebServlet("/DeleteMemberController")
public class DeleteMemberController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
			if(sessionMemberId == null) {
				response.sendRedirect(request.getContextPath()+"/LoginController");
				return;
			}
		
		request.setCharacterEncoding("utf-8");
		
		request.getRequestDispatcher("/WEB-INF/view/DeleteMember.jsp").forward(request, response);
		
		
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
			if(sessionMemberId == null) {
				response.sendRedirect(request.getContextPath()+"/LoginController");
				return;
			}
		
		request.setCharacterEncoding("utf-8");
		
		String memberPw = request.getParameter("memberPw");
		
		System.out.println("DeleteMemberController.doGet()] sessionMemberId : " + sessionMemberId);
		System.out.println("DeleteMemberController.doGet()] memberPw : " + memberPw);;
		
		MemberDao memberDao = new MemberDao();
		
		int row = memberDao.deleteMember(sessionMemberId, memberPw);
		System.out.println("[DeleteMemberController.doPost()] row : " + row);
		
		response.sendRedirect(request.getContextPath()+"/LogoutController");
		
		
	}

}
