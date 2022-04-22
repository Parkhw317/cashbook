<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insert Cashbook Form</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body class = "container">
	<div class="container p-3 my-3 bg-success text-white">
	<h1 align="center">insert Cashbook</h1>
	</div>
	
	<form action="<%=request.getContextPath()%>/InsertCashbookController" method="post">
		<table class="table table-bordered">
			<tr>
				<td>날짜</td>
				<td>
					<input type="text" name="cashDate" 
							value="<%=(String)request.getAttribute("cashDate")%>" readonly="readonly">
				</td>
			</tr>
			<!-- 
			<tr>
				<td>memberId</td>
				<td>
					<input type="text" name="memberId" >
				</td>
			 -->
			</tr>
			<tr>
				<td>kind</td>
				<td>
					<input type="radio" name="kind" value="수입">수입
					<input type="radio" name="kind" value="지출">지출
				</td>
			</tr>
			<tr>
				<td>cash</td>
				<td><input type="number" name="cash"></td>
			</tr>
			<tr>
				<td>memo</td>
				<td>
					<textarea rows="4" cols="50" name="memo"></textarea>
				</td>
			</tr>
		</table>

		<a href="<%=request.getContextPath()%>/CashbookListByMonthController?" class="btn btn-secondary btn-sm" role="button">이전</a>
		<button type="submit" class="btn btn-outline-success btn-sm">입력</button>
	</form>
</body>
</html>
