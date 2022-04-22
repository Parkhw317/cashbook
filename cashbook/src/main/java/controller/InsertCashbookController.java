package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashbookDao;
import vo.Cashbook;

@WebServlet("/InsertCashbookController")
public class InsertCashbookController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String y = request.getParameter("y");
		String m = request.getParameter("m");
		String d = request.getParameter("d");
		String cashDate = y+"-"+m+"-"+d;
		request.setAttribute("cashDate", cashDate);
		request.getRequestDispatcher("/WEB-INF/view/InsertCashbookForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cashDate = request.getParameter("cashDate");
		String kind = request.getParameter("kind");
		int cash = Integer.parseInt(request.getParameter("cash"));
		String memo = request.getParameter("memo");
		// String memberId = request.getParameter("memberId");
		
		
		System.out.println(cashDate + " <--cashDate InsertCashbookController.doPost()");
		System.out.println(kind + " <--kind InsertCashbookController.doPost()");
		System.out.println(cash + " <--cash InsertCashbookController.doPost()");
		System.out.println(memo + " <--memo InsertCashbookController.doPost()");
		
		
		Cashbook cashbook = new Cashbook();
		cashbook.setCashDate(cashDate);
		cashbook.setKind(kind);
		cashbook.setCash(cash);
		cashbook.setMemo(memo);
		// cashbook.setMemberId(memberId);
		
		
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
		System.out.println(hashtag.size() + " <--hashtag.size InsertCashbookController.doPost()");
		for(String h : hashtag) {
			System.out.println(h + " <-- hashtag InsertCashbookController.doPost()");
		}
		
		CashbookDao cashbookDao = new CashbookDao();
		cashbookDao.insertCashbook(cashbook, hashtag);
		
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
	}
}
