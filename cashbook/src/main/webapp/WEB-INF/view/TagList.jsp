<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>

	<h1>tag rank</h1>
		<div>수입, 지출별 검색</div>
			<form action="<%=request.getContextPath()%>/SelectKindController" method="get">
				<table border="1">
				<tr>
					<td>kind</td>
					<td>
						<input type="radio" name="kind" value="수입">수입
						<input type="radio" name="kind" value="지출">지출
					</td>
				</tr>
				</table>
				<button type="submit">검색</button>
			</form>

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
