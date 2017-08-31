/*
	这里放置前台功能通用的一些js方法
	主要有：登录窗口的切换、注册窗口的切换、登录功能(ajax)、注册功能(ajax)
*/

$(function(){
	
	//显示登录窗口
	$('#login_open').click(function(){
		$('#loginDiv').removeClass('hidden');
	});
	//显示注册窗口
	$('#reg_open').click(function(){
		$('#regDiv').removeClass('hidden');
	});
	//隐藏登录窗口
	$('#login_close').click(function(){
		$('#loginDiv').addClass('hidden');
	});
	//点击登录隐藏登录窗口
	$('#submitId').click(function(){
		$('#loginDiv').addClass('hidden');
	});
	//隐藏注册窗口
	$('#reg_close').click(function(){
		$('#regDiv').addClass('hidden');
	});
	//点击注册隐藏注册窗口
	$('#registId').click(function(){
		$('#regDiv').addClass('hidden');
	});
	//登录请求的处理
	$('#loginForm').validate({
		submitHandler:function(form){
			//ajax提交登录信息，并且返回登录结果
			//var email = $('#loginEmail').val();
			//var pwd = $('#loginPassword').val();
			//alert($('#loginForm').serialize());
			//使用ajax的post方法提交登录信息
			$.post("/maven-video/front/user/login.do",$('#loginForm').serialize(),function(msg){
				//alert(msg);
				if(msg=="success"){
					$.confirm({
	            	    title: '全体起立!!',
	            	    content: '欢迎大佬归来!!!',
	            	    buttons: {
	            	           确定: function () {
	            	        	   location.reload();
	            	        },
	            	           取消: function () {
	            	        	   location.reload();
	            	        },
	            	        
	            	    }
	            	});
					//登录成功,刷新页面
					
					
					
				}else{
					$.confirm({
	            	    title: '警告',
	            	    content: '用户名密码错误,请重新登录!!!',
	            	    buttons: {
	            	           确定: function () {
	            	        	   location.reload(); 
	            	        },
	            	           取消: function () {
	            	        	   location.reload();
	            	        },
	            	        
	            	    }
	            	});
				}
			},'text');
			
		},
		rules:{//写校验规则的
			email:{
				required:true,
				email:true,
				minlength:3
			},
			password:{
				required:true,
				minlength:3
			}
		},
		messages:{//写提示信息的
			email:"登录名称必须是已注册邮箱",
			password:'密码是必须填写的，3-30个字符'
		}
	});	

	//注册请求的处理
	$('#regForm').validate({
			
		submitHandler:function(form){
			//ajax提交注册信息，并且返回注册结果
			var email = $('#regEmail').val();
			var pwd = $('#regPsw').val();
			//alert($('#regForm').serialize());
			//使用ajax的post方法提交注册信息
			$.post('/maven-video/front/user/regist.do',$('#regForm').serialize(),function(result){
				//alert(result);
				if(result=="success"){
					$.confirm({
	            	    title: '客官客官',
	            	    content: '恭喜客官注册成功,快去登录吧!!!',
	            	    buttons: {
	            	           确定: function () {
	            	        	   location.reload();
	            	        },
	            	           取消: function () {
	            	        	   location.reload();
	            	        },
	            	        
	            	    }
	            	});
				}else{
					alert(result);
				}
			},'text');
			
		},
		rules:{//写校验规则的
			email:{
				required:true,
				email:true,
				minlength:3
			},
			password:{
				required:true,
				minlength:3
			},
			pswAgain:{
				required:true,
				equalTo:'#regPsw'
			}
		},
		messages:{//写提示信息的
			email:"注册账户必须是邮箱",
			password:'密码是必须填写的，3-30个字符',
			pswAgain:'两次密码必须输入一致'
		}
	});	
	
	
});