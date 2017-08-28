<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>视频管理系统</title>
		<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet">
		<script src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
		<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<style>
			.container-fluid {
				border: 0px solid black;
				margin-left: 20%;
				margin-right: 20%;
			}
			.container {
				border: 0px solid black;
				;
			}
		</style>
	</head>

	<body>
		<nav class="navbar navbar-inverse">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">视频管理系统</a>
				</div>

				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li>
							<a target="pageBox" href="${pageContext.request.contextPath }/video/videoManage.action">视频管理</a>
						</li>
						<li>
							<a target="pageBox" href="${pageContext.request.contextPath }/speaker/speakerList.action">主讲人管理</a>
						</li>
						<li>
							<a target="pageBox" href="${pageContext.request.contextPath }/course/courseList.action">课程管理</a>
						</li>
						<li>
							<a target="pageBox" href="${pageContext.request.contextPath }/video/videoAnalysis.action">统计分析</a>
						</li>

					</ul>

					<div class="nav navbar-nav navbar-right">
						<div class="navbar-brand">
							<a href="#" >${adminName.loginName }</a><a href="${pageContext.request.contextPath }/video/exitController.action" class="glyphicon glyphicon-share" ></a>				
					    </div>
					</div>
				</div>
			</div>
		</nav>

		<div class="embed-responsive embed-responsive-4by3" >
			<div class="container">
			<iframe class="embed-responsive-item" src="${pageContext.request.contextPath }/video/videoManage.action" name="pageBox"></iframe>
		    </div>
		</div>
	</body>

</html>