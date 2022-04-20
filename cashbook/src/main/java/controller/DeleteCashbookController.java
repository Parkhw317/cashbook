package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CashbookDao;
import vo.Cashbook;


@WebServlet("/DeleteCashbookController")
public class DeleteCashbookController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cashbookNo = Integer.parseInt(request.getParameter("CashbookNo"));
		
		CashbookDao cashbookDao = new CashbookDao();
		
		int row = cashbookDao.deleteCashbook(cashbookNo);
		
		response.sendRedirect(request.getContextPath()+"/CashbookListByMonthController");
	}

}
