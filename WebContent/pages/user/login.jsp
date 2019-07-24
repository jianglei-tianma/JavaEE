<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员登录页面</title>
<!-- 使用base标签解决转发出现的路径问题 （相对路径失效,不影响绝对路径）
	以后页面中使用的相对路径都会和base中的路径拼接
-->
<%@ include file="/WEB-INF/include/base.jsp" %>
</head>
<body>
	
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>尚硅谷会员</h1>
								<a href="pages/user/regist.jsp">立即注册</a>
							</div>
							<div class="msg_cont">
								<b></b>
<!-- 								<span class="errorMsg">请输入用户名和密码</span> -->
<%-- 								<span class="errorMsg"><%=request.getAttribute("msg")==null?"请输入用户名和密码":request.getAttribute("msg") %></span> --%>
								<span class="errorMsg">${empty msg?"请输入用户名和密码":msg }</span>
							</div>						
							<div class="form">
								<!-- action需要将表单数据提交给LoginServlet处理 -->
								<!--提交用户隐私信息时使用post请求提交  -->
								<form action="UserServlet" method="post">
									<!-- 不在页面中显示此表单项 设置name属性后，值也会提交 -->
									<input type="hidden" name="method" value="login"/>
									<label>用户名称：</label>
									<input value="${param.username }" class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
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