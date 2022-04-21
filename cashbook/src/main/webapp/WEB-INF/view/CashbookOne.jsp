<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cashbook One</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body class = "container">
	<div class="container p-3 my-3 bg-secondary text-white">
	<h1 align="center">Cashbook 상세보기</h1>
	</div>
	
	<div align="right">
		<a href="<%=request.getContextPath()%>/UpdateCashbookController?" class="btn btn btn-primary btn-sm" role="button">수정</a>
		<a href="<%=request.getContextPath()%>/DeleteCashbookController?cashbookNo=<%=request.getAttribute("cashbookNo")%>" class="btn btn btn-danger btn-sm" role="button">삭제</a>
	</div>
	<br>
		<table class="table table-striped">
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
		<a href="<%=request.getContextPath()%>/CashbookListByMonthController?" class="btn btn-secondary btn-sm" role="button">이전</a>
	</div>
</body>
</html>