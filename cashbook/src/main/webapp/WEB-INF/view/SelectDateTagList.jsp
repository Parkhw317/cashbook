<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Select Date Tag List</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body class="container">
<%
	List<Map<String, Object>> dateTagList = (List<Map<String, Object>>)request.getAttribute("dateTagList");
%>
	<div class="container p-3 my-3 bg-primary text-light">
	<h1 align="center">날짜별 tag 리스트</h1>
	</div>
	<a href="<%=request.getContextPath()%>/TagController?" class="btn btn-secondary btn-sm" role="button">이전</a><br><br>
		<div style="text-align:center">
		<table class="table">
			<tr>
				<th>cashDate</th>
				<th>cashbookNo</th>
				<th>kind</th>
				<th>tag</th>
				<th>cash</th>
			</tr>
			<%
				for(Map<String, Object> map : dateTagList) {
			%>

				<tr>
					<td><%=map.get("cashDate")%></td>
					<td><%=map.get("cashbookNo")%></td>
					<td><%=map.get("kind")%></td>
					<td><%=map.get("tag")%></td>
					<td><%=map.get("cash")%></td>
				</tr>
			<%
				}
			%>
		</table> 
		</div>
	</body>
</html>