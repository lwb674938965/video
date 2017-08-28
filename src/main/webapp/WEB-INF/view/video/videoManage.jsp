<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bobo" uri="http://zhiyou100.com/common/"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="UTF-8">
		<title>视频列表-视频管理</title>
		<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath }/css/jquery-confirm.css" rel="stylesheet">
		<script src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
		<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath }/js/jquery-confirm.js"></script>
	
		<style>
		    .container {
				border: 0px solid black;
			    height: 800px;
			}
		</style>
		<script type="text/javascript">3.
				var count=0;
				$(function(){
					$(".abc").click(function(){
						var x = confirm("是否确定删除");
						if(x==true){
							alert("删除成功");
						}else{
							alert("取消删除");
							return x;
						}
					});														
				});						
				$(function(){
					$(".aa").click(function(){
						var all = this.checked;
						$(".box").each(function(index,domElement){
							domElement.checked=all;
							count=index+1;
							if(all==true){
							    $(".badge").text(count);							
							}else{
								$(".badge").text(0);
								count=0;
							}
							
							
						});
						
													
					});
					
					$(".box").click(function(){
						if(this.checked==true){
							count++;
						}else{
							count--;
						}
						$(".badge").text(count);
						
						if(count == $(".box").length){
							$(".aa").prop("checked",true);
						}else{
							$(".aa").prop("checked",false);
						}
						
						
						
						
					});
					$(".deleteAll").click(function(){
						if(count==0){
							$.alert({
							    title: '警告',
							    content: '没选中删不了啊!!',
							});
						}else{
							$.confirm({
							    title: '警告',
							    content: '注意哦,是全部删除啊!!!',
							    buttons: {
							        confirm: function () {
							            $.alert('Confirmed!');
							        },
							        cancel: function () {
							            $.alert('Canceled!');
							        },
							        somethingElse: {
							            text: 'Something else',
							            btnClass: 'btn-blue',
							            keys: ['enter', 'shift'],
							            action: function(){
							                $.alert('Something else?');
							            }
							        }
							    }
							});
							/* var dele = confirm("你确定删除吗?");
							if(dele==true){
								alert("删除成功!");
								$($("form")[1]).submit();
							}else{
								alert("取消删除");
								return dele;
							} */									
						}
					})										
				});

		
		</script>
	</head>

	<body>
		<div class="container theme-showcase">

			<div class="jumbotron">
				<h2>视频列表-视频管理</h2>
			</div>

			<div>
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-form navbar-left">

					<a href="${pageContext.request.contextPath }/video/addVideo.action" class="btn btn-primary" >添加视频</a>
					<a  class="btn btn-primary deleteAll" role="button" >批量删除<span class="badge">0</span></a>
				   
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

					<form class="navbar-form navbar-right" action="${pageContext.request.contextPath }/video/videoManage.action" method="post">

						<input type="text" name="video_title" placeholder="视频标题" class="form-control" value="${video_title }"/>

						<select class="form-control" name="speaker_id">
							<option value="0">请选择主讲人</option>
							<c:forEach items="${speakerList }" var="sl" >
							<option value="${sl.id }" ${speaker_id==sl.id?"selected":"" }>${sl.speakerName }</option>
							</c:forEach>
						</select>

						<select class="form-control" name="course_id">
							<option value="0">请选择课程</option>
							<c:forEach items="${courseList }" var="cl">
							<option value="${cl.id }" ${course_id==cl.id?"selected":"" }>${cl.courseName }</option>
							</c:forEach>
						</select>

						<button type="submit" class="btn btn-primary">查询</button>

					</form>

				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
            <div style="margin-top: 10px;">
            <form action="${pageContext.request.contextPath }/video/deleteAll.action">
			<table class="table table-hover">
			       
			     <tr>
			     	<th class="col-md-0"><input type="checkbox" class="aa"></th>
			     	<th class="col-md-0">序号</th>
			     	<th class="col-md-1">名称</th>
			     	<th class="col-md-8">介绍</th>
			     	<th class="col-md-0">讲师</th>
			     	<th class="col-md-1">课程</th>
			     	<th class="col-md-1">时长(秒)</th>
			     	<th class="col-md-1">播放次数</th>
			     	<th class="col-md-0">编辑</th>
			     	<th class="col-md-0">删除</th>			     	
			     </tr>
			     <c:forEach items="${page.rows }" var="video" varStatus="status">
			     <tr>
			     	<td><input type="checkbox"  value="${video.id }" name="rowCheck" class="box"></td>
			     	<td>${status.count+(page.page-1)*5 }</td>
			     	<td>${video.videoTitle }</td>
			     	<td>${video.videoDescr }</td>
			     	<td>${video.sName }</td>
			     	<td>${video.cName }</td>
			     	<td>${video.videoLength }</td>
			     	<td>${video.videoPlayTimes }</td>
			     	<td><a class="glyphicon glyphicon-edit" role="button" href="${pageContext.request.contextPath }/video/editVideo.action?id=${video.id}"></a></td>
			     	<td><a class="glyphicon glyphicon-trash abc"  role="button" href="${pageContext.request.contextPath }/video/deleteVideo.action?id=${video.id}"></a></td>
			     </tr>
			   </c:forEach>
			</table>
			</form>
			</div>
			<div>
			   <div style="float:right;margin-right: 50px;margin-top: -10px; ">  
			 
                <bobo:page url="${pageContext.request.contextPath }/video/videoManage.action"></bobo:page>
                </div>
               <div style="float:right; margin-right:2px; margin-top: 15px;font-size: 20px;">共有${page.total }条</div>
           </div>
			

		</div>

	</body>

</html>