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

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	
   
	// 로그인 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId != null) {
			// 이미 로그인이 되어 있는 상태라면
			response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
			return;
		}
		// 로그인 되어있는 멤버라면 리다이렉트 
		request.getRequestDispatcher("WEB-INF/view/Login.jsp").forward(request, response);
	}

	
	// 로그인 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 되어있는 멤버라면 리다이렉트  
		
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		// 모델 호출
		MemberDao memberDao = new MemberDao();
		String returnMemberId = memberDao.selectMemberByIdPw(member);
		
		if(returnMemberId == null) {
			// 로그인 실패시 로그인 폼을 재요청
			System.out.println("로그인실패 <-- LoginController.doPost()");
			response.sendRedirect(request.getContextPath()+"/LoginController?loginError"); 
			return;
		}
		// 로그인 성공
		HttpSession session = request.getSession(); // 현재 연결한 클라이언트(브라우저)에 대한 세션값을 받아옴
		session.setAttribute("sessionMemberId", returnMemberId);
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController"); 
	}
		
}
