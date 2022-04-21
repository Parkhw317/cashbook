<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Select Kind Tag List</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<%
	List<Map<String, Object>> kindList = (List<Map<String, Object>>)request.getAttribute("kindList");
%>
	<h1>수입,지출 항목별 리스트</h1>
		<table border="1">
			<tr>
				<th>kind</th>
				<th>tag</th>
				<th>count</th>
				<th>rank</th>
				
			</tr>
			<%
				for(Map<String, Object> map : kindList) {
			%>

				<tr>
					<td><%=map.get("kind")%></td>
					<td><%=map.get("tag")%></td>
					<td><%=map.get("count")%></td>
					<td><%=map.get("rank")%></td>
					
				</tr>
			<%
				}
			%>
		</table> 
		<a href="<%=request.getContextPath()%>/TagController?">이전</a>
	</body>
</html>