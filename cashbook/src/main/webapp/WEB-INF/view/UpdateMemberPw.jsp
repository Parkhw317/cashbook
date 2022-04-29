<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<%

	Member member = (Member)request.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Member Form</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body class = "container">
	<div class="container p-3 my-3 bg-success text-white">
	<h1 align="center">Update Member</h1>
	</div>
	
	<form action="<%=request.getContextPath()%>/UpdateMemberPwController" method="post">

		<table class="table table-bordered">
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
				<td>changePw</td>
				<td>
					<input type="password" name="changePw">
				</td>
			</tr>
			<tr>
				<td>memberName</td>
				<td>
					<input type="text" name="memberName" value="<%=member.getMemberName()%>">
				</td>
			</tr>
			<tr>
				<td>memberAddress</td>
				<td>
					<input type="text" name="memberAddress" value="<%=member.getMemberAddress()%>">
				</td>
			</tr>
			<tr>
				<td>memberGender</td>
				<td>
					<input type ="radio" name="memberGender" value="<%=member.getMemberGender()%>" checked="checked"><%=member.getMemberGender()%><br>
					<input type="radio" name="memberGender" value="여성">여성
					<input type="radio" name="memberGender" value="남성">남성
				</td>
			</tr>
			<tr>
				<td>memberPhone</td>
				<td><input type="text" name="memberPhone" value="<%=member.getMemberPhone()%>"></td>
			</tr>
			<tr>
				<td>memberEmail</td>
				<td><input type="text" name="memberEmail" value="<%=member.getMemberEmail()%>"></td>
			</tr>
		</table>
		
		<button type="submit" class="btn btn-outline-primary btn-sm">회원정보 수정</button>
		<a href="<%=request.getContextPath()%>/SelectMemberOneController?cashbookNo=<%=session.getAttribute("sessionMemberId")%>" class="btn btn-secondary btn-sm" role="button">회원정보 수정취소</a>
	</form>
</body>
</html>
