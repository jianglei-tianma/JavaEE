package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 不需要注册，只有需要访问的才需要去配置
 * 	以后所有Servlet的基类
 * 		根据基类的请求参数method  得到方法名  ，使用方法名调用子类的方法
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集为UTF-8
		//request.setCharacterEncoding("UTF-8");
		//获取方法名
		String method = request.getParameter("method");
		//获取子类类型
		Class<? extends BaseServlet> cla = this.getClass();
		//通过方法名和参数类型 获取对应的方法  
		//  通过方法名  和  形参列表的类型 就可以确定一个唯一的方法
		try {
			//获取方法对象
			Method declaredMethod = cla.getDeclaredMethod(method, HttpServletRequest.class,HttpServletResponse.class);
			//调用方法！！！！！！！！！！！
			//参数1：通过哪个对象执行方法  参数2：实参
			declaredMethod.invoke(this, request,response);
		} catch (Exception e) {
			//e.printStackTrace();
			//将编译时异常转换成运行时异常向上抛
			throw new RuntimeException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
