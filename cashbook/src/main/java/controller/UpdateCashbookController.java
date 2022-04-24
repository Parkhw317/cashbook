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
import dao.MemberDao;
import vo.Cashbook;
import vo.Member;

@WebServlet("/UpdateCashbookController")
public class UpdateCashbookController extends HttpServlet {
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
		System.out.println("[UpdateCashbookController.doGet()] cashbookNo " + cashbookNo);

		
		CashbookDao cashbookDao = new CashbookDao();
		Cashbook cashbook = new Cashbook();
		
		cashbook = cashbookDao.selectCashbookOne(cashbookNo);
	
		request.setAttribute("cashbook", cashbook);
		
		
		request.getRequestDispatcher("/WEB-INF/view/UpdateCashbookForm.jsp").forward(request, response);
	
		
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
		String kind = request.getParameter("kind");
		int cash = Integer.parseInt(request.getParameter("cash"));
		String memo = request.getParameter("memo");
		String cashDate = request.getParameter("cashDate");
		String memberPw = request.getParameter("memberPw");
		
		System.out.println("update kind : " + kind);
		System.out.println("update cash : " + cash);
		System.out.println("update memo : " + memo);
		System.out.println("update cashbookNo : " + cashbookNo);
		System.out.println("update cashDate : " + cashDate);
		
		List<String> hashtag = new ArrayList<>();
		String memo2 = memo.replace("#", " #");
		String[] arr = memo2.split(" ");
		for(String s : arr) {
			if(s.startsWith("#")) {
				String temp = s.replace("#", "");
				if(temp.length()>0) {
					hashtag.add(temp);
				}
			}
		}
		System.out.println("[UpdateCashbookController.doPost()] hashtag.size() : " + hashtag.size());
		for(String h : hashtag) {
			System.out.println("[UpdateCashbookController.doPost()] h : " + h);
		}


		Cashbook cashbook = new Cashbook();
		cashbook.setCashbookNo(cashbookNo);
		cashbook.setCashDate(cashDate);
		cashbook.setKind(kind);
		cashbook.setCash(cash);
		cashbook.setMemo(memo);
		cashbook.setMemberId(sessionMemberId);
		
		
		
		CashbookDao cashbookDao = new CashbookDao();
		cashbookDao.updateCashbook(cashbook, hashtag, memberPw);
		
		
		
		
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
		
		
		
		
	}

}
