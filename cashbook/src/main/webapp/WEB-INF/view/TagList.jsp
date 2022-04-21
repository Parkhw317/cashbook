<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>tag rank</h1>
	<div>지출별 검색</div>
	<table border="1">
		<tr>
			<th>rank</th>
			<th>tag</th>
			<th>count</th>
		</tr>
		<%
			List<Map<String, Object>> exList = (List<Map<String, Object>>)request.getAttribute("exList");
			for(Map<String, Object> map : exList) {
		%>
				<tr>
					<td><%=map.get("rank")%></td>
					<td><%=map.get("tag")%></td>
					<td><%=map.get("count")%></td>
				</tr>
		<%			
			}
		%>
	</table>
	
		<div>수입별 검색</div>
	<table border="1">
		<tr>
			<th>rank</th>
			<th>tag</th>
			<th>count</th>
		</tr>
		<%
		List<Map<String, Object>> inList = (List<Map<String, Object>>)request.getAttribute("inList");
			for(Map<String, Object> map : inList) {
		%>
				<tr>
					<td><%=map.get("rank")%></td>
					<td><%=map.get("tag")%></td>
					<td><%=map.get("count")%></td>
				</tr>
		<%			
			}
		%>
	</table>

	<div> 태그별 상세 검색</div>
	<table border="1">
		<tr>
			<th>rank</th>
			<th>tag</th>
			<th>count</th>
		</tr>
		<%
			List<Map<String, Object>> tagList = (List<Map<String, Object>>)request.getAttribute("tagList");
			for(Map<String, Object> map : tagList) {
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
</body>
</html>
