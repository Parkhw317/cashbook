<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insert Member Form</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body class = "container">
	<div class="container p-3 my-3 bg-success text-white">
	<h1 align="center">insert Member</h1>
	</div>
	
	<form action="<%=request.getContextPath()%>/InsertMemberController" method="post">
		<table class="table table-bordered">
			<tr>
				<td>memberId</td>
				<td>
					<input type="text" name="memberId">
				</td>
			</tr>
			<tr>
				<td>memberPw</td>
				<td>
					<input type="password" name="memberPw">
				</td>
			</tr>
			<tr>
				<td>memberName</td>
				<td><input type="text" name="memberName"></td>
			</tr>
			<tr>
				<td>memberAdress</td>
				<td><input type="text" name="memberAddress"></td>
			</tr>
			<tr>
				<td>memberGender</td>
				<td>
					<input type="radio" name="memberGender" value="여성">여성
					<input type="radio" name="memberGender" value="남성">남성
				</td>
			</tr>
			<tr>
				<td>memberPhone</td>
				<td><input type="text" name="memberPhone"></td>
			</tr>
			<tr>
				<td>memberEmail</td>
				<td><input type="text" name="memberEmail"></td>
			</tr>
		</table>

		<button type="submit" class="btn btn-secondary btn-sm" >회원가입</button>
	</form>
</body>
</html>
