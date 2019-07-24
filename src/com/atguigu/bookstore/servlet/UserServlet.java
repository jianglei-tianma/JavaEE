package com.atguigu.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.service.impl.UserServiceImpl;

/**
 * 处理所有和用户相关的请求 如何区分用户的请求是注册还是登陆 在页面表单中添加一个 隐藏表单项 name=method value=需要的操作
 * 
 * 
 * 提供一个BaseServlet 以后的Servlet 都继承自BaseServlet 例如： UserServlet extends
 * BaseServlet BaseServlet doget() dopost 子类不需要在写doget和dopost了 在doget中获取
 * 请求参数method this代表 UserServlet Class cls = this.getClass();
 * cls.getDeclaredMethod(methodName,方法的参数类型...);//获取方法
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserService service = new UserServiceImpl();
	
	
	//使用Ajax验证用户名是否可用
	protected void checkUserName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取用户名
		String userName = request.getParameter("username");
		//封装User对象
		User user = new User(null,userName,null,null);
		//调用service的方法验证用户名是否可用
		boolean regist = service.regist(user);
		response.setContentType("text/html;charset=utf-8");
		if(regist){
			//证明用户名可用
			response.getWriter().write("<font style='color:green'>用户名可用!</font>");
		}else{
			//证明用户名已存在
			response.getWriter().write("用户名已存在！");
		}
		
	}
	
	// 用户注销的方法
	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取Session对象
		HttpSession session = request.getSession();
		// 使Session对象失效
		session.invalidate();
		// 重定向到首页
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收用户请求
		// 1.获取用户名
		String username = request.getParameter("username");
		// 2.获取密码
		String password = request.getParameter("password");
		// 处理用户请求
		User user = service.login(new User(null, username, password, null));
		// 响应 如果用户不存在 转发到login.html
		// 如果用户存在 重定向到 login-success.html
		if (user == null) {
			// 设置一个错误消息，并放到request域中
			request.setAttribute("msg", "用户名或密码不正确！");
			request.getRequestDispatcher("pages/user/login.jsp").forward(request, response);
			;
		} else {
			// 将用户放到session域中
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/pages/user/login_success.jsp");
		}
	}

	protected void regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取用户请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		//封装user对象
		User user = new User(null,username,password,email );
		
		// 获取用户输入的验证码
		String reqCode = request.getParameter("code");
		// 获取session域中的验证码
		HttpSession session = request.getSession();
		String sessCode = (String) session.getAttribute("code");
		// 将两者进行对比
		if (reqCode != null && reqCode.equals(sessCode)) {
			// 验证码正确，正常处理请求并将session域中的验证码移除
			session.removeAttribute("code");
			// 处理用户请求 调用service
			boolean b = service.regist(user);
			if (b) {
				//将用户保存到数据库中
				service.saveUser(user);
				
				// 注册成功，重定向到成功页面
				response.sendRedirect(request.getContextPath() + "/pages/user/regist_success.jsp");
			} else {
				// 设置一个错误消息并放到request域中
				request.setAttribute("msg", "用户名已存在！");
				// 注册失败，转发到注册页面继续注册
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			}
		} else {
			// 验证码不正确，设置一个错误消息并放到request域中
			request.setAttribute("msg", "验证码不正确！");
			// 转发到注册页面继续注册
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
		}
	}
}
