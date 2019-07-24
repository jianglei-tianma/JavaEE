package com.atguigu.bookstore.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter extends HttpFilter implements Filter {
	
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//获取session对象
		HttpSession session = request.getSession();
		//获取session域中的用户
		//String user = (String)session.getAttribute("user");
		Object user = session.getAttribute("user");
		if(user != null){
			//证明已经登陆，放行请求
			chain.doFilter(request, response);
		}else{
			//证明用户没有登陆，设置一个错误消息并转发到登陆页面
			request.setAttribute("msg", "该操作需要先登陆！");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}
		
	}
}
