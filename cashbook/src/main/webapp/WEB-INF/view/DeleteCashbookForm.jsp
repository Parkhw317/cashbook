<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Cashbook Form</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body class = "container">
	<div class="container p-3 my-3 bg-success text-white">
	<h1 align="center">Delete Cashbook</h1>
	</div>
	
	<form action="<%=request.getContextPath()%>/DeleteCashbookController" method="post">
		<table class="table table-bordered">
			<tr>
				<td>cashbookNo</td>
				<td>
					<input type="text" name="cashbookNo" 
							value="<%=request.getParameter("cashbookNo")%>" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>memberId</td>
				<td>
					<input type="text" name="memberId" value="<%=(String)request.getSession().getAttribute("sessionMemberId")%>" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>memberPw</td>
				<td>
					<input type="password" name="memberPw">
				</td>
			</tr>
		
		
	</table>
	<button type="submit" class="btn btn-outline-danger btn-sm">삭제</button>
	<a href="<%=request.getContextPath()%>/CashbookOneController?cashbookNo=<%=request.getParameter("cashbookNo")%>" class="btn btn-secondary btn-sm" role="button">삭제취소</a>
	
	</form>
	
	
</body>
</html>
