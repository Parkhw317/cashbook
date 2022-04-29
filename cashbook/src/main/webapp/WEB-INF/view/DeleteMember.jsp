<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Member Form</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body class = "container">
	<div class="container p-3 my-3 bg-success text-white">
	<h1 align="center">Delete Member</h1>
	</div>
	
	<form action="<%=request.getContextPath()%>/DeleteMemberController" method="post">
		<table class="table table-bordered">
			<br><h5 align="center">탈퇴를 위한 비밀번호를 입력해 주세요.</h5>
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
	<button type="submit" class="btn btn-outline-danger btn-sm">탈퇴</button>
	<a href="<%=request.getContextPath()%>/SelectMemberOneController?cashbookNo=<%=session.getAttribute("sessionMemberId")%>" class="btn btn-secondary btn-sm" role="button">탈퇴 취소</a>
	
	</form>
	
	
</body>
</html>
