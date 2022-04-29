<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CashbookListByMonth</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body class = "container">
	<%
		List<Map<String, Object>> list = (List<Map<String, Object>>) request.getAttribute("list");
		int y = (Integer)request.getAttribute("y");
		int m = (Integer)request.getAttribute("m");
		
		int startBlank = (Integer)request.getAttribute("startBlank");
		int endDay = (Integer)request.getAttribute("endDay");
		int endBlank = (Integer)request.getAttribute("endBlank");
		int totalTd = (Integer)request.getAttribute("totalTd");
		
		
		
		System.out.println("[cashbookListByMonth.jsp] List size : " + list.size());
		System.out.println("[cashbookListByMonth.jsp] 요청한 y : " + y);
		System.out.println("[cashbookListByMonth.jsp] 요청한 m : " + m);
		
		System.out.println("[cashbookListByMonth.jsp] 요청한 startBlank : " + startBlank);
		System.out.println("[cashbookListByMonth.jsp] 요청한 endDay : " + endDay);
		System.out.println("[cashbookListByMonth.jsp] 요청한 endBlank : " + endBlank);
		System.out.println("[cashbookListByMonth.jsp] 요청한 endBlank : " + totalTd);
	
	%>
	<br>
	<div style="text-align:right">
		<a href="<%=request.getContextPath()%>/SelectMemberOneController?=<%=session.getAttribute("sessionMemberId")%>">
		<%=session.getAttribute("sessionMemberId") %></a>님 반갑습니다. &nbsp;&nbsp;
		<a href="<%=request.getContextPath()%>/LogoutController" class="btn btn-outline-warning btn-sm" role="button">로그아웃</a>
	</div>

	<div class="container p-3 my-3 bg-warning text-white">
	<h2 align="center"><%=y%>년 <%=m%>월</h2>
	</div>
	<div>
		<a href="<%=request.getContextPath()%>/TagController" class="btn btn-primary btn-sm" role="button">tags</a>
	</div>
	<div align="right">
		<a href="<%=request.getContextPath()%>/CashbookListByMonthController?y=<%=y%>&m=<%=m-1%>" class="btn btn-outline-warning btn-sm" role="button">이전 달</a>
		<a href="<%=request.getContextPath()%>/CashbookListByMonthController?y=<%=y%>&m=<%=m+1%>" class="btn btn-outline-warning btn-sm" role="button">다음 달</a>
		<br>
		<br>
	</div>
	<!-- 
		1) 이번날 1일의 요일 firstDayYoil -> 요일 -> startBlank -> 일 0, 월 1, 화 2, ... 토 6
		2) 이번달 마지막날짜 endDay
		3) endBlank -> totalBlank
		4) td의 갯수 1 ~ totalBlank
				+
		5) 가계부 list
		6) 오늘 날짜
	 -->
	 <table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th>일</th>
				<th>월</th>
				<th>화</th>
				<th>수</th>
				<th>목</th>
				<th>금</th>
				<th>토</th>
			</tr>
		</thead>
	 	<tr>
	 		<%
               for(int i=1; i<=totalTd; i+=1) {
                  if((i-startBlank) > 0 && (i-startBlank) <= endDay) {
                     String c = "";
                     if(i%7==0) {
                        c = "text-primary";
                     } else if(i%7==1) {
                        c = "text-danger";
                     }
            %>
                     <td class="<%=c%>">
                        <%=i-startBlank%>
                        <a href="<%=request.getContextPath()%>/InsertCashbookController?y=<%=y%>&m=<%=m%>&d=<%=i-startBlank%>" class="btn btn-light">입력</a>
                     	<div>
                     		<%
                     		// 해당 날짜의 cashbook 목록 출력
                     		for (Map map : list){
                     			if((Integer)map.get("day") == (i-startBlank)){
                     				
                     			
                     		%>
                     			<div>
                     			<a href="<%=request.getContextPath()%>/CashbookOneController?cashbookNo=<%=map.get("cashbookNo")%>">
                     			[<%=map.get("kind")%>]
                     			<%=map.get("cash")%>원
                     			<%=map.get("memo")%> ...
                     			</a>
                     			</div>
                     		<%
                     				}
                     			}
                     		%>
                     		
                     	</div>
                     </td>
            <%
                  } else {
            %>
                     <td>&nbsp;</td>
            <%         
                  }
                  if(i<totalTd && i%7==0) {
            %>
                     </tr><tr><!-- 새로운 행을 추가시키기 위해 -->
            <%         
                  }
               }
            %>
         </tr>
      </tbody>
   </table>
</body>
</html>