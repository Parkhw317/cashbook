package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import vo.Member;


@WebServlet("/SelectMemberOneController")
public class SelectMemberOneController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) {
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}
		
		
		request.setCharacterEncoding("utf-8");
		String memberName = "";
		String memberAddress = "";
		String memberGender = "";
		String memberPhone = "";
		String memberEmail = "";
		
		
		System.out.println("[SelectMemberOneController.doGet()] sessionMemberId : " + sessionMemberId);

		
		MemberDao memberDao = new MemberDao();
		Member member = memberDao.selectMemberOne(sessionMemberId);
		
		request.setAttribute("memberId", member.getMemberId());
		request.setAttribute("memberName", member.getMemberName());
		request.setAttribute("memberAddress", member.getMemberAddress());
		request.setAttribute("memberGender", member.getMemberGender());
		request.setAttribute("memberPhone", member.getMemberPhone());
		request.setAttribute("memberEmail", member.getMemberEmail());
		
		request.getRequestDispatcher("/WEB-INF/view/MemberOne.jsp").forward(request, response);
		
	}

}
