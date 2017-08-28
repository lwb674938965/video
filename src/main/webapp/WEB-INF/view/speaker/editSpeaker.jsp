<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>编辑主讲人-主讲人管理</title>
		<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet">
		<script src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
		<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<style>
			.container {
				border: 0px solid black;
				;
			}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="jumbotron">
				<h2>编辑主讲人-主讲人管理</h2>
			</div>
			<form class="form-horizontal" action="${pageContext.request.contextPath }/speaker/editSpeaker.action" method="post">
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">名字</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputEmail3" placeholder="主讲人姓名" name="speakerName" value="${sp.speakerName}">
					</div>
				</div>
				
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">职业</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputEmail3" placeholder="主讲人职业" name="speakerJob" value="${sp.speakerJob }">
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">头像图片</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputEmail3" placeholder="主讲人头像地址,网络图片" name="speakerHeadUrl" value="${sp.speakerHeadUrl }">
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">简介</label>
					<div class="col-sm-10">
						<!--<input type="text" class="form-control" id="inputEmail3" placeholder="请输入视频简介" name="">-->
						<textarea class="form-control"  placeholder="主讲人简介" name="speakerDescr" rows="3">${sp.speakerDescr }</textarea>
					</div>
				</div>
			     <input type="hidden" name="id" value="${sp.id }">
			     <input type="hidden" name="id" value="${sp.insertTime }">
			     
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary">保存</button>
						<a class="btn btn-default" href="javascript:history.go(-1)">返回列表</a>
					</div>
				</div>
			</form>
			
			
		</div>
	</body>
</html>
