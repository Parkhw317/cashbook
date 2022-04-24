package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import vo.Member;

@WebServlet("/InsertMemberController")
public class InsertMemberController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		Member member = new Member();

		request.setAttribute("member", member);
		request.getRequestDispatcher("/WEB-INF/view/InsertMemberForm.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		MemberDao memberDao = new MemberDao();
		
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String memberName = request.getParameter("memberName");
		String memberAddress = request.getParameter("memberAddress");
		String memberGender = request.getParameter("memberGender");
		String memberPhone = request.getParameter("memberPhone");
		String memberEmail = request.getParameter("memberEmail");
		
		

		System.out.println("[insert] memberId : " + memberId);
		System.out.println("[insert] memberPw : " + memberPw);;
		System.out.println("[insert] memberName : " + memberName);
		System.out.println("[insert] memberAddress : " + memberAddress);
		System.out.println("[insert] memberGender : " + memberGender);
		System.out.println("[insert] memberPhone : " + memberPhone);
		System.out.println("[insert] memberEmail : " + memberEmail);
		
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		member.setMemberName(memberName);
		member.setMemberAddress(memberAddress);
		member.setMemberGender(memberGender);
		member.setMemberPhone(memberPhone);
		member.setMemberEmail(memberEmail);
		
		memberDao.insertMember(member);
		
		response.sendRedirect(request.getContextPath()+"/LoginController"); 
		
		
		
		
		
		
		
		
		
	}

}
