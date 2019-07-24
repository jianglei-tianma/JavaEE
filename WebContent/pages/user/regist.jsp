<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	$(function(){
		
		//正则表达式，用来检查字符串是否符合规则
		/* var reg = /a/;
		var flag = reg.test("cbbc");
		alert(flag); */
		
		
		//为提交按钮绑定单击响应函数
		$("#sub_btn").click(function(){
			
			//获取用户输入的用户名、密码、确认密码、电子邮件、验证码
			var name = $("[name=username]").val();
			var password = $("[name=password]").val();
			var repwd = $("[name=repwd]").val();
			var email = $("[name=email]").val();
			var code = $("[name=code]").val();
			
			//检查用户名、密码等信息是否符合规则
			//验证用户名
			//   /^[a-zA-Z0-9_-]{3,16}$/
			var nameReg = /^[a-zA-Z0-9_-]{3,16}$/;
			if(!nameReg.test(name)){
				//用户名格式不正确
				alert("请输入包含字母、数字、-、_且3-16位的用户名！");
				//取消默认行为
				return false; 
			}
			
			//验证密码
			var pwdReg = /^[a-zA-Z0-9_-]{6,18}$/;
			if(!pwdReg.test(password)){
				//密码格式不正确
				alert("请输入包含字母、数字、-、_且6-18位的密码！");
				//取消默认行为
				return false;
			}
			
			//验证确认密码
			if(repwd != password){
				//两次输入的密码不一致
				alert("两次输入的密码不一致！");
				return false;
			}
			
			//检查邮箱格式
			var emailReg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!emailReg.test(email)){
				alert("请输入正确的邮箱地址！");
				return false;
			}
			
			//检查验证码是否为空
			if(code == ""){
				alert("请输入验证码！");
				return false;
			}

		});
		//给验证码绑定单击事件
		$("#codeImg").click(function(){
			//当img标签中的src属性一改变，浏览器就会重新向src的地址再次发送请求
			$(this).attr("src","code.jpg?t="+Math.random());
		});
		//给输入用户名的文本框绑定change事件
		$("#username").change(function(){
			//获取用户输入的用户名
			var userInput = $(this).val();
			//设置请求地址
			var url = "UserServlet?method=checkUserName";
			//设置请求参数
			var params = {"username":userInput};

			$.post(url,params,function(data){
				//将响应消息设置到span
				$("#errorMsg").html(data);
				
				
			},"text");
			
		});
		
	});

</script>


<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
<%-- 								<span class="errorMsg"><%=request.getAttribute("msg")==null?"":request.getAttribute("msg") %></span> --%>
								<span id ="errorMsg" class="errorMsg">${msg }</span>
							</div>
							<div class="form">
								<form action="UserServlet" method="post">
									<input type="hidden" name="method" value="regist"/>
									<label>用户名称：</label>
									<input value="${param.username }" class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input value="${param.email }" class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" name="code"/>
									<img id="codeImg" alt="" src="code.jpg" style="float: right; margin-right: 40px;width: 80px;height: 40px">									
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>