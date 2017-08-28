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
		<style type="text/css">
		.container {
				border: 0px solid black;
				
			}
		.aa{
			height: 400px;
				margin-left: 17%;
				margin-right: 17%;
			}
		
		</style>				
	</head>
	<body>
		<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div class="container">
	<div class="jumbotron">
				<h2>统计-统计分析</h2>
	</div>	
	<div style="margin-left: 43%;">
	  <h4>课程平均播放次数</h4>	
	  数据来源:<a href="http://www.zhiyou100.com/bigdata/index.html">zhiyou100.com</a>	
	</div>
	</div>
	
    <div id="main"  class="aa"></div>
   
    <!-- ECharts单文件引入 -->
    
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('main')); 
        
        var option = {
            tooltip: {
                show: true
            },
            legend: {
                data:['平均播放次数'],
                y:"bottom"
            },
            xAxis : [
                {
                    type : 'category',
                    data : ["${str[0]}","${str[1]}","${str[2]}","${str[3]}","${str[4]}"]
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    "name":"销量",
                    "type":"bar",
                    "data": ${arr}
                }
            ]
        };

        // 为echarts对象加载数据 
        myChart.setOption(option); 
    </script>
    
</body>
</html>
