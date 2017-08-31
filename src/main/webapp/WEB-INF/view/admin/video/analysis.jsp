<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>统计分析</title>
		<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet">
		<script src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
		<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath }/js/echarts-all.js"></script>
		<script src="${pageContext.request.contextPath }/js/echarts.js"></script>
		<script type="text/javascript">
			 $(function(){
				var theData = "${data}".split(",");
				var theTimes = "${time}".split(",");
				 // 基于准备好的dom，初始化echarts图表
		        var myChart = echarts.init(document.getElementById('main'));
		        // 指定图表的配置项和数据
               var option = {
            		   title: {
                           text: '课程平均播放次数',
                           subtext: '数据来源:zhiyou100.com',
                           sublink:'http://www.zhiyou100.com',
                           x: 'center',
                           textStyle: {
                               color: '#323232',
                               fontWeight: 'normal',
                               fontSize: 20
                           },
                           subtextStyle:{
                               color: '#323232'
                           }
                       },
		        		
		        		tooltip: {
                           show: true
                       },
                       legend: {
                    	   y: 'bottom',
                    	   data:['平均播放次数']
                       },
                       xAxis : [
                           {
                               type : 'category',
                               data : theData
                           }
                       ],
                       yAxis : {
                      	 name: '平均播放次数(times)',
                         nameLocation: 'middle',
                         nameGap: 40,
                         nameTextStyle: {
                             color: '#888',
                             //fontSize: 16
                         },
                         axisLine: {
                             show: false
                         },
                         axisTick: {
                             show: false
                         }
                    },
                       series : [
                           {
                               "name":"平均播放次数",
                               "type":"bar",
                               "data":theTimes
                           }
                       ]
		            
		        };
		        // 使用刚指定的配置项和数据显示图表。
		        myChart.setOption(option); 
			});
	</script>				
	</head>
	<body>
	        <jsp:include page="/WEB-INF/view/admin/admin.jsp">
			       <jsp:param value="analysis" name="fromJsp"/>
			</jsp:include>
		<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div class="container">
		<div class="jumbotron">
  			<h2>统计 - 统计分析</h2>
		</div>
		<div class="row">
			<div id="main" style="min-width:400px;height:400px"></div>
		</div>
	</div>
	 
</body>
</html>
