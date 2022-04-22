<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body class="container">
	<div class="container p-3 my-3 bg-info text-white">
		<h1 align="center">tag rank</h1>
	</div>
	<a href="<%=request.getContextPath()%>/CashbookListByMonthController?" class="btn btn-secondary btn-sm" role="button">이전</a>
	<div class="row">
		<div class="col-md-6">
			<h4 align="center">태그별 상세 검색</h4><br>
			<div style="text-align:center">
			<table class = "table">
				
				<tr>
					<th>rank</th>
					<th>tag</th>
					<th>count</th>
				</tr>
				<%
				List<Map<String, Object>> tagList = (List<Map<String, Object>>) request.getAttribute("tagList");
				for (Map<String, Object> map : tagList) {
				%>
				<tr>
					<td><%=map.get("rank")%></td>
					<td><a href="<%=request.getContextPath()%>/TagOneListContoller?tag=<%=map.get("tag")%>"><%=map.get("tag")%></a></td>
					<td><%=map.get("count")%></td>
				</tr>
				<%
				}
				%>
				
			</table>
			</div>
		</div>
		<div class="col-md-6">
			<h4 align="center">수입, 지출별 검색</h4><br>
			<form action="<%=request.getContextPath()%>/SelectKindController"
				method="get">
				<div style="text-align:center">
				<table class = "table">
					<tr>
						<td>kind</td>
						<td>
							<div class="radio">
								<label> <input type="radio" name="kind" value="수입" checked> 수입 </label>
							</div>
						</td>
						<td>
							<div class="radio">
								<label> <input type="radio" name="kind" value="지출"> 지출 </label>
							</div>
						</td>
					</tr>
				</table>
				</div>
				<button type="submit" class="btn btn-outline-info btn-lg btn-block">검색</button>
			</form>
		</div>



	</div>
</body>
</html>
