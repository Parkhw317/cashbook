package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashbookDao;
import vo.Cashbook;


@WebServlet("/CashbookOneController")
public class CashbookOneController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		String cashDate = "";
		String kind = "";
		int cash = 0;
		String memo = "";
		String memberId="";
		
		
		System.out.println(cashbookNo + " ◀◀ cashbookNo CashbookOneController.doGet()");

		
		CashbookDao cashbookDao = new CashbookDao();
		Cashbook cashbook = cashbookDao.selectCashbookOne(cashbookNo);
		
		request.setAttribute("cashbookNo", cashbook.getCashbookNo());
		request.setAttribute("cashDate", cashbook.getCashDate());
		request.setAttribute("kind", cashbook.getKind());
		request.setAttribute("cash", cashbook.getCash());
		request.setAttribute("memo", cashbook.getMemo());
		request.setAttribute("memberId", cashbook.getMemberId());
		
		
		request.getRequestDispatcher("/WEB-INF/view/CashbookOne.jsp").forward(request, response);
		

		
		
		
	}

}
