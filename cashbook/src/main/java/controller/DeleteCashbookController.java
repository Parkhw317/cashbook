package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CashbookDao;
import vo.Cashbook;



@WebServlet("/DeleteCashbookController")
public class DeleteCashbookController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) {
			//로그인 되지 않은 상태라면
			response.sendRedirect(request.getContextPath()+"/LoginController"); 
			return;
		}
		
		request.setCharacterEncoding("utf-8");
		
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		

		
		request.getRequestDispatcher("/WEB-INF/view/DeleteCashbookForm.jsp").forward(request, response);
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
		
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		String memberPw = request.getParameter("memberPw");
		
		System.out.println("[DeleteCashbookController.doGet()] cashbookNo : " + cashbookNo);
		System.out.println("[DeleteCashbookController.doGet()] sessionMemberId : " + sessionMemberId);
		System.out.println("[DeleteCashbookController.doGet()] memberPw : " + memberPw);
		
		CashbookDao cashbookDao = new CashbookDao();
		
		int row = cashbookDao.deleteCashbook(cashbookNo, sessionMemberId, memberPw);
		System.out.println("[DeleteCashbookController.doGet()] row : " + row);
		
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
	
}
	
}