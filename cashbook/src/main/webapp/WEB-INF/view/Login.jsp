<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

</head>
<body>

<div class="container">
    <div class="row">
    	<div class="col-md-4 col-md-offset-4" style="margin-top: 20%;">
    		<div class="panel panel-default">
			  	<div class="panel-heading">
			    	<h3 class="panel-title" style="text-align:center;">로그인</h3>
			 	</div>
			  	<div class="panel-body">
			    	<form accept-charset="UTF-8" role="form" action="<%=request.getContextPath()%>/LoginController" method="post">
                    <fieldset>
			    	  	<div class="form-group">
			    		    <input class="form-control" placeholder="아이디" name="memberId" type="text">
			    		</div>
			    		<div class="form-group">
			    			<input class="form-control" placeholder="비밀번호" name="memberPw" type="password" value="">
			    		</div>
			    		<input class="btn btn-lg btn-success btn-block" type="submit" value="로그인">
			    	</fieldset>
			      	</form>
                      <hr/>
                    <center><h4>OR</h4></center>                   
                    <a href="<%=request.getContextPath()%>/InsertMemberController" class="btn btn-lg btn-info btn-block" role="button">회원가입</a>
			    </div>
			</div>
		</div>
	</div>
</div>
</body>
</html>

