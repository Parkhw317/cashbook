package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CashbookDao;

@WebServlet("/CashbookListByMonthController")
public class CashbookListByMonthController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String sessionMemberId = (String)session.getAttribute("sessionMemberId");
		if(sessionMemberId == null) {
			//로그인 되지 않은 상태라면
			response.sendRedirect(request.getContextPath()+"/LoginController"); 
			return;
		}
		
		// 1) 월별 가계부 리스트 요청 분석
		Calendar now = Calendar.getInstance(); // ex) 2022.04.19
		int y = now.get(Calendar.YEAR);
		int m = now.get(Calendar.MONTH) + 1; // 0 - 1월, 1 - 2월, ... 11 - 12월
		
		if(request.getParameter("y") != null) {
			y = Integer.parseInt(request.getParameter("y"));
		}
		if(request.getParameter("m") != null) {
			m = Integer.parseInt(request.getParameter("m"));
		}
		if(m==0) {
			m = 12;
			y = y-1;
		}
		if(m==13) {
			m = 1;
			y = y+1;
			
		}
		
		System.out.println(y+" <-- y");
		System.out.println(m+" <-- m");
		
		
		// 시작시 필요한 공백 <TD>의 갯수를 구하는 알고리즘 -> startBlank
		// firstDay는 오늘날짜를 먼저구하여 날짜만 1일로 변경해서 구하자
		Calendar firstDay = Calendar.getInstance(); // ex) 2022.04.19
		firstDay.set(Calendar.YEAR, 1);
		firstDay.set(Calendar.MONTH, m-1);
		firstDay.set(Calendar.DATE, 1); // ex) 2022.04.01
		int dayOfWeek = firstDay.get(Calendar.DAY_OF_WEEK);
		// dayOfWeek  일 1, 월 2, 화 3 ... 토 7
		// startBlank 일 0, 월 1, 화 2 ... 토 6
		// 1)
		int startBlank = dayOfWeek - 1;
		// 마지막 날짜는 자바 달력 API를 이용하여 구하자
		// 2)
		int endDay = firstDay.getActualMaximum(Calendar.DATE); // firstDay달의 제일 큰 숫자 -> 마지막 날짜
		// startBlank와 endDay를 합의 결과에 endBlank를 더해서 7의 배수가 되도록
		// 3)
		int endBlank = 0;
		if ((startBlank+endDay)%7 != 0) {
			// 7에서 startBlank+endDay의 7로 나눈 나머지 값을 빼면
			endBlank = 7-((startBlank+endDay)%7);
		}
		// 4)
		int totalTd = startBlank + endDay + endBlank;
		
		
		// 2) 모델값(월별 가계부 리스트)을 반환하는 비지니스로직(모델) 호출
		CashbookDao cashbookDao = new CashbookDao();
		List<Map<String, Object>> list = cashbookDao.selectCashbookListByMonth(y, m);
		/*
		 달력 출력에 필요한 모델 값(1), 2), 3), 4)) 데이터 베이스에서 받환된 모델 값(list, y출력년도, m출력월) + 오늘 날짜(today) 
		 */
		request.setAttribute("startBlank", startBlank);
		request.setAttribute("endDay", endDay);
		request.setAttribute("endBlank", endBlank);
		request.setAttribute("totalTd", totalTd);
		
		request.setAttribute("list", list);
		request.setAttribute("y", y);
		request.setAttribute("m", m);
		
		
		// 3) 뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/CashbookListByMonth.jsp").forward(request, response);
		
	}

}
