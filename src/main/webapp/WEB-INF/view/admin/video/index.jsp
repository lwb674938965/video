<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title>后台登陆界面</title>
		<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet">
		<script src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
		<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath }/js/jquery.validate.min.js"></script>
		<script src="${pageContext.request.contextPath }/js/messages_zh.min.js"></script>
		
		<style>
			.container {
				border: 0px solid black;
				;
			}
		</style>
		<script type="text/javascript">
		$(function(){
			$("#formId").validate({
				rules:{
					username:{
						required:true
					},
					password:{
						required:true,
						minlength:3
					},
					prepassword:{
						required:true,
						equalTo:"input[name=password]"
					}
				},
				messages:{
					username:{
						required:"请输入帐号"
					},
					password:{
						required:"请输入密码",
						minlength:"密码小于3位"
					},
					prepassword:{
						required:"请再次输入密码",
						equalTo:"两次输入的密码不相同"
					}
				}
				
				
				
				
			});
			
			
		});
		
		
		</script>

	</head>

	<body>
		<div class="container">
		<div style="margin:20% 40%;">
			<div>
			<img src="${pageContext.request.contextPath }/img/logo.png" width="100%"/>
			</div>
			
			<form action="${pageContext.request.contextPath }/admin/video/login.action" method="post" id="formId"> 
				
				<input type="text" class="form-control" id="exampleInputEmail1"  name="username" placeholder="用户名" width="100%" value="admin"><br/>
				
				<input type="password" class="form-control" id="exampleInputPassword1"  name="password" placeholder="登录密码" value="admin" ><br/>
				
				<input type="password" class="form-control" id="exampleInputPassword1"  name="prepassword" placeholder="确认密码" value="admin"><br/>
				
				<button type="submit"  style="width: 100%;background-color: yellowgreen;height: 30px;">登录</button>

			</form>

		</div>
		</div>

	</body>

</html>