<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member One</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body class = "container">
	<div class="container p-3 my-3 bg-success text-white">
	<h1 align="center">Member 상세보기</h1>
	</div>
	
	<div align="right">
		<a href="<%=request.getContextPath()%>/UpdateMemberPwController?memberId=<%=request.getAttribute("memberId")%>" class="btn btn btn-primary btn-sm" role="button">회원정보 수정</a>
		<a href="<%=request.getContextPath()%>/DeleteMemberController?memberId=<%=request.getAttribute("memberId")%>" class="btn btn btn-danger btn-sm" role="button">회원 탈퇴</a>
	</div>
	<br>
		<table class="table table-striped">
			<tr>
				<td>memberId</td>
				<td><%=request.getAttribute("memberId") %></td>
			</tr>
			<tr>
				<td>memberName</td>
				<td><%=request.getAttribute("memberName") %></td>
			</tr>
			<tr>
				<td>memberAddress</td>
				<td><%=request.getAttribute("memberAddress") %></td>
			</tr>
			<tr>
				<td>memberGender</td>
				<td><%=request.getAttribute("memberGender") %></td>
			</tr>
			<tr>
				<td>memberPhone</td>
				<td><%=request.getAttribute("memberPhone") %></td>
			</tr>
			<tr>
				<td>memberEmail</td>
				<td><%=request.getAttribute("memberEmail") %></td>
			</tr>
		</table>
	<div>	
		<a href="<%=request.getContextPath()%>/CashbookListByMonthController?" class="btn btn-secondary btn-sm" role="button">이전</a>
	</div>
</body>
</html>