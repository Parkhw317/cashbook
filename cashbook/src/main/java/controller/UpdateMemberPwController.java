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


@WebServlet("/UpdateMemberPwController")
public class UpdateMemberPwController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) {
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}
		
		request.setCharacterEncoding("utf-8");
		
		String memberId = request.getParameter("memberId");
		
		MemberDao memberDao = new MemberDao();
		Member member = new Member();
		
		member = memberDao.selectMemberOne(sessionMemberId);
		
		request.setAttribute("member", member);
		
		request.getRequestDispatcher("/WEB-INF/view/UpdateMemberPw.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) {
			//로그인 되지 않은 상태라면
			response.sendRedirect(request.getContextPath()+"/LoginController"); 
			return;
		
		}
		
		
		request.setCharacterEncoding("utf-8");
		
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String memberName = request.getParameter("memberName");
		String memberAddress = request.getParameter("memberAddress");
		String memberGender = request.getParameter("memberGender");
		String memberPhone = request.getParameter("memberPhone");
		String memberEmail = request.getParameter("memberEmail");
		String changePw = request.getParameter("changePw");
		
		System.out.println("[update] memberPw : " + memberPw);
		System.out.println("[update] memberName : " + memberName);
		System.out.println("[update] memberAddress : " + memberAddress);
		System.out.println("[update] memberGender : " + memberGender);
		System.out.println("[update] memberPhone : " + memberPhone);
		System.out.println("[update] memberEmail : " + memberEmail);
		
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		member.setMemberName(memberName);
		member.setMemberAddress(memberAddress);
		member.setMemberGender(memberGender);
		member.setMemberPhone(memberPhone);
		member.setMemberEmail(memberEmail);
		member.setChangePw(changePw);
		
		MemberDao memberDao = new MemberDao();
		memberDao.updateMemberPw(member, memberPw);
		
		
		response.sendRedirect(request.getContextPath()+"/LogoutController"); 
		
		
		
	}

}
