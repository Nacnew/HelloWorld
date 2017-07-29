package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionServlet extends HttpServlet{

	public void service(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException{
		System.out.println("service()");
		request.setCharacterEncoding("utf-8");
		//获取请求资源路径
		//http://ip:port/ajax-day01/check.do
		String uri=request.getRequestURI();
		System.out.println("uri:"+uri);
		//分析请求资源路径
		String action=uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		//截取字符串，含头不含尾
		System.out.println("action:"+action);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		//依据分析的结果，进行不同的处理
		if("/check_admin".equals(action)){
			String adminCode=request.getParameter("adminCode");
			System.out.println("adminCode:"+adminCode);
			if("King".equals(adminCode)){
				out.println("账号已经存在");
			}else{
				out.println("可以使用");
			}
		}
	}
}
