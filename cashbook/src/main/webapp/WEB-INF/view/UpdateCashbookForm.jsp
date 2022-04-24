<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<%

	Cashbook cashbook = (Cashbook)request.getAttribute("cashbook");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Cashbook Form</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body class = "container">
	<div class="container p-3 my-3 bg-success text-white">
	<h1 align="center">Update Cashbook</h1>
	</div>
	
	<form action="<%=request.getContextPath()%>/UpdateCashbookController" method="post">
		<input type ="hidden" name="cashbookNo" value=<%=cashbook.getCashbookNo() %>>
		<table class="table table-bordered">
			<tr>
				<td>날짜</td>
				<td>
					<input type="text" name="cashDate" 
							value="<%=cashbook.getCashDate()%>" readonly="readonly">
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
			<tr>
				<td>kind</td>
				<td>
					<input type = "radio" name="kind" value="<%=cashbook.getKind()%>" checked="checked"><%=cashbook.getKind()%><br>
					<input type="radio" name="kind" value="수입">수입
					<input type="radio" name="kind" value="지출">지출
				</td>
			</tr>
			<tr>
				<td>cash</td>
				<td><input type="number" name="cash" value="<%=cashbook.getCash()%>"></td>
			</tr>
			<tr>
				<td>memo</td>
				<td>
					<textarea rows="4" cols="50" name="memo"><%=cashbook.getMemo()%></textarea>
				</td>
			</tr>
		</table>
		
		<button type="submit" class="btn btn-outline-primary btn-sm">입력</button>
		<a href="<%=request.getContextPath()%>/CashbookOneController?cashbookNo=<%=cashbook.getCashbookNo()%>" class="btn btn-secondary btn-sm" role="button">수정취소</a>
	</form>
</body>
</html>
