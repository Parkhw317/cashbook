<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cashbook One</title>
</head>
<body>
	<h1>Cashbook 상세보기</h1>
		<table border="1">
			<tr>
				<td>cashbookNo</td>
				<td><%=request.getAttribute("cashbookNo") %></td>
				
			</tr>
			<tr>
				<td>cashDate</td>
				<td><%=request.getAttribute("cashDate") %></td>
			</tr>
			<tr>
				<td>kind</td>
				<td><%=request.getAttribute("kind") %></td>
			</tr>
			<tr>
				<td>cash</td>
				<td><%=request.getAttribute("cash") %></td>
			</tr>
			<tr>
				<td>memo</td>
				<td><%=request.getAttribute("memo") %></td>
			</tr>
		</table>

	
	<div>
		<a href="<%=request.getContextPath()%>/UpdateCashbookController?">수정</a>
		<a href="<%=request.getContextPath()%>/DeleteCashbookController?cashbookNo=<%=request.getAttribute("cashbookNo")%>">삭제</a>
	</div>
</body>
</html>