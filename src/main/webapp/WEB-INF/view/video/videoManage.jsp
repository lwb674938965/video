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
				function deleteInfo(id){
					$.confirm({
					    title: '警告',
					    content: '您确定要删除吗!!!',
					    buttons: {
					        confirm:{
					        	 text: '十分肯定',
					        	 action:function () {
							 $.ajax({
								 dataType:"text",
								 data:{"id":id},
								 type:"post",
								 url:"${pageContext.request.contextPath }/video/deleteVideo.action",
								 success:function(msg){
									 if(msg=="success"){
										 location.reload();
									 }
								 }	 
							 });
					             }
					        },
					                     取消: function () {
					        },
					        
					    }
					});
					
				}					
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
							        confirm:{
							        	 text: '十分肯定',
							        	 action:function () {
											  $($("form")[1]).submit();
							             }
							        },
							                    取消: function () {
							        },
							        
							    }
							});
															
						}
					});										
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
			 <thead>
			     <tr>
			     	<th><input type="checkbox" class="aa"></th>
			     	<th>序号</th>
			     	<th>名称</th>
			     	<th>介绍</th>
			     	<th>讲师</th>
			     	<th>课程</th>
			     	<th>时长(秒)</th>
			     	<th>播放次数</th>
			     	<th>编辑</th>
			     	<th>删除</th>			     	
			     </tr>
			     </thead>
			     <c:if test="${not empty page.rows }">
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
			     	<td><a class="glyphicon glyphicon-trash abc" onclick="deleteInfo(${video.id})" role="button"></a></td>
			     </tr>
			   </c:forEach>
			      </c:if>
			   <c:if test="${empty page.rows }">
			      <tr><td  style="color: red;font-size: 20px;">当前没有数据</td></tr>
			   </c:if>
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
