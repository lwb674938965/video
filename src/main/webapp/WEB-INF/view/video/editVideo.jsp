<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>视频添加-视频管理</title>
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
				<h2>编辑视频信息-视频管理</h2>
			</div>
			<form class="form-horizontal" action="${pageContext.request.contextPath }/video/editVideo.action" method="post">
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">视频标题</label>
					<div class="col-sm-10">
						<input type="email" class="form-control" id="inputEmail3" placeholder="视频标题" name="videoTitle" value="${video.videoTitle }">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">主讲人</label>
					<div class="col-sm-10">
						<select class="form-control" id="inputPassword3" name="speakerId" >
							<option value="">请选择主讲人</option>
							<c:forEach items="${editspeakerList }" var="sl" >
							<option value="${sl.id }">${sl.speakerName }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">所属课程</label>
					<div class="col-sm-10">
						<select class="form-control" id="inputPassword3" name="courseId">
							<option value="0">请选择课程</option>
							<c:forEach items="${editcourseList }" var="cl">
							<option value="${cl.id }">${cl.courseName }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">视频时长</label>
					<div class="col-sm-10">
						<input type="email" class="form-control" id="inputEmail3" placeholder="视频时长(秒)" name="videoLength" value="${video.videoLength }">
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">封面图片</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputEmail3" placeholder="视频封面图片地址,网络图片" name="videoImageUrl" value="${video.videoImageUrl }">
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">视频播放地址</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputEmail3" placeholder="视频播放地址,网络地址" name="videoUrl" value="${video.videoUrl }">
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">视频简介</label>
					<div class="col-sm-10">
						<!--<input type="text" class="form-control" id="inputEmail3" placeholder="请输入视频简介" name="">-->
						<textarea class="form-control" id="inputEmail3" placeholder="请输入视频简介" name="videoDescr" rows="3">${video.videoDescr }</textarea>
					</div>
				</div>
			     <input type="hidden" name="id" value="${video.id }">
			     <input type="hidden" name="insertTime" value="${video.insertTime }">
			     <input type="hidden" name="videoPlayTimes" value="${video.videoPlayTimes }">
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