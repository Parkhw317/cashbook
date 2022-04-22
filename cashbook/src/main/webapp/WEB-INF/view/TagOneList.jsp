<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TagKindList</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body class = "container">
<%
	List<Map<String, Object>> tagOneList = (List<Map<String, Object>>)request.getAttribute("tagOneList");
%>
	<div class="container p-3 my-3 bg-primary text-white">
	<h1 align="center">태그 항목별 리스트</h1>
	</div>
	<a href="<%=request.getContextPath()%>/TagController?" class="btn btn-secondary btn-sm" role="button">이전</a>
		<div style="text-align:center">
		<table class="table"><br><br>
			<tr>
				<th>tag</th>
				<th>cashbookNo</th>
				<th>cashDate</th>
				<th>kind</th>
				<th>cash</th>
				<th>memo</th>
			</tr>
			<%
				for(Map<String, Object> map : tagOneList) {
			%>

				<tr>
					<td><%=map.get("tag")%></td>
					<td><%=map.get("cashbookNo")%></td>
					<td><%=map.get("cashDate")%></td>
					<td><%=map.get("kind")%></td>
					<td><%=map.get("cash")%></td>
					<td><%=map.get("memo")%> </td>
				</tr>
			<%
				}
			%>
		</table> 
		</div>
	</body>
</html>